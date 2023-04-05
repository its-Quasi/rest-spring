package com.example.learn_rest_jpa.controller;
import com.example.learn_rest_jpa.entity.Book;
import com.example.learn_rest_jpa.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    /**Operaciones CRUD basicas sobre una entidad sencilla*/

    /**
     * Obtener todos los libros existentes en base de datos (datos volatiles)
     * @return
     */
    @GetMapping("/api/books")
    public List<Book> getAll() {
        return repository.findAll();
    }

    /**
     * Buscar un libro por su id
     * @param id
     * @return
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        }
        return ResponseEntity.notFound().build(); // libro no encontrado
    }

    /**
     * Crear un nuevo libro
     * @param book
     * @return
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if (book.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Book savedBook = repository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * Actualizar un libro ya existente
     * @param book
     * @return
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        Long id = book.getId();
        if (id == null) return ResponseEntity.badRequest().build();
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        Book updatedBook = repository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Eliminar un libro mediante su id
     * @param id
     */
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> remove(@PathVariable Long id){
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
