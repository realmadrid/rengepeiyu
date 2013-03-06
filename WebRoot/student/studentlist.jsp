<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
</head>
<body>
	<table border='1' align ='center' width='85%'>
		<tr><td>学号</td><td>姓名</td><td>学院</td><td>辅导员</td><td>入学时间</td><td>是否激活</td><td>是否参与本年测评</td><td>操作</td><td>修改</td></tr>
		<s:iterator value="students">
			<tr>
				<td><s:property value="sid"/></td>
				<td><s:property value="sname"/></td>
				<td><s:property value="depname"/></td>
				<td><s:property value="tname"/></td>
				<td><s:property value="jointime"/></td>
			    <td><s:if test="isreg">是</s:if><s:else>否</s:else></td>
				<td><s:if test="isgrade">是</s:if><s:else>否</s:else></td>
				<td><a href="getData1?student.sid=<s:property value="sid"/>&student.grade=<s:property value="grade"/>">查看情况</a></td>
				<td><a href="student_load_for_modify?student.sid=<s:property value="sid"/>">修改</a></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>