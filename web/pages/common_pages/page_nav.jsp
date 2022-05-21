<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 徐宇超
  Date: 2022/4/8
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page_nav">
    <%--大于首页才显示‘首页’和‘上一页’--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
    </c:if>
    【${requestScope.page.pageNo}】
    <%--如果已经是最后一页，则不显示下一页和末页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.booksCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="gotoPageNum"/>页
    <input id="confirmBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            $('#confirmBtn').on('click',function () {
                // js有一个地址栏对象location，里面有一个属性href，表示浏览器地址栏地址，并且可读可写
                // alert(location.href);
                let val = $('#gotoPageNum').val();
                if(val < 1 || val > ${requestScope.page.pageTotal}) {
                    alert('请输入正确的页码!');
                    return;
                }
                location.href = 'http://localhost:8080${requestScope.page.url}&pageNo=' + val;
            });
        });
    </script>
</div>
</body>
</html>
