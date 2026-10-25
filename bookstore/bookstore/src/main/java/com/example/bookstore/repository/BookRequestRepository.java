//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.repository;

import com.example.bookstore.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
    List<BookRequest> findByBookOfferId(Long bookOfferId);
}
