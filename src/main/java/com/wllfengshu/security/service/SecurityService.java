package com.wllfengshu.security.service;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.vo.LoginVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wllfengshu
 */
public interface SecurityService {

    /**
     * 登陆
     * @param loginVo
     * @param request
     * @return
     * @throws CustomException
     */
    Map<String, Object> login(LoginVO loginVo, HttpServletRequest request) throws CustomException;

    /**
     * 登出
     * @param sessionId
     * @throws CustomException
     * @return
     */
    Map<String, Object> logout(String sessionId) throws CustomException;

    /**
     * 心跳
     * @param sessionId
     * @throws CustomException
     * @return
     */
    Map<String, Object> touch(String sessionId) throws CustomException;

    /**
     * 获取当前用户信息
     * @param sessionId
     * @throws CustomException
     * @return
     */
    Map<String, Object> getCurrentBySession(String sessionId) throws CustomException;

}
