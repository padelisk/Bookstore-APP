//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.service;

import com.example.bookstore.model.BookRequest;

import java.util.List;

public interface BookRequestService {
    void save(BookRequest bookRequest);
    List<BookRequest> findByBookOfferId(Long bookOfferId);
    BookRequest findById(Long id);
    void selectRequest(Long requestId);

}


