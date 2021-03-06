<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>编辑图书</title>
	<%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
	<%@include file="/pages/commen/head.jsp"%>
	<style type="text/css">
		h1 {
			text-align: center;
			margin-top: 200px;
		}

		h1 a {
			color:red;
		}

		input {
			text-align: center;
		}
	</style>

	<script type="text/javascript">
		$(function () { //页面加载完成后
			//输入框失去焦点判断库存的合法
			$("#stock").blur(function checkStock() {
				//1 获取库存值
				var stockCount = this.value;
				if(stockCount<=0) {
					alert("库存值必须大于0");
					return false;
				}else return true;
			});
		});
	</script>
</head>
<body>
<div id="header">
<%--	<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
	<span class="wel_word">编辑图书</span>
	<%@include file="/pages/commen/manager_menu.jsp" %>
</div>

<div id="main">
	<form action="managers/bookServlet" method="get" onsubmit="return checkStock()">
		<input type="hidden" name="pageNo" value="${param.pageNo}">
		<input type="hidden" name="action" value="${empty param.id?"add":"update"}"/>
		<input type="hidden" name="id" value="${requestScope.book.id}"/>
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<tr>
				<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
				<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
				<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
				<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
				<td><input name="stock" type="text" value="${requestScope.book.stock}" id="stock"/></td>
				<td><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>


</div>

<%--页脚--%>
<%@include file="/pages/commen/footer.jsp"%>
</body>
</html>