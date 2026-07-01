package ms.cinema.utils;

import static ms.cinema.utils.ValidatorPatters.EMAIL_VALIDATOR_PATTERN;
import static ms.cinema.utils.ValidatorPatters.PASSWORD_VALIDATOR_PATTERN;

public final class Validators {
	
	private Validators() {}
	
	public static void requireNotBlank(String value, String fieldName) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(fieldName + " must not be blank");
		}
	}
	
	public static void requireValidEmail(String email) {
		requireNotBlank(email, "Email");
		if (!EMAIL_VALIDATOR_PATTERN.matcher(email).matches()) {
			throw new IllegalArgumentException("Invalid email");
		}
	}
	
	public static void requireValidPassword(String password) {
		requireNotBlank(password, "Password");
		if (!PASSWORD_VALIDATOR_PATTERN.matcher(password).matches()) {
			throw new IllegalArgumentException("Invalid password");
		}
	}
}