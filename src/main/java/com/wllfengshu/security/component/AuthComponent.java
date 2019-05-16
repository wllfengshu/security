package com.wllfengshu.security.component;

import com.alibaba.fastjson.JSON;
import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.model.User;
import com.wllfengshu.security.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 安全控制
 * @author wangll
 */
@Component
public class AuthComponent {

    /**
     * redis客户端
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 在redis中存放的key名
     */
    private static final String SESSION_KEY_NAME = "security:session:";

    /**
     * session过期时间
     */
    private static final long SESSION_EXPIRE_TIME = 30;

    /**
     * 验证用户是否具有指定的权限
     *     注意：这里返回userVO是为了给service层做数据权限
     * @param sessionId
     * @param permissionName
     * @return
     * @throws CustomException
     */
    public UserVO requiresPermission(String sessionId, String permissionName)throws CustomException{
        Object o = this.get(sessionId);
        if (o == null){
            throw new CustomException("sessionId无效", CustomException.ExceptionName.InvalidSessionId);
        }
        if (((UserVO) o).getPermissions().containsKey(permissionName)){
            return (UserVO) o;
        }
        throw new CustomException("没有权限", CustomException.ExceptionName.Unauthenticated);
    }

    /**
     * 把User转为UserVO
     * @return
     */
    public UserVO user2UserVo(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        List<String> roles = new ArrayList<>();
        Map<String,Object> permissions = new HashMap<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
            for (Permission permission : role.getPermissions()) {
                //TODO 这里要处理不同角色、相同权限、但是权限范围不同的问题
                permissions.put(permission.getName(),permission.getScope());
            }
        }
        userVO.setRoles(roles);
        userVO.setPermissions(permissions);
        System.out.println(JSON.toJSONString(userVO));
        return userVO;
    }

    public void put(String key,Object value){
        redisTemplate.opsForValue().set(SESSION_KEY_NAME + key,value,SESSION_EXPIRE_TIME, TimeUnit.MINUTES);
    }

    public boolean delete(String key){
        return redisTemplate.delete(SESSION_KEY_NAME + key);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(SESSION_KEY_NAME + key);
    }

    public boolean expire(String key){
        return redisTemplate.expire(SESSION_KEY_NAME + key,SESSION_EXPIRE_TIME,TimeUnit.MINUTES);
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(SESSION_KEY_NAME + key);
    }
}
