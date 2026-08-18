package ms.cinema.rooms.utilities;

import ms.cinema.rooms.models.dtos.RoomDto;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.seats.models.dtos.SeatDto;
import ms.cinema.seats.models.entities.Seat;
import ms.cinema.seats.utilities.SeatMapper;

import java.util.List;

public final class RoomMapper {
	
	public static Room toEntity(RoomDto dto) {
		if (dto == null) {
			return new Room();
		}
		
		List<Seat> seats = dto.getSeats().stream()
				.map(SeatMapper::toEntity)
				.toList();
		
		return Room.builder()
				.name(dto.getName())
				.seats(seats)
				.build();
	}
	
	public static RoomDto toDTO(Room entity) {
		if (entity == null) {
			return new RoomDto();
		}
		
		List<SeatDto> seatDtos = entity.getSeats().stream()
				.map(SeatMapper::toDTO)
				.toList();
		
		return RoomDto.builder()
				.name(entity.getName())
				.seats(seatDtos)
				.build();
	}
}