package ms.cinema.users;

import lombok.RequiredArgsConstructor;
import ms.cinema.users.models.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI{
	
	private final UserService service;
	
	@Override
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(
				service.getAll(),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<User> get(Long id) {
		return new ResponseEntity<>(
				service.get(id),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<User> update(User user) {
		return new ResponseEntity<>(
				service.update(user),
				HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> delete(Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}