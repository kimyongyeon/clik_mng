package clikmng.nanet.go.kr.mdm.utl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import clikmng.nanet.go.kr.mdm.service.MdmProperties;

/**
 * 메타데이터 엑셀 관련 정보를 처리하는 클래스
 */
public class MdmExcelUtil {

	 /**
     * 엑셀파일 A열에 정보를 반환해준다.
     * @param MultipartHttpServletRequest multiRequest
     * @return	A열에 문자열
     * @return 'CLIKA20150000329742','CLIKA20150000329743','CLIKA20150000329744'......
     * @throws Exception
     */
	public static String getOutbbsCn(MultipartHttpServletRequest multiRequest) throws IllegalStateException, IOException{
		
		String outbbsList = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		int maxSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
		int currentRowCount = 0;
		
		if (!files.isEmpty()) {
			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			while(itr.hasNext()){
				Entry<String, MultipartFile> entry = itr.next();
				MultipartFile mFile = entry.getValue();
				String fileExt = mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".") + 1);
				String fileName = "/clik-web/clik-mgr.ear/clik-mgr.war/" /*+ File.separator*/ + "excelSearchTemp" + System.currentTimeMillis() + "." + fileExt;
				File tempFile = new File(fileName);
				mFile.transferTo(tempFile);
				
				if(!mFile.isEmpty())
				{
					try
					{
						if("xls".equalsIgnoreCase(fileExt)){
							//97버전
							InputStream ExcelFileToRead = new FileInputStream(fileName);
							HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
					 
							HSSFSheet sheet=wb.getSheetAt(0);
							HSSFRow row; 
							HSSFCell cell;
					 
							Iterator<Row> rows = sheet.rowIterator();
					 
							while (rows.hasNext())
							{
								row=(HSSFRow) rows.next();
								Iterator<Cell> cells = row.cellIterator();
								
								while (cells.hasNext() && maxSearchCount > currentRowCount)
								{
									cell=(HSSFCell) cells.next();
							
									if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
									{
										outbbsList += "'" + cell.getStringCellValue() + "',";
									}
									currentRowCount++;
								}
							}
						}else if("xlsx".equalsIgnoreCase(fileExt)){
							//통합문서
							InputStream ExcelFileToRead = new FileInputStream(fileName);
							XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
							
							XSSFSheet sheet = wb.getSheetAt(0);
							XSSFRow row; 
							XSSFCell cell;
					 
							Iterator<Row> rows = sheet.rowIterator();
					 
							while (rows.hasNext())
							{
								row=(XSSFRow) rows.next();
								Iterator<Cell> cells = row.cellIterator();
								while (cells.hasNext() && maxSearchCount > currentRowCount)
								{
									cell=(XSSFCell) cells.next();
							
									if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
									{
										outbbsList += "'" + cell.getStringCellValue() + "',";
									}
									currentRowCount++;
								}
							}
						}
						
						outbbsList = outbbsList.substring(0, outbbsList.length()-1);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}finally{
						if(tempFile.exists())
							tempFile.delete();
					}
				}
			}//end while
		}//end if

		return outbbsList;
		
	}
	
}
