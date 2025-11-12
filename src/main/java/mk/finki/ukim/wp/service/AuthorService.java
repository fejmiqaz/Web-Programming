package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> findAll();
    public Author findById(Long id);
    public void deleteAuthor(Long id);
    public void save(String name, String surname, String country, String bio);
}
