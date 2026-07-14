package com.angongye.controller;

import com.angongye.entity.Dept;
import com.angongye.entity.Emp;
import com.angongye.module.MyResponse;
import com.angongye.service.HrmService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller//表示该类是一个控制器，可以处理用户发出的请求
@RequestMapping("/emp")//确定要处理的url前缀
@Slf4j //日志记录，方便进行bug的跟踪
public class EmpController {
    @Autowired
    HrmService hrmService;//业务逻辑门面组件，封装了 EmployeeDao、DeptDao

    /**
     * 解决表单中日期字符串（yyyy-MM-dd）无法自动绑定到 Emp 的 Date 属性的问题。
     * SpringMVC 在数据绑定前会调用被 @InitBinder 标注的方法注册自定义编辑器。
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        // 第二个参数 true 表示允许字段为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    // ========================= 员工管理 增删改查 =========================

    // 员工列表（带分页）  /emp/list.do
    @RequestMapping("/list")
    public String getList(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,//当前页
            @RequestParam(value = "size", defaultValue = "4") int size,//每页记录数
            HttpServletRequest request) {
        log.info("=============EmpController=============getList===========");
        PageHelper.startPage(pageNum, size);//配置分页
        List<Emp> all = hrmService.findAllEmployee();
        PageInfo<Emp> pageInfo = new PageInfo<>(all);

        //构建分页条（与部门管理保持一致的风格）
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<ul class=\"pagination\">");
        for (int i = 1; i <= pageInfo.getPages(); i++) {
            if (pageNum == i) {
                stringBuffer.append("<li class=\"page-item active\"><a class=\"page-link\" href=\"/emp/list.do?pageNum=" + i + "\">" + i + "</a></li>");
            } else {
                stringBuffer.append("<li class=\"page-item\"><a class=\"page-link\" href=\"/emp/list.do?pageNum=" + i + "\">" + i + "</a></li>");
            }
        }
        stringBuffer.append("</ul>");

        request.setAttribute("page", pageInfo);
        request.setAttribute("str", stringBuffer.toString());
        return "emp/list";
    }

    // 员工详情  /emp/detail.do?id=1
    @RequestMapping("/detail")
    public String detail(@RequestParam("id") int id, HttpServletRequest request) {
        log.info("============EmpController==============detail===========");
        Emp emp = hrmService.findEmployeeById(id);
        request.setAttribute("emp", emp);
        return "emp/detail";
    }

    // 跳转到新增页面（携带部门列表用于下拉框）  /emp/gotoAdd.do
    @RequestMapping("/gotoAdd")
    public String gotoAdd(HttpServletRequest request) {
        log.info("============EmpController==============gotoAdd===========");
        List<Dept> deptList = hrmService.findAllDept();
        request.setAttribute("deptList", deptList);
        return "emp/add";
    }

    // 新增员工  /emp/add.do
    @RequestMapping("/add")
    public void add(@ModelAttribute Emp emp,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException {
        log.info("============EmpController===============add===============");
        MyResponse save = hrmService.addEmployee(emp);
        writeScript(response, save.getMsg());
    }

    // 删除员工  /emp/deleteById.do?id=1
    @RequestMapping("/deleteById")
    public void deleteById(@RequestParam("id") int id,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        log.info("============EmpController===============deleteById===============");
        MyResponse result = hrmService.removeEmployee(id);
        writeScript(response, result.getMsg());
    }

    // 跳转到修改页面（携带员工信息与部门列表）  /emp/gotoModify.do?id=1
    @RequestMapping("/gotoModify")
    public String gotoModify(@RequestParam("id") int id,
                             HttpServletRequest request) {
        log.info("==============EmpController=============gotoModify==================");
        Emp emp = hrmService.findEmployeeById(id);
        List<Dept> deptList = hrmService.findAllDept();
        request.setAttribute("emp", emp);
        request.setAttribute("deptList", deptList);
        return "emp/modify";
    }

    // 执行修改  /emp/doModify.do
    @RequestMapping("/doModify")
    public void doModify(@ModelAttribute Emp emp,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        log.info("============EmpController===============doModify===============");
        MyResponse result = hrmService.modifyEmployee(emp);
        writeScript(response, result.getMsg());
    }

    /**
     * 统一的响应处理：弹出提示并返回员工列表页面。
     */
    private void writeScript(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('" + msg + "，返回列表页面！');");
        writer.write("window.location.href='/emp/list.do'");
        writer.write("</script>");
        writer.flush();
        writer.close();
    }
}
