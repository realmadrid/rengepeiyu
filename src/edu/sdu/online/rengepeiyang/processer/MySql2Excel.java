package edu.sdu.online.rengepeiyang.processer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import edu.sdu.online.rengepeiyang.utils.DBUtil;

public class MySql2Excel {
	HSSFWorkbook wb;
	public HSSFWorkbook getWorkbook(){
		return wb;
	}
public MySql2Excel() throws Exception
{
// 获取总列数
//int CountColumnNum = rs.getMetaData().getColumnCount() ;
int CountColumnNum=6;
int i =2 ;
int j,m;
// 创建Excel文档
wb = new HSSFWorkbook() ;
// sheet 对应一个工作页
HSSFSheet sheet = wb.createSheet("student表中的数据") ;
HSSFRow firstrow = sheet.createRow(1); //下标为0的行开始
firstrow.setHeight((short)500);
HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
String[] names = new String[CountColumnNum];
names[0] ="学院";
names[1] ="班级";
names[2] ="辅导员";
names[3] ="实参评人数";
names[4] ="应参评人数";
names[5] ="参评率%";



HSSFFont font = wb.createFont();//创建字体对象
font.setFontHeightInPoints((short)12);//设置字体大小
font.setFontName("宋体");
HSSFCellStyle cellStyle1 = wb.createCellStyle();  
cellStyle1.setFont(font);//将字体加入到样式对象
cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);//水平居中
cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
cellStyle1.setWrapText(true);//设置自动换行


HSSFFont font1 = wb.createFont();//创建字体对象
font1.setFontHeightInPoints((short)12);//设置字体大小
font1.setFontName("宋体");
font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置粗体
HSSFCellStyle cellStyle2 = wb.createCellStyle();  
cellStyle2.setFont(font1);//将字体加入到样式对象
cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);//水平居中
cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
cellStyle1.setWrapText(true);//设置自动换行


Region region = new Region(0,(short)0,0,(short)5);
HSSFRow headrow = sheet.createRow((short)0); //下标为0的行开始
headrow.setHeight((short)1000);
HSSFCell headcell = headrow.createCell((short) 0);
headcell.setCellValue("2011-2012学年春季学期山东大学人格培育参评情况");
//for(int l = 0; l < 6; i++){
sheet.addMergedRegion(new Region(0,(short)0,0,(short)5));

//HSSFCell headcell=headrow.createCell((short)0);
//headcell.setCellValue(new HSSFRichTextString("2011-2012学年春季学期山东大学人格培育参评情况"));
headcell.setCellStyle(cellStyle2);

