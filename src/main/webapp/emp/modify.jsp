<%--
 * 员工管理 - 修改页面（第14章 员工管理模块）
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
    <title>员工编辑</title>

    <style type="text/css">
        body,div,table,tr,td{ margin: 0px; padding: 0px; }
        #empEditTable{
            font-size: 15px;
            border-collapse: collapse;
            width: 380px;
            margin: 20px auto;
        }
        #empEditTable td{ height: 40px; }
    </style>
</head>

<body>
<h1>修改员工信息</h1>
<form action="/emp/doModify.do" method="post">
    <table id="empEditTable">
        <tr>
            <td>员工编号:</td>
            <td><input type="text" name="empNo" id="empNo" value="${emp.empNo}" required/></td>
        </tr>
        <tr>
            <td>员工姓名:</td>
            <td><input type="text" name="empName" id="empName" value="${emp.empName}" required/></td>
        </tr>
        <tr>
            <td>所属部门:</td>
            <td>
                <select name="empDeptId" id="empDeptId">
                    <c:forEach items="${deptList}" var="dept">
                        <option value="${dept.deptId}" ${dept.deptId == emp.empDeptId ? 'selected' : ''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
                <input type="radio" name="empSex" value="男" ${emp.empSex == '男' ? 'checked' : ''}/>男
                <input type="radio" name="empSex" value="女" ${emp.empSex == '女' ? 'checked' : ''}/>女
            </td>
        </tr>
        <tr>
            <td>学历:</td>
            <td>
                <select name="empEducation" id="empEducation">
                    <option value="大专" ${emp.empEducation == '大专' ? 'selected' : ''}>大专</option>
                    <option value="本科" ${emp.empEducation == '本科' ? 'selected' : ''}>本科</option>
                    <option value="硕士" ${emp.empEducation == '硕士' ? 'selected' : ''}>硕士</option>
                    <option value="博士" ${emp.empEducation == '博士' ? 'selected' : ''}>博士</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><input type="text" name="empEmail" id="empEmail" value="${emp.empEmail}"/></td>
        </tr>
        <tr>
            <td>联系电话:</td>
            <td><input type="text" name="empPhone" id="empPhone" value="${emp.empPhone}"/></td>
        </tr>
        <tr>
            <td>入职时间:</td>
            <td><input type="date" name="empEntryTime" id="empEntryTime" value="<fmt:formatDate value='${emp.empEntryTime}' pattern='yyyy-MM-dd'/>"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="empId" id="empId" value="${emp.empId}"/>
                <input type="submit" value="修改"/>
                <input type="reset" value="重置"/>
                <a href="javascript:window.history.back();" target="contentPage"><input type="button" value="返回"></a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
