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
@RequestMapping("/books")
public class BookReservationController {
    private final BookReservationService bookReservationService;
    private final BookService bookService;

    public BookReservationController(BookReservationService bookReservationService, BookService bookService) {
        this.bookReservationService = bookReservationService;
        this.bookService = bookService;
    }

    @PostMapping("/reservation")
    public String reservation(@RequestParam String readerName, @RequestParam Long selectedBookId, @RequestParam String readerAddress, HttpServletRequest request , @RequestParam Integer numCopies, Model model){

        Book selectedBook = bookService.findById(selectedBookId);

        bookReservationService.placeReservation(selectedBook.getTitle(), readerName, readerAddress, numCopies);

        model.addAttribute("readerName", readerName);
        model.addAttribute("readerAddress", readerAddress);
        model.addAttribute("numCopies", numCopies);
        model.addAttribute("bookTitle", selectedBook.getTitle());
        model.addAttribute("ipAddress", request.getRemoteAddr());

        return "reservationConfirmation";
    }
}
