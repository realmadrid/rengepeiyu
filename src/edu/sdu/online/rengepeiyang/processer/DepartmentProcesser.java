package edu.sdu.online.rengepeiyang.processer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.sdu.online.rengepeiyang.beans.ClazzBean;
import edu.sdu.online.rengepeiyang.beans.DepartmentBean;
import edu.sdu.online.rengepeiyang.beans.GradeBean;
import edu.sdu.online.rengepeiyang.beans.MajorBean;
import edu.sdu.online.rengepeiyang.beans.TeacherBean;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class DepartmentProcesser {
	private DepartmentBean department;
    private static DepartmentProcesser instance = new DepartmentProcesser();
    private DepartmentProcesser() {
    	
    }
    public static DepartmentProcesser getInstance() {
    	return instance;
    }
	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}
	
	public List<DepartmentBean> getDeps(Connection conn) {
		List<DepartmentBean> deps = new ArrayList<DepartmentBean>();
		DepartmentBean dep = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select depid,depname from department";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dep = new DepartmentBean();
				dep.setDepid(rs.getInt("depid"));
				dep.setDepname(rs.getString("depname"));
				deps.add(dep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DBUtil.close(pstmt,rs);
		}
		return deps;
	}
	public static List<TeacherBean> getTeachers(int depid) {
		List<TeacherBean> teachers = new ArrayList<TeacherBean>();
		Connection conn = DBUtil.getConnection();
		TeacherBean teacher = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select tid,tname from teacher where depid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				teacher = new TeacherBean();
				teacher.setTid(rs.getInt("tid"));
				teacher.setTname(rs.getString("tname"));
				teachers.add(teacher);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return teachers;
	}
	/*//���ذ༶��Ϣ
	public static List<ClazzBean> loadClazz(int depid) {
		List<ClazzBean> result = new ArrayList<ClazzBean>();
		ClazzBean clazz = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct clazz from student where depid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				clazz = new ClazzBean();
				clazz.setClazzname(rs.getString("clazz"));
				result.add(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return result;
	}*/
	public static List<ClazzBean> loadClazz(int tid) {
		List<ClazzBean> result = new ArrayList<ClazzBean>();
		ClazzBean clazz = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT clazzname,clazzid FROM major2clazz WHERE major2clazz.clazzid IN(" +
				"SELECT DISTINCT clazzid FROM student WHERE tid=?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				clazz = new ClazzBean();
				clazz.setClazzname(rs.getString("clazzname"));
				clazz.setClazzid(rs.getInt("clazzid"));
				result.add(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return result;
	}
	@SuppressWarnings("deprecation")
	public static List<GradeBean> loadGrade(){//获得当前的年份，往前推8年,没有数据库访问
		List<GradeBean> result = new ArrayList<GradeBean>();
		Date date = new Date();
		int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
		for(int i=1;i<=8;i++)
		{
			GradeBean grade = new GradeBean();
			grade.setGradeid(currentYear-i);
			grade.setGradename(currentYear-i+"级");
			result.add(grade);
		}
		return result;
	}
	public static List<MajorBean> loadMajor(int depid){//根据学院号加载专业信息
		List<MajorBean> result = new ArrayList<MajorBean>();
		MajorBean major = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select majorname,majorid  from dep2major where depid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				major = new MajorBean();
				major.setMajorid(rs.getInt("majorid"));
				major.setMajorname(rs.getString("majorname"));
				result.add(major);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return result;
	}

}
