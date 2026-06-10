package ms.cinema.rooms.seats;

import ms.cinema.rooms.seats.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
