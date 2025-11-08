package mk.finki.ukim.wp.repository.impl;

import mk.finki.ukim.wp.bootstrap.DataHolder;
import mk.finki.ukim.wp.model.BookReservation;
import mk.finki.ukim.wp.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {
    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
