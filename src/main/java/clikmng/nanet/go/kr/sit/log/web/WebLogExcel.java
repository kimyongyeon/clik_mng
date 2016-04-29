package clikmng.nanet.go.kr.sit.log.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import clikmng.nanet.go.kr.sit.log.service.LogManageVO;


public class WebLogExcel extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{

		 String excelName = "웹 로그";

		 HSSFSheet worksheet = null;
		 HSSFRow row = null;
		 HSSFCell cell = null;

		 worksheet = workbook.createSheet(excelName+ " WorkSheet");

		@SuppressWarnings("unchecked")
		LogManageVO 		searchVO			=	(LogManageVO)ModelMap.get("searchVO");
		String				searchKeyword		=	(String)ModelMap.get("searchKeyword");
		String				selSearchGubun		=	(String)ModelMap.get("selSearchGubun");
		List<LogManageVO>	resultList			=	(List<LogManageVO>)ModelMap.get("resultList");
		String				resultCnt			=	(String)ModelMap.get("resultCnt");
		
		
		// cell style 
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

		// cell style 2
		HSSFCellStyle style_number = workbook.createCellStyle();
		style_number.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_number.setBottomBorderColor(HSSFColor.BLACK.index);
		style_number.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_number.setLeftBorderColor(HSSFColor.BLACK.index);
		style_number.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_number.setRightBorderColor(HSSFColor.BLACK.index);
		style_number.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_number.setTopBorderColor(HSSFColor.BLACK.index);
		style_number.setWrapText(true);
		style_number.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		// 시트제목
		row = worksheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue(excelName);
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellStyle(style);

		// cell 병합
		worksheet.addMergedRegion(new Region(0,(short)0,0,(short)5));        	

		// 테이블 헤더
		row = worksheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("로그ID");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("발생일자");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("발생시간");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("URL");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("사용자IP");
		cell.setCellStyle(style);

		
		for( int i = 0 ; i < resultList.size(); i++ )
		{
			LogManageVO vo = resultList.get(i);
			
			row = worksheet.createRow(i + 3);
		
			cell = row.createCell(0);
			cell.setCellValue(i + 1); //"번호"
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(vo.getRequestId()); //"로그ID";
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(vo.getOccrrncDeDate()); // "발생일자"
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue(vo.getOccrrncDeTime()); //"발생시간"
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue(vo.getRequestUrl()); // "URL"
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(vo.getRequesterIp()); //"사용자IP"
			cell.setCellStyle(style);
    		
		}        
    		
		worksheet.setColumnWidth(0,10000);
		worksheet.setColumnWidth(1,10000);
		worksheet.setColumnWidth(2,10000);
		worksheet.setColumnWidth(3,10000);
		worksheet.setColumnWidth(4,10000);
		worksheet.setColumnWidth(5,10000);
		
		//worksheet.autoSizeColumn(0);
		//worksheet.autoSizeColumn(1);
		//worksheet.autoSizeColumn(2);
		//worksheet.autoSizeColumn(3);
		//worksheet.autoSizeColumn(4);
		
		StringBuilder excelFileName = new StringBuilder();

		excelFileName.append(URLEncoder.encode(excelName, "UTF-8"));
		excelFileName.append("(");		
		
		if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false)
		{
			excelFileName.append(searchVO.getSchDt1());
		}
		if( (searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false) ||
			(searchVO.getSchDt2() != null && "".equals(searchVO.getSchDt2()) == false) )
		{
			excelFileName.append("-");			
		}
		if(searchVO.getSchDt2() != null && "".equals(searchVO.getSchDt2()) == false)
		{
			excelFileName.append(searchVO.getSchDt2());
		}
		
		excelFileName.append(")");
		excelFileName.append(".xls");
 
		
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="+excelFileName+"");
		
		System.out.println("--------------------------------------------------------------------------> excel download complete.");
		response.flushBuffer(); 
	}
	
}