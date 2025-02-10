package com.example.library_management.repositories;

import com.example.library_management.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByUserIdAndBookIdAndReturnDateIsNull(Long userId, Long bookId);
}
