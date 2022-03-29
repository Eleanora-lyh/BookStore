<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%--    <link type="text/css" rel="stylesheet" href="../../static/css/style.css">--%>
    <%@include file="/pages/commen/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/pages/commen/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <%--我的订单不为空场合--%>
        <c:if test="${not empty requestScope.orderList}">
            <c:forEach items="${requestScope.orderList}" var="order">
                <tr>
                    <td>${order.createTime}</td>
                    <td>${order.price}</td>
                    <td>
                        <c:choose>
     <%--                       <c:when test="${order.status == 0}">
                                <a class="payClass" href="orderServlet?action=payOrder&orderId=${order.orderId}">待支付</a>
                            </c:when>--%>
                            <c:when test="${order.status == 0}">
                                待发货
                            </c:when>
                            <c:when test="${order.status == 1}">
                                待收货，
                                <a class="signClass"
                                   href="orderServlet?action=receiveOrder&orderId=${order.orderId}">点击以签收</a>
                            </c:when>
                            <c:when test="${order.status == 2}">
                                已签收
                            </c:when>
                        </c:choose>
                    </td>
                    <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.orderList}">
            <tr>
                <td colspan="4"><a href="pages/cart/cart.jsp">您暂时还没有任何的订单信息，快去购物车进行结账吧！</a></td>
            </tr>
        </c:if>
    </table>
</div>

<%--页脚--%>
<%@include file="/pages/commen/footer.jsp" %>
</body>
</html>