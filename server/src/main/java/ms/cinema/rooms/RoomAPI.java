package ms.cinema.rooms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ms.cinema.rooms.models.dtos.RoomDto;
import ms.cinema.rooms.models.entities.Room;
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
	ResponseEntity<List<RoomDto>> getAll();

	@Operation(method = "GET", description = "Fetch a single room by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@GetMapping("{id}")
	ResponseEntity<Room> get(@PathVariable("id") Long id);
	
	@Operation(method = "GET", description = "Fetch a single room by name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@GetMapping("{name}")
	ResponseEntity<Room> get(@PathVariable("name") String name);

	@Operation(method = "POST", description = "Save a room by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Returned when everything was processed properly"),
	})
	@PostMapping
	ResponseEntity<RoomDto> save(@Valid @RequestBody RoomDto room);

	@Operation(method = "PUT", description = "Update already existing room by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@PutMapping
	ResponseEntity<RoomDto> update(@RequestParam("id") Long id,
								   @Valid @RequestBody RoomDto room);

	@Operation(method = "DELETE", description = "Delete already existing room by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
	})
	@DeleteMapping("{id}")
	ResponseEntity<Room> delete(@PathVariable("id") Long id);
}
