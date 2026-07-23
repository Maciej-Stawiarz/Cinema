package ms.cinema.screenings.models.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.cinema.movies.models.entities.Movie;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.screenings.models.enums.ScreeningType;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Screening {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime screeningDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ScreeningType screeningType;
	
	@Column(nullable = false, length = 2)
	private String soundLanguage;
	
	@Column(length = 2)
	private String subtitleLanguage;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}