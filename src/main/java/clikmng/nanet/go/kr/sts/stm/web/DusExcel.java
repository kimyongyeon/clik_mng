package clikmng.nanet.go.kr.sts.stm.web;

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

public class DusExcel extends AbstractExcelView{

	@SuppressWarnings("deprecation")
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{

		 String excelName = "의회별자료이용통계";

		 HSSFSheet worksheet = null;
		 HSSFRow row = null;
		 HSSFCell cell = null;

		 worksheet = workbook.createSheet(excelName+ " WorkSheet");

		UseLogSummaryVO searchVO = (UseLogSummaryVO)ModelMap.get("searchVO");
		List<EgovMap> list = (List<EgovMap>)ModelMap.get("categoryList");
		HashMap<String, List> itemMap = new HashMap();
		itemMap =  (HashMap<String, List>)ModelMap.get("detailList");
		List<UseLogSummaryVO> dusList = (List<UseLogSummaryVO>)ModelMap.get("dusList");
		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		cmmCodeVO.setCodeClCd("RDC");

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

		// cell 병합
		worksheet.addMergedRegion(new Region(0,(short)0,0,(short)4));        	

		// 테이블 헤더
		row = worksheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("구분");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellStyle(style);

		// cell 병합
		worksheet.addMergedRegion(new Region(2,(short)0,2,(short)1));
		cell  = row.createCell(2);
		cell.setCellValue("이용 현황(건수)");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)2,2,(short)4));

		// 테이블헤더2
		row = worksheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("카테고리");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("자료유형");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("상세보기");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("원문보기");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("다운로드");
		cell.setCellStyle(style);

		int i = 4;
		int tmp = 4;
		int tmp2 = 0;
		
		int dv_sum = 0;
		int ov_sum = 0;
		int dl_sum = 0;
		
		try {
			for(EgovMap category : list)
			{
				String CodeId = (String)category.get("codeId");
				String CodeIdNm = (String)category.get("codeIdNm");
				List<EgovMap> Detaillist = itemMap.get(CodeId);

	    		for(int k=0; k<Detaillist.size(); k++)
	    		{

	    			EgovMap detail = Detaillist.get(k);
	    			
	                row = worksheet.createRow(i);
	                cell = row.createCell(0);
	                if ( k == 0 ) cell.setCellValue(CodeIdNm);
	                else cell.setCellValue("");
	                cell.setCellStyle(style);
	                
	                cell = row.createCell(1);
	                cell.setCellValue((String)detail.get("codeNm"));
	                cell.setCellStyle(style);
	                
	                String lineCode = (String)detail.get("code");

	                // 해당 ROW일 때 2,3,4열을 0으로 셋팅
	            	cell=row.createCell(2);
	        		cell.setCellValue(0);
	        		cell.setCellStyle(style_number);
	            	
	        		cell=row.createCell(3);
	        		cell.setCellValue(0);
	        		cell.setCellStyle(style_number);
	        		
	        		cell=row.createCell(4);
	        		cell.setCellValue(0);
	        		cell.setCellStyle(style_number);
	                
	                if (dusList.size() > 0)
	                {
	                	
		                for(int j=0; j<dusList.size(); j++)
		        		{
		                	UseLogSummaryVO dus = dusList.get(j);

		                	int cnt = (dus.getUseCo() == null ? 0 : Integer.parseInt(dus.getUseCo()));
		                	
		                	if(dus.getLogSeCode() != null && dus.getRasmblyDtaSeCode() != null)
		                	{

			                	if ( "DV".equals(dus.getLogSeCode()) 
			                			&& dus.getRasmblyDtaSeCode().equals(lineCode) ) {
			                		cell=row.createCell(2);
			                		cell.setCellValue( cnt );
			                		cell.setCellStyle(style_number);
			                		dv_sum += cnt;
			                	} 
			                	if ( "OV".equals(dus.getLogSeCode()) 
			                			&& dus.getRasmblyDtaSeCode().equals(lineCode) ) {
			                		cell = row.createCell(3);
			                		cell.setCellValue( cnt );
			                		cell.setCellStyle(style_number);
			                		ov_sum += cnt;
			                	} 
			                	if ( "DL".equals(dus.getLogSeCode()) 
			                			&& dus.getRasmblyDtaSeCode().equals(lineCode) ) { 
			                		cell = row.createCell(4);
			                		cell.setCellValue( cnt );        		
			                		cell.setCellStyle(style_number);
			                		dl_sum += cnt;
			            		} 
			                	

		                	}
		        		}
	                }

	                i++;
	                tmp2 = k;
	        	}
	    		
	    		//합계
				// 테이블 헤더
				row = worksheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue("합계");
				cell.setCellStyle(style);
				cell = row.createCell(1);
				cell.setCellStyle(style);
				
				cell=row.createCell(2);
	    		cell.setCellValue(dv_sum);
	    		cell.setCellStyle(style_number);
	        	
	    		cell=row.createCell(3);
	    		cell.setCellValue(ov_sum);
	    		cell.setCellStyle(style_number);
	    		
	    		cell=row.createCell(4);
	    		cell.setCellValue(dl_sum);
	    		cell.setCellStyle(style_number);
	    		
	    		System.out.println("-------------------------------------------------------------------------->" + tmp + "," + i);
	    		worksheet.addMergedRegion(new Region(tmp,(short)0,(i-1),(short)0));
	    		tmp = i;
	    		
			}        
		} catch (Exception e) {
			System.out.println(":::::: DUSEXCEL ERROR OCCURED!!! ::::::  >>> " + e.getStackTrace());
			e.getMessage();
		}
		

    		
		worksheet.setColumnWidth(0,10000);
		worksheet.setColumnWidth(1,10000);
		worksheet.setColumnWidth(2,3000);
		worksheet.setColumnWidth(3,3000);
		worksheet.setColumnWidth(4,3000);
		
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