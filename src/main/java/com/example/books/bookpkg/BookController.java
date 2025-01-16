package com.example.books.bookpkg;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping(path="api/b1/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //모든 책 정보 반환
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    //새 책 정보 등록
    @PostMapping
    public void registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    //책 정보 삭제
    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("studentId") Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        System.out.println("Received PUT request: bookId=" + bookId + ", title=" + title + ", author=" + author);
        bookService.updateBook(bookId, title, author);
    }


}
