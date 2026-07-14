package com.angongye.controller;

import com.angongye.entity.Emp;
import com.angongye.entity.Login;
import com.angongye.module.MyResponse;
import com.angongye.service.HrmService;
import com.angongye.service.LoginService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    HrmService hrmService;//员工的业务逻辑（门面）

    //验证登录
    // /login/login.do
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("code") String verCode, //输入的验证码信息
            HttpServletRequest request){
        log.info("=============LoginController=============login=======================");
        // 获取session中的验证码
        String sessionCode = request.getSession().getAttribute("captcha").toString();
        System.out.println("sessionCode:"+sessionCode);//保存的验证码信息
        System.out.println("verCode:"+verCode);
        // 判断验证码
        if (verCode==null || !sessionCode.equals(verCode.trim().toLowerCase())) {
            request.setAttribute("msg","验证码不正确");
            //跳转到登录页面 "配置的前缀"+aaa/bbb+"配置的后缀"  ==>   /aaa/bbb.jsp
            //return "aaa/bbb";
            return "login";
        }else{
            //验证码正确，再判断用户名与密码,
            //封装数据
            Login login = new Login();
            login.setLoginName(userName).setLoginPwd(password);

            MyResponse result = loginService.login(login);
            if(result.getSuccess()){
                //登录成功，跳转到主页面
                Login login1 = (Login) result.getTag();
                request.getSession().setAttribute("login",login1);//保存登录信息
                //获得员工信息

                Emp emp1 = hrmService.findEmployeeByLoginId(login1.getLoginId());
                request.setAttribute("emp",emp1);//把数据保存到request中，
                // request.getAttribute("emp");  // 如果从request获得数据，需要强转
                //跳转到登录页面 "配置的前缀"+main+"配置的后缀"  ==>   /main.jsp
                return "main";
            }else{
                //登录失败，跳转到登录页面
                request.setAttribute("msg",result.getMsg());
                return "login";
            }

        }
    }

    // /login/captcha.do  生成验证码
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 1);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }

    // /register 跳转到注册页面

    @RequestMapping("/register")
    public String gotoRegister(HttpServletRequest request){
        log.info("=============LoginController=============gotoRegister=======================");
       //读取数据（没有登录账号的员工信息）
        List<Emp> list = hrmService.findEmployeesWithoutAccount();
        //如果有员工还没有登录账号，可以跳转到注册页面，
        // 如果所有员工都有登录账号，跳转到登录页面
        if(list==null || list.size()==0){
            //提供消息
            request.setAttribute("msg","所有员工已经都有了账号，不需要注册");
            return "login";
        }
        request.setAttribute("list",list);
        return "register";
    }

    // 注册功能  toRegister
    @RequestMapping("/toRegister")
    public void register(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("empId") int empId, //选中的员工编号
            HttpServletRequest request,
            HttpServletResponse response) throws IOException { //返回输出流，（返回响应内容）
        log.info("=============LoginController=============register=======================");
        MyResponse result = loginService.register(userName, password, empId);
        // 使用response 进行提示并跳转
        response.setCharacterEncoding("utf-8");//设置字符集
        response.setContentType("text/html;charset=utf8");//设置返回的内容，是文本网页
        PrintWriter writer = response.getWriter();
        writer.write("<script>");

        if(result.getSuccess()){
            //成功，跳转到登录
            writer.write("alert('"+result.getMsg()+"');");
            writer.write("window.location.href='/login.jsp';");
        }else{
            //失败，跳转到注册
            writer.write("alert('"+result.getMsg()+"');");
            writer.write("window.location.href='/login/register.do';");
        }
        writer.write("</script>");
        //关闭输出流，
        writer.flush();//清缓存
        writer.close();//关闭
    }

    //退出
    @RequestMapping("/logOut")
    public void logOut(HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.info("=======LoginController============logOut=============");
        request.getSession().removeAttribute("login");//删除session保存的信息
        request.getSession().invalidate();//session失效

        // 使用response 进行提示并跳转
        response.setCharacterEncoding("utf-8");//设置字符集
        response.setContentType("text/html;charset=utf8");//设置返回的内容，是文本网页
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('成功退出系统，即将跳转到登录页面，欢迎再次使用！');");
        writer.write("window.top.location.href='/login.jsp';");//跳转到整个浏览器窗口中

        writer.write("</script>");
        //关闭输出流，
        writer.flush();//清缓存
        writer.close();//关闭

    }

}
