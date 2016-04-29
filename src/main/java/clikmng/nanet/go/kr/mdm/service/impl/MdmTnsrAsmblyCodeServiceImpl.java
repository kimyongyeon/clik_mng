package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnmPprtyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttCodeEstbsVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttclVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmTnsrAsmblyCodeService")
public class MdmTnsrAsmblyCodeServiceImpl extends AbstractServiceImpl implements MdmTnsrAsmblyCodeService {

    @Resource(name="MdmTnsrAsmblyCodeDAO")
    private MdmTnsrAsmblyCodeDAO mdmTnsrAsmblyCodeDAO;
        
    /** ID Generation */
    
/*	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCode(CODE_ID);
	}

	public List<String> selectMdmMtgIdList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmMtgIdList(vo);
	}
	
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmJrsdCmitId(vo);
	}
	
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitIdList(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmJrsdCmitIdList(vo);
	}

	public List<String> selectMdmJrsdCmitIdList(String cmitNm) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnsrAsmblyJrsdCmitIdList(cmitNm);
	}

	public List<MdmTnmPprtyVO> selectMdmTnmPprty(String END_DE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnmPprty(END_DE);
	}
	public List<MdmTnpInsttCodeEstbsVO> selectMdmTnpInsttCodeEstbs(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnpInsttCodeEstbs(vo);
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS018();
	}

	public String selectMdmDetailCodeRIS018ByCode(String CODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS018ByCode(CODE);
	}
	
	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS020() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS020();
	}

	public String selectMdmDetailCodeRIS020ByCode(String CODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS020ByCode(CODE);
	}

	public String selectMdmDetailCodeRIS020ByNm(String CODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS020ByNm(CODE);
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS002() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS002();
	}
	
	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS021All() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS021All();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS022() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS022();
	}

	public String selectMdmDetailCodeRKS025ByCodeNm(String CODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS025ByCodeNm(CODE);
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS025() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS025();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS026() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS026();
	}

	public String selectMdmDetailCodeRKS026ByCodeNm(String CODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS026ByCodeNm(CODE);
	}

	public List<MdmSearchVO> selectMdmSearchCode(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmSearchCode(vo);
	}
	
	public String selectMdmAsembyCode(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmAsembyCode(vo);
	}

	public String selectMdmTnsrAsmblyEstCode(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnsrAsmblyEstCode(vo);
	}
		
	public List<String> selectMdmTnsrAsmblyEstCodeList(String estNm) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnsrAsmblyEstCodeList(estNm);
	}

	public List<MdmTnpInsttclVO> selectMdmTnpInsttcl1() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnpInsttcl1();
	}
	
	public List<MdmTnpInsttclVO> selectMdmTnpInsttcl2(String schInsttClCode2) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnpInsttcl2(schInsttClCode2);
	}

	public List<MdmTnpInsttclVO> selectMdmTnpInsttcl3(String schInsttClCode3) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmTnpInsttcl3(schInsttClCode3);
	}

	public String selectMdmSiteId(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmSiteId(vo);
	}

	public List<MdmOrgCodeVO> selectMdmOrgCodeStep1List() throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmOrgCodeStep1List();
	}

	public List<MdmOrgCodeVO> selectMdmOrgCodeStep2List(String ORGCODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmOrgCodeStep2List(ORGCODE);
	}

	public List<MdmOrgCodeVO> selectMdmOrgCodeStep3List(String ORGCODE) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmOrgCodeStep3List(ORGCODE);
	}
	
	public List<MdmOrgCodeVO> selectMdmOrgSiteList(MdmSearchVO mdmSearchVO) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmOrgSiteList(mdmSearchVO);
	}
	
	public String selectMdmFileExt(String EXTID) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmFileExt(EXTID);
	}

	public List<MdmOutSiteVO> selectMdmSiteList(String REGION) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmSiteList(REGION);
	}

	public List<MdmOutSeedVO> selectMdmSeedList(MdmOutSeedVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectMdmSeedList(vo);
	}
	
    // -----------------  의회 및 지역 선택 리스트
	public List<String> selectLoAsmTyCodeList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectLoAsmTyCodeList(vo);
	}
	
	public List<String> selectRegionList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyCodeDAO.selectRegionList(vo);
	}
	
}