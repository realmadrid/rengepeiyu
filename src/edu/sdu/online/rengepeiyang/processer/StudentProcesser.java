package edu.sdu.online.rengepeiyang.processer;

import static edu.sdu.online.rengepeiyang.processer.QuestionProcesser.calcGrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sdu.online.rengepeiyang.beans.DepUserBean;
import edu.sdu.online.rengepeiyang.beans.MCBean;
import edu.sdu.online.rengepeiyang.beans.StudentBean;
import edu.sdu.online.rengepeiyang.beans.TeacherBean;
import edu.sdu.online.rengepeiyang.beans.User;
import edu.sdu.online.rengepeiyang.beans.UserBean;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class StudentProcesser {
	private static StudentProcesser instance = new StudentProcesser();
	private StudentProcesser() {
		
	}
	public static StudentProcesser getInstance() {
		return instance;
	}
	public User login(Connection conn,UserBean user,String loginType) {
		User result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		result = new User();
		try {
			if("student".equals(loginType)) {
				sql = "select sid, isgrade, sname,depid,jointime,years from student where sid=? and spwd=? and isreg=1";
				StudentBean tempUser = (StudentBean)user;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tempUser.getSid());
				pstmt.setString(2, tempUser.getIdnum());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					
					result.setSid(rs.getString("sid"));
					result.setIsgrade(rs.getBoolean("isgrade"));
					result.setSname(rs.getString("sname"));
					result.setGrade(calcGrade(rs.getInt("jointime")));
					result.setDepid(rs.getInt("depid"));
					result.setIsreg(true);
					result.setType("student");
					result.setErrorCode(-1);
				} else { //该用户是否身份真实？
					sql = "select sid,idnum,isreg from student where sid = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, tempUser.getSid());
					rs = pstmt.executeQuery();
					if(rs.next()) {
						if(rs.getBoolean("isreg")) { //用户已注册,说明是密码填写错误
							result.setErrorCode(0);
							return result;
						} else { //用户还未激活
							if(rs.getString("idnum").equals(tempUser.getIdnum())) { //合法用户
								result.setType("student");
								result.setSid(tempUser.getSid());
								result.setIsreg(false);
								result.setErrorCode(1);
							} else { //非法用户
								result.setErrorCode(2);
							}
							return result;
						}
					} else {  //用户不合法，或是学号填写错误
						result.setErrorCode(2);
						return result;
					}
				}
			} else if("teacher".equals(loginType)) {
				sql = "select tid, tname, depid, authority from teacher where tname=? and pwd=?";
				TeacherBean tempUser = (TeacherBean)user;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tempUser.getTname());
				pstmt.setString(2, tempUser.getPwd());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result.setTid(rs.getInt("tid"));
					result.setTname(rs.getString("tname"));
					result.setAuthority(rs.getInt("authority"));
					result.setType("teacher");
					result.setErrorCode(-1);
				} else
				result.setErrorCode(3);
			} else if("depuser".equals(loginType)) {
				sql = "select depuid, depid, depuname, authority from depuser where depuname=? and pwd=?";
				DepUserBean tempUser = (DepUserBean)user;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tempUser.getDepuname());
				pstmt.setString(2, tempUser.getPwd());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result.setDepuid(rs.getInt("depuid"));
					result.setDepid(rs.getInt("depid"));
					result.setDepuname(rs.getString("depuname"));
					result.setAuthority(rs.getInt("authority"));
					result.setType("depuser");
					result.setErrorCode(-1);
				} else
				result.setErrorCode(4);
			}else {
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt,rs);
			DBUtil.close(conn);
		}
		result.setType(loginType);
		return result;
	}
