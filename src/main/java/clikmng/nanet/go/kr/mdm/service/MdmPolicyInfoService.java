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
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;

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

public interface MdmPolicyInfoService {

	int selectMdmPolicyInfoSeq() throws Exception;
	
	String selectMdmPolicyInfoFileSeq() throws Exception;

	String selectMdmPolicyInfoMaxRegDate(String TODAY) throws Exception;
	
	int selectMdmPolicyInfoDfltListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmPolicyInfoDfltList(MdmSearchVO vo) throws Exception;

	int selectMdmPolicyInfoListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmPolicyInfoList(MdmSearchVO vo) throws Exception;
    
	int selectMdmPolicyInfoCnvrsListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmPolicyInfoCnvrsList(MdmSearchVO vo) throws Exception;

	int selectMdmPolicyInfoDplctListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmPolicyInfoVO> selectMdmPolicyInfoDplctList(MdmSearchVO vo) throws Exception;
    
    MdmPolicyInfoVO selectMdmPolicyInfoView(String OUTBBS_CN) throws Exception;

    List<MdmPolicyInfoFileVO> selectMdmPolicyInfoFileList(String OUTBBS_CN) throws Exception;
    
    List<MdmFileVO> selectMdmPolicyInfoFileListCmmn(String OUTBBS_CN) throws Exception;
    
    List<MdmPolicyInfoVO> selectMdmPolicyInfoListCmmn(String DUPLICATION) throws Exception;
    
    MdmOutSiteVO selectMdmOutSite(String SITEID) throws Exception;

    List<MdmOutSiteVO> selectMdmOutSiteList(String REGION) throws Exception;
    
    // SITE LIST(FOR ORG)
    List<MdmOutSiteVO> selectMdmOutSiteListForOrg(MdmOutSiteVO vo) throws Exception;

    String selectMdmOutSeed(String SEEDID) throws Exception;

    List<MdmOutSeedVO> selectMdmOutSeedList(MdmOutSeedVO vo) throws Exception;

    List<MdmOutDocTypeVO> selectMdmOutDocTypeList() throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception;
    
    List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception;
    
    MdmPolicyInfoFileVO selectMdmPolicyInfoFileDownPath(String DOWNID) throws Exception;

	void updateMdmPolicyInfoIsView(MdmIsViewVO vo) throws Exception;
	
	void updateMdmPolicyInfoIsViewAll(MdmIsViewVO vo) throws Exception;

	void deleteMdmPolicyInfoFile(String DOWNID) throws Exception;
    
	void updateMdmPolicyInfoFileListReCnvrs(String DOWNID) throws Exception;
	
    void deleteMdmPolicyInfoChk(MdmIsViewVO vo) throws Exception;

    void deleteMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception;

    void insertMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception;
    
    void insertMdmPolicyInfoFile(MdmPolicyInfoFileVO vo) throws Exception;

    void updateMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception;

}
