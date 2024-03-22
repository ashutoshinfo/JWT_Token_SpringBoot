package info.ashutosh.configure.jwt.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

	// Read the secret string from application.properties
	@Value("${JWT_KEY}")
	private String SECRET_STRING;

	public String generateToken(UserDetails userDetails) {
		return generateToken(null, userDetails);
	}

	public String generateToken(Map<String, Object> claims, UserDetails userDetails) {

		Date issuedAt = new Date(System.currentTimeMillis());
		Date expirationAt = new Date(System.currentTimeMillis() + 60 * 24);

		return Jwts.builder().claims(claims).subject(userDetails.getUsername()).issuedAt(issuedAt)
				.expiration(expirationAt).signWith(getPublicSigningKey(), Jwts.SIG.HS256).compact();

	}
	
//	public boolean isTokenvalid(String token) {
//		
//	}

	public String extrectUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().verifyWith(getPublicSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	private SecretKey getPublicSigningKey() {
		byte[] decode = Decoders.BASE64.decode(SECRET_STRING);

		SecretKey secret = Keys.hmacShaKeyFor(decode);
		return secret;
	}

}
