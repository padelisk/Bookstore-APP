//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.repository;

import com.example.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}


