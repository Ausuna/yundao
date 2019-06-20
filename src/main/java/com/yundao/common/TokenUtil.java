package com.yundao.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {

    public String getToken(String userName, String password) {
        String token="";
        Date iat = new Date();//签发时间
        Date exp = new Date(iat.getTime() + 1000*60*15);//过期时间
        token= JWT.create().withAudience(userName).withIssuedAt(iat).withExpiresAt(exp)
                .sign(Algorithm.HMAC256(password));
        return token;
    }


}
