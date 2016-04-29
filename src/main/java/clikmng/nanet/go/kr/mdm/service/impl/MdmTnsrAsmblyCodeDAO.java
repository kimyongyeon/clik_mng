package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
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

@Repository("MdmTnsrAsmblyCodeDAO")
public class MdmTnsrAsmblyCodeDAO extends EgovComAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCode", CODE_ID);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectMdmMtgIdList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmMtgIdList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmJrsdCmitId", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitIdList(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmJrsdCmitIdList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectMdmTnsrAsmblyJrsdCmitIdList(String cmitNm) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmJrsdCmitIdList", cmitNm);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnmPprtyVO> selectMdmTnmPprty(String END_DE) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmTnmPprty", END_DE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnpInsttCodeEstbsVO> selectMdmTnpInsttCodeEstbs(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmTnpInsttCodeEstbs", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS018", null);
	}

	public String selectMdmDetailCodeRIS018ByCode(String CODE) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS018ByCode", CODE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS020() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS020", null);
	}
	
	public String selectMdmDetailCodeRIS020ByCode(String CODE) throws Exception {
	    return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS020ByCode", CODE);
	}

	public String selectMdmDetailCodeRIS020ByNm(String CODE) throws Exception {
	    return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRIS020ByNm", CODE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS002() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS002", null);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS021All() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS021All", null);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS022() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS022", null);
	}

	public String selectMdmDetailCodeRKS025ByCodeNm(String CODE) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS025ByCodeNm", CODE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS025() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS025", null);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRKS026() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS026", null);
	}

	public String selectMdmDetailCodeRKS026ByCodeNm(String CODE) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmDetailCodeRKS026ByCodeNm", CODE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmSearchVO> selectMdmSearchCode(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmSearchCode", vo);
	}

	public String selectMdmAsembyCode(MdmSearchVO vo) throws Exception {
       return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmAsembyCode", vo);

	}

	public String selectMdmTnsrAsmblyEstCode(MdmSearchVO vo) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmTnsrAsmblyEstCode", vo);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectMdmTnsrAsmblyEstCodeList(String estNm) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmTnsrAsmblyEstCodeList", estNm);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmTnpInsttclVO> selectMdmTnpInsttcl1() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmTnpInsttcl1", null);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnpInsttclVO> selectMdmTnpInsttcl2(String schInsttClCode2) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmTnpInsttcl2", schInsttClCode2);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnpInsttclVO> selectMdmTnpInsttcl3(String schInsttClCode3) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmTnpInsttcl3", schInsttClCode3);
	}

	public String selectMdmSiteId(MdmSearchVO vo) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmSiteId", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOrgCodeVO> selectMdmOrgCodeStep1List() throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmOrgCodeStep1List", null);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOrgCodeVO> selectMdmOrgCodeStep2List(String ORGCODE) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmOrgCodeStep2List", ORGCODE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOrgCodeVO> selectMdmOrgCodeStep3List(String ORGCODE) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmOrgCodeStep3List", ORGCODE);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOrgCodeVO> selectMdmOrgSiteList(MdmSearchVO mdmSearchVO) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmOrgSiteList", mdmSearchVO);
	}
	
	public String selectMdmFileExt(String EXTID) throws Exception {
	    return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyCodeDAO.selectMdmFileExt", EXTID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutSiteVO> selectMdmSiteList(String REGION) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmSiteList", REGION);
	}

	@SuppressWarnings("unchecked")
	public List<MdmOutSeedVO> selectMdmSeedList(MdmOutSeedVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectMdmSeedList", vo);
	}

    // -----------------  의회 및 지역 선택 리스트
	@SuppressWarnings("unchecked")
	public List<String> selectLoAsmTyCodeList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectLoAsmTyCodeList", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> selectRegionList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyCodeDAO.selectRegionList", vo);
	}
	
}
