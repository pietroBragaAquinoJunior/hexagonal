package com.pietro.hexagonal.adapters.exceptions;

public class NotFoundCustomException extends RuntimeException {
    public NotFoundCustomException(String message) {
        super(message);
    }
}