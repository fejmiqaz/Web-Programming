package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.repository.AuthorRepository;
import mk.finki.ukim.wp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }
}
