package com.wllfengshu.security.service.impl;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.vo.LoginVo;
import com.wllfengshu.security.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> login(LoginVo loginVo) throws CustomException {
        logger.info("login loginVo:{}",loginVo);
        Map<String, Object> result = new HashMap<>();
        try{
            SecurityUtils.getSubject().login(new UsernamePasswordToken(loginVo.getUsername(),loginVo.getPassword()));
        }catch (UnknownAccountException uae) {
            throw new CustomException("用户名不存在", CustomException.ExceptionName.UnknownAccountException);
        } catch (IncorrectCredentialsException ice) {
            throw new CustomException("用户名或密码错误", CustomException.ExceptionName.IncorrectCredentialsException);
        } catch (Exception e){
            logger.error("login error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        result.put("sessionId",SecurityUtils.getSubject().getSession().getId().toString());
        return result;
    }

    @Override
    public Map<String, Object> logout(String sessionId) throws CustomException {
        logger.info("logout sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e){
            logger.error("login error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        result.put("operate","success");
        return result;
    }

    @Override
    public Map<String, Object> touch(String sessionId) throws CustomException {
        logger.info("touch sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        if (SecurityUtils.getSubject().getSession(false) == null){
            throw new CustomException("未登陆，无法操作", CustomException.ExceptionName.NotLoginError);
        }
        try {
            SecurityUtils.getSubject().getSession(false).touch();
        } catch (Exception e){
            logger.error("login error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        if (!((String.valueOf(SecurityUtils.getSubject().getSession().getId())).equals(sessionId))){
            throw new CustomException("已登陆，但sessionId不匹配", CustomException.ExceptionName.LoginedButMismatchSessionId);
        }
        result.put("touch","success");
        return result;
    }
}
