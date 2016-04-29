package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
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
 * 사이트정보를 처리하는 DAO 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *  
 * <pre>
 * << 개정이력 (Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */

@Repository("MdmEduManualDAO")
public class MdmEduManualDAO extends EgovComAbstractDAO {

	public int selectMdmEduManualSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualSeq", null);
    }

	public String selectMdmEduManualFileSeq() throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualFileSeq", null);
    }

	public String selectMdmEduManualMaxRegDate(String TODAY) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualMaxRegDate", TODAY);
	}

 	public int selectMdmEduManualDfltListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualDfltListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmEduManualDfltList(MdmSearchVO vo) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualDfltList", vo);
	}
	
 	public int selectMdmEduManualListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmEduManualList(MdmSearchVO vo) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualList", vo);
	}

 	public int selectMdmEduManualCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualCnvrsListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmEduManualCnvrsList(MdmSearchVO vo) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualCnvrsList", vo);
	}

	public int selectMdmEduManualDplctListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualDplctListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmEduManualDplctList(MdmSearchVO vo) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualDplctList", vo);
	}
	
	public MdmPolicyInfoVO selectMdmEduManualView(String OUTBBS_CN) throws Exception {
        return (MdmPolicyInfoVO)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualView", OUTBBS_CN);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoFileVO> selectMdmEduManualFileList(String OUTBBS_CN) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualFileList", OUTBBS_CN);
	}

	@SuppressWarnings("unchecked")
	public List<MdmFileVO> selectMdmEduManualFileListCmmn(String OUTBBS_CN) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualFileListCmmn", OUTBBS_CN);
	}

	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmEduManualListCmmn(String DUPLICATION) throws Exception {
		return list("MdmEduManualDAO.selectMdmEduManualListCmmn", DUPLICATION);
	}

	public String selectMdmOutSite(String SITEID) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmOutSite", SITEID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutSiteVO> selectMdmOutSiteList(String REGION) throws Exception {
		return list("MdmEduManualDAO.selectMdmOutSiteList", REGION);
	}

	public String selectMdmOutSeed(String SEEDID) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmOutSeed", SEEDID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutSeedVO> selectMdmOutSeedList(String DOCTYPE) throws Exception {
		return list("MdmEduManualDAO.selectMdmOutSeedList", DOCTYPE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutDocTypeVO> selectMdmOutDocTypeList() throws Exception {
		return list("MdmEduManualDAO.selectMdmOutDocTypeList", null);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return list("MdmCodeDAO.selectMdmDetailCodeRIS018", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCode", CODE_ID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmJrsdCmitId", vo);
	}
	
 	public String selectMdmEduManualFileDownPath(String DOWNID) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmEduManualDAO.selectMdmEduManualFileDownPath", DOWNID);
	}

 	public void updateMdmEduManualIsView(MdmIsViewVO vo) throws Exception {
        update("MdmEduManualDAO.updateMdmEduManualIsView", vo);
    }

	public void deleteMdmEduManualChk(MdmIsViewVO vo) throws Exception {
        update("MdmEduManualDAO.deleteMdmEduManualChk", vo);
    }
	
 	public void deleteMdmEduManualFile(String DOWNID) throws Exception {
        update("MdmEduManualDAO.deleteMdmEduManualFile", DOWNID);
	}

    public void updateMdmEduManualFileListReCnvrs(String DOWNID) throws Exception {
        update("MdmEduManualDAO.updateMdmEduManualFileListReCnvrs", DOWNID);
    }

	public void deleteMdmEduManual(String OUTBBS_CN) throws Exception {
        update("MdmEduManualDAO.deleteMdmEduManual", OUTBBS_CN);
    }

	public void insertMdmEduManual(MdmPolicyInfoVO vo) throws Exception {
        insert("MdmEduManualDAO.insertMdmEduManual", vo);
    }
	
	public void insertMdmEduManualFile(MdmPolicyInfoFileVO vo) throws Exception {
        insert("MdmEduManualDAO.insertMdmEduManualFile", vo);
    }

	public void updateMdmEduManual(MdmPolicyInfoVO vo) throws Exception {
        update("MdmEduManualDAO.updateMdmEduManual", vo);
    }

}
