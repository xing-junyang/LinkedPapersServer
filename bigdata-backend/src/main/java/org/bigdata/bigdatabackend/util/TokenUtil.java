package org.bigdata.bigdatabackend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.bigdata.bigdatabackend.po.User;
import org.bigdata.bigdatabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TokenUtil {
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    @Autowired
    UserRepository userRepository;

    public String getToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withAudience(String.valueOf(user.getUserId()))
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    public boolean verifyToken(String token) {
        try {
            Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUser(String token) {
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}