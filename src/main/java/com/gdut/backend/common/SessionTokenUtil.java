package com.gdut.backend.common;

import com.gdut.backend.po.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class SessionTokenUtil {


    //token的到期时间设定为24小时
    public static final long EXPIRATION_TIME= 1000 * 60 * 60 * 24;
    //用uuid算法生成随机私钥
    private static final String SECRET_KEY= UUID.randomUUID().toString();
    /**
     * 生成Session令牌
     * @param user 用户
     * @return Session令牌
     */
    public static String generateSessionToken(User user) {
        Date now = new Date();

        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("username",user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(Keys.hmacShaKeyFor( SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }


    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(jwt)
                .getBody();
    }


}

