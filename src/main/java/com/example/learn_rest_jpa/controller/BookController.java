package com.example.learn_rest_jpa.controller;
import com.example.learn_rest_jpa.entity.Book;
import com.example.learn_rest_jpa.repository.BookRepository;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Book> get(@PathVariable Long id){
        Optional<Book> bookOptional = repository.findById(id);
        if(bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/books/")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PostMapping("/api/books/{id}")
    public void remove(@PathVariable Long id){
        repository.deleteById(id);
    }
}
