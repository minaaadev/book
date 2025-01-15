package com.example.books;

import com.example.books.bookpkg.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping
	public List<Book> hello() {
		return List.of(
				new Book(
						1L,
						"Pachinko",
						"Min Jin Lee",
						12000
				)
			);
		}
	}