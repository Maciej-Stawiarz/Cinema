package ms.cinema.utils;

import java.util.regex.Pattern;

public final class ValidatorPatters {
	
	private ValidatorPatters() {}
	
	public static final Pattern EMAIL_VALIDATOR_PATTERN =
			Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
	
	public static final Pattern PASSWORD_VALIDATOR_PATTERN =
			Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[-=_+]).{6,}$");
}
