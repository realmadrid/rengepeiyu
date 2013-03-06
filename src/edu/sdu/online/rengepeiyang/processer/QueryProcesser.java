package edu.sdu.online.rengepeiyang.processer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class QueryProcesser {
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
		resultcpl =new ArrayList<float[]>();
		con = DBUtil.getConnection();
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
		if(grade==0){
			if(depid==0){
				sqltotal="select count(*) from student";
				sql= "select count(*) from student where isgrade=true";
			}else{
				if(tid==0){
		            sqltotal="select count(*) from student where depid="+depid;
					sql= "select count(*) from student where isgrade=true and depid="+depid;
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where depid="+depid+" and tid="+tid;
						sql= "select count(*) from student where isgrade=true and tid="+tid;
					}else{
						sqltotal="select count(*) from student where clazzid="+clazz;
						sql= "select count(*) from student where isgrade=true and clazzid="+clazz;
					}
					
				}
			}
		}else{
			if(depid==0){
				sqltotal="select count(*) from student where jointime="+grade;
				sql= "select count(*) from student where isgrade=true and jointime="+grade;
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where jointime="+grade+" and depid="+depid;
					sql= "select count(*) from student where isgrade=true and jointime="+grade+" and depid="+depid;
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where jointime="+grade+" and tid="+tid;
						sql= "select count(*) from student where isgrade=true and jointime="+grade+" and tid="+tid;
					}else{
						sqltotal="select count(*) from student where  clazzid="+clazz;
						sql= "select count(*) from student where  isgrade=true and clazzid="+clazz;
					}
				}
			}
		}
		float t[]=null;
		try {
			t = new float[3];
			state= con.prepareStatement(sqltotal);
			System.out.println("sqltotal:"+sqltotal);
			set = state.executeQuery();
			set.next();
			t[0]=set.getInt(1);
			state= con.prepareStatement(sql);
			System.out.println("sql:"+sql);
			set = state.executeQuery();
			set.next();
			t[1]=t[1]+set.getInt(1);
			if(t[0]!=0)
				t[2]=t[1]/t[0];
				resultcpl.add(t);
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
		return resultcpl;
	}
	public ArrayList<float[]> get11() {
		result11 =new ArrayList<float[]>();
		con = DBUtil.getConnection();
		int total=0;
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
		if(grade==0){
			if(depid==0){
				sqltotal="select count(*) from student where isgrade=true";
				sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_?";
				result11=execute11(sql,sqltotal, con);
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where isgrade=true and depid="+depid;
					sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_? where sid in" +
							"(select sid from student where isgrade=true and depid="+depid+")";
					result11=execute11(sql,sqltotal, con);
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where isgrade=true and tid="+tid;
						sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_? where sid in" +
						"(select sid from student where isgrade=true and tid="+tid+")";
						result11=execute11(sql,sqltotal, con);
					}else{
						sqltotal="select count(*) from student where isgrade=true and clazzid="+clazz;
						sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_? where sid in" +
						"(select sid from student where isgrade=true and clazzid="+clazz+")";
						result11=execute11(sql,sqltotal, con);
					}
					
				}
			}
		}else{
			Date date = new Date();
			int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
    		int temp=currentYear-grade;
			if(depid==0){
				sqltotal="select count(*) from student where isgrade=true and jointime="+grade;
				sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_"+temp;
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where isgrade=true and jointime="+grade+" and depid="+depid;
					sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_"+temp+" where sid in " +
							"(select sid from student where isgrade=true and jointime="+grade+" and depid="+depid+")";
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where isgrade=true and tid="+tid;
						sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_"+temp+" where sid in " +
						"(select sid from student where isgrade=true and tid="+tid+")";
					}else{
						sqltotal="select count(*) from student where isgrade=true and clazzid="+clazz;
						sql= "select s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11 from gradeinfo_"+temp+" where sid in " +
						"(select sid from student where isgrade=true and clazzid="+clazz+")";
					}
				}
			}
			
			try {
				state= con.prepareStatement(sqltotal);
				System.out.println("sqltotal:"+sqltotal);
				set = state.executeQuery();
				set.next();
				total =set.getInt(1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				state = con.prepareStatement(sql);
				System.out.println("sql:"+sql);
				set = state.executeQuery();
				for(int k=0;k<11;k++){
					float tt[]= new float[3];
					result11.add(tt);
				}
				while(set.next()){
					String prefix="s";
					for(int j=1;j<=11;j++){
						if(set.getString(prefix+j).trim().equals("1")){
							result11.get(j-1)[0]++;
						}
						else if(set.getString(prefix+j).trim().equals("2"))
						{
							result11.get(j-1)[1]++;
						}
						else result11.get(j-1)[2]++;
					}
				}
				if(total!=0){
				for(int i=0;i<11;i++){
					result11.get(i)[0]=result11.get(i)[0]/total;
					result11.get(i)[1]=result11.get(i)[1]/total;
					result11.get(i)[2]=result11.get(i)[2]/total;
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
		return result11;
	}
	public ArrayList<float[]> getSuyang(){
		resultSuyang =new ArrayList<float[]>();
		con = DBUtil.getConnection();
		int total=0;
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
		if(grade==0){
			if(depid==0){
				sqltotal="select count(*) from student where isgrade=true";
				sql= "select l1,l2 from gradeinfo_?";
				resultSuyang=executeSuyang(sql,sqltotal, con);
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where isgrade=true and depid="+depid;
					sql= "select l1,l2 from gradeinfo_? where sid in" +
							"(select sid from student where isgrade=true and depid="+depid+")";
					resultSuyang=executeSuyang(sql,sqltotal, con);
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where isgrade=true and tid="+tid;
						sql= "select l1,l2 from gradeinfo_? where sid in" +
						"(select sid from student where isgrade=true  and tid="+tid+")";
						resultSuyang=executeSuyang(sql,sqltotal, con);
					}else{
						sqltotal="select count(*) from student where isgrade=true and clazzid="+clazz;
						sql= "select l1,l2 from gradeinfo_? where sid in" +
						"(select sid from student where isgrade=true and clazzid="+clazz+")";
						resultSuyang=executeSuyang(sql,sqltotal, con);
					}
					
				}
			}
		}else{
			Date date = new Date();
			int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
    		int temp=currentYear-grade;
			if(depid==0){
				sqltotal="select count(*) from student where isgrade=true and jointime="+grade;
				sql= "select l1,l2 from gradeinfo_"+temp;
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where isgrade=true and jointime="+grade+" and depid="+depid;
					sql= "select l1,l2 from gradeinfo_"+temp+" where sid in " +
							"(select sid from student where isgrade=true and jointime="+grade+" and depid="+depid+")";
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where isgrade=true and jointime="+grade+" and tid="+tid;
						sql= "select l1,l2 from gradeinfo_"+temp+" where sid in " +
						"(select sid from student where isgrade=true and jointime="+grade+" and tid="+tid+")";
					}else{
						sqltotal="select count(*) from student where isgrade=true "+"and clazzid="+clazz;
						sql= "select l1,l2 from gradeinfo_"+temp+" where sid in " +
						"(select sid from student where isgrade=true and clazzid="+clazz+")";
					}
				}
			}
			
			try {
				state= con.prepareStatement(sqltotal);
				System.out.println("sqltotal:"+sqltotal);
				set = state.executeQuery();
				set.next();
				total =set.getInt(1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				state = con.prepareStatement(sql);
				System.out.println("sql:"+sql);
				set = state.executeQuery();
				float t[]=new float[4];
				float t2[]=new float[5];
				String str;
				while(set.next()){
					//t[0---3]分别代表层级一的A，B+,B,C
					str=set.getString("l1");
					if(str.equals("1"))
						t[0]++;
					else if(str.equals("2"))
						t[1]++;
					else if(str.equals("3"))
						t[2]++;
					else t[3]++;
					//t2[0---4]分别代表层级二的A++,A+,A,B+,B
					str =set.getString("l2");
					if(str.equals("5"))
						t2[0]++;
					else if(str.equals("6"))
						t2[1]++;
					else if(str.equals("7"))
						t2[2]++;
					else if(str.equals("8"))
						t2[3]++;
					else t2[4]++;
					
				}
				if(total!=0){
					for(int j=0;j<4;j++)//转换为百分率
						t[j]=t[j]/total;
					for(int j=0;j<5;j++)//转换为百分率
						t2[j]=t2[j]/total;
				}
				resultSuyang.add(t);
				resultSuyang.add(t2);
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
		result24 =new ArrayList<float[]>();
		con = DBUtil.getConnection();
		int total=0;
		String sql = null; // 根据条件写sql语句
		String sqltotal=null;
		if(grade==0){
			if(depid==0){
				sqltotal="select count(*) from student where isgrade=true";
				sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_?";
				result24=execute24(sql,sqltotal, con);
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where isgrade=true and depid="+depid;
					sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_? where sid in" +
							"(select sid from student where isgrade=true and depid="+depid+")";
					result24=execute24(sql,sqltotal, con);
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where isgrade=true and tid="+tid;
						sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_? where sid in" +
						"(select sid from student where isgrade=true and tid="+tid+")";
						result24=execute24(sql,sqltotal, con);
					}else{
						sqltotal="select count(*) from student where isgrade=true and clazzid="+clazz;
						sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_? where sid in" +
						"(select sid from student where isgrade=true and clazzid="+clazz+")";
						result24=execute24(sql,sqltotal, con);
					}
					
				}
			}
		}else{
			Date date = new Date();
			int currentYear = Integer.parseInt(date.toString().substring(date.toString().length()-4).trim());
    		int temp=currentYear-grade;
			if(depid==0){
				sqltotal="select count(*) from student where isgrade=true and jointime="+grade;
				sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_"+temp;
			}else{
				if(tid==0){
					sqltotal="select count(*) from student where isgrade=true and jointime="+grade+" and depid="+depid;
					sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_"+temp+" where sid in " +
							"(select sid from student where isgrade=true and jointime="+grade+" and depid="+depid+")";
				}else{
					if(clazz==0){
						sqltotal="select count(*) from student where isgrade=true and jointime="+grade+
						" and tid="+tid;
						sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_"+temp+" where sid in " +
						"(select sid from student where isgrade=true and jointime="+grade+ " and tid="+tid+")";
					}else{
						sqltotal="select count(*) from student where isgrade=true and jointime="+grade+
						" and clazzid="+clazz;
						sql= "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24 from gradeinfo_"+temp+" where sid in " +
						"(select sid from student where isgrade=true  and clazzid="+clazz+")";
					}
				}
			}
			
			try {
				state= con.prepareStatement(sqltotal);
				System.out.println("sqltotal:"+sqltotal);
				set = state.executeQuery();
				System.out.println("---------------------");
				set.next();
				total =set.getInt(1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				
				state = con.prepareStatement(sql);
				System.out.println("sql:"+sql);
				set = state.executeQuery();
				
				for(int k=0;k<24;k++){
					float tt[]= new float[3];
					result24.add(tt);
				}
				while(set.next()){
					String prefix="a";
					for(int j=1;j<=24;j++){
						if(set.getString(prefix+j).trim().equals("1")){
							result24.get(j-1)[0]++;
						}
						else if(set.getString(prefix+j).trim().equals("2"))
						{
							result24.get(j-1)[1]++;
						}
						else result24.get(j-1)[2]++;
					}
				}
				if(total!=0){
				for(int i=0;i<24;i++){
					result24.get(i)[0]=result24.get(i)[0]/total;
					result24.get(i)[1]=result24.get(i)[1]/total;
					result24.get(i)[2]=result24.get(i)[2]/total;
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
	
		return result24;
	}

	public ArrayList<float[]> execute11(String sql,String sql2,Connection con){
		ArrayList<float[]> temp = new ArrayList<float[]>();
		int total=0;
		try {
			state = con.prepareStatement(sql2);
			System.out.println("sqltotal:"+sql2);
			set  = state.executeQuery();
			set.next();
			total = set.getInt(1);
			for(int i=1;i<=8;i++){//遍历8个表
				state = con.prepareStatement(sql);
				state.setInt(1, i);
				set = state.executeQuery();
				for(int k=0;k<11;k++){
					float t[]= new float[3];
					temp.add(t);
				}
				while(set.next()){
					String prefix="s";
					for(int j=1;j<=11;j++){
						if(set.getString(prefix+j).equals("1"))
							temp.get(j-1)[0]++;
						else if(set.getString(prefix+j).equals("2"))
							temp.get(j-1)[1]++;
						else temp.get(j-1)[2]++;
					}
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
		if(total!=0){
			for(int i=0;i<11;i++){
				temp.get(i)[0]=temp.get(i)[0]/total;
				temp.get(i)[1]=temp.get(i)[1]/total;
				temp.get(i)[2]=temp.get(i)[2]/total;
			}
		}
		return temp;
	}
	public ArrayList<float[]> executeSuyang(String sql,String sql2,Connection con){
		ArrayList<float[]> temp = new ArrayList<float[]>();
		int total=0;
		float t[]=new float[4];
		float t2[]=new float[5];
		try {
			state = con.prepareStatement(sql2);
			System.out.println("sqltotal:"+sql2);
			set  = state.executeQuery();
			set.next();
			total = set.getInt(1);
			for(int i=1;i<=8;i++){//遍历8个表
				state = con.prepareStatement(sql);
				state.setInt(1, i);
				set = state.executeQuery();
				String str;
				while(set.next()){
					//t[0---3]分别代表层级一的A，B+,B,C
					str=set.getString("l1");
					if(str.equals("1"))
						t[0]++;
					else if(str.equals("2"))
						t[1]++;
					else if(str.equals("3"))
						t[2]++;
					else t[3]++;
					//t2[0---4]分别代表层级二的A++,A+,A,B+,B
					str =set.getString("l2");
					if(str.equals("5"))
						t2[0]++;
					else if(str.equals("6"))
						t2[1]++;
					else if(str.equals("7"))
						t2[2]++;
					else if(str.equals("8"))
						t2[3]++;
					else t2[4]++;
					
				}
			}
			if(total!=0){
				for(int j=0;j<4;j++)//转换为百分率
					t[j]=t[j]/total;
				for(int j=0;j<5;j++)//转换为百分率
					t2[j]=t2[j]/total;
			}
			temp.add(t);
			temp.add(t2);
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
		
		return temp;
	}
	public ArrayList<float[]> execute24(String sql,String sql2,Connection con){
		ArrayList<float[]> temp = new ArrayList<float[]>();
		int total=0;
		try {
			state = con.prepareStatement(sql2);
			System.out.println("sqltotal:"+sql2);
			set  = state.executeQuery();
			set.next();
			total = set.getInt(1);
			for(int i=1;i<=8;i++){//遍历8个表
				state = con.prepareStatement(sql);
				state.setInt(1, i);
				set = state.executeQuery();
				for(int k=0;k<24;k++){
					float t[]= new float[3];
					temp.add(t);
				}
				while(set.next()){
					String prefix="a";
					for(int j=1;j<=24;j++){
						if(set.getString(prefix+j).equals("1"))
							temp.get(j-1)[0]++;
						else if(set.getString(prefix+j).equals("2"))
							temp.get(j-1)[1]++;
						else temp.get(j-1)[2]++;
					}
				}
			}
			System.out.println("************************");
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
		if(total!=0){
			for(int i=0;i<24;i++){
				temp.get(i)[0]=temp.get(i)[0]/total;
				temp.get(i)[1]=temp.get(i)[1]/total;
				temp.get(i)[2]=temp.get(i)[2]/total;
			}
		}
		return temp;
	}
}
   