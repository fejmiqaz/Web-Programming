package mk.finki.ukim.wp.bootstrap;

import jakarta.annotation.PostConstruct;

import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.model.BookReservation;
import org.springframework.stereotype.Component;

import mk.finki.ukim.wp.model.Book;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init(){

        authors.add(new Author(1L, "George", "Orwell", "United Kingdom",
                "George Orwell was an English novelist, essayist, and critic, best known for his works '1984' and 'Animal Farm'."));

        authors.add(new Author(2L, "Jane", "Austen", "United Kingdom",
                "Jane Austen was an English novelist known for her insightful portrayals of society and class, especially in 'Pride and Prejudice'."));

        authors.add(new Author(3L, "Gabriel", "Garcia Marquez", "Colombia",
                "Gabriel García Márquez was a Colombian novelist and Nobel Prize winner, famous for pioneering magical realism in 'One Hundred Years of Solitude'."));


        books.add(new Book("Interstellar", "Sci-Fi", 10.0,authors.get(0)));
        books.add(new Book("Mad Max", "Action", 8.3, authors.get(1)));
        books.add(new Book("Top Gun", "Action", 9.9, authors.get(2)));
        books.add(new Book("Top Gun: Maverick", "Action", 10.0, authors.get(2)));
        books.add(new Book("The Godfather", "Mafia", 10.0, authors.get(1)));
        books.add(new Book("Blade Runner 2049", "Sci-Fi", 9.5,authors.get(0)));
        books.add(new Book("Blade Runner", "Action", 9.3, authors.get(0)));
        books.add(new Book("Mad Max: Furiosa", "Action", 10.0, authors.get(1)));
        books.add(new Book("The Godfather 2", "Mafia", 10.0, authors.get(2)));
    }
}
