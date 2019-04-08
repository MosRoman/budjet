package com.gmail.morovo1988.budjet.web.api.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.dto.requests.LoginReq;
import com.gmail.morovo1988.budjet.security.jwt.JWTConfigurer;
import com.gmail.morovo1988.budjet.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/security")
public class UserJWTResource {

    private final TokenProvider tokenProvider;


//    private final FacebookAuthenticationProvider facebookAuthenticationProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserJWTResource(
            final TokenProvider tokenProvider,
//            final FacebookAuthenticationProvider facebookAuthenticationProvider,
            final AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
//        this.facebookAuthenticationProvider = facebookAuthenticationProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/authenticate/email")
    public ResponseEntity<JWTToken> authorize(final @Valid @RequestBody LoginReq loginReq) {

        final UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final boolean rememberMe = loginReq.isRememberMe() == null ? false : loginReq.isRememberMe();

        final String jwt = this.tokenProvider.createToken(authentication, rememberMe);
        final HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new UserJWTResource.JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

//    @PostMapping("/authenticate/facebook")
//    public ResponseEntity<JWTToken> authorizeFacebook(final @Valid @RequestBody FacebookReg regFacebook) {
//
//        final UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(regFacebook.getFacebookId(), regFacebook.getEmail());
//
//        final Authentication authentication = this.facebookAuthenticationProvider.authenticate(authToken);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        final boolean rememberMe = regFacebook.isRememberMe() == null ? false : regFacebook.isRememberMe();
//
//        final String jwt = this.tokenProvider.createToken(authentication, rememberMe);
//        final HttpHeaders httpHeaders = new HttpHeaders();
//
//        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
//        return new ResponseEntity<>(new UserJWTResource.JWTToken(jwt), httpHeaders, HttpStatus.OK);
//    }

    @GetMapping("/authenticate")
    public String isAuthenticated(final HttpServletRequest request) {
        return request.getRemoteUser();
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    private static class JWTToken {

        private String idToken;

        JWTToken(final String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return this.idToken;
        }

        void setIdToken(final String idToken) {
            this.idToken = idToken;
        }
    }
}
