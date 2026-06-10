package ms.cinema.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController implements MovieAPI {
	
	private final MovieService service;
	
	public ResponseEntity<List<Movie>> getAll() {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK);
	}
	
	public ResponseEntity<Movie> get(Long id) {
		return new ResponseEntity<>(
				service.get(id),
				HttpStatus.OK);
	}
	
	public ResponseEntity<Movie> save(Movie movie) {
		return new ResponseEntity<>(
				service.save(movie),
				HttpStatus.CREATED);
	}
	
	public ResponseEntity<Movie> update(Movie movie) {
		return new ResponseEntity<>(
				service.update(movie),
				HttpStatus.OK);
	}
	
	public ResponseEntity<Void> delete(Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
