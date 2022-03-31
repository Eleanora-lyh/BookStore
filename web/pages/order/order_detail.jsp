<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huawei
  Date: 2022/3/28
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的订单</title>
    <%@ include file="/pages/commen/head.jsp" %>
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
    <span class="wel_word">订单详情</span>
    <%@ include file="/pages/commen/login_success_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>
        <%--没有订单项就没有查看详情，所以能进入此页面说明都是有订单的情况，那么不需要对没有订单的情况进行特判--%>
        <c:forEach items="${requestScope.orderItemList}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>

    <%--
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="order_info">
            <span class="order_span">购物车中共有<span class="b_count"></span>件商品</span>
            <span class="order_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
        </div>
    </c:if>
    --%>
</div>

<%@ include file="/pages/commen/footer.jsp" %>
</body>
</html>
