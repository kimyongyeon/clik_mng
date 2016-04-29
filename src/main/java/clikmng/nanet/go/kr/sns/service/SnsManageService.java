package clikmng.nanet.go.kr.sns.service;

import java.util.List;

import clikmng.nanet.go.kr.sit.pwm.service.PopupManageVO;

/**
 * SNS 등록, 수정 처리하는 비즈니스 인터페이스 클래스
 */
public interface SnsManageService {
	
	/**
	 * sns 소통센터를 관리하기 위해 등록된 sns 소통센터목록을 조회한다.
	 * @param snsManageVO - sns Vo
	 * @return List - sns 소통센터목록 목록
	 * 
	 * @param snsManageVO
	 */
	public List selectSnsManageList(SnsManageVO snsManageVO) throws Exception;
	
	/**
	 * sns 소통센터를 관리하기 위해 등록된 sns 소통센터목록을 조회한다.
	 * @param snsManageVO - sns Vo
	 * @return List - sns 소통센터목록 목록
	 * 
	 * @param snsManageVO
	 */	
	public int selectSnsManageListCount(SnsManageVO snsManageVO) throws Exception;
	
	/**
	 * sns 소통센터정보를 신규로 등록한다.
	 * @param snsManage
	 * @return boolean - 반영성공 여부
	 * @param snsManage
	 */
	public void insertSnsManage(SnsManageVO snsManageVO) throws Exception;	
	
	/**
	 * sns code 목록을 가져온다.
	 */
//	public List selectSnsCodeList() throws Exception;
	
	
	/**
	 * sns 상세정보
	 */	
	public SnsManageVO selectSnsDetail(SnsManageVO snsManageVO) throws Exception;
	
	/**
	 * sns 삭제
	 */
	public void deleteSns(SnsManageVO snsManageVO) throws Exception;
	
	/**
	 * sns 수정
	 */
	public void updtSns(SnsManageVO snsManageVO) throws Exception;
	
	
	/**
	 * SNS 최신글 등록
	 */
	public void insertSnsNbc(SnsVO snsVO) throws Exception;
	
	/**
	 * SNS 최신글 삭제
	 */
	public void deleteSnsNbc(SnsVO snsVO) throws Exception;
	
	/**
	 * SNS 사용여부 수정
	 */
	public void updateSnsUseAt(SnsManageVO snsManageVO) throws Exception;
	
	
}
