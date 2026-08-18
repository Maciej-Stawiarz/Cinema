package ms.cinema.rooms.models.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.cinema.seats.models.entities.Seat;
import ms.cinema.screenings.models.entities.Screening;

import java.util.List;

@Entity
@Table(name = "rooms", uniqueConstraints = {
		@UniqueConstraint(name = "uk_name", columnNames = {"name"})
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "room", orphanRemoval = true)
	private List<Screening> screenings;
	
	@OneToMany(mappedBy = "room", orphanRemoval = true)
	private List<Seat> seats;
}