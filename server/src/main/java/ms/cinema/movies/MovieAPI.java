package ms.cinema.movies;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("movie")
public interface MovieAPI {
	
	@GetMapping("all")
	ResponseEntity<List<Movie>> getAll();
	
	@GetMapping("{id}")
	ResponseEntity<Movie> get(@PathVariable("id") Long id);
	
	@PostMapping
	ResponseEntity<Movie> save(@RequestBody Movie movie);
	
	@PutMapping
	ResponseEntity<Movie> update(@RequestBody Movie movie);
	
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
