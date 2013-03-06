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
String str_tid=new String(request.getParameter("tid").getBytes("iso-8859-1"),"GBK");
int tid;
if(str_tid.length()==0)
	tid = -1;
else
	tid = Integer.valueOf(str_tid);
//System.out.println("id:====="+depid);

//check the database
	List<ClazzBean> clazzes = null;

    clazzes = DepartmentProcesser.loadClazz(tid);

    StringBuffer result = new StringBuffer();
    result.append("<select name='clazz' id='clazz'>\n");
    result.append("<option value='0'>Ñ¡Ôñ°à¼¶</option>\n");
    if(clazzes.size()>0) {
	    for(int i=0; i<clazzes.size(); i++)
	    	result.append("<option value=\""+clazzes.get(i).getClazzid()+"\">"+clazzes.get(i).getClazzname()+"</option>\n");
	    
    } 
    result.append("</select>\n");
    
   System.out.println(tid);
    response.getWriter().write(result.toString());
%>