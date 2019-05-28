package com.wllfengshu.security.rest;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.vo.LoginVO;
import com.wllfengshu.security.service.SecurityService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Slf4j
@Api(tags = "安全控制")
@RestController
@RequestMapping("/")
public class SecurityRest {

    @Autowired
    private SecurityService securityService;

    @ApiOperation(value = "登陆", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(
            @ApiParam(value = "用户名和密码",required = true) @RequestBody LoginVO loginVo,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        log.info("login loginVo:{}",loginVo);
        return securityService.login(loginVo,request);
    }

    @ApiOperation(value = "登出", httpMethod = "GET")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Map<String, Object> logout(
            @ApiParam(value = "SessionId" ,required = true) @RequestHeader("sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException{
        log.info("logout sessionId:{}",sessionId);
        return securityService.logout(sessionId);
    }

    @ApiOperation(value = "心跳", httpMethod = "GET")
    @RequestMapping(value = "/touch", method = RequestMethod.GET)
    public Map<String, Object> touch(
            @ApiParam(value = "SessionId" ,required = true) @RequestHeader("sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException{
        log.info("touch sessionId:{}",sessionId);
        return securityService.touch(sessionId);
    }

    @ApiOperation(value = "获取当前用户信息", httpMethod = "GET")
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public Map<String, Object> getCurrentBySession(
            @ApiParam(value = "SessionId" ,required = true) @RequestHeader("sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException{
        log.info("getCurrentBySession sessionId:{}",sessionId);
        return securityService.getCurrentBySession(sessionId);
    }
}
