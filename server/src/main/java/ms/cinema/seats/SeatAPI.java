package ms.cinema.seats;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ms.cinema.rooms.models.dtos.RoomDto;
import ms.cinema.seats.models.dtos.SeatDto;
import ms.cinema.seats.models.entities.Seat;
import ms.cinema.seats.models.enums.ReservationStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("room")
public interface SeatAPI {
	
	@Operation(method = "GET", description = "Fetch all seats from a room")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "404", description = "Returned when room with given ID was not found")
	})
	@GetMapping("{room_id}/seat/all")
	ResponseEntity<List<Seat>> getAllSeatsFromARoom(@PathVariable("room_id") Long roomID);
	
	@Operation(method = "GET", description = "Fetch a seat by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "404", description = "Returned when seat with given ID was not found")
	})
	@GetMapping("seat/{seat_id}")
	ResponseEntity<Seat> get(@PathVariable("seat_id") Long seatID);
	
	@Operation(method = "POST", description = "Add a list of seats to a room")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "404", description = "Returned when room with given ID was not found")
	})
	@PostMapping("{room_id}")
	ResponseEntity<RoomDto> addSeatsToARoom(@PathVariable("room_id") Long roomID,
											@Valid @RequestBody List<SeatDto> seatDTOList);
	
	@Operation(method = "PUT", description = "Update reservation status of a seat")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "404", description = "Returned when seat with given ID was not found")
	})
	@PutMapping("seat/{seat_id}")
	ResponseEntity<SeatDto> updateReservationStatusOfASeat(@PathVariable("seat_id") Long seatID,
														   @RequestParam("reservation_status") ReservationStatus reservationStatus);
	
	@Operation(method = "DELETE", description = "Delete a seat/s from a room")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "404", description = "Returned when seat with given ID was not found")
	})
	@DeleteMapping("{room_id}")
	ResponseEntity<Void> removeSeatsFromARoom(@PathVariable("room_id") Long roomID,
											  @RequestParam("seat_ids") Long[] seatIDs);
}