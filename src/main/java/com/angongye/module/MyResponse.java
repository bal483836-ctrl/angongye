package com.angongye.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//entity 包中类，与数据库表一一对应
// module 包中的类，java中需要使用的pojo
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Accessors(chain = true)
//public class MyResponse<T> implements Serializable {
//    private Integer code=200;//保存响应码
//    private String msg="";   //处理完成后的返回消息
//    private Boolean success = false; //该业务是否处理成功，登录成功（true），登录失败（false）
//    private T tag;//泛型类，创建该MyResponse对象时，要确定给出的返回tag类型，不用强转，有类型约束
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MyResponse implements Serializable {
    private Integer code=200;//保存响应码
    private String msg="";   //处理完成后的返回消息
    private Boolean success = false; //该业务是否处理成功，登录成功（true），登录失败（false）
    private Object tag;//返回的对象
}

