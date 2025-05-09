package com.example.be_swp.Service;



import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {


    @Autowired
    UsersRepository usersRepository;

    private final String SECRET_KEY = "HT4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";

    private SecretKey getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // tao token
    public String generateToken(Users users){
        String token = Jwts.builder()
                .subject(users.getId()+"")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *24))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    //verify token
    public Optional<Users> getUserByToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String idString = claims.getSubject();
        int id = Integer.parseInt(idString);

        Optional<Users> users =  usersRepository.findById(id);
        return users;
    }
}

