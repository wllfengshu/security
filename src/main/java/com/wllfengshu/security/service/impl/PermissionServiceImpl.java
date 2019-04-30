package com.wllfengshu.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wllfengshu.security.dao.PermissionDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> insert(Permission entity, String sessionId)throws CustomException {
        logger.info("insert entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        permissionDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId)throws CustomException {
        logger.info("delete id:{}",id);
        Map<String, Object> result = new HashMap<>();
        permissionDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(Permission entity, String sessionId)throws CustomException {
        logger.info("update entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        permissionDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Integer id, String sessionId)throws CustomException {
        logger.info("select id:{}",id);
        Map<String, Object> result = new HashMap<>();

        result.put("data",permissionDao.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Map<String, Object> selectsAll(Integer pageNo,Integer pageSize,String sessionId)throws CustomException {
        logger.info("selectsAll pageNo:{},pageSize:{},sessionId",pageNo,pageSize,sessionId);
        Map<String, Object> result = new HashMap<>();
        PageInfo<Permission> pageInfo = PageHelper.startPage(pageNo, pageSize)
                .setOrderBy("id desc")
                .doSelectPageInfo(() -> this.permissionDao.selectAll());
        result.put("data",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }
}
