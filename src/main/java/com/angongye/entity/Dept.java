package com.angongye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data //自动注入getter与setter
@AllArgsConstructor //注入所有属性构造函数
@NoArgsConstructor //注入空构造
@Accessors(chain = true) //链式编程？？
public class Dept implements Serializable { //实现序列化接口，方便网络传输
    // mybaits  遵循驼峰命名映射，方便实现ORM dept_id  ==》 deptId
    // dept_user_name  ==> deptUserName
    // 数据类型 int==》int===提升（不使用基本类型，使用基本类型对应的包装类）  int==》Integer
    //        varchar ===>String
    private Integer deptId;
    private String deptNo;
    private String deptName;
    private Integer deptPid;
    private String deptUser;
    private String deptAddress;



}
