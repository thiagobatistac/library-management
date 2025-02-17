package com.example.library_management.services;

import com.example.library_management.entities.Book;
import com.example.library_management.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(String title, String description, String genre, Integer releaseYear) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setDescription(description);
        newBook.setGenre(genre);
        newBook.setReleaseYear(releaseYear);

        return bookRepository.save(newBook);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book not found with title: " + title));
    }

public Book deleteBook(String title){
        Book bookToDelete = bookRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(bookToDelete);
        return bookToDelete;
}

public List<Book> findAllBooks(){
        return bookRepository.findAll();
}

}
