package com.wllfengshu.security.service.impl;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.User;
import com.wllfengshu.security.model.vo.LoginVo;
import com.wllfengshu.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Map<String, Object> login(LoginVo loginVo) throws CustomException {
        log.info("login loginVo:{}",loginVo);
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(loginVo.getUsername())){
            throw new CustomException("用户名不能为空", CustomException.ExceptionName.NeedUsername);
        }
        if (StringUtils.isEmpty(loginVo.getPassword())){
            throw new CustomException("密码不能为空", CustomException.ExceptionName.NeedPassword);
        }
        try{
            SecurityUtils.getSubject().login(new UsernamePasswordToken(loginVo.getUsername(),loginVo.getPassword()));
        } catch (UnknownAccountException uae) {
            throw new CustomException("用户名不存在", CustomException.ExceptionName.UnknownAccountException);
        } catch (IncorrectCredentialsException ice) {
            throw new CustomException("用户名或密码错误", CustomException.ExceptionName.IncorrectCredentialsException);
        } catch (LockedAccountException lae) {
            throw new CustomException("账户被锁定", CustomException.ExceptionName.LockedAccountException);
        } catch (ExcessiveAttemptsException eae) {
            throw new CustomException("过度尝试异常", CustomException.ExceptionName.ExcessiveAttemptsException);
        } catch (AuthenticationException ae) {
            throw new CustomException("身份验证异常", CustomException.ExceptionName.AuthenticationException);
        } catch (Exception e){
            log.error("login error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        result.put("sessionId",SecurityUtils.getSubject().getSession().getId().toString());
        return result;
    }

    @Override
    public Map<String, Object> logout(String sessionId) throws CustomException {
        log.info("logout sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        if (SecurityUtils.getSubject().getSession(false) == null){
            throw new CustomException("未登陆，无法操作", CustomException.ExceptionName.NotLoginError);
        }
        if (!((String.valueOf(SecurityUtils.getSubject().getSession().getId())).equals(sessionId))){
            throw new CustomException("已登陆，但sessionId不匹配", CustomException.ExceptionName.LoginButMismatchSessionId);
        }
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e){
            log.error("logout error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        result.put("logout","success");
        return result;
    }

    @Override
    public Map<String, Object> touch(String sessionId) throws CustomException {
        log.info("touch sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        if (SecurityUtils.getSubject().getSession(false) == null){
            throw new CustomException("未登陆，无法操作", CustomException.ExceptionName.NotLoginError);
        }
        if (!((String.valueOf(SecurityUtils.getSubject().getSession().getId())).equals(sessionId))){
            throw new CustomException("已登陆，但sessionId不匹配", CustomException.ExceptionName.LoginButMismatchSessionId);
        }
        try {
            SecurityUtils.getSubject().getSession(false).touch();
        } catch (Exception e){
            log.error("touch error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        result.put("touch","success");
        return result;
    }

    @Override
    public Map<String, Object> getCurrentBySession(String sessionId) throws CustomException {
        log.info("getCurrentBySession sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        if (SecurityUtils.getSubject().getSession(false) == null){
            throw new CustomException("未登陆，无法操作", CustomException.ExceptionName.NotLoginError);
        }
        if (!((String.valueOf(SecurityUtils.getSubject().getSession().getId())).equals(sessionId))){
            throw new CustomException("已登陆，但sessionId不匹配", CustomException.ExceptionName.LoginButMismatchSessionId);
        }
        User user = null;
        try {
            user = (User)SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e){
            log.error("getCurrentBySession error",e);
            throw new CustomException("操作失败,错误未知，请联系系统管理员", CustomException.ExceptionName.OperationFailed);
        }
        result.put("data",user);
        return result;
    }
}
