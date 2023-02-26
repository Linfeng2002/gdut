package com.gdut.backend.common;

import com.gdut.backend.po.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class token {
    //token的到期时间设定为24小时
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //用uuid算法生成随机私钥
    private static final String APP_SECRET= UUID.randomUUID().toString();

/**
 * 根据用户id和用户名生成token
 * @param user 用户信息
 * @return  JWT生成的token
 */
    public static String getJwtToken(User user){
        String JwtToken= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        return JwtToken;
    }
    /**
     * 判断token是否存在与有效
     * @param jwtToken token字符串
     * @return 如果token有效返回true，否则返回false
     */
    public static Jws<Claims> decode(String jwtToken) {
        // 传入Key对象
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(jwtToken);
        return claimsJws;
    }


}
