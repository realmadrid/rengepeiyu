<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/jsfunc.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>人格培育系统测评系统</title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath() %>/css/index.css"/>
</head>
<body>
	<div id="container">
		<div id="title"></div>
		<div id="body">
		<div id="a_s"><a href="logout">退出系统</a></div>
		<div class="clear"></div>
		<div id="body_l" class="left">
		<div id="s1"><a href="student_load_for_modify?student.sid=${sessionScope.user.sid }">基本信息修改</a></div>
		<div id="s2">
			<s:if test="#session.user.isgrade == false">
	      		<a href="<%=request.getContextPath() %>/student/selectpage.jsp">个人素养测评</a>
	    	</s:if>
		    <s:else><a href="javascript:alert('您已经参加过本年度测评，不必再次参加');">个人素养测评</a></s:else>
		</div>
		<div id="s3"><a onclick="javascript:lookback(${sessionScope.user.grade });">历年结果查看</a></div>
		<div id="s4"><a href="#">统计信息查看</a></div>
		</div>
		<div id="body_c"  class="left">
			<div id="content">
			<p class="p_pos">${requestScope.describ }</p>
			</div>
		</div>
		<div id="body_r"  class="left"></div>
		<div id="body_b" class="left"></div>
		</div>
		</div>

	
</body>
</html>