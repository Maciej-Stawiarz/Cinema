package ms.cinema.screenings;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.cinema.movies.Movie;
import ms.cinema.rooms.Room;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "screenings")
public class Screening {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private LocalDateTime screeningTime;
	
	@OneToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
}