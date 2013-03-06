<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 7.0.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Language" content="zh-CN"/>
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<meta name="Copyright" content="Conright (c) 2000-2011 www.online.stu.edu.cn All Rights Reserved"/>
	<title>统计数据结果</title>
	<link href="css/index2.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" language="javascript"
			src="<%=request.getContextPath()%>/jquery.js">
			</script>
		<script type="text/javascript" language="javascript"
			src="<%=request.getContextPath()%>/jsfunc2.js">
          </script>
          <script  src="action.js">
          </script>
</head>
<body>
<input type="hidden" id="context"
				value="<%=request.getContextPath()%>" />
	<div id="head"></div>
	<div id="tittle"></div>
	<div id="mainbody">
		<div id="side_bar">
			<ul id="u1">
			<li class="tab1"><a href="toExcel">生成excel</a></li>
			<li class="tab1"><input type="button" name="joinnumber" id="joinnumber" value="参评率" /></li>
			<li class="tab1"><input type="button" name="ability11" id="ability11" value="11项能力" /></li>
			<li class="tab1"><input type="button" name="suyang" id="suyang" value="素养层级 " /></li>
			<li class="tab1"><input type="button" name="ti24" id="ti24" value="24道题" /></li>
			<li class="tab1"><input type="button" name="students" id="students" value="查询尚未参评同学" /></li>
			</ul>
		</div>
		<div id="content">
			<div id="memu">
				<div class="tab3"></div>
				<div class="tab4">
					<s:select id="grade" name="grade" list="grades" listKey="gradeid"
							listValue="gradename" headerKey="0" headerValue="-请选择年级-"
							theme="simple" />
				</div>
				<div class="tab4">
				<s:select onchange="loadmajorandteacher()" id="depid" name="depid"
							list="deps" listKey="depid" listValue="depname" headerKey="0"
							headerValue="-请选择学院-" theme="simple" />
				</div>
				<div class="tab4">
				<div id="daoyuan">
							<select id="tid" name="tid" onchange="showclazz()">
								<option value="0">
									请选择辅导员
								</option>
							</select>
						</div>
				</div>
				<div class="tab4">
				<div id="myclazz">
							<select id="clazz" name="clazz">
								<option value="0">
									请选择班级
								</option>
							</select>
						</div>
				</div>
			</div>
			<div id="result">
				<!--这里用来盛放任何显示结果!-->
				欢迎使用
			</div>
		</div>
	</div>
    
</body>
</html>