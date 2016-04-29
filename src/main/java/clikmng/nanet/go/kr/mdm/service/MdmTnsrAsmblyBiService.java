package clikmng.nanet.go.kr.mdm.service;

import java.util.List;

import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyItncAsembyVO;

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

public interface MdmTnsrAsmblyBiService {
    
    int selectMdmBillSeq() throws Exception;

    int selectMdmBillFileSeq() throws Exception;

	String selectMdmBillFileDownPathByBiCn(String DOWNID) throws Exception;

	String selectMdmBillFileDownPathByBiFileId(String DOWNID) throws Exception;

	String selectMdmBillFileExt(String BI_CN) throws Exception;

    String selectMdmBillMaxRegDate(String TODAY) throws Exception;
    
 	int selectMdmBillDfltListTotCnt(MdmSearchVO vo) throws Exception;

 	List<MdmTnsrAsmblyBiVO> selectMdmBillDfltList(MdmSearchVO vo) throws Exception;
	
	int selectMdmBillListTotCnt(MdmSearchVO vo) throws Exception;
    
    List<MdmTnsrAsmblyBiVO> selectMdmBillList(MdmSearchVO vo) throws Exception;
   
	int selectMdmBillCnvrsListTotCnt(MdmSearchVO vo) throws Exception;
    
    List<MdmTnsrAsmblyBiVO> selectMdmBillCnvrsList(MdmSearchVO vo) throws Exception;
    
	int selectMdmBillDplctListTotCnt(MdmSearchVO vo) throws Exception;
    
    List<MdmTnsrAsmblyBiVO> selectMdmBillDplctList(MdmSearchVO vo) throws Exception;

    MdmTnsrAsmblyBiVO selectMdmBillView(MdmSearchVO vo) throws Exception;

    List<MdmTnsrAsmblyBiVO> selectMdmBillDplctListCmmn(MdmTnsrAsmblyBiVO vo) throws Exception;
    
    List<MdmFileVO> selectMdmBillFileListCmmn(String BI_CN) throws Exception;

    List<MdmTnsrAsmblyBiFileVO> selectMdmBillFileList(MdmSearchVO vo) throws Exception;

    List<MdmTnsrAsmblyItncAsembyVO> selectMdmBillItncAsembyList(String cnId) throws Exception;
    
    void insertMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception;
    
    void insertMdmBillFile(MdmTnsrAsmblyBiFileVO vo) throws Exception;

    void insertMdmBillCm(List<MdmTnsrAsmblyItncAsembyVO> alist) throws Exception;
    
    void updateMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception;

    void updateMdmBillIsView(MdmIsViewVO vo) throws Exception;

    void updateMdmBillFileListReCnvrs(String DOWNID) throws Exception;
    
    void deleteMdmBillChk(MdmIsViewVO vo) throws Exception;

    void deleteMdmBill(MdmTnsrAsmblyBiVO vo) throws Exception;

    void deleteMdmBillFile(String DOWNID) throws Exception;

    void deleteMdmBillItncAsemby(String BI_CN) throws Exception;

}
