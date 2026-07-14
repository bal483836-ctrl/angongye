<%--
 * 员工管理 - 列表页面（第14章 员工管理模块）
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
    <!-- Bootstrap5 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/popper.js/2.9.3/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <title>员工管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">

    <script type="text/javascript">
        function del(id){
            var result = window.confirm("确认要删除该员工吗?");
            if(true == result){
                window.location.href = '/emp/deleteById.do?id='+id;
            }
        }
    </script>
</head>

<body>
<h1 class="title">首页  &gt;&gt;员工管理 </h1>

<div class="add">
    <a href="/emp/gotoAdd.do" target="contentPage"><img alt="" src="<%=basePath %>img/add.png" width="18px" height="18px">添加员工</a>
</div>

<table class="deptInfo">
    <tr class="titleRow">
        <td>员工编号</td>
        <td>姓名</td>
        <td>所属部门</td>
        <td>性别</td>
        <td>学历</td>
        <td>邮箱</td>
        <td>联系电话</td>
        <td>入职时间</td>
        <td>操作列表</td>
    </tr>
    <c:forEach items="${page.list}" var="emp">
        <tr>
            <td>${emp.empNo}</td>
            <td>${emp.empName}</td>
            <td>${emp.deptName}</td>
            <td>${emp.empSex}</td>
            <td>${emp.empEducation}</td>
            <td>${emp.empEmail}</td>
            <td>${emp.empPhone}</td>
            <td><fmt:formatDate value="${emp.empEntryTime}" pattern="yyyy-MM-dd"/></td>
            <td>
                <img alt="删除" title="删除" src="<%=basePath %>img/delete.png" class="operateImg" onclick="del(${emp.empId})">
                <a href="/emp/gotoModify.do?id=${emp.empId}" target="contentPage"><img alt="修改" title="修改" src="<%=basePath %>img/edit.png" class="operateImg"></a>
                <a href="/emp/detail.do?id=${emp.empId}" target="contentPage"><img alt="详情" title="详情" src="<%=basePath %>img/detail.png" class="operateImg"></a>
            </td>
        </tr>
    </c:forEach>
    <tr><td colspan="9">
        <!-- 分页 -->
        <a href="/emp/list.do">首页</a> &nbsp; &nbsp;
        <c:if test="${page.pageNum>1}" var="prev">
            <a href="/emp/list.do?pageNum=${page.pageNum-1}">上一页</a> &nbsp; &nbsp;
        </c:if>
        <c:if test="${!prev}">
            <a href="/emp/list.do?pageNum=1">上一页</a> &nbsp; &nbsp;
        </c:if>

        <c:if test="${page.pageNum < page.pages}" var="next">
            <a href="/emp/list.do?pageNum=${page.pageNum+1}">下一页</a> &nbsp; &nbsp;
        </c:if>
        <c:if test="${!next}">
            <a href="/emp/list.do?pageNum=${page.pages}">下一页</a> &nbsp; &nbsp;
        </c:if>
        <a href="/emp/list.do?pageNum=${page.pages}">末页</a> &nbsp; &nbsp;
    </td></tr>
    <tr><td colspan="9">
        ${str}
    </td></tr>
</table>
</body>
</html>
