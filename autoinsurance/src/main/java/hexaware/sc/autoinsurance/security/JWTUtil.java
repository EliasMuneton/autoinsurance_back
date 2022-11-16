package hexaware.sc.autoinsurance.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import hexaware.sc.autoinsurance.domain.Token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email, Long id, Long userRoleId) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("user_email", email)
                .withClaim("user_id", id)
                .withClaim("user_role_id", userRoleId)
                .withIssuedAt(new Date())
                .withIssuer("autonisurance/Hexaware")
                .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("autonisurance/Hexaware")
                .build();
        return verifier.verify(token);

    }

    public Token geTokenData () {
        Map<String, Claim> claimsToken = (Map<String, Claim>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Token(claimsToken.get("user_id").asLong(), claimsToken.get("user_role_id").asLong(), claimsToken.get("user_email").asString());
    }
}