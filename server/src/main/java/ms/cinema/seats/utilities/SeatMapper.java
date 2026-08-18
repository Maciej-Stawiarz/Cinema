package ms.cinema.seats.utilities;

import ms.cinema.seats.models.dtos.SeatDto;
import ms.cinema.seats.models.entities.Seat;

public final class SeatMapper {

	public static Seat toEntity(SeatDto dto) {
		if (dto == null) {
			return new Seat();
		}
		
		return Seat.builder()
				.columnSign(dto.getColumnSign())
				.rowNumber(dto.getRowNumber())
				.seatType(dto.getSeatType())
				.reservationStatus(dto.getReservationStatus())
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