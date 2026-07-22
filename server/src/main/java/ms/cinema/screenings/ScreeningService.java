package ms.cinema.screenings;

import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.NotFoundException;
import ms.cinema.screenings.models.entities.Screening;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {
	
	private final ScreeningRepository repository;
	
	public List<Screening> getAll() {
		return repository.findAll();
	}
	
	public Screening get(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Screening's id should be present to fetch it");
		}
		
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a screening with id: %d", id)));
	}
	
	public Screening save(Screening screening) {
		if (screening == null) {
			throw new IllegalArgumentException("Object cannot be null to be saved");
		}
		if (screening.getId() != null) {
			throw new IllegalArgumentException("Screening's id should be null when saving new entity");
		}
		
		return repository.save(screening);
	}
	
	public Screening update(Screening screening) {
		if (screening == null) {
			throw new IllegalArgumentException("Object cannot be null to be updated");
		}
		if (screening.getId() == null) {
			throw new IllegalArgumentException("Screening's id should not be null to update the entity");
		}
		if (!repository.existsById(screening.getId())) {
			throw new IllegalArgumentException("There is no screening with given id to update");
		}
		
		return repository.save(screening);
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Screening's id should not be null to delete the entity");
		}
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("There is no screening with given id to delete");
		}
		
		repository.deleteById(id);
	}
}
