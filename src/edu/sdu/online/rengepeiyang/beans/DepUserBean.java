package edu.sdu.online.rengepeiyang.beans;

public class DepUserBean implements UserBean{
	private int depuid;
	private String depuname;
	private int depid;
	private String pwd;
	private int authority;
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
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
}
