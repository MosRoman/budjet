package com.gmail.morovo1988.budjet.validations;


import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueEmailForNewUser implements Validator {

    private final UserRepository repo;

    @Autowired
    public UniqueEmailForNewUser(final UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User req = (User) target;
        if (this.repo.existsUserByEmail(req.getEmail())) {
            errors.rejectValue("email", "already exist", "This email already exist");
        }
    }
}
