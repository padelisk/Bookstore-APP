//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.repository;

import com.example.bookstore.model.BookOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookOfferRepository extends JpaRepository<BookOffer, Long> {
    List<BookOffer> findByUserId(Long userId);
}

