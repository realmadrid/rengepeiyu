<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生基本信息</title>
</head>
<body>
	<table width="80%" border="1" align="center">
  <tr>
    <td width="15%">学号：</td>
    <td width="38%"><s:property value="student.sid"/></td>
    <td width="12%">身份证号：</td>
    <td width="35%"><s:property value="student.idnum"/></td>
  </tr>
  <tr>
    <td>姓名：</td>
    <td><s:property value="student.sname"/></td>
    <td>性别：</td>
    <td><s:property value="student.gender"/></td>
  </tr>
  <tr>
    <td>民族：</td>
    <td><s:property value="student.nation"/></td>
    <td>政治面貌：</td>
    <td><s:property value="student.pstatus"/></td>
  </tr>
  <tr>
    <td>出生日期：</td>
    <td><s:property value="student.birthday"/></td>
    <td>生源地：</td>
    <td><s:property value="student.homeaddr"/></td>
  </tr>
  <tr>
    <td>学院：</td>
    <td><s:property value="student.depname"/></td>
    <td>辅导员：</td>
    <td><s:property value="student.tname"/></td>
  </tr>
  <tr>
    <td>入学时间：</td>
    <td><s:property value="student.jointime"/></td>
    <td>学制：</td>
    <td><s:property value="student.years"/></td>
  </tr>
    <tr>
    <td>是否班干部：</td>
    <td><s:if test="#request.student.isleader">是</s:if><s:else>否</s:else></td>
    <td>是否激活：</td>
    <td><s:if test="#request.student.isreg">是</s:if><s:else>否</s:else></td>
  </tr>
    <tr>
    <td>是否参与本年测评：</td>
    <td colspan="3"><s:if test="#request.student.isgrade">是</s:if><s:else>否</s:else></td>
  </tr>
</table>

	
</body>
</html>