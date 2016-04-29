package clikmng.nanet.go.kr.uss.mng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.enc.NanetSEED;
import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.uss.mng.service.MngManage;
import clikmng.nanet.go.kr.uss.mng.service.MngService;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("MngService")
public class MngServiceImpl extends AbstractServiceImpl implements MngService {

	@Resource(name="MngDao")
	private MngDao mngDao;
	
	/**
	 * 관리자 목록 조회
	 */
	public List selectMngList(MngVO mngVO) throws Exception {
		return (List)mngDao.selectMngList(mngVO);
	}

	/**
	 * 관리자 목록 갯수 조회
	 */
    public int selectMngListCnt(MngVO mngVO) throws Exception {
        return (Integer)mngDao.selectMngListCnt(mngVO);
    }
    
	/**
	 * 관리자 권한 리스트 조회
	 */
	public List selectAuthorList(MngVO mngVO) throws Exception {
		return (List)mngDao.selectAuthorList(mngVO);
	}    
	
	/**
	 * 관리자 등록 및 Mapping Table insert(TNPEMPLYRSCRTYESTBS)
	 */
	public int selectMngId(MngVO mngVO) throws Exception {
		return mngDao.selectMngId(mngVO);
	}
	
	public void insertMngManage(MngVO mngVO) throws Exception {
		mngDao.insertMngManage(mngVO);
	}
	
	public void insertMngMappingManage(MngVO mngVO) throws Exception {
		mngDao.insertMngMappingManage(mngVO);
	}	
	
	
	/**
	 * 관리자 상세내용
	 */
	public MngManage selectMngDetail(MngVO mngVO) throws Exception {
		return (MngManage)mngDao.selectMngDetail(mngVO);
	}
	
	/**
	 * 관리자 수정처리
	 */
	public void editMngDetail(MngVO mngVO) throws Exception {
		// 관리자 수정
		mngDao.editMngDetail(mngVO);
		// 보안설정 테이블 수정
		// mngDao.editAuthorDetail(mngVO);
	}	
	
	
	public void editMngDetailWithPassword(MngVO mngVO) throws Exception {
		// 관리자 수정
		mngDao.editMngDetailWithPassword(mngVO);
		// 보안설정 테이블 수정
		// mngDao.editAuthorDetail(mngVO);
	}		
	
	/**
	 * 관리자 삭제처리
	 */
	public void delMngManage(MngVO mngVO) throws Exception {
		// 보안설정 테이블 수정
		// mngDao.delMngMappingManage(mngVO);
		// 관리자 수정
		mngDao.delMngManage(mngVO);

	}
	
	/** ---------------- 지방의회/지자체 관리자 시작 ----------------  */
	/**
	 * 지방의회/지자체 목록 조회
	 */
	public List selectLocalMngList(MngVO mngVO) throws Exception {
		return (List)mngDao.selectLocalMngList(mngVO);
	}

	/**
	 * 지방의회/지자체 목록 갯수 조회
	 */
    public int selectLocalMngListCnt(MngVO mngVO) throws Exception {
        return (Integer)mngDao.selectLocalMngListCnt(mngVO);
    }
	
    
	/**
	 * 지방의회/지자체 상세내용
	 */
	public MngVO selectLocalMngDetail(MngVO mngVO) throws Exception {
		
		return (MngVO)mngDao.selectLocalMngDetail(mngVO);
	}
	
	/**
	 * 지방의회/지자체 관리자 승인
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateApprovalLocalMng(MngVO mngVO) throws Exception{
		mngDao.updateApprovalLocalMng(mngVO);
	}
	
	/**
	 * 지방의회/지자체 관리자 수정처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateLocalMngDetail(MngVO mngVO) throws Exception{
		mngDao.updateLocalMngDetail(mngVO);
	}
	
	/**
	 * 지방의회/지자체 관리자 삭제처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void deleteLocalMngDetail(MngVO mngVO) throws Exception{
		mngDao.deleteLocalMngDetail(mngVO);
	}
	
	
}
