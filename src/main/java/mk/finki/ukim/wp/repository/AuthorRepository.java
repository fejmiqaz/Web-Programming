package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    public List<Author> findAllAuthors();
//    public Optional<Author> findById(Long id);
//    public void deleteAuthor(Long id);
//    void save(String name, String surname, String country, String biography);
}
