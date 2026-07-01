package ms.cinema.users;

import lombok.RequiredArgsConstructor;
import ms.cinema.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	
	public List<User> getAll() {
		return repository.findAll();
	}
	
	public User get(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("User's id should be present to fetch it");
		}
		
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Could not find a user with id: %d", id)));
	}
	
	public User update(User user) {
		if (user == null) {
			throw new IllegalArgumentException("Object cannot be null to be updated");
		}
		if (user.getId() == null) {
			throw new IllegalArgumentException("User's id should not be null to update the entity");
		}
		if (!repository.existsById(user.getId())) {
			throw new IllegalArgumentException("There is no user with given id to update");
		}
		
		return repository.save(user);
	}
	
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("User's id should not be null to delete the entity");
		}
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("There is no user with given id to delete");
		}
		
		repository.deleteById(id);
	}
}
