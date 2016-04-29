package clikmng.nanet.go.kr.sns.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.sns.service.SnsManageService;
import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.sns.service.SnsVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/**
 * 개요
 * - SNS 소통센터에 대한 ServiceImpl을 정의한다.
 * 
 * 상세내용
 * - SNS 소통센터에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - SNS 소통센터의 조회기능은 목록조회, 상세조회로, 사용자화면 보기로 구분된다.
 */

@Service("snsManageService")
public class SnsManageServiceImpl extends AbstractServiceImpl implements SnsManageService {

        @Resource(name = "snsManageDAO")
	public SnsManageDAO dao;

        @Resource(name = "egovPopupManageIdGnrService")
        private EgovIdGnrService idgenService;
        
	public SnsManageServiceImpl(){}


	/**
	 * SNS 소통센터를 관리하기 위해 등록된 SNS 소통센터목록을 조회한다.
	 * @param popupManageVO - SNS 소통센터 Vo
	 * @return List - SNS 소통센터 목록
	 * 
	 * @param popupManageVO
	 */
	public List selectSnsManageList(SnsManageVO snsManageVO) throws Exception {
		return (List)dao.selectSnsManageList(snsManageVO);
	}
	
	
    /**
     * SNS 소통센터를 관리하기 위해 등록된 SNS 소통센터목록을 조회한다.
     * @param popupManageVO - SNS 소통센터 Vo
     * @return List - SNS 소통센터 목록
     * 
     */
    public int selectSnsManageListCount(SnsManageVO snsManageVO) throws Exception {
            return (Integer)dao.selectSnsManageListCount(snsManageVO);
    }
    
	/**
	 * SNS 소통센터 정보를 신규로 등록한다.
	 * @param snsManage
	 * @return boolean - 반영성공 여부
	 * 
	 */
	public void insertSnsManage(SnsManageVO snsManageVO) throws Exception {
	    String sMakeId = idgenService.getNextStringId();
	    dao.insertSnsManage(snsManageVO);
	}   
	
	/**
	 * SNS 코드 목록  
	 * @return List - SNS 소통센터 코드목록
	 */
//	public List selectSnsCodeList() throws Exception {
//		return (List)dao.selectSnsCodeList();
//	}	
//	
	
	/**
	 * SNS 상세보기
	 */
	public SnsManageVO selectSnsDetail(SnsManageVO snsManageVO) throws Exception {
		return (SnsManageVO)dao.selectSnsDetail(snsManageVO);
	}

	/**
	 * 삭제한다.
	 */
	public void deleteSns(SnsManageVO snsManageVO) throws Exception {
	    dao.deleteSns(snsManageVO);
	}
	
	/**
	 * 수정한다.
	 */
	public void updtSns(SnsManageVO snsManageVO) throws Exception {
	    dao.updtSns(snsManageVO);
	}	
	
	
	/**
	 * SNS 최신글을 등록한다.
	 */
	public void insertSnsNbc(SnsVO snsVO) throws Exception{
		dao.insertSnsNbc(snsVO);
	}
	
	/**
	 * SNS 최신글을 삭제한다.
	 */
	public void deleteSnsNbc(SnsVO snsVO) throws Exception{
		dao.deleteSnsNbc(snsVO);
	}
	
	/**
	 * SNS 사용여부 수정
	 */
	public void updateSnsUseAt(SnsManageVO snsManageVO) throws Exception {
		dao.updateSnsUseAt(snsManageVO);
	}
	
}