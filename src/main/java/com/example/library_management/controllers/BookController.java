package com.example.library_management.controllers;

import com.example.library_management.entities.Book;
import com.example.library_management.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.createBook(book.getTitle(), book.getDescription(), book.getGenre(), book.getReleaseYear());
        return ResponseEntity.ok(newBook);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Book> findBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findBookByTitle(title));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteBook(@PathVariable String title){
        bookService.deleteBook(title);
        return ResponseEntity.noContent().build();
    }
}
