<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<link type="text/css" rel="stylesheet" href="/static/css/style.css" >
<script src="../../static/script/jquery-1.7.2.js"></script>
</head>

<script type="text/javascript">
	$(function(){
		$("a.loginOut").click(function () {
			return confirm("你确定要注销吗?");
		});
		$('.addToCart').on('click',function () {
			var bookId = $(this).attr('bookId');
			location.href = 'http://localhost:8080/cartServlet?action=addItem&id=' + bookId;
		});
	});
</script>
<body>
	
	<div id="header">
			<span class="wel_word">网上书城</span>
			<div>
				<!--如果用户还没有登陆，即sessionScope.username为空，则显示登录注册菜单-->
				<c:if test="${empty sessionScope.username}">
					<span>请先登录或注册</span>
					<a href="/pages/user/login.jsp">登录</a> |
					<a href="/pages/user/regist.jsp">注册</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty sessionScope.username}">
					<span>欢迎登录<span class="um_span">${sessionScope.username}</span></span>
					<a class="loginOut" href="/userServlet?action=loginOut">注销</a>
					<a href="/pages/cart/cart.jsp">购物车</a>
					<a href="/pages/cart/checkout.jsp">我的订单</a>
					<a href="/pages/manager/manager.jsp">后台管理</a>
				</c:if>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="/client/clientBookServlet" method="get">
<%--					设置隐藏域--%>
					<input type="hidden" name="action" value="queryPrice">
					价格：<input id="min" type="text" name="minPrice" value="${param.minPrice}"> 元 -
						<input id="max" type="text" name="maxPrice" value="${param.maxPrice}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<span style="color: #39987c;font-size: 24px">您的购物车空空如也~</span>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span style="color: red">${sessionScope.book.name}</span>加入到了购物车中
					</div>
				</c:if>
			</div>
			<%--使用jstl表达式对client每个图书信息进行遍历--%>
			<c:forEach items="${requestScope.page.items}" var="book">

				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button class="addToCart" bookId="${book.id}">加入购物车</button>
						</div>
					</div>
				</div>

			</c:forEach>


		</div>
		<%--底部分页栏--%>
		<jsp:include page="/pages/common_pages/page_nav.jsp"></jsp:include>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>