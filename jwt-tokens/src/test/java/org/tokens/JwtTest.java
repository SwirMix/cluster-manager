package org.tokens;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Assert;
import org.junit.Test;
import org.token.TokenObject;
import org.token.impl.JwtService;

import java.util.UUID;

public class JwtTest {

    private String secret = "newbie";
    private JwtService service;


    @Test
    public void serviceTest(){
        service = new JwtService(secret);
        TokenObject first = new TokenObject(UUID.randomUUID().toString(), "d-people");
        String jwt = service.createToken(first);
        System.out.println(jwt);
        System.out.println(first.getSupport());
        TokenObject result = service.decryptToken(jwt);
        System.out.println(result.getSupport());
        Assert.assertEquals(first.getLogin(), result.getLogin());
    }

    @Test
    public void test(){
        String signed = JWT.create()
                .withClaim("id", UUID.randomUUID().toString())
                .sign(Algorithm.HMAC512("secret"));
        JWTVerifier verified = JWT.require(Algorithm.HMAC512("secret")).build();
        DecodedJWT jwt = verified.verify(signed);
        String id = jwt.getClaim("id").asString();
        System.out.println("final");
    }
}
