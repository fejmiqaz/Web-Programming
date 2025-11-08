package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Author;

import java.util.List;

public interface AuthorRepository {
    public List<Author> findAll();
    public Author findById(Long id);
}
