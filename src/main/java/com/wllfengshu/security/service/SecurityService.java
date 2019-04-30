package com.wllfengshu.security.service;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.vo.LoginVo;

import java.util.Map;

/**
 * @author wllfengshu
 */
public interface SecurityService {

    /**
     * 登陆
     * @param loginVo
     * @return
     * @throws CustomException
     */
    Map<String, Object> login(LoginVo loginVo) throws CustomException;

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

}
