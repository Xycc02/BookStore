<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >
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
</head>
<body>
		<div id="header">
			<%@ include file="/pages/common_pages/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="/admin/bookServlet" method="get">
<%--				添加图书当前页--%>
				<input type="hidden" name="addCurrentPage" value="${param.addCurrentPage}">
<%--	删除图书当前页--%>
				<input type="hidden" name="delCurrentPage" value="${param.delCurrentPage}">
	<%--	修改图书当前页--%>
	<input type="hidden" name="updateCurrentPage" value="${param.updateCurrentPage}">
				<%--获取从BookServlet中的getBook()中请求转发过来的要修改图书的id--%>
				<input type="hidden" name="id" value="${requestScope.book.id}">
<%--					获取到图书的id之后，通过param.id是否为空( 此处param.id等价于在服务器端的req.getParamter("id") )--%>
<%--					为空：即没有从客户端拿到id的值，即为添加图书;非空：即为在客户端拿到了id的值，即为修改图书--%>
				<input type="hidden" name="action" value="${empty param.id ? "addBook" : "updateBook"}">
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
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>