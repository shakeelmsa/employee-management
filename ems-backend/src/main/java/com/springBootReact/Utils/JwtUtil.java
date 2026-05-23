package com.springBootReact.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private static final long EXPIRATION =
            1000 * 60 * 60;

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(
            String email){

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    public static String extractEmail(
            String token){

        return Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
