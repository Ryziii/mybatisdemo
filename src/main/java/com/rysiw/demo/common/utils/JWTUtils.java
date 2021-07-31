package com.rysiw.demo.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Rysiw
 * @date 2021/7/31 01:31
 */
public class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    private static final String SECRET_KEY = "demoKey";

    public static String generatorToken( String payload){
        return Jwts.builder()
                .setSubject(payload)
                .setExpiration(new Date(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String parseToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    public static boolean isTokenValid(String jwt){
        try{
            parseToken(jwt);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            return false;
        }
        return true;
    }
}
