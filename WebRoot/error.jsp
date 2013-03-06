<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>人格培育系统测评登陆</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath() %>/css/login.css"/>
</head>
<body>
	<div id="container">
		<div id="bg">
			<div id="center">
				<div id="bgl" class="left"></div>
				<div id="bgc" class="left">
				<p class="p">Oops!出错了！</p>
			  <s:if test="#session.user == null">
				<p>${requestScope.tip }，<a href="<%=request.getContextPath() %>/preindex.jsp">这里</a>重新登录</p>
			  </s:if>
			  <s:else>
			  	<p>${requestScope.tip }，回到 <a href="<%=request.getContextPath() %>/profile?student.sid=${sessionScope.user.sid}">首页</a></p>
			  </s:else>
				</div>
				
			</div>
		</div>

	</div>
</body>
</html>