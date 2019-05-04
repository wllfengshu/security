package com.wllfengshu.security.service;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Role;

import java.util.Map;

/**
 * @author wllfengshu
 */
public interface RoleService {

    /**
     * 插入
     *
     * @param entity
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> insert(Role entity, String sessionId)throws CustomException;

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
    Map<String, Object> update(Role entity, String sessionId)throws CustomException;

    /**
     * 按ID查询
     *
     * @param needPermission
     * @param id
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> select(Boolean needPermission,Integer id, String sessionId)throws CustomException;

    /**
     * 查询所有
     *
     * @param needPermission
     * @param pageNo
     * @param pageSize
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> selectAll(Boolean needPermission,Integer pageNo,Integer pageSize,String sessionId)throws CustomException;
}
