package ms.cinema.seats.models.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.seats.models.enums.ReservationStatus;
import ms.cinema.seats.models.enums.SeatType;

@Entity
@Table(name = "seats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String seatName;
	
	@Column(nullable = false, length = 1)
	private Character columnSign;
	
	@Column(nullable = false)
	private Integer rowNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SeatType seatType;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservationStatus reservationStatus;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}