package com.wllfengshu.security.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限实体类
 * @author wllfengshu
 */
@Data
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "ID")
    private Integer id;

    /**
     * 用来做页面权限和操作权限
     * 例如：insertUser
     * 如果前端检查到该用户没有insertUser的权限，则不用展示插入用户的页面；
     * 如果其没有insertUser的权限，则无法进行插入用户的操作（不管是页面还是通过接口的方式都无法操作）。
     */
    @Column(name = "`name`", columnDefinition = "权限名")
    private String name;

    @Column(name = "description", columnDefinition = "权限描述")
    private String description;

    /**
     * 用来做数据权限
     * TODO 这里暂只考虑2种值：all全部；person个人
     * 1）all：查询全部
     * 2）person：查询个人的数据
     */
    @Column(name = "scope", columnDefinition = "权限范围")
    private String scope;

}
