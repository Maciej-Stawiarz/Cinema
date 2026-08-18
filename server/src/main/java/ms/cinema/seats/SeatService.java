package ms.cinema.seats;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.models.exceptions.NotFoundException;
import ms.cinema.rooms.RoomService;
import ms.cinema.rooms.models.dtos.RoomDto;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.rooms.utilities.RoomMapper;
import ms.cinema.seats.models.dtos.SeatDto;
import ms.cinema.seats.models.entities.Seat;
import ms.cinema.seats.models.enums.ReservationStatus;
import ms.cinema.seats.utilities.SeatMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeatService {
	
	private final SeatRepository seatRepository;
	private final RoomService roomService;
	
	public List<Seat> getAllSeatsFromARoom(Long roomID) {
		Room foundRoom = roomService.get(roomID);
		
		return foundRoom.getSeats();
	}
	
	public Seat get(Long seatID) {
		return seatRepository
				.findById(seatID)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a seat with id: %d", seatID)));
	}
	
	@Transactional
	public RoomDto addSeatsToARoom(Long roomID,
								   List<SeatDto> seatDtoList) {
		
		Room foundRoom = roomService.get(roomID);
		
		RoomDto foundRoomDTO = RoomMapper.toDTO(foundRoom);
		foundRoomDTO.getSeats().addAll(seatDtoList);
		
		return roomService.update(roomID, foundRoomDTO);
	}
	
	@Transactional
	public SeatDto updateReservationStatus(Long seatID,
	                                       ReservationStatus reservationStatus) {
		Seat foundSeat = seatRepository
				.findById(seatID)
				.orElseThrow(() -> new NotFoundException("There is no seat with given id"));
		
		foundSeat.setReservationStatus(reservationStatus);
		
		Seat savedSeat = seatRepository.save(foundSeat);
		return SeatMapper.toDTO(savedSeat);
	}
	
	@Transactional
	public void removeSeatsFromARoom(Long roomID,
									 Long... seatIDs) {
		
		Set<Long> idsToRemove = Set.of(seatIDs);
		Room foundRoom = roomService.get(roomID);
		
		List<Seat> updatedSeats = foundRoom.getSeats().stream()
				.filter(seat -> !idsToRemove.contains(seat.getId()))
				.toList();
		
		foundRoom.setSeats(updatedSeats);
		roomService.update(roomID, RoomMapper.toDTO(foundRoom));
	}
}