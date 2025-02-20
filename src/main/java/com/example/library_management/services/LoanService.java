package com.example.library_management.services;

import com.example.library_management.dto.responses.LoanResponse;
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

    public LoanResponse borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null);

        Loan savedLoan = loanRepository.save(loan);
        return convertToLoanResponse(savedLoan);
    }

    public LoanResponse returnBook(Long userId, Long bookId) {
        Loan loan = loanRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        Loan updatedLoan = loanRepository.save(loan);
        return convertToLoanResponse(updatedLoan);
    }

    private LoanResponse convertToLoanResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getUser().getName(),
                loan.getUser().getEmail(),
                loan.getBook().getTitle(),
                loan.getBook().getReleaseYear()
        );
    }
}

