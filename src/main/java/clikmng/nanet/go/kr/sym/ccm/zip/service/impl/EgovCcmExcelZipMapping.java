package clikmng.nanet.go.kr.sym.ccm.zip.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import clikmng.nanet.go.kr.sym.ccm.zip.service.Zip;
import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

/**
 * 
 * Excel 우편번호 매핑 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public class EgovCcmExcelZipMapping extends EgovExcelMapping {

	/**
	 * 우편번호 엑셀파일 맵핑
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Object mappingColumn(HSSFRow row) {
		HSSFCell cell0 = row.getCell((int) 0);
    	HSSFCell cell1 = row.getCell((int) 1);
    	HSSFCell cell2 = row.getCell((int) 2);
    	HSSFCell cell3 = row.getCell((int) 3);
    	HSSFCell cell4 = row.getCell((int) 4);
    	HSSFCell cell5 = row.getCell((int) 5);
    	HSSFCell cell6 = row.getCell((int) 6);
    	HSSFCell cell7 = row.getCell((int) 7);

		Zip vo = new Zip();

		vo.setZip            (EgovExcelUtil.getValue(cell0));
		vo.setSn             (Integer.parseInt(EgovExcelUtil.getValue(cell1)));
		vo.setCtprvnNm       (EgovExcelUtil.getValue(cell2));
		vo.setSignguNm       (EgovExcelUtil.getValue(cell3));
		vo.setEmdNm          (EgovExcelUtil.getValue(cell4));
		vo.setFrstRegisterId (EgovExcelUtil.getValue(cell7));

		if (cell5 != null) {vo.setLiBuldNm   (EgovExcelUtil.getValue(cell5));}
		if (cell6 != null) {vo.setLnbrDongHo (EgovExcelUtil.getValue(cell6));}
		
		
		return vo;
	}
}
