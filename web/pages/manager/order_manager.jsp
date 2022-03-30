<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
    <%@include file="/pages/commen/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%@include file="/pages/commen/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>物流状态</td>
        </tr>
        <c:if test="${not empty requestScope.allOrder}">
            <c:forEach items="${requestScope.allOrder}" var="order">
                <%--测试当前获取的order的status状态--%>
                ${order.status}
                <tr><%--fn:substring(order.createTime,0,10)--%>
                    <td>${order.createTime}</td>
                    <td>${order.price}</td>
                    <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                    <td>

                        <c:choose>
                            <%--待发货，显示超链接--%>
                            <c:when test="${order.status == 0}">
                                <a href="orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a>
                            </c:when>
                            <%--其他物流状态，显示文字--%>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${order.status == 1}">
                                        已发货
                                    </c:when>
                                    <c:when test="${order.status == 2}">
                                        已签收
                                    </c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <%--        <c:if test="${empty requestScope.allOrder}">
                    <tr>
                        <td colspan="4"><a href="pages/manager/manager.jsp">目前暂时没有订单需要处理！点击返回管理主页</a></td>
                    </tr>
                </c:if>--%>
    </table>
</div>

<%--页脚--%>
<%@include file="/pages/commen/footer.jsp" %>
</body>
</html>