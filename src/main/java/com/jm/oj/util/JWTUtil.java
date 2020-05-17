package com.jm.oj.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
    // 过期时间设置为24小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 生成jwt token
     * @param email 唯一标识符，用学生或老师的邮箱
     * @param pwd 用来签名的secret，用户的密码
     * @return jwt token
     */
    public static String create(String email, String pwd) {
        Date expireTime = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(pwd);

        return JWT.create()
                .withClaim("email", email)
                .withExpiresAt(expireTime)
                .sign(algorithm);
    }

    /**
     * 校验token有效性
     * @param token
     * @param email
     * @param pwd
     * @return
     */
    public static boolean verify(String token, String email, String pwd) {
        Algorithm algorithm = Algorithm.HMAC256(pwd);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("email", email)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }

    /**
     * 从token中获得UID
     * @param token
     * @return
     */
    public static String getUID(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("email").asString();
    }
}
