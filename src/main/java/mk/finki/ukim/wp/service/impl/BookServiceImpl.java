package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Author;
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
    public Book save(String title, String genre, Double averageRating, Author author) {
        Book book;

        book = new Book(title,genre,averageRating,author);

        return bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, String title, String genre, Double averageRating, Author author) {
        if(title.isEmpty() || genre.isEmpty() || averageRating.isNaN() || author == null){
            throw new IllegalArgumentException();
        }

        Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        bookRepository.save(book);

    }


    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).isPresent() ? bookRepository.findById(id).get() : null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> listBooksByAuthorId(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public List<Book> searchBooksByRating(Double rating) {
        return bookRepository.findBookByAverageRatingGreaterThanEqual(rating);
    }

    @Override
    public List<Book> searchBooksByTitleAndRating(String searchText, Double searchRating) {
        return bookRepository.findBookByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(searchText,searchRating);
    }
}
