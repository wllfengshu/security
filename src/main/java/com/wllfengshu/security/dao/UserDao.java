package com.wllfengshu.security.dao;

import com.wllfengshu.security.model.User;
import com.wllfengshu.security.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wllfengshu
 */
@Repository
public interface UserDao extends MyMapper<User> {

    /**
     * 查询所有的用户和对应的角色
     * @return
     */
    List<User> selectAllAndRole();

    /**
     * 查询所有的用户和对应的角色和权限
     * @return
     */
    List<User> selectAllAndRoleAndPermission();
}
