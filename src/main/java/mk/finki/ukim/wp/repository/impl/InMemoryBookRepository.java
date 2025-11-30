//package mk.finki.ukim.wp.repository.impl;
//
//import mk.finki.ukim.wp.bootstrap.DataHolder;
//import mk.finki.ukim.wp.model.Author;
//import mk.finki.ukim.wp.model.Book;
//import mk.finki.ukim.wp.repository.BookRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryBookRepository implements BookRepository {
//    @Override
//    public List<Book> findAll() {
//        return DataHolder.books;
//    }
//
//    @Override
//    public List<Book> searchBooks(String text, Double rating) {
//        return DataHolder.books.stream().filter(b -> b.getTitle().toLowerCase().contains(text.toLowerCase()) && b.getAverageRating() >= rating).toList();
//    }
//
//    @Override
//    public void save(String title, String genre, Double averageRating, Author author) {
//        Book book = new Book(title, genre, averageRating, author);
//        DataHolder.books.add(book);
//    }
//
//    @Override
//    public Optional<Book> findById(Long id) {
//        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
//    }
//
//    @Override
//    public void deleteBook(Long id) {
//        DataHolder.books.removeIf(b->b.getId().equals(id));
//    }
//}
