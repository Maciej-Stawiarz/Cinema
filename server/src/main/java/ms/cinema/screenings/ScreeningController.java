package ms.cinema.screenings;

import lombok.RequiredArgsConstructor;
import ms.cinema.screenings.models.entities.Screening;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScreeningController {
	
	private final ScreeningService service;

}
