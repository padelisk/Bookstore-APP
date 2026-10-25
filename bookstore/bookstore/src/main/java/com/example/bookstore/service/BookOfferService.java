//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.service;

import com.example.bookstore.model.BookOffer;

import java.util.List;

public interface BookOfferService {
    void save(BookOffer bookOffer);
    List<BookOffer> findAll();
    List<BookOffer> findByUserId(Long userId);
    BookOffer findById(Long id);
    void deleteById(Long id);
}




