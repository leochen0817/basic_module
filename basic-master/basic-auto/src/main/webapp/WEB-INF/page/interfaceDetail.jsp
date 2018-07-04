<%@page import="com.forte.auto.*"%>
<%@page import="com.forte.auto.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口详情</title>
<script type="text/javascript">
function toRunInterfaceJsp(idf_interface){
	//var idf_interface = document.getElementById(idf_interface);
	window.location.href = "toRunInterfaceJsp.html?idf_interface="+idf_interface;
}
</script>
</head>
<%
ForteInterface jiekou = (ForteInterface)request.getAttribute("jiekou");
if(jiekou == null){
	jiekou = new ForteInterface();
}
%>
<body>
<h2>接口详情</h2>
<p>序号：<%=jiekou.getIdf_interface() %></p>
<p>接口名称：<%=jiekou.getF_name() %></p>
<p>接口描述：<%=jiekou.getF_desc() %></p>
<p>接口地址：<%=jiekou.getF_url() %></p>
<p>接口方法：<%=jiekou.getF_method() %></p>
<p>接口参数：<%=jiekou.getF_parameters() %></p>
<input type="button" value="执行" onclick="toRunInterfaceJsp(<%=jiekou.getIdf_interface() %>)"/>
</body>
</html>