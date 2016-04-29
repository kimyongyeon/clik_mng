package clikmng.nanet.go.kr.csm.service;

import java.util.List;

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
public interface CsmManageService {
	
    /**
     * 기관유형 리스트
     * @param vo
     * @return	기관유형 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectOrgTypeList() throws Exception;
	
    /**
     * 수집기관 리스트
     * @param vo
     * @return	수집기관 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCollectionOrgList() throws Exception;
    /**
     * 기관별 수집 내역 리스트
     * @param vo
     * @return	기관별 수집 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCsmList(CsmManageVO vo) throws Exception;
	public int selectCsmListTotCnt(CsmManageVO vo) throws Exception;
	
    /**
     * 수집카테고리 리스트
     * @param vo
     * @return	수집카테고리 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCategoryList() throws Exception;

    /**
     * 수집대비 서비스 리스트
     * @param vo
     * @return	수집카테고리 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCollectionServiceList(CsmManageVO vo) throws Exception;
	
}
