package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyApndxVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMintsVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtrVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyNumPrVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblySesnVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblySpkngVO;

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

@Repository("MdmTnsrAsmblyMintsDAO")
public class MdmTnsrAsmblyMintsDAO extends EgovComAbstractDAO {

    public int selectMdmMinutesSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesSeq", null);
    }

    public int selectMdmAsmblyNumPr(MdmTnsrAsmblyMintsVO vo) throws Exception{
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmAsmblyNumPr", vo);
    }

    public int selectMdmAsmblySesn(MdmTnsrAsmblyMintsVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmAsmblySesn", vo);
    }

    public String selectMdmAsmblyMtgId(MdmTnsrAsmblyMintsVO vo) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmAsmblyMtgId", vo);
    }

    public String selectMdmAsmblyMaxMtgId(MdmTnsrAsmblyMintsVO vo) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmAsmblyMaxMtgId", vo);
    }
    
 	public String selectMdmMinutesMaxRegDate(String TODAY) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesMaxRegDate", TODAY);
	}

	public String selectMdmApndxFileExt(String MINTS_CN) throws Exception {
	    return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmApndxFileExt", MINTS_CN);
	}

 	public int selectMdmMinutesDfltListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesDfltListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMintsVO> selectMdmMinutesDfltList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesDfltList", vo);
	}
	
    public int selectMdmMinutesListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesListTotCnt", vo);
    }
    
    @SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMintsVO> selectMdmMinutesList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesList", vo);
	}
	
    public int selectMdmMinutesCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesCnvrsListTotCnt", vo);
    }
    
    @SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMintsVO> selectMdmMinutesCnvrsList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesCnvrsList", vo);
	}
    
    public MdmTnsrAsmblyMintsVO selectMdmMinutesView(String cnId) throws Exception {
        return (MdmTnsrAsmblyMintsVO)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesView", cnId);
    }

	@SuppressWarnings("unchecked")
	public List<MdmFileVO> selectMdmMinutesFileListCmmn(String MINTS_CN) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesFileListCmmn", MINTS_CN);
	}

    public MdmTnsrAsmblyMintsVO selectMdmMinutesHtml(MdmTnsrAsmblyMintsVO vo) throws Exception {
        return (MdmTnsrAsmblyMintsVO)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesHtml", vo);
    }
    
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblySpkngVO> selectMdmMinutesSpkngList(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesSpkngList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtrVO> selectMdmMinutesMtrList(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesMtrList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyApndxVO> selectMdmMinutesApndxList(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesApndxList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmDetailCodeRIS018", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmDetailCode", CODE_ID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmJrsdCmitId", vo);
	}

	public int selectMdmAppendixListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmAppendixListTotCnt", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyApndxVO> selectMdmAppendixList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmAppendixList", vo);
	}
	
    public String selectMdmMinutesViewTitle(MdmTnsrAsmblyMintsVO vo) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsDAO.selectMdmMinutesViewTitle", vo);
    }

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmMinutesMtgNmList(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		return list("MdmTnsrAsmblyMintsDAO.selectMdmMinutesMtgNmList", vo);
	}

	public void insertMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception {
        insert("MdmTnsrAsmblyMintsDAO.insertMdmMinutes", vo);
	}	

	public void insertMdmAsmblyNumPr(MdmTnsrAsmblyNumPrVO vo) throws Exception {
        insert("MdmTnsrAsmblyMintsDAO.insertMdmAsmblyNumPr", vo);
	}	

	public void insertMdmAsmblySesn(MdmTnsrAsmblySesnVO vo) throws Exception {
        insert("MdmTnsrAsmblyMintsDAO.insertMdmAsmblySesn", vo);
	}	

	public void insertMdmAsmblyMtgNm(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
        insert("MdmTnsrAsmblyMintsDAO.insertMdmAsmblyMtgNm", vo);
	}	
	

	public void updateMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.updateMdmMinutes", vo);
	}	

	public void updateMdmMinutesIsView(MdmIsViewVO vo) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.updateMdmMinutesIsView", vo);
	}	
	
    public void updateMdmMinutesFileListReCnvrs(String MINTS_CN) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.updateMdmMinutesFileListReCnvrs", MINTS_CN);
    }

	public void deleteMdmMinutesChk(MdmIsViewVO vo) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.deleteMdmMinutesChk", vo);
    }
	
    public void deleteMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.deleteMdmMinutes", vo);
    }

    public void deleteMdmMinutesFile(String MINTS_CN) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.deleteMdmMinutesFile", MINTS_CN);
    }

    public void deleteMdmMinutesAppFile(String APNDX_ID) throws Exception {
        update("MdmTnsrAsmblyMintsDAO.deleteMdmMinutesAppFile", APNDX_ID);
    }
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectRasmblyNumperList(String rasmblyNumper) throws Exception {
    	return list("MdmTnsrAsmblyMintsDAO.selectRasmblyNumperList", rasmblyNumper);
	}
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectRasmblySesnList(HashMap<String,Object> map) throws Exception {
    	return list("MdmTnsrAsmblyMintsDAO.selectRasmblySesnList", map);
	}
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectMtgnmList(HashMap<String,Object> map) throws Exception {
    	return list("MdmTnsrAsmblyMintsDAO.selectMtgnmList", map);
	}
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectItemList(HashMap<String,Object> map) throws Exception {
    	return list("MdmTnsrAsmblyMintsDAO.selectItemList", map);
	}
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectAsembyList(HashMap<String,Object> map) throws Exception {
    	return list("MdmTnsrAsmblyMintsDAO.selectAsembyList", map);
	}
}
