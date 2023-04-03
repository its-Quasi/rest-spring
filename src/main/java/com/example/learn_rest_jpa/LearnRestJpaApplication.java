package com.example.learn_rest_jpa;

import com.example.learn_rest_jpa.entity.Book;
import com.example.learn_rest_jpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class LearnRestJpaApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LearnRestJpaApplication.class, args);
		BookRepository repo = context.getBean(BookRepository.class);
		Book b1 = new Book(null, "Gabriel Garcia Marquez", "Cien anios de soledad", 200, LocalDate.of(2023, 10, 4), true);
		Book b2 = new Book(null, "Gabriel Garcia Marquez", "Coronel no tiene", 200, LocalDate.of(2023, 10, 4), true);

		repo.save(b1);
		repo.save(b2);



	}
}
