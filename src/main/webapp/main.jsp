<%--
 * @author: 张绍鹏
 * @addr: 南京市
 * @tel： 137 7663 2257 张老师  （微信同号）
 * @user:Thinkpad
 * @date:2026/7/13 - 8:49
 * @projectName:angongye
 * 
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 新 Bootstrap5 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/css/bootstrap.min.css">
    <!--  popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.staticfile.org/popper.js/2.9.3/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap5 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <!-- 引入图标库  -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <title>main.html</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>

    <style type="text/css">
        .hide{
            display: none;

        }
    </style>
    <script type="text/javascript">
        $(function(){
            
            $("li[class='menu'] span").each(function(){
                $(this).click(function(){
                    //this代表的是span
                    $(this).siblings(".hide").slideToggle();

                });

            });

        });

    </script>


</head>

<body>
<div id = "mainDiv">
    <div id = "header">
        <div id = "logoDiv" class="lft">
            南京某某OA教育集团
        </div>
        <div id = "userDiv" class="rft">
<%--    key.属性名        --%>
            ${emp.empName}
        </div>
    </div>
    <div id = "welcomeDiv">
        欢迎使用某某OA管理系统
    </div>


    <div id = "contentDiv">
        <div id = "content-left" class="lft">
            <ul>

                <li class="menu">
                    <span>人事管理</span>
                    <ul class="hide">
                        <li class="menu-sub" ><a href="/dept/list.do"  id="deptManager"   target="contentPage">部门管理</a></li>
                        <li class="menu-sub"><a href="/emp/list.do" id="empManager" target="contentPage">员工管理</a></li>
                        <li class="menu-sub">请假管理</li>
                    </ul>

                </li>

                <li class="menu">
                    <span>财务管理</span>
                    <ul  class="hide">
                        <li class="menu-sub">报销管理</li>
                    </ul>

                </li>
                <li class="menu">
                    <span>系统管理</span>
                    <ul class="hide">
                        <li class="menu-sub">账户维护</li>
                        <li class="menu-sub">角色管理</li>
                        <li class="menu-sub">权限管理</li>
                        <li class="menu-sub">密码重置</li>
<%--
功能是 实现了,但是没有实现提示功能
<li class="menu-sub"><a href="/login/logOut.do">系统退出</a></li>
 --%>
                        <li class="menu-sub"><a href="javascript:myExit();">系统退出</a></li>
                    </ul>

                </li>

            </ul>


        </div>

        <div id = "content-right" class="rft">
            <iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
            </iframe>
        </div>
    </div>

    <div id = "footer">
        <span>&copy;版权归属南京某某OA江北总部</span>
    </div>

</div>
<script>
    function  myExit(){
        if (confirm("是否退出本系统?")){
            window.location.href="/login/logOut.do";
        }
    }
</script>
</body>
</html>
