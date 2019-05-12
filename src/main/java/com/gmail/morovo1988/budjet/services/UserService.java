package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.dto.requests.UpdateUserReq;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user) ;

    User loadUserByEmail(String email);

    List<User> findUsers();

    void deleteUserById(Long id);

    Optional<UpdateUserReq> loadUpdateUserById(Long userId);

    User updateUser(UpdateUserReq updatedUser);

    User findUser(Long id);
}
