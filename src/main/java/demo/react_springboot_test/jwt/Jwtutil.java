package demo.react_springboot_test.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class Jwtutil {

    private SecretKey secretKey;

    public Jwtutil(@Value("${authen.jwt.secret.key}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String CreateJWT(String category, String username, String role, Long expireMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireMs))
                .signWith(secretKey)
                .compact();
    }
}
