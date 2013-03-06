<%@page import="java.util.*" pageEncoding="GBK"%>
<%request.setCharacterEncoding("GBK");
  response.setCharacterEncoding("GBK");
 %>
 <%@page import="edu.sdu.online.rengepeiyang.processer.*,edu.sdu.online.rengepeiyang.beans.*" %>
<%
response.setContentType("text/xml");
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0); //prevents catching at proxy server
String str_mid=new String(request.getParameter("mid").getBytes("iso-8859-1"),"GBK");
int depid;
if(str_mid.length()==0)
	depid = -1;
else
	depid = Integer.valueOf(str_mid);
//System.out.println("id:====="+depid);

//check the database
    List<MCBean> majors = null;
    majors = StudentProcesser.getClazzsByMajorId(depid);
    StringBuffer result = new StringBuffer();
    if(majors.size()>0) {
    	result.append("<option >--«Î—°‘Ò∞‡º∂--</option>\n");
    	for(int i=0; i<majors.size(); i++) {
    		result.append("<option value=\""+majors.get(i).getId()+"\">"+majors.get(i).getName()+"</option>\n");	
    	}
    }
    //System.out.println(result.toString());
    response.getWriter().write(result.toString());
%>