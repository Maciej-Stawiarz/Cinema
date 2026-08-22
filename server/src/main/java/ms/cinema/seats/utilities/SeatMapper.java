package ms.cinema.seats.utilities;

import ms.cinema.rooms.models.entities.Room;
import ms.cinema.seats.models.dtos.SeatDto;
import ms.cinema.seats.models.entities.Seat;

public final class SeatMapper {

	public static Seat toEntity(SeatDto seatDto, Room roomEntity) {
		if (seatDto == null) {
			return new Seat();
		}
		
		String seatName = String.valueOf(seatDto.getColumnSign()) + seatDto.getRowNumber();
		
		return Seat.builder()
				.seatName(seatName)
				.columnSign(seatDto.getColumnSign())
				.rowNumber(seatDto.getRowNumber())
				.seatType(seatDto.getSeatType())
				.reservationStatus(seatDto.getReservationStatus())
				.room(roomEntity)
				.build();
	}
	
	public static SeatDto toDTO(Seat entity) {
		if (entity == null) {
			return new SeatDto();
		}
		
		return SeatDto.builder()
				.columnSign(entity.getColumnSign())
				.rowNumber(entity.getRowNumber())
				.seatType(entity.getSeatType())
				.reservationStatus(entity.getReservationStatus())
				.build();
	}
}