package com.wllfengshu.security.dao;

import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wllfengshu
 */
@Repository
public interface RoleDao extends MyMapper<Role> {

    /**
     * 查询所有的角色和对应的权限
     * @return
     */
    List<Role> selectAllAndPermission();

}
