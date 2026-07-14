package com.angongye.service;

import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import com.angongye.module.MyResponse;

import java.util.List;

/**
 * 业务逻辑门面组件（第14章 HrmService）。
 * 本系统只使用一个业务逻辑组件 HrmService，作为门面封装多个 DAO 组件，
 * 业务逻辑组件依赖底层的 DAO 组件，向上实现系统的业务逻辑功能。
 * 个人作业选取两个表：部门（Dept）与员工（Employee）。
 */
public interface HrmService {

    // ================= 部门（Dept）业务 =================
    List<Dept> findAllDept();            // 查询全部部门
    Dept findDeptById(int id);           // 根据 id 查询部门
    MyResponse addDept(Dept dept);       // 新增部门（校验编号唯一）
    MyResponse modifyDept(Dept dept);    // 修改部门（校验编号唯一）
    MyResponse removeDept(int id);       // 删除部门（部门下有员工时不允许删除）

    // ================= 员工（Employee）业务 =================
    List<Emp> findAllEmployee();         // 查询全部员工
    Emp findEmployeeById(int id);        // 根据 id 查询员工
    MyResponse addEmployee(Emp emp);     // 新增员工（校验编号唯一）
    MyResponse modifyEmployee(Emp emp);  // 修改员工（校验编号唯一）
    MyResponse removeEmployee(int id);   // 删除员工
}
