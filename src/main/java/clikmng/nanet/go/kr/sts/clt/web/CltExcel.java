package clikmng.nanet.go.kr.sts.clt.web;

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

import clikmng.nanet.go.kr.sts.clt.service.CltManageVO;
import clikmng.nanet.go.kr.sts.clt.service.CltStsVO;


public class CltExcel extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{

		String excelName = "수집통계";

		HSSFSheet worksheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		worksheet = workbook.createSheet(excelName+ " WorkSheet");

		
		@SuppressWarnings("unchecked")
		CltManageVO searchVO = (CltManageVO)ModelMap.get("searchVO");
		List<CltStsVO> cltList = (List<CltStsVO>)ModelMap.get("cltList");
		
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
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

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

		String term = searchVO.getSearchTerm();
		
		if("term_sum".equals(term)){			
			// 시트제목
			row = worksheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue(excelName);
			cell.setCellStyle(style);
			for(int i=1; i<=18; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			
			// 테이블 헤더
			//연도,의안,회의록,지방정책정보,의원현황,지역현안소식,교육&매뉴얼,합계
			row = worksheet.createRow(2);
			cell = row.createCell(0);
			cell.setCellValue("구분");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("의안");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("회의록");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("지방정책정보");
			cell.setCellStyle(style);
			for(int i=5; i<=14; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			cell = row.createCell(15);
			cell.setCellValue("의원현황");
			cell.setCellStyle(style);
			cell = row.createCell(16);
			cell.setCellValue("지역현안소식");
			cell.setCellStyle(style);
			cell = row.createCell(17);
			cell.setCellValue("교육&매뉴얼");
			cell.setCellStyle(style);
			cell = row.createCell(18);
			cell.setCellValue("합계");
			cell.setCellStyle(style);


			// 테이블헤더2
			//홍보보도소식,정책/업무자료,연구자료,의정활동자료,통계,정책매뉴얼,출장보고서,세미나/공청회,감사자료,기타,소계
			row = worksheet.createRow(3);
			for(int i=0; i<=3; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			cell = row.createCell(4);
			cell.setCellValue("홍보보도소식");
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue("정책/업무자료");
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("연구자료");
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue("의정활동자료");
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue("통계");
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue("정책매뉴얼");
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue("출장보고서");
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue("세미나/공청회");
			cell.setCellStyle(style);
			cell = row.createCell(12);
			cell.setCellValue("감사자료");
			cell.setCellStyle(style);
			cell = row.createCell(13);
			cell.setCellValue("기타");
			cell.setCellStyle(style);
			cell = row.createCell(14);
			cell.setCellValue("소계");
			cell.setCellStyle(style);
			for(int i=15; i<=18; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			
			// cell 병합
			worksheet.addMergedRegion(new Region(0, (short)0, 0,(short)18));	//상단 제목
			worksheet.addMergedRegion(new Region(2, (short)0, 3, (short)1));	//구분
			worksheet.addMergedRegion(new Region(2, (short)2, 3, (short)2));	//의안
			worksheet.addMergedRegion(new Region(2, (short)3, 3, (short)3));	//회의록
			worksheet.addMergedRegion(new Region(2, (short)4, 2, (short)14));	//지방정책정보
			worksheet.addMergedRegion(new Region(2, (short)15, 3, (short)15));	//의원현황
			worksheet.addMergedRegion(new Region(2, (short)16, 3, (short)16));	//지역현안소식
			worksheet.addMergedRegion(new Region(2, (short)17, 3, (short)17));	//교육&매뉴얼
			worksheet.addMergedRegion(new Region(2, (short)18, 3, (short)18));	//합계
			
			try {
					
				int startRow = 4;
				
				//광역의회
				int sumBill = 0;
				int sumMinutes = 0;
				int sum100 = 0;
				int sum200 = 0;
				int sum300 = 0;
				int sum400 = 0;
				int sum500 = 0;
				int sum600 = 0;
				int sum700 = 0;
				int sum800 = 0;
				int sum900 = 0;
				int sum999 = 0;
				int sumTot_sub = 0;
				int sumAssemblyinfo = 0;
				int sumNews = 0;
				int sum140 = 0;
				int sumTot_cnt = 0;
				
				int sumBill_2 = 0;
        		int sumMinutes_2 = 0;
        		int sum100_2 = 0;
        		int sum200_2 = 0;
        		int sum300_2 = 0;
        		int sum400_2 = 0;
        		int sum500_2 = 0;
        		int sum600_2 = 0;
        		int sum700_2 = 0;
        		int sum800_2 = 0;
        		int sum900_2 = 0;
        		int sum999_2 = 0;
        		int sumTot_sub_2 = 0;
        		int sumAssemblyinfo_2 = 0;
        		int sumNews_2 = 0;
        		int sum140_2 = 0;
        		int sumTot_cnt_2 = 0;
        		
        		int sumBill_3 = 0;
        		int sumMinutes_3 = 0;
        		int sum100_3 = 0;
        		int sum200_3 = 0;
        		int sum300_3 = 0;
        		int sum400_3 = 0;
        		int sum500_3 = 0;
        		int sum600_3 = 0;
        		int sum700_3 = 0;
        		int sum800_3 = 0;
        		int sum900_3 = 0;
        		int sum999_3 = 0;
        		int sumTot_sub_3 = 0;
        		int sumAssemblyinfo_3 = 0;
        		int sumNews_3 = 0;
        		int sum140_3 = 0;
        		int sumTot_cnt_3 = 0;
				
				if(cltList != null){
					int sRow = startRow;
					int eRow = startRow;
					boolean flag1 = true;
					
	                for(int i=0; i<cltList.size(); i++){
	                	CltStsVO clt = cltList.get(i);
	                	
	                	if("WAC".equals(clt.getRasmbly_ty_code())){
	                		
		                	row = worksheet.createRow(startRow);
		                	
		                	if(flag1){
		                		cell = row.createCell(0);
			            		cell.setCellValue("광역의회");
			            		cell.setCellStyle(style);
			            		sRow = startRow;
			            		flag1 = false;
		                	}else{
		                		cell = row.createCell(0);
		                		cell.setCellStyle(style);
		                	}
		                	
		            		cell = row.createCell(1);
		            		cell.setCellValue(clt.getRasmbly_nm());
		            		cell.setCellStyle(style);
		                	
		            		cell = row.createCell(2);
		            		cell.setCellValue(Integer.parseInt(clt.getBill()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(3);
		            		cell.setCellValue(Integer.parseInt(clt.getMinutes()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(4);
		            		cell.setCellValue(Integer.parseInt(clt.get_100()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(5);
		            		cell.setCellValue(Integer.parseInt(clt.get_200()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(6);
		            		cell.setCellValue(Integer.parseInt(clt.get_300()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(7);
		            		cell.setCellValue(Integer.parseInt(clt.get_400()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(8);
		            		cell.setCellValue(Integer.parseInt(clt.get_500()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(9);
		            		cell.setCellValue(Integer.parseInt(clt.get_600()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(10);
		            		cell.setCellValue(Integer.parseInt(clt.get_700()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(11);
		            		cell.setCellValue(Integer.parseInt(clt.get_800()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(12);
		            		cell.setCellValue(Integer.parseInt(clt.get_900()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(13);
		            		cell.setCellValue(Integer.parseInt(clt.get_999()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(14);
		            		cell.setCellValue(Integer.parseInt(clt.getTot_sub()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(15);
		            		cell.setCellValue(Integer.parseInt(clt.getAssemblyinfo()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(16);
		            		cell.setCellValue(Integer.parseInt(clt.getNews()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(17);
		            		cell.setCellValue(Integer.parseInt(clt.get_140()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(18);
		            		cell.setCellValue(Integer.parseInt(clt.getTot_cnt()));
		            		cell.setCellStyle(style_number);
		            		
		            		sumBill += Integer.parseInt(clt.getBill());
		            		sumMinutes += Integer.parseInt(clt.getMinutes());
							sum100 += Integer.parseInt(clt.get_100());
							sum200 += Integer.parseInt(clt.get_200());
							sum300 += Integer.parseInt(clt.get_300());
							sum400 += Integer.parseInt(clt.get_400());
							sum500 += Integer.parseInt(clt.get_500());
							sum600 += Integer.parseInt(clt.get_600());
							sum700 += Integer.parseInt(clt.get_700());
							sum800 += Integer.parseInt(clt.get_800());
							sum900 += Integer.parseInt(clt.get_900());
							sum999 += Integer.parseInt(clt.get_999());
							sumTot_sub += Integer.parseInt(clt.getTot_sub());
							sumAssemblyinfo += Integer.parseInt(clt.getAssemblyinfo());
							sumNews += Integer.parseInt(clt.getNews());
							sum140 += Integer.parseInt(clt.get_140());
							sumTot_cnt += Integer.parseInt(clt.getTot_cnt());
		            		
		            		startRow++;
	                	}
	        		}
	                //광역의회 소계
	                row = worksheet.createRow(startRow);

               		cell = row.createCell(0);
               		cell.setCellStyle(style);
                	
            		cell = row.createCell(1);
            		cell.setCellValue("소계");
            		cell.setCellStyle(style);
                	
            		cell = row.createCell(2);
            		cell.setCellValue(sumBill);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(3);
            		cell.setCellValue(sumMinutes);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(4);
            		cell.setCellValue(sum100);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(5);
            		cell.setCellValue(sum200);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(6);
            		cell.setCellValue(sum300);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(7);
            		cell.setCellValue(sum400);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(8);
            		cell.setCellValue(sum500);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(9);
            		cell.setCellValue(sum600);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(10);
            		cell.setCellValue(sum700);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(11);
            		cell.setCellValue(sum800);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(12);
            		cell.setCellValue(sum900);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(13);
            		cell.setCellValue(sum999);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(14);
            		cell.setCellValue(sumTot_sub);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(15);
            		cell.setCellValue(sumAssemblyinfo);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(16);
            		cell.setCellValue(sumNews);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(17);
            		cell.setCellValue(sum140);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(18);
            		cell.setCellValue(sumTot_cnt);
            		cell.setCellStyle(style_number);
            		
            		eRow = startRow;
            		
            		startRow++;
            		
            		worksheet.addMergedRegion(new Region(sRow, (short)0, eRow, (short)0));
				
				
	        		//기초의회
	            	sRow = startRow;
					eRow = startRow;
					boolean falg2 = true;
					
	                for(int i=0; i<cltList.size(); i++){
	                	CltStsVO clt = cltList.get(i);
	                	
	                	if("BAC".equals(clt.getRasmbly_ty_code())){
	                		
		                	row = worksheet.createRow(startRow);
	
		                	if(falg2){
		                		cell = row.createCell(0);
			            		cell.setCellValue("기초의회");
			            		cell.setCellStyle(style);
			            		
			            		sRow = startRow;
			            		falg2 = false;
		                	}else{
		                		cell = row.createCell(0);
		                		cell.setCellStyle(style);
		                	}
		                	
		            		cell = row.createCell(1);
		            		cell.setCellValue(clt.getRasmbly_nm());
		            		cell.setCellStyle(style);
		                	
		            		cell = row.createCell(2);
		            		cell.setCellValue(Integer.parseInt(clt.getBill()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(3);
		            		cell.setCellValue(Integer.parseInt(clt.getMinutes()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(4);
		            		cell.setCellValue(Integer.parseInt(clt.get_100()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(5);
		            		cell.setCellValue(Integer.parseInt(clt.get_200()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(6);
		            		cell.setCellValue(Integer.parseInt(clt.get_300()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(7);
		            		cell.setCellValue(Integer.parseInt(clt.get_400()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(8);
		            		cell.setCellValue(Integer.parseInt(clt.get_500()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(9);
		            		cell.setCellValue(Integer.parseInt(clt.get_600()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(10);
		            		cell.setCellValue(Integer.parseInt(clt.get_700()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(11);
		            		cell.setCellValue(Integer.parseInt(clt.get_800()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(12);
		            		cell.setCellValue(Integer.parseInt(clt.get_900()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(13);
		            		cell.setCellValue(Integer.parseInt(clt.get_999()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(14);
		            		cell.setCellValue(Integer.parseInt(clt.getTot_sub()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(15);
		            		cell.setCellValue(Integer.parseInt(clt.getAssemblyinfo()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(16);
		            		cell.setCellValue(Integer.parseInt(clt.getNews()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(17);
		            		cell.setCellValue(Integer.parseInt(clt.get_140()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(18);
		            		cell.setCellValue(Integer.parseInt(clt.getTot_cnt()));
		            		cell.setCellStyle(style_number);
		            		
		            		sumBill_2 += Integer.parseInt(clt.getBill());
		            		sumMinutes_2 += Integer.parseInt(clt.getMinutes());
							sum100_2 += Integer.parseInt(clt.get_100());
							sum200_2 += Integer.parseInt(clt.get_200());
							sum300_2 += Integer.parseInt(clt.get_300());
							sum400_2 += Integer.parseInt(clt.get_400());
							sum500_2 += Integer.parseInt(clt.get_500());
							sum600_2 += Integer.parseInt(clt.get_600());
							sum700_2 += Integer.parseInt(clt.get_700());
							sum800_2 += Integer.parseInt(clt.get_800());
							sum900_2 += Integer.parseInt(clt.get_900());
							sum999_2 += Integer.parseInt(clt.get_999());
							sumTot_sub_2 += Integer.parseInt(clt.getTot_sub());
							sumAssemblyinfo_2 += Integer.parseInt(clt.getAssemblyinfo());
							sumNews_2 += Integer.parseInt(clt.getNews());
							sum140_2 += Integer.parseInt(clt.get_140());
							sumTot_cnt_2 += Integer.parseInt(clt.getTot_cnt());
		            		
		            		startRow++;
	                	}
	        		}
	                //기초의회 소계
	                row = worksheet.createRow(startRow);
	
	           		cell = row.createCell(0);
	           		cell.setCellStyle(style);
	            	
	        		cell = row.createCell(1);
	        		cell.setCellValue("소계");
	        		cell.setCellStyle(style);
	            	
	        		cell = row.createCell(2);
	        		cell.setCellValue(sumBill_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(3);
	        		cell.setCellValue(sumMinutes_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(4);
	        		cell.setCellValue(sum100_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(5);
	        		cell.setCellValue(sum200_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(6);
	        		cell.setCellValue(sum300_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(7);
	        		cell.setCellValue(sum400_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(8);
	        		cell.setCellValue(sum500_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(9);
	        		cell.setCellValue(sum600_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(10);
	        		cell.setCellValue(sum700_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(11);
	        		cell.setCellValue(sum800_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(12);
	        		cell.setCellValue(sum900_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(13);
	        		cell.setCellValue(sum999_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(14);
	        		cell.setCellValue(sumTot_sub_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(15);
	        		cell.setCellValue(sumAssemblyinfo_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(16);
	        		cell.setCellValue(sumNews_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(17);
	        		cell.setCellValue(sum140_2);
	        		cell.setCellStyle(style_number);
	        		
	        		cell = row.createCell(18);
	        		cell.setCellValue(sumTot_cnt_2);
	        		cell.setCellStyle(style_number);
	        		
	        		eRow = startRow;
	        		
	        		startRow++;
	        		
	        		worksheet.addMergedRegion(new Region(sRow, (short)0, eRow, (short)0));
            		
                
	        		//정부기관, 연구기관, 기타
	        		sRow = startRow;
	        		eRow = startRow;

	                for(int i=0; i<cltList.size(); i++){
	                	
	                	CltStsVO clt = cltList.get(i);
	                	
	                	if("ETC".equals(clt.getRasmbly_ty_code())){
	                		
		                	row = worksheet.createRow(startRow);
	
	                		cell = row.createCell(0);
	                		cell.setCellValue(clt.getRasmbly_nm());
		            		cell.setCellStyle(style);
		            		worksheet.addMergedRegion(new Region(startRow, (short)0, startRow, (short)1));
		                	
		            		cell = row.createCell(1);
		            		cell.setCellStyle(style);
		                	
		            		cell = row.createCell(2);
		            		cell.setCellValue(Integer.parseInt(clt.getBill()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(3);
		            		cell.setCellValue(Integer.parseInt(clt.getMinutes()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(4);
		            		cell.setCellValue(Integer.parseInt(clt.get_100()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(5);
		            		cell.setCellValue(Integer.parseInt(clt.get_200()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(6);
		            		cell.setCellValue(Integer.parseInt(clt.get_300()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(7);
		            		cell.setCellValue(Integer.parseInt(clt.get_400()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(8);
		            		cell.setCellValue(Integer.parseInt(clt.get_500()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(9);
		            		cell.setCellValue(Integer.parseInt(clt.get_600()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(10);
		            		cell.setCellValue(Integer.parseInt(clt.get_700()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(11);
		            		cell.setCellValue(Integer.parseInt(clt.get_800()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(12);
		            		cell.setCellValue(Integer.parseInt(clt.get_900()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(13);
		            		cell.setCellValue(Integer.parseInt(clt.get_999()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(14);
		            		cell.setCellValue(Integer.parseInt(clt.getTot_sub()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(15);
		            		cell.setCellValue(Integer.parseInt(clt.getAssemblyinfo()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(16);
		            		cell.setCellValue(Integer.parseInt(clt.getNews()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(17);
		            		cell.setCellValue(Integer.parseInt(clt.get_140()));
		            		cell.setCellStyle(style_number);
		            		
		            		cell = row.createCell(18);
		            		cell.setCellValue(Integer.parseInt(clt.getTot_cnt()));
		            		cell.setCellStyle(style_number);
		            		
		            		sumBill_3 += Integer.parseInt(clt.getBill());
		            		sumMinutes_3 += Integer.parseInt(clt.getMinutes());
							sum100_3 += Integer.parseInt(clt.get_100());
							sum200_3 += Integer.parseInt(clt.get_200());
							sum300_3 += Integer.parseInt(clt.get_300());
							sum400_3 += Integer.parseInt(clt.get_400());
							sum500_3 += Integer.parseInt(clt.get_500());
							sum600_3 += Integer.parseInt(clt.get_600());
							sum700_3 += Integer.parseInt(clt.get_700());
							sum800_3 += Integer.parseInt(clt.get_800());
							sum900_3 += Integer.parseInt(clt.get_900());
							sum999_3 += Integer.parseInt(clt.get_999());
							sumTot_sub_3 += Integer.parseInt(clt.getTot_sub());
							sumAssemblyinfo_3 += Integer.parseInt(clt.getAssemblyinfo());
							sumNews_3 += Integer.parseInt(clt.getNews());
							sum140_3 += Integer.parseInt(clt.get_140());
							sumTot_cnt_3 += Integer.parseInt(clt.getTot_cnt());
							
		            		startRow++;
	                	}
	        		}
        		}
        		
                //합계
                row = worksheet.createRow(startRow);

        		cell = row.createCell(0);
        		cell.setCellValue("합계");
        		cell.setCellStyle(style);
        		worksheet.addMergedRegion(new Region(startRow, (short)0, startRow, (short)1));
            	
        		cell = row.createCell(1);
        		cell.setCellStyle(style);
            	
        		cell = row.createCell(2);
        		cell.setCellValue(sumBill + sumBill_2 + sumBill_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(3);
        		cell.setCellValue(sumMinutes + sumMinutes_2 + sumMinutes_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(4);
        		cell.setCellValue(sum100 + sum100_2 + sum100_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(5);
        		cell.setCellValue(sum200 + sum200_2 + sum200_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(6);
        		cell.setCellValue(sum300 + sum300_2 + sum300_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(7);
        		cell.setCellValue(sum400 + sum400_2 + sum400_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(8);
        		cell.setCellValue(sum500 + sum500_2 + sum500_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(9);
        		cell.setCellValue(sum600 + sum600_2 + sum600_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(10);
        		cell.setCellValue(sum700 + sum700_2 + sum700_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(11);
        		cell.setCellValue(sum800 + sum800_2 + sum800_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(12);
        		cell.setCellValue(sum900 + sum900_2 + sum900_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(13);
        		cell.setCellValue(sum999 + sum999_2 + sum999_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(14);
        		cell.setCellValue(sumTot_sub + sumTot_sub_2 + sumTot_sub_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(15);
        		cell.setCellValue(sumAssemblyinfo + sumAssemblyinfo_2 + sumAssemblyinfo_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(16);
        		cell.setCellValue(sumNews + sumNews_2 + sumNews_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(17);
        		cell.setCellValue(sum140 + sum140_2 + sum140_3);
        		cell.setCellStyle(style_number);
        		
        		cell = row.createCell(18);
        		cell.setCellValue(sumTot_cnt + sumTot_cnt_2 + sumTot_cnt_3);
        		cell.setCellStyle(style_number);
        		
        		startRow++;
				
			} catch (Exception e) {
				System.out.println(":::::: CLTEXCEL ERROR OCCURED!!! ::::::  >>> " + e.getStackTrace());
				e.getMessage();
			}
			
		}else{
			// 시트제목
			row = worksheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue(excelName);
			cell.setCellStyle(style);
			for(int i=1; i<=17; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			
			// 테이블 헤더
			//연도,의안,회의록,지방정책정보,의원현황,지역현안소식,교육&매뉴얼,합계
			row = worksheet.createRow(2);
			cell = row.createCell(0);
			cell.setCellValue("연도");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("의안");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue("회의록");
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("지방정책정보");
			cell.setCellStyle(style);
			for(int i=4; i<=13; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			cell = row.createCell(14);
			cell.setCellValue("의원현황");
			cell.setCellStyle(style);
			cell = row.createCell(15);
			cell.setCellValue("지역현안소식");
			cell.setCellStyle(style);
			cell = row.createCell(16);
			cell.setCellValue("교육&매뉴얼");
			cell.setCellStyle(style);
			cell = row.createCell(17);
			cell.setCellValue("합계");
			cell.setCellStyle(style);


			// 테이블헤더2
			//홍보보도소식,정책/업무자료,연구자료,의정활동자료,통계,정책매뉴얼,출장보고서,세미나/공청회,감사자료,기타,소계
			row = worksheet.createRow(3);
			for(int i=0; i<=2; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			cell = row.createCell(3);
			cell.setCellValue("홍보보도소식");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("정책/업무자료");
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue("연구자료");
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("의정활동자료");
			cell.setCellStyle(style);
			cell = row.createCell(7);
			cell.setCellValue("통계");
			cell.setCellStyle(style);
			cell = row.createCell(8);
			cell.setCellValue("정책매뉴얼");
			cell.setCellStyle(style);
			cell = row.createCell(9);
			cell.setCellValue("출장보고서");
			cell.setCellStyle(style);
			cell = row.createCell(10);
			cell.setCellValue("세미나/공청회");
			cell.setCellStyle(style);
			cell = row.createCell(11);
			cell.setCellValue("감사자료");
			cell.setCellStyle(style);
			cell = row.createCell(12);
			cell.setCellValue("기타");
			cell.setCellStyle(style);
			cell = row.createCell(13);
			cell.setCellValue("소계");
			cell.setCellStyle(style);
			for(int i=14; i<=17; i++){
				cell = row.createCell(i);
				cell.setCellStyle(style);
			}
			
			// cell 병합
			worksheet.addMergedRegion(new Region(0, (short)0, 0,(short)17));	//상단 제목
			worksheet.addMergedRegion(new Region(2, (short)0, 3, (short)0));	//연도
			worksheet.addMergedRegion(new Region(2, (short)1, 3, (short)1));	//의안
			worksheet.addMergedRegion(new Region(2, (short)2, 3, (short)2));	//회의록
			worksheet.addMergedRegion(new Region(2, (short)3, 2, (short)13));	//지방정책정보
			worksheet.addMergedRegion(new Region(2, (short)14, 3, (short)14));	//의원현황
			worksheet.addMergedRegion(new Region(2, (short)15, 3, (short)15));	//지역현안소식
			worksheet.addMergedRegion(new Region(2, (short)16, 3, (short)16));	//교육&매뉴얼
			worksheet.addMergedRegion(new Region(2, (short)17, 3, (short)17));	//합계

			try {
				if (cltList != null){
					
					int startRow = 4;
					
					int sumBill = 0;
					int sumMinutes = 0;
					int sum100 = 0;
					int sum200 = 0;
					int sum300 = 0;
					int sum400 = 0;
					int sum500 = 0;
					int sum600 = 0;
					int sum700 = 0;
					int sum800 = 0;
					int sum900 = 0;
					int sum999 = 0;
					int sumTot_sub = 0;
					int sumAssemblyinfo = 0;
					int sumNews = 0;
					int sum140 = 0;
					int sumTot_cnt = 0;
	                for(int i=0; i<cltList.size(); i++){
	                	row = worksheet.createRow(startRow);
	                	
	                	CltStsVO clt = cltList.get(i);
	                	
	            		cell = row.createCell(0);
	            		cell.setCellValue(clt.getTerm());
	            		cell.setCellStyle(style_number);
	                	
	            		cell = row.createCell(1);
	            		cell.setCellValue(Integer.parseInt(clt.getBill()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(2);
	            		cell.setCellValue(Integer.parseInt(clt.getMinutes()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(3);
	            		cell.setCellValue(Integer.parseInt(clt.get_100()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(4);
	            		cell.setCellValue(Integer.parseInt(clt.get_200()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(5);
	            		cell.setCellValue(Integer.parseInt(clt.get_300()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(6);
	            		cell.setCellValue(Integer.parseInt(clt.get_400()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(7);
	            		cell.setCellValue(Integer.parseInt(clt.get_500()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(8);
	            		cell.setCellValue(Integer.parseInt(clt.get_600()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(9);
	            		cell.setCellValue(Integer.parseInt(clt.get_700()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(10);
	            		cell.setCellValue(Integer.parseInt(clt.get_800()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(11);
	            		cell.setCellValue(Integer.parseInt(clt.get_900()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(12);
	            		cell.setCellValue(Integer.parseInt(clt.get_999()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(13);
	            		cell.setCellValue(Integer.parseInt(clt.getTot_sub()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(14);
	            		cell.setCellValue(Integer.parseInt(clt.getAssemblyinfo()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(15);
	            		cell.setCellValue(Integer.parseInt(clt.getNews()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(16);
	            		cell.setCellValue(Integer.parseInt(clt.get_140()));
	            		cell.setCellStyle(style_number);
	            		
	            		cell = row.createCell(17);
	            		cell.setCellValue(Integer.parseInt(clt.getTot_cnt()));
	            		cell.setCellStyle(style_number);
	            		
	            		sumBill += Integer.parseInt(clt.getBill());
	            		sumMinutes += Integer.parseInt(clt.getMinutes());
						sum100 += Integer.parseInt(clt.get_100());
						sum200 += Integer.parseInt(clt.get_200());
						sum300 += Integer.parseInt(clt.get_300());
						sum400 += Integer.parseInt(clt.get_400());
						sum500 += Integer.parseInt(clt.get_500());
						sum600 += Integer.parseInt(clt.get_600());
						sum700 += Integer.parseInt(clt.get_700());
						sum800 += Integer.parseInt(clt.get_800());
						sum900 += Integer.parseInt(clt.get_900());
						sum999 += Integer.parseInt(clt.get_999());
						sumTot_sub += Integer.parseInt(clt.getTot_sub());
						sumAssemblyinfo += Integer.parseInt(clt.getAssemblyinfo());
						sumNews += Integer.parseInt(clt.getNews());
						sum140 += Integer.parseInt(clt.get_140());
						sumTot_cnt += Integer.parseInt(clt.getTot_cnt());
	            		
	            		startRow++;
	        		}
	                
	                //합계
	                row = worksheet.createRow(startRow);

            		cell = row.createCell(0);
            		cell.setCellValue("합계");
            		cell.setCellStyle(style);
                	
            		cell = row.createCell(1);
            		cell.setCellValue(sumBill);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(2);
            		cell.setCellValue(sumMinutes);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(3);
            		cell.setCellValue(sum100);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(4);
            		cell.setCellValue(sum200);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(5);
            		cell.setCellValue(sum300);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(6);
            		cell.setCellValue(sum400);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(7);
            		cell.setCellValue(sum500);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(8);
            		cell.setCellValue(sum600);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(9);
            		cell.setCellValue(sum700);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(10);
            		cell.setCellValue(sum800);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(11);
            		cell.setCellValue(sum900);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(12);
            		cell.setCellValue(sum999);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(13);
            		cell.setCellValue(sumTot_sub);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(14);
            		cell.setCellValue(sumAssemblyinfo);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(15);
            		cell.setCellValue(sumNews);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(16);
            		cell.setCellValue(sum140);
            		cell.setCellStyle(style_number);
            		
            		cell = row.createCell(17);
            		cell.setCellValue(sumTot_cnt);
            		cell.setCellStyle(style_number);
            		
            		startRow++;
	            }        
			} catch (Exception e) {
				System.out.println(":::::: CLTEXCEL ERROR OCCURED!!! ::::::  >>> " + e.getStackTrace());
				e.getMessage();
			} 
		}
		
		worksheet.setColumnWidth(0, 5000);
		worksheet.setColumnWidth(1, "term_sum".equals(searchVO.getSearchTerm()) ? 5000 : 3000);
		worksheet.setColumnWidth(2, 3000);
		worksheet.setColumnWidth(3, 3000);
		worksheet.setColumnWidth(4, 3000);
		worksheet.setColumnWidth(5, 3000);
		worksheet.setColumnWidth(6, 3000);
		worksheet.setColumnWidth(7, 3000);
		worksheet.setColumnWidth(8, 3000);
		worksheet.setColumnWidth(9, 3000);
		worksheet.setColumnWidth(10, 3000);
		worksheet.setColumnWidth(11, 3000);
		worksheet.setColumnWidth(12, 3000);
		worksheet.setColumnWidth(13, 3000);
		worksheet.setColumnWidth(14, 3000);
		worksheet.setColumnWidth(15, 3000);
		worksheet.setColumnWidth(16, 3000);
		worksheet.setColumnWidth(17, 3000);
		if("term_sum".equals(searchVO.getSearchTerm()))
			worksheet.setColumnWidth(18, 3000);

		
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