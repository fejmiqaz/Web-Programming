package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    public List<Author> findAllAuthors();
    public Optional<Author> findById(Long id);
}
