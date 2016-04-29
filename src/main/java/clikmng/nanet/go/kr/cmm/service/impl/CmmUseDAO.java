package clikmng.nanet.go.kr.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;

/**
 * @Class Name : CmmUseDAO.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *
 * @author 
 * @since 
 * @version
 * @see
 *
 */
@Repository("cmmUseDAO")
public class CmmUseDAO extends EgovComAbstractDAO {

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception {
	return list("CmmUseDAO.selectCmmCodeDetail", vo);
    }

    /**
     * 공통코드로 사용할 조직정보를 를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception {
	return list("CmmUseDAO.selectOgrnztIdDetail", vo);
    }

    /**
     * 공통코드로 사용할그룹정보를 를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) throws Exception {
	return list("CmmUseDAO.selectGroupIdDetail", vo);
    }
    
    //--------------------------------------------- 기관 및 지역 관련 시작-----------------------------------------------
    /**
     *  기관유형을 코드형태로 리턴한다
     * 
     * @param 조회조건정보 vo
     * @return 그룹정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectInsttTyDetails(CmmnDetailCode vo) throws Exception {
    	return list("CmmUseDAO.selectInsttTyDetails", vo);
    }
    
    
    /**
     * 광역시정보를 코드형태로 리턴한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectBrtcCodeDetails(CmmnDetailCode vo) throws Exception {
	return list("CmmUseDAO.selectBrtcCodeDetails", vo);
    }    
    
    
    /**
     * 지방의회 코드를 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<CmmnDetailCode> selectLoasmInfo(CmmnDetailCode vo) throws Exception {
    	return list("CmmUseDAO.selectLoasmInfo", vo);
    }
    
    /**
     * 기관 목록을 조회한다.
     * @param 조회조건정보 vo
     * @return 기관 목록
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<CmmnDetailCode> selectLoasmInfoAll(CmmnDetailCode vo) throws Exception{
    	return list("CmmUseDAO.selectLoasmInfoAll", vo);
    }
    //--------------------------------------------- 지역 관련 끝-----------------------------------------------      
}
