<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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
				
				<div id="bgc">
					<div id="title_1" class="left"></div>
					<p id="pos">首次使用本系统请用学号和身份证号登陆&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				
					<s:form action =""  id="d_s">
					<table>
					<tr>
						<td class="w1">
						用户名：
						</td>
						<td class="w2">
						<input name="user" value="" type="text" class="input_s"/>
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
						<select class="input_s">
					  <option value ="user1">学生</option>
					  <option value ="user2">辅导员</option>
					  <option value="user3">学院</option>
					  <option value="user4">学工部</option>
					  </select>
						</td>
					</tr>
					</table>
					<div id="t">
						<input name="button1" type="button" id="button_s1" class="left" src="./images/button1.png" />
						<input name="button2" type="button" id="button_s2" class="left" src="./images/button1.png" />
					</div>
					</s:form>
				
					
				</div>
				
				
			</div>
		</div>

	</div>
</body>
</html>