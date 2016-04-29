package clikmng.nanet.go.kr.sts.clt.service;

import java.util.List;

import clikmng.nanet.go.kr.sts.clt.service.CltManageVO;

/**
 * 
 * 수집통계를 처리하는 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */
public interface CltManageService {
	 
	/**
	 * 수집통계 - 기간별
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List selectCltStsList(CltManageVO CltManageVO) throws Exception;
	
	/**
	 * 수집통계 - 기간합산
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List selectCltStsTermSumList(CltManageVO CltManageVO) throws Exception;
}
