package com.gmail.morovo1988.budjet.dto.requests;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class LoginReq {


    @NotNull
    @Size(min = 1, max = 50)
    private String email;

    @Size(min = 8, max = 4100)
    private String password;

    private Boolean rememberMe;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(final Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginVM{" +
                "email='" + this.email + '\'' +
                ", rememberMe=" + this.rememberMe +
                '}';
    }
}
