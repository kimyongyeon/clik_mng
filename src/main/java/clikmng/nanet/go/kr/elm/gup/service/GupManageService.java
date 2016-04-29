package clikmng.nanet.go.kr.elm.gup.service;

import java.util.List;

import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupListVO;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupDetailVO;


/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public interface GupManageService {
	
	/**
	 * 저작권허락 권한 관리
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List<ElmGupListVO> selectElmGupList()  throws Exception;


	/**
	 * 저작권허락 권한 관리. - 그룹 목록 조회
	 * @param 
 	 * @return UserClassVO
	 * @throws Exception
	 */
	public ElmGupDetailVO selectElmGupDetail(ElmGupDetailVO vo) throws Exception;
	
		
	public List<UserClassVO> selectUserClass()  throws Exception;
		

	/**
	 * 저작권허락 권한 관리
	 * @param ElmGupDetailVO
	 * @throws Exception
	 */
	public void insertElmGupRegist(ElmGupDetailVO vo) throws Exception;

	/**
	 * 저작권허락 권한 관리
	 * @param ElmGupDetailVO
	 * @throws Exception
	 */
	public void updateElmGupDetail(ElmGupDetailVO vo) throws Exception;

	/**
	 * 저작권허락 권한 관리
	 * @param ElmGupDetailVO
	 * @throws Exception
	 */
	public void deleteElmGupDetail(ElmGupDetailVO vo) throws Exception;

}
