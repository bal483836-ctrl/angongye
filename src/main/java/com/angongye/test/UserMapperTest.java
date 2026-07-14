package com.angongye.test;


import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import com.angongye.mapper.DeptMapper;
import com.angongye.mapper.EmpMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// 指定Spring测试运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 加载Spring核心配置文件
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@Slf4j //打印日志
public class UserMapperTest {

 @Autowired
 DeptMapper deptMapper;

 @Autowired
 EmpMapper empMapper;

 @Test
 public void test001() throws InterruptedException {
  System.out.println("hello world");

  Thread.sleep(10000); //睡眠10s
 }

 @Test
 public void test002() throws InterruptedException {
  List<Dept> deptAll = deptMapper.getDeptAll();  //调用接口中的方法
  deptAll.forEach(System.out::println); //打印

  Thread.sleep(10000);
 }

 @Test
 public void test003() throws InterruptedException {
  Dept deptById = deptMapper.getDeptById(3);
  System.out.println(deptById);

  Thread.sleep(10000);
 }

 @Test
 public void test004() throws InterruptedException {
  Dept deptById = deptMapper.getDeptByNo("dept001");
  System.out.println(deptById);

  Thread.sleep(10000);
 }

 @Test
 public void test005() throws InterruptedException {
  HashMap<String,Object> map =   new HashMap<>();
  map.put("no","dept001");
  map.put("myId",1);
  List<Dept> deptAll = deptMapper.getDeptByCondition(map);  //调用接口中的方法
  deptAll.forEach(System.out::println); //打印

  Thread.sleep(10000);
 }

 @Test
 public void test006() throws InterruptedException {
  Dept dept = new Dept();
  //链式编程
  dept.setDeptAddress("aaa");
  dept.setDeptName("bbb");


  dept.setDeptUser("张三")
          .setDeptAddress("bb")
          .setDeptId(10)
          .setDeptNo("dept002");


  List<Dept> deptAll = deptMapper.getDeptByObj(dept);  //调用接口中的方法
  deptAll.forEach(System.out::println); //打印

  Thread.sleep(10000);
 }

 @Test
 public void test007() throws InterruptedException {

  List<Dept> deptAll = deptMapper.getDeptGreateThanId(5);  //调用接口中的方法
  deptAll.forEach(System.out::println); //打印

  Thread.sleep(10000);
 }
 @Test
 public void test008() throws InterruptedException {

  List<Dept> deptAll = deptMapper.getDeptLessThanId(8);  //调用接口中的方法
  deptAll.forEach(System.out::println); //打印

  Thread.sleep(10000);
 }

 @Test
 public void test009() throws InterruptedException {

  List<Dept> deptAll = deptMapper.getDeptByName2("class");  //调用接口中的方法
  deptAll.forEach(System.out::println); //打印

  Thread.sleep(10000);
 }

 @Test
 public void test010() throws InterruptedException {
 Dept dept = new Dept();
 dept.setDeptNo("jsj001")
         .setDeptName("软工01班")
         .setDeptPid(0)
         .setDeptUser("涨涨涨")
         .setDeptAddress("逸夫楼405");
 int count=deptMapper.saveDept(dept);
  System.out.println(count);
  Thread.sleep(10000);
 }

 @Test
 public void test011() throws InterruptedException {
  Dept dept = new Dept();
  dept.setDeptNo("jsj002")
          .setDeptName("软工02班")
          .setDeptPid(0)
          .setDeptUser("涨涨涨")
          .setDeptAddress("逸夫楼405");
  int count=deptMapper.saveDeptAndId(dept);
  System.out.println(dept);
  Thread.sleep(10000);
 }


 @Test
 public void test012() throws InterruptedException {
  Dept dept = new Dept();
  dept.setDeptNo("jsj002")
          .setDeptName("软工02班")
          .setDeptPid(0)
          .setDeptUser("涨涨涨")
          .setDeptAddress("逸夫楼405")
          .setDeptId(12);

  int count=deptMapper.updateById(dept);
  System.out.println(count);
  Thread.sleep(10000);
 }

 @Test
 public void test013() throws InterruptedException {

  int count=deptMapper.deleteById(12);
  Thread.sleep(10000);
 }

 @Test
 public void test014() throws InterruptedException {
  Emp emp = new Emp();
  emp.setEmpEmail("163");
  emp.setEmpName("张");
  List<Emp> list = empMapper.getEmpByCondition(emp);
  list.forEach(System.out::println);
  Thread.sleep(10000);
 }

 @Test
 public void test015() throws InterruptedException {
  Emp emp = new Emp();
  emp.setEmpEmail("163");
  emp.setEmpName("张");
  List<Emp> list = empMapper.getEmpByConditionWhere(emp);
  list.forEach(System.out::println);
  Thread.sleep(10000);
 }

 @Test
 public void test016() throws InterruptedException {
  Emp emp = new Emp();
  emp.setEmpEmail("163");
  emp.setEmpName("张");
  List<Emp> list = empMapper.getEmpByChoose(emp);
  list.forEach(System.out::println);
  Thread.sleep(10000);
 }

 @Test
 public void test017() throws InterruptedException {
  Emp emp = new Emp();
  emp.setEmpEmail("163");
  emp.setEmpName("张");
  emp.setEmpId(1);
  emp.setEmpNo("hello");
  int count = empMapper.updateBySet(emp);

  Thread.sleep(10000);
 }

 @Test
 public void test018() throws InterruptedException {
  List<Integer> list1 = new ArrayList<>();
  list1.add(1);
  list1.add(3);
  list1.add(5);
  list1.add(6);
  list1.add(7);

  List<Emp> list = empMapper.getEmpByForeach(list1);
  list.forEach(System.out::println);
  Thread.sleep(10000);
 }

 @Test
 public void test019() throws InterruptedException {
  List<Emp> list1 = new ArrayList<>();
  list1.add(new Emp(1));
  list1.add(new Emp(3));
  list1.add(new Emp(4));
  list1.add(new Emp(5));
  list1.add(new Emp(6));

  List<Emp> list = empMapper.getEmpByForeach2(list1);
  list.forEach(System.out::println);
  Thread.sleep(10000);
 }

 @Test
 public void test020() throws InterruptedException {
  List<Emp> list1 = new ArrayList<>();
  list1.add(new Emp(16,"emp_001"));
  list1.add(new Emp(17,"emp_002"));
  list1.add(new Emp(18,"emp_003"));
  list1.add(new Emp(19,"emp_004"));

  int count = empMapper.batchInsert(list1);
  System.out.println(count);
  Thread.sleep(10000);
 }
}