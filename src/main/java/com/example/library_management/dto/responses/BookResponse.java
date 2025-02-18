package com.example.library_management.dto.responses;

public class BookResponse {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String genre;
    private String description;

    public BookResponse(Long id, String title, Integer releaseYear, String genre, String description) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }
}

