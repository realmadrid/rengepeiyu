package edu.sdu.online.rengepeiyang.actions;

import java.sql.Connection;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.beans.ConfigBean;
import edu.sdu.online.rengepeiyang.beans.QuestionBean;
import edu.sdu.online.rengepeiyang.beans.User;
import edu.sdu.online.rengepeiyang.processer.QuestionProcesser;
import edu.sdu.online.rengepeiyang.utils.DBUtil;
/**
 * 
 * @author pingguoilove
 *
 */
public class QuestionAction extends ActionSupport{
	private QuestionBean qb;
	private QuestionProcesser qp;
	private int grade;
	public QuestionBean getQb() {
		return qb;
	}
	public void setQb(QuestionBean qb) {
		this.qb = qb;
	}
	
	public QuestionProcesser getQp() {
		return qp;
	}
	public void setQp(QuestionProcesser qp) {
		this.qp = qp;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/*
	 * 存储测评结果方法
	 */
	public String selectAnswer() throws Exception {
		Connection conn = DBUtil.getConnection();
		qp = QuestionProcesser.getInstance();
		int result[] = qp.storeResult(conn, qb,((User)ActionContext.getContext().getSession().get("user")).getGrade(),((User)ActionContext.getContext().getSession().get("user")).getDepid());
		((User)ActionContext.getContext().getSession().get("user")).setIsgrade(true);
		if(result[0]>0) {
			String describ = "";
			if(result[0]!=result[1]) {
				describ +="您的测评等级为 "+ConfigBean.STAT_LEVEL[result[1]]+",您拥有"+ConfigBean.STAT_DESCRIB1[result[0]]+" 并且 "+ConfigBean.STAT_DESCRIB1[result[1]];
			} else {
				describ +="您的测评等级为 "+ConfigBean.STAT_LEVEL[result[0]]+"您拥有 "+ConfigBean.STAT_DESCRIB1[result[0]];
			}
			ActionContext.getContext().put("describ", describ);
			return SUCCESS; //存储成功
		}
		else
			return ERROR; //存储失败
	}
	/*
	 * 加载测评结果
	 * 方法有问题，需要修改//TODO
	 * @param uid,grade 用户id和年级（由于数据库做了切割，将不同年级的测评信息放在了不同的表中，因此需要将年级信息传入，以便选择正确的数据库表操作）
	 */
	public String loadAnswer() throws Exception {
		Connection conn = DBUtil.getConnection();
		qp = QuestionProcesser.getInstance();
		QuestionBean qb = qp.getAnswers(conn, ((User)ActionContext.getContext().getSession().get("user")).getSid(), grade);
		if(qb != null) {
		setGrade(grade); //将年级信息向下传递
		setQb(qb);
		return SUCCESS;
		} else {
			ActionContext.getContext().put("tip", "未找到您"+ConfigBean.GRADE_INFO[grade]+"相关信息");
			return ERROR;
		}
	}
	/*
	 * 修改测评结果
	 * @param qb,grade 其中qb是一份测评结果，包含了被测评学生的学号，grade是
	 */
	public String modifyAnswer() throws Exception {
		Connection conn = DBUtil.getConnection();
		qp = QuestionProcesser.getInstance();
		int result[] = qp.modifyAnswers(conn, qb, grade);
//		System.out.println("your grade is:"+grade+" and your sid is:"+qb.getSid());
		if(result[0]>0) {
			String describ = "";
			if(result[0]!=result[1]) {
				describ +="修改测评选项后：您的测评等级为 "+ConfigBean.STAT_LEVEL[result[1]]+",您拥有"+ConfigBean.STAT_DESCRIB1[result[0]]+" 并且 "+ConfigBean.STAT_DESCRIB1[result[1]];
			} else {
				describ +="修改测评选项后：您的测评等级为 "+ConfigBean.STAT_LEVEL[result[0]]+"您拥有 "+ConfigBean.STAT_DESCRIB1[result[0]];
			}
			ActionContext.getContext().put("describ", describ);
			return SUCCESS; //存储成功
		}
		else {
			ActionContext.getContext().put("tip", "修改本年度测评结果失败！");
			return ERROR; //存储失败
		}
	}
	public String loadeQuestion() {
		return SUCCESS;
	}
}
