package com.example.restpracticetask.services;

import com.example.restpracticetask.entity.Book;
import com.example.restpracticetask.exception.BookNotFoundException;
import com.example.restpracticetask.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    void init(){
        Book book1=new Book("Amadoka", "Andruchovych", BigDecimal.valueOf(350.0));
        Book book2=new Book("Harry Potter", "Rowling", BigDecimal.valueOf(240.0));
        Book book3=new Book("Tak ale", "Prohasko", BigDecimal.valueOf(280.0));
        bookRepository.saveAll(Arrays.asList(book2,book1,book3));
    }
    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        isFound(bookId);
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(Book book, Long bookId) {
        var temp=bookRepository.findById(bookId).orElse(null);
        temp.setBookName(book.getBookName());
        temp.setAuthor(book.getAuthor());
        temp.setPrice(book.getPrice());
        bookRepository.save(temp);
        return temp;
    }

    @Override
    public String deleteBook(Long bookId) {
        isFound(bookId);
        bookRepository.deleteById(bookId);
        return "Book with id: "+ bookId+ " was deleted";
    }

    public void isFound(Long bookId) {
        boolean isExist = bookRepository.existsById(bookId);
        if (!isExist) {
            throw new BookNotFoundException(
                    "Shop with id: " + bookId + " not found, try to put correct id");
        }
    }
}
