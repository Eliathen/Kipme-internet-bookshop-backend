package com.bookshop.core.exceptions.base;

public abstract class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

}
