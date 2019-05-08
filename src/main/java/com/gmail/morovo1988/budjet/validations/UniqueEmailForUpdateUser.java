package com.gmail.morovo1988.budjet.validations;


import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.dto.requests.UpdateUserReq;
import com.gmail.morovo1988.budjet.exceptions.UserNotFoundException;
import com.gmail.morovo1988.budjet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueEmailForUpdateUser implements Validator {

    private final UserRepository repo;

    @Autowired
    public UniqueEmailForUpdateUser(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateUserReq.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UpdateUserReq currentUser = (UpdateUserReq) target;

        if (this.isNew(currentUser)) {
            this.checkEmail(currentUser.getEmail(), errors);
            return;
        }

        User user = this.loadUserById(currentUser.getId());

        if (this.isEqualsEmail(currentUser.getEmail(), user.getEmail())) {
            return;
        }

        this.checkEmail(currentUser.getEmail(), errors);
    }

    private boolean isNew(final UpdateUserReq user) {
        return user.getId() == null;
    }

    private void checkEmail(String email, Errors errors) {
        if (this.repo.existsUserByEmail(email)) {
            errors.rejectValue("email", "already exist", "This email already exist");
        }
    }

    private boolean isEqualsEmail(final String currentUserEmail, final String userEmail) {
        return userEmail.equalsIgnoreCase(currentUserEmail);
    }

    private User loadUserById(Long userId) {
        return this.repo.findOneById(userId).orElseThrow(UserNotFoundException::new);
    }
}

