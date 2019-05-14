package com.wllfengshu.security.rest;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.service.PermissionService;
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
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permissions")
public class PermissionRest {

    @Autowired
    private PermissionService permissionService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "插入", httpMethod = "POST")
    @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public Map<String, Object> insert(
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Permission entity)throws CustomException {
        logger.info("insert entity:{},sessionId:{}",entity,sessionId);
        return permissionService.insert(entity,sessionId);
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(
            @PathVariable("id") Integer id,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("delete id:{},sessionId:{}",id,sessionId);
        return permissionService.delete(id,sessionId);
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/permission", method = RequestMethod.PUT)
    public Map<String, Object> update(
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Permission entity)throws CustomException {
        logger.info("update entity:{},sessionId:{}",entity,sessionId);
        return permissionService.update(entity,sessionId);
    }

    @ApiOperation(value = "按ID查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public Map<String, Object> select(
            @PathVariable("id") Integer id,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("select id:{},sessionId:{}",id,sessionId);
        return permissionService.select(id,sessionId);
    }

    @ApiOperation(value = "查询所有", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页数(从0开始，默认0)", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数量(默认10)", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sessionId", value = "SessionId", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "IllegalParam")
    })
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Map<String, Object> selectAll(
            @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "sessionId") String sessionId,
            HttpServletRequest request,
            HttpServletResponse response)throws CustomException {
        logger.info("selectAll pageNo:{},pageSize:{},sessionId:{}",pageNo,pageSize,sessionId);
        return permissionService.selectAll(pageNo,pageSize,sessionId);
    }
}
