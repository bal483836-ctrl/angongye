-- =====================================================================
--  人事管理系统（HRM）数据库脚本   ——《Spring+MyBatis 企业应用实战》第14章
--  说明：本脚本用于初始化项目所需的数据库、数据表以及演示数据。
--  使用方法（在 MySQL 8 中执行）：
--     mysql -u root -p < angongye.sql
--  或在 Navicat / IDEA Database 工具中直接运行本文件。
--
--  运行后默认登录账号： zhangsan   密码： 123456
-- =====================================================================

-- 1. 创建数据库（与 db.properties 中 jdbc:mysql://localhost:3306/angongye 对应）
CREATE DATABASE IF NOT EXISTS `angongye`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

USE `angongye`;

-- 为保证可重复执行，先删除旧表（注意先删有外键关系的从表）
DROP TABLE IF EXISTS `t_emp`;
DROP TABLE IF EXISTS `t_login`;
DROP TABLE IF EXISTS `t_dept`;

-- =====================================================================
-- 表 1：部门表 t_dept
-- =====================================================================
CREATE TABLE `t_dept` (
    `dept_id`      INT          NOT NULL AUTO_INCREMENT COMMENT '部门序号，自增主键',
    `dept_no`      VARCHAR(20)  NOT NULL COMMENT '部门编号，唯一',
    `dept_name`    VARCHAR(50)  DEFAULT NULL COMMENT '部门名称',
    `dept_pid`     INT          DEFAULT 0    COMMENT '上级部门编号，0 表示顶级部门',
    `dept_user`    VARCHAR(50)  DEFAULT NULL COMMENT '部门负责人',
    `dept_address` VARCHAR(100) DEFAULT NULL COMMENT '部门位置',
    PRIMARY KEY (`dept_id`),
    UNIQUE KEY `uk_dept_no` (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

INSERT INTO `t_dept` (`dept_id`,`dept_no`,`dept_name`,`dept_pid`,`dept_user`,`dept_address`) VALUES
(1,'dept001','总经理办公室',0,'王总','行政楼 301'),
(2,'dept002','人力资源部',  0,'李经理','行政楼 201'),
(3,'dept003','研发部',      0,'张经理','研发楼 5 层'),
(4,'dept004','市场部',      0,'赵经理','行政楼 401'),
(5,'dept005','财务部',      0,'钱经理','行政楼 101');

-- =====================================================================
-- 表 2：员工表 t_emp
-- =====================================================================
CREATE TABLE `t_emp` (
    `emp_id`          INT          NOT NULL AUTO_INCREMENT COMMENT '员工序号，自增主键',
    `emp_no`          VARCHAR(20)  NOT NULL COMMENT '员工编号，唯一',
    `emp_name`        VARCHAR(50)  DEFAULT NULL COMMENT '员工姓名',
    `emp_dept_id`     INT          DEFAULT NULL COMMENT '所属部门（关联 t_dept.dept_id）',
    `emp_sex`         VARCHAR(4)   DEFAULT NULL COMMENT '性别',
    `emp_education`   VARCHAR(20)  DEFAULT NULL COMMENT '学历',
    `emp_email`       VARCHAR(50)  DEFAULT NULL COMMENT '邮箱',
    `emp_phone`       VARCHAR(20)  DEFAULT NULL COMMENT '联系电话',
    `emp_entry_time`  DATETIME     DEFAULT NULL COMMENT '入职时间',
    `emp_create_time` DATETIME     DEFAULT NULL COMMENT '记录创建时间',
    `emp_login_id`    INT          DEFAULT NULL COMMENT '关联的登录账号（t_login.login_id），空表示尚未注册账号',
    PRIMARY KEY (`emp_id`),
    UNIQUE KEY `uk_emp_no` (`emp_no`),
    KEY `idx_emp_dept_id` (`emp_dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

INSERT INTO `t_emp`
(`emp_id`,`emp_no`,`emp_name`,`emp_dept_id`,`emp_sex`,`emp_education`,`emp_email`,`emp_phone`,`emp_entry_time`,`emp_create_time`,`emp_login_id`) VALUES
(1,'emp001','张三',1,'男','本科','zhangsan@angongye.com','13800000001','2023-03-01 09:00:00','2023-03-01 09:00:00',1),
(2,'emp002','李四',2,'女','硕士','lisi@angongye.com',    '13800000002','2023-05-10 09:00:00','2023-05-10 09:00:00',NULL),
(3,'emp003','王五',3,'男','本科','wangwu@angongye.com',  '13800000003','2023-06-15 09:00:00','2023-06-15 09:00:00',NULL),
(4,'emp004','赵六',3,'男','大专','zhaoliu@angongye.com', '13800000004','2023-08-20 09:00:00','2023-08-20 09:00:00',NULL),
(5,'emp005','孙七',4,'女','本科','sunqi@angongye.com',   '13800000005','2023-09-01 09:00:00','2023-09-01 09:00:00',NULL),
(6,'emp006','周八',5,'女','硕士','zhouba@angongye.com',  '13800000006','2023-10-11 09:00:00','2023-10-11 09:00:00',NULL);

-- =====================================================================
-- 表 3：登录账号表 t_login（用于系统登录，配合员工表使用）
-- =====================================================================
CREATE TABLE `t_login` (
    `login_id`     INT         NOT NULL AUTO_INCREMENT COMMENT '序号，自增主键',
    `login_name`   VARCHAR(20) NOT NULL COMMENT '登录名，唯一',
    `login_pwd`    CHAR(32)    DEFAULT NULL COMMENT '加盐 MD5 加密后的密码',
    `login_salt`   VARCHAR(20) DEFAULT NULL COMMENT '随机盐',
    `login_index`  INT         DEFAULT NULL COMMENT '加密字符数组索引',
    `login_status` INT         DEFAULT 0    COMMENT '状态：0-正常，1-已禁用，2-已删除',
    `login_error`  INT         DEFAULT 0    COMMENT '连续登录错误次数',
    PRIMARY KEY (`login_id`),
    UNIQUE KEY `uk_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录账号表';

-- 密码 123456，盐 aaaa，索引 2，经 MD5Util.md5To32String 加密得到下方密文
INSERT INTO `t_login`
(`login_id`,`login_name`,`login_pwd`,`login_salt`,`login_index`,`login_status`,`login_error`) VALUES
(1,'zhangsan','0EC07ABAB52629E7DDED3F7EEB1A26F7','aaaa',2,0,0);
