package clikmng.nanet.go.kr.elm.grp.service;

import java.util.List;
import clikmng.nanet.go.kr.elm.com.UserClassVO;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public interface GrpManageService {
	
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param 
     * @return 
     * @throws Exception
     */
	public List<UserClassVO> selectElmGrpList()  throws Exception;

	/**
	 * 기존 관리자 아이디 중복 체크를 한다.
	 * @param mngVO
	 * @throws Exception
	 */
	public int selectUserGroupIdChk(String userGroupId) throws Exception;
	
	/**
	 * 관리자 등록을 한다.
	 * @param mngVO
	 * @throws Exception
	 */
	public void insertElmGrpRegist(UserClassVO userClassVO) throws Exception;

	/**
	 * 관리자 상세보기
	 * @param mngVO
	 * @throws Exception
	 */
	public UserClassVO selectElmGrpDetail(UserClassVO userClassVO) throws Exception;
	
	/**
	 * 관리자 수정처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateElmGrpDetail(UserClassVO userClassVO) throws Exception;
	
	/**
	 * 관리자 삭제처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void deleteElmGrpDetail(UserClassVO userClassVO) throws Exception;
	
}
