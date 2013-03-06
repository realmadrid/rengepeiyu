<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>测试版系统管理员页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
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
			<table width="80%" border="1" align="center">
				<tr>
					<td height="39" colspan="7" align="center">
						标题：统计数据结果
					</td>
				</tr>
				<tr>
					<td height="39" align="center">
						
						<a href="toExcel">生成excel</a>
					</td>
					<td width="15%" height="39" align="center">
						全校
					</td>
					<td width="13%" align="center">
						<s:select id="grade" name="grade" list="grades" listKey="gradeid"
							listValue="gradename" headerKey="0" headerValue="-请选择年级-"
							theme="simple" />
					</td>
					<td width="15%" align="center">
						<s:select onchange="loadmajorandteacher()" id="depid" name="depid"
							list="deps" listKey="depid" listValue="depname" headerKey="0"
							headerValue="-请选择学院-" theme="simple" />
					</td>

					<td width="13%" align="center">
						<div id="daoyuan">
							<select id="tid" name="tid" onchange="showclazz()">
								<option value="0">
									请选择辅导员
								</option>
							</select>
						</div>
					</td>
					<td width="12%" align="center">
						<div id="myclazz">
							<select id="clazz" name="clazz">
								<option value="0">
									请选择班级
								</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td width="17%" height="25" align="center">
						<input type="button" name="joinnumber" id="joinnumber" value="参评率" />
						<a href="allcpl">所有统计</a>
					</td>
					<td colspan="6" rowspan="5" valign="top">
						<div id="result">
							欢迎使用
						</div>
					</td>
				</tr>
				
				<tr>
					<td height="13" align="center">
						<input type="button" name="ability11" id="ability11" value="11项能力" />
						<a href="all11">所有统计</a>
					</td>
				</tr>
				<tr>
					<td height="6" align="center">
						<input type="button" name="suyang" id="suyang" value="素养层级 " />
						<a href="allsuyang">所有统计</a>
					</td>
				</tr>
				<tr>
					<td height="6" align="center">
						<input type="button" name="ti24" id="ti24" value="24道题" />
						<a href="all24">所有统计</a>
					</td>
				</tr>
				<tr>
					<td height="25" align="center">
						<input type="button" name="history" id="history" value="历史" />
						
					</td>
				</tr>
				<tr>
					<td height="25" align="center">
						<input type="button" name="personal" id="personal" value="查询单个同学" />
					</td>
				</tr>
				<tr>
					<td height="42" align="center">
						&nbsp;
					</td>
				</tr>
			</table>

	</body>
</html>
