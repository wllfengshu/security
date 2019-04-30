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
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",upToken.getUsername());
        User user = userDao.selectOneByExample(example);
        if (user == null){
            logger.error("用户不存在");
            throw new UnknownAccountException();
        }
        if (!((String.valueOf(upToken.getPassword())).equals(user.getPassword()))){
            logger.error("用户名或密码错误");
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }

}
