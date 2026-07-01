package ms.cinema.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
		@NotBlank String name,
		@NotBlank String surname,
		@NotBlank @Email String email,
		@NotBlank @Size(min = 6) String password) {
}
