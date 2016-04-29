package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyMintsService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmTnsrAsmblyMintsService")
public class MdmTnsrAsmblyMintsServiceImpl extends AbstractServiceImpl implements MdmTnsrAsmblyMintsService {

    @Resource(name="MdmTnsrAsmblyMintsDAO")
    private MdmTnsrAsmblyMintsDAO mdmTnsrAsmblyMintsDAO;

    public int selectMdmMinutesSeq() throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesSeq();
	}

    public int selectMdmAsmblyNumPr(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmAsmblyNumPr(vo);
	}

    public int selectMdmAsmblySesn(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmAsmblySesn(vo);
	}

    public String selectMdmAsmblyMtgId(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmAsmblyMtgId(vo);
	}

    public String selectMdmAsmblyMaxMtgId(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmAsmblyMaxMtgId(vo);
	}

	public String selectMdmMinutesMaxRegDate(String TODAY) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesMaxRegDate(TODAY);
	}
	
	public String selectMdmApndxFileExt(String MINTS_CN) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmApndxFileExt(MINTS_CN);
	}

	public int selectMdmMinutesDfltListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesDfltListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyMintsVO> selectMdmMinutesDfltList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesDfltList(vo);
	}
    
    public int selectMdmMinutesListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesListTotCnt(vo);
	}

    public List<MdmTnsrAsmblyMintsVO> selectMdmMinutesList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesList(vo);
	}
    
	public int selectMdmMinutesCnvrsListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesCnvrsListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyMintsVO> selectMdmMinutesCnvrsList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesCnvrsList(vo);
	}

	public MdmTnsrAsmblyMintsVO selectMdmMinutesView(String cnId) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesView(cnId);
	}
	
	public List<MdmFileVO> selectMdmMinutesFileListCmmn(String MINTS_CN) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesFileListCmmn(MINTS_CN);
	}

	public MdmTnsrAsmblyMintsVO selectMdmMinutesHtml(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesHtml(vo);
	}

	public List<MdmTnsrAsmblySpkngVO> selectMdmMinutesSpkngList(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesSpkngList(vo);
	}

	public List<MdmTnsrAsmblyMtrVO> selectMdmMinutesMtrList(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesMtrList(vo);
	}

	public List<MdmTnsrAsmblyApndxVO> selectMdmMinutesApndxList(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesApndxList(vo);
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmDetailCodeRIS018();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmDetailCode(CODE_ID);
	}

	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmJrsdCmitId(vo);
	}

	public int selectMdmAppendixListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmAppendixListTotCnt(vo);
	}

	public List<MdmTnsrAsmblyApndxVO> selectMdmAppendixList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmAppendixList(vo);
	}

	public String selectMdmMinutesViewTitle(MdmTnsrAsmblyMintsVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesViewTitle(vo);
	}

	public List<MdmTnsrAsmblyMtgNmVO> selectMdmMinutesMtgNmList(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMdmMinutesMtgNmList(vo);
	}

	public void insertMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.insertMdmMinutes(vo);
	}

	public void insertMdmAsmblyNumPr(MdmTnsrAsmblyNumPrVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.insertMdmAsmblyNumPr(vo);
	}
	
	public void insertMdmAsmblySesn(MdmTnsrAsmblySesnVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.insertMdmAsmblySesn(vo);
	}
	
	public void insertMdmAsmblyMtgNm(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.insertMdmAsmblyMtgNm(vo);
	}

	public void updateMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.updateMdmMinutes(vo);
	}

	public void updateMdmMinutesIsView(MdmIsViewVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.updateMdmMinutesIsView(vo);
	}

	public void updateMdmMinutesFileListReCnvrs(String MINTS_CN) throws Exception {
		mdmTnsrAsmblyMintsDAO.updateMdmMinutesFileListReCnvrs(MINTS_CN);
	}
	
	public void deleteMdmMinutesChk(MdmIsViewVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.deleteMdmMinutesChk(vo);
	}

	public void deleteMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception {
		mdmTnsrAsmblyMintsDAO.deleteMdmMinutes(vo);
	}

	public void deleteMdmMinutesFile(String MINTS_CN) throws Exception {
		mdmTnsrAsmblyMintsDAO.deleteMdmMinutesFile(MINTS_CN);
	}

	public void deleteMdmMinutesAppFile(String APNDX_ID) throws Exception {
		mdmTnsrAsmblyMintsDAO.deleteMdmMinutesAppFile(APNDX_ID);
	}

	public List<HashMap<String,Object>> selectRasmblyNumperList(String rasmblyNumper) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectRasmblyNumperList(rasmblyNumper);
	}
	
	public List<HashMap<String,Object>> selectRasmblySesnList(HashMap<String,Object> map) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectRasmblySesnList(map);
	}
	
	public List<HashMap<String,Object>> selectMtgnmList(HashMap<String,Object> map) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectMtgnmList(map);
	}
	
	public List<HashMap<String,Object>> selectItemList(HashMap<String,Object> map) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectItemList(map);
	}
	
	public List<HashMap<String,Object>> selectAsembyList(HashMap<String,Object> map) throws Exception {
		return mdmTnsrAsmblyMintsDAO.selectAsembyList(map);
	}
}
