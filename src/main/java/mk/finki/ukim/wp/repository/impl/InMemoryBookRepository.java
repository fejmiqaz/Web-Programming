package mk.finki.ukim.wp.repository.impl;

import mk.finki.ukim.wp.bootstrap.DataHolder;
import mk.finki.ukim.wp.model.Book;
import mk.finki.ukim.wp.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(b -> b.getTitle().toLowerCase().contains(text.toLowerCase()) && b.getAverageRating() >= rating).toList();
    }

    @Override
    public void save(Book book) {
        DataHolder.books.add(book);
    }

    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteBook(Book book) {
        DataHolder.books.remove(book);
    }
}
