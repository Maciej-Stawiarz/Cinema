package ms.cinema.screenings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ms.cinema.screenings.models.dtos.ScreeningDto;
import ms.cinema.screenings.models.entities.Screening;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("screening")
public interface ScreeningAPI {
	
	@Operation(method = "GET", description = "Fetch all screenings for given parameters")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@GetMapping
	ResponseEntity<List<ScreeningDto>> getScreenings(@RequestParam(required = false, name = "screeningDate") LocalDateTime screeningDate,
													 @RequestParam(required = false, name = "movieId") Long movieId,
													 @RequestParam(required = false, name = "movieTitle") String movieTitle);
	
	@Operation(method = "GET", description = "Fetch screening by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@GetMapping("{id}")
	ResponseEntity<Screening> getScreening(@PathVariable("id") Long id);
	
	@Operation(method = "POST", description = "Add screening to a movie")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@PostMapping
	ResponseEntity<ScreeningDto> addScreeningToMovie(@Valid @RequestBody ScreeningDto screeningDto);
	
	@Operation(method = "DELETE", description = "Delete screening")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@DeleteMapping("{id}")
	ResponseEntity<Void> deleteScreening(@PathVariable("id") Long id);
}