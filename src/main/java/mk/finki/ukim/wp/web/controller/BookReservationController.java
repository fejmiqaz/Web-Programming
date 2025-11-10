package mk.finki.ukim.wp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.model.Book;
import mk.finki.ukim.wp.model.BookReservation;
import mk.finki.ukim.wp.service.AuthorService;
import mk.finki.ukim.wp.service.BookReservationService;
import mk.finki.ukim.wp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books/reservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;
    private final BookService bookService;

    public BookReservationController(BookReservationService bookReservationService, BookService bookService) {
        this.bookReservationService = bookReservationService;
        this.bookService = bookService;
    }

    @PostMapping
    public String reservation(@RequestParam String readerName, @RequestParam Long bookId, @RequestParam String readerAddress, HttpServletRequest request , @RequestParam Integer numCopies, Model model){

        Book selectedBook = bookService.findById(bookId);

        bookReservationService.placeReservation(selectedBook.getTitle(), readerName, readerAddress, numCopies);

        model.addAttribute("readerName", readerName);
        model.addAttribute("readerAddress", readerAddress);
        model.addAttribute("numCopies", numCopies);
        model.addAttribute("bookTitle", selectedBook.getTitle());
        model.addAttribute("ipAddress", request.getRemoteAddr());

        System.out.println("Book reservation POST received!");

        return "reservationConfirmation";
    }
}
