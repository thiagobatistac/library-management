package com.example.library_management.dto.requests;

import jakarta.validation.constraints.NotNull;

public class LoanRequest {

    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Book ID is required")
    private Long bookId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
