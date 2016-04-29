package clikmng.nanet.go.kr.est.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.est.service.EstManageVO;

/**
 * 
 * 환경설정
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
@Repository("EstManageDAO")
public class EstManageDAO extends EgovComAbstractDAO {
	
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<EstManageVO> selectNtctAtList()  throws Exception{
		return list("estManageDAO.selectNtctAtList", null);
	}
	
	/**
     * 환경설정 - 자동/수동 변환 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<EstManageVO> selectCnvrAtList()  throws Exception{
		return list("estManageDAO.selectCnvrAtList", null);
	}	

	/**
     * 환경설정 - 자동/수동 게시 수정 
     * @param 
     * @return 
     * @throws Exception
     */
	public void updateNtctAt(EstManageVO vo)  throws Exception {
		update ("estManageDAO.updateNtctAt", vo);
	}
	
	/**
     * 환경설정 - 자동/수동 변환 수정
     * @param 
     * @return 
     * @throws Exception
     */
	public void updateCnvrAt(EstManageVO vo)  throws Exception {
		update ("estManageDAO.updateCnvrAt", vo);
	}
	
	
	
}
