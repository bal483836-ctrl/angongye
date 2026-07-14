package com.angongye.service.impl;

import com.angongye.entity.Emp;
import com.angongye.mapper.EmpMapper;
import com.angongye.module.MyResponse;
import com.angongye.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service //表示这是一个业务逻辑类
@Transactional //事务管理？当某个函数发生异常时，回滚数据
@Slf4j // 日志记录
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    // ============ 员工管理 增删改查（第14章 员工管理模块）============
    @Override
    public List<Emp> getAllEmp() {
        log.info("===========EmpServiceImpl============getAllEmp=======");
        return empMapper.getEmpAll();
    }

    @Override
    public Emp getEmpDetail(int id) {
        log.info("===========EmpServiceImpl============getEmpDetail=======");
        return empMapper.getEmpById(id);
    }

    @Override
    public MyResponse save(Emp emp) {
        log.info("===========EmpServiceImpl============save=======");
        MyResponse result = new MyResponse();
        // 校验员工编号是否已经存在（唯一）
        Emp temp = empMapper.getEmpByNo(emp.getEmpNo());
        if (temp == null || temp.getEmpId() == null) {
            int count = empMapper.saveEmp(emp);
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
    public MyResponse deleteById(int id) {
        log.info("===========EmpServiceImpl============deleteById=======");
        MyResponse result = new MyResponse();
        int count = empMapper.deleteEmpById(id);
        if (count == 1) {
            result.setMsg("员工删除成功，重新加载数据");
            result.setSuccess(true);
        } else {
            result.setMsg("员工删除失败，重新加载数据");
        }
        return result;
    }

    @Override
    public MyResponse updateById(Emp emp) {
        log.info("===========EmpServiceImpl============updateById=======");
        MyResponse result = new MyResponse();
        // 校验员工编号唯一（除自身外不能重复）
        List<Emp> list = empMapper.getEmpNoUnique(emp);
        if (list == null || list.size() == 0) {
            int count = empMapper.updateEmpById(emp);
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
    public List<Emp> getEmpByCondition(Emp emp) {

        return Collections.emptyList();
    }

    @Override
    public List<Emp> getEmpByConditionWhere(Emp emp) {
        return Collections.emptyList();
    }

    @Override
    public List<Emp> getEmpByChoose(Emp emp) {
        return Collections.emptyList();
    }

    @Override
    public int updateBySet(Emp emp) {
        return 0;
    }

    @Override
    public List<Emp> getEmpByForeach(List<Integer> list) {
        return Collections.emptyList();
    }

    @Override
    public List<Emp> getEmpByForeach2(List<Emp> list) {
        return Collections.emptyList();
    }

    @Override
    public int batchInsert(List<Emp> list) {
        log.info("===========EmpServiceImpl============batchInsert=======");
        int count = 0;
        count = empMapper.batchInsert(list);
        return count;
    }

    @Override
    public Emp getEmpByLoginId(int id) {
        log.info("===========EmpServiceImpl============getEmpByLoginId=======");
        return empMapper.getEmpByLoginId(id);
    }

    @Override
    public List<Emp> getEmpNoLoginNo() {
        log.info("===========EmpServiceImpl============getEmpNoLoginNo=======");
        return empMapper.getEmpNoLoginNo();
    }
}
