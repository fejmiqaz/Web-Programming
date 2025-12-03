package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findAll();
//    List<Book> searchBooks(String text, Double rating);
//    void save(String title, String genre,Double averageRating, Author author);
//    Optional<Book> findById(Long id);
//    void deleteBook(Long id);

    List<Book> findAllByAuthor_Id(Long authorId);

    List<Book> findBookByAverageRatingGreaterThanEqual(Double rating); // added this

    List<Book> findBookByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(String searchText, Double searchRating); // fixed this too
}
