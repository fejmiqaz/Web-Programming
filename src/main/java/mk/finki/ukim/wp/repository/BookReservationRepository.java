package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.BookReservation;

public interface BookReservationRepository {
    BookReservation save(BookReservation reservation);
}
