<%@page import="com.forte.auto.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加接口</title>
</head>
<body>
<h2>添加<%=request.getAttribute("f_source") %>接口</h2>
 
<form:form action="saveInterface.html" method="post" name="myForm" modelAttribute="forteInterface">
<input type="hidden" name="f_source" value="<%=request.getAttribute("f_source") %>"/>
接口名称：<form:input path="f_name" style="width:500px"/><form:errors path="f_name"/></br>
接口描述：<form:input path="f_desc" style="width:500px"/><form:errors path="f_desc"/></br>
接口url：<form:input path="f_url" style="width:500px"/><form:errors path="f_url"/></br>
接口方法：<form:input path="f_method" style="width:500px"/><form:errors path="f_method"/></br>
接口参数：<form:input path="f_parameters" style="width:500px"/><form:errors path="f_parameters"/></br>
<input type="submit" value="保存"/><input type="reset" value="重置"/></br>
</form:form>
</body>
</html>