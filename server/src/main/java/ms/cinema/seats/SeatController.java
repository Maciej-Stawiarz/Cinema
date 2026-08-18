package ms.cinema.seats;

import lombok.RequiredArgsConstructor;
import ms.cinema.rooms.models.dtos.RoomDto;
import ms.cinema.seats.models.dtos.SeatDto;
import ms.cinema.seats.models.entities.Seat;
import ms.cinema.seats.models.enums.ReservationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController implements SeatAPI {
	
	private final SeatService service;
	
	@Override
	public ResponseEntity<List<Seat>> getAllSeatsFromARoom(Long roomID) {
		return new ResponseEntity<>(
				service.getAllSeatsFromARoom(roomID),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<Seat> get(Long seatID) {
		return new ResponseEntity<>(
				service.get(seatID),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<RoomDto> addSeatsToARoom(Long roomID, List<SeatDto> seatDTOList) {
		return new ResponseEntity<>(
				service.addSeatsToARoom(roomID, seatDTOList),
				HttpStatus.CREATED
		);
	}
	
	@Override
	public ResponseEntity<SeatDto> updateReservationStatusOfASeat(Long seatID, ReservationStatus reservationStatus) {
		return new ResponseEntity<>(
				service.updateReservationStatus(seatID, reservationStatus),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<Void> removeSeatsFromARoom(Long roomID, Long[] seatIDs) {
		service.removeSeatsFromARoom(roomID, seatIDs);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}