package com.tobeto.lms.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private String SECRET_KEY="d292adaeffb3c70433c9a9c06902eef5fa3a9f1473fe43398151ae83869a234f9d9595a825c90060cb927f9ffbc1ab001c4062f3ff7c2ae50ae14d53fa4e6fcd";
    private long EXPIRATION=600000;

    public String generateToken(String userName, Map<String,Object> extraClaims){
        return Jwts
                .builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .claims(extraClaims)
                .subject(userName)
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaimsFromToken(String token){
        SecretKey Key=(SecretKey) getSignKey();
        return Jwts
                .parser()
                .verifyWith(Key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(String token){return getClaimsFromToken(token).getExpiration().after(new Date());}

    public String extracthUserName(String token){return getClaimsFromToken(token).getSubject();}

    public String extractUserRole(String token){return getClaimsFromToken(token).get("UserRole", String.class);
    }


}
