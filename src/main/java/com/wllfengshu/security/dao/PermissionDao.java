package com.wllfengshu.security.dao;

import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.utils.MyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author wllfengshu
 */
@Repository
public interface PermissionDao extends MyMapper<Permission> {

}
