package com.example.books.bookpkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 모든 책 정보 반환 (책 목록 페이지)
    @GetMapping
    public String getBooksPage(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "book";  // "book.html" 템플릿 반환
    }

    // 책 추가페이지 이동
    @GetMapping("/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    // 새 책 정보 저장
    @PostMapping("/new")
    public String saveNewBook(@ModelAttribute("book") Book book) {
        bookService.addNewBook(book);
        return "redirect:/books";
    }

    // 책 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit";  // "edit.html" 템플릿 반환
    }

    // 책 정보 수정
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        Book existingBook = bookService.getBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());

        bookService.updateBook(existingBook);
        return "redirect:/books";  // 수정 후 책 목록 페이지로 리다이렉트
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return "삭제 완료";
    }
}
