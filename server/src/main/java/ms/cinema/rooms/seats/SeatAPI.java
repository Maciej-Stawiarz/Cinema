package ms.cinema.rooms.seats;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ms.cinema.rooms.seats.models.Seat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("seat")
public interface SeatAPI {
	
	@Operation(method = "GET", description = "Fetch all existing seats")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@GetMapping("all")
	ResponseEntity<List<Seat>> getAll();
	
	@Operation(method = "GET", description = "Fetch a single seat by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when there is no object with given id")
	})
	@GetMapping("{id}")
	ResponseEntity<Seat> get(@PathVariable("id") Long id);
	
	@Operation(method = "POST", description = "Save a seat by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is not null")
	})
	@PostMapping
	ResponseEntity<Seat> save(@RequestBody Seat seat);
	
	@Operation(method = "PUT", description = "Update already existing seat by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given object's id already exists in the database")
	})
	@PutMapping
	ResponseEntity<Seat> update(@RequestBody Seat seat);
	
	@Operation(method = "DELETE", description = "Delete already existing seat be id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given id doesn't exist in the database")
	})
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
	
	
}
