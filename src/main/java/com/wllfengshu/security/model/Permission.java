package com.wllfengshu.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wllfengshu
 */
@Table(name = "t_permission")
@ApiModel(value = "权限实体类")
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", notes = "ID")
    private Integer id;

    @Column(name = "`name`")
    @ApiModelProperty(name = "name", notes = "权限名")
    private String name;

    @Column(name = "description")
    @ApiModelProperty(name = "description", notes = "描述")
    private String description;

}
