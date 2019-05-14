package com.wllfengshu.security.utils;

import com.wllfengshu.security.exception.CustomException;
import com.wllfengshu.security.model.Permission;
import com.wllfengshu.security.model.Role;
import com.wllfengshu.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 安全控制
 * @author wangll
 */
@Component
public class AuthUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 在redis中存放的hash的表名
     */
    private static final String SESSION_HASH_NAME = "security:session";

    /**
     * 验证用户是否具有指定的权限
     *     注意：这里返回user是为了给service层做数据权限
     * @param sessionId
     * @param permissionName
     * @return
     * @throws CustomException
     */
    public User requiresPermission(String sessionId,String permissionName)throws CustomException{
        Object o = this.get(sessionId);
        if (o == null){
            throw new CustomException("sessionId无效", CustomException.ExceptionName.InvalidSessionId);
        }
        User user = (User) o;
        for (Role role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                if (permissionName.equals(permission.getName())){
                    return user;
                }
            }
        }
        throw new CustomException("没有权限", CustomException.ExceptionName.Unauthenticated);
    }

    public void put(String key,Object value){
        redisTemplate.opsForHash().put(SESSION_HASH_NAME,key,value);
    }

    public void delete(String key){
        redisTemplate.opsForHash().delete(SESSION_HASH_NAME,key);
    }

    public boolean hasKey(String key){
        return redisTemplate.opsForHash().hasKey(SESSION_HASH_NAME,key);
    }

    public Object get(String key){
        return redisTemplate.opsForHash().get(SESSION_HASH_NAME,key);
    }

}
