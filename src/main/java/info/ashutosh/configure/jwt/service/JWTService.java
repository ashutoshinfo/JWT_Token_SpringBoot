package info.ashutosh.configure.jwt.service;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

	@Value("${JWT_KEY}") // Read the secret string from application.properties
	private String SECRET_STRING;

	public String extrectUsername(String token) {
		return token;

	}

//	public Claims getCh

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().verifyWith(getPublicSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	private SecretKey getPublicSigningKey() {
		// TODO Auto-generated method stub
		byte[] decode = Decoders.BASE64.decode(SECRET_STRING);

		SecretKey secret = Keys.hmacShaKeyFor(decode);
		return secret;
	}

}
