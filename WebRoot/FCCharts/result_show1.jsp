<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript" src="<%=request.getContextPath() %>/FCCharts/FusionCharts.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人格培育系统-学生主页</title>
</head>
<body>
<div><select name="grade">
	<s:bean name="org.apache.struts2.util.Counter" id="counter">
		   <s:param name="first" value="1" />
		   <s:param name="last" value="8" />
		   <s:iterator>
		     <option value='<s:property/>'><s:property/>年级</option>
		   </s:iterator>
	   </s:bean>
</select></div>
<div id="chartdiv" align="center"></div>

<input type="hidden" id="dataurl" value="<s:property value="xmlData"/>"/>
<script type="text/javascript">
var myChart = new FusionCharts("<%=request.getContextPath()%>/FCCharts/swf/FCF_Line.swf", "myChartId", "600", "500");
var dataURL = document.getElementById("dataurl").value;
myChart.setDataXML(dataURL);

myChart.render("chartdiv");
</script>
</body>
</html>