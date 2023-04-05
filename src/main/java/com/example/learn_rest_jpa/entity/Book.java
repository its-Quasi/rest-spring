package com.example.learn_rest_jpa.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String name;
    private Integer pages;
    private LocalDate releaseDate;
    private Boolean online;

    public Book(){}

    public Book(Long id, String author, String name, Integer pages, LocalDate releaseDate, Boolean online) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.pages = pages;
        this.releaseDate = releaseDate;
        this.online = online;
    }
    public Long getId(){
        return this.id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}

