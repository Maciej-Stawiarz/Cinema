package ms.cinema.security2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotBlank
	@Email
	public String email;
	@NotBlank
	@Size(min = 6)
	public String password;
}
