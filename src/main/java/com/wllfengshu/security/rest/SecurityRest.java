package com.wllfengshu.security.rest;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.vo.LoginVo;
import com.wllfengshu.security.service.SecurityService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Api(tags = "安全控制")
@RestController
@RequestMapping("/")
public class SecurityRest {

    @Autowired
    private SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "登陆", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(
            @ApiParam(value = "用户名和密码") @RequestBody LoginVo loginVo,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("login loginVo:{}",loginVo);
        return securityService.login(loginVo);
    }

    @ApiOperation(value = "登出", httpMethod = "GET")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Map<String, Object> logout(
            @ApiParam(value = "SessionId") @RequestHeader("sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException{
        logger.info("logout sessionId:{}",sessionId);
        return securityService.logout(sessionId);
    }

    @ApiOperation(value = "心跳", httpMethod = "GET")
    @RequestMapping(value = "/touch", method = RequestMethod.GET)
    public Map<String, Object> touch(
            @ApiParam(value = "SessionId") @RequestHeader("sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException{
        logger.info("touch sessionId:{}",sessionId);
        return securityService.touch(sessionId);
    }
}
