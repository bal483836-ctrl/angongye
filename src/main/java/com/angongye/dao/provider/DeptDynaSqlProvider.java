package com.angongye.dao.provider;

import com.angongye.entity.Dept;
import org.apache.ibatis.jdbc.SQL;

import static com.angongye.util.common.HrmConstants.DEPTTABLE;

/**
 * 部门动态 SQL 提供类（第14章 DeptDynaSqlProvider）。
 * 使用 MyBatis 提供的 org.apache.ibatis.jdbc.SQL 构建器，根据传入对象的属性动态拼接 SQL。
 */
public class DeptDynaSqlProvider {

    // 动态插入部门
    public String insertDept(final Dept dept) {
        return new SQL() {{
            INSERT_INTO(DEPTTABLE);
            if (dept.getDeptNo() != null && !dept.getDeptNo().equals("")) {
                VALUES("dept_no", "#{deptNo}");
            }
            if (dept.getDeptName() != null && !dept.getDeptName().equals("")) {
                VALUES("dept_name", "#{deptName}");
            }
            // 顶级部门 pid 默认为 0
            VALUES("dept_pid", "#{deptPid}");
            if (dept.getDeptUser() != null && !dept.getDeptUser().equals("")) {
                VALUES("dept_user", "#{deptUser}");
            }
            if (dept.getDeptAddress() != null && !dept.getDeptAddress().equals("")) {
                VALUES("dept_address", "#{deptAddress}");
            }
        }}.toString();
    }

    // 动态修改部门
    public String updateDept(final Dept dept) {
        return new SQL() {{
            UPDATE(DEPTTABLE);
            if (dept.getDeptNo() != null && !dept.getDeptNo().equals("")) {
                SET("dept_no = #{deptNo}");
            }
            if (dept.getDeptName() != null && !dept.getDeptName().equals("")) {
                SET("dept_name = #{deptName}");
            }
            if (dept.getDeptPid() != null) {
                SET("dept_pid = #{deptPid}");
            }
            if (dept.getDeptUser() != null && !dept.getDeptUser().equals("")) {
                SET("dept_user = #{deptUser}");
            }
            if (dept.getDeptAddress() != null && !dept.getDeptAddress().equals("")) {
                SET("dept_address = #{deptAddress}");
            }
            WHERE("dept_id = #{deptId}");
        }}.toString();
    }
}
