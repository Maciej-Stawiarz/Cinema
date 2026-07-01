package ms.cinema.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
public interface UserAPI {
	
	@Operation(method = "GET", description = "Fetch all existing users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@GetMapping("all")
	ResponseEntity<List<User>> getAll();
	
	@Operation(method = "GET", description = "Fetch a single user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when there is no object with given id")
	})
	@GetMapping("{id}")
	ResponseEntity<User> get(@PathVariable("id") Long id);
	
	@Operation(method = "PUT", description = "Update already existing user by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given object's id already exists in the database")
	})
	@PutMapping
	ResponseEntity<User> update(@RequestBody User user);
	
	@Operation(method = "DELETE", description = "Delete already existing user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given id doesn't exist in the database")
	})
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
