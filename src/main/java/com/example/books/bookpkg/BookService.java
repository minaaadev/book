package com.example.books.bookpkg;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    //책 목록 반환
    public List<Book> getBooks() {return bookRepository.findAll();}

    public void addNewBook(Book book) {
        Optional<Book> bookOptional=bookRepository
                .findBookByPrice(book.getPrice());
        if (bookOptional.isPresent()){
            throw new IllegalStateException("가격을 입력하세요.");
        }
        bookRepository.save(book);
    }
    public void deleteBook(Long bookId){
        boolean exists = bookRepository.existsById(bookId);
        if(!exists) {
            throw new IllegalStateException(
                    "id가 " + bookId + " 인 책이 존재하지 않습니다.");
        }
        bookRepository.deleteById(bookId);
        }

        @Transactional
        public void updateBook(Long bookId,
                               String title,
                               String author) {
        Book book = bookRepository.findById(bookId).orElseThrow(()
                ->new IllegalStateException("id가 " + bookId +"인 책이 존재하지 않습니다."));

    if (title!=null && title.length()>0 && !Objects.equals(book.getTitle(),title)){
        book.setTitle(title);
    }
    if(author!=null&& author.length() > 0 && !Objects.equals(book.getAuthor(), author)){
        Optional<Book> bookOptional=bookRepository.findBookByAuthor(author);
        if(bookOptional.isPresent()){
            throw new IllegalStateException("author taken");
        }
        book.setAuthor(author);
    }
}

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
