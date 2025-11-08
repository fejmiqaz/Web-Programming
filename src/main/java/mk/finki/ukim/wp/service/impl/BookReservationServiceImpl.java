package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.BookReservation;
import mk.finki.ukim.wp.repository.BookReservationRepository;
import mk.finki.ukim.wp.service.BookReservationService;
import org.springframework.stereotype.Service;


@Service
public class BookReservationServiceImpl implements BookReservationService {
    BookReservationRepository bookReservation;

    public BookReservationServiceImpl(BookReservationRepository bookReservation) {
        this.bookReservation = bookReservation;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        if(bookTitle == null || bookTitle.isEmpty()
        || readerName == null || readerName.isEmpty()
        || readerAddress == null || readerAddress.isEmpty()){
            throw new IllegalArgumentException();
        }
        return bookReservation.save(new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies));
    }
}
