package com.example.term3.exception;

public class InvalidSockException extends RuntimeException {
    public InvalidSockException(String massage) {
        super(massage);
    }
}
