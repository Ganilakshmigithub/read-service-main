package com.spring.read_service.services;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class JWTService {
    private final String secretKey;
    public JWTService() {
        // Generate a secure secret key for signing JWTs
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
    }
    public String generateToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(getKey())
                .compact();
    }
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }
    // Extract username from JWT
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // Extract roles from JWT
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }
    // Generic method to extract a claim from the token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // Extract all claims from JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // Validate the token against user details (including username and roles)
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        final List<String> tokenRoles = extractRoles(token);
        // Extract roles from UserDetails and convert them to a List<String>
        final List<String> userRoles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
        // Validate username, token expiration, and roles
        boolean isUsernameValid = username.equals(userDetails.getUsername());
        boolean areRolesValid = tokenRoles.containsAll(userRoles) && userRoles.containsAll(tokenRoles); // Ensures roles match
        return isUsernameValid && !isTokenExpired(token) && areRolesValid;
    }
    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // Extract expiration date from JWT
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

