package clikmng.nanet.go.kr.csm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.csm.service.CsmManageDefaultVO;
import clikmng.nanet.go.kr.csm.service.CsmManageService;
import clikmng.nanet.go.kr.csm.service.CsmManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 수집관리
 */
@Service("CsmManageService")
public class CsmManageServiceImpl extends AbstractServiceImpl implements CsmManageService {

    @Resource(name="CsmManageDAO")
    private CsmManageDAO csmManageDAO;
        
    /** ID Generation */
/*    
	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
    /**
     * 기관유형 리스트
     * @param vo
     * @return	기관유형 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectOrgTypeList() throws Exception {
		return csmManageDAO.selectOrgTypeList();
	}    
    
    
    /**
     * 수집기관 리스트
     * @param vo
     * @return	수집기관 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCollectionOrgList() throws Exception {
		return csmManageDAO.selectCollectionOrgList();
	}
    
    
    /**
	 * 기관별 수집 내역
	 * @param vo - 조회할 정보가 담긴 CsmManageVO
	 * @return 기관별 수집 내역 조회
	 * @exception Exception
	 */
    public List selectCsmList(CsmManageVO vo) throws Exception {
    	return csmManageDAO.selectCsmList(vo);
    }

    public int selectCsmListTotCnt(CsmManageVO vo) throws Exception {
    	return csmManageDAO.selectCsmListTotCnt(vo);
    }

    /**
     * 수집카테고리 리스트
     * @param vo
     * @return	수집카테고리 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCategoryList() throws Exception {
		return csmManageDAO.selectCategoryList();
	}
	
    /**
     * 수집대비 서비스 리스트
     * @param vo
     * @return	수집카테고리 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCollectionServiceList(CsmManageVO vo) throws Exception {
		return csmManageDAO.selectCollectionServiceList(vo);
	}
    
}
