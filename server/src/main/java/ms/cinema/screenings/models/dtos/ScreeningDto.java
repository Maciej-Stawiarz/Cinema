package ms.cinema.screenings.models.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ms.cinema.screenings.models.enums.ScreeningType;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScreeningDto {
	
	@NotNull(message = "Screening date must be added")
	@Future(message = "Screening date must be in the future")
	private LocalDateTime screeningDate;
	@NotNull(message = "Screening type must be added")
	private ScreeningType screeningType;
	@NotBlank(message = "Sound language must be added")
	@Size(message = "Code for the sound language must be two characters long, for example: EN", min = 2, max = 2)
	private String soundLanguage;
	@Size(message = "Code for subtitle language must be two characters long, for example: EN", min = 2, max = 2)
	private String subtitleLanguage;
	@NotNull(message = "Movie reference must be added")
	@Positive(message = "Movie id needs to a positive number")
	private Long movieId;
	@NotNull(message = "Room reference must be added")
	@Positive(message = "Room id needs to be a positive number")
	private Long roomId;
}
