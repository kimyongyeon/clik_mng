package clikmng.nanet.go.kr.mob.web;

import java.net.URLEncoder;
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

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.mob.service.MobManageDefaultVO;
import clikmng.nanet.go.kr.mob.service.MobileLogVO;

public class MusExcel extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{

		 String excelName = "모바일 이용현황";

		 HSSFSheet worksheet = null;
		 HSSFRow row = null;
		 HSSFCell cell = null;

		 worksheet = workbook.createSheet(excelName+ " WorkSheet");

		@SuppressWarnings("unchecked")
		MobManageDefaultVO 				searchVO		=	(MobManageDefaultVO)ModelMap.get("searchVO");
		List<MobileLogVO> resultSumList = (List<MobileLogVO>) ModelMap.get("resultSumList");
		List<MobileLogVO> resultList = (List<MobileLogVO>) ModelMap.get("resultList");

		
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
//		cell = row.createCell(4);
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellStyle(style);

		// cell 병합
		//worksheet.addMergedRegion(new Region(0, (short)0, 0, (short)6));        	
		worksheet.addMergedRegion(new Region(0, (short)0, 0, (short)3));

		// 써머리
		row = worksheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("누적통계");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("누적 설치횟수");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("누적 실행횟수");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellStyle(style);

		row = worksheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("iOS");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("Android");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("Total");
		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("iOS");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellValue("Android");
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellValue("Total");
//		cell.setCellStyle(style);
		
		int sFR_ios = 0;
		int sFR_android = 0;
		int sNR_ios = 0;
		int sNR_android = 0;
		for(MobileLogVO vo : resultSumList)
    	{
			if("ios".equals(vo.getAppOs()) && "FR".equals(vo.getLogSeCode())){
				sFR_ios = Integer.parseInt(vo.getCnt());
			}else if("android".equals(vo.getAppOs()) && "FR".equals(vo.getLogSeCode())){
				sFR_android = Integer.parseInt(vo.getCnt());
			}else if("ios".equals(vo.getAppOs()) && "NR".equals(vo.getLogSeCode())){
				sNR_ios = Integer.parseInt(vo.getCnt());
			}else if("android".equals(vo.getAppOs()) && "NR".equals(vo.getLogSeCode())){
				sNR_android = Integer.parseInt(vo.getCnt());
			}
    	}
		row = worksheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue(CommonStringUtil.zeroConvertHashMap(sFR_ios));
		cell.setCellStyle(style_number);
		cell = row.createCell(2);	
		cell.setCellValue(CommonStringUtil.zeroConvertHashMap(sFR_android));
		cell.setCellStyle(style_number);
		cell = row.createCell(3);
		cell.setCellValue(CommonStringUtil.zeroConvertHashMap(sFR_ios + sFR_android));
		cell.setCellStyle(style_number);
//		cell = row.createCell(4);
//		cell.setCellValue(CommonStringUtil.zeroConvertHashMap(sNR_ios));
//		cell.setCellStyle(style_number);
//		cell = row.createCell(5);
//		cell.setCellValue(CommonStringUtil.zeroConvertHashMap(sNR_android));
//		cell.setCellStyle(style_number);
//		cell = row.createCell(6);
//		cell.setCellValue(CommonStringUtil.zeroConvertHashMap(sNR_ios + sNR_android));
//		cell.setCellStyle(style_number);

		// cell 병합
		worksheet.addMergedRegion(new Region(2, (short)0, 4, (short)0));
		worksheet.addMergedRegion(new Region(2, (short)1, 2, (short)3));
//		worksheet.addMergedRegion(new Region(2, (short)4, 2, (short)6));		
		
		
		// 테이블 헤더
		row = worksheet.createRow(8);
		cell = row.createCell(0);
		cell.setCellValue("일자");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("설치횟수");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("실행횟수");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellStyle(style);

		row = worksheet.createRow(9);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("iOS");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("Android");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("Total");
		cell.setCellStyle(style);
//		cell = row.createCell(4);
//		cell.setCellValue("iOS");
//		cell.setCellStyle(style);
//		cell = row.createCell(5);
//		cell.setCellValue("Android");
//		cell.setCellStyle(style);
//		cell = row.createCell(6);
//		cell.setCellValue("Total");
//		cell.setCellStyle(style);



		// cell 병합
		worksheet.addMergedRegion(new Region(8, (short)0, 9, (short)0));
		worksheet.addMergedRegion(new Region(8, (short)1, 8, (short)3));
//		worksheet.addMergedRegion(new Region(8, (short)4, 8, (short)6));


		int i = 10;
		int sum_FR_ios = 0;
		int sum_FR_android = 0;
		int sum_NR_ios = 0;
		int sum_NR_android = 0;
		
		for(MobileLogVO vo : resultList)
    	{
			String creatDt = vo.getCreatDt();
			int FR_ios = Integer.parseInt(vo.getFR_ios());
			int FR_android = Integer.parseInt(vo.getFR_android());
			int NR_ios = Integer.parseInt(vo.getNR_ios());
			int NR_android = Integer.parseInt(vo.getNR_android());
			sum_FR_ios += FR_ios;
			sum_FR_android += FR_android;
			sum_NR_ios += NR_ios;
			sum_NR_android += NR_android;
			
			row = worksheet.createRow(i++);
			cell = row.createCell(0);
			cell.setCellValue(creatDt.substring(0, 4) + "." + creatDt.substring(4, 6) + (creatDt.length() > 6 ? "." + creatDt.substring(6, 8) : "")); //"일자"
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue(FR_ios); //"iOS"
			cell.setCellStyle(style_number);
			cell = row.createCell(2);
			cell.setCellValue(FR_android); //"Android"
			cell.setCellStyle(style_number);
			cell = row.createCell(3);
			cell.setCellValue(FR_ios + FR_android); //"Total"
			cell.setCellStyle(style_number);
//			cell = row.createCell(4);
//			cell.setCellValue(NR_ios); //"iOS"
//			cell.setCellStyle(style_number);
//			cell = row.createCell(5);
//			cell.setCellValue(NR_android); //"Android"
//			cell.setCellStyle(style_number);
//			cell = row.createCell(6);
//			cell.setCellValue(NR_ios + NR_android); //"Total"
//			cell.setCellStyle(style_number);
    	}
		
		//합계
		row = worksheet.createRow(i++);
		cell = row.createCell(0);
		cell.setCellValue("합계"); 
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue(sum_FR_ios); //"iOS"
		cell.setCellStyle(style_number);
		cell = row.createCell(2);
		cell.setCellValue(sum_FR_android); //"Android"
		cell.setCellStyle(style_number);
		cell = row.createCell(3);
		cell.setCellValue(sum_FR_ios + sum_FR_android); //"Total"
		cell.setCellStyle(style_number);
//		cell = row.createCell(4);
//		cell.setCellValue(sum_NR_ios); //"iOS"
//		cell.setCellStyle(style_number);
//		cell = row.createCell(5);
//		cell.setCellValue(sum_NR_android); //"Android"
//		cell.setCellStyle(style_number);
//		cell = row.createCell(6);
//		cell.setCellValue(sum_NR_ios + sum_NR_android); //"Total"
//		cell.setCellStyle(style_number);
    		
		worksheet.setColumnWidth(0,6000);
		worksheet.setColumnWidth(1,6000);
		worksheet.setColumnWidth(2,6000);
		worksheet.setColumnWidth(3,6000);
//		worksheet.setColumnWidth(4,6000);
//		worksheet.setColumnWidth(5,6000);
//		worksheet.setColumnWidth(6,6000);
		
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