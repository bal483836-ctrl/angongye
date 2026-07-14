<%--
 * @author: 张绍鹏
 * @addr: 南京市
 * @tel： 137 7663 2257 张老师  （微信同号）
 * @user:Thinkpad
 * @date:2026/7/13 - 15:16
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
    <title>部门编辑</title>

    <style type="text/css">
        body,div,table,tr,td{
            margin: 0px;
            padding: 0px;
        }

        #deptEditTable{
            font-size: 15px;
            border-collapse: collapse;
            width: 350px;
            margin: 20px auto;


        }

        #deptEditTable td{
            height: 40px;
        }

    </style>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/app.css">
</head>

<body>
<h1>新增部门信息</h1>
<form action="/dept/add.do" method="post">

    <table id = "deptEditTable">
        <tr>
            <td>
                部门编号:
            </td>
            <td>
                <input type = "text" name="deptNo" id="deptNo"/>
            </td>
        </tr>
        <tr>
            <td>
                部门名称:
            </td>
            <td>
                <input type = "text" name="deptName" id="deptName"/>
            </td>
        </tr>

        <tr>
            <td>
                部门位置:
            </td>
            <td>
                <input type = "text" name="deptAddress" id="deptLoc"/>
            </td>
        </tr>

        <tr>
            <td>
                部门负责人:
            </td>
            <td>
                <input type = "text" name="deptUser" id="deptMaster"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type = "submit" value="添加"/>
                <input type = "reset" value="重置"/>
                <a href="javascript:window.history.back();" target="contentPage"><input type="button" value="返回"></a>
            </td>
        </tr>
    </table>


</form>
</body>
</html>
