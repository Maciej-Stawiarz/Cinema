package ms.cinema.screenings;

import lombok.RequiredArgsConstructor;
import ms.cinema.screenings.models.entities.Screening;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScreeningController implements ScreeningAPI {
	
	private final ScreeningService service;
	
	@Override
	public ResponseEntity<List<Screening>> getAll() {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Screening> get(Long id) {
		return new ResponseEntity<>(
				service.get(id),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Screening> save(Screening screening) {
		return new ResponseEntity<>(
				service.save(screening),
				HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<Screening> update(Screening screening) {
		return new ResponseEntity<>(
				service.update(screening),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Screening> delete(Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
