package ms.cinema.movies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ms.cinema.movies.models.dtos.MovieDto;
import ms.cinema.movies.models.entities.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("movie")
public interface MovieAPI {
	
	@Operation(method = "GET", description = "Fetch all existing movies")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@GetMapping("all")
	ResponseEntity<List<MovieDto>> getAll();
	
	@Operation(method = "GET", description = "Fetch a single movie by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@GetMapping("{id}")
	ResponseEntity<Movie> get(@PathVariable("id") Long id);
	
	@Operation(method = "GET", description = "Fetch a single movie by title")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@GetMapping
	ResponseEntity<Movie> get(@RequestParam("title") String title);
	
	@Operation(method = "POST", description = "Save a movie by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Returned when everything was processed properly"),
	})
	@PostMapping
	ResponseEntity<MovieDto> save(@Valid @RequestBody MovieDto movie);
	
	@Operation(method = "PUT", description = "Update already existing movie by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@PutMapping("{id}")
	ResponseEntity<MovieDto> update(@PathVariable("id") Long id,
									@Valid @RequestBody MovieDto movie);

	@Operation(method = "DELETE", description = "Delete already existing movie by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}