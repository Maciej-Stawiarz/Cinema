package ms.cinema.movies;

import lombok.RequiredArgsConstructor;
import ms.cinema.movies.models.dtos.MovieDto;
import ms.cinema.movies.models.entities.Movie;
import ms.cinema.movies.utilities.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	// TODO: Methods that fetch movies for today, for given genres, for privileges that user has??, for specific rooms
	
	private final MovieRepository repository;
	
	// TODO: Maybe I should add some extra validation? Most of it would be in the DTO, so maybe some db constraints?
	public MovieDto save(MovieDto movieDto) {
		if (movieDto == null) {
			throw new RuntimeException(); // TODO: Change exception type and message
		}
		
		Movie movie = MovieMapper.toEntity(movieDto);
		Movie savedMovie = repository.save(movie);
		return MovieMapper.toDTO(savedMovie);
	}
	
	/** TODO: Maybe filter it out based on the constraints of user privileges.
	 *  TODO: Can verify some db contraints, user constraints, pagination,
	 *  TODO: filtering output by calling only those for movies that are
	 *  TODO: aired today, etc...
	 */
	public List<MovieDto> getAll() {
		return repository.findAll().stream()
				.map(MovieMapper::toDTO)
				.toList();
	}
	
	public Movie get(Long id) {
		if (id == null) {
			throw new RuntimeException(); // TODO: Change exception type and message
		}
		
		return repository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("")); // TODO: Change exception type and message
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new RuntimeException(); // TODO: Change exception type and message
		}
		if (!repository.existsById(id)) {
			throw new RuntimeException(); // TODO: Change exception type and message
		}
		
		repository.deleteById(id);
	}
}