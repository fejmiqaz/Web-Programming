package mk.finki.ukim.wp.web.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import mk.finki.ukim.wp.service.AuthorService;
import mk.finki.ukim.wp.service.BookReservationService;
import mk.finki.ukim.wp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("error", error);
        }
        model.addAttribute("books", bookService.listAll());

        return "listBooks";
    }

    @PostMapping("/books")
    public String filter(@RequestParam String bookName, @RequestParam Double bookRating, Model model){
        model.addAttribute("books", bookService.searchBooks(bookName, bookRating));
        return "listBooks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books/add")
    public String saveBook(@RequestParam String title, @RequestParam String genre, @RequestParam Double averageRating, @RequestParam Long authorId){
        Author author = authorService.findById(authorId);
        bookService.save(new Book(title, genre, averageRating, author));
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{bookId}")
    public String showEditForm(@PathVariable Long bookId, Model model){
        Book book = bookService.findById(bookId);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, @RequestParam String title, @RequestParam String genre, @RequestParam Double averageRating, @RequestParam Long authorId){
        Book bookToEdit = bookService.findById(bookId);
        bookToEdit.setTitle(title);
        bookToEdit.setGenre(genre);
        bookToEdit.setAverageRating(averageRating);
        bookToEdit.setAuthor(authorService.findById(authorId));

        bookService.save(bookToEdit);
        return "redirect:/books";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        Book bookToDelete = bookService.findById(id);
        if(bookToDelete != null){
            bookService.deleteBook(bookToDelete);
        }
        return "redirect:/books";
    }

}
