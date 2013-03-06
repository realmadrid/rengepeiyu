/**
 * 
 */
package edu.sdu.online.rengepeiyang.processer;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import edu.sdu.online.rengepeiyang.beans.ClazzBean;
import edu.sdu.online.rengepeiyang.beans.DepartmentBean;
import edu.sdu.online.rengepeiyang.beans.TeacherBean;
import edu.sdu.online.rengepeiyang.utils.DBUtil;

/**
 * @date：2012-5-15 下午08:06:58
 * @project：rengepeiyang4
 * @file：statisticsOf24Terms.java
 * @author Mandy
 * @version 1.0
 * @since JDK 1.6.0_21
 * @discription：
 */
public class GenerateExecelProcesser {

	final static int GRADE_NUM = 8;

	/*
	 * 表单的表头样式
	 */
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {
		// 创建单元格样式--标题样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBottomBorderColor(HSSFColor.RED.index);// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 300);
		font.setColor(HSSFColor.BLUE.index);
		style.setFont(font);// 设置字体
		return style;
	}

	/*
	 * 表单的内容样式
	 */
	public static HSSFCellStyle getContentStyle(HSSFWorkbook wb) {
		// 创建单元格样式--内容样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setWrapText(true);// 自动换行
		return style;
	}

	public static HSSFCellStyle getlPercentStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		// 创建单元格样式--百分数样式0.00%
		// style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		// 创建单元格样式--小数样式0.00
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		return style;

	}

	public static void setTitlePattern1(HSSFWorkbook wb, HSSFSheet sheet,
			String title, String keyword, int l) {
		// 设置excel每列宽度
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 3500);

		// 创建Excel的sheet的第一行--标题
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设定第一行的高度

		// 创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);

		// 合并单元格(startRow，endRow，startColumn，endColumn)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, l));

		// 给Excel的单元格设置样式和赋值
		cell.setCellStyle(getTitleStyle(wb));
		cell.setCellValue(title);

		// 设置第二行单元格的内容格式
		HSSFCellStyle style = wb.createCellStyle();
		style.setWrapText(true);// 自动换行

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(keyword);

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("选项");

		for (int j = 2; j <= l; j++) {
			cell = row.createCell(j);
			cell.setCellStyle(style);
			cell.setCellValue("第" + (j - 1) + "题");
		}
	}

	public static void setTitlePattern2(HSSFWorkbook wb, HSSFSheet sheet,
			String title, String keyword, int l) {// l=9

		// 设置excel每列宽度
		sheet.setColumnWidth(0, 7000);
		// sheet.setColumnWidth(1, 3500);
		// 创建Excel的sheet的第一行--标题
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设定第一行的高度

		// 创建一个Excel的单元格
		HSSFCell cell = row.createCell(0);
		// 合并单元格(startRow，endRow，startColumn，endColumn)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, l));
		// 给Excel的单元格设置样式和赋值
		cell.setCellStyle(getTitleStyle(wb));
		cell.setCellValue(title);

		// 设置第二行单元格的内容格式
		HSSFCellStyle style = wb.createCellStyle();
		style.setWrapText(true);// 自动换行
		// 两项层级的合并单元格
		row = sheet.createRow(1);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("拥有健全人格素养情况");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 10));
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("拥有健全人格素养情况");

		row = sheet.createRow(2);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(keyword);

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("A");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("B+");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("B");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("C");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("A++");

		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("A+");

		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("A");

		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("B+");

		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("B");
	}

	public static List<DepartmentBean> getDeps(Connection conn) {
		List<DepartmentBean> deps = new ArrayList<DepartmentBean>();
		DepartmentBean dep = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select depid,depname from department";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dep = new DepartmentBean();
				dep.setDepid(rs.getInt("depid"));
				dep.setDepname(rs.getString("depname"));
				deps.add(dep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return deps;
	}

	public static List<TeacherBean> getTes(Connection conn) {
		List<TeacherBean> tbs = new ArrayList<TeacherBean>();
		TeacherBean tb = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select tid,tname from teacher";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tb = new TeacherBean();
				tb.setTid(rs.getInt("tid"));
				tb.setTname(rs.getString("tname"));
				tbs.add(tb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return tbs;
	}

	public static List<ClazzBean> getClas(Connection conn) {
		List<ClazzBean> cbs = new ArrayList<ClazzBean>();
		ClazzBean cb = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select clazzid,clazzname from major2clazz";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cb = new ClazzBean();
				cb.setClazzid(rs.getInt("clazzid"));
				cb.setClazzname(rs.getString("clazzname"));
				cbs.add(cb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, rs);
		}
		return cbs;
	}

	/*
	 * 生成“24题-所有学院”表 以每个导员的信息为插入单元
	 */
	public static void generateExcelByAcademysOf24Terms(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("24题-所有学院");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有学院24题选题情况", "学院", 25);

		int gradeID = 1; // 年级号
		int depID = 1; // 学院号
		int quesID = 1; // 题号
		int awNum = 1; // 答案

		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		List<DepartmentBean> deps = getDeps(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (depID = 1; depID <= deps.size(); depID++) {
			int countOf24Terms[][] = new int[24][3];
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				for (quesID = 1; quesID <= 24; quesID++) {
					for (awNum = 1; awNum <= 3; awNum++) {

						String sql = "SELECT COUNT(a"
								+ quesID
								+ ") FROM (SELECT * FROM  gradeinfo_"
								+ gradeID
								+ " WHERE sid IN (SELECT sid FROM student WHERE depid='"
								+ depID + "' AND isgrade=true)) AS temp " + "WHERE a" + quesID
								+ "='" + awNum + "'";
						// System.out.println(sql);
						try {
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							if (rs.next()) {
								countOf24Terms[quesID - 1][awNum - 1] = countOf24Terms[quesID - 1][awNum - 1]
										+ rs.getInt("count(a" + quesID + ")");// ????
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.close(ps, rs);
						}
					}
				}
			}

			// 以学院为单位 写入execel
			int index = 2 + (depID - 1) * 3;// 每条学院记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(deps.get(depID - 1).getDepname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????
			System.out.println(deps.get(depID - 1).getDepname());

			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf24Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 25; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					if (count != 0) {
						float p = ((float) countOf24Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}
				}
				row = sheet.createRow(index + i + 1);
			}
		}

		DBUtil.close(conn);
	}

	/*
	 * 生成“24题-所有导员”表
	 */
	public static void generateExcelByTeachersOf24Terms(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("24题-所有导员");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有导员所带学生24题选题情况", "导员", 25);

		int gradeID = 1; // 年级号
		int teID = 1; // 学院号
		int quesID = 1; // 题号
		int awNum = 1; // 答案
		int countOf24Terms[][] = new int[24][3]; //

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		List<TeacherBean> tbs = getTes(conn);// 导员列表

		// 以学院为单位 查询数据库 然后写入execel

		for (teID = 1; teID <= tbs.size(); teID++) {
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				for (quesID = 1; quesID <= 24; quesID++) {
					for (awNum = 1; awNum <= 3; awNum++) {

						String sql = "SELECT COUNT(a"
								+ quesID
								+ ") FROM (SELECT * FROM  gradeinfo_"
								+ gradeID
								+ " WHERE sid IN (SELECT sid FROM student WHERE tid='"
								+ tbs.get(teID - 1).getTid()
								+ "' AND isgrade=true)) AS temp " + "WHERE a"
								+ quesID + "='" + awNum + "'";
						// System.out.println(sql);
						try {
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							if (rs.next()) {
								countOf24Terms[quesID - 1][awNum - 1] = countOf24Terms[quesID - 1][awNum - 1]
										+ rs.getInt("count(a" + quesID + ")");// ????
								// rs.getInt()
								// 0
								// or
								// 1
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.close(ps, rs);
						}
					}
				}
			}

			// 以学院为单位 写入execel
			int index = 2 + (teID - 1) * 3;// 每条导员记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 导员名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(tbs.get(teID - 1).getTname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????
			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf24Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 25; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					if (count != 0) {
						float p = ((float) countOf24Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}

				}
				row = sheet.createRow(index + i + 1);
			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByGradesOf24Terms(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("24题-所有年级");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有年级24题选题情况", "年级", 25);

		int gradeID = 1; // 年级号
		int quesID = 1; // 题号
		int awNum = 1; // 答案
		int countOf24Terms[][] = new int[24][3]; //

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;

		// 以学院为单位 查询数据库 然后写入execel

		for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
			for (quesID = 1; quesID <= 24; quesID++) {
				for (awNum = 1; awNum <= 3; awNum++) {
					String sql = "SELECT COUNT(a" + quesID
							+ ") FROM gradeinfo_" + gradeID + " WHERE a"
							+ quesID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf24Terms[quesID - 1][awNum - 1] = rs
									.getInt("count(a" + quesID + ")");// ????
							// rs.getInt()
							// 0 or
							// 1
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
			}

			// 以学院为单位 写入execel
			int index = 2 + (gradeID - 1) * 3;// 每条导员记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 导员名称单元格
			cell.setCellStyle(normalStyle);

			Calendar a = Calendar.getInstance();// 系统当前年份计算在校年级(按第二学期年份为准)
			int year = a.get(Calendar.YEAR);
			cell.setCellValue((year - gradeID) + "级");// ??????

			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf24Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 25; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					if (count != 0) {
						float p = ((float) countOf24Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}

				}
				row = sheet.createRow(index + i + 1);
			}
		}

		DBUtil.close(conn);
	}

	/*
	 * 班级
	 */
	public static void generateExcelByClazzsOf24Terms(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("24题-所有班级");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有班级24题选题情况", "班级", 25);

		int gradeID = 1; // 年级号
		int clazzID = 1; // 学院号
		int quesID = 1; // 题号
		int awNum = 1; // 答案
		int countOf24Terms[][] = new int[24][3]; //
		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;

		Connection conn = DBUtil.getConnection();
		List<ClazzBean> clas = getClas(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (clazzID = 1; clazzID <= clas.size(); clazzID++) {
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				for (quesID = 1; quesID <= 24; quesID++) {
					for (awNum = 1; awNum <= 3; awNum++) {

						String sql = "SELECT COUNT(a"
								+ quesID
								+ ") FROM (SELECT * FROM  gradeinfo_"
								+ gradeID
								+ " WHERE sid IN (SELECT sid FROM student WHERE clazzid='"
								+ clazzID + "' AND isgrade=true)) AS temp "
								+ "WHERE a" + quesID + "='" + awNum + "'";
						// System.out.println(sql);
						try {
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							if (rs.next()) {
								countOf24Terms[quesID - 1][awNum - 1] = countOf24Terms[quesID - 1][awNum - 1]
										+ rs.getInt("count(a" + quesID + ")");// ????
								// rs.getInt()
								// 0
								// or
								// 1
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.close(ps, rs);
						}
					}
				}
			}

			// 以班级为单位 写入execel
			int index = 2 + (clazzID - 1) * 3;// 每条学院记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(clas.get(clazzID - 1).getClazzname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????

			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf24Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 25; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					if (count != 0) {
						float p = ((float) countOf24Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}

				}
				row = sheet.createRow(index + i + 1);
			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByAcademysOf11Capbility(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("11项能力-所有学院");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有学院11项能力情况", "学院", 12);

		int gradeID = 1; // 年级号
		int depID = 1; // 学院号
		int quesID = 1; // 题号
		int awNum = 1; // 答案

		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		List<DepartmentBean> deps = getDeps(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (depID = 1; depID <= deps.size(); depID++) {
			int countOf11Terms[][] = new int[11][3];
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				for (quesID = 1; quesID <= 11; quesID++) {
					for (awNum = 1; awNum <= 3; awNum++) {

						String sql = "SELECT COUNT(s"
								+ quesID
								+ ") FROM (SELECT * FROM  gradeinfo_"
								+ gradeID
								+ " WHERE sid IN (SELECT sid FROM student WHERE depid='"
								+ depID + "' AND isgrade=true)) AS temp "
								+ "WHERE s" + quesID + "='" + awNum + "'";
						try {
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							if (rs.next()) {
								countOf11Terms[quesID - 1][awNum - 1] = countOf11Terms[quesID - 1][awNum - 1]
										+ rs.getInt("count(s" + quesID + ")");// ????
								// rs.getInt()
								// 0
								// or
								// 1
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.close(ps, rs);
						}
					}
				}
			}

			// 以学院为单位 写入execel
			int index = 2 + (depID - 1) * 3;// 每条学院记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(deps.get(depID - 1).getDepname());// ?????? 能保证得到的学院是按编号顺序排列吗????
//			System.out.println(index);
//			System.out.println(deps.get(depID - 1).getDepname());

			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf11Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				row = sheet.createRow(index + i);
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue("层级" + (char) (i + 65));

				for (int j = 2; j <= 12; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					// System.out.println(j-2);
					if (count != 0) {
						float p = ((float) countOf11Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}

				}
				row = sheet.createRow(index + i + 1);
			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByTeachersOf11Capbility(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("11项能力-所有导员");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有导员所带学生11项能力情况", "导员", 12);

		int gradeID = 1; // 年级号
		int teID = 1; // 学院号
		int quesID = 1; // 题号
		int awNum = 1; // 答案
		int countOf11Terms[][] = new int[11][3]; //

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		List<TeacherBean> tbs = getTes(conn);// 导员列表

		// 以学院为单位 查询数据库 然后写入execel

		for (teID = 1; teID <= tbs.size(); teID++) {
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				for (quesID = 1; quesID <= 11; quesID++) {
					for (awNum = 1; awNum <= 3; awNum++) {

						String sql = "SELECT COUNT(s"
								+ quesID
								+ ") FROM (SELECT * FROM  gradeinfo_"
								+ gradeID
								+ " WHERE sid IN (SELECT sid FROM student WHERE tid='"
								+ tbs.get(teID - 1).getTid()
								+ "' AND isgrade=true)) AS temp " + "WHERE s"
								+ quesID + "='" + awNum + "'";
						// System.out.println(sql);
						try {
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							if (rs.next()) {
								countOf11Terms[quesID - 1][awNum - 1] = countOf11Terms[quesID - 1][awNum - 1]
										+ rs.getInt("count(s" + quesID + ")");// ????
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.close(ps, rs);
						}
					}
				}
			}

			// 以学院为单位 写入execel
			int index = 2 + (teID - 1) * 3;// 每条导员记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 导员名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(tbs.get(teID - 1).getTname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????
			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf11Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 12; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					// System.out.println(j-2);
					if (count != 0) {
						float p = ((float) countOf11Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}

				}
				row = sheet.createRow(index + i + 1);
			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByGradesOf11Capbility(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("11项能力-所有年级");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有年级11项能力情况", "年级", 12);

		int gradeID = 1; // 年级号
		int quesID = 1; // 题号
		int awNum = 1; // 答案
		int countOf11Terms[][] = new int[11][3]; //

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;

		// 以学院为单位 查询数据库 然后写入execel

		for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
			for (quesID = 1; quesID <= 11; quesID++) {
				for (awNum = 1; awNum <= 3; awNum++) {
					String sql = "SELECT COUNT(s" + quesID
							+ ") FROM gradeinfo_" + gradeID + " WHERE s"
							+ quesID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf11Terms[quesID - 1][awNum - 1] = rs
									.getInt("count(s" + quesID + ")");// ????
							// rs.getInt()
							// 0 or
							// 1
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
			}

			// 以学院为单位 写入execel
			int index = 2 + (gradeID - 1) * 3;// 每条导员记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell((short) 0);// 导员名称单元格
			cell.setCellStyle(normalStyle);

			Calendar a = Calendar.getInstance();// 系统当前年份计算在校年级(按第二学期年份为准)
			int year = a.get(Calendar.YEAR);
			cell.setCellValue((year - gradeID) + "");// ??????
			// 能保证得到的学院是按编号顺序排列吗????

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 12; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					// System.out.println(j-2);
					cell.setCellValue(countOf11Terms[j - 2][i]);

				}
				row = sheet.createRow(index + i + 1);

			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByClazzsOf11Capbility(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("11项能力-所有班级");
		// "所有导员所带学生24题选题情况"
		setTitlePattern1(wb, sheet, "所有班级11项能力情况", "班级", 12);

		int gradeID = 1; // 年级号
		int clazzID = 1; // 班级号
		int quesID = 1; // 题号
		int awNum = 1; // 答案
		int countOf11Terms[][] = new int[11][3]; //
		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;

		Connection conn = DBUtil.getConnection();
		List<ClazzBean> clas = getClas(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (clazzID = 1; clazzID <= clas.size(); clazzID++) {
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				for (quesID = 1; quesID <= 11; quesID++) {
					for (awNum = 1; awNum <= 3; awNum++) {

						String sql = "SELECT COUNT(s"
								+ quesID
								+ ") FROM (SELECT * FROM  gradeinfo_"
								+ gradeID
								+ " WHERE sid IN (SELECT sid FROM student WHERE clazzid='"
								+ clazzID + "' AND isgrade=true)) AS temp "
								+ "WHERE s" + quesID + "='" + awNum + "'";
//						 System.out.println(sql);
						try {
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							if (rs.next()) {
								countOf11Terms[quesID - 1][awNum - 1] = countOf11Terms[quesID - 1][awNum - 1]
										+ rs.getInt("count(s" + quesID + ")");// ????
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.close(ps, rs);
						}
					}
				}
			}

			// 以班级为单位 写入execel
			int index = 2 + (clazzID - 1) * 3;// 每条学院记录在execel的左上角行数索引
			sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0, 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell(0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(clas.get(clazzID - 1).getClazzname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????

			// 参选人数
			int count = 0;
			for (int i = 0; i < 3; i++) {
				count += countOf11Terms[0][i];
			}

			for (int i = 0; i < 3; i++) {
				cell = row.createCell(1);
				cell.setCellStyle(normalStyle);
				cell.setCellValue((char) (65 + i) + "");

				for (int j = 2; j <= 12; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(percentStyle);
					if (count != 0) {
						float p = ((float) countOf11Terms[j - 2][i] / (float) count);
						cell.setCellValue(p);
					} else {
						cell.setCellValue(0);
					}

				}
				row = sheet.createRow(index + i + 1);

			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByTeachersOf2Levels(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("2项层级-所有导员");
		// "所有导员所带学生24题选题情况"
		setTitlePattern2(wb, sheet, "所有导员所带学生2项层级情况", "导员", 10);

		int gradeID = 1; // 年级号
		int teID = 1; // 学院号
		int levelID = 1; // 题号
		int awNum = 1; // 答案

		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		List<TeacherBean> tbs = getTes(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (teID = 1; teID <= tbs.size(); teID++) {
			int countOf2Levers[][] = new int[2][];
			countOf2Levers[0] = new int[4];
			countOf2Levers[1] = new int[5];

			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				// for (levelID = 1; levelID <= 2; levelID++) {
				levelID = 1;
				for (awNum = 1; awNum <= 4; awNum++) {
					String sql = "SELECT COUNT(l"
							+ levelID
							+ ") FROM (SELECT * FROM  gradeinfo_"
							+ gradeID
							+ " WHERE sid IN (SELECT sid FROM student WHERE tid='"
							+ tbs.get(teID - 1).getTid()
							+ "' AND isgrade=true)) AS temp " + "WHERE l"
							+ levelID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf2Levers[levelID - 1][awNum - 1] = countOf2Levers[levelID - 1][awNum - 1]
									+ rs.getInt("count(l" + levelID + ")");// ????
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
				levelID = 2;
				for (awNum = 5; awNum <= 9; awNum++) {
					String sql = "SELECT COUNT(l"
							+ levelID
							+ ") FROM (SELECT * FROM  gradeinfo_"
							+ gradeID
							+ " WHERE sid IN (SELECT sid FROM student WHERE tid='"
							+ tbs.get(teID - 1).getTid()
							+ "' AND isgrade=true)) AS temp " + "WHERE l"
							+ levelID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf2Levers[levelID - 1][awNum - 5] = countOf2Levers[levelID - 1][awNum - 5]
									+ rs.getInt("count(l" + levelID + ")");// ????
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
			}

			// 以学院为单位 写入execel
			int index = (teID + 2);// 内容行的索引
			// sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0,
			// 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell((short) 0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(tbs.get(teID - 1).getTname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????

			// 参选人数
			int count = 0;
			for (int i = 0; i < 4; i++) {
				count += countOf2Levers[0][i];
			}

			for (int i = 0; i < 4; i++) {
				cell = row.createCell(i + 1);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[0][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}

			for (int i = 0; i < 5; i++) {
				cell = row.createCell(i + 6);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[1][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByGradesOf2Levels(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("2项层级-所有年级");
		// "所有导员所带学生24题选题情况"
		setTitlePattern2(wb, sheet, "所有年级2项层级情况", "年级", 10);

		int gradeID = 1; // 年级号
		int levelID = 1; // 题号
		int awNum = 1; // 答案

		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();

		// 以学院为单位 查询数据库 然后写入execel

		int countOf2Levers[][] = new int[2][];
		countOf2Levers[0] = new int[4];
		countOf2Levers[1] = new int[5];
		for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
			// for (levelID = 1; levelID <= 2; levelID++) {
			levelID = 1;
			for (awNum = 1; awNum <= 4; awNum++) {
				String sql = "SELECT COUNT(l" + levelID
						+ ") FROM (SELECT * FROM  gradeinfo_" + gradeID
						+ ") AS temp " + "WHERE l" + levelID + "='" + awNum
						+ "'";
				System.out.println(sql);
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						countOf2Levers[levelID - 1][awNum - 1] = countOf2Levers[levelID - 1][awNum - 1]
								+ rs.getInt("count(l" + levelID + ")");// ????
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBUtil.close(ps, rs);
				}
			}
			levelID = 2;
			for (awNum = 5; awNum <= 9; awNum++) {
				String sql = "SELECT COUNT(l" + levelID
						+ ") FROM (SELECT * FROM  gradeinfo_" + gradeID
						+ ") AS temp " + "WHERE l" + levelID + "='" + awNum
						+ "'";
				// System.out.println(sql);
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						countOf2Levers[levelID - 1][awNum - 5] = countOf2Levers[levelID - 1][awNum - 5]
								+ rs.getInt("count(l" + levelID + ")");// ????
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBUtil.close(ps, rs);
				}
			}

			// 以学院为单位 写入execel
			int index = (gradeID + 2);// 内容行的索引
			// sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0,
			// 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell((short) 0);// 学院名称单元格
			cell.setCellStyle(normalStyle);

			Calendar a = Calendar.getInstance();// 系统当前年份计算在校年级(按第二学期年份为准)
			int year = a.get(Calendar.YEAR);
			cell.setCellValue((year - gradeID) + "级");// ??????
			System.out.println(index);
			System.out.println((year - gradeID) + "级");

			// 参选人数
			int count = 0;
			for (int i = 0; i < 4; i++) {
				count += countOf2Levers[0][i];
			}

			for (int i = 0; i < 4; i++) {
				cell = row.createCell(i + 1);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[0][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}

			for (int i = 0; i < 5; i++) {
				cell = row.createCell(i + 6);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[1][i] / (float) count);
					System.out.println(countOf2Levers[1][i]);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}
		}
		DBUtil.close(conn);
	}

	public static void generateExcelByClazzsOf2Levels(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("2项层级-所有班级");
		// "所有导员所带学生24题选题情况"
		setTitlePattern2(wb, sheet, "所有班级2项层级情况", "班级", 10);

		int gradeID = 1; // 年级号
		int clazzID = 1; // 班级号
		int levelID = 1; // 题号
		int awNum = 1; // 答案

		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		List<ClazzBean> clas = getClas(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (clazzID = 1; clazzID <= clas.size(); clazzID++) {
			int countOf2Levers[][] = new int[2][];
			countOf2Levers[0] = new int[4];
			countOf2Levers[1] = new int[5];
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				// for (levelID = 1; levelID <= 2; levelID++) {
				levelID = 1;
				for (awNum = 1; awNum <= 4; awNum++) {
					String sql = "SELECT COUNT(l"
							+ levelID
							+ ") FROM (SELECT * FROM  gradeinfo_"
							+ gradeID
							+ " WHERE sid IN (SELECT sid FROM student WHERE clazzid='"
							+ clazzID + "' AND isgrade=true)) AS temp "
							+ "WHERE l" + levelID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf2Levers[levelID - 1][awNum - 1] = countOf2Levers[levelID - 1][awNum - 1]
									+ rs.getInt("count(l" + levelID + ")");// ????
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
				levelID = 2;
				for (awNum = 5; awNum <= 9; awNum++) {
					String sql = "SELECT COUNT(l"
							+ levelID
							+ ") FROM (SELECT * FROM  gradeinfo_"
							+ gradeID
							+ " WHERE sid IN (SELECT sid FROM student WHERE clazzid='"
							+ clazzID + "'AND isgrade=true)) AS temp "
							+ "WHERE l" + levelID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf2Levers[levelID - 1][awNum - 5] = countOf2Levers[levelID - 1][awNum - 5]
									+ rs.getInt("count(l" + levelID + ")");// ????
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
			}

			// 以学院为单位 写入execel
			int index = (clazzID + 2);// 内容行的索引
			// sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0,
			// 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell((short) 0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(clas.get(clazzID - 1).getClazzname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????

			// 参选人数
			int count = 0;
			for (int i = 0; i < 4; i++) {
				count += countOf2Levers[0][i];
			}

			for (int i = 0; i < 4; i++) {
				cell = row.createCell(i + 1);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[0][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}

			for (int i = 0; i < 5; i++) {
				cell = row.createCell(i + 6);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[1][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}
		}

		DBUtil.close(conn);
	}

	public static void generateExcelByAcademysOf2Level2(HSSFWorkbook wb,
			HSSFCellStyle normalStyle, HSSFCellStyle percentStyle) {

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("2项层级-所有学院");
		// "所有导员所带学生24题选题情况"
		setTitlePattern2(wb, sheet, "所有学院2项层级情况", "学院", 10);

		int gradeID = 1; // 年级号
		int depID = 1; // 学院号
		int levelID = 1; // 题号
		int awNum = 1; // 答案

		PreparedStatement ps = null; // 预编译
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		List<DepartmentBean> deps = getDeps(conn);

		// 以学院为单位 查询数据库 然后写入execel

		for (depID = 1; depID <= deps.size(); depID++) {
			int countOf2Levers[][] = new int[2][];
			countOf2Levers[0] = new int[4];
			countOf2Levers[1] = new int[5];
			for (gradeID = 1; gradeID <= GRADE_NUM; gradeID++) {
				// for (levelID = 1; levelID <= 2; levelID++) {
				levelID = 1;
				for (awNum = 1; awNum <= 4; awNum++) {
					String sql = "SELECT COUNT(l"
							+ levelID
							+ ") FROM (SELECT * FROM  gradeinfo_"
							+ gradeID
							+ " WHERE sid IN (SELECT sid FROM student WHERE depid='"
							+ depID + "' AND isgrade=true)) AS temp "
							+ "WHERE l" + levelID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf2Levers[levelID - 1][awNum - 1] = countOf2Levers[levelID - 1][awNum - 1]
									+ rs.getInt("count(l" + levelID + ")");// ????
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
				levelID = 2;
				for (awNum = 5; awNum <= 9; awNum++) {
					String sql = "SELECT COUNT(l"
							+ levelID
							+ ") FROM (SELECT * FROM  gradeinfo_"
							+ gradeID
							+ " WHERE sid IN (SELECT sid FROM student WHERE depid='"
							+ depID + "'AND isgrade=true)) AS temp "
							+ "WHERE l" + levelID + "='" + awNum + "'";
					// System.out.println(sql);
					try {
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							countOf2Levers[levelID - 1][awNum - 5] = countOf2Levers[levelID - 1][awNum - 5]
									+ rs.getInt("count(l" + levelID + ")");// ????
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						DBUtil.close(ps, rs);
					}
				}
			}

			// 以学院为单位 写入execel
			int index = (depID + 2);// 内容行的索引
			// sheet.addMergedRegion(new CellRangeAddress(index, index + 2, 0,
			// 0));// 合并单元格(startRow，endRow，startColumn，endColumn)
			HSSFRow row = sheet.createRow(index);
			HSSFCell cell = row.createCell((short) 0);// 学院名称单元格
			cell.setCellStyle(normalStyle);
			cell.setCellValue(deps.get(depID - 1).getDepname());// ??????
			// 能保证得到的学院是按编号顺序排列吗????

			// 参选人数
			int count = 0;
			for (int i = 0; i < 4; i++) {
				count += countOf2Levers[0][i];
			}

			for (int i = 0; i < 4; i++) {
				cell = row.createCell(i + 1);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[0][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}

			for (int i = 0; i < 5; i++) {
				cell = row.createCell(i + 6);
				cell.setCellStyle(percentStyle);
				if (count != 0) {
					float p = ((float) countOf2Levers[1][i] / (float) count);
					cell.setCellValue(p);
				} else {
					cell.setCellValue(0);
				}
			}
		}

		DBUtil.close(conn);
	}

	public static void getAllExcel24Terms(HSSFWorkbook wb) {
		long time = System.currentTimeMillis();
		HSSFCellStyle style1 = getContentStyle(wb);
		HSSFCellStyle style2 = getlPercentStyle(wb);
		System.out.println("generateExcelByAcademysOf24Terms....");
		generateExcelByAcademysOf24Terms(wb, style1, style2);
		System.out.println("generateExcelByTeachersOf24Terms....");
		generateExcelByTeachersOf24Terms(wb, style1, style2);
		System.out.println("generateExcelByGradesOf24Terms....");
		generateExcelByGradesOf24Terms(wb, style1, style2);
		System.out.println("generateExcelByClazzsOf24Terms....");
		generateExcelByClazzsOf24Terms(wb, style1, style2);
		System.out.println("开始写入文件....+\n");
		long time1 = System.currentTimeMillis();
		System.out.println(time1 - time + "ms");
		System.out.println("over");

	}

	public static void getAllExcel11Capbilitys(HSSFWorkbook wb) {
		long time = System.currentTimeMillis();
		HSSFCellStyle style1 = getContentStyle(wb);
		HSSFCellStyle style2 = getlPercentStyle(wb);
		System.out.println("generateExcelByAcademysOf11Capbility....");
		generateExcelByAcademysOf11Capbility(wb, style1, style2);
		System.out.println("generateExcelByTeachersOf11Capbility....");
		generateExcelByTeachersOf11Capbility(wb, style1, style2);
		System.out.println("generateExcelByGradesOf11Capbility....");
		generateExcelByGradesOf11Capbility(wb, style1, style2);
		System.out.println("generateExcelByClazzsOf11Capbility....");
		generateExcelByClazzsOf11Capbility(wb, style1, style2);
		System.out.println("开始写入文件....+\n");
		long time1 = System.currentTimeMillis();
		System.out.println(time1 - time + "ms");
		System.out.println("over");
	}

	public static void getAllExcel2Levels(HSSFWorkbook wb) {
		long time = System.currentTimeMillis();
		HSSFCellStyle style1 = getContentStyle(wb);
		HSSFCellStyle style2 = getlPercentStyle(wb);
		System.out.println("generateExcelByAcademysOf2Level2....");
		generateExcelByAcademysOf2Level2(wb, style1, style2);
		System.out.println("generateExcelByTeachersOf2Levels...");
		generateExcelByTeachersOf2Levels(wb, style1, style2);
		System.out.println("generateExcelByGradesOf2Levels...");
		generateExcelByGradesOf2Levels(wb, style1, style2);
		System.out.println("generateExcelByClazzsOf2Levels...");
		generateExcelByClazzsOf2Levels(wb, style1, style2);
		System.out.println("开始写入文件....+\n");
		long time1 = System.currentTimeMillis();
		System.out.println(time1 - time + "ms");
		System.out.println("over");
	}

	public static void main(String[] args) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();

		// getAllExcel24Terms(wb);
		// getAllExcel2Levels(wb);
		 getAllExcel11Capbilitys(wb);

		FileOutputStream os = new FileOutputStream("D:\\test.xls");
		wb.write(os);
		os.close();
	}
}
