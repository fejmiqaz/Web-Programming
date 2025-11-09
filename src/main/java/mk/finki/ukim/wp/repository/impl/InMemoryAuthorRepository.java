package mk.finki.ukim.wp.repository.impl;

import mk.finki.ukim.wp.bootstrap.DataHolder;
import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {
    @Override
    public List<Author> findAllAuthors() {
        return DataHolder.authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream().filter(a -> a.getId().equals(id)).findFirst();
    }
}
