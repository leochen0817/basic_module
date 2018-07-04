<%@page import="com.forte.auto.entity.HRBatch"%>
<%@page import="com.forte.auto.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量任务</title>
</head>
<%
List batchList = (ArrayList)request.getAttribute("batchList");
if(batchList == null){
	batchList = new ArrayList();
}
%>
<body>
<h2>批量任务</h2>
<table border="1">
  <tr>
    <th>序号</th>
    <th>批量日期</th>
    <th>存储过程名称</th>
    <th>执行开始时间</th>
    <th>执行结束时间</th>
    <th>状态</th>
  </tr>
<%
for(int i=0;i<batchList.size();i++){
	HRBatch batch = new HRBatch();
	batch = (HRBatch)batchList.get(i);
	int id = batch.getId();
	String batch_date = batch.getBatch_date();
	String procedure_name = batch.getProcedure_name();
	if(procedure_name.trim().equalsIgnoreCase("PRO_CHANGE")){
		procedure_name = "变动趋势批量";
	}
	if(procedure_name.trim().equalsIgnoreCase("REPORT_EMP_ANALYSIS")){
		procedure_name = "劳动力分析批量";
	}
	String execute_begin_time = batch.getExecute_begin_date().toString();
	String execute_end_time = batch.getExecute_end_date().toString();
	String execute_state = batch.getExecute_state();
	if(execute_state.trim().equalsIgnoreCase("success")){
		execute_state = "成功";
	}else{
		execute_state = "失败";
	}
%>
<tr>
<td><%=id %></td>
<td><%=batch_date %></td>
<td style="width:150px"><%=procedure_name %></td>
<td style="width:200px"><%=execute_begin_time %></td>
<td style="width:200px"><%=execute_end_time %></td>
<td><%=execute_state %></td>
</tr>
<%  
}
%>
</table>
</body>
</html>