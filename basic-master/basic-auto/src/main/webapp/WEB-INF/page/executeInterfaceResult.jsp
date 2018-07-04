<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.forte.auto.entity.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量执行接口用例结果</title>
</head>
<%
ForteInterface jiekou = (ForteInterface)request.getAttribute("jiekou");
List<ForteRunner> resultPatch = (List<ForteRunner>)request.getAttribute("resultPatch");

if(jiekou == null){
	jiekou = new ForteInterface();
}
if(resultPatch == null){
	resultPatch = new ArrayList();
}
%>
<body>
<h2>批量执行接口用例结果</h2>
<table border="1">
  <tr>
    <th width="100px">序号</th>
    <th width="100px">接口名称</th>
    <th width="200px">接口url</th>
    <th width="200px">接口请求</th>
    <th width="300px">接口响应</th>
    <th width="100px">执行结果</th>
  </tr>
<%
for(int i=0;i<resultPatch.size();i++){
%>  
  <tr>
  	<td><%=resultPatch.get(i).getF_patch() %></td>
  	<td><%=jiekou.getF_name() %></td>
  	<td><%=jiekou.getF_url() %></td>
  	<td><%=resultPatch.get(i).getF_parameters() %></td>
  	<td><xmp><%=resultPatch.get(i).getF_response() %></xmp></td>
  	<td><%="1".equals(resultPatch.get(i).getF_status())?"成功":"失败" %></td>
  </tr>
<%
}
%>
</body>
</html>