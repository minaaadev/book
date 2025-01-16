package com.example.books.bookpkg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Book pachinko = new Book(
                    "Pachinko",
                    "Min Jin Lee",
                    14000
            );
            Book nightCircus = new Book(
                    "The Night Circus",
                    "Erin Morgenstern",
                    12000
            );
            repository.saveAll(
                    List.of(pachinko,nightCircus)
            );
        };
    }
}
