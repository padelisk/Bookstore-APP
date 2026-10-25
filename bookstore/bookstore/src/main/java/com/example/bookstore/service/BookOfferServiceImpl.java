//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.service;

import com.example.bookstore.model.BookOffer;
import com.example.bookstore.repository.BookOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOfferServiceImpl implements BookOfferService {

    @Autowired
    private BookOfferRepository bookOfferRepository;

    @Override
    public void save(BookOffer bookOffer) {
        bookOfferRepository.save(bookOffer);
    }

    @Override
    public List<BookOffer> findAll() {
        return bookOfferRepository.findAll();
    }

    @Override
    public List<BookOffer> findByUserId(Long userId) {
        return bookOfferRepository.findByUserId(userId);
    }

    @Override
    public BookOffer findById(Long id) {
        return bookOfferRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        bookOfferRepository.deleteById(id);
    }
}



