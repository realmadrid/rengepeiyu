package edu.sdu.online.rengepeiyang.processer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import edu.sdu.online.rengepeiyang.beans.QuestionBean;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class QuestionProcesser {
	

	private int[] meixiangtishu = {3,2,3,1,3,3,3,2,2,1,1};
	private int[] meileixiangshu = {3,2,6};
	private static QuestionProcesser instance = new QuestionProcesser();
	
	private QuestionProcesser() {
		
	}
	public static QuestionProcesser getInstance() {
		return instance;
	}

	/**
	 * 第一项：8个题
	 * 		  1.1（1-3）
	 * 		  1.2（4-5）
	 * 		  1.3（6-8）
	 * 第二项：4个题
	 *        2.1(9)
	 *        2.2(10-12)
	 * 第三项：12个题
	 * 		  3.1（13-15）
	 * 		  3.2（16-18）
	 * 		  3.3（19-20）
	 * 	      3.4（21-22）
	 *        3.5（23）
	 *        3.6（24）
	 * @author pingguoilove
	 *
	 */
	public int[] initEleven(String answer) {
	    int[] level = new int[11];; //每一项典型表现的情况
		int index = 0; //记录第几题
		int index1 = 0; //记录第几项典型行为
		int temp1 = 0;
		int temp2 = 0;
		int l1 = 0;
		int l2 = 0;
		int l3 = 0;
		char temp = ' ';
		for(int i=0; i<meileixiangshu.length; i++) { //三类：i表示第几类
			temp1 = index1;
			for(int j=temp1; j<temp1+meileixiangshu[i]; j++) { //每一类多少项典型行为：j记录第几项典型行为
				temp2 = index;
				l1 = 0;
				l2 = 0; 
				l3 = 0;
				for(int k=temp2; k<temp2+meixiangtishu[j]; k++) { //k记录第几题
					temp = answer.charAt(index);  //判断每一类典型行为的题目选择的优,良,弱个数
					if(temp == '1') {
						l1 ++; //优秀
					} else if(temp == '2') {
						l2 ++; //良好
					} else {
						l3 ++; //弱项
					}
					index ++;
				}
				//判断每一典型行为的级别
				if(l2==0 && l3==0) {
					level[index1] = 1; //优 
				} else if(l3 != 0) {
					level[index1] = 3; //弱
				} else {
					level[index1] = 2; //良
				}
				index1 ++;
			}
		}
		return level;
	}
	public int[] getState(int states[]) {
		int res[] = new int[2];
		int result = -1;
		int level1 = 0; //优
		int level2 = 0; //良
		int level3 = 0; //弱
		//统计前两类，即前5项典型表现的情况
		for(int i=0; i<5; i++) {
			if(states[i] == 1) {
				level1 ++; //优
			} else if(states[i] == 2) {
				level2 ++; //良
			} else {
				level3 ++; //弱
			}
		}
		//根据前两类的表现情况
		if(level3 >0) { //有弱项
			result = 4;
		} else { //无弱项，可以继续
			if(level1 == 5) { //优=5
				result = 1;
			} else if(level1 >= 3) { //优>=3，且无弱项
				result = 2;
			} else { //无弱项,A<3
				result = 3;
			}
		}
	/*	if(result == 4) //不能继续了，一，二类表现中有C
			return result;*/
		
		//在一，二类判断的基础上
		level1 = 0;
		level2 = 0;
		level3 = 0;
		for(int i=5; i<11; i++) {
			if(states[i] == 1) {
				level1 ++; //优
			} else if(states[i] == 2) {
				level2 ++; //良
			} else {
				level3 ++; //弱
			}
		}
		System.out.println("level1="+level1+"level2="+level2+"level3="+level3);
		/*if(result == 4)  //第一二类结果中出现了C，则不需要继续往下了，结果就是4
			return result;*/
		res[0] = result;

		//在第一，二类结果+第三类结果来计算最终结果
		if(result == 1) {
			if(level1 == 6) {
				result = 5;
			} else {
				if(level3==0) {
					if(level1>=3)result = 6;
					else if(level3==0)result=7;
				} 
			}
		} 
		if(result<5){
			if(result<=2){
				if(level3<=2){
					result=8;
				}
			}
			if(result<=3){
				if(level3<=4)result=9;
			}
		}
		res[1] = result;
		return res;
	}
	/*
	 * 应用入学时间算年级
	 */
	public static int calcGrade(int jointime) {
		int currentYear = -1;
		int joinYear = -1;
		int grade = 0; //年级
		int currentMonth = 0;
		Calendar ca = Calendar.getInstance();
		currentYear = ca.get(Calendar.YEAR); //获取年份
		currentMonth = ca.get(Calendar.MONTH); //获取月份
		joinYear = jointime;
		grade = currentYear - joinYear;
		if(currentMonth>=9)
			grade = grade + 1;
		return grade;
	}
	//存储结果：由于数据库被切割，因此需要根据学号判断学生的年级，再选择表来存储
	public int[] storeResult(Connection conn,QuestionBean questions,int grade,int depid) {
		int res[] = new int[2];
		res[0]= -1;
		res[1] = -1;
		if(grade<0||grade>8)
			return res;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "";
		String atemp = "";
		String stemp = "";
		int eleven[] = initEleven(questions.getAnswers()); //11个典型行为
		int level[] = getState(eleven); //等级 level[0]是第1,2类的等级，level[1]是第三类的等级，如果level[0]==level[1]则说明没有成功人格素养
		for(int i=0; i<24; i++) {
			atemp +="a"+(i+1)+",";
		}
		for(int i=0; i<10; i++) {
			stemp +="s"+(i+1)+",";
		}
		stemp +="s11";
		int currentYear = -1;
		Calendar ca = Calendar.getInstance();
		currentYear = ca.get(Calendar.YEAR); //获取年份
//		System.out.println("grade ==== joinYear"+grade+"||"+joinYear);
		sql = "insert into gradeinfo_"+grade+"(sid,gradeyear,l1,l2,"+atemp+stemp+") "+
			  "values(?,?,?,?,?,?,?,?,?,?,"+
			  	     "?,?,?,?,?,?,?,?,?,?,"+
			  	     "?,?,?,?,?,?,?,?,?,?,"+
			  	     "?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questions.getSid());
			pstmt.setInt(2, currentYear);
			pstmt.setInt(3, level[0]);
			pstmt.setInt(4, level[1]);
			pstmt.setString(5, questions.getQuestion1()+"");
			pstmt.setString(6, questions.getQuestion2()+"");
			pstmt.setString(7, questions.getQuestion3()+"");
			pstmt.setString(8, questions.getQuestion4()+"");
			pstmt.setString(9, questions.getQuestion5()+"");
			pstmt.setString(10, questions.getQuestion6()+"");
			pstmt.setString(11, questions.getQuestion7()+"");
			pstmt.setString(12, questions.getQuestion8()+"");
			pstmt.setString(13, questions.getQuestion9()+"");
			pstmt.setString(14, questions.getQuestion10()+"");
			pstmt.setString(15, questions.getQuestion11()+"");
			pstmt.setString(16, questions.getQuestion12()+"");
			pstmt.setString(17, questions.getQuestion13()+"");
			pstmt.setString(18, questions.getQuestion14()+"");
			pstmt.setString(19, questions.getQuestion15()+"");
			pstmt.setString(20, questions.getQuestion16()+"");
			pstmt.setString(21, questions.getQuestion17()+"");
			pstmt.setString(22, questions.getQuestion18()+"");
			pstmt.setString(23, questions.getQuestion19()+"");
			pstmt.setString(24, questions.getQuestion20()+"");
			pstmt.setString(25, questions.getQuestion21()+"");
			pstmt.setString(26, questions.getQuestion22()+"");
			pstmt.setString(27, questions.getQuestion23()+"");
			pstmt.setString(28, questions.getQuestion24()+"");
			pstmt.setString(29, eleven[0]+"");
			pstmt.setString(30, eleven[1]+"");
			pstmt.setString(31, eleven[2]+"");
			pstmt.setString(32, eleven[3]+"");
			pstmt.setString(33, eleven[4]+"");
			pstmt.setString(34, eleven[5]+"");
			pstmt.setString(35, eleven[6]+"");
			pstmt.setString(36, eleven[7]+"");
			pstmt.setString(37, eleven[8]+"");
			pstmt.setString(38, eleven[9]+"");
			pstmt.setString(39, eleven[10]+"");
			result = pstmt.executeUpdate();
			if(result >0) {
				sql = "update student set isgrade = 1 where sid = ?"; //修改是否已填问卷的标志
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, questions.getSid());
				System.out.println("affect rows:"+pstmt.executeUpdate());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
			
		}
		if(result >0)
			return level;
		else
			return res;
	}
	public QuestionBean getAnswers(Connection conn,String sid,int grade) {
		QuestionBean qb = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String answers = "";
		sql = "select a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,gradeyear from gradeinfo_"+grade+" where sid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qb = new QuestionBean();
				qb.setQuestion1(rs.getInt("a1"));
				qb.setQuestion2(rs.getInt("a2"));
				qb.setQuestion3(rs.getInt("a3"));
				qb.setQuestion4(rs.getInt("a4"));
				qb.setQuestion5(rs.getInt("a5"));
				qb.setQuestion6(rs.getInt("a6"));
				qb.setQuestion7(rs.getInt("a7"));
				qb.setQuestion8(rs.getInt("a8"));
				qb.setQuestion9(rs.getInt("a9"));
				qb.setQuestion10(rs.getInt("a10"));
				qb.setQuestion11(rs.getInt("a11"));
				qb.setQuestion12(rs.getInt("a12"));
				qb.setQuestion13(rs.getInt("a13"));
				qb.setQuestion14(rs.getInt("a14"));
				qb.setQuestion15(rs.getInt("a15"));
				qb.setQuestion16(rs.getInt("a16"));
				qb.setQuestion17(rs.getInt("a17"));
				qb.setQuestion18(rs.getInt("a18"));
				qb.setQuestion19(rs.getInt("a19"));
				qb.setQuestion20(rs.getInt("a20"));
				qb.setQuestion21(rs.getInt("a21"));
				qb.setQuestion22(rs.getInt("a22"));
				qb.setQuestion23(rs.getInt("a23"));
				qb.setQuestion24(rs.getInt("a24"));
				qb.setGradeyear(rs.getInt("gradeyear"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
			DBUtil.close(conn);
		}
		return qb;
	}
	//修改测评结果
	public int[] modifyAnswers(Connection conn,QuestionBean qb,int grade) {
		int res[] = new int[2];
		res[0] = -1;
		res[1] = -1;
		PreparedStatement pstmt = null;
		int result = 0;
		String atemp = "";
		String stemp = "";
		int eleven[] = initEleven(qb.getAnswers()); //11个典型行为
		int level[] = getState(eleven); //等级 level[0]是第1,2类的等级，level[1]是第三类的等级，如果level[0]==level[1]则说明没有成功人格素养
		for(int i=0; i<24; i++) {
			atemp +="a"+(i+1)+"=?,";
		}
		for(int i=0; i<10; i++) {
			stemp +="s"+(i+1)+"=?,";
		}
		stemp +="s11=?";
		String sql = "update gradeinfo_"+grade+" set l1=?,l2=?,"+atemp+stemp+" where sid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, level[0]);
			pstmt.setInt(2, level[1]);
			pstmt.setString(3, qb.getQuestion1()+"");
			pstmt.setString(4, qb.getQuestion2()+"");
			pstmt.setString(5, qb.getQuestion3()+"");
			pstmt.setString(6, qb.getQuestion4()+"");
			pstmt.setString(7, qb.getQuestion5()+"");
			pstmt.setString(8, qb.getQuestion6()+"");
			pstmt.setString(9, qb.getQuestion7()+"");
			pstmt.setString(10, qb.getQuestion8()+"");
			pstmt.setString(11, qb.getQuestion9()+"");
			pstmt.setString(12, qb.getQuestion10()+"");
			pstmt.setString(13, qb.getQuestion11()+"");
			pstmt.setString(14, qb.getQuestion12()+"");
			pstmt.setString(15, qb.getQuestion13()+"");
			pstmt.setString(16, qb.getQuestion14()+"");
			pstmt.setString(17, qb.getQuestion15()+"");
			pstmt.setString(18, qb.getQuestion16()+"");
			pstmt.setString(19, qb.getQuestion17()+"");
			pstmt.setString(20, qb.getQuestion18()+"");
			pstmt.setString(21, qb.getQuestion19()+"");
			pstmt.setString(22, qb.getQuestion20()+"");
			pstmt.setString(23, qb.getQuestion21()+"");
			pstmt.setString(24, qb.getQuestion22()+"");
			pstmt.setString(25, qb.getQuestion23()+"");
			pstmt.setString(26, qb.getQuestion24()+"");
			pstmt.setString(27, eleven[0]+"");
			pstmt.setString(28, eleven[1]+"");
			pstmt.setString(29, eleven[2]+"");
			pstmt.setString(30, eleven[3]+"");
			pstmt.setString(31, eleven[4]+"");
			pstmt.setString(32, eleven[5]+"");
			pstmt.setString(33, eleven[6]+"");
			pstmt.setString(34, eleven[7]+"");
			pstmt.setString(35, eleven[8]+"");
			pstmt.setString(36, eleven[9]+"");
			pstmt.setString(37, eleven[10]+"");
			pstmt.setString(38, qb.getSid());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		if(result>0)
			return level;
		else //修改失败
			return res;
	}
}
