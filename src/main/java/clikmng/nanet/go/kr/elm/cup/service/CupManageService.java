package clikmng.nanet.go.kr.elm.cup.service;

import java.util.List;

import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.CopyrightPermVO;
import clikmng.nanet.go.kr.elm.cup.service.ElmCupListVO;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public interface CupManageService {


	/**
	 * 저작권허락 권한 리스트
	 * @param CopyrightPermVO
	 * @return
	 * @throws Exception
	 */
	public int selectElmCupListTotCnt(ElmCupListVO vo)  throws Exception;
	public List<ElmCupListVO> selectElmCupList(ElmCupListVO vo)  throws Exception;
	public List<ElmCupListVO> selectElmCupListAjax(ElmCupListVO vo)  throws Exception;
	/**
	 * 저작권허락 권한 등록
	 * @param CopyrightPermVO
	 *
	 */
	public void insertElmCupRegist(CopyrightPermVO copyrightPermVO) throws Exception;

	/**
	 * 저작권허락 권한 조회
	 * @param CopyrightPermVO
	 * @return CopyrightPermVO
	 *
	 * @param mngVO
	 */
	public CopyrightPermVO selectElmCupDetail(CopyrightPermVO vo) throws Exception;

	/**
	 * 저작권허락 권한 수정
	 * @param CopyrightPermVO
	 */
	public void updateElmCupDetail(CopyrightPermVO copyrightPermVO) throws Exception;

	/**
	 * 저작권허락 권한 삭제
	 * @param CopyrightPermVO
	 */
	public void deleteElmCupDetail(CopyrightPermVO copyrightPermVO) throws Exception;

	/**
	 * 저작권허락 아이콘 삭제
	 * @param CopyrightPermVO
	 */
	public void deleteIconFile(CopyrightPermVO copyrightPermVO) throws Exception;

	

}
