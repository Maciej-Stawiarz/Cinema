package ms.cinema.rooms.seats.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ms.cinema.rooms.Room;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String seatName;
	private Character columnNumber;
	private Integer rowNumber;
	@Enumerated(EnumType.STRING)
	private SeatType seatType;
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	public Seat(Long id, Character columnNumber, Integer rowNumber, SeatType seatType, ReservationStatus reservationStatus) {
		this.id = id;
		this.columnNumber = columnNumber;
		this.rowNumber = rowNumber;
		this.seatName = columnNumber + String.valueOf(rowNumber);
		this.seatType = seatType;
		this.reservationStatus = reservationStatus;
	}
}
