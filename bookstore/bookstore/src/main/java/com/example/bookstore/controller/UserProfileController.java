//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.controller;

import com.example.bookstore.model.BookOffer;
import com.example.bookstore.model.BookRequest;
import com.example.bookstore.model.User;
import com.example.bookstore.service.BookOfferService;
import com.example.bookstore.service.BookRequestService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/user-profile")
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BookOfferService bookOfferService;

    @Autowired
    private BookRequestService bookRequestService;

    @GetMapping
    public String showUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            return "redirect:/login?error=true";
        }

        List<BookOffer> allBookOffers = bookOfferService.findAll();
        List<BookOffer> userBookOffers = bookOfferService.findByUserId(user.getId());

        Map<Long, String> takenByUsers = new HashMap<>();
        for (BookOffer bookOffer : userBookOffers) {
            if (bookOffer.isTaken()) {
                for (BookRequest request : bookOffer.getBookRequests()) {
                    if (request.isSelected()) {
                        takenByUsers.put(bookOffer.getId(), request.getUser().getUsername());
                        break;
                    }
                }
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("allBookOffers", allBookOffers);
        model.addAttribute("userBookOffers", userBookOffers);
        model.addAttribute("newBookOffer", new BookOffer());
        model.addAttribute("newBookRequest", new BookRequest());
        model.addAttribute("takenByUsers", takenByUsers);

        return "user-profile";
    }

    @PostMapping("/add-book-offer")
    public String addBookOffer(@ModelAttribute BookOffer newBookOffer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            return "redirect:/login?error=true";
        }

        newBookOffer.setUser(user);
        bookOfferService.save(newBookOffer);
        return "redirect:/user-profile";
    }

    @PostMapping("/request-book-offer/{bookOfferId}")
    public String requestBookOffer(@PathVariable Long bookOfferId, @RequestParam String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            return "redirect:/login?error=true";
        }

        BookOffer bookOffer = bookOfferService.findById(bookOfferId);
        if (bookOffer == null) {
            logger.error("Book offer not found: {}", bookOfferId);
            return "redirect:/user-profile";
        }

        if (bookOffer.isTaken()) {
            logger.error("Book offer already taken: {}", bookOfferId);
            return "redirect:/user-profile";
        }

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookOffer(bookOffer);
        bookRequest.setUser(user);
        bookRequest.setMessage(message);
        bookRequestService.save(bookRequest);

        return "redirect:/user-profile";
    }

    @GetMapping("/view-requests/{bookOfferId}")
    public String viewBookOfferRequests(@PathVariable Long bookOfferId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            return "redirect:/login?error=true";
        }

        BookOffer bookOffer = bookOfferService.findById(bookOfferId);
        if (bookOffer == null || !bookOffer.getUser().getId().equals(user.getId())) {
            logger.error("Book offer not found or user not authorized: {}", bookOfferId);
            return "redirect:/user-profile";
        }

        List<BookRequest> bookRequests = bookRequestService.findByBookOfferId(bookOfferId);
        model.addAttribute("bookRequests", bookRequests);
        model.addAttribute("bookOfferId", bookOfferId);

        return "book-requests";
    }

    @PostMapping("/select-request/{bookRequestId}")
    public String selectBookRequest(@PathVariable Long bookRequestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            return "redirect:/login?error=true";
        }

        BookRequest bookRequest = bookRequestService.findById(bookRequestId);
        if (bookRequest == null || !bookRequest.getBookOffer().getUser().getId().equals(user.getId())) {
            logger.error("Book request not found or user not authorized: {}", bookRequestId);
            return "redirect:/user-profile";
        }

        bookRequestService.selectRequest(bookRequestId);


        return "redirect:/user-profile/view-requests/" + bookRequest.getBookOffer().getId();
    }

    @PostMapping("/delete-book-offer/{bookOfferId}")
    public String deleteBookOffer(@PathVariable Long bookOfferId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            return "redirect:/login?error=true";
        }

        BookOffer bookOffer = bookOfferService.findById(bookOfferId);
        if (bookOffer == null || !bookOffer.getUser().getId().equals(user.getId())) {
            logger.error("Book offer not found or user not authorized: {}", bookOfferId);
            return "redirect:/user-profile";
        }

        bookOfferService.deleteById(bookOfferId);

        return "redirect:/user-profile";
    }
}
