package clikmng.nanet.go.kr.mdm.service;

import java.util.List;

import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutDocTypeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;

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

public interface MdmEduManualService {

	int selectMdmEduManualSeq() throws Exception;
	
	String selectMdmEduManualFileSeq() throws Exception;

	String selectMdmEduManualMaxRegDate(String TODAY) throws Exception;
	
	int selectMdmEduManualDfltListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmEduManualDfltList(MdmSearchVO vo) throws Exception;

	int selectMdmEduManualListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmEduManualList(MdmSearchVO vo) throws Exception;
    
	int selectMdmEduManualCnvrsListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmEduManualCnvrsList(MdmSearchVO vo) throws Exception;

	int selectMdmEduManualDplctListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmEduManualDplctList(MdmSearchVO vo) throws Exception;
    
    MdmPolicyInfoVO selectMdmEduManualView(String OUTBBS_CN) throws Exception;

    List<MdmPolicyInfoFileVO> selectMdmEduManualFileList(String OUTBBS_CN) throws Exception;
    
    List<MdmFileVO> selectMdmEduManualFileListCmmn(String OUTBBS_CN) throws Exception;
    
    List<MdmPolicyInfoVO> selectMdmEduManualListCmmn(String DUPLICATION) throws Exception;
    
    String selectMdmOutSite(String SITEID) throws Exception;

    List<MdmOutSiteVO> selectMdmOutSiteList(String REGION) throws Exception;

    String selectMdmOutSeed(String SEEDID) throws Exception;

    List<MdmOutSeedVO> selectMdmOutSeedList(String DOCTYPE) throws Exception;

    List<MdmOutDocTypeVO> selectMdmOutDocTypeList() throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception;
    
    List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception;
    
	String selectMdmEduManualFileDownPath(String DOWNID) throws Exception;

	void updateMdmEduManualIsView(MdmIsViewVO vo) throws Exception;

	void deleteMdmEduManualFile(String DOWNID) throws Exception;
    
	void updateMdmEduManualFileListReCnvrs(String DOWNID) throws Exception;
	
    void deleteMdmEduManualChk(MdmIsViewVO vo) throws Exception;

    void deleteMdmEduManual(String OUTBBS_CN) throws Exception;

    void insertMdmEduManual(MdmPolicyInfoVO vo) throws Exception;
    
    void insertMdmEduManualFile(MdmPolicyInfoFileVO vo) throws Exception;

    void updateMdmEduManual(MdmPolicyInfoVO vo) throws Exception;

}