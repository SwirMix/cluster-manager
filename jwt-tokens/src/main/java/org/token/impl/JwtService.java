package org.token.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.token.TokenObject;
import org.token.TokenService;

import java.util.Date;

@Component
public class JwtService implements TokenService<TokenObject> {

    @Value("${app.jwtSecret}")
    private String secret;

    public JwtService(String secret) {
        this.secret = secret;
    }

    public JwtService() {
    }

    @Override
    public TokenObject decryptToken(String token) {
        JWTVerifier verified = JWT.require(Algorithm.HMAC512(this.secret)).build();
        DecodedJWT jwt = verified.verify(token);

        String id = jwt.getClaim("userLogin").asString();
        Date date = jwt.getClaim("date").asDate();
        String lol = jwt.getClaim("msg").asString();

        return new TokenObject(id, lol, date);
    }

    @Override
    public String createToken(TokenObject data) {
        String signed = JWT.create()
                .withClaim("userLogin",data.getLogin())
                .withClaim("date", data.getCreateDate())
                .withClaim("msg", data.getSupport())
                .sign(Algorithm.HMAC512(this.secret));
        return signed;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
