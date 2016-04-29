package clikmng.nanet.go.kr.mdm.service;

import java.util.HashMap;
import java.util.List;

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

public interface MdmTnsrAsmblyMintsService {
	
	int selectMdmMinutesSeq() throws Exception;
	
    int selectMdmAsmblyNumPr(MdmTnsrAsmblyMintsVO vo) throws Exception;

    int selectMdmAsmblySesn(MdmTnsrAsmblyMintsVO vo) throws Exception;

    String selectMdmAsmblyMtgId(MdmTnsrAsmblyMintsVO vo) throws Exception;

    String selectMdmAsmblyMaxMtgId(MdmTnsrAsmblyMintsVO vo) throws Exception;

    String selectMdmMinutesMaxRegDate(String TODAY) throws Exception;
    
    String selectMdmApndxFileExt(String MINTS_CN) throws Exception;
    
 	int selectMdmMinutesDfltListTotCnt(MdmSearchVO vo) throws Exception;

 	List<MdmTnsrAsmblyMintsVO> selectMdmMinutesDfltList(MdmSearchVO vo) throws Exception;
 	
    int selectMdmMinutesListTotCnt(MdmSearchVO vo) throws Exception;

    List<MdmTnsrAsmblyMintsVO> selectMdmMinutesList(MdmSearchVO vo) throws Exception;
    
 	int selectMdmMinutesCnvrsListTotCnt(MdmSearchVO vo) throws Exception;

 	List<MdmTnsrAsmblyMintsVO> selectMdmMinutesCnvrsList(MdmSearchVO vo) throws Exception;
 	
    MdmTnsrAsmblyMintsVO selectMdmMinutesView(String cnId) throws Exception;

    List<MdmFileVO> selectMdmMinutesFileListCmmn(String MINTS_CN) throws Exception;

    MdmTnsrAsmblyMintsVO selectMdmMinutesHtml(MdmTnsrAsmblyMintsVO vo) throws Exception;
    
	List<MdmTnsrAsmblySpkngVO> selectMdmMinutesSpkngList(MdmTnsrAsmblyMintsVO vo) throws Exception;

	List<MdmTnsrAsmblyMtrVO> selectMdmMinutesMtrList(MdmTnsrAsmblyMintsVO vo) throws Exception;
	
	List<MdmTnsrAsmblyApndxVO> selectMdmMinutesApndxList(MdmTnsrAsmblyMintsVO vo) throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception;
    
    List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception;
    
    int selectMdmAppendixListTotCnt(MdmSearchVO vo) throws Exception;
    
    List<MdmTnsrAsmblyApndxVO> selectMdmAppendixList(MdmSearchVO vo) throws Exception;

    String selectMdmMinutesViewTitle(MdmTnsrAsmblyMintsVO vo) throws Exception;

    List<MdmTnsrAsmblyMtgNmVO> selectMdmMinutesMtgNmList(MdmTnsrAsmblyMtgNmVO vo) throws Exception;

    void insertMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception;

    void insertMdmAsmblyNumPr(MdmTnsrAsmblyNumPrVO vo) throws Exception;
    
    void insertMdmAsmblySesn(MdmTnsrAsmblySesnVO vo) throws Exception;
    
    void insertMdmAsmblyMtgNm(MdmTnsrAsmblyMtgNmVO vo) throws Exception;

    void updateMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception;

    void updateMdmMinutesIsView(MdmIsViewVO vo) throws Exception;
    
    void updateMdmMinutesFileListReCnvrs(String MINTS_CN) throws Exception;
    
    void deleteMdmMinutesChk(MdmIsViewVO vo) throws Exception;

    void deleteMdmMinutes(MdmTnsrAsmblyMintsVO vo) throws Exception;

    void deleteMdmMinutesFile(String MINTS_CN) throws Exception;
    
    void deleteMdmMinutesAppFile(String APNDX_ID) throws Exception;
    
    List<HashMap<String,Object>> selectRasmblyNumperList(String rasmblyNumper) throws Exception;
    
    List<HashMap<String,Object>> selectRasmblySesnList(HashMap<String,Object> map) throws Exception;
    
    List<HashMap<String,Object>> selectMtgnmList(HashMap<String,Object> map) throws Exception;
	
	List<HashMap<String,Object>> selectItemList(HashMap<String,Object> map) throws Exception;
	
	List<HashMap<String,Object>> selectAsembyList(HashMap<String,Object> map) throws Exception;
}
