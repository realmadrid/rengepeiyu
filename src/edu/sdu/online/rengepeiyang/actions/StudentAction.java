package edu.sdu.online.rengepeiyang.actions;

import java.sql.Connection;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.beans.DepartmentBean;
import edu.sdu.online.rengepeiyang.beans.MCBean;
import edu.sdu.online.rengepeiyang.beans.StudentBean;
import edu.sdu.online.rengepeiyang.beans.TeacherBean;
import edu.sdu.online.rengepeiyang.beans.User;
import edu.sdu.online.rengepeiyang.processer.ChartsProcesser;
import edu.sdu.online.rengepeiyang.processer.DepartmentProcesser;
import edu.sdu.online.rengepeiyang.processer.StudentProcesser;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class StudentAction extends ActionSupport{
	private StudentBean student;
	private StudentProcesser sp;
	private DepartmentProcesser dp;
	private ChartsProcesser cp;
	private String xmlData;
	private List<DepartmentBean> deps;
	private List<TeacherBean> tb;
	private List<StudentBean> students;
	private String sid; //用于传递学号使用
	public StudentBean getStudent() {
		return student;
	}

	public void setStudent(StudentBean student) {
		this.student = student;
	}
	
	//加载学生基本信息
	public List<DepartmentBean> getDeps() {
		return deps;
	}

	public void setDeps(List<DepartmentBean> deps) {
		this.deps = deps;
	}

	public List<TeacherBean> getTb() {
		return tb;
	}

	public void setTb(List<TeacherBean> tb) {
		this.tb = tb;
	}
	
	public List<StudentBean> getStudents() {
		return students;
	}

	public void setStudents(List<StudentBean> students) {
		this.students = students;
	}

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}
    
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	//加载学生基本信息
	public String loadStudent() throws Exception {
		Connection conn = DBUtil.getConnection();
		sp = StudentProcesser.getInstance();
		dp = DepartmentProcesser.getInstance();
		StudentBean temp = sp.loadStudentsBySid(conn, student.getSid());

		if(temp== null) {
			ActionContext.getContext().put("tip","没有学号为：["+student.getSid()+"]的同学");
			DBUtil.close(conn);
			return ERROR;
		}
		deps = dp.getDeps(conn); //设置学院列表
		setDeps(deps);
		tb = DepartmentProcesser.getTeachers(temp.getDepid()); //设置教师列表
		setTb(tb);
		
		setStudent(temp);
		DBUtil.close(conn);
		return SUCCESS;
	}
	//激活
	public String studentReg() throws Exception{
		Connection conn = DBUtil.getConnection();
		sp = StudentProcesser.getInstance();
		boolean result = sp.studentReg(conn, student);
//		System.out.println(student);
		if(result)
			return SUCCESS;
		else
			return ERROR;
/*		System.out.println("pwd=="+student.getSpwd()+" tid=="+student.getTid()+" isleader=="+student.isIsleader()+"depid=="+student.getDepid()+"years=="+student.getYears());
		return SUCCESS;*/
	}
	//学生搜索
	public String studentSearch() throws Exception {
		Connection conn = DBUtil.getConnection();
		sp = StudentProcesser.getInstance();
//		System.out.println();
		students = sp.searchStudent(conn, student.getDepid(), student.getTid(), student.getSname(), student.getSid());
		setStudents(students);
		if(students.size()==0) {
			ActionContext.getContext().put("tip", "没有搜到相关条件的同学！");
			return ERROR;
		}
		return SUCCESS;
	}
	//学生信息修改
	public String modifyStudent() throws Exception {
		Connection conn = DBUtil.getConnection();
		sp = StudentProcesser.getInstance();
		
		boolean result = sp.modifyStudent(conn,student);
		if(result) {
			setSid(student.getSid()); //将学号往下传，传给下一个action
			return ((User)ActionContext.getContext().getSession().get("user")).getType()+"success";
		} else {
			ActionContext.getContext().put("tip", "对不起，学生信息修改失败！");
			return ERROR;
		}
	}
	//获取首页数据
	public String profile() throws Exception{
		StudentBean st = null;
		Connection conn =DBUtil.getConnection();
		sp = StudentProcesser.getInstance();
		st = sp.loadStudentsBySid(conn, student.getSid());
		setStudent(st);
		return SUCCESS;
	}
	//根据学生ID获得学生
	public String getXMLData1() throws Exception {
//		((User)ActionContext.getContext().getSession().get("user")).setSid(student.getSid()); //设置学生ID
		Connection conn =DBUtil.getConnection();
		cp = ChartsProcesser.getInstance();
		xmlData = cp.getData1ById(conn, student.getSid(), student.getGrade());
		setXmlData(xmlData);
		DBUtil.close(conn);
		return SUCCESS;
	}
}
