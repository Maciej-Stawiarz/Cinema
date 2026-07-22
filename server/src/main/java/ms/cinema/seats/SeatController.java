package ms.cinema.seats;

import lombok.RequiredArgsConstructor;
import ms.cinema.seats.models.entities.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController implements SeatAPI {
	
	private final SeatService service;
	
	@Override
	public ResponseEntity<List<Seat>> getAll() {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Seat> get(Long id) {
		return new ResponseEntity<>(
				service.get(id),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Seat> save(Seat seat) {
		return new ResponseEntity<>(
				service.save(seat),
				HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<Seat> update(Seat seat) {
		return new ResponseEntity<>(
				service.update(seat),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> delete(Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
