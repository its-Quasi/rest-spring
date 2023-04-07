package com.example.learn_rest_jpa.controller;

import com.example.learn_rest_jpa.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port); // creado por spring
        testRestTemplate = new TestRestTemplate(restTemplateBuilder); // Creado por dev
    }

    @Test
    void getAll() {
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books" , Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //Crear el libro que se debe enviar mediante la peticion de prueba

        String testBook = new String("""
            {
                "author": "Proof Author",
                "name": "Proof Book",
                "pages": 100,
                "releaseDate": "2010-10-04",
                "online": false
            }
            """
        );

        HttpEntity<String> request = new HttpEntity<>(testBook, headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
        Book result = response.getBody();
        assertEquals(1l, result.getId());
        assertEquals("Proof Book", result.getName());
    }
}