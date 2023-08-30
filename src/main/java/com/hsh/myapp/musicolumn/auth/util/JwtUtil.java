package com.hsh.myapp.musicolumn.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hsh.myapp.musicolumn.auth.AuthUser;

import java.util.Date;

public class JwtUtil {
    public String secret = "your-secret";
    public final long TOKEN_TIME = 1000 * 60 * 60 * 24 * 7;

    public String createToken(long joinNo, String id, String nickName){
        Date now = new Date();
        Date exp = new Date(now.getTime() + TOKEN_TIME);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withSubject(String.valueOf(joinNo))
                .withClaim("id",id)
                .withClaim("nickName",nickName)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);

        }
    public AuthUser validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try{
            DecodedJWT decodedJWT = verifier.verify(token);
            Long joinNo = Long.valueOf(decodedJWT.getSubject());
            String id = decodedJWT.getClaim("id").asString();
            String nickName = decodedJWT.getClaim("nickName").asString();

            return AuthUser.builder()
                    .joinNo(joinNo)
                    .id(id)
                    .nickName(nickName)
                    .build();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
