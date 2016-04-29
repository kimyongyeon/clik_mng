package clikmng.nanet.go.kr.col.cfm.service;

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


public class CfmListExcel extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{
		
		List<CfmCompareListVO>	resultList		=	(List<CfmCompareListVO>)ModelMap.get("resultList");
		
		String excelName = "표준연계API_파일동기화_목록";

		// 만들 sheet의 갯수를 정한다.
		int maxCount = Integer.parseInt(ModelMap.get("resultCnt")+"")/50000 ;
		 
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
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3)); // cell 병합        	

			// 테이블 헤더
			row = worksheet.createRow(2);
			
			cell = row.createCell(0);
			cell.setCellValue("번호");
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue("KEY정보");
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue("파일URL");
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue("파일저장경로");
			cell.setCellStyle(style);
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				CfmCompareListVO vo = resultList.get(i);
				
				row = worksheet.createRow(i + 3);
			
				//번호
				cell = row.createCell(0);
				cell.setCellValue((k-1)*50000 + i + 1);	
				cell.setCellStyle(style);
				
				//KEY정보
				cell = row.createCell(1);
				cell.setCellValue(vo.getApi_key());
				cell.setCellStyle(style);
				
				//파일URL
				cell = row.createCell(2);
				cell.setCellValue(vo.getFile_url());
				cell.setCellStyle(style);
				
				//파일저장경로
				cell = row.createCell(3);
				cell.setCellValue(vo.getFile_path());
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
	    		
			worksheet.setColumnWidth(0,5000);
			worksheet.setColumnWidth(1,10000);
			worksheet.setColumnWidth(2,10000);
			worksheet.setColumnWidth(3,10000);
			
		 }
		
		StringBuilder excelFileName = new StringBuilder();

		excelFileName.append(URLEncoder.encode(excelName, "UTF-8"));
		excelFileName.append(".xls");
		
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="+excelFileName+"");
		
		System.out.println("--------------------------------------------------------------------------> excel download complete.");
		response.flushBuffer(); 
	}
	
}