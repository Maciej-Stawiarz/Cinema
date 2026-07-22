package ms.cinema.movies;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
	ResponseEntity<List<Movie>> getAll();
	
	@Operation(method = "GET", description = "Fetch a single movie by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "400", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "404", description = "Returned when there is no object with given id")
	})
	@GetMapping("{id}")
	ResponseEntity<Movie> get(@PathVariable("id") Long id);
	
	@Operation(method = "POST", description = "Save a movie by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "400", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "400", description = "Returned when given object's id is not null")
	})
	@PostMapping
	ResponseEntity<Movie> save(@RequestBody Movie movie);
	
	@Operation(method = "PUT", description = "Update already existing movie by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "400", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "400", description = "Returned when given object's id is null"),
			@ApiResponse(responseCode = "400", description = "Returned when an object with given object's id already exists in the database")
	})
	@PutMapping
	ResponseEntity<Movie> update(@RequestBody Movie movie);
	
	@Operation(method = "DELETE", description = "Delete already existing movie by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "400", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "400", description = "Returned when an object with given id doesn't exist in the database")
	})
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
