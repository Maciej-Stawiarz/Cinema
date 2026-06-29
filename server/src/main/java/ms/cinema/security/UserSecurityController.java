package ms.cinema.security2;

import lombok.RequiredArgsConstructor;
import ms.cinema.security2.dtos.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSecurityController implements UserSecurityAPI {
	
	private final UserSecurityService service;
	
	@Override
	public ResponseEntity<Void> login(LoginRequest request) {
		String token = service.login(request.email, request.password);
		
		return ResponseEntity
				.ok()
				.header("Authorization", "Bearer " + token)
				.build();
	}
}
