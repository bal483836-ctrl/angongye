<%--
 * @author: 张绍鹏
 * @addr: 南京市
 * @tel： 137 7663 2257 张老师  （微信同号）
 * @user:Thinkpad
 * @date:2026/7/12 - 14:32
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
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/login.css">

    <style type="text/css">
        body{
            background-color: #0070A2;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/app.css">
</head>

<body>
<div id = "login">
    <div id = "title">
        某某OA管理系统
    </div>
    <div style="border:1px solid red; width:400px;height:30px; line-height:30px;" id="message">${msg}</div>
    <form action="/login/login.do" method="post">
        <table id="loginTable" style="margin-top:0px;">
            <tr>
                <td>用户名:&nbsp;</td>
                <td>
                    <input type= "text" name = "userName" id = "userName" value="zhangsan"/>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
                <td>
                    <input type= "password" name = "password" id = "password" value="123456"/>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr >
                <td>验证码:&nbsp;</td>
                <td>
                    <input type= "text" name = "code" id = "code" style="width:80px;"/>
                    <img id="imgShow" src="/login/captcha.do" onclick="flushCode()" width="130" height="35" align="middle"/>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td colspan="2">
                    <input type= "submit" value="登&nbsp;录" class="btn"/>
                    <a href="/login/register.do" class="btn btn-primary ">注册</a>
                </td>
            </tr>

        </table>
    </form>
</div>
<script>
    function flushCode(){
      var img = document.getElementById("imgShow");
      var date = new Date();
      img.src="/login/captcha.do?time="+date.getTime();
    }

</script>
</body>
</html>
