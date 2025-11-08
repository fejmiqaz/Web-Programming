package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Book;
import mk.finki.ukim.wp.repository.BookRepository;
import mk.finki.ukim.wp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if(text == null || text.isEmpty() || rating == null || rating.isNaN()){
            throw new IllegalArgumentException();
        }
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.deleteBook(book);
    }
}
