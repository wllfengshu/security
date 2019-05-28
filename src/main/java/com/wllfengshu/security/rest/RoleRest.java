package com.wllfengshu.security.rest;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.service.RoleService;
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
@Api(tags = "角色管理")
@RestController
@RequestMapping("/roles")
public class RoleRest {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "插入", httpMethod = "POST")
    @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Map<String, Object> insert(
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Role entity)throws CustomException {
        log.info("insert entity:{},sessionId:{}",entity,sessionId);
        return roleService.insert(entity,sessionId);
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(
            @PathVariable("id") Integer id,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        log.info("delete id:{},sessionId:{}",id,sessionId);
        return roleService.delete(id,sessionId);
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public Map<String, Object> update(
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Role entity)throws CustomException {
        log.info("update entity:{},sessionId:{}",entity,sessionId);
        return roleService.update(entity,sessionId);
    }

    @ApiOperation(value = "按ID查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "needPermission", value = "是否需要权限（默认false）", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Map<String, Object> select(
            @RequestParam(value = "needPermission",required = false,defaultValue = "false") Boolean needPermission,
            @PathVariable("id") Integer id,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        log.info("select needPermission:{},id:{},sessionId:{}",needPermission,id,sessionId);
        return roleService.select(needPermission,id,sessionId);
    }

    @ApiOperation(value = "查询所有", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "needPermission", value = "是否需要权限（默认false）", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页数(从0开始，默认0)", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数量(默认10)", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Map<String, Object> selectAll(
            @RequestParam(value = "needPermission",required = false,defaultValue = "false") Boolean needPermission,
            @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        log.info("selectAll needPermission:{},pageNo:{},pageSize:{},sessionId:{}",needPermission,pageNo,pageSize,sessionId);
        return roleService.selectAll(needPermission,pageNo,pageSize,sessionId);
    }
}
