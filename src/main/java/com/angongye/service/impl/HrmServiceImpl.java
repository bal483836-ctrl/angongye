package com.angongye.service.impl;

import com.angongye.dao.DeptDao;
import com.angongye.dao.EmployeeDao;
import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import com.angongye.module.MyResponse;
import com.angongye.service.HrmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务逻辑门面组件实现（第14章 HrmServiceImpl）。
 * 该组件依赖 DeptDao、EmployeeDao 两个 DAO 组件，面向 DAO 接口编程，
 * 只关心业务逻辑（如编号唯一校验、删除约束），无须关心数据访问的实现细节。
 */
@Service
@Transactional
@Slf4j
public class HrmServiceImpl implements HrmService {

    @Autowired
    private DeptDao deptDao;
    @Autowired
    private EmployeeDao employeeDao;

    // ================= 部门（Dept）业务 =================
    @Override
    public List<Dept> findAllDept() {
        log.info("=========HrmServiceImpl=========findAllDept=========");
        return deptDao.selectAllDept();
    }

    @Override
    public Dept findDeptById(int id) {
        log.info("=========HrmServiceImpl=========findDeptById=========");
        return deptDao.selectById(id);
    }

    @Override
    public MyResponse addDept(Dept dept) {
        log.info("=========HrmServiceImpl=========addDept=========");
        MyResponse result = new MyResponse();
        Dept temp = deptDao.selectByNo(dept.getDeptNo());
        if (temp == null || temp.getDeptId() == null) {
            dept.setDeptPid(0);
            int count = deptDao.save(dept);
            if (count == 1) {
                result.setMsg("该部门创建成功");
                result.setSuccess(true);
            } else {
                result.setMsg("该部门创建失败");
            }
        } else {
            result.setMsg("该部门编号已存在，不能新建该部门");
        }
        return result;
    }

    @Override
    public MyResponse modifyDept(Dept dept) {
        log.info("=========HrmServiceImpl=========modifyDept=========");
        MyResponse result = new MyResponse();
        List<Dept> list = deptDao.selectNoUnique(dept);
        if (list == null || list.size() == 0) {
            int count = deptDao.update(dept);
            if (count == 1) {
                result.setMsg("该部门修改成功，重新加载数据");
                result.setSuccess(true);
            } else {
                result.setMsg("该部门修改失败，重新加载数据");
            }
        } else {
            result.setMsg("该部门编号已经被使用，不能修改部门");
        }
        return result;
    }

    @Override
    public MyResponse removeDept(int id) {
        log.info("=========HrmServiceImpl=========removeDept=========");
        MyResponse result = new MyResponse();
        // 部门下如果还有员工，则不允许删除
        List<Emp> emps = employeeDao.selectByDeptId(id);
        if (emps == null || emps.size() == 0) {
            int count = deptDao.deleteById(id);
            if (count == 1) {
                result.setMsg("该部门删除成功，重新加载数据");
                result.setSuccess(true);
            } else {
                result.setMsg("该部门删除失败，重新加载数据");
            }
        } else {
            result.setMsg("该部门下有员工，不能删除该部门");
        }
        return result;
    }

    // ================= 员工（Employee）业务 =================
    @Override
    public List<Emp> findAllEmployee() {
        log.info("=========HrmServiceImpl=========findAllEmployee=========");
        return employeeDao.selectAllEmp();
    }

    @Override
    public Emp findEmployeeById(int id) {
        log.info("=========HrmServiceImpl=========findEmployeeById=========");
        return employeeDao.selectById(id);
    }

    @Override
    public MyResponse addEmployee(Emp emp) {
        log.info("=========HrmServiceImpl=========addEmployee=========");
        MyResponse result = new MyResponse();
        Emp temp = employeeDao.selectByNo(emp.getEmpNo());
        if (temp == null || temp.getEmpId() == null) {
            int count = employeeDao.save(emp);
            if (count == 1) {
                result.setMsg("员工添加成功");
                result.setSuccess(true);
            } else {
                result.setMsg("员工添加失败");
            }
        } else {
            result.setMsg("该员工编号已存在，不能重复添加");
        }
        return result;
    }

    @Override
    public MyResponse modifyEmployee(Emp emp) {
        log.info("=========HrmServiceImpl=========modifyEmployee=========");
        MyResponse result = new MyResponse();
        List<Emp> list = employeeDao.selectNoUnique(emp);
        if (list == null || list.size() == 0) {
            int count = employeeDao.update(emp);
            if (count == 1) {
                result.setMsg("员工修改成功，重新加载数据");
                result.setSuccess(true);
            } else {
                result.setMsg("员工修改失败，重新加载数据");
            }
        } else {
            result.setMsg("该员工编号已被占用，不能修改");
        }
        return result;
    }

    @Override
    public MyResponse removeEmployee(int id) {
        log.info("=========HrmServiceImpl=========removeEmployee=========");
        MyResponse result = new MyResponse();
        int count = employeeDao.deleteById(id);
        if (count == 1) {
            result.setMsg("员工删除成功，重新加载数据");
            result.setSuccess(true);
        } else {
            result.setMsg("员工删除失败，重新加载数据");
        }
        return result;
    }
}
