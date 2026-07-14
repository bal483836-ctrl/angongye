package com.angongye.filter;

import com.angongye.utils.ReaderXml;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebFilter(value = {"*.do","*.action"})
@Slf4j
public class LoginFilter implements Filter {

    List<String> urls=null;
    //初始化方法，只执行一次，用于初始化数据
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls= ReaderXml.getList();//加载数据

    }

    //每次请求服务器时，都会经过的方法
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest)request;
//        HttpServletResponse resp = (HttpServletResponse)response;
//        String url = req.getRequestURI();
//        log.info("=================url:"+url);
//
//        // 哪些url要进行登录验证，login/login.do, login/register.do, login/captcha.do ,login/toRegister.do
//        if("/login/register.do".equals(url)
//                || "/login/login.do".equals(url)
//                || "/login/captcha.do".equals(url)){
//            //不用验证
//
//        }else {
//            //要验证，判断是否已经登录
//            Object login = req.getSession().getAttribute("login");
//            System.out.println("login:" + login);
//            if (login == null) {
//                //没有登录，跳转到到登录页面
//                resp.setCharacterEncoding("utf-8");
//                resp.setContentType("text/html;charset=utf8");
//                PrintWriter writer = resp.getWriter();
//                writer.write("<script>");
//                writer.write("alert('您尚未登录，即将跳转到登录页面');");
//                writer.write("window.location.href='/login.jsp';");
//                writer.write("</script>");
//                writer.flush();
//                writer.close();
//            }
//        }
//        //通过这个过滤器，
//        chain.doFilter(req,resp);// 调用下一个过滤器
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String url = req.getRequestURI();
        log.info("=================url:"+url);

        // 哪些url要进行登录验证，login/login.do, login/register.do, login/captcha.do ,login/toRegister.do
        if(urls.contains(url)){
            //不用验证

        }else {
            //要验证，判断是否已经登录
            Object login = req.getSession().getAttribute("login");
            System.out.println("login:" + login);
            if (login == null) {
                //没有登录，跳转到到登录页面
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("text/html;charset=utf8");
                PrintWriter writer = resp.getWriter();
                writer.write("<script>");
                writer.write("alert('您尚未登录，即将跳转到登录页面');");
                writer.write("window.location.href='/login.jsp';");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
        }
        //通过这个过滤器，
        chain.doFilter(req,resp);// 调用下一个过滤器
    }


    // 销毁方法，只执行一次，
    @Override
    public void destroy() {

    }
}
