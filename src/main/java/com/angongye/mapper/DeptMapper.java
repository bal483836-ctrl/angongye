package com.angongye.mapper;

import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DeptMapper {
    //获得所有的部门信息
    List<Dept> getDeptAll();
    Dept getDeptByNo(String no); // 通过部门编号，获得部门信息
    Dept getDeptById(int id);
    List<Dept> getDeptByCondition(HashMap<String ,Object> map);
    List<Dept> getDeptByObj(Dept dept);
    List<Dept> getDeptGreateThanId(int id);
    List<Dept> getDeptLessThanId(int id);

    List<Dept> getDeptByName2(String no);


    int saveDept(Dept dept);//保存
    int saveDeptAndId(Dept dept);

    int updateById(Dept dept);


    int deleteById(int id);//删除

    List<Dept> getDeptNoUnique(Dept dept);

    int updateById2(Dept dept);

}