for(j= 0 ;j<CountColumnNum; j++){
firstcell[j] = firstrow.createCell((short)j);
firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
firstcell[j].setCellStyle(cellStyle1); 
}
int col1width = sheet.getColumnWidth(1);
System.out.println("单元格宽度："+col1width);
sheet.setColumnWidth(0, 25 * 256);
sheet.setColumnWidth(1, 25 * 256);
sheet.setColumnWidth(2, 20 * 256);
sheet.setColumnWidth(3, 20 * 256);
sheet.setColumnWidth(4, 20 * 256);
sheet.setColumnWidth(5, 20 * 256);






 //ArrayList<String[]> resultcpl;
 Connection con;
 ResultSet set=null;
 ResultSet set1=null;
 ResultSet set2=null;
 PreparedStatement state=null;
 PreparedStatement state1=null;
 PreparedStatement state2=null;
 
 PreparedStatement ps1=null;
 PreparedStatement ps2=null;
 ResultSet rs1=null;
 ResultSet rs2=null;

	int total=0,deptotal=0;//,total1=0,total2=0,total3=0,total4=0,total5=0,total6=0,total7=0,total8=0,
	int ttotal=0;
	int teachertotal=0,teacherjoin=0;
	int num=0;
	int joined=0,depjoined=0;//,joined1=0,joined2=0,joined3=0,joined4=0,joined5=0,joined6=0,joined7=0,joined8=0,
	int tjoined=0;
	String depname=null;
	String tname=null;
	String temp[]=new String[6];
	String buffername=null;
	DecimalFormat df = new DecimalFormat("0.00");


	con = DBUtil.getConnection();
	String sqlall = null; // 总人数
	String sqljoined = null; // 参加的人数
	String sql="select student.clazzid,depname,clazzname,tname from student,teacher,department,major2clazz where student.depid=department.depid and student.clazzid=major2clazz.clazzid and student.tid=teacher.tid group by student.clazzid order by depname desc,tname asc,clazzname asc";
    sqlall= "select count(*) from student where clazzid=? ";
    sqljoined ="select count(*) from student where clazzid=? and isgrade=1";
    String gradeall="select jointime,count(sid) from student group by jointime order by jointime asc";
    String gradejoined="select count(*) from student where isgrade=1 and jointime=?";

    try {
    	state1 = con.prepareStatement(sql);
		System.out.println(state1.toString());

		set1 = state1.executeQuery();
		while(set1.next()){
    	
		//System.out.println("temp[0]="+temp[0]);

		
		state = con.prepareStatement(sqlall);
		state.setInt(1, set1.getInt(1));
		set = state.executeQuery();
		if(set.next())
		total = set.getInt(1);
		
		state2 = con.prepareStatement(sqljoined);
		state2.setInt(1,set1.getInt(1));
		set2 = state2.executeQuery();
		if(set2.next())
		joined = set2.getInt(1);
		
		if(num!=0){
    	if(depname.equals(set1.getString(2))){
		
        if(tname.equals(set1.getString(4))){
        	
		temp[0]=set1.getString(2);
		temp[1]=set1.getString(3);
		temp[2]=set1.getString(4);
		temp[3]=Integer.toString(joined);
		temp[4]=Integer.toString(total);
		temp[5]=df.format((float)joined/total*100);
		//temp[5]=Float.toString((float)joined/total*100);
		depjoined=depjoined+joined;
		deptotal=deptotal+total;
		depname=set1.getString(2);
        tname=set1.getString(4);
        teachertotal+=total;
        teacherjoin+=joined;

		
		// 创建电子表格的一行
		HSSFRow row = sheet.createRow(i) ; // 下标为1的行开始
		row.setHeight((short)300);

		for(j=0;j<CountColumnNum;j++)
		{
		// 在一行内循环
		HSSFCell cell = row.createCell((short) j) ;
		// 设置表格的编码集，使支持中文
		//// 先判断数据库中的数据类型
		// 将结果集里的值放入电子表格中
		//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

        cell.setCellStyle(cellStyle1);  
		cell.setCellValue(temp[j]);
		}
		i++ ;
		
        }else{//if(tname.equals(set1.getString(4))){
    		temp[0]="";
    		temp[1]="";
    		temp[2]=temp[2]+" "+"汇总";
    		temp[3]=Integer.toString(teacherjoin);
    		temp[4]=Integer.toString(teachertotal);
    		temp[5]=df.format((float)teacherjoin/teachertotal*100);
    		//temp[5]=Float.toString((float)teacherjoin/teachertotal*100);

            teachertotal=0;
            teacherjoin=0;
		
		// 创建电子表格的一行
		HSSFRow row = sheet.createRow(i) ; // 下标为1的行开始
		row.setHeight((short)300);
		for(j=0;j<CountColumnNum;j++)
		{
		// 在一行内循环
		HSSFCell cell = row.createCell((short) j) ;
		// 设置表格的编码集，使支持中文
		//// 先判断数据库中的数据类型
		// 将结果集里的值放入电子表格中
		//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;
		if(j==2){
			cell.setCellStyle(cellStyle2);
		}else{
			cell.setCellStyle(cellStyle1);  
		}

		cell.setCellValue(temp[j]);
		}
		i++ ;
		
		
		temp[0]=set1.getString(2);
		temp[1]=set1.getString(3);
		temp[2]=set1.getString(4);
		temp[3]=Integer.toString(joined);
		temp[4]=Integer.toString(total);
		temp[5]=df.format((float)joined/total*100);
		//temp[5]=Float.toString((float)joined/total*100);
		depjoined=depjoined+joined;
		deptotal=deptotal+total;
		depname=set1.getString(2);
        tname=set1.getString(4);
        teachertotal+=total;
        teacherjoin+=joined;
		
	
	// 创建电子表格的一行
	HSSFRow row1 = sheet.createRow(i) ; // 下标为1的行开始
	row1.setHeight((short)300);
	for(j=0;j<CountColumnNum;j++)
	{
	// 在一行内循环
	HSSFCell cell = row1.createCell((short) j) ;
	// 设置表格的编码集，使支持中文
	//// 先判断数据库中的数据类型
	// 将结果集里的值放入电子表格中
	//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

	cell.setCellStyle(cellStyle1);  
	cell.setCellValue(temp[j]);
	}
	i++ ;
        }
		
    	}else{//if(depname.equals(set1.getString(2))){
    		
    		buffername=temp[0];
    		temp[0]="";
    		temp[1]="";
    		temp[2]=temp[2]+" "+"汇总";
    		temp[3]=Integer.toString(teacherjoin);
    		temp[4]=Integer.toString(teachertotal);
    		//DecimalFormat df = new DecimalFormat("0.00");
    		temp[5]=df.format((float)teacherjoin/teachertotal*100);
    		//temp[5]=Float.toString((float)teacherjoin/teachertotal*100);

            teachertotal=0;
            teacherjoin=0;
		
		// 创建电子表格的一行
		HSSFRow row = sheet.createRow(i) ; // 下标为1的行开始
		row.setHeight((short)300);
		for(j=0;j<CountColumnNum;j++)
		{
		// 在一行内循环
		HSSFCell cell = row.createCell((short) j) ;
		// 设置表格的编码集，使支持中文
		//// 先判断数据库中的数据类型
		// 将结果集里的值放入电子表格中
		//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

		if(j==2){
			cell.setCellStyle(cellStyle2);
		}else{
			cell.setCellStyle(cellStyle1);  
		}  
		cell.setCellValue(temp[j]);
		}
		i++ ;
    		
    		
    		temp[0]=buffername+" "+"汇总";
    		temp[1]="";
    		temp[2]="";
    		temp[3]=Integer.toString(depjoined);
    		temp[4]=Integer.toString(deptotal);
    		//DecimalFormat df = new DecimalFormat("0.00");
    		temp[5]=df.format((float)depjoined/deptotal*100);
    		//temp[5]=Float.toString((float)depjoined/deptotal*100);

    		
    		deptotal=0;
    		depjoined=0;
		
		// 创建电子表格的一行
		HSSFRow row3 = sheet.createRow(i) ; // 下标为1的行开始
		row3.setHeight((short)300);
		for(j=0;j<CountColumnNum;j++)
		{
		// 在一行内循环
		HSSFCell cell = row3.createCell((short) j) ;
		// 设置表格的编码集，使支持中文
		//// 先判断数据库中的数据类型
		// 将结果集里的值放入电子表格中
		//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

		if(j==0){
			cell.setCellStyle(cellStyle2);
		}else{
			cell.setCellStyle(cellStyle1);  
		} 
		cell.setCellValue(temp[j]);
		}
		i++ ;
		
		
		temp[0]=set1.getString(2);
		temp[1]=set1.getString(3);
		temp[2]=set1.getString(4);
		temp[3]=Integer.toString(joined);
		temp[4]=Integer.toString(total);
		temp[5]=df.format((float)joined/total*100);
		//temp[5]=Float.toString((float)joined/total*100);
		depname=set1.getString(2);
		depjoined=depjoined+joined;
		deptotal=deptotal+total;
        tname=set1.getString(4);
        teachertotal+=total;
        teacherjoin+=joined;
		
	
	// 创建电子表格的一行
	HSSFRow row1 = sheet.createRow(i) ; // 下标为1的行开始
	row1.setHeight((short)300);
	for(j=0;j<CountColumnNum;j++)
	{
	// 在一行内循环
	HSSFCell cell = row1.createCell((short) j) ;
	// 设置表格的编码集，使支持中文
	//// 先判断数据库中的数据类型
	// 将结果集里的值放入电子表格中
	//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

	cell.setCellStyle(cellStyle1);  
	cell.setCellValue(temp[j]);
	}
	i++ ;
	
    	}
    	
		}else{
			temp[0]=set1.getString(2);
			temp[1]=set1.getString(3);
			temp[2]=set1.getString(4);
			temp[3]=Integer.toString(joined);
			temp[4]=Integer.toString(total);
			//temp[5]=Float.toString((float)joined/total*100);
    		temp[5]=df.format((float)joined/total*100);
			depname=set1.getString(2);
			depjoined=depjoined+joined;
			deptotal=deptotal+total;
	        tname=set1.getString(4);
	        teachertotal+=total;
	        teacherjoin+=joined;


			
			// 创建电子表格的一行
			HSSFRow row = sheet.createRow(i) ; // 下标为1的行开始
			row.setHeight((short)300);
			for(j=0;j<CountColumnNum;j++)
			{
			// 在一行内循环
			HSSFCell cell = row.createCell((short) j) ;
			// 设置表格的编码集，使支持中文
			//// 先判断数据库中的数据类型
			// 将结果集里的值放入电子表格中
			//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

			cell.setCellStyle(cellStyle1);  
			cell.setCellValue(temp[j]);
			}
			i++ ;
			num++;
		}
		}//while
		
		buffername=temp[0];
		temp[0]="";
		temp[1]="";
		temp[2]=temp[2]+" "+"汇总";
		temp[3]=Integer.toString(teacherjoin);
		temp[4]=Integer.toString(teachertotal);
		temp[5]=df.format((float)teacherjoin/teachertotal*100);
		//temp[5]=Float.toString((float)teacherjoin/teachertotal*100);

        teachertotal=0;
        teacherjoin=0;
	
	// 创建电子表格的一行
	HSSFRow row = sheet.createRow(i) ; // 下标为1的行开始
	row.setHeight((short)300);
	for(j=0;j<CountColumnNum;j++)
	{
	// 在一行内循环
	HSSFCell cell = row.createCell((short) j) ;
	// 设置表格的编码集，使支持中文
	//// 先判断数据库中的数据类型
	// 将结果集里的值放入电子表格中
	//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

	if(j==2){
		cell.setCellStyle(cellStyle2);
	}else{
		cell.setCellStyle(cellStyle1);  
	} 
	cell.setCellValue(temp[j]);
	}
	i++ ;
		
		
		temp[0]=buffername+" "+"汇总";
		temp[1]="";
		temp[2]="";
		temp[3]=Integer.toString(depjoined);
		temp[4]=Integer.toString(deptotal);
		//temp[5]=Float.toString((float)depjoined/deptotal*100);
		temp[5]=df.format((float)depjoined/deptotal*100);


		
		deptotal=0;
		depjoined=0;
	
	// 创建电子表格的一行
	HSSFRow row4 = sheet.createRow(i) ; // 下标为1的行开始
	row4.setHeight((short)300);
	for(j=0;j<CountColumnNum;j++)
	{
	// 在一行内循环
	HSSFCell cell = row4.createCell((short) j) ;
	// 设置表格的编码集，使支持中文
	//// 先判断数据库中的数据类型
	// 将结果集里的值放入电子表格中
	//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

	if(j==0){
		cell.setCellStyle(cellStyle2);
	}else{
		cell.setCellStyle(cellStyle1);  
	} 
	cell.setCellValue(temp[j]);
	}
	i++ ;
	
	int join=0;
	ps1=con.prepareStatement(gradeall);
	rs1=ps1.executeQuery();

	while(rs1.next()){
		ps2=con.prepareStatement(gradejoined);
		ps2.setInt(1,rs1.getInt(1));
		rs2=ps2.executeQuery();
		if(rs2.next())
			join=rs2.getInt(1);
		ttotal+=rs1.getInt(2);
		tjoined+=join;

		temp[0]=rs1.getString(1)+"级汇总";
		temp[1]="";
		temp[2]="";
		temp[3]=Integer.toString(join);
		temp[4]=Integer.toString(rs1.getInt(2));
		//temp[5]=Float.toString((float)join/rs1.getInt(2)*100);
		temp[5]=df.format((float)join/rs1.getInt(2)*100);

		
		// 创建电子表格的一行
		HSSFRow row2 = sheet.createRow(i) ; // 下标为1的行开始
		row2.setHeight((short)300);
		for(j=0;j<CountColumnNum;j++)
		{
		// 在一行内循环
		HSSFCell cell = row2.createCell((short) j) ;
		// 设置表格的编码集，使支持中文
		//// 先判断数据库中的数据类型
		// 将结果集里的值放入电子表格中
		//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

		if(j==0){
			cell.setCellStyle(cellStyle2);
		}else{
			cell.setCellStyle(cellStyle1);  
		}
		cell.setCellValue(temp[j]);
		}
		i++ ;
	}
	    
	temp[0]="总计";
	temp[1]="";
	temp[2]="";
	temp[3]=Integer.toString(tjoined);
	temp[4]=Integer.toString(ttotal);
	//temp[5]=Float.toString((float)tjoined/ttotal*100);
	temp[5]=df.format((float)tjoined/ttotal*100);

	// 创建电子表格的一行
	HSSFRow row2 = sheet.createRow(i) ; // 下标为1的行开始
	row2.setHeight((short)300);
	for(j=0;j<CountColumnNum;j++)
	{
	// 在一行内循环
	HSSFCell cell = row2.createCell((short) j) ;
	// 设置表格的编码集，使支持中文
	//// 先判断数据库中的数据类型
	// 将结果集里的值放入电子表格中
	//cell.setCellValue(new HSSFRichTextString(rs.getString(j+1))) ;

	if(j==0){
		cell.setCellStyle(cellStyle2);
	}else{
		cell.setCellStyle(cellStyle1);  
	} 
	cell.setCellValue(temp[j]);
	}
	i++ ;

	
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try{
			if(con!=null){
				con.close();
			}
			if(state!=null){
				state.close();
			}
			if(state1!=null){
				state1.close();
			}
			if(state2!=null){
				state2.close();
			}
			if(set!=null){
				set.close();
			}
			if(set1!=null){
				set1.close();
			}
			if(set2!=null){
				set2.close();
			}
		}catch(SQLException ee){
			ee.printStackTrace();
		}
	}



// 创建文件输出流，准备输出电子表格
//OutputStream out = new FileOutputStream("E:\\person.xls") ;
//wb.write(out) ;
//out.close() ;
//System.out.println("数据库导出成功") ;

}


public static void main(String[] args)
{
try {
@SuppressWarnings("unused")
MySql2Excel excel = new MySql2Excel() ;
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}
