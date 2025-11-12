package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.repository.AuthorRepository;
import mk.finki.ukim.wp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAllAuthors();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteAuthor(id);
    }

    @Override
    public void save(String name, String surname, String country, String bio) {
        authorRepository.save(name, surname, country, bio);
    }
}
