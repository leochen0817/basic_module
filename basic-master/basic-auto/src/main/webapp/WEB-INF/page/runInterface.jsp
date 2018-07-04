<%@page import="com.forte.auto.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执行用例</title>
<script type="text/javascript">
function downModule(idf_interface){
	window.location.href = "download.html?idf_interface=" + idf_interface;
}
</script>
</head>
<%
ForteInterface jiekou = (ForteInterface)request.getAttribute("jiekou");

%>
<body>
<h2>执行用例</h2>
<form action="executeInterfaceResult.html" method="post">
<input type="hidden" name="idf_interface" value="<%=jiekou.getIdf_interface()%>"/>
<input type="hidden" name="f_name" value="<%=jiekou.getF_name()%>"/>
<input type="hidden" name="f_method" value="<%=jiekou.getF_method()%>"/>
<input type="hidden" name="f_url" value="<%=jiekou.getF_url()%>"/>
<input type="hidden" name="f_desc" value="<%=jiekou.getF_desc()%>"/>
<input type="hidden" name="f_parameters" value="<%=jiekou.getF_parameters()%>"/>
<p>接口名称：<%=jiekou.getF_name() %></p>
<p>接口描述：<%=jiekou.getF_desc() %></p>
<p>接口url：<%=jiekou.getF_url() %></p>
<p>接口方法：<%=jiekou.getF_method() %></p>
<p>参数列表</p>
<%
String parameters = jiekou.getF_parameters();
String[] paramList = new String[100];
if(parameters == null || "".equals(parameters)){
%>
<p><input type="hidden" name="paramList" />没有参数</p>
<%
}else{
	if("".equals(parameters) != true && parameters.split("\\|").length == 1){
		paramList[0] = parameters;
%>
<p><%=paramList[0] %>:<input type="text" name="paramList" style="width:500px"/></p>
<%
	}else{
		paramList = parameters.split("\\|");
		for(int i=0;i<paramList.length;i++){
%>
<p><%=paramList[i] %>:<input type="text" name="paramList" style="width:500px"/></p>
<%
		}
	}
}
%>
<p><input type="submit" value="提交"/></p>
<p><input type="button" value="下载模板" onclick="downModule(<%=jiekou.getIdf_interface()%>)" /></p>
</form>
<form action="upload.html?idf_interface=<%=jiekou.getIdf_interface()%>"  enctype="multipart/form-data" method="post">
<input type="file" name="file" value="上传批量文件并执行"/></br>
<input type="submit" value="执行" name="submit"/>
</form>
</body>
</html>