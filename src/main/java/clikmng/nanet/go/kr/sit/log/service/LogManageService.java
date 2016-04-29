package clikmng.nanet.go.kr.sit.log.service;

import java.util.List;
import java.util.Map;

import clikmng.nanet.go.kr.sym.log.clg.service.LoginLog;

/**
 * 
 * 사이트정보를 처리하는 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public interface LogManageService {
	
	/**
     * 접속 로그 목록 조회, 카운트
     * @param searchVO
     * @return 목록
     * @throws Exception
     */
	public Map selectConnectLogListInfo(LogManageVO vo) throws Exception;
	
	/**
     * 웹 로그 목록 조회, 카운트
     * @param searchVO
     * @return 목록
     * @throws Exception
     */
	public Map selectWebLogListInfo(LogManageVO vo) throws Exception;
	
}
