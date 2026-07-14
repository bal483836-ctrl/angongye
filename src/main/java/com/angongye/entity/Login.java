package com.angongye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Login implements Serializable {
    private Integer loginId; // int primary key not null auto_increment comment '序号，自增',
    private String loginName;// varchar(10) unique comment '登录名，唯一',
    private String loginPwd;// char(32) comment '密码',
    private String loginSalt;// varchar(10) comment '随机盐',
    private Integer loginIndex;// int comment '加密字符数组的索引',
    private Integer loginStatus;// int comment '当前状态 0-正常，1-已经禁用，2-已经删除',
    private Integer loginError;// int
}
