package edu.sdu.online.rengepeiyang.beans;

import java.util.Calendar;

/**
 * 第一类：8个题
 * 		  1.1（1-3）
 * 		  1.2（4-5）
 * 		  1.3（6-8）
 * 第二类：4个题
 *        2.1(9)
 *        2.2(10-12)
 * 第三类：12个题
 * 		  3.1（13-15）
 * 		  3.2（16-18）
 * 		  3.3（19-20）
 * 	      3.4（21-22）
 *        3.5（23）
 *        3.6（24）
 * @author pingguoilove
 *
 */
public class QuestionBean {
	private int question1;
	private int question2;
	private int question3;
	private int question4;
	private int question5;
	private int question6;
	private int question7;
	private int question8;
	private int question9;
	private int question10;
	private int question11;
	private int question12;
	private int question13;
	private int question14;
	private int question15;
	private int question16;
	private int question17;
	private int question18;
	private int question19;
	private int question20;
	private int question21;
	private int question22;
	private int question23;
	private int question24;
	private String answers;
	private int eleven[];
	private int level[];
	private String sid;
	private int gradeyear;
    private boolean modify;
	public int getQuestion1() {
		return question1;
	}
	public void setQuestion1(int question1) {
		this.question1 = question1;
	}
	public int getQuestion2() {
		return question2;
	}
	public void setQuestion2(int question2) {
		this.question2 = question2;
	}
	public int getQuestion3() {
		return question3;
	}
	public void setQuestion3(int question3) {
		this.question3 = question3;
	}
	public int getQuestion4() {
		return question4;
	}
	public void setQuestion4(int question4) {
		this.question4 = question4;
	}
	public int getQuestion5() {
		return question5;
	}
	public void setQuestion5(int question5) {
		this.question5 = question5;
	}
	public int getQuestion6() {
		return question6;
	}
	public void setQuestion6(int question6) {
		this.question6 = question6;
	}
	public int getQuestion7() {
		return question7;
	}
	public void setQuestion7(int question7) {
		this.question7 = question7;
	}
	public int getQuestion8() {
		return question8;
	}
	public void setQuestion8(int question8) {
		this.question8 = question8;
	}
	public int getQuestion9() {
		return question9;
	}
	public void setQuestion9(int question9) {
		this.question9 = question9;
	}
	public int getQuestion10() {
		return question10;
	}
	public void setQuestion10(int question10) {
		this.question10 = question10;
	}
	public int getQuestion11() {
		return question11;
	}
	public void setQuestion11(int question11) {
		this.question11 = question11;
	}
	public int getQuestion12() {
		return question12;
	}
	public void setQuestion12(int question12) {
		this.question12 = question12;
	}
	public int getQuestion13() {
		return question13;
	}
	public void setQuestion13(int question13) {
		this.question13 = question13;
	}
	public int getQuestion14() {
		return question14;
	}
	public void setQuestion14(int question14) {
		this.question14 = question14;
	}
	public int getQuestion15() {
		return question15;
	}
	public void setQuestion15(int question15) {
		this.question15 = question15;
	}
	public int getQuestion16() {
		return question16;
	}
	public void setQuestion16(int question16) {
		this.question16 = question16;
	}
	public int getQuestion17() {
		return question17;
	}
	public void setQuestion17(int question17) {
		this.question17 = question17;
	}
	public int getQuestion18() {
		return question18;
	}
	public void setQuestion18(int question18) {
		this.question18 = question18;
	}
	public int getQuestion19() {
		return question19;
	}
	public void setQuestion19(int question19) {
		this.question19 = question19;
	}
	public int getQuestion20() {
		return question20;
	}
	public void setQuestion20(int question20) {
		this.question20 = question20;
	}
	public int getQuestion21() {
		return question21;
	}
	public void setQuestion21(int question21) {
		this.question21 = question21;
	}
	public int getQuestion22() {
		return question22;
	}
	public void setQuestion22(int question22) {
		this.question22 = question22;
	}
	public int getQuestion23() {
		return question23;
	}
	public void setQuestion23(int question23) {
		this.question23 = question23;
	}
	public int getQuestion24() {
		return question24;
	}
	public void setQuestion24(int question24) {
		this.question24 = question24;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getGradeyear() {
		return gradeyear;
	}
	
	public int[] getEleven() {
		return eleven;
	}
	public void setEleven(int[] eleven) {
		this.eleven = eleven;
	}
	public int[] getLevel() {
		return level;
	}
	public void setLevel(int[] level) {
		this.level = level;
	}
	/*
	 * 只能修改当年的结果，具体判断是否可以修改的方法可以重新设定
	 */
	public boolean isModify() {
		int currentYear;
		Calendar ca = Calendar.getInstance();
		currentYear = ca.get(Calendar.YEAR); //获取年份
		if(currentYear != gradeyear) { 
			modify = false;
		} else {
			modify = true;
		}
		return modify;
	}
	public void setModify(boolean modify) {
		this.modify = modify;
	}
	public void setGradeyear(int gradeyear) {
		this.gradeyear = gradeyear;
	}
	public String getAnswers() {
		answers = ""+question1+question2+question3+question4+question5+question6+question7+question8+question9+question10+
				  question11+question12+question13+question14+question15+question16+question17+question18+question19+question20+
				  question21+question22+question23+question24;
		return answers;
	}
	public String getSplitAnswers() {
		answers = ""+question1+","+question2+","+question3+","+question4+","+question5+","+question6+","+question7+","+question8+","+question9+","+question10+","+
				  question11+","+question12+","+question13+","+question14+","+question15+","+question16+","+question17+","+question18+","+question19+","+question20+","+
				  question21+","+question22+","+question23+","+question24;
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public String toSring(){
		return "qt1"+question1+"\n"+
		"qt2"+question2+"\n"+
		"qt3"+question3+"\n"+
		"qt4"+question4+"\n"+
		"qt5"+question5+"\n"+
		"qt6"+question6+"\n"+
		"qt7"+question7+"\n"+
		"qt8"+question8+"\n"+
		"qt9"+question9+"\n"+
		"qt10"+question10+"\n"+
		"qt11"+question11+"\n"+
		"qt12"+question12+"\n"+
		"qt13"+question13+"\n"+
		"qt14"+question14+"\n"+
		"qt15"+question15+"\n"+
		"qt16"+question16+"\n"+
		"qt17"+question17+"\n"+
		"qt18"+question18+"\n"+
		"qt91"+question19+"\n"+
		"qt20"+question20+"\n"+
		"qt21"+question21+"\n"+
		"qt22"+question22+"\n"+
		"qt23"+question23+"\n"+
		"qt24"+question24+"\n"+
		"sid===="+sid;
	}
}
