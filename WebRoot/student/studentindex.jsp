<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/jsfunc.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人格培育系统-学生主页</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/index.css"/>
</head>
<body>
	<div id="container">
		<div id="title"></div>
		<div id="body">
		<div id="body_l" class="left">
		<div id="s1"><a href="student_load_for_modify?student.sid=${sessionScope.user.sid }">基本信息修改</a></div>
		<div id="s2">
			<s:if test="#session.user.isgrade == false">
	      		<a href="<%=request.getContextPath() %>/student/selectpage.jsp">个人素养测评</a>
	    	</s:if>
		    <s:else><a href="javascript:alert('您已经参加过本年度测评，不必再次参加');">个人素养测评</a></s:else>
		</div>
		<%--<a href="load_answer_modify?grade=${sessionScope.user.grade }">历年结果查看</a>--%>
		<div id="s3"><a onclick="javascript:lookback(${sessionScope.user.grade });">历年结果查看</a></div>
		<div id="s4"><a href="#">统计信息查看</a></div>
		</div>
		
		<div id="body_c"  class="left">
		<div id="content">
		<p>您好，${sessionScope.user.sname}！欢迎来到人格培育系统!<br>您<s:if test="#session.user.isgrade == false">还未参与过测评</s:if><s:else>已参与过本年测评</s:else></p>
		<table id="pos">
				<tr>
					<td class="w1">学号：</td>
					<td class="w2"><s:property value="student.sid"/></td>
				</tr>
				<tr>
				  <td class="w1">身份证号：</td>
					<td class="w2"><s:property value="student.idnum"/></td>
				</tr>
				 <tr>
					<td class="w1">姓名：</td>
					<td class="w2"><s:property value="student.sname"/></td>
				</tr>
				<tr>
					<td class="w1">性别：</td>
					<td class="w2"><s:property value="student.gender"/></td>
				</tr>
				  <tr>
					<td class="w1">民族：</td>
					<td class="w2"><s:property value="student.nation"/></td>
				</tr>
				 <tr>
					<td class="w1">政治面貌：</td>
					<td class="w2"><s:property value="student.pstatus"/></td>
				  </tr>
				  <tr>
					<td class="w1">出生日期：</td>
					<td class="w2"><s:property value="student.birthday"/></td>
				</tr>
				 <tr>
					<td class="w1">生源地：</td>
					<td class="w2"><s:property value="student.homeaddr"/></td>
				  </tr>
				  <tr>
					<td class="w1">学院：</td>
					<td class="w2"><s:property value="student.depname"/></td>
				</tr>
				<tr>	
					<td class="w1">专业：</td>
					
					<td class="w2"><s:property value="student.major"/></td>
				  </tr>
				   <tr>	
			
					<td class="w1">班级：</td>
					
					<td class="w2"><s:property value="student.clazz"/></td>
				  </tr>
				  <tr>	
			
					<td class="w1">学制：</td>
					
					<td class="w2"><s:property value="student.years"/></td>
				  </tr>
				  <tr>
					<td class="w1">辅导员姓名：</td>
					<td class="w2">
					<s:property value="student.tname"/>
					</td>
				</tr>
				 <tr>
					<td class="w1">是否是班干部：</td>
					<td class="w2">
					<s:if test="#request.student.isleader">是</s:if><s:else>否</s:else>
					</td>
				  </tr>
				  </table>
		</div>
		</div>
		<div id="body_r"  class="left"></div>
		<div id="body_b" class="left"></div>
		</div>
		</div>
</body>
</html>