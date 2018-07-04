<%@page import="com.forte.auto.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口列表</title>
<script type="text/javascript">
function deleteInterface(idf_interface){
	if(confirm("确认删除吗")){
		window.location.href = "deleteInterface.html?idf_interface="+idf_interface;
	}else{
		return;
	}
}
function updateInterface(idf_interface){
	window.location.href = "toUpdateInterfaceJsp.html?idf_interface="+idf_interface;
}
</script>
</head>
<%
List interfaceList = (ArrayList)request.getAttribute("interfaceList");
if(interfaceList == null){
	interfaceList = new ArrayList();
}
%>
<body>
<h2><%=request.getAttribute("f_source") %>接口列表</h2>
<form action="">
<table border="1">
  <tr>
    <th>序号</th>
    <th>接口名称</th>
    <th>接口描述</th>
    <th>接口来源</th>
    <th>操作</th>
  </tr>
  
  	<c:forEach items="${interfaceList}" var="li">
	<tr>
		<td><c:out value="${li.idf_interface}"/></td>
		<td><a href="toRunInterfaceJsp.html?idf_interface=<c:out value="${li.idf_interface}"/>"><c:out value="${li.f_name}"/></a></td>
		<td><c:out value="${li.f_desc}"/></td>
		<td><c:out value="${li.f_source}"/></td>
		<td><input type="button" value="修改" onclick="updateInterface(<c:out value="${li.idf_interface}"/>)"/><input type="button" value="删除" onclick="deleteInterface(<c:out value="${li.idf_interface}"/>)"/></td>
	</tr>
	</c:forEach>
</table>
</form>
</body>
</html>