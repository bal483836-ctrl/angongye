<%--
 * @author: 张绍鹏
 * @addr: 南京市
 * @tel： 137 7663 2257 张老师  （微信同号）
 * @user:Thinkpad
 * @date:2026/7/13 - 15:01
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
    <title>部门明细</title>


</head>

<body>
<h1>详情如下：</h1>
<table  class="table table-hover table-striped table-bordered ">
    <thead>
        <tr><th>属性</th><th>数据</th></tr>
    </thead>
    <tbody>
        <tr><td>序号</td><td> ${dept.deptId}</td></tr>
        <tr><td>部门编号</td><td> ${dept.deptNo}</td></tr>
        <tr><td>部门名称</td><td> ${dept.deptName}</td></tr>
        <tr><td>部门主管</td><td> ${dept.deptUser}</td></tr>
        <tr><td>部门地址</td><td> ${dept.deptAddress}</td></tr>

    </tbody>

</table>
<br>

<a href="javascript:window.history.back();" target="contentPage"><input type="button" value="返回"></a>
</body>
</html>
