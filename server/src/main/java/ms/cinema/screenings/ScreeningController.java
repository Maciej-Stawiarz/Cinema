package ms.cinema.screenings;

import lombok.RequiredArgsConstructor;
import ms.cinema.screenings.models.dtos.ScreeningDto;
import ms.cinema.screenings.models.entities.Screening;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScreeningController implements ScreeningAPI {
	
	private final ScreeningService service;
	
	
	@Override
	public ResponseEntity<List<ScreeningDto>> getScreenings(LocalDateTime screeningDate,
															Long movieId,
															String movieTitle) {
		return new ResponseEntity<>(
				service.getScreenings(screeningDate, movieId, movieTitle),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<Screening> getScreening(Long id) {
		return new ResponseEntity<>(
				service.getScreening(id),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<ScreeningDto> addScreeningToMovie(ScreeningDto screeningDto) {
		return new ResponseEntity<>(
				service.addScreeningToMovie(screeningDto),
				HttpStatus.CREATED
		);
	}
	
	@Override
	public ResponseEntity<Void> deleteScreening(Long id) {
		service.deleteScreening(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
