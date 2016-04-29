package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyItncAsembyVO;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyBiService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmTnsrAsmblyBiService")
public class MdmTnsrAsmblyBiServiceImpl extends AbstractServiceImpl implements MdmTnsrAsmblyBiService {

    @Resource(name="MdmTnsrAsmblyBiDAO")
    private MdmTnsrAsmblyBiDAO mdmTnsrAsmblyBiDAO;
        
    /** ID Generation */
    
/*	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
	public int selectMdmBillSeq() throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillSeq();
	}
	
	public int selectMdmBillFileSeq() throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillFileSeq();
	}

	public String selectMdmBillFileDownPathByBiCn(String DOWNID) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillFileDownPathByBiCn(DOWNID);
	}

	public String selectMdmBillFileDownPathByBiFileId(String DOWNID) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillFileDownPathByBiFileId(DOWNID);
	}

	public String selectMdmBillFileExt(String BI_CN) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillFileExt(BI_CN);
	}

	public String selectMdmBillMaxRegDate(String TODAY) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillMaxRegDate(TODAY);
	}

	public int selectMdmBillDfltListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillDfltListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyBiVO> selectMdmBillDfltList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillDfltList(vo);
	}
	
	public int selectMdmBillListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyBiVO> selectMdmBillList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillList(vo);
	}
	
	public int selectMdmBillCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillCnvrsListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyBiVO> selectMdmBillCnvrsList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillCnvrsList(vo);
	}
	
	public int selectMdmBillDplctListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillDplctListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyBiVO> selectMdmBillDplctList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillDplctList(vo);
	}
	
	public MdmTnsrAsmblyBiVO selectMdmBillView(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillView(vo);
	}
	
	public List<MdmTnsrAsmblyBiVO> selectMdmBillDplctListCmmn(MdmTnsrAsmblyBiVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillDplctListCmmn(vo);
	}

	public List<MdmFileVO> selectMdmBillFileListCmmn(String BI_CN) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillFileListCmmn(BI_CN);
	}

	public List<MdmTnsrAsmblyBiFileVO> selectMdmBillFileList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillFileList(vo);
	}

	public List<MdmTnsrAsmblyItncAsembyVO> selectMdmBillItncAsembyList(String cnId) throws Exception {
		return mdmTnsrAsmblyBiDAO.selectMdmBillItncAsembyList(cnId);
	}
	
	public void insertMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception {
        mdmTnsrAsmblyBiDAO.insertMdmBill(vo);
    }

	public void insertMdmBillFile(MdmTnsrAsmblyBiFileVO vo) throws Exception {
        mdmTnsrAsmblyBiDAO.insertMdmBillFile(vo);
    }

	public void insertMdmBillCm(List<MdmTnsrAsmblyItncAsembyVO> alist) throws Exception {
    	mdmTnsrAsmblyBiDAO.insertMdmBillCm(alist);
	}
	
	public void updateMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception {
        mdmTnsrAsmblyBiDAO.updateMdmBill(vo);
    }

	public void updateMdmBillIsView(MdmIsViewVO vo) throws Exception {
        mdmTnsrAsmblyBiDAO.updateMdmBillIsView(vo);
    }

	public void updateMdmBillFileListReCnvrs(String DOWNID) throws Exception {
        mdmTnsrAsmblyBiDAO.updateMdmBillFileListReCnvrs(DOWNID);
    }
	
	public void deleteMdmBillChk(MdmIsViewVO vo) throws Exception {
		mdmTnsrAsmblyBiDAO.deleteMdmBillChk(vo);
	}

	public void deleteMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception {
		mdmTnsrAsmblyBiDAO.deleteMdmBill(vo);
	}

	public void deleteMdmBillFile(String DOWNID) throws Exception {
		mdmTnsrAsmblyBiDAO.deleteMdmBillFile(DOWNID);
	}

	public void deleteMdmBillItncAsemby(String BI_CN) throws Exception {
		mdmTnsrAsmblyBiDAO.deleteMdmBillItncAsemby(BI_CN);
	}

}
