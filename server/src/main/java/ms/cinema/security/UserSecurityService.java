package ms.cinema.security;

import lombok.RequiredArgsConstructor;
import ms.cinema.security.dtos.UserSecurity;
import ms.cinema.security.jwt.JwtTokenService;
import ms.cinema.users.User;
import ms.cinema.users.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService {
	
	private final UserRepository repository;
	private final JwtTokenService tokenProvider;
	
	public String login(String username, String password) {
		User user = findUserByUsername(username);
		
		//TODO: Verify, whether password matches given password. Need to take care of password hashing, but it's still not ready
		
		UserDetails userDetails = new UserSecurity(user);
		
		return tokenProvider.generateToken(userDetails);
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
