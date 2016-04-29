package clikmng.nanet.go.kr.est.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.est.service.EstManageService;
import clikmng.nanet.go.kr.est.service.EstManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("EstManageService")
public class EstManageServiceImpl extends AbstractServiceImpl implements EstManageService {

    @Resource(name="EstManageDAO")
    private EstManageDAO estManageDAO;
        
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<EstManageVO> selectNtctAtList()  throws Exception{
		return estManageDAO.selectNtctAtList();
	}
	
	/**
     * 환경설정 - 자동/수동 변환 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<EstManageVO> selectCnvrAtList()  throws Exception{
		return estManageDAO.selectCnvrAtList();
	}
    
	
	/**
     * 환경설정 - 자동/수동 게시 수정 
     * @param 
     * @return 
     * @throws Exception
     */
	public void updateNtctAt(EstManageVO vo)  throws Exception {
		estManageDAO.updateNtctAt(vo);
	}
	
	/**
     * 환경설정 - 자동/수동 변환 수정
     * @param 
     * @return 
     * @throws Exception
     */
	public void updateCnvrAt(EstManageVO vo)  throws Exception {
		estManageDAO.updateCnvrAt(vo);
	}
	
}
