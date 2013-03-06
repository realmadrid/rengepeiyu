package edu.sdu.online.rengepeiyang.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.opensymphony.xwork2.ActionSupport;

import edu.sdu.online.rengepeiyang.processer.GenerateExecelProcesser;
import edu.sdu.online.rengepeiyang.processer.MySql2Excel;

public class AllAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream downloadStream;
	private String fileName;
	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String allcpl() throws Exception {
		HSSFWorkbook workbook=null;
		try {
			@SuppressWarnings("unused")
			MySql2Excel excel = new MySql2Excel() ;
			 workbook = excel.getWorkbook();
			} catch (Exception e) {
			e.printStackTrace();
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
		fileName = "参评率的总体统计数据"+".xls";
		return SUCCESS;
	}
	public String all11(){
		fileName = "11项典型行为的总体统计数据"+".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		GenerateExecelProcesser.getAllExcel11Capbilitys(workbook);
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
		return SUCCESS;
	}
	public String allsuyang(){
		fileName = "素养层级的总体统计数据"+".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		GenerateExecelProcesser.getAllExcel2Levels(workbook);
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
		return SUCCESS;
	}
	public String all24(){
		fileName = "24道题目的总体统计数据"+".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		GenerateExecelProcesser.getAllExcel24Terms(workbook);
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
		return SUCCESS;
	}
    
}
