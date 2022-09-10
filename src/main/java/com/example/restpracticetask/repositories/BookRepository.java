package com.example.restpracticetask.repositories;

import com.example.restpracticetask.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book,Long> {
}
