package clikmng.nanet.go.kr.mim.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clikmng.nanet.go.kr.sit.log.service.LogManageVO;

/**
 * 
 * 모바일 관리 처리하는 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */
public interface MimManageService {
	
	/**
     * 그룹설정 - 그룹목록
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public Map selectGroupListInfo(MimManageVO searchVO) throws Exception;	
	
	/**
     * 팝업 - 지방의원을 조회한다.
     * @param searchVO
     * @return 구성원 목록
     * @throws Exception
     */
	public Map selectPopSearchRasmblyListInfo(MimManageVO searchVO) throws Exception;

	/**
     * 팝업 - 지방의회 및 지자체 담당자를 조회한다.
     * @param searchVO, asemblySeCode - C:지방의회,  B : 지자체
     * @return 구성원 목록
     * @throws Exception
     */
	public Map selectPopSearchRasmblyChargerListInfo(MimManageVO searchVO) throws Exception;
	
	/**
	 * 메일링 -  그룹 및 그룹 구성원 등록
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertGroupInfo(MimManageVO searchVO) throws Exception;
	
	/**
	 * 메일링 -  그룹 및 그룹 구성원 상세보기
	 * @param searchVO
	 * @throws Exception
	 */
	public Map selectGroupDetailInfo(MimManageVO searchVO) throws Exception;
	
	/**
	 * 메일링 -  그룹 및 그룹 구성원 수정
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateGroupInfo(MimManageVO searchVO) throws Exception;
	
	/**
	 * 메일링 -  그룹 및 그룹 구성원 삭제
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteGroupInfo(MimManageVO searchVO) throws Exception;
	
	
    //######################################  메일 발송 시작 ########################################//
	
	/**
	 * 메일링 -  팝업 그룹 조회
	 * @param searchVO
	 * @throws Exception
	 */
	public Map selectPopGroupListInfo(MimManageVO mimManageVO) throws Exception;	
	
	/**
	 * 메일링 -  메일발송
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertSendMailInfo(MimManageVO mimManageVO) throws Exception;
	
	/**
	 * 메일링 -  수신거부 수신자 제외
	 * @param String rcvrEmail
	 * @throws Exception
	 */
	public List selectRejectRcvr(MimManageVO mimManageVO) throws Exception;	
	
	/**
	 * 메일링 -  발송완료 후 발송상태 수정 및 수신자거부 명수 수정
	 * @param MimManageVO
	 * @throws Exception
	 */
	public void updateSendMailStatus(MimManageVO mimManageVO) throws Exception;	
	
	
	/**
	 * 메일링 -  그룹 구성원 조회
	 * @param MimManageVO
	 * @throws Exception
	 */
	public List selectSearchGroupAjax(MimManageVO mimManageVO) throws Exception;
	
	/**
	 * 메일링 -  양식선택
	 * @param 
	 * @throws Exception
	 */
	public List selectFormList() throws Exception;
	
	/**
	 * 메일링 -  양식선택
	 * @param 
	 * @throws Exception
	 */
	public String selectChangeFormAjax(MimManageVO vo) throws Exception;
	
    //######################################  메일 발송 끝 ########################################//
	
    //######################################  메일 발송 목록 시작 ########################################//
	
	/**
	 * 메일링 -  발송목록 내역
	 * @param 
	 * @throws Exception
	 */
	public Map selectSendMailListInfo(MimManageVO vo) throws Exception;
	
    //######################################  메일 발송 목록 끝 ########################################//	
	
    //######################################  메일 수신거부 목록 시작 ########################################//
	
	/**
	 * 메일링 -  수신거부목록 내역
	 * @param 
	 * @throws Exception
	 */
	public Map selectRejectEmailList(MimManageVO vo) throws Exception;
	
	/**
	 * 메일링 -  수신거부정보 중복확인
	 * @param 
	 * @throws Exception
	 */
	public int selectConfirmEmail(String rejectEmail) throws Exception;	
	
	/**
	 * 메일링 -  수신거부정보 등록
	 * @param 
	 * @throws Exception
	 */
	public void insertRejectEmailInfo(HashMap<String, String> rejectInfoMap) throws Exception;
	
	
    //######################################  메일 수신거부 목록 끝 ########################################//		
	
	
}
