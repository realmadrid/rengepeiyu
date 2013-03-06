package edu.sdu.online.rengepeiyang.beans;

public class User {
	private String sid;
	private String sname;
	private int grade; //年级
	private boolean isgrade; //是否已经填写测评系统了
	private boolean isreg; //是否已经注册
	private int jointime; //入学时间
	private int years;  //学制
	private int tid; //教师编号
	private String tname; //教师姓名
	
	private int depuid; //学院用户编号
	private int depid; //所在学院编号
	private String depuname; //学院用户姓名
	
	private String sysname; //超级用户名
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	private String type; // 用户类型
	private int authority;//权限
	
	private int errorCode; //错误编号：登陆时候用
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getDepuid() {
		return depuid;
	}
	public void setDepuid(int depuid) {
		this.depuid = depuid;
	}
	public String getDepuname() {
		return depuname;
	}
	public void setDepuname(String depuname) {
		this.depuname = depuname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public boolean isIsgrade() {
		return isgrade;
	}
	public void setIsgrade(boolean isgrade) {
		this.isgrade = isgrade;
	}
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
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
	public boolean isIsreg() {
		return isreg;
	}
	public void setIsreg(boolean isreg) {
		this.isreg = isreg;
	}
	
	
	
	
}
