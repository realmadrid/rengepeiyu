<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/jsfunc.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生基本信息维护-<s:property value="student.sname"/></title>
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath() %>/css/register.css"/>
</head>
<body onload="initModify()">
	<div id="container">
		<div id="bg">
			<s:form action="student_modify" method="post" theme="simple">
			<input type="hidden" id="context" value="<%=request.getContextPath() %>"/>
			<input type="hidden" name="student.sid" value="<s:property value="student.sid"/>"/>
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
					<td class="w1">是否修改密码：</td>
					<td class="w2"><input type="checkbox" id="pwd" name="student.modifypwd" value="true"/></td>
				</tr>
				 <tr>
					<td class="w1">确认新密码：</td>
					<td class="w2"><input type="password" name="student.spwd"/></td>
				</tr>
				 <tr>
					<td class="w1">政治面貌：</td>
					<td class="w2"><s:textfield name="student.pstatus"/></td>
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
					<td class="w2">
					<s:select onchange="loadteacher()" id="depid" name="student.depid"  list="deps" listKey="depid" listValue="depname" headerKey="" headerValue="-请选择学院-"/>
					</td>
				</tr>
				<tr>
					<td class="w1">专业：</td>
					<td class="w2">
					<input type="hidden" id="midvalue" value="<s:property value="student.majorid"/>">
					<select id="mid" name="student.majorid" onchange="loadclazz()">
      		<option>--请选择专业--</option>
      	</select>
					</td>
				</tr>
				<tr>
					<td class="w1">辅导员姓名：</td>
					<td class="w2">
					<input type="hidden" id="tidvalue" value="<s:property value="student.tid"/>">
					   <select id="tid" name="student.tid" >
				        	<option>--选择导员--</option>
				        </select>
				     </td>
				</tr>
				 <tr>
					<td class="w1">班级：</td>
					<td class="w2">
					<input type="hidden" id="cidvalue" value="<s:property value="student.clazzid"/>"/>
					<select id="cid" name="student.clazzid">
      		<option>--请选择班级--</option>
      	</select>
					</td>
				 </tr>
				 <tr>	
			
					<td class="w1">学制：</td>
					
					<td class="w2">
					<input type="hidden" id="yearsvalue" value="<s:property value="student.years"/>">
					<select id='years' name="student.years">
							<option value="4">4年制</option>
							<option value="5">5年制</option>
							<option value="6">6年制</option>
							<option value="7">7年制</option>
							<option value="8">8年制</option>
						</select></td>
				  </tr>
				   <tr>
					<td class="w1">入学年份：</td>
					<td class="w2">
						<input type="text" name="student.jointime" value="<s:property value="student.jointime"/>"/>
					</td>
					</tr>
				 <tr>
					<td class="w1">是否是班干部：</td>
					<td class="w2">
					<s:radio name="student.isleader" list="#{true:'是',false:'否'}"></s:radio>
					</td>
				  </tr>
				 <tr>
					<%--<td class="w1">入学年份：</td>
					<td class="w2">
					<select id='years' name="student.years">
							<option value="4">2008</option>
							<option value="5">2009</option>
							<option value="6">2010</option>
							<option value="7">2011</option>
						</select>
					</td>
				 </tr>
				  --%></table>
			
			<input class="right" type="submit" value="" id="submit"/>
			</s:form>
			<p class="right">请先确认信息，再激活用户名！</p>
		</div>
		</div>

</body>
</html>