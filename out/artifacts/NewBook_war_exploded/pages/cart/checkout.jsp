<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算页面</title>
<%--    <link type="text/css" rel="stylesheet" href="../../static/css/style.css">--%>
    <%@include file="/pages/commen/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
<%--    <img class="logo_img" alt="" src="static/img/logo.gif">--%>
    <span class="wel_word">结算</span>
    <%--静态包含，登录 成功之后的菜单 --%>
    <%@include file="/pages/commen/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>你的订单已结算，订单号为：${sessionScope.orderId}</h1>


</div>

<%--页脚--%>
<%@include file="/pages/commen/footer.jsp"%>
</body>
</html>