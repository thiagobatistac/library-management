package com.example.library_management.services;

import com.example.library_management.dto.responses.LoanResponse;
import com.example.library_management.entities.Book;
import com.example.library_management.entities.Loan;
import com.example.library_management.entities.User;
import com.example.library_management.exceptions.BookNotAvailableException;
import com.example.library_management.exceptions.BookNotFoundException;
import com.example.library_management.exceptions.LoanNotFoundException;
import com.example.library_management.exceptions.UserNotFoundException;
import com.example.library_management.repositories.BookRepository;
import com.example.library_management.repositories.LoanRepository;
import com.example.library_management.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository){
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public LoanResponse borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        if (loanRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId).isPresent()) {
            throw new BookNotAvailableException("This book is already loaned.");
        }

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
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

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

