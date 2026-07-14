package com.angongye.dao;

import com.angongye.dao.provider.EmployeeDynaSqlProvider;
import com.angongye.entity.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

import static com.angongye.util.common.HrmConstants.EMPLOYEETABLE;

/**
 * 员工 DAO 组件（第14章 DAO 模式）。
 * 列表/详情查询通过左连接部门表附带部门名称 deptName，便于页面展示。
 */
@Mapper
public interface EmployeeDao {

    // 查询全部员工（含部门名称）
    @Select("select e.*, d.dept_name as deptName "
            + "from " + EMPLOYEETABLE + " e "
            + "left join t_dept d on e.emp_dept_id = d.dept_id "
            + "order by e.emp_id")
    List<Emp> selectAllEmp();

    // 根据 id 查询员工（含部门名称）
    @Select("select e.*, d.dept_name as deptName "
            + "from " + EMPLOYEETABLE + " e "
            + "left join t_dept d on e.emp_dept_id = d.dept_id "
            + "where e.emp_id = #{id}")
    Emp selectById(int id);

    // 根据员工编号查询（新增时校验编号唯一）
    @Select("select * from " + EMPLOYEETABLE + " where emp_no = #{empNo}")
    Emp selectByNo(String empNo);

    // 修改时校验：除自身外是否存在相同的员工编号
    @Select("select * from " + EMPLOYEETABLE + " where emp_id != #{empId} and emp_no = #{empNo}")
    List<Emp> selectNoUnique(Emp emp);

    // 查询某部门下的员工（删除部门前判断该部门是否还有员工）
    @Select("select * from " + EMPLOYEETABLE + " where emp_dept_id = #{deptId}")
    List<Emp> selectByDeptId(int deptId);

    // 根据 id 删除员工
    @Delete("delete from " + EMPLOYEETABLE + " where emp_id = #{id}")
    int deleteById(int id);

    // 动态插入员工
    @InsertProvider(type = EmployeeDynaSqlProvider.class, method = "insertEmp")
    int save(Emp emp);

    // 动态修改员工
    @UpdateProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmp")
    int update(Emp emp);
}
