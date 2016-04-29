package clikmng.nanet.go.kr.est.service;

import java.util.List;


/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public interface EstManageService {
	
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param 
     * @return 
     * @throws Exception
     */
	public List<EstManageVO> selectNtctAtList()  throws Exception;
	
	/**
     * 환경설정 - 자동/수동 변환 목록 
     * @param 
     * @return 
     * @throws Exception
     */
	public List<EstManageVO> selectCnvrAtList()  throws Exception;
	
	/**
     * 환경설정 - 자동/수동 게시 수정 
     * @param 
     * @return 
     * @throws Exception
     */
	public void updateNtctAt(EstManageVO vo)  throws Exception;
	
	/**
     * 환경설정 - 자동/수동 변환 수정
     * @param 
     * @return 
     * @throws Exception
     */
	public void updateCnvrAt(EstManageVO vo)  throws Exception;
	    
}
