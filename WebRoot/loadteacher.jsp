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
    List<MCBean> majors = null;
    teachers = DepartmentProcesser.getTeachers(depid);
    majors = StudentProcesser.getMajorsByDepId(depid);
    StringBuffer result = new StringBuffer();

    result.append("<option value=''>选择辅导员</option>\n");
    if(teachers.size()>0) {
	    for(int i=0; i<teachers.size(); i++)
	    	result.append("<option value=\""+teachers.get(i).getTid()+"\">"+teachers.get(i).getTname()+"</option>\n");
	    
    } 

    if(majors.size()>0) {
    	result.append("||\n");
    	result.append("<option >--请选择专业--</option>\n");
    	for(int i=0; i<majors.size(); i++) {
    		result.append("<option value=\""+majors.get(i).getId()+"\">"+majors.get(i).getName()+"</option>\n");	
    	}
    }
    //System.out.println(result.toString());
    response.getWriter().write(result.toString());
%>