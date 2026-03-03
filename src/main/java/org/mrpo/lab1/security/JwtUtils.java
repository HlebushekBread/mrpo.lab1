package org.mrpo.lab1.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.lifetime}")
    private int jwtLifetime;

    public String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> authoritiesList = userDetails.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).toList();
        claims.put("id", String.valueOf(userDetails.getUser().getId()));
        claims.put("fullName", userDetails.getUser().getFullName());
        claims.put("role", userDetails.getUser().getRole().getName());
        claims.put("authorities", authoritiesList);
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtLifetime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public String getIdFromToken(String token) {
        return getAllClaimsFromToken(token).get("id", String.class);
    }

    public String getFullNameFromToken(String token) {
        return getAllClaimsFromToken(token).get("fullName", String.class);
    }

    public List<String> getAuthoritiesFromToken(String token) {
        return getAllClaimsFromToken(token).get("authorities", List.class);
    }
}