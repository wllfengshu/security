package com.wllfengshu.security.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wllfengshu
 */
@ApiModel(value = "登陆时的参数")
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "username", notes = "用户名", required = true)
    private String username;

    @ApiModelProperty(name = "password", notes = "密码", required = true)
    private String password;

}
