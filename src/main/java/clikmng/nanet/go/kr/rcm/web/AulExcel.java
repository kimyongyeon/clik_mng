package clikmng.nanet.go.kr.rcm.web;

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
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import clikmng.nanet.go.kr.rcm.service.RcmManageDefaultVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


public class AulExcel extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{
		
		RcmManageDefaultVO searchVO = (RcmManageDefaultVO)ModelMap.get("searchVO");
		List<EgovMap> resultList = (List<EgovMap>) ModelMap.get("resultList");
		
		 String excelName = "OPENAPI_이용내역";

		 // 만들 sheet의 갯수를 정한다.
		 int maxCount = Integer.parseInt((String)ModelMap.get("resultCnt"))/50000 ;
		 
		 // 50000건 그 이상이라면 maxCount + 1 시트
		 if(maxCount < 1) {
			 maxCount = 1;
		 } else {
			 maxCount += 1;
		 }

		 int k = 1;
		 for(int cnt = 1; cnt <= maxCount; cnt++) {
			 
			 HSSFSheet worksheet = null;
			 HSSFRow row = null;
			 HSSFCell cell = null;
			 
			 worksheet = workbook.createSheet(excelName + "_" + cnt);
			 
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
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4)); // cell 병합        	

			// 테이블 헤더
			row = worksheet.createRow(2);
			cell = row.createCell(0);
			cell.setCellValue("이용기관");
			cell.setCellStyle(style);
			worksheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 0)); // cell 병합
			
			cell = row.createCell(1);
			cell.setCellValue("OPENAPI 이용 트래픽");
			cell.setCellStyle(style);
			/*STYLE 적용을 위한 CELL 생성 S*/
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellStyle(style);
			/*STYLE 적용을 위한 CELL 생성 E*/
			worksheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3)); // cell 병합
			
			cell = row.createCell(4);
			cell.setCellValue("합계");
			worksheet.addMergedRegion(new CellRangeAddress(2, 3, 4, 4)); // cell 병합
			cell.setCellStyle(style);
			
			row = worksheet.createRow(3);
			cell = row.createCell(1);
			cell.setCellValue("회의록");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("의안");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("의원");
			cell.setCellStyle(style);
			/*STYLE 적용을 위한 CELL 생성 S*/
			cell = row.createCell(4);
			cell.setCellStyle(style);
			/*STYLE 적용을 위한 CELL 생성 E*/
			int reqstInsttTotal 		= 0;
			int minutesTotal 			= 0;
			int billTotal 					= 0;
			int assemblyinfoTotal 	= 0;
			
			int minutesCo 				= 0;
			int billCo 						= 0;
			int assemblyinfoCo 		= 0;
			int i 								= 0;
			
			for(  ; i < resultList.size(); i++ )
			{
				EgovMap vo = resultList.get(i);
				
				row = worksheet.createRow(i + 4);
				
				//이용기관 항목별 트래픽
				minutesCo 				= Integer.parseInt(vo.get("minutesCo").toString()); 
				billCo						= Integer.parseInt(vo.get("billCo").toString());
				assemblyinfoCo		= Integer.parseInt(vo.get("assemblyinfoCo").toString());
				
				//전체 트래픽
				minutesTotal 			+= minutesCo;
				billTotal					+= billCo;
				assemblyinfoTotal	+= assemblyinfoCo;
				
				//이용기관 전체 트래픽
				reqstInsttTotal			= minutesCo + billCo + assemblyinfoCo;
				
				//이용기관
				cell = row.createCell(0);
				cell.setCellValue(vo.get("reqstInsttNm").toString());
				cell.setCellStyle(style);
				
				//회의록
				cell = row.createCell(1);
				cell.setCellValue(minutesCo);
				cell.setCellStyle(style);
				
				//의안
				cell = row.createCell(2);
				cell.setCellValue(billCo);
				cell.setCellStyle(style);
				
				//의원
				cell = row.createCell(3);
				cell.setCellValue(assemblyinfoCo);
				cell.setCellStyle(style);
				
				//기관별 합계
				cell = row.createCell(4);
				cell.setCellValue(reqstInsttTotal);
				cell.setCellStyle(style);
				
				if(i == 49999) {
					k = k + 1;
					System.out.println("삭제 시작");
					Class<?> klass = resultList.getClass();
					Class<?>[] paramTypes = { Integer.TYPE, Integer.TYPE };
					java.lang.reflect.Method m = klass.getDeclaredMethod("removeRange", paramTypes);
					Object[] arguments = { new Integer(0), new Integer(50000) };
					m.setAccessible(true);
					m.invoke(resultList, arguments);
					System.out.println("삭제 완료");
					break;
				}
			}        
	    	
			//전체합계
			row = worksheet.createRow(i + 4);
			
			//이용기관
			cell = row.createCell(0);
			cell.setCellValue("합계");
			cell.setCellStyle(style);
			
			//회의록
			cell = row.createCell(1);
			cell.setCellValue(minutesTotal);
			cell.setCellStyle(style);
			
			//의안
			cell = row.createCell(2);
			cell.setCellValue(billTotal);
			cell.setCellStyle(style);
			
			//의원
			cell = row.createCell(3);
			cell.setCellValue(assemblyinfoTotal);
			cell.setCellStyle(style);
			
			//기관별 합계
			cell = row.createCell(4);
			cell.setCellValue(minutesTotal+billTotal+assemblyinfoTotal);
			cell.setCellStyle(style);
			
			worksheet.setColumnWidth(0,10000);
			worksheet.setColumnWidth(1,5000);
			worksheet.setColumnWidth(2,5000);
			worksheet.setColumnWidth(3,5000);
			worksheet.setColumnWidth(4,5000);
			
		 }
		
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