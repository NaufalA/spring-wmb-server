package com.warungmakanbahari.warungmakanbahari.shared.utils;

import com.warungmakanbahari.warungmakanbahari.shared.exceptions.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JwtUtility {
    private final KeyPair keyPair;

    private final Long expiration;

    public JwtUtility(@Value("${jwt.expiration}") Long expiration) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        this.keyPair = keyGen.generateKeyPair();
        this.expiration = expiration;
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(keyPair.getPublic()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token Expired");
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException("JWT Unsupported");
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Token Malformed");
        } catch (SignatureException e) {
            throw new UnauthorizedException("Unknown Signature");
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException("Token Invalid");
        }
    }
}
