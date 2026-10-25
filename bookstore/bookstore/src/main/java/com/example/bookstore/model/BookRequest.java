//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.model;

import jakarta.persistence.*;

@Entity
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_offer_id")
    private BookOffer bookOffer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
    private boolean isSelected;
    private boolean isNotified;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookOffer getBookOffer() {
        return bookOffer;
    }

    public void setBookOffer(BookOffer bookOffer) {
        this.bookOffer = bookOffer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean isNotified) {
        this.isNotified = isNotified;
    }
}
