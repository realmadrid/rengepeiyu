package edu.sdu.online.rengepeiyang.actions;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.processer.QueryProcesser;
import net.sf.json.JSONObject;
public class QueryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int depid;
    private int clazz;
    private int  grade;
    private int tid;
    private String gradename;
    private String tname;
    private String dname;
    private String cname;
    private String result ;
    Map<String, String> map = new HashMap<String, String>();
    private ArrayList<float[]> resultcpl;//参评率
    private ArrayList<float[]> result11;//等级比率
    private ArrayList<float[]> resultSuyang;// 素养层级
	private ArrayList<float[]> result24;// 24道题
     private String pagecpl;
     private String page11;
     private String pageSuyang;
     private String page24;
    private QueryProcesser processer;
    
	public void setDepid(int depid) {
		this.depid = depid;
	}


	public void setClazz(int clazz) {
		this.clazz = clazz;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public String getResult() {
		return result;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public void setGradename(String gradename) {
		this.gradename = gradename;
	}


	public void setTname(String tname) {
		this.tname = tname;
	}


	public void setDname(String dname) {
		this.dname = dname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getCPL(){
		ActionContext.getContext().getSession().put("state", "cpl");
		saveInfo();
		System.out.println("cname,cpl:  "+cname);
    	processer = new QueryProcesser();
    	processer.setClazz(clazz);
    	processer.setDepid(depid);
    	processer.setGrade(grade);
    	processer.setTid(tid);
    	resultcpl = processer.getCPL();
    	DecimalFormat df1=(DecimalFormat)NumberFormat.getInstance();
    	DecimalFormat df2=(DecimalFormat)NumberFormat.getInstance();
        df1.setMaximumFractionDigits(0);
        df2.setMaximumFractionDigits(4);
        //System.out.println("tid:  "+tid);
		pagecpl="<table width='100%' border='1'>" +
				"<tr>" +
				"<td width='50%' height='35' align='right'>总人数</td>" +
				"<td width='50%'>"+df1.format(resultcpl.get(0)[0])+"</td>" +
				"</tr>" +
				"<tr>" +
				"<td height='31' align='right'>总参评人数</td>" +
				"<td>"+df1.format(resultcpl.get(0)[1])+"</td>" +
				"</tr>" +
				"<tr>" +
				"<td height='34' align='right'>参评率</td>" +
				"<td>"+df2.format(resultcpl.get(0)[2])+"</td>" +
				"</tr>" +
				"</table>";
		try {
			map.put("pagecpl",  new String(pagecpl.getBytes(), "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 将要返回的map对象进行json处理
		JSONObject jo = JSONObject.fromObject(map);
		result = jo.toString();
    	return SUCCESS;
    }
    public String get11(){
    	ActionContext.getContext().getSession().put("state", "11");
    	saveInfo();
    	System.out.println("cname,11 :  "+cname);
    	processer = new QueryProcesser();
    	processer.setClazz(clazz);
    	processer.setDepid(depid);
    	processer.setGrade(grade);
    	processer.setTid(tid);
    	result11= processer.get11();
    	DecimalFormat df2=(DecimalFormat)NumberFormat.getInstance();
    	 df2.setMaximumFractionDigits(4);
    	page11="<table width='100%' border='1'>" +
    			"<tr>" +
    			"<td width='45'>典型行为</td>"+
        "<td width='45' align='center'>优</td>"+
        "<td width='44' align='center'>良</td>" +
        "<td width='44' align='center'>中</td>" +
        "</tr>" ;
    	for(int i=1;i<=11;i++)
    		page11=page11+
    		"<tr>" +
    		"<td>"+i+"</td>" +
    		"<td width='45' align='center'>"+df2.format(result11.get(i-1)[0])+"</td>" +
    		"<td width='44' align='center'>"+df2.format(result11.get(i-1)[1])+"</td>" +
    		"<td width='44' align='center'>"+df2.format(result11.get(i-1)[2])+"</td>" +
    		"</tr>" ;
    	    
    	try {
			map.put("page11", new String(page11.getBytes(), "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 将要返回的map对象进行json处理
		JSONObject jo = JSONObject.fromObject(map);
		result = jo.toString();
    	return SUCCESS;
    }
   public String getSuyang(){
	   ActionContext.getContext().getSession().put("state", "suyang");
	   saveInfo();
	   System.out.println("cname,suyang :  "+cname);
	processer = new QueryProcesser();
   	processer.setClazz(clazz);
   	processer.setDepid(depid);
   	processer.setGrade(grade);
   	processer.setTid(tid);
   	resultSuyang = processer.getSuyang();
   	DecimalFormat df2=(DecimalFormat)NumberFormat.getInstance();
	 df2.setMaximumFractionDigits(4);
   	pageSuyang=
	"<table   border='1' width='100%'>" +
	"<col width='72' span='3' />" +
	"<tr>" +
	"<td width='71'>素养</td>" +
	"<td width='123'>层级</td>" +
	"<td width='124'>所占比例</td>" +
	"</tr>" +
	"<tr>" +
	"<td rowspan='4' width='71'>健全人格<br />" +
	"素养</td>" +
	"<td>" +
	"A</td>" +
	"<td>"+df2.format(resultSuyang.get(0)[0])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td>" +
	"B+</td>" +
	"<td>"+df2.format(resultSuyang.get(0)[1])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td>" +
	"B</td>" +
	"<td>"+df2.format(resultSuyang.get(0)[2])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td>" +
	"C</td>" +
	"<td>"+df2.format(resultSuyang.get(0)[3])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td rowspan='5' width='71'>成功人格<br />" +
	" 素养</td>" +
	"<td>A++</td>" +
	"<td>"+df2.format(resultSuyang.get(1)[0])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td>" +
	"A+</td>" +
	"<td>"+df2.format(resultSuyang.get(1)[1])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td>A</td>" +
	"<td>"+df2.format(resultSuyang.get(1)[2])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td>" +
	"B+</td>" +
	"<td>"+df2.format(resultSuyang.get(1)[3])+"</td>" +
	"</tr>" +
	"<tr>" +
	"<td> B</td>" +
	"<td>"+df2.format(resultSuyang.get(1)[4])+"</td>" +
	"</tr>" +
	"</table>";
 	try {
		map.put("pageSuyang",  new String(pageSuyang.getBytes(), "gbk"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
 	map.put("test","helloworld");
 	System.out.println("helloworld");
	// 将要返回的map对象进行json处理
	JSONObject jo = JSONObject.fromObject(map);
	result = jo.toString();
	   return SUCCESS;
   }
	public String get24(){
		ActionContext.getContext().getSession().put("state", "24");
		saveInfo();
		System.out.println("cname,24 :  "+cname);
		processer = new QueryProcesser();
    	processer.setClazz(clazz);
    	processer.setDepid(depid);
    	processer.setGrade(grade);
    	processer.setTid(tid);
    	result24= processer.get24();
    	DecimalFormat df2=(DecimalFormat)NumberFormat.getInstance();
    	 df2.setMaximumFractionDigits(4);
		page24="<table width='100%' border='1'>  <tr>" +
				"<td width='25%' align='center'>题号</td>" +
				"<td width='25%' align='center'>A</td>" +
				"<td width='25%' align='center'>B</td>" +
				"<td width='25%' align='center'>C</td>" +
				"</tr>";
		for(int i=0;i<24;i++)
			page24=page24+"<tr>" +
					"<td align='center'>"+(i+1)+"</td>" +
					"<td align='center'>"+df2.format(result24.get(i)[0])+"</td>" +
					"<td align='center'>"+df2.format(result24.get(i)[1])+"</td>" +
					"<td align='center'>"+df2.format(result24.get(i)[2])+"</td>" +
					"</tr>";
		page24=page24+"</table>";
		try {
			map.put("page24", new String(page24.getBytes(), "gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("test","helloworld");
		// 将要返回的map对象进行json处理
		JSONObject jo = JSONObject.fromObject(map);
		result = jo.toString();
		return SUCCESS;
	}
	public void saveInfo(){
		ActionContext.getContext().getSession().put("gradename", gradename);
		ActionContext.getContext().getSession().put("grade", grade+"");
		ActionContext.getContext().getSession().put("dname", dname);
		ActionContext.getContext().getSession().put("depid", depid+"");
		ActionContext.getContext().getSession().put("cname", cname);
		ActionContext.getContext().getSession().put("clazz", clazz+"");
		ActionContext.getContext().getSession().put("tid", tid+"");
		ActionContext.getContext().getSession().put("tname", tname);
	}
}
