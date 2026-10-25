//PANTELEIMON KAKOS AM:5060 ,TSAFONIS ALEKSNDROS AM:4934
package com.example.bookstore.service;

import com.example.bookstore.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);

}




