package br.ufrn.ect.rastreador.app.security;

import static br.ufrn.ect.rastreador.app.security.SecurityConstants.EXPIRATION_TIME;
import static br.ufrn.ect.rastreador.app.security.SecurityConstants.SECRET;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {
    public static String getToken(String login) {
        String token = JWT.create()
                .withSubject(login)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        return token;
    }
}