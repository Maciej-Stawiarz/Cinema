package ms.cinema.rooms.models.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ms.cinema.seats.models.dtos.SeatDto;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoomDto {
	
	@NotBlank(message = "Room name must be added")
	private String name;
	
	@NotEmpty(message = "New seats must be added to the room")
	@Valid
	private List<SeatDto> seats;
}