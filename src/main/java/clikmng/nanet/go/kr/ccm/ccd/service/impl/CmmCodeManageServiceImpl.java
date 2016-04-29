package clikmng.nanet.go.kr.ccm.ccd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmClCode;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCode;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * 
 * 공통코드에 대한 서비스 구현클래스를 정의한다
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
 *
 * </pre>
 */
@Service("CmmCodeManageService")
public class CmmCodeManageServiceImpl extends AbstractServiceImpl implements CmmCodeManageService {

    @Resource(name="CmmCodeManageDAO")
    private CmmCodeManageDAO cmmCodeManageDAO;
   
	
	/**
	 * 공통분류코드를 등록한다.
	 */
	public void insertCmmClCode(CmmClCode cmmClCode) throws Exception {
		cmmCodeManageDAO.insertCmmClCode(cmmClCode);    	
	}
	
	/**
	 * 공통코드를 등록한다.
	 */
	public void insertCmmCode(CmmCode cmmCode) throws Exception {
    	cmmCodeManageDAO.insertCmmCode(cmmCode);    	
	}
	
	/**
	 * 공통상세코드를 등록한다.
	 */
	public void insertCmmDetailCode(CmmnDetailCode cmmCode) throws Exception {
    	cmmCodeManageDAO.insertCmmDetailCode(cmmCode);    	
	}	

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 */
	public CmmCodeVO selectCmmClCode(CmmCodeVO cmmCodeVO) throws Exception {
		CmmCodeVO ret = (CmmCodeVO)cmmCodeManageDAO.selectCmmClCode(cmmCodeVO);
    	return ret;
	}
	
	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	public CmmCodeVO selectCmmCode(CmmCodeVO cmmCodeVO) throws Exception {
		CmmCodeVO ret = (CmmCodeVO)cmmCodeManageDAO.selectCmmCode(cmmCodeVO);
    	return ret;
	}	
	
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 */
	public CmmCodeVO selectCmmCodeDetail(CmmCodeVO cmmCodeVO) throws Exception {
		CmmCodeVO ret = (CmmCodeVO)cmmCodeManageDAO.selectCmmCodeDetail(cmmCodeVO);
    	return ret;
	}		

	/**
	 * 공통분류코드 목록을 조회한다.
	 */
	public List selectCmmClCodeList(CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmClCodeList(searchVO);
	}	
	
	/**
	 * 공통코드 목록을 조회한다.
	 */
	public List selectCmmCodeList(CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmCodeList(searchVO);
	}
	
	/**
	 * 공통상세코드 목록을 조회한다.
	 */
	public List selectCmmDetailCodeList(CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmDetailCodeList(searchVO);
	}

	/**
	 * 공통코드 총 갯수를 조회한다.
	 */
	public int selectCmmCodeListTotCnt(CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmCodeListTotCnt(searchVO);
	}

	/**
	 * 공통분류코드를 수정한다.
	 */
	public void updateCmmClCode(CmmClCode cmmClCode) throws Exception {
		cmmCodeManageDAO.updateCmmClCode(cmmClCode);
	}	
	
	/**
	 * 공통코드를 수정한다.
	 */
	public void updateCmmCode(CmmCode cmmCode) throws Exception {
		cmmCodeManageDAO.updateCmmCode(cmmCode);
	}

	/**
	 * 공통상세코드를 수정한다.
	 */
	public void updateCmmDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		cmmCodeManageDAO.updateCmmDetailCode(cmmnDetailCode);
	}
	
	/**
	 * 공통상세코드 순번을 수정한다.
	 */
	public void updateCmmDetailCodeOrdr(CmmnDetailCode cmmnDetailCode) throws Exception {
		cmmCodeManageDAO.updateCmmDetailCodeOrdr(cmmnDetailCode);
	}	
	
	/**
	 * 공통분류코드를 삭제한다.
	 */
	public void deleteCmmClCode(CmmClCode cmmClCode) throws Exception {
		cmmCodeManageDAO.deleteCmmClCode(cmmClCode);
	}		
	
	/**
	 * 공통코드를 삭제한다.
	 */
	public void deleteCmmCode(CmmCode cmmCode) throws Exception {
		cmmCodeManageDAO.deleteCmmCode(cmmCode);
	}	
	
	/**
	 * 공통상세코드를 삭제한다.
	 */
	public void deleteCmmDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		cmmCodeManageDAO.deleteCmmDetailCode(cmmnDetailCode);
	}		
	
	/**
	 * 공통코드 순번을 수정한다.
	 */
	public void updateCmmCodeOrdr(CmmCode cmmnCode) throws Exception {
		cmmCodeManageDAO.updateCmmCodeOrdr(cmmnCode);
	}	
}
