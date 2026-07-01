package ms.cinema.security.dtos;

import lombok.RequiredArgsConstructor;
import ms.cinema.users.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserSecurity implements UserDetails {
	
	private final User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}
	
	@Override
	public @Nullable String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getEmail();
	}
}
