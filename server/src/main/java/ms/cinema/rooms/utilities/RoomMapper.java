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
		
		Room room = Room.builder()
				.name(dto.getName())
				.build();
		
		List<Seat> seats = dto.getSeats().stream()
				.map(seatDto -> SeatMapper.toEntity(seatDto, room))
				.toList();
		room.setSeats(seats);
		
		return room;
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