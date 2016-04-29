package clikmng.nanet.go.kr.sts.stm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sts.stm.service.DtaUseLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.HourLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.HrsmnpdVO;
import clikmng.nanet.go.kr.sts.stm.service.OSLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.StatsAsmblyAsemby;
import clikmng.nanet.go.kr.sts.stm.service.StatsAsmblyAsembyDetail;
import clikmng.nanet.go.kr.sts.stm.service.StatsPrmpstCmit;
import clikmng.nanet.go.kr.sts.stm.service.StatsPrmpstCmitDetail;
import clikmng.nanet.go.kr.sts.stm.service.StmInfoMngVO;
import clikmng.nanet.go.kr.sts.stm.service.StmManageDefaultVO;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.WbsrLogSummaryVO;



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
@Repository("StmManageDAO")
public class StmManageDAO extends EgovComAbstractDAO {


	/**
	 * 자료 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UseLogSummaryVO> selectUseLogList(UseLogSummaryVO vo)  throws Exception{
		return list("stmManageDAO.selectUseLogList", vo);
	}
	
	/**
	 * 자료 이용 통계 조회 2
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UseLogSummaryVO> selectDusList(UseLogSummaryVO vo)  throws Exception{
		return list("stmManageDAO.selectDusList", vo);
	}
	
	/**
	 * 자료 이용 통계 조회 - 그룹별
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UseLogSummaryVO> selectGrpDusList(UseLogSummaryVO vo)  throws Exception{
		return list("stmManageDAO.selectGrpDusList", vo);
	}
	
	/**
	 * 이용자 방문 통계 - 그룹별(기간지정)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpUvsList(StmManageDefaultVO vo) throws Exception{
		return list("stmManageDAO.selectGrpUvsList", vo);
	}
	
	/**
	 * 이용자 방문 통계 - 그룹별 (월별)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpUvsMonthList(StmManageDefaultVO vo) throws Exception{
		return list("stmManageDAO.selectGrpUvsMonthList", vo);
	}
	
	/**
	 * 이용자 방문 통계 - 월별 (그룹화)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectUvsMonGrpList(StmManageDefaultVO vo) throws Exception{
		return list("stmManageDAO.selectUvsMonGrpList", vo);
	}
	
	/**
	 * 이용자 방문 통계 - OS별
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectOsUvsList(StmManageDefaultVO vo) throws Exception{
		return list("stmManageDAO.selectOsUvsList", vo);
	}

	/**
	 * OS 별 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OSLogSummaryVO> selectOSLogList(StmManageDefaultVO vo)  throws Exception{
		return list("stmManageDAO.selectOSLogList", vo);
	}

	/**
	 * 브라우져 별 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<WbsrLogSummaryVO> selectWbsrLogList(StmManageDefaultVO vo)  throws Exception{
		return list("stmManageDAO.selectWbsrLogList", vo);
	}

	/**
	 * 시간별 별 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<HourLogSummaryVO> selectHourLogList(StmManageDefaultVO vo)  throws Exception{
		return list("stmManageDAO.selectHourLogList", vo);
	}

	/**
	 * 일 별 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<HourLogSummaryVO> selectDayLogList(StmManageDefaultVO vo)  throws Exception{
		return list("stmManageDAO.selectDayLogList", vo);
	}
	
	/**
	 * 월 별 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<HourLogSummaryVO> selectMonthLogList(StmManageDefaultVO vo)  throws Exception{
		return list("stmManageDAO.selectMonthLogList", vo);
	}
	
	/**
	 * 년 별 이용 통계 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<HourLogSummaryVO> selectYearLogList(StmManageDefaultVO vo)  throws Exception{
		return list("stmManageDAO.selectYearLogList", vo);
	}

	/**
	 * 전일 방문자 수
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public String countVisitorDayBefore(String dt)  throws Exception{
		return (String)getSqlMapClientTemplate().queryForObject("stmManageDAO.countVisitorDayBefore", dt);
	}

	/**
	 * 금일 방문자 수
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public String countVisitorToday(String dt)  throws Exception{
		return (String)getSqlMapClientTemplate().queryForObject("stmManageDAO.countVisitorToday", dt);
	}

	/**
	 * 금일 플랫폼별 방문자 수
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public List<?> selectVisitorPlatform(String dt)  throws Exception{
		return list("stmManageDAO.selectVisitorPlatform", dt);
	}

	/**
	 * 금일 시간대별 방문자 수
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public List<?> selectVisitorHour(String dt)  throws Exception{
		return list("stmManageDAO.selectVisitorHour", dt);
	}
	
	/**
	 * 통계정보관리 목록을 조회한다
	 * @param StmInfoMngVO
	 * @return List<StmInfoMngVO>	
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<StmInfoMngVO> selectStmInfoMngList(StmInfoMngVO searchVO) throws Exception{
		return list("stmManageDAO.selectStmInfoMngList", searchVO);
	}
	
	/**
	 * 통계정보관리 - 상임위원회 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoScommit(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoScommit", map);
	}
	
	/**
	 * 통계정보관리 - 의원 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoLaman(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoLaman", map);
	}
	
	/**
	 * 통계정보관리 - 의안 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoBill(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoBill", map);
	}
	
	/**
	 * 통계정보관리 - 상임위원회 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoScommitDetail(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoScommitDetail", map);
	}
	
	/**
	 * 통계정보관리 - 의원 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoLamanDetail(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoLamanDetail", map);
	}
	
	/**
	 * 통계정보관리 - 의안 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoBillDetail(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoBillDetail", map);
	}
	
	/**
	 * 통계정보관리 - 회의록 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoMints(HashMap<String,Object> map) throws Exception{
		return update("stmManageDAO.deleteStmInfoMints", map);
	}
	
	/**
	 * 자료등록 통계정보화면 - 기수기간 목록을 조회한다.
	 * @param 
	 * @return 기수기간 목록
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HrsmnpdVO> selectHrsmnpdList() throws Exception{
		return list("stmManageDAO.selectHrsmnpdList",null);
	}
	
    /**
	 * 통계정보화면 - 의회정당별의원 정보를 조회한다.
	 * @param 
	 * @return 의회정당별의원 정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectAsmbyAsembyList(HashMap<String,Object> map) throws Exception{
    	return list("stmManageDAO.selectAsmbyAsembyList",map);
	}
	
    /**
	 * 통계정보화면 - 상임위원회 정보를 조회한다.
	 * @param 
	 * @return 상임위원회 정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectPrmpstCmitList(HashMap<String,Object> map) throws Exception{
    	return list("stmManageDAO.selectPrmpstCmitList",map);
	}
    
    /**
	 * 통계정보화면 - 의안통계 정보를 조회한다.
	 * @param 
	 * @return 의안통계 정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectStatsBillList(HashMap<String,Object> map) throws Exception{
    	return list("stmManageDAO.selectStatsBillList",map);
	}
    
    /**
	 * 통계정보화면 - 회의록 정보를 조회한다.
	 * @param 
	 * @return 회의록 정보
	 * @exception Exception
	 */    
	@SuppressWarnings("unchecked")
	public HashMap<String,Object> selectMinutesCnt(HashMap<String,Object> map) throws Exception{
    	return (HashMap<String,Object>) getSqlMapClient().queryForObject("stmManageDAO.selectMinutesCnt",map);
	}
	
