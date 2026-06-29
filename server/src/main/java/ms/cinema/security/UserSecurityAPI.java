package ms.cinema.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ms.cinema.security.dtos.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("security")
public interface UserSecurityAPI {
	
	@Operation(method = "POST", description = "Log user into as account with given credentials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returned when everything was processed properly")
	})
	@PostMapping("login")
	ResponseEntity<Void> login(@Valid @RequestBody LoginRequest loginRequest);
}
