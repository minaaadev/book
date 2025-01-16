package com.example.books.bookpkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/books")
public class BookUiController {

    private final BookService bookService;

    @Autowired
    public BookUiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooksPage(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "book"; // book.html 템플릿 파일을 렌더링
    }

    @GetMapping("/new")
    public String createBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book",new Book());
        return "add_book";
    }
    @PostMapping//("/books")
    public String saveBook(@ModelAttribute("book") Book book){
        bookService.addNewBook(book);
        return "redirect:/books";
    }
}
