package edu.sdu.online.rengepeiyang.actions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import edu.sdu.online.rengepeiyang.beans.User;
import edu.sdu.online.rengepeiyang.processer.TeacherService;
public class TeacherAction extends ActionSupport {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int clazzid;
	Map<String, String> map = new HashMap<String, String>();
	 private String result ;
	 private String pagestudents;
	 ArrayList<String[]> students;
     public void setClazzid(int clazzid) {
		this.clazzid = clazzid;
	}

	public String getResult() {
		return result;
	}

	public String getStudents(){
		User user;
		user = (User)ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().put("state", "students");
		ActionContext.getContext().getSession().put("clazz", clazzid+"");
		ActionContext.getContext().getSession().put("tid", user.getTid()+"");
		TeacherService service = new TeacherService();
		service.setClazzid(clazzid);
		service.setTid(user.getTid());
		students = service.getStudents();
		pagestudents = "<table width='100%' border='1'>" +
				"<tr>" +
				"<td colspan='3'>查询条件</td>" +
				"</tr>" +
				"<tr>" +
				"<td width='33%' align='center'>学号</td>" +
				"<td width='33%' align='center'>姓名</td>" +
				"<td width='34%' align='center'>班级</td>" +
				"</tr>";
		for(int i=0;i<students.size();i++){
			pagestudents = pagestudents+"<tr>" +
			"<td width='33%' align='center'>"+students.get(i)[0]+"</td>" +
			"<td width='33%' align='center'>"+students.get(i)[1]+"</td>" +
			"<td width='34%' align='center'>"+students.get(i)[2]+"</td>" +
			"</tr>";
		}
		pagestudents =pagestudents+"</table>";
		try {
			map.put("pagestudents",  new String(pagestudents.getBytes(), "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 将要返回的map对象进行json处理
		JSONObject jo = JSONObject.fromObject(map);
		result = jo.toString();
    	 return SUCCESS;
     }

}
