package ms.cinema.rooms;

import ms.cinema.rooms.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	Optional<Room> findByName(String name);
	boolean existsByName(String name);
}
