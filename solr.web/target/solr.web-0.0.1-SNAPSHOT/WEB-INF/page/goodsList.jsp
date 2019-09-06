<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
 <body>
<a href="${pageContext.request.contextPath}/goods/add">添加商品</a>
<form id="searchForm" action="<%=path%>/goods/list" method="post">
	<table width="80%" border="1" cellspacing="0" cellpadding="5">
  <tr>
    <td>
    	标题：<input type="text" name="name" id="name" value="${goodsQuery.name}" />
    </td>
    <td><input type="submit" name="sbt" id="sbt" value="搜索" /></td>
  </tr>
</table>
</form>

<table width="80%" border="1" cellspacing="0" cellpadding="5">
  <tr>
    <td>编号</td>
    <td>名称</td>
    <td>价格</td>
    <td>状态</td>
    <td>类型</td>
    <td>图片</td>
    <td>操作</td>
  </tr>
  <c:forEach items="${goodsList}" var="goods">
  <tr>
    <td>${goods.id }</td>
    <td>${goods.name }</td>
    <td>${goods.price}</td>
    <td>${goods.status}</td>
    <td>${goods.type}</td>
    <td><img src="${goods.img}" /></td>
    <td><a href="${pageContext.request.contextPath}/goods/delete?id=${goods.id }">删除</a></td>
  </tr>
 </c:forEach>
</table>
</body>
</html>
