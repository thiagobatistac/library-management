package com.example.library_management.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String message){
        super(message);
    }
}
