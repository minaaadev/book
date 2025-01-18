package com.example.books.bookpkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/b1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 모든 책 정보 반환
    @GetMapping
    public String getBooks(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "book_list";  // "book_list" 템플릿을 반환
    }

    // 새 책 정보 등록
    @PostMapping
    public String registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
        return "redirect:/books";  // 책 목록 페이지로 리다이렉트
    }

    // 책 수정 폼 페이지로 이동 (GET)
    @GetMapping("books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        //Book book = bookService.getBookById(id);
        model.addAttribute("book", bookService.getBookById(id));
        return "edit";  // "edit" 템플릿을 반환
    }

    // 책 수정 처리 (POST)
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        Book existingBook = bookService.getBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());

        bookService.updateBook(existingBook);
        return "redirect:/books";  // 수정 후 책 목록 페이지로 리다이렉트
    }
}
