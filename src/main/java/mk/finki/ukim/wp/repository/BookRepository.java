package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    void save(String title, String genre,Double averageRating, Author author);
    Book findById(Long id);
    void deleteBook(Long id);
}
