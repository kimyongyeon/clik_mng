package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
import clikmng.nanet.go.kr.mdm.service.MdmPolicyInfoService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmPolicyInfoService")
public class MdmPolicyInfoServiceImpl extends AbstractServiceImpl implements MdmPolicyInfoService {

    @Resource(name="MdmPolicyInfoDAO")
    private MdmPolicyInfoDAO mdmPolicyInfoDAO;
        
    /** ID Generation */
    
/*	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/

	public int selectMdmPolicyInfoSeq() throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoSeq();
	}

	public String selectMdmPolicyInfoFileSeq() throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoFileSeq();
	}

	public String selectMdmPolicyInfoMaxRegDate(String TODAY) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoMaxRegDate(TODAY);
	}

	public int selectMdmPolicyInfoDfltListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoDfltListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoDfltList(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoDfltList(vo);
	}

	public int selectMdmPolicyInfoListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoList(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoList(vo);
	}

	public int selectMdmPolicyInfoCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoCnvrsListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoCnvrsList(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoCnvrsList(vo);
	}

	public int selectMdmPolicyInfoDplctListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoDplctListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoDplctList(MdmSearchVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoDplctList(vo);
	}

	public MdmPolicyInfoVO selectMdmPolicyInfoView(String OUTBBS_CN) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoView(OUTBBS_CN);
	}
	
	public List<MdmPolicyInfoFileVO> selectMdmPolicyInfoFileList(String OUTBBS_CN) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoFileList(OUTBBS_CN);
	}
	
	public List<MdmFileVO> selectMdmPolicyInfoFileListCmmn(String OUTBBS_CN) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoFileListCmmn(OUTBBS_CN);
	}

	public List<MdmPolicyInfoVO> selectMdmPolicyInfoListCmmn(String DUPLICATION) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoListCmmn(DUPLICATION);
	}

	public MdmOutSiteVO selectMdmOutSite(String SITEID) throws Exception {
		return mdmPolicyInfoDAO.selectMdmOutSite(SITEID);
	}

	public List<MdmOutSiteVO> selectMdmOutSiteList(String REGION) throws Exception {
		return mdmPolicyInfoDAO.selectMdmOutSiteList(REGION);
	}
	
	public List<MdmOutSiteVO> selectMdmOutSiteListForOrg(MdmOutSiteVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmOutSiteListForOrg(vo);
	}

	public String selectMdmOutSeed(String SEEDID) throws Exception {
		return mdmPolicyInfoDAO.selectMdmOutSeed(SEEDID);
	}

	public List<MdmOutSeedVO> selectMdmOutSeedList(MdmOutSeedVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmOutSeedList(vo);
	}

	public List<MdmOutDocTypeVO> selectMdmOutDocTypeList() throws Exception {
		return mdmPolicyInfoDAO.selectMdmOutDocTypeList();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return mdmPolicyInfoDAO.selectMdmDetailCodeRIS018();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return mdmPolicyInfoDAO.selectMdmDetailCode(CODE_ID);
	}

	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return mdmPolicyInfoDAO.selectMdmJrsdCmitId(vo);
	}
	
	public MdmPolicyInfoFileVO selectMdmPolicyInfoFileDownPath(String DOWNID) throws Exception {
		return mdmPolicyInfoDAO.selectMdmPolicyInfoFileDownPath(DOWNID);
	}

	public void deleteMdmPolicyInfoFile(String DOWNID) throws Exception {
		mdmPolicyInfoDAO.deleteMdmPolicyInfoFile(DOWNID);
	}

	public void updateMdmPolicyInfoFileListReCnvrs(String DOWNID) throws Exception {
		mdmPolicyInfoDAO.updateMdmPolicyInfoFileListReCnvrs(DOWNID);
	}

	public void updateMdmPolicyInfoIsView(MdmIsViewVO vo) throws Exception {
		mdmPolicyInfoDAO.updateMdmPolicyInfoIsView(vo);
	}
	
	public void updateMdmPolicyInfoIsViewAll(MdmIsViewVO vo) throws Exception {
		mdmPolicyInfoDAO.updateMdmPolicyInfoIsViewAll(vo);
	}

	public void deleteMdmPolicyInfoChk(MdmIsViewVO vo) throws Exception {
		mdmPolicyInfoDAO.deleteMdmPolicyInfoChk(vo);
	}

	public void deleteMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception {
		mdmPolicyInfoDAO.deleteMdmPolicyInfo(vo);
	}

	public void insertMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception {
		mdmPolicyInfoDAO.insertMdmPolicyInfo(vo);		
	}
	
	public void insertMdmPolicyInfoFile(MdmPolicyInfoFileVO vo) throws Exception {
		mdmPolicyInfoDAO.insertMdmPolicyInfoFile(vo);		
	}

	public void updateMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception {
		mdmPolicyInfoDAO.updateMdmPolicyInfo(vo);		
	}

}
