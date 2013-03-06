<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>人格培育系统测评登陆</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/login1.css"/>
</head>
<body>

	<div id="container">
		<div id="bg">
			<div id="center">
				
				${requestScope.tip }
				<div id="bgc">
					<div id="title_1" class="left"></div>
					<p id="pos">首次使用本系统的同学请使用学号登陆，密码为空</p>
					<s:form action="login" method="post" theme="simple" id="d_s">
					<table>
					<tr>
						<td class="w1">
						用户名：
						</td>
						<td class="w2">
						<input name="username" type="text" class="input_s"/>
						</td>
					</tr>
					<tr>
						<td class="w1">
						密码：
						</td>
						<td class="w2">
						<input name="password" type="password" class="input_s"/>
						</td>
					</tr>
					<tr>
						<td class="w1">
						用户类型：
						</td>
						<td class="w2">
						<select class="input_s" name="loginType">
					  <option value="student">学生</option>
     					<option value="teacher">辅导员</option>
     					<option value="depuser">学院管理员</option>
     					<option value="sysuser">学工部管理员</option>
					  </select>
						</td>
					</tr>
					</table>
					<div id="t">
						<input name="button1" type="button" id="button_s1" class="left" src="./images/button1.png" />
						<input name="button2" type="submit" value="" id="button_s2" class="left" src="./images/button1.png" />
					</div>
					</s:form>
				
				</div>
				
				
			</div>
		</div>

	</div>
</body>
</html>