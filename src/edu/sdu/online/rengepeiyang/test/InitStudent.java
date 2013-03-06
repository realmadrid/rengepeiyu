package edu.sdu.online.rengepeiyang.test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class InitStudent {
	public static void main(String []args) {
//		String strDate = "19890806";
//		System.out.println(strDate.substring(0,4)+"-"+strDate.substring(4,6)+"-"+strDate.substring(6));
//		initStudent();
//		initDepartment();
//		initTeacher();
//		initDep2Major();
		initMajor2Clazz();
//		loadDep2Major();
	}
	public static void initStudent() {
		
		Connection conn =DBUtil.getConnection();
		Map<String,String> sids = new HashMap<String,String>();
//		String strSids = "";
//		strSids = "200911270001,201111090156,201107290057,201107290038,201107290039,201107290043,201107290040,201100270453,200811090002,200801040039,201100020225,201000800265,S0807290091,201100800343,20101327001";
//		String arrSids[] = strSids.split(",");
//		for(int i=0; i<arrSids.length; i++) {
//			sids.put(arrSids[i], arrSids[i]);
//		}
		PreparedStatement pstmt = null;
		 int res[] = null;
		String sql = "insert into student(sid,gender,sname,idnum,nation,birthday,pstatus,homeaddr,depid,jointime) "+
								  "values(?,?,?,?,?,?,?,?,?,?)";
		try {
		pstmt = conn.prepareStatement(sql);
		 Workbook book = Workbook.getWorkbook(new File("E:\\备忘\\学生在线\\学线项目\\人格培养\\data\\student.xls"));  
		 Sheet sheet = book.getSheet(0); 
		 Cell [] cell2;
	     String sid = "";
	     String gender = "";
	     String sname = "";
	     String idnum = "";
	     String nation = "";
	     Date birthday = null;
	     String pstatus = "";
	     String homeaddr = "";
	     int depid = -1;
         int jointime = -1;
	     String tname = "";
	     String strDate = "";
	     int length = sheet.getRows();
	     SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	     for(int i=1; i<length; i++) {
	     	cell2 = sheet.getRow(i);
	     	sid = cell2[0].getContents();
	     	//if(sids.containsKey(sid)) {
	     	sname = cell2[1].getContents();
	     	idnum = cell2[2].getContents();
	     	gender = cell2[3].getContents();
	     	nation = cell2[4].getContents();
	     	strDate = cell2[5].getContents();
	     	if(strDate.length()>0)
	     	birthday = myFormatter.parse(strDate.substring(0,4)+"-"+strDate.substring(4,6)+"-"+strDate.substring(6));
	     	pstatus = cell2[6]==null?null:cell2[6].getContents();
	     	homeaddr = cell2[7]==null?null:cell2[7].getContents();
	     	depid = Integer.valueOf(cell2[8].getContents());
	     	jointime = Integer.valueOf(cell2[13].getContents());
	     	pstmt.setString(1, sid);
	     	pstmt.setString(2, gender);
	     	pstmt.setString(3, sname);
	     	pstmt.setString(4, idnum.length()>0?idnum:null);
	     	pstmt.setString(5, nation);
	     	pstmt.setDate(6, null == birthday?null:new java.sql.Date(birthday.getTime()));
	     	pstmt.setString(7, pstatus.length()>0?pstatus:null);
	     	pstmt.setString(8, homeaddr.length()>0?homeaddr:null);
	     	pstmt.setInt(9, depid);
	     	pstmt.setInt(10, jointime);
	     	pstmt.addBatch();
	     	//System.out.println(sid+"== "+sname+"=="+idnum+"=="+gender+"=="+nation+"=="+strDate+"=="+pstatus+"=="+homeaddr+"=="+depid+"=="+jointime);
	     	
	     	System.out.println(pstmt.toString());
//	     	pstmt.executeUpdate();
//	     	}
//	     	System.out.println(sid+"  "+sname+"  "+idnum+"  "+gender+"  "+nation+"  "+strDate+"  "+pstatus+"  "+homeaddr+"  "+depid+"  "+clazz);
	     }
	     res = pstmt.executeBatch();
	     book.close();
	     for(int i=0; i<res.length ;i++)
    		 System.out.println(i);
		} catch(SQLException e) {
			e.printStackTrace();
			
		     for(int i=0; i<res.length ;i++)
	    		 System.out.println(i);
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			for(int i=0; i<res.length ;i++)
				if(res[i]==0)
	    		 System.out.println(i);
			try{
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch(Exception e) {
				e.printStackTrace();
		}
		}
	}
	public static void initDepartment() {
		Map<String,Integer> sids = new HashMap<String,Integer>();
		Connection conn =DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 int res[] = null;
		String sql = "insert into department(depid,depname) values(?,?)";
		try {
		 pstmt = conn.prepareStatement(sql);
		 Workbook book = Workbook.getWorkbook(new File("E:\\备忘\\学生在线\\学线项目\\人格培养\\student.xls"));  
		 Sheet sheet = book.getSheet(0); 
		 Cell [] cell2;
	     String sid = "";
	     int length = sheet.getRows();
	     SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	     int depid = -1;
	     String depname = "";
	     for(int i=1; i<length; i++) {
	     	cell2 = sheet.getRow(i);
	     	depid = Integer.valueOf(cell2[8].getContents());
	     	depname = cell2[9].getContents();
	     	if(!sids.containsKey(depname)) {
	     		sids.put(depname, depid);
	     	}
//	     	System.out.println(sid+"  "+sname+"  "+idnum+"  "+gender+"  "+nation+"  "+strDate+"  "+pstatus+"  "+homeaddr+"  "+depid+"  "+clazz);
	     }
	     Iterator itor = sids.keySet().iterator();
	     String dep= "";
	    /* while(itor.hasNext()) {
	    	 dep = itor.next().toString();
	    	 System.out.println(dep+","+sids.get(dep));
	     }*/
	     int temp = -1;
	     for(int i=0; i<sids.size()-4; i++) {
	    	 itor = sids.keySet().iterator();
	    	 while(itor.hasNext()) {
	    		 dep = itor.next().toString();
	    		 if(sids.get(dep) == i+1) {
	    			 temp = i+1;
	    			 break;
	    		 }
	    	 }
	    	 pstmt.setInt(1, temp);
	    	 pstmt.setString(2, dep);
	    	 pstmt.addBatch();
	    	 System.out.println(dep+","+temp);
	     }
	     pstmt.executeBatch();
	     book.close();
		} catch(Exception e) {
			e.printStackTrace();
		     
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch(Exception e) {
				e.printStackTrace();
		}
		}
//		Iterator itor = sids.keySet().iterator();
//		while(itor.hasNext())
//			System.out.println(itor.next());
//		System.out.println("\n size:"+sids.size());
	}
	public static void initTeacher() {
		Connection conn =DBUtil.getConnection();
		PreparedStatement pstmt = null;
		 int res[] = null;
		String sql = "insert into teacher(tid,tname,depid,pwd) values(?,?,?,?)";
		try {
		 pstmt = conn.prepareStatement(sql);
		 Workbook book = Workbook.getWorkbook(new File("E:\\备忘\\学生在线\\学线项目\\人格培养\\data\\111121辅导员考评花名册（含研、兼）.xls"));  
		 Sheet sheet = book.getSheet(0); 
		 Cell [] cell2;
	     String sid = "";
	     int length = sheet.getRows();
	     int depid = -1;
	     int tid = -1;
	     String tname = "";
	     for(int i=1; i<length; i++) {
	     	cell2 = sheet.getRow(i);
	     	tid = Integer.valueOf(cell2[0].getContents());
	     	tname = cell2[2].getContents();
	     	depid = Integer.valueOf(cell2[3].getContents());
	     	pstmt.setInt(1, tid);
	     	pstmt.setString(2, tname);
	     	pstmt.setInt(3, depid);
	     	pstmt.setString(4, "peiyang@online");
	     	pstmt.addBatch();
//	     	System.out.println(sid+"  "+sname+"  "+idnum+"  "+gender+"  "+nation+"  "+strDate+"  "+pstatus+"  "+homeaddr+"  "+depid+"  "+clazz);
	     }
	  
	     pstmt.executeBatch();
	     book.close();
		} catch(Exception e) {
			e.printStackTrace();
		     
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch(Exception e) {
				e.printStackTrace();
		}
			
		}
	}
	/*
	 *200911270001
201111090156
201107290057
201107290038
201107290039
201107290043
201107290040
201100270453
200811090002
200801040039
201100020225
201000800265
S0807290091
201100800343
20101327001

 size:15
	 */
	public static void initDep2Major() {
		HashMap<String, HashMap<String, String>> majors = new HashMap<String,HashMap<String,String>>();
		Connection conn =DBUtil.getConnection();
		Workbook book = null;
		PreparedStatement pstmt = null;
		 int res[] = null;
		String sql = "insert into dep2major(depid,majorname) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			 book = Workbook.getWorkbook(new File("E:\\备忘\\学生在线\\学线项目\\人格培养\\data\\student.xls"));  
			 Sheet sheet = book.getSheet(0); 
			 Cell [] cell2;
		     int length = sheet.getRows();
		     String s_depid=""; //学院编号字符
		     int depid = -1; //学院数字编号
		     String majorname=""; //专业名称
		     HashMap<String,String> temp = null;
		     for(int i=1; i<length; i++) {
		    	 cell2 = sheet.getRow(i); //取出一行
		    	 s_depid=cell2[8].getContents().trim();
		    	 majorname = cell2[11].getContents().trim();
		    	 if(s_depid.length() == 0||majorname.length() ==0)
		    		 continue;
		    	 if(majors.containsKey(s_depid)) { //map中包含该学院信息
		    		 if(!majors.get(s_depid).containsKey(majorname)) { //该学院中不包含该专业信息
		    			 majors.get(s_depid).put(majorname, majorname);
		    		 }
		    	 } else {
		    		 temp = new HashMap<String,String>();
		    		 temp.put(majorname, majorname);
		    		 majors.put(s_depid, temp);
		    	 }
		    	 //System.out.println(s_depid+"        "+cell2[9].getContents()+"         "+majorname);
		     }
		     /*
		      * 现在：<学院编号,<专业,专业>>已经全部存到majors中了，现在取出来，存到数据库中
		      */
		    Set<String> depids = majors.keySet();
		    Set<String> mjs = null;
		    Iterator<String> itd = depids.iterator();
		    Iterator<String> itm = null;
		    String d = ""; //学院编号
		    String m = ""; //专业编号
		    while(itd.hasNext()) {
		    	d = itd.next();
		    	temp = majors.get(d); //取出该学院的所有专业
		    	mjs = temp.keySet();
		    	itm = mjs.iterator(); //学院的key
		    	while(itm.hasNext()) {
		    		//取出一个专业
		    		m = itm.next();
		    		pstmt.setInt(1, Integer.parseInt(d));
		    		pstmt.setString(2, m);
		    		pstmt.addBatch();
		    		System.out.println(d+"  "+m);
		    	}
		    }
		    res = pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (BiffException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		     try {
						if(pstmt != null) {
							pstmt.close();
							pstmt = null;
						}
						if(conn != null) {
							conn.close();
							conn = null;
						}
						book.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void initMajor2Clazz() {
		HashMap<String, HashMap<String, String>> majors = new HashMap<String,HashMap<String,String>>();
		Connection conn =DBUtil.getConnection();
		Workbook book = null;
		PreparedStatement pstmt = null;
		 int res[] = null;
		String sql = "insert into major2clazz(majorid,clazzname) values(?,?)";
		try {
			
			pstmt = conn.prepareStatement(sql);
			 book = Workbook.getWorkbook(new File("E:\\备忘\\学生在线\\学线项目\\人格培养\\data\\student.xls"));  
			 Sheet sheet = book.getSheet(0); 
			 Cell [] cell2;
		     int length = sheet.getRows();
		     String s_mname=""; //专业名称
		     int majorid = -1; //专业编号
		     String clazzname=""; //班级名称
		     HashMap<String,String> temp = null;
		     String depid="";
		     for(int i=1; i<length; i++) {
		    	 cell2 = sheet.getRow(i); //取出一行
		    	 s_mname=cell2[11].getContents().trim();
		    	 clazzname = cell2[12].getContents().trim();
                 depid = cell2[8].getContents().trim();
		    	 if(s_mname.length() == 0||clazzname.length() == 0)
		    		 continue;
		    	 if(majors.containsKey(depid+s_mname)) { //map中包含该专业信息
		    		 if(!majors.get(depid+s_mname).containsKey(depid+clazzname)) { //该专业不包含该班级信息
		    			 majors.get(depid+s_mname).put(depid+clazzname, clazzname);
		    		 }
		    	 } else {
		    		 temp = new HashMap<String,String>();
		    		 temp.put(depid+clazzname, clazzname);
		    		 majors.put(depid+s_mname, temp);
		    	 }
		    	 //System.out.println(s_depid+"        "+cell2[9].getContents()+"         "+majorname);
		     }
		     /*
		      * 现在：<专业名称,<班级,班级>>已经全部存到majors中了，现在取出来，存到数据库中
		      */
		    Set<String> mjs = majors.keySet(); //专业名称
		    Set<String> cls = null; //班级名称
		    Iterator<String> itm = mjs.iterator();
		    Iterator<String> itc = null;
		    String m = ""; //专业名称
		    String c = ""; //班级编号
		    Map<String,Integer> dm = loadDep2Major(); //加载“学院名称-学院编号”
		    while(itm.hasNext()) {
		    	m = itm.next();
		    	temp = majors.get(m); //取出该专业所对的班级
		    	cls = temp.keySet();
		    	itc = cls.iterator(); //班级的key
		    	while(itc.hasNext()) {
		    		//取出一个班级
		    		c = itc.next();
		    		
		    		pstmt.setInt(1, dm.get(m));
		    		pstmt.setString(2, majors.get(m).get(c));
		    		pstmt.addBatch();
			    	
		    		//System.out.println(dm.get(m)+"  "+c);
		    	}
		    }
		    res = pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (BiffException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		     try {
						if(pstmt != null) {
							pstmt.close();
							pstmt = null;
						}
						if(conn != null) {
							conn.close();
							conn = null;
						}
						book.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static Map<String,Integer> loadDep2Major() {
		Map<String,Integer> dm = new HashMap<String,Integer>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select depid,majorid,majorname from dep2major order by majorid";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				System.out.println(rs.getString("depid")+rs.getString("majorname")+"======="+rs.getInt("majorid"));
				dm.put(rs.getInt("depid")+rs.getString("majorname"), rs.getInt("majorid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  try {
				    if(rs != null) {
				    	rs.close();
				    	rs = null;
				    }
					if(pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if(conn != null) {
						conn.close();
						conn = null;
					}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		}
		return dm;
	}
}
