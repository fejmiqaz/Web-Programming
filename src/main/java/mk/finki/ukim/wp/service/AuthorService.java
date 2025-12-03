package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    void deleteAuthor(Long id);
    void save(String name, String surname, String country, String bio);
    void updateAuthor(Long id, String name, String surname, String country, String bio);
}