//	public boolean regist(Connection conn,StudentBean student) { //主要完善密码，辅导员姓名，是否是班委这三点
//		
//		
//	}
	//加载学生基本信息
	public StudentBean loadStudentsBySid(Connection conn,String sid) {
		StudentBean student = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sid,gender,student.depid,depname,sname,idnum,nation,birthday,pstatus,homeaddr,jointime,years,student.tid,tname,isleader,isgrade,isreg,student.majorid,majorname,student.clazzid,clazzname from student left join dep2major on student.majorid=dep2major.majorid left join major2clazz on student.clazzid=major2clazz.clazzid left join department on student.depid = department.depid left join teacher on student.tid = teacher.tid where sid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				student = new StudentBean();
				student.setSid(rs.getString("sid"));
				student.setGender(rs.getString("gender"));
				student.setSname(rs.getString("sname"));
				student.setIdnum(rs.getString("idnum"));
				student.setNation(rs.getString("nation"));
				student.setBirthday(rs.getDate("birthday"));
				student.setPstatus(rs.getString("pstatus"));
				student.setHomeaddr(rs.getString("homeaddr"));
				student.setDepid(rs.getInt("depid"));
//				student.setDepname(loadDepname(conn).get(student.getDepid()));
				student.setDepname(rs.getString("depname"));
				student.setMajor(rs.getString("majorname"));
				student.setMajorid(rs.getInt("majorid"));
				System.out.println("major-id:"+rs.getInt("majorid"));
				student.setClazz(rs.getString("clazzname"));
				student.setClazzid(rs.getInt("clazzid"));
				student.setTid(rs.getInt("tid"));
				student.setTname(rs.getString("tname"));
				student.setJointime(rs.getInt("jointime"));
				student.setYears(rs.getInt("years"));
				student.setIsleader(rs.getBoolean("isleader"));
				student.setIsgrade(rs.getBoolean("isgrade"));
				student.setIsreg(rs.getBoolean("isreg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return student;
	}
	public Map<Integer,String> loadDepname(Connection conn) {
		Map<Integer,String> depname = new HashMap<Integer,String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select depname,depid from department";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(!depname.containsKey(rs.getString("depid"))) {
					depname.put(rs.getInt("depid"), rs.getString("depname"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return depname;
	}
	//加载教师姓名
	public Map<Integer,String> loadTeachername(Connection conn) {
		Map<Integer,String> tname = new HashMap<Integer,String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select tname,tid from teacher";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(!tname.containsKey(rs.getString("tid"))) {
					tname.put(rs.getInt("tid"), rs.getString("tname"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return tname;
	}
	//学生激活功能
	public boolean studentReg(Connection conn,StudentBean student) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update student set spwd=?,tid=?,isleader=?,depid=?,years=?, depid=?,majorid=?,jointime=?,pstatus=?,clazzid=?,isreg=1 where sid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getSpwd());
			pstmt.setInt(2, student.getTid());
			pstmt.setBoolean(3, student.isIsleader());
			pstmt.setInt(4, student.getDepid());
			pstmt.setInt(5, student.getYears());
			pstmt.setInt(6, student.getDepid());
			pstmt.setInt(7, student.getMajorid());
			pstmt.setInt(8, student.getJointime());
			pstmt.setString(9, student.getPstatus());
			pstmt.setInt(10, student.getClazzid());
			pstmt.setString(11, student.getSid());
//	System.out.println(pstmt.toString());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
			
		}
		if(result>0)
			return true;
		else
			return false;
	}
	//查询学院名称
	public String getDepName(Connection conn,int depid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		String sql = "select depname from department where depid=?";
	    try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("depname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return result;
	}
	//查询辅导员姓名
	public String getTeacherName(Connection conn,int tid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		String sql = "select tname from teacher where tid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("sname");
			}
 		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return result;
	}
	//根据学院编号加载专业
	public static List<MCBean> getMajorsByDepId(int depid) {
		Connection conn = DBUtil.getConnection();
		List<MCBean> majors = new ArrayList<MCBean>();
		MCBean major = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select majorid,majorname from dep2major where depid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				major = new MCBean();
				major.setId(rs.getInt("majorid"));
				major.setName(rs.getString("majorname"));
				majors.add(major);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return majors;
	}
	//根据专业名加载班级
	public static List<MCBean> getClazzsByMajorId(int majorid) {
		Connection conn = DBUtil.getConnection();
		List<MCBean> clazzs = new ArrayList<MCBean>();
		MCBean clazz = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select clazzid,clazzname from major2clazz where majorid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, majorid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				clazz = new MCBean();
				clazz.setId(rs.getInt("clazzid"));
				clazz.setName(rs.getString("clazzname"));
				clazzs.add(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return clazzs;
	}
	//搜索学生信息列表
	public List<StudentBean> searchStudent(Connection conn,Integer depid,Integer tid,String sname,String sid) {
		List<StudentBean> students = new ArrayList<StudentBean>();
		StudentBean student = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sid,sname,depid,tid,jointime,isgrade,isreg from student where 1=1 ";
		if(depid != null)
			sql +="and depid="+depid+" ";
		if(tid != null)
			sql +="and tid ="+tid+" ";
		if(sname!=null && sname.length()>0) 
			sql += "and sname ='"+sname+"'";
		if(sid != null && sid.length()>0) 
			sql += "and sid = '"+sid+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			//System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				student = new StudentBean();
				student.setSid(rs.getString("sid"));
				student.setSname(rs.getString("sname"));
//				System.out.println("学生姓名："+student.getSname());
				student.setDepid(rs.getInt("depid"));
				student.setTid(rs.getInt("tid"));
				student.setJointime(rs.getInt("jointime"));
				student.setIsgrade(rs.getBoolean("isgrade"));
				student.setIsreg(rs.getBoolean("isreg"));
				student.setGrade(calcGrade(student.getJointime()));
				student.setDepname(loadDepname(conn).get(student.getDepid()));
//				System.out.println("学院编号："+student.getDepid()+"学院："+loadDepname(conn).get(student.getDepid()));
				student.setTname(loadTeachername(conn).get(student.getTid()));
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return students;
	}
	//修改学生信息
	public boolean modifyStudent(Connection conn,StudentBean student) {
		PreparedStatement pstmt = null;
		int recourds = -1;
		String sql = "";
		if(student.isModifypwd())
			sql = "update student set tid=?,isleader=?,depid=?,years=?, depid=?,majorid=?,jointime=?,pstatus=?,clazzid=?,spwd=?,isreg=1 where sid=?";
		else
			sql = "update student set tid=?,isleader=?,depid=?,years=?, depid=?,majorid=?,jointime=?,pstatus=?,clazzid=?,isreg=1 where sid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, student.getSpwd());
			pstmt.setInt(1, student.getTid());
			pstmt.setBoolean(2, student.isIsleader());
			pstmt.setInt(3, student.getDepid());
			pstmt.setInt(4, student.getYears());
			pstmt.setInt(5, student.getDepid());
			pstmt.setInt(6, student.getMajorid());
			pstmt.setInt(7, student.getJointime());
			pstmt.setString(8, student.getPstatus());
			pstmt.setInt(9, student.getClazzid());
			if(student.isModifypwd()) {
				pstmt.setString(10, student.getSpwd());
				pstmt.setString(11, student.getSid());
			} else {
				pstmt.setString(10, student.getSid());
			}
//	System.out.println(pstmt.toString());
			recourds = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
		} 
		if(recourds > 0) {
			return true;
		} else {
			return false;
		}
	}
}
