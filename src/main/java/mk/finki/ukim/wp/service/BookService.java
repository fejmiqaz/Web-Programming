package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();

    List<Book> searchBooksByRating(Double rating);

    Book save(String title, String genre, Double averageRating, Author author);
    void updateBook(Long id, String title, String genre, Double averageRating, Author author);
    Book findById(Long id);
    void deleteBook(Long id);
    List<Book> listBooksByAuthorId(Long name);
}
