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
String str_depid=new String(request.getParameter("depid").getBytes("iso-8859-1"),"GBK");
int depid;
if(str_depid.length()==0)
	depid = -1;
else
	depid = Integer.valueOf(str_depid);
//System.out.println("id:====="+depid);

//check the database
	List<TeacherBean> teachers = null;

    teachers = DepartmentProcesser.getTeachers(depid);

    StringBuffer result = new StringBuffer();
    result.append("<select name='student.tid' id='tid' onchange='showclazz()'>\n");
    result.append("<option value='0'>Ñ¡Ôñ¸¨µ¼Ô±</option>\n");
    if(teachers.size()>0) {
	    for(int i=0; i<teachers.size(); i++)
	    	result.append("<option value=\""+teachers.get(i).getTid()+"\">"+teachers.get(i).getTname()+"</option>\n");
	    
    } 
    result.append("</select>\n");
    
   // System.out.println(result.toString());
    response.getWriter().write(result.toString());
    //request.getRequestDispatcher("/loadmajor.jsp&depid="+depid).forward(request, response);
%>