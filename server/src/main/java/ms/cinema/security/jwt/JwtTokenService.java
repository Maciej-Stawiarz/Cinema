package ms.cinema.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenService {
	
	@Value("${security.jwt.expiration-ms}")
	private long jwtExpirationInMs;
	
	@Value("${security.jwt.secret-key}")
	private String secretKey;
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(getSignedKey())
				.compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			return getExpirationDateFromToken(token).after(new Date());
		} catch (JwtException exception) {
			return false;
		}
	}
	
	public Date getExpirationDateFromToken(String token) {
		return extractAllTokenClaims(token).getExpiration();
	}
	
	public String getUsernameFromToken(String token) {
		return extractAllTokenClaims(token).getSubject();
	}
	
	private Claims extractAllTokenClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSignedKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	private SecretKey getSignedKey() {
		byte[] keyBytes = Base64.getDecoder().decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}