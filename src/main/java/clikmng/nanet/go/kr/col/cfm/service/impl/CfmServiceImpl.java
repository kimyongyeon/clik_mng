package clikmng.nanet.go.kr.col.cfm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.col.cfm.service.CfmCompareListVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmCompareResultVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmFileVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmSearchVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 표준연계API 파일동기화를 처리하는 비즈니스 구현 클래스
 * */
@Service("CfmService")
public class CfmServiceImpl extends AbstractServiceImpl implements CfmService {

	@Resource(name = "CfmDAO")
	private CfmDAO cfmDAO;

	
    /**
     * 임시수집파일리스트 테이블에 관리자에 의해 등록된 파일리스트 삭제
     * @param vo
     * @throws Exception
     */
	@Override
	public void deleteTempCollectionFileList(String mngrID) throws Exception {
		
		cfmDAO.deleteTempCollectionFileList(mngrID);
		
	}
	
    /**
     * 수집파일비교결과 테이블에 관리자에 의해 등록된 파일리스트 삭제
     * @param vo
     * @throws Exception
     */
	@Override
	public void deleteCollectionFileList(String mngrID) throws Exception {
		
		cfmDAO.deleteCollectionFileList(mngrID);
		
	}
	
    /**
     * TEMP테이블에 파일리스트 저장
     * @param vo
     * @throws Exception
     */
	@Override
	public void insertTempCollectionFileList(CfmFileVO vo) throws Exception {
		// TODO Auto-generated method stub
		cfmDAO.insertTempCollectionFileList(vo);
		
	}
	
    /**
     * 표준연계API 파일동기화 이력로그
     * @param vo
     * @throws Exception
     */ 
	@Override
	public void insertCompareLog(CfmSearchVO vo) throws Exception {
		// TODO Auto-generated method stub
		cfmDAO.insertCompareLog(vo);
	}
	
	/**
	 * 표준연계API 파일동기화 관련 로그 및 파일비교실행
	 * 
	 * @param vo
	 * @return 파일비교결과카운트
	 * @throws Exception
	 */
	@Override
	public void compareCollectionFile(CfmSearchVO vo)
			throws Exception {
		// TODO Auto-generated method stub
		cfmDAO.compareCollectionFile(vo);
	}

	
    /**
     * 표준연계API 파일동기화 파일 비교 결과 조회
     * @param vo
     * @return	파일비교결과
     * @throws Exception
     */	
	@Override
	public List<CfmCompareResultVO> compareResult(String compare_id) throws Exception {
		// TODO Auto-generated method stub
		return cfmDAO.compareResult(compare_id);
	}

    /**
     * 표준연계API 파일동기화 파일 비교 리스트 조회
     * @param vo
     * @return	파일비교리스트
     * @throws Exception
     */		
	@Override
	public List<CfmCompareListVO> selectCompareList(HashMap<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		return cfmDAO.selectCompareList(map);
	}

	
	
    /**
     * 표준연계API 파일동기화 파일 비교 리스트에서 파일 삭제
     * @param vo
     * @return	파일삭제카운트
     * @throws Exception
     */	
	@Override
	public void deleteCompareListFile(String[] checkedFilePath)
			throws Exception {
		// TODO Auto-generated method stub
		cfmDAO.deleteCompareListFile(checkedFilePath);
		
	}


	
	

}
