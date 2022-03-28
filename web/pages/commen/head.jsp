<%--
  Created by IntelliJ IDEA.
  User: huawei
  Date: 2022/3/22
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--base标签永远固定，想对路径的跳转结果，写到工程路径表示当前是web文件夹-->
<%
    String basepath = request.getScheme() //获取当前的协议 ：http
            + "://"
            + request.getServerName() // localhost
            + ":"
            + request.getServerPort() //服务器端口号
            + request.getContextPath() // /book
            + "/";
    pageContext.setAttribute("basePath", basepath);
%>
<base href="<%= basepath %>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
