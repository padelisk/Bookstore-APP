//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String fullName;
    private String address;
    private int age;
    private String phoneNumber;

    @ElementCollection
    private List<String> preferredCategories;
    private String favoriteAuthors;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookOffer> bookOffers;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getPreferredCategories() {
        return preferredCategories;
    }

    public void setPreferredCategories(List<String> preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

    public String getFavoriteAuthors() {
        return favoriteAuthors;
    }

    public void setFavoriteAuthors(String favoriteAuthors) {
        this.favoriteAuthors = favoriteAuthors;
    }

    public List<BookOffer> getBookOffers() {
        return bookOffers;
    }

    public void setBookOffers(List<BookOffer> bookOffers) {
        this.bookOffers = bookOffers;
    }
}






