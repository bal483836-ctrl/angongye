package com.angongye.dao.provider;

import com.angongye.entity.Emp;
import org.apache.ibatis.jdbc.SQL;

import static com.angongye.util.common.HrmConstants.EMPLOYEETABLE;

/**
 * 员工动态 SQL 提供类（第14章 EmployeeDynaSqlProvider）。
 * 使用 org.apache.ibatis.jdbc.SQL 构建器动态拼接员工的增、改语句。
 */
public class EmployeeDynaSqlProvider {

    // 动态插入员工（创建时间由数据库 now() 填充）
    public String insertEmp(final Emp emp) {
        return new SQL() {{
            INSERT_INTO(EMPLOYEETABLE);
            if (emp.getEmpNo() != null && !emp.getEmpNo().equals("")) {
                VALUES("emp_no", "#{empNo}");
            }
            if (emp.getEmpName() != null && !emp.getEmpName().equals("")) {
                VALUES("emp_name", "#{empName}");
            }
            if (emp.getEmpDeptId() != null) {
                VALUES("emp_dept_id", "#{empDeptId}");
            }
            if (emp.getEmpSex() != null && !emp.getEmpSex().equals("")) {
                VALUES("emp_sex", "#{empSex}");
            }
            if (emp.getEmpEducation() != null && !emp.getEmpEducation().equals("")) {
                VALUES("emp_education", "#{empEducation}");
            }
            if (emp.getEmpEmail() != null && !emp.getEmpEmail().equals("")) {
                VALUES("emp_email", "#{empEmail}");
            }
            if (emp.getEmpPhone() != null && !emp.getEmpPhone().equals("")) {
                VALUES("emp_phone", "#{empPhone}");
            }
            if (emp.getEmpEntryTime() != null) {
                VALUES("emp_entry_time", "#{empEntryTime}");
            }
            VALUES("emp_create_time", "now()");
        }}.toString();
    }

    // 动态修改员工
    public String updateEmp(final Emp emp) {
        return new SQL() {{
            UPDATE(EMPLOYEETABLE);
            if (emp.getEmpNo() != null && !emp.getEmpNo().equals("")) {
                SET("emp_no = #{empNo}");
            }
            if (emp.getEmpName() != null && !emp.getEmpName().equals("")) {
                SET("emp_name = #{empName}");
            }
            if (emp.getEmpDeptId() != null) {
                SET("emp_dept_id = #{empDeptId}");
            }
            if (emp.getEmpSex() != null && !emp.getEmpSex().equals("")) {
                SET("emp_sex = #{empSex}");
            }
            if (emp.getEmpEducation() != null && !emp.getEmpEducation().equals("")) {
                SET("emp_education = #{empEducation}");
            }
            if (emp.getEmpEmail() != null && !emp.getEmpEmail().equals("")) {
                SET("emp_email = #{empEmail}");
            }
            if (emp.getEmpPhone() != null && !emp.getEmpPhone().equals("")) {
                SET("emp_phone = #{empPhone}");
            }
            if (emp.getEmpEntryTime() != null) {
                SET("emp_entry_time = #{empEntryTime}");
            }
            WHERE("emp_id = #{empId}");
        }}.toString();
    }
}
