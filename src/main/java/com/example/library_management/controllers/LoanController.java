package com.example.library_management.controllers;

import com.example.library_management.dto.requests.LoanRequest;
import com.example.library_management.entities.Loan;
import com.example.library_management.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrowBook(@RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.borrowBook(loanRequest.getUserId(), loanRequest.getBookId());
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/return")
    public ResponseEntity<Loan> returnBook(@RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.returnBook(loanRequest.getUserId(), loanRequest.getBookId());
        return ResponseEntity.ok(loan);
    }
}
