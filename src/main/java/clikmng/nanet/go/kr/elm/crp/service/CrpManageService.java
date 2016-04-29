package clikmng.nanet.go.kr.elm.crp.service;

import java.util.List;

import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestSetupVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpListVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpDetailVO;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public interface CrpManageService {
	
	/**
	 * 열람신청 권한 관리 리스트 조회 
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List<ElmCrpListVO> selectElmCrpList(ElmCrpListVO searchVO)  throws Exception;
	public int selectElmCrpListTotCnt(ElmCrpListVO searchVO)  throws Exception;	


	/**
	 * 열람신청 권한 관리 리스트 등록
	 * @param mngVO
	 * @throws Exception
	 */
	public void insertElmCrpRegist(ElmCrpDetailVO vo) throws Exception;

	/**
	 * 열람신청 권한 관리 상세보기
	 * @param mngVO
	 * @throws Exception
	 */
	public ElmCrpDetailVO selectElmCrpDetail(ElmCrpDetailVO vo) throws Exception;
	public List<ReadGrantVO> selectElmCrpOpenCode(ElmCrpDetailVO vo)  throws Exception;
	
	/**
	 * 열람신청 권한 관리 수정처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateElmCrpDetail(ElmCrpDetailVO vo) throws Exception;
	
	/**
	 * 열람신청 권한 관리 삭제처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void deleteElmCrpDetail(ElmCrpDetailVO vo) throws Exception;

}
