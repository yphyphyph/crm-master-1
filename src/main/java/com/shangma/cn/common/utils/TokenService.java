package com.shangma.cn.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shangma.cn.common.cache.CacheService;
import com.shangma.cn.common.cache.JsonUtils;
import com.shangma.cn.common.cache.KeyUtils;
import com.shangma.cn.domin.entity.LoginAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 14:45
 * 文件说明：
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TokenService {


    private static final String secret = "gjiofugouiarhaouiyry";


    private final CacheService cacheService;


    public String setLoginAdminAndCreateToken(LoginAdmin loginAdmin) {
        String uuid = UUID.randomUUID().toString();
        loginAdmin.setUuid(uuid);
        //缓存用户信息
        cacheService.setCache(KeyUtils.LOGIN_ADMIN + uuid, JsonUtils.obj2Str(loginAdmin));
        return createToken(uuid);
    }


    /**
     * 获取登录人的信息
     *
     * @return
     */

    public LoginAdmin getLoginAdmin() {
        String uuidFromToken = getUUIDFromToken(ServletUtils.getRequest());
        String cache = cacheService.getCache(KeyUtils.LOGIN_ADMIN + uuidFromToken);
        LoginAdmin loginAdmin = JsonUtils.str2Obj(cache, LoginAdmin.class);
        return loginAdmin;
    }


    public void setLoginAdmin(LoginAdmin loginAdmin) {
        cacheService.setCache(KeyUtils.LOGIN_ADMIN + loginAdmin.getUuid(), JsonUtils.obj2Str(loginAdmin));
    }


    /**
     * 获得登录的Id值
     *
     * @return
     */
    public Long getAdminId() {
        return getLoginAdmin().getAdmin().getId();
    }

    /**
     * 是否是管理员
     */
    public boolean isAdmin() {
        return getLoginAdmin().getAdmin().getIsAdmin();
    }

    /**
     * 生成Token的方法
     *
     * @param uuid
     * @return authxx : Bearer + token
     */
    public String createToken(String uuid) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("尚马教育")
                    .withClaim("uuid", uuid)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            log.error("token生成失败");
        }
        return "";
    }

    public DecodedJWT getDecodedJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("尚马教育")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            log.error("token解析失败");
        }
        return null;
    }


    public String getUUIDFromToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String s = authorization.split(" ")[1];
        DecodedJWT decodedJWT = getDecodedJWT(s);
        String uuid = decodedJWT.getClaim("uuid").asString();
        return uuid;
    }


}
