package ms.cinema.security;

import lombok.RequiredArgsConstructor;
import ms.cinema.security.models.dtos.LoginRequest;
import ms.cinema.security.models.dtos.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSecurityController implements UserSecurityAPI {
	
	private final UserSecurityService service;
	
	@Override
	public ResponseEntity<Void> login(LoginRequest request) {
		String token = service.login(request);
		
		return ResponseEntity
				.ok()
				.header("Authorization", "Bearer " + token)
				.build();
	}
	
	@Override
	public ResponseEntity<Void> register(RegistrationRequest request) {
		service.register(request);
		
		return ResponseEntity
				.ok()
				.build();
	}
}
