package ms.cinema.security;

import lombok.RequiredArgsConstructor;
import ms.cinema.security.dtos.LoginRequest;
import ms.cinema.security.dtos.RegistrationRequest;
import ms.cinema.security.dtos.UserSecurity;
import ms.cinema.security.jwt.JwtTokenService;
import ms.cinema.users.User;
import ms.cinema.users.UserRepository;
import ms.cinema.utils.Validators;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService {
	
	private final UserRepository repository;
	private final JwtTokenService tokenProvider;
	private final PasswordEncoder passwordEncoder;
	
	public String login(LoginRequest request) {
		User user = findUserByUsername(request.email());
		
		String hashedPassword = passwordEncoder.encode(request.password());
		
		if (!passwordEncoder.matches(hashedPassword, user.getPassword())) {
			throw new BadCredentialsException("Invalid username or password");
		}
		
		UserDetails userDetails = new UserSecurity(user);
		
		return tokenProvider.generateToken(userDetails);
	}
	
	public void register(RegistrationRequest request) {
		String name = request.name();
		String surname = request.surname();
		String username = request.email();
		String password = request.password();
		
		Validators.requireNotBlank(name, "Name");
		Validators.requireNotBlank(surname, "Surname");
		Validators.requireValidEmail(username);
		Validators.requireValidPassword(password);
		
		if (repository.findUserByEmail(username).isPresent()) {
			throw new IllegalArgumentException("There already is an user with given e-mail address");
		}
		
		User user = new User(
				name,
				surname,
				username,
				passwordEncoder.encode(password));
		
		repository.save(user);
	}
	
	public UserDetails loadUserByUsername(String username) {
		User user = findUserByUsername(username);
		return new UserSecurity(user);
	}
	
	private User findUserByUsername(String username) {
		return repository
				.findUserByEmail(username)
				.orElseThrow(() -> new BadCredentialsException("Invalid credentials! User not found."));
	}
}
