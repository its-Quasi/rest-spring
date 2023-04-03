package com.example.learn_rest_jpa.controller;
import com.example.learn_rest_jpa.entity.Book;
import com.example.learn_rest_jpa.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository){
        this.repository = repository;
    }

    @GetMapping("/api/books")
    public List<Book> getAll(){
        return repository.findAll();
    }

    //Get a book by ID
    @GetMapping("/api/books/{id}")
    public Book get(@PathVariable Long id){
        Optional<Book> bookOptional = repository.findById(id);
        return bookOptional.orElse(null);
    }

    @PostMapping("/api/books/")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

}
