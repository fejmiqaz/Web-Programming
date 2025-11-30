package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findAll();
//    List<Book> searchBooks(String text, Double rating);
//    void save(String title, String genre,Double averageRating, Author author);
//    Optional<Book> findById(Long id);
//    void deleteBook(Long id);

    @Query("SELECT b from Book b WHERE LOWER(b.author.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Book> findAllByAuthor_Id(String name);

    @Query("SELECT b from Book b WHERE b.title LIKE %:text% AND b.averageRating >= :rating")
    List<Book> searchBooksByTitleAndAverageRating(String text, Double rating);
}
