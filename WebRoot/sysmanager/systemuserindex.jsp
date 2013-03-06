<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/jsfunc.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人格培育系统-学生主页</title>
</head>
<body onload="loadDepartment()">
	欢迎您,${sessionScope.user.sysname }
	<s:form action="student_search" method="post">
	<input type="hidden" id="context" value="<%=request.getContextPath() %>"/>
		<table>
		<tr>
			<td>学院</td>
			<td>辅导员</td>
			<td>姓名</td>
			<td colspan="2">学号</td>
		</tr>
		<tr>
		<td>
		<div id='depname'>
		<select name="">
			<option value=''>请选择学院</option>
		</select>
		</div>
		</td>
		<td>
		<div id='daoyuan'>
			<select name="">
			<option value=''>请选择辅导员</option>
		</select>
		</div>
		</td>
		<td>
			<input type="text" name="student.sname"/>
		</td>
		<td>
			<input type="text" name="student.sid"/>
		</td>
		<td>
			<input type="submit" name="查询"/>
		</td>
		</tr>
		</table>
	</s:form>
</body>
</html>