package com.yundao.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yundao.bean.UserInfo;
import com.yundao.bean.UserLogin;
import com.yundao.bean.UserSession;
import com.yundao.dao.UserInfoDao;
import com.yundao.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import java.util.Date;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 执行认证
        if (token == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NOLOGIN, "请先进行登录");
        }
        // 获取 token 中的 帐号
        String account;
        try {
            account = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }

        UserLogin userLogin = userInfoDao.userLogin(account);

        if (userLogin == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NOLOGIN, "用户不存在，请重新登录");
        }
        Date expiresAt;
        try {
            expiresAt = JWT.decode(token).getExpiresAt();
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }

        String newToken;
        if(expiresAt.compareTo(new Date()) >= 0) {
            //验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userLogin.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new UnicomRuntimeException(UnicomResponseEnums.NOLOGIN, "验证失败，请重新登录");
            }

            UserInfo user = userInfoDao.getUserByAccount(account);
            UserSession.set("currentUser",user);
            //更新token
            newToken = tokenUtil.getToken(userLogin.getAccount(), userLogin.getPassword());
            response.setHeader("token", newToken);
            return true;
        }else {
            throw new UnicomRuntimeException(UnicomResponseEnums.NOLOGIN, "登录信息已过期，请重新登录");
        }
    }
}
