package clikmng.nanet.go.kr.sts.stm.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
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

public class GrpDusExcel extends AbstractExcelView{

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String,Object> ModelMap
								,	HSSFWorkbook workbook
								,	HttpServletRequest request
								, 	HttpServletResponse response) throws Exception{

		 String excelName = "이용자그룹별자료이용통계";

		 HSSFSheet worksheet = null;
		 HSSFRow row = null;
		 HSSFCell cell = null;

		 worksheet = workbook.createSheet(excelName+ " WorkSheet");

		UseLogSummaryVO searchVO = (UseLogSummaryVO)ModelMap.get("searchVO");
		List<EgovMap> list = (List<EgovMap>)ModelMap.get("categoryList");
		HashMap<String, List<EgovMap>> itemMap = new HashMap<String, List<EgovMap>>();
		itemMap =  (HashMap<String, List<EgovMap>>)ModelMap.get("detailList");
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
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(0,(short)0,0,(short)9));// cell 병합        	


		// 테이블 헤더
		row = worksheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("구분");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)0,2,(short)1));// cell 병합

		// 테이블 헤더
		cell  = row.createCell(2);
		cell.setCellValue("광역의회");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)2,2,(short)4));
		
		// 테이블 헤더
		cell  = row.createCell(5);
		cell.setCellValue("기초의회");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellStyle(style);
		worksheet.addMergedRegion(new Region(2,(short)5,2,(short)7));
		
		// 테이블 헤더
		cell  = row.createCell(8);
		cell.setCellValue("국회");
		cell.setCellStyle(style);
		
		// 테이블 헤더
		cell  = row.createCell(9);
		cell.setCellValue("일반");
		cell.setCellStyle(style);
		
		// 테이블헤더2
		row = worksheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("카테고리");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("자료유형");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("직원");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("관리자");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("의원");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("직원");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("관리자");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("의원");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("");
		cell.setCellStyle(style);

		int i = 4;
		
		Iterator<UseLogSummaryVO> it = null;
		UseLogSummaryVO vo = null;
		boolean isCategoryData = false;
		int[] valueSum = new int[8];
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
	                
	                isCategoryData = false;
	                it = dusList.iterator();
	                while(it.hasNext()){
	                	vo = it.next();
	                	
	                	if(vo.getRasmblyDtaSeCode() != null && vo.getRasmblyDtaSeCode().equals((String)detail.get("code"))){
	                		
	                		System.out.println("======== " + detail.get("code"));
	                		System.out.println("valueSum[0] : " + valueSum[0]);
	                		System.out.println("valueSum[1] : " + valueSum[1]);
	                		System.out.println("valueSum[2] : " + valueSum[2]);
	                		System.out.println("valueSum[3] : " + valueSum[3]);
	                		System.out.println("valueSum[4] : " + valueSum[4]);
	                		System.out.println("valueSum[5] : " + valueSum[5]);
	                		System.out.println("valueSum[6] : " + valueSum[6]);
	                		System.out.println("valueSum[7] : " + valueSum[7]);
	                		System.out.println("");
	                		System.out.println("");
	                		
	                		isCategoryData = true;
	                		cell=row.createCell(2);
	    	        		cell.setCellValue(vo.getWAC001());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[0] += Integer.parseInt(vo.getWAC001());
	    	        		
	    	        		cell=row.createCell(3);
	    	        		cell.setCellValue(vo.getWAC002());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[1] += Integer.parseInt(vo.getWAC002());
	    	        		
	    	        		cell=row.createCell(4);
	    	        		cell.setCellValue(vo.getWAC003());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[2] += Integer.parseInt(vo.getWAC003());
	    	        		
	    	        		cell=row.createCell(5);
	    	        		cell.setCellValue(vo.getBAC001());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[3] += Integer.parseInt(vo.getBAC001());
	    	        		
	    	        		cell=row.createCell(6);
	    	        		cell.setCellValue(vo.getBAC002());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[4] += Integer.parseInt(vo.getBAC002());
	    	        		
	    	        		cell=row.createCell(7);
	    	        		cell.setCellValue(vo.getBAC003());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[5] += Integer.parseInt(vo.getBAC003());
	    	        		
	    	        		cell=row.createCell(8);
	    	        		cell.setCellValue(vo.getASM());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[6] += Integer.parseInt(vo.getASM());
	    	        		
	    	        		cell=row.createCell(9);
	    	        		cell.setCellValue(vo.getNOR());
	    	        		cell.setCellStyle(style_number);
	    	        		valueSum[7] += Integer.parseInt(vo.getNOR());
	                	}
	                }
	                
	                //해당 카테고리 정보가 없을 경우 카운트 정보를 모두 0으로 설정
	                if(!isCategoryData){
	                	cell=row.createCell(2);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	            	
    	        		cell=row.createCell(3);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	        		
    	        		cell=row.createCell(4);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	        		
    	        		cell=row.createCell(5);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	        		
    	        		cell=row.createCell(6);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	        		
    	        		cell=row.createCell(7);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	        		
    	        		cell=row.createCell(8);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
    	        		
    	        		cell=row.createCell(9);
    	        		cell.setCellValue(0);
    	        		cell.setCellStyle(style_number);
	                }

	                i++;
	        	}
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
    		cell.setCellValue(valueSum[0]);
    		cell.setCellStyle(style_number);
        	
    		cell=row.createCell(3);
    		cell.setCellValue(valueSum[1]);
    		cell.setCellStyle(style_number);
    		
    		cell=row.createCell(4);
    		cell.setCellValue(valueSum[2]);
    		cell.setCellStyle(style_number);
    		
    		cell=row.createCell(5);
    		cell.setCellValue(valueSum[3]);
    		cell.setCellStyle(style_number);
    		
    		cell=row.createCell(6);
    		cell.setCellValue(valueSum[4]);
    		cell.setCellStyle(style_number);
    		
    		cell=row.createCell(7);
    		cell.setCellValue(valueSum[5]);
    		cell.setCellStyle(style_number);
    		
    		cell=row.createCell(8);
    		cell.setCellValue(valueSum[6]);
    		cell.setCellStyle(style_number);
    		
    		cell=row.createCell(9);
    		cell.setCellValue(valueSum[7]);
    		cell.setCellStyle(style_number);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(":::::: GRPDUSEXCEL ERROR OCCURED!!! ::::::  >>> " + e.getStackTrace());
			e.getMessage();
		}
		

    		
		worksheet.setColumnWidth(0,10000);
		worksheet.setColumnWidth(1,10000);
		worksheet.setColumnWidth(2,3000);
		worksheet.setColumnWidth(3,3000);
		worksheet.setColumnWidth(4,3000);
		worksheet.setColumnWidth(5,3000);
		worksheet.setColumnWidth(6,3000);
		worksheet.setColumnWidth(7,3000);
		worksheet.setColumnWidth(8,3000);
		worksheet.setColumnWidth(9,3000);
		
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