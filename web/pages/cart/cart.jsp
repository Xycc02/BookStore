<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >
<script src="/static/script/jquery-1.7.2.js"></script>
</head>
<body>
<script>
	$(function() {
		$('a.deleteItem').on('click',function () {
			return confirm("你确定要删除购物车中的["+$(this).parent().parent().find('td:first').text()+"]吗?");
		});
		$('#clearCart').on('click',function () {
			return confirm("你确定要清空购物车吗?");
		});
		$('.updateCount').on('change',function () {
			//获取商品名称
			var name = $(this).parent().parent().find('td:first').text();
			//获取商品id
			var id = $(this).attr('bookId');
			//获取改变商品的数量
			var count = this.value;

			if( confirm("你确定修改  [" +name + "]  的数量为  " +count + "  吗?")) {
				//确定修改
				location.href = "http://localhost:8080/cartServlet?action=updateCount&id=" +id+ "&count=" +count;
			}else{
				//取消修改,恢复原来的数量
				this.value = this.defaultValue;
			}
		});
		$('#payment').on('click',function () {
			return confirm("你确定要支付 ${sessionScope.cart.totalPrice} 元吗?");
		});
	});
</script>
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--包含公共页面，方便维护--%>
			<%@ include file="/pages/common_pages/welcomInfo.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<c:if test="${not empty sessionScope.cart.items}">
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
			</c:if>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount" bookId="${entry.value.id}" style="width: 50px;text-align: center" type="text" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="/cartServlet?action=deleItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span" id="clearCart"><a href="/cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="/orderServlet?action=createOrder" id="payment">去结账</a></span>
			</div>
		</c:if>
		<c:if test="${empty sessionScope.cart.items}">
			<a href="/index.jsp" style="color: #39987c;font-size: 40px;margin-left: 25%">购物车空空如也,去首页逛逛吧!~</a>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>