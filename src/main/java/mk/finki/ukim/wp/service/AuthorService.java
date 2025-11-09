package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();
    public Optional<Author> findById(Long id);
}
