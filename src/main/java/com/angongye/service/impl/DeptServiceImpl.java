package com.angongye.service.impl;

import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import com.angongye.mapper.DeptMapper;
import com.angongye.mapper.EmpMapper;
import com.angongye.module.MyResponse;
import com.angongye.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DeptServiceImpl implements DeptService {
 @Autowired
 DeptMapper deptMapper;
 @Autowired
 EmpMapper empMapper;


 @Override
 public List<Dept> getAll() {
  log.info("========DeptServiceImpl============getAll===========");
  return deptMapper.getDeptAll();
 }

 @Override
 public Dept getDeptById(int id) {
  log.info("========DeptServiceImpl============getDeptById===========");
  return deptMapper.getDeptById(id);
 }

 @Override
 public MyResponse save(Dept dept) {
  log.info("========DeptServiceImpl============save===========");
  MyResponse result = new MyResponse();
  //判断该部门编号是否存在
  Dept temp = deptMapper.getDeptByNo(dept.getDeptNo());
  if(temp==null || temp.getDeptId()==null){
   //添加
   dept.setDeptPid(0);
   int count = deptMapper.saveDept(dept);
   if(count==1){
    result.setMsg("该部门创建成功");
    result.setSuccess(true);
   }else{
    result.setMsg("该部门创建失败");
   }

  }else{
   result.setMsg("该账号已经存在，不能新建该部门");
  }
  return result;
 }

 @Override
 public MyResponse deleteById(int id) {
  log.info("========DeptServiceImpl============deleteById===========");
  MyResponse result = new MyResponse();
  // 查询该部门下有员工，如果有，不能删除该部门，如果没有可以删除
  List<Emp> list = empMapper.getEmpsByDeptId(id);
  if(list==null || list.size()==0){
   //可以删除
   int count = deptMapper.deleteById(id);
   if(count==1){
    result.setMsg("该部门删除成功，重新加载数据");
    result.setSuccess(true);
   }else{
    result.setMsg("该部门删除失败，重新加载数据");
   }
  }else{
   result.setMsg("该部门下有员工，不能删除该部门");
  }
  return result;
 }

 @Override
 public MyResponse updateById2(Dept dept) {
  log.info("========DeptServiceImpl============updateById2===========");
  MyResponse result = new MyResponse();
  // 保证部门编号唯一
  List<Dept> list = deptMapper.getDeptNoUnique(dept);
  if(list==null || list.size()==0){
   //编号是唯一的，可以修改
   int count = deptMapper.updateById2(dept);
   if(count==1){
    result.setMsg("该部门修改成功，重新加载数据");
    result.setSuccess(true);
   }else{
    result.setMsg("该部门修改失败，重新加载数据");
   }
  }else{
   result.setMsg("该部门已经被使用，不能修改部门");
  }
  return result;
 }
}
