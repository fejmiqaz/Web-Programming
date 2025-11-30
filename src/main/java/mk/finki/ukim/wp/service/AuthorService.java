package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    void deleteAuthor(Long id);
    void save(String name, String surname, String country, String bio);
    Author updateAuthor(Long id, String name, String surname, String country, String bio);
}
