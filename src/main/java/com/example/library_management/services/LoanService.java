package com.example.library_management.services;

import com.example.library_management.entities.Book;
import com.example.library_management.entities.Loan;
import com.example.library_management.entities.User;
import com.example.library_management.repositories.BookRepository;
import com.example.library_management.repositories.LoanRepository;
import com.example.library_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Loan borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        Loan loan = new Loan();
       loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long userId, Long bookId) {
        Loan loan = loanRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        return loanRepository.save(loan);
    }
}

