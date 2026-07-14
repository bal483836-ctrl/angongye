package com.angongye.dao;

import com.angongye.dao.provider.DeptDynaSqlProvider;
import com.angongye.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

import static com.angongye.util.common.HrmConstants.DEPTTABLE;

/**
 * 部门 DAO 组件（第14章 DAO 模式）。
 * MyBatis 建议以接口 + 注解的方式完成 SQL 映射，该接口即可直接作为 DAO 组件使用。
 * 简单 SQL 使用 @Select/@Delete 静态注解；动态 SQL（增、改）交给 DeptDynaSqlProvider 提供类。
 */
@Mapper
public interface DeptDao {

    // 查询全部部门
    @Select("select * from " + DEPTTABLE)
    List<Dept> selectAllDept();

    // 根据 id 查询部门
    @Select("select * from " + DEPTTABLE + " where dept_id = #{id}")
    Dept selectById(int id);

    // 根据部门编号查询（新增时校验编号唯一）
    @Select("select * from " + DEPTTABLE + " where dept_no = #{deptNo}")
    Dept selectByNo(String deptNo);

    // 修改时校验：除自身外是否存在相同的部门编号
    @Select("select * from " + DEPTTABLE + " where dept_id != #{deptId} and dept_no = #{deptNo}")
    List<Dept> selectNoUnique(Dept dept);

    // 根据 id 删除部门
    @Delete("delete from " + DEPTTABLE + " where dept_id = #{id}")
    int deleteById(int id);

    // 动态插入部门
    @InsertProvider(type = DeptDynaSqlProvider.class, method = "insertDept")
    int save(Dept dept);

    // 动态修改部门
    @UpdateProvider(type = DeptDynaSqlProvider.class, method = "updateDept")
    int update(Dept dept);
}
