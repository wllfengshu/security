package com.wllfengshu.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author wllfengshu
 */
@Table(name = "t_user")
@ApiModel(value = "用户实体类")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", notes = "ID")
    private Integer id;

    @Column(name = "username")
    @ApiModelProperty(name = "username", notes = "用户名")
    private String username;

    @Column(name = "password")
    @ApiModelProperty(name = "password", notes = "密码")
    private String password;

    /**
     * 角色的集合
     */
    @Transient
    private List<Role> roles;

}
