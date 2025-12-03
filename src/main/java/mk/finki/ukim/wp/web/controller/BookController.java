package mk.finki.ukim.wp.web.controller;

import mk.finki.ukim.wp.model.Book;
import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.service.BookService;
import mk.finki.ukim.wp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path={"/books", "/"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) Long authorId,
                            @RequestParam(required = false) Double findByRating,
                            @RequestParam(required = false) Double searchRating,
                            @RequestParam(required = false) String searchText,
                            Model model) {
        List<Book> books;
        if(searchText != null && searchRating != null){
            books = bookService.searchBooksByTitleAndRating(searchText, searchRating); // fixed
        }else if (findByRating != null) {
           books = bookService.searchBooksByRating(findByRating); // fixed
        }else if( authorId != null){
            books = bookService.listBooksByAuthorId(authorId); // fixed
        }else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.findAll());

        model.addAttribute("selectedAuthorId", authorId); // added this line

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
        Author author = authorService.findById(authorId);
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
        Author author = authorService.findById(authorId);
        bookService.updateBook(bookId, title, genre, averageRating, author);
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }
}
