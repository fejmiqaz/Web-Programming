package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.BookReservation;
public interface BookReservationService {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);
}
