package edu.sdu.online.rengepeiyang.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.beans.User;
import edu.sdu.online.rengepeiyang.processer.QueryProcesser;
import edu.sdu.online.rengepeiyang.processer.TeacherService;

public class ToExcelAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream downloadStream;
	private String fileName;
	private int depid=0;
    private int clazz=0;
    private int  grade=0;
    private int tid=0;
    private String gradename;
    private String tname;
    private String dname;
    private String cname;
    private QueryProcesser processer;
    private ArrayList<float[]> result;
    DecimalFormat df2=(DecimalFormat)NumberFormat.getInstance();
	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public String getFileName() {
		String temp=null;
		try {
			temp= new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}
    
	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTheExcel() throws Exception {
		String state=(String)ActionContext.getContext().getSession().get("state");
		if(state.equals("students")){
    		getStudentsExcel();
    	}else{
    		depid=Integer.parseInt((String)ActionContext.getContext().getSession().get("depid"));
    		 clazz=Integer.parseInt((String)ActionContext.getContext().getSession().get("clazz"));
    		 grade=Integer.parseInt((String)ActionContext.getContext().getSession().get("grade"));
    		 tid=Integer.parseInt((String)ActionContext.getContext().getSession().get("tid"));
    		 gradename=(String)ActionContext.getContext().getSession().get("gradename");
    		 dname=(String)ActionContext.getContext().getSession().get("dname");
    		 cname=(String)ActionContext.getContext().getSession().get("cname");
    		 tname=(String)ActionContext.getContext().getSession().get("tname");
    		processer = new QueryProcesser();
        	processer.setClazz(clazz);
        	processer.setDepid(depid);
        	processer.setGrade(grade);
        	processer.setTid(tid);
        	df2.setMaximumFractionDigits(4);
    		if(state.equals("cpl")){
    			System.out.println("----getcplExcel()-----");
    			 getcplExcel();
    		}else if(state.equals("11")){
    		     get11Excel();
    		}else if(state.equals("suyang")){
    			 getSuyangExcel();
    		}
    		else if(state.equals("24")){
    			 get24Excel();
    		}
    	}
		
		return SUCCESS;
	}
	public void  getStudentsExcel(){
		 ArrayList<String[]> students;
		 TeacherService service = new TeacherService();
			service.setClazzid(clazz);
			User user;
			user = (User)ActionContext.getContext().getSession().get("user");
			service.setTid(user.getTid());
			students = service.getStudents();
			String teacherName = user.getTname();
			fileName =teacherName+"所辅导的未参评同学统计"+"xls";
			XSSFWorkbook workbook=null;
		    workbook = new XSSFWorkbook();
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			XSSFSheet sheet = workbook.createSheet("sheet1");
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = null;
			cell = row.createCell(0);
			cell.setCellValue("尚未参评的同学");
			cell = row.createCell(1);
			cell = row.createCell(2);
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellValue("学号");
			cell = row.createCell(1);
			cell.setCellValue("姓名");
			cell = row.createCell(2);
			cell.setCellValue("班级");
			for(int i=0;i<students.size();i++){
				row = sheet.createRow(i+2);
				cell = row.createCell(0);
				cell.setCellValue(students.get(i)[0]);
				cell = row.createCell(1);
				cell.setCellValue(students.get(i)[1]);
				cell = row.createCell(2);
				cell.setCellValue(students.get(i)[2]);
			}
			mergeCells(sheet,0,0,0,2);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				workbook.write(outputStream);
				outputStream.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			byte[] byteArray = outputStream.toByteArray();
			downloadStream = new ByteArrayInputStream(byteArray, 0,byteArray.length);
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
    public void getcplExcel(){
    	result = processer.getCPL();
    	executeFileName("参评率统计");
    	System.out.println(fileName);
    	XSSFWorkbook workbook=null;
	    workbook = new XSSFWorkbook();
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		XSSFSheet sheet = workbook.createSheet("sheet1");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = null;
		cell = row.createCell(0);
		cell.setCellValue("总人数");
		cell = row.createCell(1);
		cell.setCellValue((int)(result.get(0)[0]));
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("总参评人数");
		cell = row.createCell(1);
		cell.setCellValue((int)(result.get(0)[1]));
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("参评率");
		cell = row.createCell(1);
		cell.setCellValue(Float.parseFloat(df2.format(result.get(0)[2])));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] byteArray = outputStream.toByteArray();
		downloadStream = new ByteArrayInputStream(byteArray, 0,byteArray.length);
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    public void get11Excel(){
    	result= processer.get11();
    	executeFileName("11项典型行为统计");
    	XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		XSSFSheet sheet = workbook.createSheet("sheet1");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = null;
			cell = row.createCell(0);
			cell.setCellValue("典型行为");
			cell = row.createCell(1);
			cell.setCellValue("优");
			cell = row.createCell(2);
			cell.setCellValue("良");
			cell = row.createCell(3);
			cell.setCellValue("中");

		for (int i = 0; i < 11; i++) {
			row = sheet.createRow(i + 1);
			cell = row.createCell(0);
			cell.setCellValue(i+1);
			cell = row.createCell(1);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(i)[0])));
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(i)[1])));
			cell = row.createCell(3);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(i)[2])));
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] byteArray = outputStream.toByteArray();
		downloadStream = new ByteArrayInputStream(byteArray, 0,byteArray.length);
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void getSuyangExcel(){
    	result=processer.getSuyang();
    	executeFileName("素养层级统计");
    	XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		XSSFSheet sheet = workbook.createSheet("sheet1");
		XSSFRow row ;
		XSSFCell cell = null;
		    row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("素养");
			cell = row.createCell(1);
			cell.setCellValue("层级");
			cell = row.createCell(2);
			cell.setCellValue("所占比例");
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellValue("健全人格素养");
			cell = row.createCell(1);
			cell.setCellValue("A");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(0)[0])));
			row = sheet.createRow(2);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("B+");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(0)[1])));
			row = sheet.createRow(3);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("B");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(0)[2])));
			row = sheet.createRow(4);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("C");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(0)[3])));
			row = sheet.createRow(5);
			cell = row.createCell(0);
			cell.setCellValue("成功人格素养");
			cell = row.createCell(1);
			cell.setCellValue("A++");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(1)[0])));
			row = sheet.createRow(6);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("A+");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(1)[1])));
			row = sheet.createRow(7);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("A");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(1)[2])));
			row = sheet.createRow(8);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("B+");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(1)[3])));
			row = sheet.createRow(9);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("B");
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(1)[4])));
			mergeCells(sheet,1,4,0,0);
			mergeCells(sheet,5,9,0,0);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] byteArray = outputStream.toByteArray();
		downloadStream = new ByteArrayInputStream(byteArray, 0,byteArray.length);
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void get24Excel(){
    	result= processer.get24();
    	executeFileName("24道题统计");
    	XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		XSSFSheet sheet = workbook.createSheet("sheet1");
		XSSFRow row;
		XSSFCell cell = null;
		    row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue("题号");
			cell = row.createCell(1);
			cell.setCellValue("A");
			cell = row.createCell(2);
			cell.setCellValue("B");
			cell = row.createCell(3);
			cell.setCellValue("C");
		for (int i = 0; i < 24; i++) {
			row = sheet.createRow(i + 1);
			cell = row.createCell(0);
			cell.setCellValue(i+1);
			cell = row.createCell(1);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(i)[0])));
			cell = row.createCell(2);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(i)[1])));
			cell = row.createCell(3);
			cell.setCellValue(Float.parseFloat(df2.format(result.get(i)[2])));
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] byteArray = outputStream.toByteArray();
		downloadStream = new ByteArrayInputStream(byteArray, 0,byteArray.length);
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void executeFileName(String str){
    	if(grade==0){
    		if(depid==0){
    		fileName="来自全校学生的"+str+".xlsx";
    		}else{
    			if(tid==0){
    				fileName="来自"+dname+"全体师生的"+str+".xlsx";
    			}else{
    				if(clazz==0){
    					fileName="辅导员"+tname+"所带学生的"+str+".xlsx";
    				}else{
    					fileName="来自"+cname+"班全体同学的"+str+".xlsx";
    				}
    			}
    		}
    	}else{
    		if(depid==0){
    			fileName="来自"+gradename+"的"+str+".xlsx";
    		}else{
    			if(tid==0){
    				fileName="来自"+gradename+dname+"全体同学的"+str+".xlsx";
    			}else{
    				if(clazz==0){
    					fileName="来自"+gradename+dname+"辅导员"+tname+"所辅导的所有学生的"+str+".xlsx";
    				}else{
    					fileName= "来自"+cname+"班的全体同学的"+str+".xlsx";
    				}
    			}
    		}
    	}
    }
    public void mergeCells(XSSFSheet sheet,int rowfrom,int rowto,int colfrom,int colto){  
        CellRangeAddress region = new CellRangeAddress(rowfrom,rowto,colfrom,colto);  
        sheet.addMergedRegion(region);  
    }  
}
