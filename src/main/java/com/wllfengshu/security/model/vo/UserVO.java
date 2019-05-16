package com.wllfengshu.security.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 之所以有一个UserVO，而不使用User的原因是：
 * 1、User中的数据结构比较复杂，每次进行权限检查都要两层循环进行遍历，效率低；
 * 2、作为一个微服务，如果把User给第三方应用进行解析也是十分不友好的；
 * 3、User信息并不需要都缓存到redis中，例如密码、用户个人信息等，缓存UserVO的目的主要是为了进行权限控制；
 * 4、不同角色可能会有相同的权限，但是权限的范围可能不一样，这个就需要UserVO进行处理了。
 * @author wangll
 */
@ApiModel(value = "用户信息")
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", notes = "ID", required = true)
    private Integer id;

    @ApiModelProperty(name = "username", notes = "用户名", required = true)
    private String username;

    /**
     * 形如：
     * ["admin","consumer","producer"]
     */
    @ApiModelProperty(name = "roles", notes = "角色名的集合", required = true)
    private List<String> roles;

    /**
     * 形如：
     * {{"insertPermission":null},{"selectUser":"all"}}
     * 注意：这里的key是权限名，这里的value是scope的值
     */
    @ApiModelProperty(name = "permissions", notes = "权限的集合", required = true)
    private Map<String,Object> permissions;

}
