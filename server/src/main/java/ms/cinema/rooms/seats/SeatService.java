package ms.cinema.rooms.seats;

import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.NotFoundException;
import ms.cinema.rooms.seats.models.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
	
	private final SeatRepository repository;
	
	public List<Seat> getAll() {
		return repository.findAll();
	}
	
	public Seat get(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Seat's id should be present to fetch it");
		}
		
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a seat with id: %d", id)));
	}
	
	public Seat save(Seat seat) {
		if (seat == null) {
			throw new IllegalArgumentException("Object cannot be null to be saved");
		}
		if (seat.getId() != null) {
			throw new IllegalArgumentException("Seat's id should be null when saving new entity");
		}
		
		return repository.save(seat);
	}
	
	public Seat update(Seat seat) {
		if (seat == null) {
			throw new IllegalArgumentException("Object cannot be null to be updated");
		}
		if (seat.getId() == null) {
			throw new IllegalArgumentException("Seat's id should not be null to update the entity");
		}
		if (!repository.existsById(seat.getId())) {
			throw new IllegalArgumentException("There is no seat with given id to update");
		}
		
		return repository.save(seat);
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Seat's id should not be null to delete the entity");
		}
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("There is no seat with given id to delete");
		}
		
		repository.deleteById(id);
	}
}
