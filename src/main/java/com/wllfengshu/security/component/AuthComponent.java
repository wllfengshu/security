package com.wllfengshu.security.component;

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
                /**
                 * TODO 这里要处理不同角色、相同权限、但是权限范围不同的问题(暂时只考虑all和person)
                 * eg:假设有这样两个角色：“坐席”和“团队长”
                 * 【角色介绍】：
                 * 他们都可以拨打电话，拨打完毕后都可以查询电话的拨打记录（权限名为：callRecord）；
                 * 团队长可以管理坐席，坐席只能查看自己的拨打记录，团队长可以查看所有拨打记录。
                 * 【角色定义】：
                 * 坐席的callRecord权限定义为{"permission":"callRecord","scope":"person"},这里的person表示只能查自己的拨打记录
                 * 团队长的callRecord权限定义为{"permission":"callRecord","scope":"all"}，这里的all表示可以查全部的拨打记录
                 * 【问题描述】：
                 * 假设这两个角色被一个用户同时拥有，那么这个用户的callRecord权限应该取最大范围，也即是“all”,
                 * 得到的最终权限为{"callRecord":"all"}
                 */
                if (permissions.containsKey(permission.getName()) && "all".equals(permissions.get(permission.getName()))){
                    continue;
                }
                permissions.put(permission.getName(),permission.getScope());
            }
        }
        userVO.setRoles(roles);
        userVO.setPermissions(permissions);
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
