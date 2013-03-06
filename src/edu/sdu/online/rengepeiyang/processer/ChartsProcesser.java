package edu.sdu.online.rengepeiyang.processer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.sdu.online.rengepeiyang.beans.DataBean;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class ChartsProcesser {
	
	int FC_ColorCounter=0;
	
	String[] arr_FCColors = new String[]{
		"1941A5",
		"AFD8F8",
		"F6BD0F",
		"8BBA00",
		"A66EDD",
		"F984A1",
		"CCCC00", 
		"999999",
		"0099CC",
		"FF0000", 
		"006F00", 
		"0099FF", 
		"FF66CC", 
		"669966",
		"7C7CB4",
		"FF9933",
		"9900FF",
		"99FFCC",
		"CCCCFF",
		"669900",
	};
	/*
	 "1941A5"; //Dark Blue
	 "CCCC00"; //Chrome Yellow+Green
	"999999"; //Grey
	"0099CC"; //Blue Shade
	"FF0000"; //Bright Red 
	"006F00"; //Dark Green
	"0099FF"; //Blue (Light)
	"FF66CC"; //Dark Pink
	"669966"; //Dirty green
	"7C7CB4"; //Violet shade of blue
	"FF9933"; //Orange
	"9900FF"; //Violet
	"99FFCC";//Blue+Green Light
	"CCCCFF"; //Light violet
	"669900"; //Shade of green
	*/
	
	//getFCColor method helps return a color from arr_FCColors array. It uses
	//cyclic iteration to return a color from a given index. The index value is
	//maintained in FC_ColorCounter
	private static ChartsProcesser instance = new ChartsProcesser();
	private ChartsProcesser () {}
	public static ChartsProcesser getInstance() {
		return instance;
	}
	public String getFCColor() {
		//Update index
		FC_ColorCounter += 1;
		//Return color
		return arr_FCColors[FC_ColorCounter % arr_FCColors.length];
		
	}
	public String getData1ById(Connection conn,String sid,int grade) {
		String answers = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select answers from gradeinfo_"+grade+" where sid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			DataBean data = null;
			if(rs.next()) {
				data = new DataBean();
				data.setDataStr(rs.getString("answers"));
				data.setCaption(sid+"测评情况");
				data.setxName("题号");
				data.setyName("选项");
				data.setDecimalPrecision(false);
				data.setYmax(3);
				data.setYmin(0);
				data.setNumDivLines(2);
				answers = getXMLDataStr1(data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return answers;
	}
	public String getXMLDataStr1(DataBean data) {
		
		StringBuffer xml = new StringBuffer();
		String temp = "<graph caption='"+data.getCaption()+"' ";
		xml.append("<?xml version=1.0 encoding='UTF-8'?>\n");
		if(data.getSubCaption() != null)
			temp +=" subcaption='"+data.getSubCaption()+"'";
		temp +=" xAxisName='"+data.getxName()+"' yAxisName='"+data.getyName()+"' ";
		if(!data.isDecimalPrecision())
			temp +=" decimalPrecision='0'";
		temp +=" yaxismaxvalue='"+data.getYmax()+"' yaxisminvalue='"+data.getYmin()+"'"+"numDivLines='"+data.getNumDivLines()+"'";
		temp +=">\n";
		xml.append(temp);
		int dataArr[] = toIntArray(data.getDataStr());
		
		for(int i=0; i<dataArr.length; i++)
			xml.append("\t\t<set name='NO."+(i+1)+"' value='"+dataArr[i]+"' color='"+getFCColor()+"'/>\n");
		
		xml.append("</graph>");
		System.out.println(xml.toString());
		return xml.toString();
	}
	//将字符串翻译成整型数组
	public int[] toIntArray(String data) {
		int arr[] = new int[data.length()];
		for(int i=0; i<arr.length; i++)
			arr[i] = 0;
		for(int i=0; i<data.length(); i++) {
			arr[i] = Integer.valueOf(data.charAt(i)+"");
		}
		return arr;
	}
}
