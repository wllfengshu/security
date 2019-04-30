package com.wllfengshu.security.dao;

import com.wllfengshu.security.model.User;
import com.wllfengshu.security.utils.MyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author wllfengshu
 */
@Repository
public interface UserDao extends MyMapper<User> {

}
