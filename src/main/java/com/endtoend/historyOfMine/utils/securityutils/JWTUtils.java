package com.endtoend.historyOfMine.utils.securityutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
public class JWTUtils {
    private static final String SECRET = "6E327234753778214125442A472D4B6150645367566B59703373367638792F42";
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    private static final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build();
    private static final long minutesFromNowInMils = 300 * 60 * 1000;


    public static String generateToken(UserDetails userDetails){
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + minutesFromNowInMils))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername())) && isTokenNotExpired(token);
    }

    public static boolean isTokenNotExpired(String token) {
        return getExpirationDate(token).after(new Date());
    }

    public static Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }


    public static <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public static String getUsername(String token){
        return getClaim(token, Claims::getSubject);
    }

    public static Claims getClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }





}
