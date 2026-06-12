package ms.cinema.screenings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("screening")
public interface ScreeningAPI {
	
	@Operation(method = "GET", description = "Fetch all existing screenings")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
		
	})
	@GetMapping("all")
	ResponseEntity<List<Screening>> getAll();
	
	@Operation(method = "GET", description = "Fetch a single screening by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when there is no object with given id")
	})
	@GetMapping("{id}")
	ResponseEntity<Screening> get(@PathVariable("id") Long id);
	
	@Operation(method = "POST", description = "Save a screening by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is not null")
	})
	@PostMapping
	ResponseEntity<Screening> save(@RequestBody Screening screening);
	
	@Operation(method = "PUT", description = "Update already existing screening by providing JSON body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given object is null"),
			@ApiResponse(responseCode = "200", description = "Returned when given object's id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given object's id already exists in the database")
	})
	@PutMapping
	ResponseEntity<Screening> update(@RequestBody Screening screening);
	
	@Operation(method = "DELETE", description = "Delete already existing screening by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "200", description = "Returned when given id is null"),
			@ApiResponse(responseCode = "200", description = "Returned when an object with given id doesn't exist in the database")
	})
	@DeleteMapping("{id}")
	ResponseEntity<Screening> delete(@PathVariable("id") Long id);
}