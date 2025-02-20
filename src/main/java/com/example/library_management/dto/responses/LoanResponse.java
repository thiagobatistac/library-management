package com.example.library_management.dto.responses;


public class LoanResponse {

    private Long id;
    private String userName;
    private String userEmail;
    private String bookTitle;
    private Integer bookReleaseYear;

    public LoanResponse(Long id, String userName, String userEmail, String bookTitle, Integer bookReleaseYear) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.bookTitle = bookTitle;
        this.bookReleaseYear = bookReleaseYear;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Integer getBookReleaseYear() {
        return bookReleaseYear;
    }

}
