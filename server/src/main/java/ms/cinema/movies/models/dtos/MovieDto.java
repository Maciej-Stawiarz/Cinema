package ms.cinema.movies.models.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ms.cinema.movies.models.enums.Genre;

import java.time.LocalDate;
import java.util.EnumSet;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieDto {
	
	@NotBlank(message = "Title of the movie must be added")
	private String title;
	@NotBlank(message = "Description of the movie must be added")
	private String description;
	@NotNull(message = "Release date must be added")
	private LocalDate releaseDate;
	@NotNull(message = "Duration of the movie must be added")
	@Positive(message = "Duration of the movie needs to be above 0")
	private Long duration;
	private String cast;
	@NotBlank(message = "Director of the movie must be added")
	private String director;
	private String placeAndTimeOfProduction;
	@NotEmpty(message = "Movie genres must be added")
	private EnumSet<Genre> genres;
	@NotBlank(message = "Original language of the movie must be added")
	@Size(message = "Code of the language must be 2 characters long, for example: EN", min = 2, max = 2)
	private String originalLanguage;
}
