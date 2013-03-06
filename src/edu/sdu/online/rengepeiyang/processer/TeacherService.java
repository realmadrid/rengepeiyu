package edu.sdu.online.rengepeiyang.processer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class TeacherService {
	private int tid;
	private int clazzid;
	private Connection con = null;
	private ResultSet set = null;
	private PreparedStatement state = null;

	public void setTid(int tid) {
		this.tid = tid;
	}

	public void setClazzid(int clazzid) {
		this.clazzid = clazzid;
	}

	public ArrayList<String[]> getStudents() {
		ArrayList<String[]> temp = new ArrayList<String[]>();
		String sql=null;
		if(clazzid==0){
			 sql= "select sid,sname,clazzname from student,major2clazz where tid="+tid
			+" and student.clazzid=major2clazz.clazzid";
		}else{
			 sql = "select sid,sname,clazzname from student,major2clazz where " +
					"student.clazzid="+clazzid+" and major2clazz.clazzid="+clazzid;
		}
		con = DBUtil.getConnection();
		try{
			state = con.prepareStatement(sql);
			set = state.executeQuery();
			while(set.next()){
				String strs[]= new String[3];
				strs[0]=set.getString("sid");
				strs[1]=set.getString("sname");
				strs[2]=set.getString("clazzname");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(con!=null)
					con.close();
				if(state!=null)
					state.close();
				if(set!=null)
					set.close();
				}catch(SQLException so){
					so.printStackTrace();
				}
			}
		return temp;
	}
}
