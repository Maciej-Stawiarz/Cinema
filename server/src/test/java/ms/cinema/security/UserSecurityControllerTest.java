package ms.cinema.security;

import ms.cinema.security.models.dtos.LoginRequest;
import ms.cinema.security.models.dtos.RegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSecurityControllerTest {
	
	private UserSecurityController controller;
	
	@Mock
	private UserSecurityService service;
	
	@BeforeEach
	public void init() {
		controller = new UserSecurityController(service);
	}
	
	@Test
	public void loginSuccessfully() {
		LoginRequest request = new LoginRequest(
				"email@gmail.com",
				"Password123=");
		
		when(service.login(request))
				.thenReturn("mocked-jwt-token");
		
		ResponseEntity<Void> response = controller.login(request);
		
		verify(service, times(1))
				.login(request);
		
		assertThat(response)
				.isNotNull()
					.extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
					.containsExactly(HttpStatus.OK, null);
		
		assertThat(response.getHeaders().getFirst("Authorization"))
				.isEqualTo("Bearer mocked-jwt-token");
	}
	
	@Test
	public void registerSuccessfully() {
		RegistrationRequest request = new RegistrationRequest(
				"Name",
				"Surname",
				"email@gmail.com",
				"Password123=");
		
		ResponseEntity<Void> response = controller.register(request);
		
		verify(service, times(1))
				.register(request);
		
		assertThat(response)
				.isNotNull()
				.extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
				.containsExactly(HttpStatus.OK, null);
	}
}
