package clikmng.nanet.go.kr.uss.mng.web;

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

import clikmng.nanet.go.kr.uss.mng.service.MngManage;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;


public class LocalMngListExcel extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{
		
		List<MngManage>	resultList		=	(List<MngManage>)ModelMap.get("resultList");
		
		String excelName = "지방의회_지자체담당자_목록";

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
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9)); // cell 병합        	

			// 테이블 헤더
			row = worksheet.createRow(2);
			
			cell = row.createCell(0);
			cell.setCellValue("번호");
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue("관리자");
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue("아이디");
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue("이름");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue("소속");
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue("대표관심의회");
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue("전화번호");
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue("이메일");
			cell.setCellStyle(style);
			
			cell = row.createCell(8);
			cell.setCellValue("등록일");
			cell.setCellStyle(style);
			
			cell = row.createCell(9);
			cell.setCellValue("승인여부");
			cell.setCellStyle(style);
			
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				MngManage vo = resultList.get(i);
				
				row = worksheet.createRow(i + 3);
			
				//번호
				cell = row.createCell(0);
				cell.setCellValue((k-1)*50000 + i + 1);	
				cell.setCellStyle(style);
				
				//관리자
				String[] chargerSeCodeList = {"지원","관리자","의원"};
				String chargerSeCodeNm = "";
				
				if("001".equals(vo.getChargerSeCode())) chargerSeCodeNm = chargerSeCodeList[0];
				if("002".equals(vo.getChargerSeCode())) chargerSeCodeNm = chargerSeCodeList[1];
				if("003".equals(vo.getChargerSeCode())) chargerSeCodeNm = chargerSeCodeList[2];
				
				cell = row.createCell(1);
				cell.setCellValue(chargerSeCodeNm);
				cell.setCellStyle(style);
				
				//아이디
				cell = row.createCell(2);
				cell.setCellValue(vo.getUnityId());
				cell.setCellStyle(style);
				
				//이름
				cell = row.createCell(3);
				cell.setCellValue(vo.getChargerNm());
				cell.setCellStyle(style);
				
				//소속
				cell = row.createCell(4);
				cell.setCellValue(vo.getLoasmNm());
				cell.setCellStyle(style);
				
				//대표관심의회
				cell = row.createCell(5);
				cell.setCellValue(vo.getIntRasmblyInsttClCodeNm1());
				cell.setCellStyle(style);
				
				//전화번호
				cell = row.createCell(6);
				cell.setCellValue(vo.getChargerTelno());
				cell.setCellStyle(style);
				
				//이메일
				cell = row.createCell(7);
				cell.setCellValue(vo.getChargerEmail());
				cell.setCellStyle(style);
				
				//등록일
				cell = row.createCell(8);
				cell.setCellValue(vo.getFrstRegisterPnttm());
				cell.setCellStyle(style);
				
				//승인여부
				cell = row.createCell(9);
				cell.setCellValue("Y".equals(vo.getConfmSttusAt()) ? "승인" : "미승인");
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
			worksheet.setColumnWidth(4,10000);
			worksheet.setColumnWidth(5,10000);
			worksheet.setColumnWidth(6,10000);
			worksheet.setColumnWidth(7,10000);
			worksheet.setColumnWidth(8,10000);
			worksheet.setColumnWidth(9,10000);
			
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