package ms.cinema.security;

import ms.cinema.security.models.dtos.LoginRequest;
import ms.cinema.security.models.dtos.RegistrationRequest;
import ms.cinema.security.jwts.JwtTokenService;
import ms.cinema.users.models.entities.User;
import ms.cinema.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSecurityServiceTest {
	
	private UserSecurityService service;
	
	@Mock
	private UserRepository repository;
	@Mock
	private JwtTokenService tokenProvider;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@BeforeEach
	public void init() {
		service = new UserSecurityService(repository, tokenProvider, passwordEncoder);
	}
	
	@Test
	public void loginWhenEmailDoesntExists() {
		LoginRequest request = new LoginRequest("email@gmail.com", "password");
		
		when(repository.findUserByEmail("email@gmail.com"))
				.thenReturn(Optional.empty());
		
		assertThatThrownBy(() -> service.login(request))
				.isInstanceOf(BadCredentialsException.class)
				.hasMessage("Invalid username or password");
	}
	
	@Test
	public void loginWhenPasswordDoesntMatch() {
		LoginRequest request = new LoginRequest("email@gmail.com", "passwor");
		
		when(repository.findUserByEmail("email@gmail.com"))
				.thenReturn(Optional.of(generateUserEntity()));
		
		assertThatThrownBy(() -> service.login(request))
				.isInstanceOf(BadCredentialsException.class)
				.hasMessage("Invalid username or password");
	}
	
	@Test
	public void loginSuccessfully() {
		LoginRequest request = new LoginRequest("email@gmail.com", "password");
		
		when(repository.findUserByEmail("email@gmail.com"))
				.thenReturn(Optional.of(generateUserEntity()));
		when(tokenProvider.generateToken(any(UserDetails.class)))
				.thenReturn("mocked-jwt-token");
		
		String token = service.login(request);
		
		assertThat(token)
				.isNotBlank()
				.isEqualTo("mocked-jwt-token");
	}
	
	@ParameterizedTest
	@MethodSource("generateInvalidRegistrationRequests")
	public void registerWhenFieldsAreInvalid(RegistrationRequest request,
											 Class<? extends Throwable> expectedException,
											 String expectedErrorMessage) {
		
			assertThatThrownBy(() -> service.register(request))
					.isInstanceOf(expectedException)
					.hasMessage(expectedErrorMessage);
	}
	
	@Test
	public void registerWhenUsernameAlreadyExists() {
		RegistrationRequest request = new RegistrationRequest(
				"Name",
				"Surname",
				"email@gmail.com",
				"Password123=");
		
		when(repository.findUserByEmail(request.email()))
				.thenReturn(Optional.of(mock(User.class)));
		
		assertThatThrownBy(() -> service.register(request))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("There already is a user with given e-mail address");
	}
	
	@Test
	public void registerSuccessfully() {
		RegistrationRequest request = new RegistrationRequest(
				"Name",
				"Surname",
				"email@gmail.com",
				"Password123=");
		
		when(repository.findUserByEmail(request.email()))
				.thenReturn(Optional.empty());
		
		service.register(request);
		
		verify(repository, times(1))
				.findUserByEmail(request.email());
		verify(repository, times(1))
				.save(any(User.class));
	}
	
	@Test
	public void loadUserByUsernameSuccessfully() {
		String username = "email@gmail.com";
		
		when(repository.findUserByEmail(username))
				.thenReturn(Optional.of(mock(User.class)));
		
		UserDetails userDetails = service.loadUserByUsername(username);
		
		assertThat(userDetails)
				.isNotNull();
	}
	
	
	private User generateUserEntity() {
		String password = passwordEncoder.encode("password");
		return new User("Name", "Surname", "email@gmail.com", password);
	}
	
	private static Stream<Arguments> generateInvalidRegistrationRequests() {
		final String PASS_ERR = "Invalid password. A password must contain one uppercase letter, o" +
				"ne number, one symbol: '-', '=', '_', '+' and be at least" +
				"6 characters long";
		
		return Stream.of(
				Arguments.of(new RegistrationRequest("", "Surname", "email@gmail.com", "password"),
							 IllegalArgumentException.class,
							 "Name must not be blank"),
				Arguments.of(new RegistrationRequest(null, "Surname", "email@gmail.com", "password"),
							 IllegalArgumentException.class,
							 "Name must not be blank"),
				
				Arguments.of(new RegistrationRequest("Name", "", "email@gmail.com", "password"),
				             IllegalArgumentException.class,
				             "Surname must not be blank"),
				Arguments.of(new RegistrationRequest("Name", null, "email@gmail.com", "password"),
				             IllegalArgumentException.class,
				             "Surname must not be blank"),
				
				Arguments.of(new RegistrationRequest("Name", "Surname", "", "password"),
				             IllegalArgumentException.class,
				             "Email must not be blank"),
				Arguments.of(new RegistrationRequest("Name", "Surname", null, "password"),
				             IllegalArgumentException.class,
				             "Email must not be blank"),
				Arguments.of(new RegistrationRequest("Name", "Surname", "@gmail.com", "password"),
				             IllegalArgumentException.class,
				             "Invalid email"),
				
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", ""),
				             IllegalArgumentException.class,
				             "Password must not be blank"),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", null),
				             IllegalArgumentException.class,
				             "Password must not be blank"),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "password"),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "Password"),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "password1"),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "password-"),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "pass"),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "Pa1_-"),
							 IllegalArgumentException.class,
							 PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "password["),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "Pa1_-"),
				             IllegalArgumentException.class,
				             PASS_ERR),
				Arguments.of(new RegistrationRequest("Name", "Surname", "email@gmail.com", "Password123"),
				             IllegalArgumentException.class,
				             PASS_ERR));
	}
}
