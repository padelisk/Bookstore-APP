//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.service;

import com.example.bookstore.model.BookOffer;
import com.example.bookstore.model.BookRequest;
import com.example.bookstore.repository.BookRequestRepository;
import com.example.bookstore.repository.BookOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRequestServiceImpl implements BookRequestService {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Autowired
    private BookOfferRepository bookOfferRepository;

    @Override
    public void save(BookRequest bookRequest) {
        bookRequestRepository.save(bookRequest);
    }

    @Override
    public List<BookRequest> findByBookOfferId(Long bookOfferId) {
        return bookRequestRepository.findByBookOfferId(bookOfferId);
    }

    @Override
    public BookRequest findById(Long id) {
        return bookRequestRepository.findById(id).orElse(null);
    }

    @Override
    public void selectRequest(Long requestId) {
        BookRequest selectedRequest = bookRequestRepository.findById(requestId).orElse(null);
        if (selectedRequest != null) {
            selectedRequest.setSelected(true);
            selectedRequest.setNotified(true);
            bookRequestRepository.save(selectedRequest);


            BookOffer bookOffer = selectedRequest.getBookOffer();
            bookOffer.setTaken(true);
            bookOfferRepository.save(bookOffer);
        }
    }

}

