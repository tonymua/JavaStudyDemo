package com.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.RedisService;
import com.service.TokenService;

/**
 * @author:
 * @date: created in 22:16 2021/3/25
 * @version:
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisService redisService;

    @Override
    public String createToken() {
        String uuid = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        token.append("LOGEN_PRES_").append(uuid);
        boolean result = redisService.setExp(token.toString(), token.toString(), 10000L);
        if (result){
            return token.toString();
        }
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (token==null||"".equals(token)){
            token = request.getParameter("token");
            if (token==null||"".equals(token)){
                throw new Exception("请求违法！");
            }
        }
        if (!redisService.isExist(token)) {
            throw new Exception("请求违法！");
        }
        boolean removeResult = redisService.remove(token);
        if (!removeResult){
            throw new RuntimeException("清理token失败！");
        }
        return true;
    }
}