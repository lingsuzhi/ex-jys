package com.lsz.jys.apply.dto.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * 登录
 */
@Data
public class LoginDTO implements Serializable {

    //账号
    private String userId;

    //密码
    private String password;
}
