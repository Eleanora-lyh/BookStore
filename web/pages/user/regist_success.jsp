<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--固定的base标签、头部信息--%>
    <%@include file="/pages/commen/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word"></span>
    <%--显示登陆成功后共有的提示信息--%>
    <%@include file="/pages/commen/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>注册成功! <a href="index.html">转到主页</a></h1>

</div>

<%--页脚--%>
<%@include file="/pages/commen/footer.jsp" %>
</body>
</html>