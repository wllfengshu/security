package com.wllfengshu.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wllfengshu.security.dao.RoleDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.service.RoleService;
import com.wllfengshu.security.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    AuthUtil authUtil;
    @Autowired
    private RoleDao roleDao;

    @Override
    public Map<String, Object> insert(Role entity, String sessionId)throws CustomException {
        log.info("insert entity:{}",entity);
        authUtil.requiresPermission(sessionId,"insertRole");
        Map<String, Object> result = new HashMap<>();
        roleDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId)throws CustomException {
        log.info("delete id:{}",id);
        authUtil.requiresPermission(sessionId,"deleteRole");
        Map<String, Object> result = new HashMap<>();
        roleDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(Role entity, String sessionId)throws CustomException {
        log.info("update entity:{}",entity);
        authUtil.requiresPermission(sessionId,"updateRole");
        Map<String, Object> result = new HashMap<>();
        roleDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Boolean needPermission,Integer id, String sessionId)throws CustomException {
        log.info("select needPermission:{},id:{}",needPermission,id);
        authUtil.requiresPermission(sessionId,"selectRole");
        Map<String, Object> result = new HashMap<>();
        if (needPermission){
            Map<String, Object> condition = new HashMap<>();
            condition.put("id",id);
            List<Role> roles = roleDao.selectRoleAndPermission(condition);
            result.put("data",(roles==null || roles.size()<=0)?null:roles.get(0));
        }else {
            result.put("data",roleDao.selectByPrimaryKey(id));
        }
        return result;
    }

    @Override
    public Map<String, Object> selectAll(Boolean needPermission,Integer pageNo,Integer pageSize, String sessionId)throws CustomException {
        log.info("selectAll needPermission:{},pageNo:{},pageSize:{}",needPermission,pageNo,pageSize);
        authUtil.requiresPermission(sessionId,"selectAllRole");
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<Role> pageInfo = null;
        if (needPermission){
            pageInfo = new PageInfo<>(roleDao.selectRoleAndPermission(new HashMap<>()));
        }else {
            pageInfo = new PageInfo<>(roleDao.selectAll());
        }
        result.put("data",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }
}
