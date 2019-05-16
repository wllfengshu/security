package com.wllfengshu.security.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 * @author wllfengshu
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "ID")
    private Integer id;

    @Column(name = "username", columnDefinition = "用户名")
    private String username;

    @Column(name = "password", columnDefinition = "密码")
    private String password;

    /**
     * 角色的集合
     */
    @Transient
    private List<Role> roles;

}
