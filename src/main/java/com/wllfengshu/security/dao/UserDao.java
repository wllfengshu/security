package com.wllfengshu.security.dao;

import com.wllfengshu.security.model.User;
import com.wllfengshu.security.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Repository
public interface UserDao extends MyMapper<User> {

    /**
     * 查询用户和对应的角色
     * @param paramsMap
     * @return
     */
    List<User> selectUserAndRole(Map<String, Object> paramsMap);

    /**
     * 查询用户和对应的角色和权限
     * @param paramsMap
     * @return
     */
    List<User> selectUserAndRoleAndPermission(Map<String, Object> paramsMap);
}
