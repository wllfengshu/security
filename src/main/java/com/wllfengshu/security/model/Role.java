package com.wllfengshu.security.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 * @author wllfengshu
 */
@Data
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "ID")
    private Integer id;

    @Column(name = "`name`", columnDefinition = "角色名")
    private String name;

    @Column(name = "description", columnDefinition = "角色描述")
    private String description;

    /**
     * 权限的集合
     */
    @Transient
    private List<Permission> permissions;

}
