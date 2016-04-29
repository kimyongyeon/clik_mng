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
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.service.MdmEduManualService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmEduManualService")
public class MdmEduManualServiceImpl extends AbstractServiceImpl implements MdmEduManualService {

    @Resource(name="MdmEduManualDAO")
    private MdmEduManualDAO mdmEduManualDAO;
        
    /** ID Generation */
    
/*	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
 
	public int selectMdmEduManualSeq() throws Exception {
		return mdmEduManualDAO.selectMdmEduManualSeq();
	}

	public String selectMdmEduManualFileSeq() throws Exception {
		return mdmEduManualDAO.selectMdmEduManualFileSeq();
	}

	public String selectMdmEduManualMaxRegDate(String TODAY) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualMaxRegDate(TODAY);
	}

	public int selectMdmEduManualDfltListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualDfltListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmEduManualDfltList(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualDfltList(vo);
	}

	public int selectMdmEduManualListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmEduManualList(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualList(vo);
	}

	public int selectMdmEduManualCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualCnvrsListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmEduManualCnvrsList(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualCnvrsList(vo);
	}

	public int selectMdmEduManualDplctListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualDplctListTotCnt(vo);
	}
	
	public List<MdmPolicyInfoVO> selectMdmEduManualDplctList(MdmSearchVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualDplctList(vo);
	}

	public MdmPolicyInfoVO selectMdmEduManualView(String OUTBBS_CN) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualView(OUTBBS_CN);
	}
	
	public List<MdmPolicyInfoFileVO> selectMdmEduManualFileList(String OUTBBS_CN) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualFileList(OUTBBS_CN);
	}
	
	public List<MdmFileVO> selectMdmEduManualFileListCmmn(String OUTBBS_CN) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualFileListCmmn(OUTBBS_CN);
	}

	public List<MdmPolicyInfoVO> selectMdmEduManualListCmmn(String DUPLICATION) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualListCmmn(DUPLICATION);
	}

	public String selectMdmOutSite(String SITEID) throws Exception {
		return mdmEduManualDAO.selectMdmOutSite(SITEID);
	}

	public List<MdmOutSiteVO> selectMdmOutSiteList(String REGION) throws Exception {
		return mdmEduManualDAO.selectMdmOutSiteList(REGION);
	}

	public String selectMdmOutSeed(String SEEDID) throws Exception {
		return mdmEduManualDAO.selectMdmOutSeed(SEEDID);
	}

	public List<MdmOutSeedVO> selectMdmOutSeedList(String DOCTYPE) throws Exception {
		return mdmEduManualDAO.selectMdmOutSeedList(DOCTYPE);
	}

	public List<MdmOutDocTypeVO> selectMdmOutDocTypeList() throws Exception {
		return mdmEduManualDAO.selectMdmOutDocTypeList();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return mdmEduManualDAO.selectMdmDetailCodeRIS018();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return mdmEduManualDAO.selectMdmDetailCode(CODE_ID);
	}

	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return mdmEduManualDAO.selectMdmJrsdCmitId(vo);
	}
	
	public String selectMdmEduManualFileDownPath(String DOWNID) throws Exception {
		return mdmEduManualDAO.selectMdmEduManualFileDownPath(DOWNID);
	}

	public void deleteMdmEduManualFile(String DOWNID) throws Exception {
		mdmEduManualDAO.deleteMdmEduManualFile(DOWNID);
	}

	public void updateMdmEduManualFileListReCnvrs(String DOWNID) throws Exception {
		mdmEduManualDAO.updateMdmEduManualFileListReCnvrs(DOWNID);
	}

	public void updateMdmEduManualIsView(MdmIsViewVO vo) throws Exception {
		mdmEduManualDAO.updateMdmEduManualIsView(vo);
	}

	public void deleteMdmEduManualChk(MdmIsViewVO vo) throws Exception {
		mdmEduManualDAO.deleteMdmEduManualChk(vo);
	}

	public void deleteMdmEduManual(String OUTBBS_CN) throws Exception {
		mdmEduManualDAO.deleteMdmEduManual(OUTBBS_CN);
	}

	public void insertMdmEduManual(MdmPolicyInfoVO vo) throws Exception {
		mdmEduManualDAO.insertMdmEduManual(vo);		
	}
	
	public void insertMdmEduManualFile(MdmPolicyInfoFileVO vo) throws Exception {
		mdmEduManualDAO.insertMdmEduManualFile(vo);		
	}

	public void updateMdmEduManual(MdmPolicyInfoVO vo) throws Exception {
		mdmEduManualDAO.updateMdmEduManual(vo);		
	}

}
