package clikmng.nanet.go.kr.rlm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.rlm.service.CodeVO;
import clikmng.nanet.go.kr.rlm.service.HrsmnpdPprtyVO;
import clikmng.nanet.go.kr.rlm.service.HrsmnpdVO;
import clikmng.nanet.go.kr.rlm.service.PprtyVO;
import clikmng.nanet.go.kr.rlm.service.RasmblyNumprPdVO;
import clikmng.nanet.go.kr.rlm.service.RasmblyVO;
import clikmng.nanet.go.kr.rlm.service.RlmManageDefaultVO;
import clikmng.nanet.go.kr.rlm.service.RlmManageVO;

/**
 * 
 * 사이트정보를 처리하는 DAO 클래스
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
@Repository("RlmManageDAO")
public class RlmManageDAO extends EgovComAbstractDAO {

	/**
     * 테이블의 다음PK 값을 가져온다.
     * @param tableName : 조회할 테이블명
     * @return next PK 값
     * @throws SQLException 
     * */
    public String getNextPKValue(String tableName) throws SQLException{
    	return (String)getSqlMapClient().queryForObject("RcmManage.getNextPKValue",tableName);
    }
    
    /**
     * 테이블의 다음PK값을 다음PK값으로 수정한다.
     * @param tableName : 변경할 테이블명
     * @return 
     * */
    public void setNextPKValue(String tableName){
    	update("RcmManage.updateNextPK",tableName);
    }
	
	/**
	 * 공통코드를 조회한다.
	 * @param code_id
	 * @return 공통코드 목록
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<CodeVO> getCommCodeList(String codeId) throws Exception{
		return (List<CodeVO>)list("RcmManage.getCommCodeList", codeId);
	}
	
	/**
	 * 지방의회 수집 API Key 목록을 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<RasmblyVO> selectRasmblyApiKeyList(RlmManageDefaultVO searchVO) throws Exception{
		  return (List<RasmblyVO>)list("RcmManage.selectRasmblyApiKeyList", searchVO);
	}
	
	public int selectRasmblyApiKeyListCnt(RlmManageDefaultVO searchVO) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("RcmManage.selectRasmblyApiKeyListCnt", searchVO);
	}
	
	
		
	/**
	 * 지방의회 수집 API Key 목록을 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public int deleteRasmblyApiKey(String rasmblyId) throws Exception{
		  return update("RcmManage.deleteRasmblyApiKey", rasmblyId);
	}
	
	/**
     * 지방의회 연계관리 - 수집 API Key 저장
     * @param 등록 할 API Key 정보
     * @throws Exception
     */
	public void insertRasmblyApiKey(RasmblyVO vo) throws Exception{
		insert("RcmManage.insertRasmblyApiKey",vo);
	}
	
	/**
     * 지방의회 연계관리 - 수집 API Key 수정
     * @param 수정 할 API Key 정보
     * @param model
     * @return 처리수
     * @throws Exception
     */
	public int updateRasmblyApiKey(RasmblyVO vo) throws Exception{
		return update("RcmManage.updateRasmblyApiKey",vo);
	}
	
	/**
	 * 지방의회 수집 API Key를 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public RasmblyVO selectRasmblyApiKey(RasmblyVO vo) throws Exception{
		  return (RasmblyVO) getSqlMapClient().queryForObject("RcmManage.selectRasmblyApiKey", vo);
	}
	
	/**
	 * 기수 목록을 조회한다.
	 * @param searchVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<HrsmnpdVO> selectGenerationFlagList(RlmManageDefaultVO vo) throws Exception{
		return (List<HrsmnpdVO>)list("RcmManage.selectGenerationFlagList", vo);
	}
	
	/**
	 * 기수 상세정보를 조회한다.
	 * @param HrsmnpdVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	public HrsmnpdVO selectGenerationFlag(HrsmnpdVO vo) throws Exception{
		return (HrsmnpdVO) getSqlMapClient().queryForObject("RcmManage.selectGenerationFlag", vo);
	}
	
	/**
	 * 기수 매핑 의회 목록을 조회한다.
	 * @param HrsmnpdVO
	 * @return 조회결과
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<RasmblyNumprPdVO> selectRasmblyNumprPdList(HrsmnpdVO vo) throws Exception{
		return (List<RasmblyNumprPdVO>)list("RcmManage.selectRasmblyNumprPdList", vo);
	}
	
	/**
	 * 지방의회 연계관리 - 대수/기수 새로운 맵핑 정보를 조회한다.
     * @param HrsmnpdVO
     * @param model
     * @return 조회결과
     * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectNumprMapping(HrsmnpdVO vo) throws Exception{
		return (List<HashMap<String,Object>>)list("RcmManage.selectNumprMapping", vo);
	}
	
	/**
	 * 기수 기간 정보를 등록한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public void insertHrsmnpd(HrsmnpdVO vo) throws Exception{
		insert("RcmManage.insertHrsmnpd",vo);
	}
	
	/**
	 * 기수 기간 정보를 수정한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int updateHrsmnpd(HrsmnpdVO vo) throws Exception{
		return update("RcmManage.updateHrsmnpd",vo);
	}
	
	/**
	 * 기수 기간 정보를 삭제한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int deleteHrsmnpd(HrsmnpdVO vo) throws Exception{
		return update("RcmManage.deleteHrsmnpd",vo);
	}
	
	/**
	 * 대수 기간 정보를 등록한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public void insertNumprpd(RasmblyNumprPdVO vo) throws Exception{
		update("RcmManage.insertNumprpd",vo);
	}
	
	/**
	 * 대수 기간 정보를 수정한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int updateNumprpd(RasmblyNumprPdVO vo) throws Exception{
		return update("RcmManage.updateNumprpd",vo);
	}
	
	/**
	 * 기수번호에 해당하는 대수 기간 정보를 삭제한다.
	 * @param HrsmnpdVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int deleteNumprpd(HrsmnpdVO vo) throws Exception{
		return update("RcmManage.deleteNumprpd",vo);
	}
	
	/**
	 * 기수별 정당 정보 목록을 조회한다.
	 * @param 
	 * @return 기수별 정당 목록
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<HrsmnpdPprtyVO> selectHrsmnPprtyMngList() throws Exception{
		return (List<HrsmnpdPprtyVO>)list("RcmManage.selectHrsmnPprtyMngList",null);
	}
	
	/**
	 * 기수별 정당 상세정보를 조회한다.
	 * @param 
	 * @return 기수별 정당 상세정보
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<HrsmnpdPprtyVO> selectHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		return (List<HrsmnpdPprtyVO>) list("RcmManage.selectHrsmnPprtyMng",vo);
	}
	
	/**
	 * 기수별 정당 정보를 등록한다.
	 * @param HrsmnpdPprtyVO
	 * @return 
	 * @throws Exception
	 * */
	public void insertHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		insert("RcmManage.insertHrsmnPprtyMng",vo);
	}
	
	/**
	 * 기수별 정당 정보를 수정한다.
	 * @param HrsmnpdPprtyVO
	 * @return 처리결과
	 * @throws Exception
	 * */
	public int updateHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		return update("RcmManage.updateHrsmnPprtyMng",vo);
	}
	
	/**
	 * 기수별 정당 정보를 삭제한다.
	 * @param HrsmnpdPprtyVO
	 * @return 처리 결과
	 * @throws Exception
	 * */
	public int deleteHrsmnPprtyMng(HrsmnpdPprtyVO vo) throws Exception{
		return update("RcmManage.deleteHrsmnPprtyMng",vo);
	}
	
	/**
	 * 지방의회 정당 목록을 조회한다.
	 * @param RlmManageDefaultVO
	 * @return 정당 목록
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<PprtyVO> selectPprtyList(RlmManageDefaultVO vo) throws Exception{
		return list("RcmManage.selectPprtyList",vo);
	}
	
	/**
	 * 연계API 수집 내역 목록
	 * @param RlmManageVO
	 * @return 연계 API 수집 내역
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<RlmManageVO> selectCollectionInfoList(RlmManageVO vo) throws Exception {
		return list("RcmManage.selectCollectionInfoList", vo);
	}
	
	/**
	 * 연계API 수집 내역 갯수
	 * @param RlmManageVO
	 * @return 연계 API 수집 내역
	 * @throws Exception
	 * */
	public int selectCollectionInfoCnt(RlmManageVO vo) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("RcmManage.selectCollectionInfoCnt", vo);
	}
	
	/**
	 * 연계의회 목록
	 * @param RlmManageVO
	 * @return 연계의회 목록
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<RlmManageVO> SelectRasmblyList() throws Exception {
		return list("RcmManage.selectRasmblyList", null);
	}
	
	/**
	 * 연계API 구분
	 * @param RlmManageVO
	 * @return 연계API 구분
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<RlmManageVO> SelectApiList() throws Exception {
		return list("RcmManage.selectApiList", null);
	}
	
	/**
	 * 표준연계 API 수집요청 목록을 조회한다.
	 * @param RlmManageVO
	 * @return 표준연계 API 수집요청 목록
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectStdCntcApiColctMngList(RlmManageVO searchVO){
		return (List<HashMap<String,Object>>) list("RcmManage.selectStdCntcApiColctMngList", searchVO);
	}
	
	/**
	 * 통계정보화면 - 검색 정보 유지를 위한 의회 정보를 조회한다.
	 * @param 의회아이디
	 * @return 의회 정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public HashMap<String,Object> selectRasmblyInfo(HashMap<String,Object> map) throws Exception{
    	return (HashMap<String,Object>)getSqlMapClientTemplate().queryForObject("RcmManage.selectRasmblyInfo", map);
    }
    
    /**
	 * 표준연계 API 수집요청 상세정보를 조회한다.
	 * @param 
	 * @return 표준연계api 상세정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public HashMap<String,Object> selectStdCntcApiColctMngDetail(HashMap<String,Object> map) throws Exception{
    	return (HashMap<String,Object>)getSqlMapClientTemplate().queryForObject("RcmManage.selectStdCntcApiColctMngDetail", map);
    }
    
    /**
	 * 표준연계 API 수집요청 상세정보를 삭제한다.
	 * @param 
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int DeleteStdCntcApiColct(HashMap<String,Object> map) throws Exception{
    	return update("RcmManage.DeleteStdCntcApiColct", map);
    }
    
    /**
	 * 표준연계 API 수집요청 상세정보를 등록한다.
	 * @param 재수집요청 상세정보
	 * @return 
	 * @exception Exception
	 */    
    public void insertStdCntcApiColct(HashMap<String,Object> map) throws Exception{
    	insert("RcmManage.insertStdCntcApiColct", map);
    }
    
    /**
	 * 표준연계 API 모니터링 정보를 조회한다.
	 * @param 
	 * @return 지방의회 연계  정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectStdCntcApiColctMntrng() throws Exception{
    	return (List<HashMap<String,Object>>) list("RcmManage.selectStdCntcApiColctMntrng", null);
    }
    
    /**
	 * 연계 API 관리 - 승격 지방의회 정보를 수정한다.
	 * @param RasmblyVO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int updatePrmtRasmbly(RasmblyVO vo) throws Exception{
    	return update("RcmManage.updatePrmtRasmbly", vo);
    }
    
    /**
	 * 연계 API 관리 - 승격 지방의회 정보를 등록한다.
	 * @param RasmblyVO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public void insertPrmtRasmbly(RasmblyVO vo) throws Exception{
    	insert("RcmManage.insertPrmtRasmbly", vo);
    }
    
    /**
	 * 연계 API 관리 - 승격 지방의회 정보를 삭제한다.
	 * @param RasmblyVO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int deletePrmtRasmbly(RasmblyVO vo) throws Exception{
    	return update("RcmManage.deletePrmtRasmbly", vo);
    }
    
    /**
	 * 지방의회 정당코드 목록을 조회한다.
	 * @param 
	 * @return 정당코드 목록
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectPprtyCodeList(RlmManageDefaultVO vo) throws Exception{
    	return (List<HashMap<String,Object>>) list("RcmManage.selectPprtyCodeList", vo);
    }
    
    /**
	 * 지방의회 정당코드 상세정보를 조회한다.
	 * @param 정당코드
	 * @return 정당코드 상세정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public HashMap<String,Object> selectPprtyCodeDetail(HashMap<String,Object> vo) throws Exception{
    	return (HashMap<String,Object>)getSqlMapClientTemplate().queryForObject("RcmManage.selectPprtyCodeDetail", vo);
    }
    
    /**
	 * 지방의회 정당코드 정보를 삭제한다.
	 * @param 정당코드
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int DeletePprtyCode(HashMap<String,Object> vo) throws Exception{
    	return update("RcmManage.DeletePprtyCode", vo);
    }
    
    /**
	 * 지방의회 정당코드 정보를 등록한다.
	 * @param 정당코드
	 * @return 처리결과
	 * @exception Exception
	 */    
    public void InsertPprtyCode(HashMap<String,Object> vo) throws Exception{
    	insert("RcmManage.InsertPprtyCode", vo);
    }
    
    /**
	 * 지방의회 정당코드 정보를 수정한다.
	 * @param 정당코드
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int UpdatePprtyCode(HashMap<String,Object> vo) throws Exception{
    	return update("RcmManage.UpdatePprtyCode", vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent 설정 정보를 등록한다.
	 * @param 설정정보
	 * @return 처리결과
	 * @exception Exception
	 */    
    public void insertCouncilSystemControl(HashMap<String,Object> vo) throws Exception{
    	insert("RcmManage.insertCouncilSystemControl", vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent log 목록을 조회한다.
	 * @param 의회ID
	 * @return 처리결과
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectCouncilSystemControllList(String vo) throws Exception{
    	return list("RcmManage.selectCouncilSystemControllList", vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent log 상세을 조회한다.
	 * @param REQST_NO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public String selectCouncilSystemLogDetailView(String vo) throws Exception{
    	return (String) getSqlMapClientTemplate().queryForObject("RcmManage.selectCouncilSystemLogDetailView", vo);
    }
    
    /**
	 * 표준연계API 모니터링 : agent log 확인여부를 수정한다.
	 * @param REQST_NO
	 * @return 처리결과
	 * @exception Exception
	 */    
    public int updateCouncilSystemLogDetailView(String vo) throws Exception{
    	return update("RcmManage.updateCouncilSystemLogDetailView", vo);
    }
}
