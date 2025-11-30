package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
//    BookReservation save(BookReservation reservation);
}
