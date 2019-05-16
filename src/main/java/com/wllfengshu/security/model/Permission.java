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
     * 用来做数据权限，这里存在四种值：all全部；org本部门及其下属部门；dept其他部门；person个人
     * 1）all：查询全部（如果有多租户的概念，这里可以表示查整个租户）
     * 2）org：查询本部门及其下属部门
     * 3）dept：查询其他部门数据（这里可以是一个json数组，用来存放其他部门的id）
     * 4）person：查询个人的数据
     */
    @Column(name = "scope", columnDefinition = "权限范围")
    private String scope;

}
