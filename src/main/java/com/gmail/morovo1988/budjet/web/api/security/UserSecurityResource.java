package com.gmail.morovo1988.budjet.web.api.security;


import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.exceptions.ValidationNewUserReqException;
import com.gmail.morovo1988.budjet.services.USerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/security")
public class UserSecurityResource {

    private final USerServiceImpl securityService;

    @Autowired
    public UserSecurityResource(
            final USerServiceImpl securityService
    ) {
        this.securityService = securityService;
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(
            @Valid @RequestBody final User req,
            final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            final String errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::toString)
                    .collect(Collectors.joining(","));

            throw new ValidationNewUserReqException(errors);
        }
        this.securityService.createUser(req);
    }

//    @PostMapping("register/facebook")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void registerAccountFromFacebook(@Valid @RequestBody final NewUserFacebookReq req,
//                                            final BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//
//            final String errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(ObjectError::toString)
//                    .collect(Collectors.joining(","));
//
//            throw new ValidationNewUserReqException(errors);
//        }
//        this.securityService.registerFromFacebook(req);
//
//    }
//
//    @GetMapping(path = "/remind_password")
//    public void remindPassword(@RequestParam("email") final String email) {
//        this.securityService.remindPassword(email);
//    }
}
