package com.gmail.morovo1988.budjet.exceptions;

public class UserNotFoundException extends RuntimeException {
       public UserNotFoundException() {  super("user not found");
    }
}
