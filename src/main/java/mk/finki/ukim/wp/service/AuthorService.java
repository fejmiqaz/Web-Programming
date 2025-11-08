package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> findAll();
    public Author findById(Long id);
}
