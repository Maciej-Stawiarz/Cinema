package ms.cinema.seats.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.seats.models.enums.ReservationStatus;
import ms.cinema.seats.models.enums.SeatType;

@Entity
@Table(name = "seats", uniqueConstraints =
	@UniqueConstraint(name = "uk_seat", columnNames = {"seatName", "room_id"}))
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@JsonIgnore
	private Room room;
}