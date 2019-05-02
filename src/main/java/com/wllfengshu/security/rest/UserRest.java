package com.wllfengshu.security.rest;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.User;
import com.wllfengshu.security.service.UserService;
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
@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserRest {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "插入", httpMethod = "POST")
    @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Map<String, Object> insert(
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody User entity)throws CustomException {
        logger.info("insert entity:{}",entity);
        return userService.insert(entity,sessionId);
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(
            @PathVariable("id") Integer id,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("delete id:{}",id);
        return userService.delete(id,sessionId);
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Map<String, Object> update(
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody User entity)throws CustomException {
        logger.info("update entity:{}",entity);
        return userService.update(entity,sessionId);
    }

    @ApiOperation(value = "按ID查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Map<String, Object> select(
            @PathVariable("id") Integer id,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("select id:{}",id);
        return userService.select(id,sessionId);
    }

    @ApiOperation(value = "查询所有", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "needRole", value = "是否需要角色（默认false）", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "needRoleAndPermission", value = "是否需要角色和权限（默认false）", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页数(从0开始，默认0)", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数量(默认10)", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Map<String, Object> selectsAll(
            @RequestParam(value = "needRole",required = false,defaultValue = "false") Boolean needRole,
            @RequestParam(value = "needRoleAndPermission",required = false,defaultValue = "false") Boolean needRoleAndPermission,
            @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("selectsAll needRole:{},needRoleAndPermission:{},pageNo:{},pageSize:{},sessionId",needRole,needRoleAndPermission,pageNo,pageSize,sessionId);
        return userService.selectsAll(needRole,needRoleAndPermission,pageNo,pageSize,sessionId);
    }
}
