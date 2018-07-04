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
</head>
<%
ForteInterface jiekou = (ForteInterface)request.getAttribute("jiekou");
if(jiekou == null){
	jiekou = new ForteInterface();
}
%>
<body>
<h2>接口详情</h2>
<form action="updateInterface.html">
<input type="hidden" name="idf_interface" value="<%=jiekou.getIdf_interface() %>"/>
<input type="hidden" name="f_source" value="<%=jiekou.getF_source() %>" />
<p>序号：<%=jiekou.getIdf_interface() %></p>
<p>接口名称：<input type="text" name="f_name" value="<%=jiekou.getF_name() %>" style="width:500px"/></p>
<p>接口描述：<input type="text" name="f_desc" value="<%=jiekou.getF_desc() %>" style="width:500px"/></p>
<p>接口地址：<input type="text" name="f_url" value="<%=jiekou.getF_url() %>" style="width:500px"/></p>
<p>接口方法：<input type="text" name="f_method" value="<%=jiekou.getF_method() %>" style="width:500px"/></p>
<p>接口参数：<input type="text" name="f_parameters" value="<%=jiekou.getF_parameters() %>" style="width:500px"/></p>
<input type="submit" value="修改"/>
</form>
</body>
</html>