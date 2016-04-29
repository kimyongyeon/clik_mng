package clikmng.nanet.go.kr.ccm.ccd.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmClCode;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCode;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;

/**
 * 
 * 공통코드에 대한 데이터 접근 클래스를 정의한다
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
@Repository("CmmCodeManageDAO")
public class CmmCodeManageDAO extends EgovComAbstractDAO {

	/**
	 * 공통분류코드를 등록한다.
	 * @param cmmClCode
	 * @throws Exception
	 */
	public void insertCmmClCode(CmmClCode cmmClCode) throws Exception {
        insert("CmmClCodeManageDAO.insertCmmClCode", cmmClCode);
	}		
	
	/**
	 * 공통코드를 등록한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void insertCmmCode(CmmCode cmmCode) throws Exception {
        insert("CmmCodeManageDAO.insertCmmCode", cmmCode);
	}

	
	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void insertCmmDetailCode(CmmnDetailCode cmmCode) throws Exception {
        insert("CmmCodeManageDAO.insertCmmDetailCode", cmmCode);                
	}	
	

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * @param cmmCodeVO
	 * @return CmmCodeVO(공통분류코드)
	 */
	public CmmCodeVO selectCmmClCode(CmmCodeVO cmmCodeVO) throws Exception {
		return (CmmCodeVO)selectByPk("CmmCodeManageDAO.selectCmmClCode", cmmCodeVO);
	}	

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmCodeVO
	 * @return CmmCodeVO(공통코드)
	 */
	public CmmCodeVO selectCmmCode(CmmCodeVO cmmCodeVO) throws Exception {
		return (CmmCodeVO)selectByPk("CmmCodeManageDAO.selectCmmCode", cmmCodeVO);
	}			
	
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param cmmCodeVO
	 * @return CmmCodeVO(공통코드)
	 */
	public CmmCodeVO selectCmmCodeDetail(CmmCodeVO cmmCodeVO) throws Exception {
		return (CmmCodeVO)selectByPk("CmmCodeManageDAO.selectCmmCodeDetail", cmmCodeVO);
	}	

    /**
	 * 공통분류코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    public List selectCmmClCodeList(CmmCodeVO searchVO) throws Exception {
        return list("CmmCodeManageDAO.selectCmmClCodeList", searchVO);
    }	
	
    /**
	 * 공통코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    public List selectCmmCodeList(CmmCodeVO searchVO) throws Exception {
        return list("CmmCodeManageDAO.selectCmmCodeList", searchVO);
    }

    
    /**
	 * 공통상세코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    public List selectCmmDetailCodeList(CmmCodeVO searchVO) throws Exception {
        return list("CmmCodeManageDAO.selectCmmDetailCodeList", searchVO);
    }    
    
    
    /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
    public int selectCmmCodeListTotCnt(CmmCodeVO searchVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("CmmCodeManageDAO.selectCmmCodeListTotCnt", searchVO);
    }

	/**
	 * 공통분류코드를 수정한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void updateCmmClCode(CmmClCode cmmClCode) throws Exception {
		update("CmmClCodeManageDAO.updateCmmClCode", cmmClCode);
	}	    
    
	/**
	 * 공통코드를 수정한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void updateCmmCode(CmmCode cmmCode) throws Exception {
		update("CmmCodeManageDAO.updateCmmCode", cmmCode);
	}
	
	/**
	 * 공통상세코드를 수정한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void updateCmmDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		update("CmmCodeManageDAO.updateCmmDetailCode", cmmnDetailCode);
	}
	
	/**
	 * 공통상세코드순번을 수정한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void updateCmmDetailCodeOrdr(CmmnDetailCode cmmnDetailCode) throws Exception {
		update("CmmCodeManageDAO.updateCmmDetailCodeOrdr", cmmnDetailCode);
	}	
	
	/**
	 * 공통분류코드를 삭제한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void deleteCmmClCode(CmmClCode cmmClCode) throws Exception {
		delete("CmmClCodeManageDAO.deleteCmmClCode", cmmClCode);
	}	
	
	/**
	 * 공통코드를 삭제한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void deleteCmmCode(CmmCode cmmCode) throws Exception {
		delete("CmmCodeManageDAO.deleteCmmCode", cmmCode);
	}
	
	/**
	 * 공통상세코드를 삭제한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void deleteCmmDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		delete("CmmCodeManageDAO.deleteCmmDetailCode", cmmnDetailCode);
	}	
	
	/**
	 * 공통상세코드순번을 수정한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	public void updateCmmCodeOrdr(CmmCode cmmCode) throws Exception {
		update("CmmCodeManageDAO.updateCmmCodeOrdr", cmmCode);
	}	
}
