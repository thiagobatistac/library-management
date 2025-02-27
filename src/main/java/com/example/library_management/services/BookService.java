package com.example.library_management.services;

import com.example.library_management.dto.requests.BookRequest;
import com.example.library_management.dto.responses.BookResponse;
import com.example.library_management.entities.Book;
import com.example.library_management.exceptions.BookNotFoundException;
import com.example.library_management.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(BookRequest request) {
        Book newBook = new Book();
        newBook.setTitle(request.getTitle());
        newBook.setReleaseYear(request.getReleaseYear());
        newBook.setGenre(request.getGenre());
        newBook.setDescription(request.getDescription());

        Book savedBook = bookRepository.save(newBook);
        return convertToBookResponse(savedBook);
    }

    public BookResponse findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));
        return convertToBookResponse(book);
    }

    public List<BookResponse> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
    }

    public void deleteBookByTitle(String title) {
        Book bookToDeleteByTitle = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));
        bookRepository.delete(bookToDeleteByTitle);
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        bookRepository.deleteById(id);
    }

    private BookResponse convertToBookResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getReleaseYear(),
                book.getGenre(),
                book.getDescription()
        );
    }
}
