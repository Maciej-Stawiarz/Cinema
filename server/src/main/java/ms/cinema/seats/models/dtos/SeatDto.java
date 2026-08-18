package ms.cinema.seats.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ms.cinema.seats.models.enums.ReservationStatus;
import ms.cinema.seats.models.enums.SeatType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SeatDto {
	
	@NotNull(message = "Seats' column sign must be added")
	private Character columnSign;
	@NotNull(message = "Seats' row number must be added")
	@Positive(message = "Seats' row number must be above 0")
	private Integer rowNumber;
	@NotNull(message = "Seat type must be added")
	private SeatType seatType;
	@NotNull(message = "Seat must have a reservation status added")
	private ReservationStatus reservationStatus;
}
