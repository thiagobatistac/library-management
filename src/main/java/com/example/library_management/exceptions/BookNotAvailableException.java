package com.example.library_management.exceptions;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String message){
        super(message);
    }
}
