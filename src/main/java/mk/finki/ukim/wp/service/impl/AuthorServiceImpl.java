package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.repository.AuthorRepository;
import mk.finki.ukim.wp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void save(String name, String surname, String country, String bio) {

        if(name == null || name.isEmpty() || surname == null || surname.isEmpty() ||
            country == null || country.isEmpty() || bio == null || bio.isEmpty()){
            throw new IllegalArgumentException();
        }

        authorRepository.save(new Author(name,surname,country,bio));
    }

    @Override
    public void updateAuthor(Long id, String name, String surname, String country, String bio) {

        if(name == null || name.isEmpty() || surname == null || surname.isEmpty() ||
            country == null || country.isEmpty() || bio == null || bio.isEmpty()){
            throw new IllegalArgumentException();
        }

        Author author = authorRepository.findById(id).orElseThrow();

        author.setName(name);
        author.setSurname(surname);
        author.setBiography(bio);
        author.setCountry(country);

        this.authorRepository.save(author);
    }
}
