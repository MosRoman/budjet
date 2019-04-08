package com.gmail.morovo1988.budjet.exceptions;


public class ValidationNewUserReqException extends RuntimeException {
    public ValidationNewUserReqException(final String errors) {
        super(errors);
    }
}