	/**
	 * 통계정보관리화면 - 지방의회 대수를 조회한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectRasmblyNumpr(HashMap<String,Object> map) throws Exception{
    	return list("stmManageDAO.selectRasmblyNumpr",map);
    }
	
    /**
	 * 통계정보화면 - 의회정당별 의원정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsAsmblyAsemby(StatsAsmblyAsemby vo) throws Exception{
    	insert("stmManageDAO.insertStatsAsmblyAsemby", vo);
    }
	
    /**
	 * 통계정보화면 - 의회정당별 의원정보를 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsAsmblyAsemby(StatsAsmblyAsemby vo) throws Exception{
    	return update("stmManageDAO.updateStatsAsmblyAsemby", vo);
    }
    
    /**
	 * 통계정보화면 - 의회정당별 의원 상세정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsAsmblyAsembyDetail(StatsAsmblyAsembyDetail vo) throws Exception{
    	insert("stmManageDAO.insertStatsAsmblyAsembyDetail", vo);
    }
	
    /**
	 * 통계정보화면 - 의회정당별 의원 상세정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsAsmblyAsembyDetail(StatsAsmblyAsembyDetail vo) throws Exception{
    	return update("stmManageDAO.updateStatsAsmblyAsembyDetail", vo);
    }
	
    /**
	 * 통계정보화면 - 상임위원회설치내역을 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsPrmpstCmit(StatsPrmpstCmit vo) throws Exception{
    	insert("stmManageDAO.insertStatsPrmpstCmit", vo);
    }
    
    /**
	 * 통계정보화면 - 상임위원회설치내역을 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsPrmpstCmit(StatsPrmpstCmit vo) throws Exception{
    	return update("stmManageDAO.updateStatsPrmpstCmit", vo);
    }
    
    /**
	 * 통계정보화면 - 상임위원회설치내역 상세를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsPrmpstCmitDetail(StatsPrmpstCmitDetail vo) throws Exception{
    	update("stmManageDAO.insertStatsPrmpstCmitDetail", vo);
    }
	
    /**
	 * 통계정보화면 - 상임위원회설치내역 상세를 삭제한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int deleteStatsPrmpstCmitDetail(StatsPrmpstCmitDetail vo) throws Exception{
    	return update("stmManageDAO.deleteStatsPrmpstCmitDetail", vo);
    }
	
    /**
	 * 통계정보화면 - 검색 정보 유지를 위한 의회 정보를 조회한다.
	 * @param 의회아이디
	 * @return 의회 정보
	 * @exception Exception
	 */    
    @SuppressWarnings("unchecked")
	public HashMap<String,Object> selectRasmblyInfo(HashMap<String,Object> map) throws Exception{
    	return (HashMap<String,Object>)getSqlMapClientTemplate().queryForObject("stmManageDAO.selectRasmblyInfo", map);
    }
    
