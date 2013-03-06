<%@page import="java.util.*,java.sql.*,edu.sdu.online.rengepeiyang.utils.*" pageEncoding="GBK"%>
<%request.setCharacterEncoding("GBK");
  response.setCharacterEncoding("GBK");
 %>
 <%@page import="edu.sdu.online.rengepeiyang.processer.*,edu.sdu.online.rengepeiyang.beans.*" %>
<%
response.setContentType("text/xml");
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0); //prevents catching at proxy server

//System.out.println("id:====="+depid);

//check the database
	List<DepartmentBean> deps = null;
	Connection conn = DBUtil.getConnection();
    deps = DepartmentProcesser.getInstance().getDeps(conn);
	DBUtil.close(conn);
    StringBuffer result = new StringBuffer();
    result.append("<select id='depid' name='student.depid' onchange='loadteacher()'>\n");
    result.append("<option value=''>—°‘Ò—ß‘∫</option>\n");
    if(deps.size()>0) {
	    for(int i=0; i<deps.size(); i++)
	    	result.append("<option value=\""+deps.get(i).getDepid()+"\">"+deps.get(i).getDepname()+"</option>\n");
	    
    } 
    result.append("</select>\n");
    
   // System.out.println(result.toString());
    response.getWriter().write(result.toString());
%>