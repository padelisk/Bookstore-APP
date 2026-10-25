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
public class Test0 {

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
        mockMvc.perform(post("/user-profile/add-book-offer")
                        .with(SecurityMockMvcRequestPostProcessors.user("kostas7").password("password1"))
                        .param("title", "Test Book1")
                        .param("author", "Test Author1")
                        .param("category", "Fiction")
                        .param("summary", "Test Summary")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));
        mockMvc.perform(post("/user-profile/add-book-offer")
                        .with(SecurityMockMvcRequestPostProcessors.user("kostas7").password("password1"))
                        .param("title", "Test Book2")
                        .param("author", "Test Author2")
                        .param("category", "Fiction")
                        .param("summary", "Test Summary")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user-profile"));
    }
}
