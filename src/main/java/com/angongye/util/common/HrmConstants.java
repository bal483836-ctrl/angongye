package com.angongye.util.common;

/**
 * 系统公共常量类（对应《Spring+MyBatis 企业应用实战》第14章 HrmConstants）。
 * 集中管理数据表名等常量。本项目沿用已有的表名（t_dept、t_emp）。
 */
public class HrmConstants {
    // 数据库表常量
    public static final String DEPTTABLE = "t_dept";      // 部门表
    public static final String EMPLOYEETABLE = "t_emp";   // 员工表
    public static final String LOGINTABLE = "t_login";    // 登录账号表

    // 默认每页条数
    public static final int PAGE_DEFAULT_SIZE = 4;
}
