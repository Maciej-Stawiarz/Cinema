package ms.cinema.rooms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("room")
public interface RoomAPI {
	
	@Operation(method = "GET", description = "Fetch all existing rooms")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@GetMapping("all")
	ResponseEntity<List<Room>> getAll();
	
	@Operation(method = "GET", description = "Fetch a single room by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when there is no object with given id")
	})
	@GetMapping("{id}")
	ResponseEntity<Room> get(@PathVariable("id") Long id);
	
	@Operation(method = "POST", description = "Save a room by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is not null")
	})
	@PostMapping
	ResponseEntity<Room> save(@RequestBody Room room);
	
	@Operation(method = "PUT", description = "Update already existing room by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given object's id already exists in the database")
	})
	@PutMapping
	ResponseEntity<Room> update(@RequestBody Room room);
	
	@Operation(method = "DELETE", description = "Delete already existing room by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given id doesn't exist in the database")
	})
	@DeleteMapping("{id}")
	ResponseEntity<Room> delete(@PathVariable("id") Long id);
}
