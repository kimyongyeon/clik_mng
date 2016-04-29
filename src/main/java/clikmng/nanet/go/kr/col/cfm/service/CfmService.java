package clikmng.nanet.go.kr.col.cfm.service;

import java.util.HashMap;
import java.util.List;


/**
 * 
 * 표준연계API 파일동기화를 처리하는 서비스 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */
public interface CfmService {
	
	public void deleteTempCollectionFileList(String mngID) throws Exception;
	
	public void deleteCollectionFileList(String mngID) throws Exception;
	
	public void insertTempCollectionFileList(CfmFileVO vo) throws Exception;
	
	public void insertCompareLog(CfmSearchVO vo) throws Exception;
	
	public void compareCollectionFile(CfmSearchVO vo) throws Exception;
	
	public List<CfmCompareResultVO> compareResult(String compare_id) throws Exception;
	
	public List<CfmCompareListVO> selectCompareList(HashMap<String, String> map) throws Exception;
	
	public void deleteCompareListFile(String[] checkedFilePath) throws Exception;
	
}
