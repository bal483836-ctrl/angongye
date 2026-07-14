<%--
 * @author: 张绍鹏
 * @addr: 南京市
 * @tel： 137 7663 2257 张老师  （微信同号）
 * @user:Thinkpad
 * @date:2026/7/12 - 15:23
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
    <title>Title</title>
</head>
<body>
<div class="container mt-3">
    <h3>模态框实例</h3>
    <p>点击按钮打开模态框</p>

    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
        打开模态框
    </button>
</div>

<!-- 模态框 -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">模态框标题</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- 模态框内容 -->
            <div class="modal-body">
                模态框内容..
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" onclick="submit()" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>
<script>
    function submit(){
        alert("提交数据");
        // 使用vue实现较为简单，如果使用js+dom就比较慢了。
        // 通过表单元素的onChange事件对应的方法，获得文本框的数据，放在对应的变量中，统一提交。
        //
        // 这里可以使用ajax方式提交数据，如何刷新表格数据？
        // 通过js操作dom，动态的处理表格的内容。

    }
</script>

</body>
</html>