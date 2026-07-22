package ms.cinema.rooms;

import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.NotFoundException;
import ms.cinema.rooms.models.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
	
	private final RoomRepository repository;
	
	public List<Room> getAll() {
		return repository.findAll();
	}
	
	public Room get(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Room's id should be present to fetch it");
		}
		
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a seat with id: %d", id)));
	}
	
	public Room save(Room room) {
		if (room == null) {
			throw new IllegalArgumentException("Object cannot be null to be saved");
		}
		if (room.getId() != null) {
			throw new IllegalArgumentException("Room's id should be null when saving new entity");
		}
		
		return repository.save(room);
	}
	
	public Room update(Room room) {
		if (room == null) {
			throw new IllegalArgumentException("Object cannot be null to be updated");
		}
		if (room.getId() == null) {
			throw new IllegalArgumentException("Room's id should not be null to update the entity");
		}
		if (!repository.existsById(room.getId())) {
			throw new IllegalArgumentException("There is no room with given id to update");
		}
		
		return repository.save(room);
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Room's id should not be null to delete the entity");
		}
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("There is no room with given id to delete");
		}
		
		repository.deleteById(id);
	}
}