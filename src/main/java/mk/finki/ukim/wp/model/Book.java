package mk.finki.ukim.wp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Book {
    private String title;
    private String genre;
    private Double averageRating;
    private Long id;
    private Author author;

    private static long COUNTER = 1L;

    public Book(){}

    public Book(String title, String genre, Double averageRating, Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.id = COUNTER++;
        this.author = author;

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
