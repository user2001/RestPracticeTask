package com.example.restpracticetask.services;

import com.example.restpracticetask.entity.Book;

import java.util.List;

public interface BookServiceInt {
    public List<Book> getBooks();
    public Book getBookById(Long bookId);
    public Book addBook(Book book);
    public Book updateBook(Book book, Long bookId);
    public String deleteBook(Long bookId);

}
