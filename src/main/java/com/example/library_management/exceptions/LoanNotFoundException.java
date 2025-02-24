package com.example.library_management.exceptions;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(String message){
        super(message);
    }
}
