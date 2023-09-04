package com.hsh.myapp.musicolumn.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hsh.myapp.musicolumn.auth.AuthUser;
import com.hsh.myapp.musicolumn.post.entity.Post;
import com.hsh.myapp.musicolumn.post.entity.PostComment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    public String secret = "your-secret";
    public final long TOKEN_TIME = 1000 * 60 * 60 * 24 * 7;

    public String createToken(long joinNo, String id, String nickName, LocalDate birthdate, String email,
                              String userImage){
        Date now = new Date();
        Date exp = new Date(now.getTime() + TOKEN_TIME);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withSubject(String.valueOf(joinNo))
                .withClaim("id",id)
                .withClaim("nickName",nickName)
                .withClaim("birthdate",birthdate.toString())
                .withClaim("email",email)
                .withClaim("image",userImage)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);

        }
    public AuthUser validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try{
            DecodedJWT decodedJWT = verifier.verify(token);
            Long userNumber = Long.valueOf(decodedJWT.getSubject());
            String id = decodedJWT.getClaim("id").asString();
            String nickName = decodedJWT.getClaim("nickName").asString();
            LocalDate birthdate = LocalDate.parse(decodedJWT.getClaim("birthdate").asString());
            String email =  decodedJWT.getClaim("email").asString();
            String image = decodedJWT.getClaim("image").asString();

            return AuthUser.builder()
                    .userNumber(userNumber)
                    .id(id)
                    .nickName(nickName)
                    .birthdate(birthdate)
                    .email(email)
                    .image(image)
                    .build();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
