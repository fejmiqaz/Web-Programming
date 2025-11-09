package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    void save(String title, String genre, Double averageRating, Author author);
    Book findById(Long id);
    void deleteBook(Long id);
}
