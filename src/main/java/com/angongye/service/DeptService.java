package com.angongye.service;

import com.angongye.entity.Dept;
import com.angongye.module.MyResponse;

import java.util.List;

/**
 * @author: java-张绍鹏
 * addr: 江苏南京
 * tel:137 7663 2257 张老师  （微信同号）
 * @user: Thinkpad
 * @date: 2026/7/13 - 14:46
 * @Description: 简单描述一下Java类
 * projectName:angongye
 */
public interface DeptService {

    List<Dept> getAll();//获得全部
    Dept getDeptById(int id);//

    MyResponse save(Dept dept);//添加新部门信息

    MyResponse deleteById(int id);

    MyResponse updateById2(Dept dept);
}
