package com.wllfengshu.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wllfengshu.security.dao.RoleDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> insert(Role entity, String sessionId)throws CustomException {
        logger.info("insert entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        roleDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId)throws CustomException {
        logger.info("delete id:{}",id);
        Map<String, Object> result = new HashMap<>();
        roleDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(Role entity, String sessionId)throws CustomException {
        logger.info("update entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        roleDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Boolean needPermission,Integer id, String sessionId)throws CustomException {
        logger.info("select needPermission:{},id:{}",needPermission,id);
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
    public Map<String, Object> selectAll(Boolean needPermission,Integer pageNo,Integer pageSize,String sessionId)throws CustomException {
        logger.info("selectAll needPermission:{},pageNo:{},pageSize:{},sessionId",needPermission,pageNo,pageSize,sessionId);
        Map<String, Object> result = new HashMap<>();
        PageInfo<Role> pageInfo = null;
        if (needPermission){
            pageInfo = PageHelper.startPage(pageNo, pageSize)
                    .doSelectPageInfo(() -> this.roleDao.selectRoleAndPermission(new HashMap<>()));
        }else {
            pageInfo = PageHelper.startPage(pageNo, pageSize)
                    .doSelectPageInfo(() -> this.roleDao.selectAll());
        }
        result.put("data",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }
}
