package edu.sdu.online.rengepeiyang.actions;

import java.sql.Connection;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.beans.ClazzBean;
import edu.sdu.online.rengepeiyang.beans.DepartmentBean;
import edu.sdu.online.rengepeiyang.beans.GradeBean;
import edu.sdu.online.rengepeiyang.beans.MajorBean;
import edu.sdu.online.rengepeiyang.beans.TeacherBean;
import edu.sdu.online.rengepeiyang.processer.DepartmentProcesser;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class SystemAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<DepartmentBean> deps ;
	List<GradeBean> grades;
	List<MajorBean> majors=null;
	List<ClazzBean> clazzes=null;
	List<TeacherBean> tb =null;
	private Connection con = DBUtil.getConnection();
 
	public List<DepartmentBean> getDeps() {
		return deps;
	}
	public List<GradeBean> getGrades() {
		return grades;
	}
	
	public List<MajorBean> getMajors() {
		return majors;
	}
	public List<ClazzBean> getClazzes() {
		return clazzes;
	}
	
	public List<TeacherBean> getTb() {
		return tb;
	}
	@Override
	public String execute() throws Exception {
		DepartmentProcesser processer = DepartmentProcesser.getInstance();
		deps = processer.getDeps(con);
		grades = DepartmentProcesser.loadGrade();
		return SUCCESS;
	}

}
