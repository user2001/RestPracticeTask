package com.example.restpracticetask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String author;

    private BigDecimal price;

    @Version
    private Long version;

    public Book(String bookName, String author, BigDecimal price) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

}
