package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
//    BookReservation save(BookReservation reservation);
}
