package com.rysiw.demo.common.utils;

import com.rysiw.demo.common.constant.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author Rysiw
 * @date 2021/7/31 01:31
 */
public class JwtTokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    private static final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(apiKeySecretBytes);

    public static String generatorToken(String username, boolean isrRemember){
//        long expiration = isrRemember ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
        long expiration = SecurityConstants.EXPIRATION_FORTEST;
        Date expirationDate = new Date(new Date().getTime() + expiration * 1000);
        return Jwts.builder()
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String parseToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
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
