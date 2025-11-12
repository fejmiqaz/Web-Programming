package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    public List<Author> findAllAuthors();
    public Optional<Author> findById(Long id);
    public void deleteAuthor(Long id);
    void save(String name, String surname, String country, String biography);
}
