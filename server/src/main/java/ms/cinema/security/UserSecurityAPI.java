package ms.cinema.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ms.cinema.security.models.dtos.LoginRequest;
import ms.cinema.security.models.dtos.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("security")
public interface UserSecurityAPI {
	
	@Operation(method = "POST", description = "Log user into as account with given credentials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "400", description = "Returned when credentials were invalid")
		
	})
	@PostMapping("login")
	ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request);
	
	@Operation(method = "POST", description = "Register an user in the application")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly"),
			@ApiResponse(responseCode = "400", description = "Returned when a field in the registration form was not present"),
			@ApiResponse(responseCode = "400", description = "Returned when there is already a user with that e-mail"),
		
	})
	@PostMapping("register")
	ResponseEntity<Void> register(@Valid @RequestBody RegistrationRequest request);
}