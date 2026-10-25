//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.Test;

import com.example.bookstore.model.BookOffer;
import com.example.bookstore.model.BookRequest;
import com.example.bookstore.repository.BookOfferRepository;
import com.example.bookstore.repository.BookRequestRepository;
import com.example.bookstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class Test1 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookOfferRepository bookOfferRepository;

    @Autowired
    private BookRequestRepository bookRequestRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        userRepository.deleteAll();
        bookOfferRepository.deleteAll();
        bookRequestRepository.deleteAll();
    }

    @Test
    public void testUserScenario() throws Exception {
        // Register first user
        mockMvc.perform(post("/register")
                        .param("username", "kostas7")
                        .param("password", "password1")
                        .param("fullName", "Konstantinos Papadopoulos")
                        .param("address", "Dodonis Ioannina")
                        .param("age", "30")
                        .param("phoneNumber", "6985476211")
                        .param("preferredCategories", "Science")
                        .param("favoriteAuthors", "Author")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // User1 logs in and adds a book offer
        mockMvc.perform(post("/login")
                        .param("username", "kostas7")
                        .param("password", "password1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));

        mockMvc.perform(post("/user-profile/add-book-offer")
                        .with(SecurityMockMvcRequestPostProcessors.user("kostas7").password("password1"))
                        .param("title", "Test Book")
                        .param("author", "Test Author")
                        .param("category", "Fiction")
                        .param("summary", "Test Summary")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));

        // Register second user
        mockMvc.perform(post("/register")
                        .param("username", "panos6")
                        .param("password", "password2")
                        .param("fullName", "Panayiotis Panayiotoy")
                        .param("address", "Pargis Ioannina")
                        .param("age", "25")
                        .param("phoneNumber", "6965478214")
                        .param("preferredCategories", "Fiction")
                        .param("favoriteAuthors", "Author Two")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // User2 logs in
        mockMvc.perform(post("/login")
                        .param("username", "panos6")
                        .param("password", "password2")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));

        // Find the added book offer
        BookOffer bookOffer = bookOfferRepository.findAll().get(0);



        // User2 requests the book
        mockMvc.perform(post("/user-profile/request-book-offer/" + bookOffer.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("panos6").password("password2"))
                        .param("message", "tha ithela na paro to biblio")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));
        BookRequest bookRequest = bookRequestRepository.findAll().get(0);
         mockMvc.perform(post("/login")
                        .param("username", "kostas7")
                        .param("password", "password1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));

          mockMvc.perform(post("/user-profile/view-requests/" + bookOffer.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("kostas7").password("password1"))

                        .with(csrf()));


          mockMvc.perform(post("/user-profile/select-request/" + bookRequest.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("kostas7").password("password1"))

                        .with(csrf()));

    }

}




