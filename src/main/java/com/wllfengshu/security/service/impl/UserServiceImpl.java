package com.wllfengshu.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wllfengshu.security.dao.UserDao;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.User;
import com.wllfengshu.security.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, Object> insert(User entity, String sessionId)throws CustomException {
        logger.info("insert entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        userDao.insert(entity);
        return result;
    }

    @Override
    public Map<String, Object> delete(Integer id, String sessionId)throws CustomException {
        logger.info("delete id:{}",id);
        Map<String, Object> result = new HashMap<>();
        userDao.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public Map<String, Object> update(User entity, String sessionId)throws CustomException {
        logger.info("update entity:{}",entity);
        Map<String, Object> result = new HashMap<>();
        userDao.updateByPrimaryKey(entity);
        return result;
    }

    @Override
    public Map<String, Object> select(Integer id, String sessionId)throws CustomException {
        logger.info("select id:{}",id);
        Map<String, Object> result = new HashMap<>();
        result.put("data",userDao.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Map<String, Object> selectsAll(Integer pageNo,Integer pageSize,String sessionId)throws CustomException {
        logger.info("selectsAll pageNo:{},pageSize:{},sessionId",pageNo,pageSize,sessionId);
        Map<String, Object> result = new HashMap<>();
        PageInfo<User> pageInfo = PageHelper.startPage(pageNo, pageSize)
                                            .setOrderBy("id desc")
                                            .doSelectPageInfo(() -> this.userDao.selectAll());
        result.put("data",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }
}
