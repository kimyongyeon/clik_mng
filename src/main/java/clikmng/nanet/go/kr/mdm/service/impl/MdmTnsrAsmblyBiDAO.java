package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyItncAsembyVO;

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

@Repository("MdmTnsrAsmblyBiDAO")
public class MdmTnsrAsmblyBiDAO extends EgovComAbstractDAO {

	public int selectMdmBillSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillSeq", null);
	}

	public int selectMdmBillFileSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillFileSeq", null);
	}

 	public String selectMdmBillFileDownPathByBiCn(String DOWNID) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillFileDownPathByBiCn", DOWNID);
	}

 	public String selectMdmBillFileDownPathByBiFileId(String DOWNID) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillFileDownPathByBiFileId", DOWNID);
	}

 	public String selectMdmBillFileExt(String BI_CN) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillFileExt", BI_CN);
	}
 	
 	public String selectMdmBillMaxRegDate(String TODAY) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillMaxRegDate", TODAY);
	}

 	public int selectMdmBillDfltListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillDfltListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyBiVO> selectMdmBillDfltList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyBiDAO.selectMdmBillDfltList", vo);
	}
 	
 	public int selectMdmBillListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyBiVO> selectMdmBillList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyBiDAO.selectMdmBillList", vo);
	}
	
 	public int selectMdmBillCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillCnvrsListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyBiVO> selectMdmBillCnvrsList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyBiDAO.selectMdmBillCnvrsList", vo);
	}
	
 	public int selectMdmBillDplctListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillDplctListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyBiVO> selectMdmBillDplctList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyBiDAO.selectMdmBillDplctList", vo);
	}
	
	public MdmTnsrAsmblyBiVO selectMdmBillView(MdmSearchVO vo) throws Exception {
        return (MdmTnsrAsmblyBiVO)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyBiDAO.selectMdmBillView", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyBiVO> selectMdmBillDplctListCmmn(MdmTnsrAsmblyBiVO vo) throws Exception {
		return list("MdmTnsrAsmblyBiDAO.selectMdmBillDplctListCmmn", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmFileVO> selectMdmBillFileListCmmn(String BI_CN) throws Exception {
		return list("MdmTnsrAsmblyBiDAO.selectMdmBillFileListCmmn", BI_CN);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyBiFileVO> selectMdmBillFileList(MdmSearchVO vo) throws Exception {
        return list("MdmTnsrAsmblyBiDAO.selectMdmBillFileList", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyItncAsembyVO> selectMdmBillItncAsembyList(String cnId) throws Exception {
        return list("MdmTnsrAsmblyBiDAO.selectMdmBillItncAsembyList", cnId);
	}

	public void insertMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception {
        insert("MdmTnsrAsmblyBiDAO.insertMdmBill", vo);
    }

	public void insertMdmBillFile(MdmTnsrAsmblyBiFileVO vo) throws Exception {
        insert("MdmTnsrAsmblyBiDAO.insertMdmBillFile", vo);
    }

	public void insertMdmBillCm(List<MdmTnsrAsmblyItncAsembyVO> alist) throws Exception {
        insert("MdmTnsrAsmblyBiDAO.insertMdmBillCm", alist);
    }
	
    public void updateMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception {
        update("MdmTnsrAsmblyBiDAO.updateMdmBill", vo);
    }

    public void updateMdmBillIsView(MdmIsViewVO vo) throws Exception {
        update("MdmTnsrAsmblyBiDAO.updateMdmBillIsView", vo);
    }

    public void updateMdmBillFileListReCnvrs(String DOWNID) throws Exception {
        update("MdmTnsrAsmblyBiDAO.updateMdmBillFileListReCnvrs", DOWNID);
    }

    public void deleteMdmBillChk(MdmIsViewVO vo) throws Exception {
        update("MdmTnsrAsmblyBiDAO.deleteMdmBillChk", vo);
    }

    public void deleteMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception {
        update("MdmTnsrAsmblyBiDAO.deleteMdmBill", vo);
    }

    public void deleteMdmBillFile(String DOWNID) throws Exception {
        update("MdmTnsrAsmblyBiDAO.deleteMdmBillFile", DOWNID);
    }

    public void deleteMdmBillItncAsemby(String BI_CN) throws Exception {
        update("MdmTnsrAsmblyBiDAO.deleteMdmBillItncAsemby", BI_CN);
    }

}
