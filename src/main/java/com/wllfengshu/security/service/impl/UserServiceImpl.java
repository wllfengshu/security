package com.wllfengshu.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wllfengshu.security.dao.UserDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.User;
import com.wllfengshu.security.service.UserService;
import com.wllfengshu.security.component.AuthComponent;
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
public class UserServiceImpl implements UserService {

    @Autowired
    AuthComponent authComponent;
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> insert(User entity, String sessionId)throws CustomException {
        log.info("insert entity:{}",entity);
        authComponent.requiresPermission(sessionId,"insertUser");
        Map<String, Object> result = new HashMap<>();
        userDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId)throws CustomException {
        log.info("delete id:{}",id);
        authComponent.requiresPermission(sessionId,"deleteUser");
        Map<String, Object> result = new HashMap<>();
        userDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(User entity, String sessionId)throws CustomException {
        log.info("update entity:{}",entity);
        authComponent.requiresPermission(sessionId,"updateUser");
        Map<String, Object> result = new HashMap<>();
        userDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Boolean needRole,Boolean needRoleAndPermission,Integer id, String sessionId)throws CustomException {
        log.info("select needRole:{},needRoleAndPermission:{},id:{}",needRole,needRoleAndPermission,id);
        authComponent.requiresPermission(sessionId,"selectUser");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> condition = new HashMap<>();
        condition.put("id",id);
        if (needRoleAndPermission){
            List<User> users = userDao.selectUserAndRoleAndPermission(condition);
            result.put("data",(users==null || users.size()<=0)?null:users.get(0));
        }else if(needRole){
            List<User> users = userDao.selectUserAndRole(condition);
            result.put("data",(users==null || users.size()<=0)?null:users.get(0));
        }else {
            result.put("data",userDao.selectByPrimaryKey(id));
        }
        return result;
    }

    @Override
    public Map<String, Object> selectAll(Boolean needRole,Boolean needRoleAndPermission,Integer pageNo,Integer pageSize, String sessionId)throws CustomException {
        log.info("selectAll needRole:{},needRoleAndPermission:{},pageNo:{},pageSize:{}",needRole,needRoleAndPermission,pageNo,pageSize);
        authComponent.requiresPermission(sessionId,"selectAllUser");
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<User> pageInfo = null;
        if (needRoleAndPermission){
            pageInfo = new PageInfo<>(userDao.selectUserAndRoleAndPermission(new HashMap<>()));
        }else if(needRole){
            pageInfo = new PageInfo<>(userDao.selectUserAndRole(new HashMap<>()));
        }else {
            pageInfo = new PageInfo<>(userDao.selectAll());
        }
        result.put("data",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }
}
