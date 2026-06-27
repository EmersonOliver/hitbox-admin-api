package br.com.hitbox.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${secret.key.api}")
    private String secret;


    public DecodedJWT decode(String token) {
      return  JWT.require(
                        Algorithm.HMAC256(secret)
                )
                .withIssuer("erp-hitbox")
                .build()
                .verify(token);
    }
}
