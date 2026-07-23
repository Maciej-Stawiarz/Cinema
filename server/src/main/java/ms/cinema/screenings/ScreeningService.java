package ms.cinema.screenings;

import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.NotFoundException;
import ms.cinema.screenings.models.entities.Screening;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {
	
	private final ScreeningRepository repository;
	

}
