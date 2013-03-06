package edu.sdu.online.rengepeiyang.beans;

import java.util.Date;

public class StudentBean implements UserBean{
	private String sid;
	private String sname;
	private String idnum;
	private String gender;
	private String spwd;
	private boolean modifypwd;
	private String nation;
	private Date birthday;
	private String pstatus;
	private String homeaddr;
	private Integer depid;
	private String depname;
	private String major; //专业
	private Integer majorid; //专业编号
	private String clazz; //班级信息
	private Integer clazzid; //专业编号
	private int jointime; //入学年份
	private int years; //学制
	private String tname;
	private Integer tid;
	private boolean isleader;
	private boolean isgrade;
	private int grade;
	private boolean isreg;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getSpwd() {
		return spwd;
	}
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getHomeaddr() {
		return homeaddr;
	}
	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public boolean isIsleader() {
		return isleader;
	}
	public void setIsleader(boolean isleader) {
		this.isleader = isleader;
	}
	public boolean isIsgrade() {
		return isgrade;
	}
	public void setIsgrade(boolean isgrade) {
		this.isgrade = isgrade;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public boolean isIsreg() {
		return isreg;
	}
	public void setIsreg(boolean isreg) {
		this.isreg = isreg;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public int getJointime() {
		return jointime;
	}
	public void setJointime(int jointime) {
		this.jointime = jointime;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public Integer getMajorid() {
		return majorid;
	}
	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}
	public Integer getClazzid() {
		return clazzid;
	}
	public void setClazzid(Integer clazzid) {
		this.clazzid = clazzid;
	}
	
	public boolean isModifypwd() {
		return modifypwd;
	}
	public void setModifypwd(boolean modifypwd) {
		this.modifypwd = modifypwd;
	}
	public String toString() {
		return "years:"+years+"\n"+
				"pstatus:"+pstatus+"\n"+
				"classid:"+clazzid+"\n"+
				"jointime:"+jointime+"\n";
	}
	
}
