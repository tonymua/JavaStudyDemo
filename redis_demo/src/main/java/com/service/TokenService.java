package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:
 * @date: created in 22:16 2021/3/25
 * @version:
 */
public interface TokenService {
    /**
     * 创建token
     * @return
     */
    String createToken();
    /**
     * 检验token
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request) throws Exception;
}