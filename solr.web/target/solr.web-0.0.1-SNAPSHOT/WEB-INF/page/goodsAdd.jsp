<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<h2>添加商品</h2>
<form action="<%=path%>/goods/save" method="post" enctype="multipart/form-data">
	
<table width="50%" border="1" cellspacing="0" cellpadding="5">
	<tr>
		<td>商品编号:</td>
		<td><input type="text" name="id" id="id" value="" /></td>
	</tr>
	<tr>
		<td>商品名称:</td>
		<td>
			<input type="text" name="name" id="name" value="" />
		</td>
	</tr>
	<tr>
		<td>价格:</td>
		<td><input type="text" name="price" id="price" value="" /></td>
	</tr>
	<tr>
		<td>状态:</td>
		<td>
			<select name="status" id="status">
				<option value="">--请选择--</option>
				<option value="1">上架</option>
				<option value="2">下架</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>类型:</td>
		<td>
			<select name="type" id="type">
				<option value="">--请选择--</option>
				<option value="1">短袖T恤</option>
				<option value="2">卫衣</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>图片:</td>
		<td><input type="text" name="img" id="img" value="" /></td>
	</tr>
	<tr>
		<td>描述:</td>
		<td><input type="text" name="describle" id="describle" value="" /></td>
	</tr>
	<tr align="center">
		<td colspan="2"><input type="submit" name="button2" id="button2" value="保存" /></td>
	</tr>
</table>
</form>
</body>
</html>
