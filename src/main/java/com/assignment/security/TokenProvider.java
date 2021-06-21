package com.assignment.security;

import com.assignment.repository.UserRepository;
import com.assignment.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    private JwtParser jwtParser;


    @Value("${security.jwt.token.base64-secret:base64-secret}")
    private String base64Secret;

    @Value("${security.jwt.token.token-validity-in-seconds:token-validity-in-seconds}")
    private long tokenValidityInMilliseconds;

    private final UserRepository userRepository;

    @PostConstruct
    public void initialize() {
        byte[] keyBytes;
        log.debug("Using a Base64-encoded JWT secret key");
        keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        this.tokenValidityInMilliseconds =
                1000 * tokenValidityInMilliseconds;
    }

    public String createToken(Authentication authentication) {

        User user = userRepository.findByUserName((String) authentication.getPrincipal()).orElseThrow(() -> new AccessDeniedException("Access denied"));
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String subject = user.getUserName();
        long now = (new Date()).getTime();
        Date validity;
        validity = new Date(now + this.tokenValidityInMilliseconds);
        String role = user.getRole().toString();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("userName", user.getUserName());
        claims.put("role", role);

        return Jwts.builder()
                .setSubject(subject)
                .claim(AUTHORITIES_KEY, authorities)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication extractAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        String subject = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(subject, null, authorities);
    }


    public String getRole(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }

    public Long getUserId(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("userId", Long.class);
    }

    public String getUserName(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("userName", String.class);
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token");
            log.trace("Invalid JWT token trace", e);
        }
        return false;
    }
}