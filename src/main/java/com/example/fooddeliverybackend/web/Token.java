package com.example.fooddeliverybackend.web;
import com.example.fooddeliverybackend.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class Token {
    long time = 3_000_000;
    Date expirationDate = new Date(System.currentTimeMillis()+time);
    String password = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public String getToken(String username, Role role){
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .claim("ROLES", role.getRoleName())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean tokenCheck(String token){
        Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token);
        return true;
    }

    public String userCheck(String username){
        return Jwts
                .parser()
                .setSigningKey(password)
                .parseClaimsJws(username)
                .getBody()
                .getSubject();

    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(password);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + time)).signWith(SignatureAlgorithm.HS256, password)
                .compact();
    }

}
