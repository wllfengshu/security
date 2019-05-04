package com.wllfengshu.security.service;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Permission;

import java.util.Map;

/**
 * @author wllfengshu
 */
public interface PermissionService {

    /**
     * 插入
     *
     * @param entity
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> insert(Permission entity, String sessionId)throws CustomException;

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
    Map<String, Object> update(Permission entity, String sessionId)throws CustomException;

    /**
     * 按ID查询
     *
     * @param id
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> select(Integer id, String sessionId)throws CustomException;

    /**
     * 查询所有
     *
     * @param pageNo
     * @param pageSize
     * @param sessionId
     * @return
     * @throws CustomException
     */
    Map<String, Object> selectAll(Integer pageNo,Integer pageSize,String sessionId)throws CustomException;
}
