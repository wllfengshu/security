package com.wllfengshu.security.service.impl;

import com.wllfengshu.security.dao.UserDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.User;
import com.wllfengshu.security.model.vo.LoginVo;
import com.wllfengshu.security.service.SecurityService;
import com.wllfengshu.security.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wllfengshu
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    AuthUtil authUtil;
    @Autowired
    private UserDao userDao;

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
        Map<String, Object> condition = new HashMap<>();
        condition.put("username",loginVo.getUsername());
        //由于用户名是唯一的，所以理论上只能查出一条记录
        List<User> users = userDao.selectUserAndRoleAndPermission(condition);
        log.debug("doGetAuthenticationInfo user:{}",users);
        if (users == null || users.size() <= 0){
            log.error("用户不存在");
            throw new CustomException("用户不存在", CustomException.ExceptionName.UnknownAccountException);
        }
        if (!((String.valueOf(loginVo.getPassword())).equals(users.get(0).getPassword()))){
            log.error("用户名或密码错误");
            throw new CustomException("用户名或密码错误", CustomException.ExceptionName.IncorrectCredentialsException);
        }
        String sessionId = UUID.randomUUID().toString();
        authUtil.put(sessionId,users.get(0));
        result.put("sessionId",sessionId);
        return result;
    }

    @Override
    public Map<String, Object> logout(String sessionId) throws CustomException {
        log.info("logout sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        authUtil.delete(sessionId);
        result.put("logout","success");
        return result;
    }

    @Override
    public Map<String, Object> touch(String sessionId) throws CustomException {
        log.info("touch sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        result.put("touch",authUtil.hasKey(sessionId));
        return result;
    }

    @Override
    public Map<String, Object> getCurrentBySession(String sessionId) throws CustomException {
        log.info("getCurrentBySession sessionId:{}",sessionId);
        Map<String, Object> result = new HashMap<>();
        result.put("data",authUtil.get(sessionId));
        return result;
    }
}
