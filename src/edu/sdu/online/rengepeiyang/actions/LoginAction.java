package edu.sdu.online.rengepeiyang.actions;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.beans.DepUserBean;
import edu.sdu.online.rengepeiyang.beans.StudentBean;
import edu.sdu.online.rengepeiyang.beans.TeacherBean;
import edu.sdu.online.rengepeiyang.beans.User;
import edu.sdu.online.rengepeiyang.processer.StudentProcesser;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class LoginAction extends ActionSupport{
	private StudentBean student;
	private TeacherBean teacher;
	private DepUserBean depuser;
	private Connection conn;
	private String username;
	private String password;
	private String loginType;
	private StudentProcesser studentProcesser= StudentProcesser.getInstance();
	public StudentBean getStudent() {
		return student;
	}
	public void setStudent(StudentBean student) {
		this.student = student;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	public TeacherBean getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DepUserBean getDepuser() {
		return depuser;
	}
	public void setDepuser(DepUserBean depuser) {
		this.depuser = depuser;
	}
	/*
	 * 登陆
	 * 传入参数是：登陆类型和用户名，密码
	 * 学生登陆首先会到数据看验证是否有改学号的学生，如果有，则看该学生账户是否已经激活，如果已经激活再看其密码是否正确，正确则
	 * 跳转到学生首页；如果没有激活，则跳转到激活页面，在激活页面里需要选择辅导员，选择学制信息，填写密码，以及是否是班干部信息
	 */
	public String login() throws Exception {
		Connection conn = DBUtil.getConnection();
		User user = null;
		if("student".equals(loginType)) {
			student = new StudentBean();
			student.setSid(username);
			student.setIdnum(password);
			user = studentProcesser.login(conn, student, loginType);
		}
		else if("teacher".equals(loginType)) {
			teacher = new TeacherBean();
			teacher.setTname(username);
			teacher.setPwd(password);
			user = studentProcesser.login(conn, teacher, loginType);
		} else if("depuser".equals(loginType)) {
			depuser = new DepUserBean();
			depuser.setDepuname(username);
			depuser.setPwd(password);
			user = studentProcesser.login(conn, depuser, loginType);
		} else if("sysuser".equals(loginType)) {
			user = new User();
			if("123".equals(username)&&"123".equals(password)) {
				user.setType(loginType);
				user.setSysname("Admin");
				user.setErrorCode(-1);
			} else {
				user.setErrorCode(6);
			}
		}
		if(user.getErrorCode() < 0) {
			ActionContext.getContext().getSession().put("user", user);
			if(loginType.equals("student")) {
				//将学号往下传
				    setUsername(username);
					return "student_home";
			} else if(loginType.equals("teacher")) {
				return "teachersuccess";
			} else if(loginType.equals("depuser")) {
				return "depusersuccess";
			} else if(loginType.equals("sysuser")) {
				return "sysusersuccess";
			} 
				ActionContext.getContext().put("tip", "登陆用户类型不正确");
				return ERROR;
		} else {
			if(loginType.equals("student")) {
				if(user.getErrorCode() == 0) {
					ActionContext.getContext().put("tip", "对不起，您的密码输入有误！");
				} else if(user.getErrorCode() == 1) {
					ActionContext.getContext().getSession().put("user", user);
					setUsername(username); // 将学号往下传
					return "jihuo";
				} else if(user.getErrorCode() == 2) {
					ActionContext.getContext().put("tip", "对不起，您的身份不合法或则学号填写有误");
				}
			}
			if(loginType.equals("teacher")) {
				ActionContext.getContext().put("tip", "对不起，您的用户名或密码错误！");
			}
			if(loginType.equals("depuser")) {
				ActionContext.getContext().put("tip", "对不起，您的身份不合法或则学号填写有误");
			}
            if(loginType.equals("sysuser")) {
            	ActionContext.getContext().put("tip", "对不起，您的身份不合法或则学号填写有误");
            }
			return ERROR;
		}
	}
	public String logout() throws Exception {
		if(ActionContext.getContext().getSession().get("user")!=null) {
			ActionContext.getContext().getSession().remove("user");
			ActionContext.getContext().put("describ", "感谢您使用 山东大学人格培养系统，您已安全退出");
		} else {
			ActionContext.getContext().put("describ", "您的登陆已经失效， 不需退出");
		}
		return SUCCESS;
	}
}
