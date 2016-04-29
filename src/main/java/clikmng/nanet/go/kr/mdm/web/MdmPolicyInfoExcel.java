package clikmng.nanet.go.kr.mdm.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;


public class MdmPolicyInfoExcel extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{
		
		MdmSearchVO 				searchVO		=	(MdmSearchVO)ModelMap.get("searchVO");
		List<MdmPolicyInfoVO>	resultList		=	(List<MdmPolicyInfoVO>)ModelMap.get("resultList");
		
		String excelName = "지방정책정보_메타데이터";
		
		Pattern pattern = Pattern.compile("^[_a-zA-Z가-힣]");
		
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
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14)); // cell 병합        	

			// 테이블 헤더
			row = worksheet.createRow(2);
			cell = row.createCell(0);
			cell.setCellValue("일련번호");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("기관명");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("게시판명");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("자료유형");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("제목");
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue("작성자");
			cell.setCellStyle(style);
//			cell = row.createCell(6);
//			cell.setCellValue("첨부파일유무");
//			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("등록일");
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue("수집일");
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue("삭제");
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue("중복");
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue("게시");
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue("문서번호");
			cell.setCellStyle(style);
			cell = row.createCell(12);
			cell.setCellValue("수집URL");
			cell.setCellStyle(style);
			cell = row.createCell(13);
			cell.setCellValue("서비스URL");
			cell.setCellStyle(style);
			
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				MdmPolicyInfoVO vo = resultList.get(i);
				
				row = worksheet.createRow(i + 3);
			
				//일련번호
				cell = row.createCell(0);
				cell.setCellValue((k-1)*50000 + i + 1);	
				cell.setCellStyle(style);
				
				//기관명
				cell = row.createCell(1);
				cell.setCellValue(vo.getSEEDNM());
				cell.setCellStyle(style);
				
				//게시판명
				cell = row.createCell(2);
				cell.setCellValue(vo.getSEEDNAME());
				cell.setCellStyle(style);
				
				//자료유형
				cell = row.createCell(3);
				cell.setCellValue(vo.getSITENM());
				cell.setCellStyle(style);
				
				//제목
				cell = row.createCell(4);
				cell.setCellValue(vo.getTITLE());
				cell.setCellStyle(style);
				
				//작성자
				cell = row.createCell(5);
				cell.setCellValue(vo.getWRITER());
				cell.setCellStyle(style);
				
				//첨부파일유무
//				cell = row.createCell(6);
//				if(vo.getFILECNT() > 0) {
//					String value = "";
//					if(vo.getFILESUCCCNT() > 0 ) {
//						value += "변환성공(" + vo.getFILESUCCCNT() + ")";
//					}
//					if(vo.getFILEFLRCNT() > 0 ) {
//						value += value.length() != 0 ? " / " : "";
//						value += "변환실패(" + vo.getFILEFLRCNT() + ")";
//					}
//					if((vo.getFILECNT() - vo.getFILESUCCCNT() - vo.getFILEFLRCNT()) > 0 ) {
//						value += value.length() != 0 ? " / " : "";
//						value += "변환대기(" + (vo.getFILECNT() - vo.getFILESUCCCNT() - vo.getFILEFLRCNT()) + ")";
//					}
//					cell.setCellValue(value);
//				}
//				cell.setCellStyle(style);
				
				//등록일
				cell = row.createCell(6);
//				if(vo.getCDATE() != null && !pattern.matcher(vo.getCDATE()).find()){
//					cell.setCellValue(CommonUtil.formatDate(vo.getCDATE()!=null ? vo.getCDATE() : "","-"));
//				}else{
//					cell.setCellValue("");
//				}
				cell.setCellValue(vo.getCDATE());
				cell.setCellStyle(style);
				
				//수집일
				cell = row.createCell(7);
				if(vo.getREGDATE() != null){
					cell.setCellValue(CommonUtil.formatDate(vo.getREGDATE()!=null ? vo.getREGDATE() : "","-"));
				}else{
					cell.setCellValue("");
				}
				cell.setCellStyle(style);
				
				//삭제
				cell = row.createCell(8);
				cell.setCellValue(vo.getCUD_CODE().equals("D") ? "삭제" : "");
				cell.setCellStyle(style);
				
				//중복
				cell = row.createCell(9);
				cell.setCellValue(vo.getDUPCNT() > 1 ? "중복(" + vo.getDUPCNT() + ")" : "");	
				cell.setCellStyle(style);

				//게시
				cell = row.createCell(10);
				if(vo.getISVIEW() != null && vo.getISVIEW().equals("N")){
					cell.setCellValue("미게시");
				} else {
					cell.setCellValue("게시");
				}
				cell.setCellStyle(style);

				//문서번호
				cell = row.createCell(11);
				cell.setCellValue(vo.getOUTBBS_CN());
				cell.setCellStyle(style);
				
				//수집URL
				cell = row.createCell(12);
				cell.setCellValue(vo.getURL());
				cell.setCellStyle(style);
				
				//서비스URL
				cell = row.createCell(13);
				cell.setCellValue("http://clik.nanet.go.kr/potal/search/searchView.do?collection=policyinfo&DOCID="+vo.getOUTBBS_CN());
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
			worksheet.setColumnWidth(10,10000);
			worksheet.setColumnWidth(11,10000);
			worksheet.setColumnWidth(12,10000);
			worksheet.setColumnWidth(13,10000);
//			worksheet.setColumnWidth(14,10000);
			
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