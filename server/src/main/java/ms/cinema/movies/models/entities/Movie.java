package ms.cinema.movies.models.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.cinema.movies.models.enums.Genre;
import ms.cinema.screenings.models.entities.Screening;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

@Entity
@Table(name = "movies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column(nullable = false)
	private Long duration;
	
	private String cast;
	
	@Column(nullable = false)
	private String director;
	
	private String placeAndTimeOfProduction;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumSet<Genre> genres;
	
	@Column(name = "original_language_code", nullable = false, length = 2)
	private String originalLanguage;
	
	@OneToMany(mappedBy = "movie", orphanRemoval = true, fetch = FetchType.EAGER)
	List<Screening> screenings;
}