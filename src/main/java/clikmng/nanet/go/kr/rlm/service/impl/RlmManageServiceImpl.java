package clikmng.nanet.go.kr.rlm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.rlm.service.CodeVO;
import clikmng.nanet.go.kr.rlm.service.HrsmnpdPprtyVO;
import clikmng.nanet.go.kr.rlm.service.HrsmnpdVO;
import clikmng.nanet.go.kr.rlm.service.PprtyVO;
import clikmng.nanet.go.kr.rlm.service.RasmblyNumprPdVO;
import clikmng.nanet.go.kr.rlm.service.RasmblyVO;
import clikmng.nanet.go.kr.rlm.service.RlmManageDefaultVO;
import clikmng.nanet.go.kr.rlm.service.RlmManageService;
import clikmng.nanet.go.kr.rlm.service.RlmManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("RlmManageService")
public class RlmManageServiceImpl extends AbstractServiceImpl implements RlmManageService {

    @Resource(name="RlmManageDAO")
    private RlmManageDAO RlmManageDAO;
        
    /** ID Generation */
/*    
	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/

    /**
     * 테이블의 다음PK 값을 가져온다.
     * @param tableName : 조회할 테이블명
     * @return next PK 값
     * @throws SQLException 
     * */
    public String getNextPKValue(String tableName) throws SQLException{
    	return RlmManageDAO.getNextPKValue(tableName);
    }
    
    /**
     * 테이블의 다음PK 값으로 설정한다.
     * @param tableName : 설정할 테이블명
     * @throws SQLException 
     * */
    public void setNextPKValue(String tableName) throws SQLException{
    	RlmManageDAO.setNextPKValue(tableName);
    }
    
    /**
	 * 공통코드를 조회한다.
	 * @param code_id
	 * @return 공통코드 목록
	 * @throws Exception
	 * */
	public List<CodeVO> getCommCodeList(String codeId) throws Exception{
		return RlmManageDAO.getCommCodeList(codeId);
	}
	
    /**
	 * 지방의회 수집 API Key 목록을 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public List<RasmblyVO> selectRasmblyApiKeyList(RlmManageDefaultVO searchVO) throws Exception{
		return RlmManageDAO.selectRasmblyApiKeyList(searchVO);
	}
	public int selectRasmblyApiKeyListCnt(RlmManageDefaultVO searchVO) throws Exception{
		return RlmManageDAO.selectRasmblyApiKeyListCnt(searchVO);
	}
	
	/**
     * 지방의회 연계관리 - 수집 API Key 삭제
     * @param 삭제 할 API Key 리스트
     * @param model
     * @return	"clikMng/rlm/CollectionApiList"
     * @throws Exception
     */
	public int deleteRasmblyApiKey(String param) throws Exception{
		return RlmManageDAO.deleteRasmblyApiKey(param);
	}
	
	/**
     * 지방의회 연계관리 - 수집 API Key 저장
     * @param 등록 할 API Key 정보
     * @throws Exception
     */
	public void insertRasmblyApiKey(RasmblyVO vo) throws Exception{
		RlmManageDAO.insertRasmblyApiKey(vo);
	}
	
	/**
     * 지방의회 연계관리 - 수집 API Key 수정
     * @param 수정 할 API Key 정보
     * @param model
     * @return 처리수
     * @throws Exception
     */
	public int updateRasmblyApiKey(RasmblyVO vo) throws Exception{
		return RlmManageDAO.updateRasmblyApiKey(vo);
	}
	
	/**
	 * 지방의회 수집 API Key를 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public RasmblyVO selectRasmblyApiKey(RasmblyVO vo) throws Exception{
		return RlmManageDAO.selectRasmblyApiKey(vo);
	}
	
	/**
	 * 기수 목록을 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public List<HrsmnpdVO> selectGenerationFlagList(RlmManageDefaultVO vo) throws Exception{
		return RlmManageDAO.selectGenerationFlagList(vo);
	}
	
	/**
	 * 기수 상세정보를 조회한다.
	 * @param HrsmnpdVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public HrsmnpdVO selectGenerationFlag(HrsmnpdVO vo) throws Exception{
		return RlmManageDAO.selectGenerationFlag(vo);
	}
	
	/**
	 * 기수 매핑 의회 목록을 조회한다.
	 * @param HrsmnpdVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public List<RasmblyNumprPdVO> selectRasmblyNumprPdList(HrsmnpdVO vo) throws Exception{
		return RlmManageDAO.selectRasmblyNumprPdList(vo);
	}
	
	/**
	 * 지방의회 연계관리 - 대수/기수 새로운 맵핑 정보를 조회한다.
     * @param HrsmnpdVO
     * @param model
     * @return 조회결과
     * @throws Exception
	 * */
	public List<HashMap<String,Object>> selectNumprMapping(HrsmnpdVO vo) throws Exception{
		return RlmManageDAO.selectNumprMapping(vo);
	}
	
