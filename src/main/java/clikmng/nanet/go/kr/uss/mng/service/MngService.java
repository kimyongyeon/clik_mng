package clikmng.nanet.go.kr.uss.mng.service;

import java.util.HashMap;
import java.util.List;

import clikmng.nanet.go.kr.sns.service.SnsManageVO;

public interface MngService {
	
	/**
	 * 관리자 조회 및 게시물 수를 구한다.
	 * @param mngVO
	 * @throws Exception
	 */
	public List selectMngList(MngVO mngVO) throws Exception;
	public int selectMngListCnt(MngVO mngVO) throws Exception;

	/**
	 * 관리자 조회 및 게시물 수를 구한다.
	 * @param mngVO
	 * @throws Exception
	 */
	public List selectAuthorList(MngVO mngVO) throws Exception;
	
	
	/**
	 * 관리자 등록을 한다.
	 * @param mngVO
	 * @throws Exception
	 */
	public int selectMngId(MngVO mngVO) throws Exception; 	// 기등록 된 ID인지 확인.
	public void insertMngManage(MngVO mngVO) throws Exception;
	public void insertMngMappingManage(MngVO mngVO) throws Exception;
	
	/**
	 * 관리자 상세보기
	 * @param mngVO
	 * @throws Exception
	 */
	public MngManage selectMngDetail(MngVO mngVO) throws Exception;
	
	/**
	 * 관리자 수정처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void editMngDetail(MngVO mngVO) throws Exception;
	public void editMngDetailWithPassword(MngVO mngVO) throws Exception;	
	/**
	 * 관리자 삭제처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void delMngManage(MngVO mngVO) throws Exception;
	
	/** ---------------- 지방의회/지자체 관리자 시작 ----------------  */
	/**
	 * 지방의회/지자체 조회 및 게시물 수를 구한다.
	 * @param mngVO
	 * @throws Exception
	 */
	public List selectLocalMngList(MngVO mngVO) throws Exception;
	public int selectLocalMngListCnt(MngVO mngVO) throws Exception;
	
	/**
	 * 지방의회/지자체 상세보기
	 * @param mngVO
	 * @throws Exception
	 */
	public MngVO selectLocalMngDetail(MngVO mngVO) throws Exception;
	
	/**
	 * 지방의회/지자체 관리자 승인
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateApprovalLocalMng(MngVO mngVO) throws Exception;
		
	/**
	 * 지방의회/지자체 관리자 수정처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateLocalMngDetail(MngVO mngVO) throws Exception;
	
	/**
	 * 지방의회/지자체 관리자 삭제처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void deleteLocalMngDetail(MngVO mngVO) throws Exception;	
}
