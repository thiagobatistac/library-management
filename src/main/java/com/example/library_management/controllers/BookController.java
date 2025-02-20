package com.example.library_management.controllers;

import com.example.library_management.dto.requests.BookRequest;
import com.example.library_management.dto.responses.BookResponse;
import com.example.library_management.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse newBook = bookService.createBook(request);
        return ResponseEntity.status(201).body(newBook);
    }

    @GetMapping("/{title}")
    public ResponseEntity<BookResponse> findBookByTitle(@PathVariable String title) {
        BookResponse book = bookService.findBookByTitle(title);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteBook(@PathVariable String title) {
        bookService.deleteBook(title);
        return ResponseEntity.noContent().build();
    }
}

