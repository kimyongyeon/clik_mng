package clikmng.nanet.go.kr.elm.cup.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.CopyrightPermVO;
import clikmng.nanet.go.kr.elm.cup.service.ElmCupListVO;
import clikmng.nanet.go.kr.sit.pwm.service.PopupManageVO;


/**
 *
 * 저작권허락 권한 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
@Repository("CupManageDAO")
public class CupManageDAO extends EgovComAbstractDAO {

	/**
     * 저작권허락 권한 리스트
     * @param CopyrightPermVO
     * @return
     * @throws Exception
     */
	public List<ElmCupListVO> selectElmCupList(ElmCupListVO vo)  throws Exception{
		return list("cupManageDAO.selectElmCupList", vo);
	}
	public int selectElmCupListTotCnt(ElmCupListVO vo)  throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("cupManageDAO.selectElmCupListTotCnt", vo);
	}
	
	public List<ElmCupListVO> selectElmCupListAjax(ElmCupListVO vo)  throws Exception{
		return list("cupManageDAO.selectElmCupListAjax", vo);
	}

	/**
	 * 저작권허락 권한 등록
	 * @param CopyrightPermVO
	 *
	 */
	public void insertElmCupRegist(CopyrightPermVO copyrightPermVO) throws Exception {
		insert("cupManageDAO.insertElmCupRegist", copyrightPermVO);
	}

	/**
	 * 저작권허락 권한 조회
	 * @param CopyrightPermVO
	 * @return CopyrightPermVO
	 *
	 * @param mngVO
	 */
	public CopyrightPermVO selectElmCupDetail(CopyrightPermVO vo) throws Exception {
		return (CopyrightPermVO)getSqlMapClientTemplate().queryForObject("cupManageDAO.selectElmCupDetail", vo);
	}

	/**
	 * 저작권허락 권한 수정
	 * @param CopyrightPermVO
	 */
	public void updateElmCupDetail(CopyrightPermVO copyrightPermVO) throws Exception {
		update("cupManageDAO.updateElmCupDetail", copyrightPermVO);
	}

	/**
	 * 저작권허락 권한 삭제
	 * @param MngVO
	 */
	public void deleteElmCupDetail(CopyrightPermVO copyrightPermVO) throws Exception {
		delete("cupManageDAO.deleteElmCupDetail", copyrightPermVO);
	}
	
	public FileVO selectIconFile(CopyrightPermVO copyrightPermVO) throws Exception {
		return (FileVO) selectByPk("cupManageDAO.selectIconFile", copyrightPermVO);
	}
	
	
}
