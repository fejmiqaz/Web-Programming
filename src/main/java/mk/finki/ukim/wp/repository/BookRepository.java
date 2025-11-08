package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    void save(Book book);
    Book findById(Long id);
    void deleteBook(Book book);
}
