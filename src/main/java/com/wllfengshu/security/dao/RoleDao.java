package com.wllfengshu.security.dao;

import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wllfengshu
 */
@Repository
public interface RoleDao extends MyMapper<Role> {

    /**
     * 查询角色和对应的权限
     * @param paramsMap
     * @return
     */
    List<Role> selectRoleAndPermission(Map<String, Object> paramsMap);

}
