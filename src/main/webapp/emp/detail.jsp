<%--
 * 员工管理 - 详情页面（第14章 员工管理模块）
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
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/popper.js/2.9.3/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <title>员工明细</title>
</head>

<body>
<h1>员工详情如下：</h1>
<table class="table table-hover table-striped table-bordered">
    <thead>
        <tr><th>属性</th><th>数据</th></tr>
    </thead>
    <tbody>
        <tr><td>序号</td><td>${emp.empId}</td></tr>
        <tr><td>员工编号</td><td>${emp.empNo}</td></tr>
        <tr><td>员工姓名</td><td>${emp.empName}</td></tr>
        <tr><td>所属部门</td><td>${emp.deptName}</td></tr>
        <tr><td>性别</td><td>${emp.empSex}</td></tr>
        <tr><td>学历</td><td>${emp.empEducation}</td></tr>
        <tr><td>邮箱</td><td>${emp.empEmail}</td></tr>
        <tr><td>联系电话</td><td>${emp.empPhone}</td></tr>
        <tr><td>入职时间</td><td><fmt:formatDate value="${emp.empEntryTime}" pattern="yyyy-MM-dd"/></td></tr>
        <tr><td>创建时间</td><td><fmt:formatDate value="${emp.empCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
    </tbody>
</table>
<br>
<a href="javascript:window.history.back();" target="contentPage"><input type="button" value="返回"></a>
</body>
</html>
