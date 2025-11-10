package mk.finki.ukim.wp.web.controller;

import mk.finki.ukim.wp.model.Book;
import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.service.BookService;
import mk.finki.ukim.wp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String searchText,
                            @RequestParam(required = false) Double rating,
                            Model model) {
        if (searchText != null && rating != null) {
            model.addAttribute("books", bookService.searchBooks(searchText, rating));
        } else {
            model.addAttribute("books", bookService.listAll());
        }
        return "listBooks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "bookForm";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        Author author = authorService.findById(authorId).get();
        bookService.save(title, genre, averageRating, author);
        return "redirect:/books";
    }

    @GetMapping("/edit/{bookId}")
    public String showEditForm(@PathVariable Long bookId, Model model) {
        Book book = bookService.findById(bookId);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "bookForm";
    }

    @PostMapping("/edit/{bookId}")
    public String updateBook(@PathVariable Long bookId,
                             @RequestParam String title,
                             @RequestParam String genre,
                             @RequestParam Double averageRating,
                             @RequestParam Long authorId) {
        Author author = authorService.findById(authorId).get();
        bookService.save(title, genre, averageRating, author);
        return "redirect:/books";
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }
}
