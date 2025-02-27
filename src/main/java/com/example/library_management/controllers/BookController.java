package com.example.library_management.controllers;

import com.example.library_management.dto.requests.BookRequest;
import com.example.library_management.dto.responses.BookResponse;
import com.example.library_management.entities.Book;
import com.example.library_management.exceptions.BookNotFoundException;
import com.example.library_management.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

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
    public ResponseEntity<String> deleteBookByTitle(@PathVariable String title) {
        bookService.deleteBookByTitle(title);
        return ResponseEntity.ok("Book deleted successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted successfully.");
    }
}




