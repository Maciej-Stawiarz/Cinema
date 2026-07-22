package ms.cinema.screenings.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ms.cinema.movies.models.entities.Movie;
import ms.cinema.rooms.models.entities.Room;
import ms.cinema.screenings.models.enums.ScreeningType;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
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
	@JoinColumn(name = "screenings")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "screenings")
	private Room room;
}