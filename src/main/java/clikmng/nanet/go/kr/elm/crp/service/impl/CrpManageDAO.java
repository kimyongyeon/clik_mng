package clikmng.nanet.go.kr.elm.crp.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestSetupVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpListVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpDetailVO;

/**
 * 
 * 환경설정
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
@Repository("CrpManageDAO")
public class CrpManageDAO extends EgovComAbstractDAO {
	
	/**
     * 열람 신청 권한 리스트 조회 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<ElmCrpListVO> selectElmCrpList(ElmCrpListVO searchVO)  throws Exception{
		return list("crpManageDAO.selectElmCrpList", searchVO);
	}
	public int selectElmCrpListTotCnt(ElmCrpListVO searchVO)  throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("crpManageDAO.selectElmCrpListTotCnt", searchVO);
	}



	/**
	 * 열람신청 권한를 등록한다.
	 * @param mngVO
	 * 
	 */
	public void insertElmCrpRegist(ElmCrpDetailVO vo) throws Exception {
	    insert("crpManageDAO.insertElmCrpRegist", vo);
	}      

	/**
	 * 열람신청 권한 - 열람 권한를 등록한다.
	 * @param mngVO
	 * 
	 */
	
	public void insertElmCrpOpenCode(ReadGrantVO vo) throws Exception {
	    insert("crpManageDAO.insertElmCrpOpenCode", vo);
	}      
	
	
	/**
	 * 열람신청 권한 - 상세내용 조회
	 * @param MngVO
	 * @return Object
	 */
	public ElmCrpDetailVO selectElmCrpDetail(ElmCrpDetailVO vo) throws Exception {
	    return (ElmCrpDetailVO)getSqlMapClientTemplate().queryForObject("crpManageDAO.selectElmCrpDetail", vo);
	}	

	public List<ReadGrantVO> selectElmCrpOpenCode(ElmCrpDetailVO vo) throws Exception {
	    return list("crpManageDAO.selectElmCrpOpenCode", vo);
	}	

	
	/**
	 * 열람신청 권한 - 수정
	 * @param ElmCrpDetailVO
	 * @return 
	 */
	public void updateElmCrpDetail(ElmCrpDetailVO vo) throws Exception {
	    update("crpManageDAO.updateElmCrpDetail", vo);
	}	
	//열람신청 권한 - 열람 권한를 수정한다.
	public void updateElmCrpOpenCode(ReadGrantVO vo) throws Exception {
	    update("crpManageDAO.updateElmCrpOpenCode", vo);
	}	

	/**
	 * 관리자 상세내용 삭제 / 보안설정 삭제 
 	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
	public void deleteElmCrpDetail(ElmCrpDetailVO vo) throws Exception {
	    delete("crpManageDAO.deleteElmCrpDetail", vo);
	}	

	public void deleteElmCrpOpenCode(ElmCrpDetailVO vo) throws Exception {
	    delete("crpManageDAO.deleteElmCrpOpenCode", vo);
	}	

}
