package edu.sdu.online.rengepeiyang.processer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class TestQueryProcesser {
	private int depid;
	private int clazz;
	private int grade;
	private int tid;
	private ArrayList<float[]> resultcpl;// 参评率
	private ArrayList<float[]> result11;// 11项能力
	private ArrayList<float[]> resultSuyang;// 素养层级
	private ArrayList<float[]> result24;// 24道题
	private Connection con=null;
	private ResultSet set=null;
	private PreparedStatement state=null;

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public void setClazz(int clazz) {
		this.clazz = clazz;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public ArrayList<float[]> getCPL() {
		int total;
		int joined;
		con = DBUtil.getConnection();
		String sqlall = null; // 总人数
		String sqljoined = null; // 参加的人数
        sqlall= "SELECT COUNT(*) FROM student WHERE depid=?" +
        		"  AND clazzid=? AND tid=? AND jointime=?";
        sqljoined ="SELECT COUNT(*) FROM student WHERE depid=? AND" +
		" clazzid=? AND tid=? AND jointime=? and isgrade=?";
        try {
			state = con.prepareStatement(sqlall);
			state.setInt(1, depid);
			state.setInt(2, clazz);
			state.setInt(3, tid);
			state.setInt(4, grade);
			set = state.executeQuery();
			set.next();
			total = set.getInt(1);
			state = con.prepareStatement(sqljoined);
			state.setInt(1, depid);
			state.setInt(2, clazz);
			state.setInt(3, tid);
			state.setInt(4, grade);
			state.setBoolean(5, true);
			set = state.executeQuery();
			set.next();
			joined = set.getInt(1);
			float temp[]=new float[3];
			temp[0]=total;
			temp[1]=joined;
			temp[2]=(float)joined/total;
			resultcpl.add(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(con!=null){
					con.close();
				}
				if(state!=null){
					state.close();
				}
				if(set!=null){
					set.close();
				}
			}catch(SQLException ee){
				ee.printStackTrace();
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
		}
		return resultcpl;
	}

	public ArrayList<float[]> get11() {
		//此方法返回的list数组需要注意
		//共有11个数组，0到10对应11项能力
		con = DBUtil.getConnection();
		int total=0;
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
        if(grade==0){
            for(int i=1;i<=8;i++)//该循环用于获得8个表的总人数
            {
            	sqltotal= "SELECT COUNT(sid) FROM gradeinfo_1 WHERE gradeinfo_"+i+".sid IN(" +
    			"SELECT sid FROM student " +
    			"WHERE  depid=? AND clazzid=? AND tid=? AND jointime=?)";
            	try {
					state = con.prepareStatement(sqltotal);
					state.setInt(1, depid);
					state.setInt(2, clazz);
					state.setInt(3, tid);
					state.setInt(4, grade);
					set= state.executeQuery();
					set.next();
					total = total+set.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            for(int k=1;k<=8;k++){//该循环用于统计11项能力和2个层级
            	sql="SELECT s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 FROM gradeinfo_"+k+" " +
    			"WHERE gradeinfo_1.sid IN " +
    			"(SELECT sid FROM student  " +
    			"WHERE depid=? AND clazzid=? AND tid=? AND jointime=?)";
        		try {
    				state = con.prepareStatement(sql);
    				state.setInt(1, depid);
    				state.setInt(2, clazz);
    				state.setInt(3, tid);
    				state.setInt(4, grade);
    				set= state.executeQuery();
    				float t[];
    				while(set.next()){
    					String sx="s";
    					for(int i=1;i<=11;i++){//统计11项能力，这里用到拼接字符串来获得列名如s1,s2
    						t= new float[3];
    						if(set.getString(sx+i)=="1")
    							t[0]++;
    						else if(set.getString(sx+i)=="2")
    							t[1]++;
    						else t[2]++;
    						for(int j=0;j<3;j++)//转换为百分率
    							t[j]=t[j]/total;
    						result11.add(t);
    					}
    				}
    			} catch (SQLException e) {
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
        		
            }
        }else{
        	Date date = new Date();
    		int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
    		int temp=currentYear-grade;
    		//根据入学年份选择具体的表，需要拼接字符串以获得表名
    		sqltotal= "SELECT COUNT(sid) FROM gradeinfo_1 WHERE gradeinfo_"+temp+".sid IN(" +
			"SELECT sid FROM student " +
			"WHERE  depid=? AND clazzid=? AND tid=? AND jointime=?)";
    		
    		sql="SELECT s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 FROM gradeinfo_"+temp+" " +
			"WHERE gradeinfo_1.sid IN " +
			"(SELECT sid FROM student  " +
			"WHERE depid=? AND clazzid=? AND tid=? AND jointime=?)";
    		try {
    			state = con.prepareStatement(sqltotal);
				state.setInt(1, depid);
				state.setInt(2, clazz);
				state.setInt(3, tid);
				state.setInt(4, grade);
				set= state.executeQuery();
			    set.next();
			    total = set.getInt(1);
				state = con.prepareStatement(sql);
				state.setInt(1, depid);
				state.setInt(2, clazz);
				state.setInt(3, tid);
				state.setInt(4, grade);
				set= state.executeQuery();
				float t[];
				while(set.next()){
					String sx="s";
					for(int i=1;i<=11;i++){//统计11项能力，这里用到拼接字符串来获得列名如s1,s2
						t= new float[3];
						if(set.getString(sx+i)=="1")
							t[0]++;
						else if(set.getString(sx+i)=="2")
							t[1]++;
						else t[2]++;
						for(int j=0;j<3;j++)//转换为百分率
							t[j]=t[j]/total;
						result11.add(t);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
        }
		return result11;
	}
	public ArrayList<float[]> getSuyang(){
		con = DBUtil.getConnection();
		int total=0;
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
        if(grade==0){
            for(int i=1;i<=8;i++)//该循环用于获得8个表的总人数
            {
            	sqltotal= "SELECT COUNT(sid) FROM gradeinfo_1 WHERE gradeinfo_"+i+".sid IN(" +
    			"SELECT sid FROM student " +
    			"WHERE  depid=? AND clazzid=? AND tid=? AND jointime=?)";
            	try {
					state = con.prepareStatement(sqltotal);
					state.setInt(1, depid);
					state.setInt(2, clazz);
					state.setInt(3, tid);
					state.setInt(4, grade);
					set= state.executeQuery();
					set.next();
					total = total+set.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            for(int k=1;k<=8;k++){//该循环用于统计2个层级
            	sql="SELECT l1,l2 FROM gradeinfo_"+k+" " +
    			"WHERE gradeinfo_1.sid IN " +
    			"(SELECT sid FROM student  " +
    			"WHERE depid=? AND clazzid=? AND tid=? AND jointime=?)";
        		try {
    				state = con.prepareStatement(sql);
    				state.setInt(1, depid);
    				state.setInt(2, clazz);
    				state.setInt(3, tid);
    				state.setInt(4, grade);
    				set= state.executeQuery();
    				float t[];
    				while(set.next()){
    					t=new float[4];//能力层级一
    					//t[0---3]分别代表层级一的A，B+,B,C
    					if(set.getString("l1")=="1")
    						t[0]++;
    					else if(set.getString("l1")=="2")
    						t[1]++;
    					else if(set.getString("l1")=="3")
    						t[2]++;
    					else t[3]++;
    					for(int j=0;j<4;j++)//转换为百分率
							t[j]=t[j]/total;
    					resultSuyang.add(t);
    					t = new float[5];//能力层级二
    					//t[0---4]分别代表层级二的A++,A+,A,B+,B
    					if(set.getString("l2")=="1")
    						t[0]++;
    					else if(set.getString("l2")=="5")
    						t[1]++;
    					else if(set.getString("l2")=="6")
    						t[2]++;
    					else if(set.getString("l2")=="7")
    						t[3]++;
    					else t[4]++;
    					for(int j=0;j<5;j++)//转换为百分率
							t[j]=t[j]/total;
    					resultSuyang.add(t);
    				}
    			} catch (SQLException e) {
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
        		
            }
        }else{
        	Date date = new Date();
    		int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
    		int temp=currentYear-grade;
    		//根据入学年份选择具体的表，需要拼接字符串以获得表名
    		sqltotal= "SELECT COUNT(sid) FROM gradeinfo_1 WHERE gradeinfo_"+temp+".sid IN(" +
			"SELECT sid FROM student " +
			"WHERE  depid=? AND clazzid=? AND tid=? AND jointime=?)";
    		
    		sql="SELECT l1,l2 FROM gradeinfo_"+temp+" " +
			"WHERE gradeinfo_1.sid IN " +
			"(SELECT sid FROM student  " +
			"WHERE depid=? AND clazzid=? AND tid=? AND jointime=?)";
    		try {
    			state = con.prepareStatement(sqltotal);
				state.setInt(1, depid);
				state.setInt(2, clazz);
				state.setInt(3, tid);
				state.setInt(4, grade);
				set= state.executeQuery();
			    set.next();
			    total = set.getInt(1);
				state = con.prepareStatement(sql);
				state.setInt(1, depid);
				state.setInt(2, clazz);
				state.setInt(3, tid);
				state.setInt(4, grade);
				set= state.executeQuery();
				float t[];
				while(set.next()){
					t=new float[4];//能力层级一
					//t[0---3]分别代表层级一的A，B+,B,C
					if(set.getString("l1")=="1")
						t[0]++;
					else if(set.getString("l1")=="2")
						t[1]++;
					else if(set.getString("l1")=="3")
						t[2]++;
					else t[3]++;
					for(int j=0;j<4;j++)//转换为百分率
						t[j]=t[j]/total;
					resultSuyang.add(t);
					t = new float[5];//能力层级二
					//t[0---4]分别代表层级二的A++,A+,A,B+,B
					if(set.getString("l2")=="1")
						t[0]++;
					else if(set.getString("l2")=="5")
						t[1]++;
					else if(set.getString("l2")=="6")
						t[2]++;
					else if(set.getString("l2")=="7")
						t[3]++;
					else t[4]++;
					for(int j=0;j<5;j++)//转换为百分率
						t[j]=t[j]/total;
					resultSuyang.add(t);
				}
			} catch (SQLException e) {
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
    		
        }
		return resultSuyang;
	}
	public ArrayList<float[]> get24(){
		con = DBUtil.getConnection();
		int total=0;
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
        if(grade==0){
            for(int i=1;i<=8;i++)//该循环用于获得8个表的总人数
            {
            	sqltotal= "SELECT COUNT(sid) FROM gradeinfo_1 WHERE gradeinfo_"+i+".sid IN(" +
    			"SELECT sid FROM student " +
    			"WHERE  depid=? AND clazzid=? AND tid=? AND jointime=?)";
            	try {
					state = con.prepareStatement(sqltotal);
					state.setInt(1, depid);
					state.setInt(2, clazz);
					state.setInt(3, tid);
					state.setInt(4, grade);
					set= state.executeQuery();
					set.next();
					total = total+set.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            for(int k=1;k<=8;k++){//该循环用于统计24道题目
            	sql="SELECT a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a2 FROM gradeinfo_"+k+" " +
    			"WHERE gradeinfo_1.sid IN " +
    			"(SELECT sid FROM student  " +
    			"WHERE depid=? AND clazzid=? AND tid=? AND jointime=?)";
        		try {
    				state = con.prepareStatement(sql);
    				state.setInt(1, depid);
    				state.setInt(2, clazz);
    				state.setInt(3, tid);
    				state.setInt(4, grade);
    				set= state.executeQuery();
    				float t[];
    				while(set.next()){
    					String ax="a";
    					for(int i=1;i<=24;i++){//统计11项能力，这里用到拼接字符串来获得列名如s1,s2
    						t= new float[3];
    						if(set.getString(ax+i)=="1")
    							t[0]++;
    						else if(set.getString(ax+i)=="2")
    							t[1]++;
    						else t[2]++;
    						for(int j=0;j<3;j++)//转换为百分率
    							t[j]=t[j]/total;
    						result24.add(t);
    					}
    					
    				}
    			} catch (SQLException e) {
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
        		
            }
        }else{
        	Date date = new Date();
    		int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
    		int temp=currentYear-grade;
    		//根据入学年份选择具体的表，需要拼接字符串以获得表名
    		sqltotal= "SELECT COUNT(sid) FROM gradeinfo_1 WHERE gradeinfo_"+temp+".sid IN(" +
			"SELECT sid FROM student " +
			"WHERE  depid=? AND clazzid=? AND tid=? AND jointime=?)";
    		
    		sql="SELECT a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 FROM gradeinfo_"+temp+" " +
			"WHERE gradeinfo_1.sid IN " +
			"(SELECT sid FROM student  " +
			"WHERE depid=? AND clazzid=? AND tid=? AND jointime=?)";
    		try {
    			state = con.prepareStatement(sqltotal);
				state.setInt(1, depid);
				state.setInt(2, clazz);
				state.setInt(3, tid);
				state.setInt(4, grade);
				set= state.executeQuery();
			    set.next();
			    total = set.getInt(1);
				state = con.prepareStatement(sql);
				state.setInt(1, depid);
				state.setInt(2, clazz);
				state.setInt(3, tid);
				state.setInt(4, grade);
				set= state.executeQuery();
				float t[];
				while(set.next()){
					String ax="a";
					for(int i=1;i<=11;i++){//统计11项能力，这里用到拼接字符串来获得列名如s1,s2
						t= new float[3];
						if(set.getString(ax+i)=="1")
							t[0]++;
						else if(set.getString(ax+i)=="2")
							t[1]++;
						else t[2]++;
						for(int j=0;j<3;j++)//转换为百分率
							t[j]=t[j]/total;
						result24.add(t);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
        }
		return result24;
	}
}
