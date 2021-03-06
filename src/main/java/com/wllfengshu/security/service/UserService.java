package com.wllfengshu.security.service;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.User;

import java.util.Map;

/**
 * @author wllfengshu
 */
public interface UserService {

    /**
     * 插入
     *
     * @param entity
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> insert(User entity, String sessionId)throws CustomException;

    /**
     * 删除
     *
     * @param id
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> delete(Integer id, String sessionId)throws CustomException;

    /**
     * 更新
     *
     * @param entity
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> update(User entity, String sessionId)throws CustomException;

    /**
     * 按ID查询
     *
     * @param needRole
     * @param needRoleAndPermission
     * @param id
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> select(Boolean needRole,Boolean needRoleAndPermission,Integer id, String sessionId)throws CustomException;

    /**
     * 查询所有
     *
     * @param needRole
     * @param needRoleAndPermission
     * @param pageNo
     * @param pageSize
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> selectAll(Boolean needRole,Boolean needRoleAndPermission,Integer pageNo,Integer pageSize,String sessionId)throws CustomException;
}
