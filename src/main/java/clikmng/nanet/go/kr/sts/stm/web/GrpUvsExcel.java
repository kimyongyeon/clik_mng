package clikmng.nanet.go.kr.sts.stm.web;

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

import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.sts.stm.service.StmManageDefaultVO;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.UvsLogSummaryVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public class GrpUvsExcel extends AbstractExcelView{

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{

		 String excelName = "그룹별이용자방문통계";

		 HSSFSheet worksheet = null;
		 HSSFRow row = null;
		 HSSFCell cell = null;

		 worksheet = workbook.createSheet(excelName+ " WorkSheet");

		StmManageDefaultVO searchVO = (StmManageDefaultVO)ModelMap.get("searchVO");
		List<UvsLogSummaryVO> list = (List<UvsLogSummaryVO>)ModelMap.get("logList");

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
		worksheet.addMergedRegion(new Region(0,(short)0,0,(short)5));// cell 병합        	

		// 테이블 헤더
		row = worksheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("구분");
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)0,3,(short)0));// cell 병합
		
		cell = row.createCell(1);
		cell.setCellValue("PC");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)1,2,(short)2));// cell 병합
		
		cell = row.createCell(3);
		cell.setCellValue("모바일");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)3,2,(short)4));// cell 병합
		
		cell = row.createCell(5);
		cell.setCellValue("계");
		cell.setCellStyle(style);
		
		row = worksheet.createRow(3);
		cell  = row.createCell(1);
		cell.setCellValue("접속");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("검색");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("접속");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("검색");
		cell.setCellStyle(style);

		int rowNum = 4;
		
		int pcAccCnt = 0;
		int pcQurCnt = 0;
		int moAccCnt = 0;
		int moQurCnt = 0;
		
		int pcAccCntSum = 0;
		int pcQurCntSum = 0;
		int moAccCntSum = 0;
		int moQurCntSum = 0;
		
		try {
    		for(int i=0; i<list.size(); i++)
    		{
    			UvsLogSummaryVO item = list.get(i);
    			
    			pcAccCnt = Integer.parseInt(CommonUtil.nvl(item.getPc_access_cnt(), "0"));
    			pcQurCnt = Integer.parseInt(CommonUtil.nvl(item.getPc_query_cnt(), "0"));
    			moAccCnt = Integer.parseInt(CommonUtil.nvl(item.getMobile_access_cnt(), "0"));
    			moQurCnt = Integer.parseInt(CommonUtil.nvl(item.getMobile_query_cnt(), "0"));
    			pcAccCntSum += pcAccCnt;
    			pcQurCntSum += pcQurCnt;
    			moAccCntSum += moAccCnt;
    			moQurCntSum += moQurCnt;
    			
                row = worksheet.createRow(rowNum);
                cell = row.createCell(0);
                cell.setCellValue(item.getRasmbly_nm());
                cell.setCellStyle(style);
                
                cell = row.createCell(1);
                cell.setCellValue(item.getPc_access_cnt());
                cell.setCellStyle(style_number);
                
                cell = row.createCell(2);
                cell.setCellValue(item.getPc_query_cnt());
                cell.setCellStyle(style_number);
                
                cell = row.createCell(3);
                cell.setCellValue(item.getMobile_access_cnt());
                cell.setCellStyle(style_number);
                
                cell = row.createCell(4);
                cell.setCellValue(item.getMobile_query_cnt());
                cell.setCellStyle(style_number);
                
                cell = row.createCell(5);
                cell.setCellValue(pcAccCnt + pcQurCnt + moAccCnt + moQurCnt);
                cell.setCellStyle(style_number);
                
                rowNum++;
    		}
    		
    		//합계
    		row = worksheet.createRow(rowNum);
            cell = row.createCell(0);
            cell.setCellValue("합계");
            cell.setCellStyle(style);
            
            cell = row.createCell(1);
            cell.setCellValue(pcAccCntSum);
            cell.setCellStyle(style_number);
            
            cell = row.createCell(2);
            cell.setCellValue(pcQurCntSum);
            cell.setCellStyle(style_number);
            
            cell = row.createCell(3);
            cell.setCellValue(moAccCntSum);
            cell.setCellStyle(style_number);
            
            cell = row.createCell(4);
            cell.setCellValue(moQurCntSum);
            cell.setCellStyle(style_number);
            
            cell = row.createCell(5);
            cell.setCellValue(pcAccCntSum + pcQurCntSum + moAccCntSum + moQurCntSum);
            cell.setCellStyle(style_number);
		} catch (Exception e) {
			System.out.println(":::::: GRPUVSEXCEL ERROR OCCURED!!! ::::::  >>> " + e.getStackTrace());
			e.getMessage();
		}
		
		worksheet.setColumnWidth(0,10000);
		worksheet.setColumnWidth(1,3000);
		worksheet.setColumnWidth(2,3000);
		worksheet.setColumnWidth(3,3000);
		worksheet.setColumnWidth(4,3000);
		worksheet.setColumnWidth(4,3000);
				
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