	/**
	 * 기수 기간 정보를 등록한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public void insertHrsmnpd(HrsmnpdVO vo) throws Exception{
		RlmManageDAO.insertHrsmnpd(vo);
	}
	
	/**
	 * 기수 기간 정보를 수정한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int updateHrsmnpd(HrsmnpdVO vo) throws Exception{
		return RlmManageDAO.updateHrsmnpd(vo);
	}
	
	/**
	 * 기수 기간 정보를 삭제한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int deleteHrsmnpd(HrsmnpdVO vo) throws Exception{
		return RlmManageDAO.deleteHrsmnpd(vo);
	}
	
	/**
	 * 대수 기간 정보를 등록한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public void insertNumprpd(RasmblyNumprPdVO vo) throws Exception{
		RlmManageDAO.insertNumprpd(vo);
	}
	
	/**
	 * 대수 기간 정보를 수정한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int updateNumprpd(RasmblyNumprPdVO vo) throws Exception{
		return RlmManageDAO.updateNumprpd(vo);
	}
	
	/**
	 * 기수번호에 해당하는 대수 기간 정보를 삭제한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int deleteNumprpd(HrsmnpdVO vo) throws Exception{
		return RlmManageDAO.deleteNumprpd(vo);
	}
	
	/**
	 * 기수별 정당 정보 목록을 조회한다.
	 * @param 
	 * @return 기수별 정당 목록
	 * @throws Exception
	 * */
	public List<HrsmnpdPprtyVO> selectHrsmnPprtyMngList() throws Exception{
		return RlmManageDAO.selectHrsmnPprtyMngList();
	}
	
