//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.controller;

import com.example.bookstore.model.BookOffer;
import com.example.bookstore.model.BookRequest;
import com.example.bookstore.model.User;
import com.example.bookstore.service.BookOfferService;
import com.example.bookstore.service.BookRequestService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookRequestController {

    @Autowired
    private BookOfferService bookOfferService;

    @Autowired
    private BookRequestService bookRequestService;

    @Autowired
    private UserService userService;

    @PostMapping("/book-offer/{bookOfferId}/request")
    public String requestBook(@PathVariable Long bookOfferId, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        BookOffer bookOffer = bookOfferService.findById(bookOfferId);

        if (user != null && bookOffer != null) {
            BookRequest bookRequest = new BookRequest();
            bookRequest.setBookOffer(bookOffer);
            bookRequest.setUser(user);
            bookRequestService.save(bookRequest);
            return "redirect:/user-profile";
        } else {
            model.addAttribute("error", "Failed to request book");
            return "error";
        }
    }
}

