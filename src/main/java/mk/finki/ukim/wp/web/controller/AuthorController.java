package mk.finki.ukim.wp.web.controller;

import mk.finki.ukim.wp.model.Book;
import mk.finki.ukim.wp.model.Author;
import mk.finki.ukim.wp.service.BookService;
import mk.finki.ukim.wp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path={"/authors"})
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(BookService bookService, AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String searchText,
                            @RequestParam(required = false) Double rating,
                            Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "listAuthors";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "authorForm";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country,
                           @RequestParam String biography) {
        authorService.save(name, surname, country, biography);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "authorForm";
    }

    @PostMapping("/edit-form/{id}")
    public String updateBook(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        authorService.save(name,surname,country,biography);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
