package com.wllfengshu.security.shiro;

import com.wllfengshu.security.dao.PermissionDao;
import com.wllfengshu.security.dao.RoleDao;
import com.wllfengshu.security.dao.UserDao;
import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义realm
 *
 * @author wllfengshu
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        User user = (User) principal.getPrimaryPrincipal();
        logger.debug("doGetAuthorizationInfo user:{}",user);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
			info.addRole(role.getName());
			for (Permission permission : role.getPermissions()) {
				info.addStringPermission(permission.getName());
			}
        }
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        Map<String, Object> condition = new HashMap<>();
        condition.put("username",upToken.getUsername());
        List<User> users = userDao.selectUserAndRoleAndPermission(condition);
        logger.debug("doGetAuthenticationInfo user:{}",users);
        if (users == null || users.size() <= 0){
            logger.error("用户不存在");
            throw new UnknownAccountException();
        }
        if (!((String.valueOf(upToken.getPassword())).equals(users.get(0).getPassword()))){
            logger.error("用户名或密码错误");
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(users.get(0), users.get(0).getPassword(), getName());
        return info;
    }

}
