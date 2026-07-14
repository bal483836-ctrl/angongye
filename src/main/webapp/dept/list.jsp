<%--
 * @author: 张绍鹏
 * @addr: 南京市
 * @tel： 137 7663 2257 张老师  （微信同号）
 * @user:Thinkpad
 * @date:2026/7/13 - 14:34
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
    <title>部门管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/layer/layer.js"></script>

    <style type="text/css">


    </style>

    <script type="text/javascript">

        function del(id){
            //提示信息，
            var result = window.confirm("确认要删除吗?");
            if(true == result){
                //alert("删除成功");
                window.location.href = '/dept/deleteById.do?id='+id;
            }
        }

    </script>
</head>

<body>
<h1 class="title">首页  &gt;&gt;部门管理 </h1>

<div class="add">
    <a href="/dept/add.jsp" target="contentPage"><img alt="" src="<%=basePath %>img/add.png" width="18px" height="18px">添加部门</a>
</div>

<table class="deptInfo">
    <tr class="titleRow">
        <td>部门编号</td>
        <td>部门名称</td>
        <td>部门位置</td>
        <td>部门负责人</td>
        <td>操作列表</td>
    </tr>
   <c:forEach items="${page.list}" var="dept">
       <tr>
           <td>${dept.deptNo}</td>
           <td>${dept.deptName}</td>
           <td>${dept.deptAddress}</td>
           <td>${dept.deptUser}</td>
           <td>
               <img alt="删除" title="删除" src="<%=basePath %>img/delete.png" class="operateImg" onclick="del(${dept.deptId})">
               <a href="/dept/gotoModify.do?id=${dept.deptId}" target="contentPage"><img  alt="修改" title="修改" src="<%=basePath %>img/edit.png" class="operateImg" ></a>
               <a href="/dept/detail.do?id=${dept.deptId}" target="contentPage"><img  alt="详情" title="详情" src="<%=basePath %>img/detail.png" class="operateImg"></a>
           </td>
       </tr>
   </c:forEach>
    <tr><td colspan="5">
        <!-- 分页 -->
        <a href="/dept/list.do">首页</a> &nbsp; &nbsp;
        <c:if test="${page.pageNum>1}" var="prev">
            <a href="/dept/list.do?pageNum=${page.pageNum-1}">上一页</a> &nbsp; &nbsp;
        </c:if>
        <c:if test="${!prev}">
            <a href="/dept/list.do?pageNum=1">上一页</a> &nbsp; &nbsp;
        </c:if>

        <c:if test="${page.pageNum < page.pages}" var="next">
            <a href="/dept/list.do?pageNum=${page.pageNum+1}">下一页</a> &nbsp; &nbsp;
        </c:if>
        <c:if test="${!next}">
            <a href="/dept/list.do?pageNum=${page.pages}">下一页</a> &nbsp; &nbsp;
        </c:if>
        <a href="/dept/list.do?pageNum=${page.pages}">末页</a> &nbsp; &nbsp;
    </td></tr>
    <tr><td colspan="5">
        ${str}
    </td></tr>

</table>
</body>
</html>
