package ms.cinema.movies;

import lombok.RequiredArgsConstructor;
import ms.cinema.movies.models.dtos.MovieDto;
import ms.cinema.movies.models.entities.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieAPI {
	
	private final MovieService service;
	
	@Override
	public ResponseEntity<List<MovieDto>> getAll() {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<Movie> get(String title) {
		return new ResponseEntity<>(
				service.get(title),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<MovieDto> save(MovieDto movieDto) {
		return new ResponseEntity<>(
				service.save(movieDto),
				HttpStatus.CREATED
		);
	}
	
	@Override
	public ResponseEntity<MovieDto> update(Long id, MovieDto movie) {
		return new ResponseEntity<>(
				service.update(id, movie),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<Void> delete(Long id) {
		service.delete(id);
		return new ResponseEntity<>(
				HttpStatus.OK
		);
	}
}