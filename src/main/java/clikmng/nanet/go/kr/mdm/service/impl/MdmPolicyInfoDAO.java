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
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;

/**
 * 
 * 사이트정보를 처리하는 DAO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */

@Repository("MdmPolicyInfoDAO")
public class MdmPolicyInfoDAO extends EgovComAbstractDAO {

	public int selectMdmPolicyInfoSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoSeq", null);
    }

	public String selectMdmPolicyInfoFileSeq() throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoFileSeq", null);
    }

 	public String selectMdmPolicyInfoMaxRegDate(String TODAY) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoMaxRegDate", TODAY);
	}

 	public int selectMdmPolicyInfoDfltListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoDfltListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoDfltList(MdmSearchVO vo) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoDfltList", vo);
	}
	
 	public int selectMdmPolicyInfoListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoList(MdmSearchVO vo) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoList", vo);
	}

 	public int selectMdmPolicyInfoCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoCnvrsListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoCnvrsList(MdmSearchVO vo) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoCnvrsList", vo);
	}

	public int selectMdmPolicyInfoDplctListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoDplctListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoDplctList(MdmSearchVO vo) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoDplctList", vo);
	}
	
	public MdmPolicyInfoVO selectMdmPolicyInfoView(String OUTBBS_CN) throws Exception {
        return (MdmPolicyInfoVO)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoView", OUTBBS_CN);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoFileVO> selectMdmPolicyInfoFileList(String OUTBBS_CN) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoFileList", OUTBBS_CN);
	}

	@SuppressWarnings("unchecked")
	public List<MdmFileVO> selectMdmPolicyInfoFileListCmmn(String OUTBBS_CN) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoFileListCmmn", OUTBBS_CN);
	}

	@SuppressWarnings("unchecked")
	public List<MdmPolicyInfoVO> selectMdmPolicyInfoListCmmn(String DUPLICATION) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmPolicyInfoListCmmn", DUPLICATION);
	}

	public MdmOutSiteVO selectMdmOutSite(String SITEID) throws Exception {
		return (MdmOutSiteVO)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmOutSite", SITEID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutSiteVO> selectMdmOutSiteList(String REGION) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmOutSiteList", REGION);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmOutSiteVO> selectMdmOutSiteListForOrg(MdmOutSiteVO vo) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmOutSiteListForOrg", vo);
	}

	public String selectMdmOutSeed(String SEEDID) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmOutSeed", SEEDID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutSeedVO> selectMdmOutSeedList(MdmOutSeedVO vo) throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmOutSeedList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutDocTypeVO> selectMdmOutDocTypeList() throws Exception {
		return list("MdmPolicyInfoDAO.selectMdmOutDocTypeList", null);
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
	
 	public MdmPolicyInfoFileVO selectMdmPolicyInfoFileDownPath(String DOWNID) throws Exception {
        return (MdmPolicyInfoFileVO)getSqlMapClientTemplate().queryForObject("MdmPolicyInfoDAO.selectMdmPolicyInfoFileDownPath", DOWNID);
	}

 	public void updateMdmPolicyInfoIsView(MdmIsViewVO vo) throws Exception {
        update("MdmPolicyInfoDAO.updateMdmPolicyInfoIsView", vo);
    }
 	
 	public void updateMdmPolicyInfoIsViewAll(MdmIsViewVO vo) throws Exception {
        update("MdmPolicyInfoDAO.updateMdmPolicyInfoIsViewAll", vo);
    }

	public void deleteMdmPolicyInfoChk(MdmIsViewVO vo) throws Exception {
        update("MdmPolicyInfoDAO.deleteMdmPolicyInfoChk", vo);
    }
	
 	public void deleteMdmPolicyInfoFile(String DOWNID) throws Exception {
        update("MdmPolicyInfoDAO.deleteMdmPolicyInfoFile", DOWNID);
	}

    public void updateMdmPolicyInfoFileListReCnvrs(String DOWNID) throws Exception {
        update("MdmPolicyInfoDAO.updateMdmPolicyInfoFileListReCnvrs", DOWNID);
    }

	public void deleteMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception {
        update("MdmPolicyInfoDAO.deleteMdmPolicyInfo", vo);
    }

	public void insertMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception {
        insert("MdmPolicyInfoDAO.insertMdmPolicyInfo", vo);
    }
	
	public void insertMdmPolicyInfoFile(MdmPolicyInfoFileVO vo) throws Exception {
        insert("MdmPolicyInfoDAO.insertMdmPolicyInfoFile", vo);
    }

	public void updateMdmPolicyInfo(MdmPolicyInfoVO vo) throws Exception {
        update("MdmPolicyInfoDAO.updateMdmPolicyInfo", vo);
    }

}
