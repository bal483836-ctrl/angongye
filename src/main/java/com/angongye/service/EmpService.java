package com.angongye.service;


import com.angongye.entity.Emp;
import com.angongye.module.MyResponse;

import java.util.List;

public interface EmpService {
    // ============ 员工管理 增删改查（第14章 员工管理模块）============
    List<Emp> getAllEmp();          // 查询全部员工
    Emp getEmpDetail(int id);       // 通过 id 查询员工详情
    MyResponse save(Emp emp);       // 新增员工
    MyResponse deleteById(int id);  // 删除员工
    MyResponse updateById(Emp emp); // 修改员工


    List<Emp> getEmpByCondition(Emp emp);
    List<Emp> getEmpByConditionWhere(Emp emp);
    List<Emp> getEmpByChoose(Emp emp);
    int updateBySet(Emp emp); //有问题（数据库结构改了）
    List<Emp> getEmpByForeach(List<Integer> list); //普通集合
    List<Emp> getEmpByForeach2(List<Emp> list);//对象集合

    int batchInsert(List<Emp> list);//批量添加 ，有问题（数据库结构改了）

    Emp getEmpByLoginId(int id);

    List<Emp> getEmpNoLoginNo();// 没有登录账号的员工信息
}
