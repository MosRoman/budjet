package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user) ;

    User loadUserByEmail(String email);

    List<User> findUsers();
}
