package com.wllfengshu.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wllfengshu.security.dao.PermissionDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.service.PermissionService;
import com.wllfengshu.security.component.AuthComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    AuthComponent authComponent;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Map<String, Object> insert(Permission entity, String sessionId)throws CustomException {
        log.info("insert entity:{}",entity);
        authComponent.requiresPermission(sessionId,"insertPermission");
        Map<String, Object> result = new HashMap<>();
        permissionDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId)throws CustomException {
        log.info("delete id:{}",id);
        authComponent.requiresPermission(sessionId,"deletePermission");
        Map<String, Object> result = new HashMap<>();
        permissionDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(Permission entity, String sessionId)throws CustomException {
        log.info("update entity:{}",entity);
        authComponent.requiresPermission(sessionId,"updatePermission");
        Map<String, Object> result = new HashMap<>();
        permissionDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Integer id, String sessionId)throws CustomException {
        log.info("select id:{}",id);
        authComponent.requiresPermission(sessionId,"selectPermission");
        Map<String, Object> result = new HashMap<>();
        result.put("data",permissionDao.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Map<String, Object> selectAll(Integer pageNo,Integer pageSize, String sessionId)throws CustomException {
        log.info("selectAll pageNo:{},pageSize:{}",pageNo,pageSize);
        authComponent.requiresPermission(sessionId,"selectAllPermission");
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissionDao.selectAll());
        result.put("data",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }
}
