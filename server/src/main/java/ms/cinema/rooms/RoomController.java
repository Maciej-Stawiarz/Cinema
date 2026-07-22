package ms.cinema.rooms;

import lombok.RequiredArgsConstructor;
import ms.cinema.rooms.models.entities.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController implements RoomAPI {
	
	private final RoomService service;
	
	@Override
	public ResponseEntity<List<Room>> getAll() {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Room> get(Long id) {
		return new ResponseEntity<>(
				service.get(id),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Room> save(Room room) {
		return new ResponseEntity<>(
				service.save(room),
				HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<Room> update(Room room) {
		return new ResponseEntity<>(
				service.update(room),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Room> delete(Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}