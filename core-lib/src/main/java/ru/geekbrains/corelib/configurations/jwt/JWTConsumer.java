package ru.geekbrains.corelib.configurations.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.corelib.dtos.UserInfo;

import java.util.List;

@Component
public class JWTConsumer {

    @Value("$(jwt.secret)")
    private String JWT_SECRET;

    public UserInfo parseToken(String token) throws ExpiredJwtException {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);
        Long userId = jwsClaims.getBody()
                .get("id", Long.class);
        String login = jwsClaims.getBody()
                .get("login",String.class);
        List<String> roles = jwsClaims.getBody()
                .get("roles", List.class);

        return new UserInfo(userId,login,roles);
    }
}
