package com.example.books.bookpkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 책 목록 반환
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // 책 추가
    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    // 책 삭제
    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("id가 " + bookId + "인 책이 존재하지 않습니다.");
        }
        bookRepository.deleteById(bookId);
    }

    // 책 정보 수정
    @Transactional
    public void updateBook(Book book) {
        bookRepository.save(book);  // 책 정보 저장
    }

    // id로 책 조회
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("id가 " + id + "인 책이 존재하지 않습니다.")
        );
    }
}
