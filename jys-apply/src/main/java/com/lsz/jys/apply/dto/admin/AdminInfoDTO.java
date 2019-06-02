package com.lsz.jys.apply.dto.admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminInfoDTO {
    //账号
    private String userId;
    //性别
    private Integer sex;
    //用户昵称
    private String nickName;
    //手机号码
    private String cellPhone;
    //密码
    private String password;
    //加密类型
    private String pwdType;
    //创建时间
    private LocalDateTime createDate;
}
