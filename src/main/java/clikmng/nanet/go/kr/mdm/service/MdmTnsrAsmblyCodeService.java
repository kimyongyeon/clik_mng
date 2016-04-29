package clikmng.nanet.go.kr.mdm.service;

import java.util.List;

import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnmPprtyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttCodeEstbsVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttclVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;

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

public interface MdmTnsrAsmblyCodeService {

    List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception;
    
    List<String> selectMdmMtgIdList(MdmSearchVO vo) throws Exception;
    
    List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmSearchVO vo) throws Exception;

    List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitIdList(MdmTnsrAsmblyMtgNmVO vo) throws Exception;
    
    List<String> selectMdmJrsdCmitIdList(String cmitNm) throws Exception;

	List<MdmTnmPprtyVO> selectMdmTnmPprty(String END_DE) throws Exception;

    List<MdmTnpInsttCodeEstbsVO> selectMdmTnpInsttCodeEstbs(MdmSearchVO vo) throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception;

    String selectMdmDetailCodeRIS018ByCode(String CODE) throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRIS020() throws Exception;

    String selectMdmDetailCodeRIS020ByCode(String CODE) throws Exception;

    String selectMdmDetailCodeRIS020ByNm(String CODE) throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRKS002() throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRKS021All() throws Exception;

    List<MdmDetailCodeVO> selectMdmDetailCodeRKS022() throws Exception;

    String selectMdmDetailCodeRKS025ByCodeNm(String CODE) throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCodeRKS025() throws Exception;

    String selectMdmDetailCodeRKS026ByCodeNm(String CODE) throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCodeRKS026() throws Exception;

    List<MdmSearchVO> selectMdmSearchCode(MdmSearchVO vo) throws Exception;

    String selectMdmAsembyCode(MdmSearchVO vo) throws Exception;

    String selectMdmTnsrAsmblyEstCode(MdmSearchVO vo) throws Exception;

    List<String> selectMdmTnsrAsmblyEstCodeList(String estNm) throws Exception;

    List<MdmTnpInsttclVO> selectMdmTnpInsttcl1() throws Exception;

    List<MdmTnpInsttclVO> selectMdmTnpInsttcl2(String schInsttClCode2) throws Exception;

    List<MdmTnpInsttclVO> selectMdmTnpInsttcl3(String schInsttClCode3) throws Exception;
    
    String selectMdmSiteId(MdmSearchVO vo) throws Exception;

    List<MdmOrgCodeVO> selectMdmOrgCodeStep1List() throws Exception;

    List<MdmOrgCodeVO> selectMdmOrgCodeStep2List(String ORGCODE) throws Exception;

    List<MdmOrgCodeVO> selectMdmOrgCodeStep3List(String ORGCODE) throws Exception;
    
    // 기관(사이트리스트)
    List<MdmOrgCodeVO> selectMdmOrgSiteList(MdmSearchVO mdmSearchVO) throws Exception;
    
    String selectMdmFileExt(String EXTID) throws Exception;

    List<MdmOutSiteVO> selectMdmSiteList(String REGION) throws Exception;

    List<MdmOutSeedVO> selectMdmSeedList(MdmOutSeedVO vo) throws Exception;
    
    // -----------------  의회 및 지역 선택 리스트
    List<String> selectLoAsmTyCodeList(MdmSearchVO vo) throws Exception;	// 의회
    List<String> selectRegionList(MdmSearchVO vo) throws Exception;			// 지역

}