    /**
	 * 통계정보화면 - 의안통계정보를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsBill(HashMap<String,Object> map) throws Exception{
    	insert("stmManageDAO.insertStatsBill", map);
    }
	
    /**
	 * 통계정보화면 - 의안통계정보를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsBill(HashMap<String,Object> map) throws Exception{
    	return update("stmManageDAO.updateStatsBill", map);
    }
    
    /**
	 * 통계정보화면 - 의안통계정보 상세를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsBillDetail(HashMap<String,Object> map) throws Exception{
    	insert("stmManageDAO.insertStatsBillDetail", map);
    }
	
    /**
	 * 통계정보화면 - 의안통계정보 상세를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsBillDetail(HashMap<String,Object> map) throws Exception{
    	return update("stmManageDAO.updateStatsBillDetail", map);
    }
    
    /**
	 * 통계정보화면 - 회의록 통계정보를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsMints(HashMap<String,Object> map) throws Exception{
    	insert("stmManageDAO.insertStatsMints", map);
    }
    
    /**
	 * 통계정보화면 - 회의록 통계정보를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsMints(HashMap<String,Object> map) throws Exception{
    	return update("stmManageDAO.updateStatsMints", map);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public void insertOSLogSummary(OSLogSummaryVO vo) throws Exception {
		insert("stmManagerDAO.insertOSLogSummary", vo);
	}

	public void insertWbsrLogSummary(WbsrLogSummaryVO vo) throws Exception {
		insert("stmManagerDAO.insertWbsrLogSummary", vo);
	}
	
	public void insertHourLogSummary(HourLogSummaryVO vo) throws Exception {
		insert("stmManagerDAO.insertHourLogSummary", vo);
	}

	public void insertDtaUseLogSummary(DtaUseLogSummaryVO vo) throws Exception {
		insert("stmManagerDAO.insertDtaUseLogSummary", vo);
	}

}
