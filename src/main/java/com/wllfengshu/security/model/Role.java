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
@Table(name = "t_role")
@ApiModel(value = "角色实体类")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", notes = "ID")
    private Integer id;

    @Column(name = "`name`")
    @ApiModelProperty(name = "name", notes = "角色名")
    private String name;

    @Column(name = "description")
    @ApiModelProperty(name = "description", notes = "描述")
    private String description;

    /**
     * 权限的集合
     */
    @Transient
    private List<Permission> permissions;

}
