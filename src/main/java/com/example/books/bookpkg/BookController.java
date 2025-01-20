package com.example.books.bookpkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // 책 목록
    @GetMapping
    public String getBooksPage(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "book";
    }

    // 책 추가 페이지 이동
    @GetMapping("/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    // 새로운 책 정보 저장
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
        return "edit";
    }

    // 책 정보 수정
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        Book existingBook = bookService.getBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());

        bookService.updateBook(existingBook);
        return "redirect:/books";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
            return ResponseEntity.ok("삭제되었습니다.");
       }


       //도서 검색 페이지 이동
    @GetMapping("/search")
    public String searchBooksPage() {
        return "search";
    }

    //id 조회로 책 정보 검색
    @PostMapping("/search")
    public String searchBookById(@RequestParam Long id, Model model) {
        try{
            Book book = bookService.getBookById(id);
            model.addAttribute("book", book);
            return "search";
        }
        catch (IllegalStateException e) {
            model.addAttribute("error", "책을 찾을 수 없습니다.");
            return "search";
        }

    }
}

