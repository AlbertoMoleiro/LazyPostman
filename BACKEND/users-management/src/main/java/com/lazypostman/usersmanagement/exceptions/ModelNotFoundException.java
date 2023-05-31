package com.lazypostman.usersmanagement.exceptions;

public class ModelNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ModelNotFoundException(String message) {
        super(message);
    }
}
