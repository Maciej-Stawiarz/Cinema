package ms.cinema.rooms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.cinema.rooms.seats.models.Seat;
import ms.cinema.screenings.Screening;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private Set<Seat> seats = new HashSet<>();
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private Set<Screening> screenings = new HashSet<>();
}
