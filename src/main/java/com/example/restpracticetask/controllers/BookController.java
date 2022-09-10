package com.example.restpracticetask.controllers;

import com.example.restpracticetask.entity.Book;
import com.example.restpracticetask.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        var list = bookService.getBooks();
        String version = String.valueOf(list.hashCode());
        return ResponseEntity.ok()
                .eTag(version)
                .body(list);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        String version = Long.toString(book.getVersion());

        return ResponseEntity.ok()
                .eTag(version)
                .body(book);

    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return "Book with id: " + bookId + " was deleted";
    }

    @PutMapping(value = "/{bookId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Book updateBook(@RequestBody Book book, @PathVariable Long bookId) {
        return bookService.updateBook(book, bookId);
    }

}
