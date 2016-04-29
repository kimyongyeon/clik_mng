package clikmng.nanet.go.kr.cmm.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.sit.spc.service.SpcVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : EgovCmmUseServiceImpl.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
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
@Service("CmmUseService")
public class CmmUseServiceImpl extends AbstractServiceImpl implements CmmUseService {

    @Resource(name = "cmmUseDAO")
    private CmmUseDAO cmmUseDAO;

    /**
     * 공통코드를 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception {
	return cmmUseDAO.selectCmmCodeDetail(vo);
    }

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
     * 
     * @param voList
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List voList) throws Exception {
	ComDefaultCodeVO vo;
	Map<String, List<CmmnDetailCode>> map = new HashMap<String, List<CmmnDetailCode>>();

	Iterator iter = voList.iterator();
	while (iter.hasNext()) {
	    vo = (ComDefaultCodeVO)iter.next();
	    map.put(vo.getCodeId(), cmmUseDAO.selectCmmCodeDetail(vo));
	}

	return map;
    }

    /**
     * 조직정보를 코드형태로 리턴한다.
     * 
     * @param 조회조건정보 vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception {
	return cmmUseDAO.selectOgrnztIdDetail(vo);
    }

    /**
     * 그룹정보를 코드형태로 리턴한다.
     * 
     * @param 조회조건정보 vo
     * @return 그룹정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) throws Exception {
	return cmmUseDAO.selectGroupIdDetail(vo);
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
	return cmmUseDAO.selectInsttTyDetails(vo);
    }
    
    /**
     * 광역시정보를 코드형태로 리턴한다.
     * 
     * @param 조회조건정보 vo
     * @return 그룹정보 List
     * @throws Exception
     */
    public List<CmmnDetailCode> selectBrtcCodeDetails(CmmnDetailCode vo) throws Exception {
	return cmmUseDAO.selectBrtcCodeDetails(vo);
    }
    
    /**
     * 자치단체 및 의회 코드, 코드명 조회
     * @param 조회조건정보 vo
     * @return 글 목록
     * @throws Exception
     */
    public List<CmmnDetailCode> selectLoasmInfo(CmmnDetailCode vo) throws Exception {
        return cmmUseDAO.selectLoasmInfo(vo);
    }
    
    /**
     * 기관 목록을 조회한다.
     * @param 조회조건정보 vo
     * @return 기관 목록
     * @throws Exception
     */
    public List<CmmnDetailCode> selectLoasmInfoAll(CmmnDetailCode vo) throws Exception{
    	return cmmUseDAO.selectLoasmInfoAll(vo);
    }
    
    //--------------------------------------------- 지역 관련 끝-----------------------------------------------  
}
