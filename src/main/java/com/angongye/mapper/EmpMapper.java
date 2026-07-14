package com.angongye.mapper;

import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface EmpMapper {
    List<Emp> getEmpByCondition(Emp emp);
    List<Emp> getEmpByConditionWhere(Emp emp);
    List<Emp> getEmpByChoose(Emp emp);
    int updateBySet(Emp emp);
    List<Emp> getEmpByForeach(List<Integer> list); //普通集合
    List<Emp> getEmpByForeach2(List<Emp> list);//对象集合

    int batchInsert(List<Emp> list);//批量添加

    Emp getEmpByLoginId(int id);

    List<Emp> getEmpNoLoginNo();// 没有登录账号的员工信息

    int updateLoginIdByEmpId(Emp emp);//通过id修改对应的登录Id

    List<Emp> getEmpsByDeptId(int deptId);

    // ============ 员工管理 增删改查（第14章 员工管理模块）============
    List<Emp> getEmpAll();               // 查询全部员工（含部门名称）
    Emp getEmpById(int id);              // 通过主键查询员工（含部门名称）
    Emp getEmpByNo(String no);           // 通过员工编号查询员工（校验编号唯一）
    int saveEmp(Emp emp);                // 新增员工
    int updateEmpById(Emp emp);          // 修改员工
    int deleteEmpById(int id);           // 删除员工
    List<Emp> getEmpNoUnique(Emp emp);   // 修改时校验员工编号是否被其他记录占用
}
