package ms.cinema.movies;

import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	private final MovieRepository repository;
	
	public List<Movie> getAll() {
		return repository.findAll();
	}
	
	public Movie get(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Movie's id should be present to fetch it");
		}
		
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a movie with id: %d", id)));
	}
	
	public Movie save(Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("Object cannot be null to be saved");
		}
		if (movie.getId() != null) {
			throw new IllegalArgumentException("Movie's id should be null when saving new entity");
		}
		
		return repository.save(movie);
	}
	
	public Movie update(Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("Object cannot be null to be updated");
		}
		if (movie.getId() == null) {
			throw new IllegalArgumentException("Movie's id should not be null to update the entity");
		}
		if (!repository.existsById(movie.getId())) {
			throw new IllegalArgumentException("There is no movie with given id to update");
		}
		
		return repository.save(movie);
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Movie's id should not be null to delete the entity");
		}
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("There is no movie with given id to delete");
		}
		
		repository.deleteById(id);
	}
}