	/**
	 * 기수별 정당 상세정보를 조회한다.
	 * @param 
	 * @return 기수별 정당 상세정보
	 * @throws Exception
	 * */
	public List<HrsmnpdPprtyVO> selectHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		return RlmManageDAO.selectHrsmnPprtyMng(vo);
	}
	
	/**
	 * 기수별 정당 정보를 등록한다.
	 * @param HrsmnpdPprtyVO
	 * @return 
	 * @throws Exception
	 * */
	public void insertHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		RlmManageDAO.insertHrsmnPprtyMng(vo);
	}
	
	/**
	 * 기수별 정당 정보를 수정한다.
	 * @param HrsmnpdPprtyVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int updateHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		return RlmManageDAO.updateHrsmnPprtyMng(vo);
	}
	
	/**
	 * 기수별 정당 정보를 삭제한다.
	 * @param HrsmnpdPprtyVO
	 * @return 처리 결과
	 * @throws Exception
	 * */
	public int deleteHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		return RlmManageDAO.deleteHrsmnPprtyMng(vo);
	}
	
	/**
	 * 지방의회 정당 목록을 조회한다.
	 * @param RlmManageDefaultVO
	 * @return 정당 목록
	 * @throws Exception
	 * */
	public List<PprtyVO> selectPprtyList(RlmManageDefaultVO vo) throws Exception{
		return RlmManageDAO.selectPprtyList(vo);
	}
	
	/**
	 * 연계API 수집 내역
	 * @param RlmManageVO
	 * @return 연계 API 수집 내역
	 * @throws Exception
	 * */
    public Map<String, Object> selectCollectionInfoList(RlmManageVO vo) throws Exception {
    	
		List<RlmManageVO> _result = RlmManageDAO.selectCollectionInfoList(vo);
		int _cnt = RlmManageDAO.selectCollectionInfoCnt(vo);
		 
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}	
	
	/**
	 * 연계의회 목록
	 * @param RlmManageVO
	 * @return 연계의회 목록
	 * @throws Exception
	 * */
	public List<RlmManageVO> selectRasmblyList() throws Exception {
		return RlmManageDAO.SelectRasmblyList();
	}
	
	/**
	 * 연계API 구분
	 * @param RlmManageVO
	 * @return 연계API 구분
	 * @throws Exception
	 * */
	public List<RlmManageVO> selectApiList() throws Exception {
		return RlmManageDAO.SelectApiList();
	}
	
	/**
	 * 표준연계 API 수집요청 목록을 조회한다.
	 * @param RlmManageVO
	 * @return 표준연계 API 수집요청 목록
	 * @throws Exception
	 * */
	public List<HashMap<String,Object>> selectStdCntcApiColctMngList(RlmManageVO searchVO){
		return RlmManageDAO.selectStdCntcApiColctMngList(searchVO);
	}
	
	/**
	 * 통계정보화면 - 검색 정보 유지를 위한 의회 정보를 조회한다.
	 * @param 의회아이디
	 * @return 의회 정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectRasmblyInfo(HashMap<String,Object> map) throws Exception{
    	return RlmManageDAO.selectRasmblyInfo(map);
    }
    
    /**
	 * 표준연계 API 수집요청 상세정보를 조회한다.
	 * @param 
	 * @return 표준연계api 상세정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectStdCntcApiColctMngDetail(HashMap<String,Object> map) throws Exception{
    	return RlmManageDAO.selectStdCntcApiColctMngDetail(map);
    }
    
    /**
	 * 표준연계 API 수집요청 상세정보를 삭제한다.
	 * @param 
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int DeleteStdCntcApiColct(HashMap<String,Object> map) throws Exception{
    	return RlmManageDAO.DeleteStdCntcApiColct(map);
    }
    
    /**
	 * 표준연계 API 수집요청 상세정보를 등록한다.
	 * @param 재수집요청 상세정보
	 * @return 
	 * @exception Exception
	 */    
    public void insertStdCntcApiColct(HashMap<String,Object> map) throws Exception{
    	RlmManageDAO.insertStdCntcApiColct(map);
    }
    
    /**
	 * 표준연계 API 모니터링 정보를 조회한다.
	 * @param 
	 * @return 지방의회 연계  정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectStdCntcApiColctMntrng() throws Exception{
    	return RlmManageDAO.selectStdCntcApiColctMntrng();
    }
    
    /**
	 * 연계 API 관리 - 승격 지방의회 정보를 수정한다.
	 * @param RasmblyVO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int updatePrmtRasmbly(RasmblyVO vo) throws Exception{
    	return RlmManageDAO.updatePrmtRasmbly(vo);
    }
    
    /**
	 * 연계 API 관리 - 승격 지방의회 정보를 emdfhr한다.
	 * @param RasmblyVO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public void insertPrmtRasmbly(RasmblyVO vo) throws Exception{
    	RlmManageDAO.insertPrmtRasmbly(vo);
    }
    
    /**
	 * 연계 API 관리 - 승격 지방의회 정보를 삭제한다.
	 * @param RasmblyVO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int deletePrmtRasmbly(RasmblyVO vo) throws Exception{
    	return RlmManageDAO.deletePrmtRasmbly(vo);
    }
    
    /**
	 * 지방의회 정당코드 목록을 조회한다.
	 * @param 
	 * @return 정당코드 목록
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectPprtyCodeList(RlmManageDefaultVO vo) throws Exception{
    	return RlmManageDAO.selectPprtyCodeList(vo);
    }
    
    /**
	 * 지방의회 정당코드 상세정보를 조회한다.
	 * @param 정당코드
	 * @return 정당코드 상세정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectPprtyCodeDetail(HashMap<String,Object> vo) throws Exception{
    	return RlmManageDAO.selectPprtyCodeDetail(vo);
    }
    
    /**
	 * 지방의회 정당코드 정보를 삭제한다.
	 * @param 정당코드
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int DeletePprtyCode(HashMap<String,Object> vo) throws Exception{
    	return RlmManageDAO.DeletePprtyCode(vo);
    }
    
    /**
	 * 지방의회 정당코드 정보를 등록한다.
	 * @param 정당코드
	 * @return 처리결과
	 * @exception Exception
	 */    
    public void InsertPprtyCode(HashMap<String,Object> vo) throws Exception{
    	RlmManageDAO.InsertPprtyCode(vo);
    }
    
    /**
	 * 지방의회 정당코드 정보를 수정한다.
	 * @param 정당코드
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int UpdatePprtyCode(HashMap<String,Object> vo) throws Exception{
    	return RlmManageDAO.UpdatePprtyCode(vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent 설정 정보를 등록한다.
	 * @param 설정정보
	 * @return 처리결과
	 * @exception Exception
	 */    
    public void insertCouncilSystemControl(HashMap<String,Object> vo) throws Exception{
    	RlmManageDAO.insertCouncilSystemControl(vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent log 목록을 조회한다.
	 * @param 의회ID
	 * @return 처리결과
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectCouncilSystemControllList(String vo) throws Exception{
    	return RlmManageDAO.selectCouncilSystemControllList(vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent log 상세을 조회한다.
	 * @param REQST_NO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public String selectCouncilSystemLogDetailView(String vo) throws Exception{
    	return RlmManageDAO.selectCouncilSystemLogDetailView(vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent log 확인여부를 수정한다.
	 * @param REQST_NO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int updateCouncilSystemLogDetailView(String vo) throws Exception{
    	return RlmManageDAO.updateCouncilSystemLogDetailView(vo);
    }
}
