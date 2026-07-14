package com.angongye.controller;

import com.angongye.entity.Dept;
import com.angongye.module.MyResponse;
import com.angongye.service.HrmService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Autowired
    HrmService hrmService;//业务逻辑门面组件，封装了各 DAO

    // /detp/list.do //后面还要加入数据分页效果
//    @RequestMapping("/list")
//    public String getList(HttpServletRequest request){
//        log.info("=============DeptController=============getList===========");
//        //    /dept/list.jsp
//        //给jsp文件提供数据
//        List<Dept> all = deptService.getAll();
//        request.setAttribute("list",all);
//        return "dept/list";
//    }

    //带分页
    @RequestMapping("/list")
    public String getList(
            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,//当前页是第几页，如果不给这个参数，默认是第一页
            @RequestParam(value = "size",defaultValue = "4") int size,//每一页显示的记录数，如果不给这个参数，默认是5条信息
            HttpServletRequest request){
        log.info("=============DeptController=============getList===========");
        //    /dept/list.jsp
        //给jsp文件提供数据
        PageHelper.startPage(pageNum,size);//配置分页内容，PageHelper.startPage(当前页是第几页, 每一页显示是记录数);
        //获得所有的内容，
        List<Dept> all = hrmService.findAllDept();
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<>(all);
        pageInfo.getList();//获得分页的数据${page.list}
        pageInfo.getPages();//一共多少页 ${page.pages}
        pageInfo.getPageNum();//当前页 ${page.pageNum}
        pageInfo.getTotal();//总的记录数

        pageInfo.getSize();//实际条数？？
        pageInfo.getPageSize();//理论条数？？

        StringBuffer stringBuffer = new StringBuffer();
        //构建分页代码
        stringBuffer.append("<ul class=\"pagination\">");

        for(int i=1;i<=pageInfo.getPages();i++){
            if(pageNum==i){
                stringBuffer.append("<li class=\"page-item active\"><a class=\"page-link\" href=\"/dept/list.do?pageNum="+i+"\">"+i+"</a></li>");
            }else{
                stringBuffer.append("<li class=\"page-item\"><a class=\"page-link\" href=\"/dept/list.do?pageNum="+i+"\">"+i+"</a></li>");
            }
        }

        stringBuffer.append("</ul>");
        request.setAttribute("page",pageInfo);
        request.setAttribute("str",stringBuffer.toString());

        return "dept/list";
    }




    //通过Id获得部门信息
    // /detp/detail.do?id=${dept.deptId}
    @RequestMapping("/detail")
    public String detail(@RequestParam("id") int id,HttpServletRequest request){
        log.info("============DeptController==============detail===========");
        //通过Id到数据库中查询部门信息
        Dept dept = hrmService.findDeptById(id);
        request.setAttribute("dept",dept);
        return "dept/detail";
    }

    // /dept/add.do
    @RequestMapping("/add")
    public void add(@ModelAttribute Dept dept,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException {
        log.info("============DeptController===============add===============");
        //（部门编号唯一），如果该部门编号不存在，可以保存，保存数据
        //给业务处理这项工作
        MyResponse save = hrmService.addDept(dept);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('"+save.getMsg()+",返回列表页面！');");

        writer.write("window.location.href='/dept/list.do'");

        writer.write("</script>");

        writer.flush();
        writer.close();

    }
// /dept/deleteById.do?id=1
    @RequestMapping("/deleteById")
    public void deleteById(@RequestParam("id") int id,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException {
        log.info("============DeptController===============deleteById===============");
        //（部门编号唯一），如果该部门编号不存在，可以保存，保存数据
        //给业务处理这项工作
        MyResponse save = hrmService.removeDept(id);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('"+save.getMsg()+",返回列表页面！');");

        writer.write("window.location.href='/dept/list.do'");

        writer.write("</script>");

        writer.flush();
        writer.close();

    }

    // 跳转到修改页面，/gotoModify
    @RequestMapping("/gotoModify")
    public String gotoModify(@RequestParam("id") int id,
                             HttpServletRequest request){
        log.info("==============DeptController=============gotoModify==================");
        //通过id查找部门信息
        Dept dept = hrmService.findDeptById(id);
        request.setAttribute("dept",dept);//把数据放到request中，在页面中使用
        // 跳转到对应的页面上
        return "dept/modify";
    }

    // /doModify 修改功能
    @RequestMapping("/doModify")
    public void doModify(@ModelAttribute Dept dept,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        log.info("============DeptController===============doModify===============");
        MyResponse result = hrmService.modifyDept(dept);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf8");
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('"+result.getMsg()+",返回列表页面！');");

        writer.write("window.location.href='/dept/list.do'");

        writer.write("</script>");

        writer.flush();
        writer.close();

    }

}














