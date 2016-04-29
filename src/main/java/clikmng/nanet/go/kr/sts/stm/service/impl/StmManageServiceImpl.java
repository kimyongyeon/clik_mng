package clikmng.nanet.go.kr.sts.stm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
import clikmng.nanet.go.kr.sts.stm.service.StmManageService;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.WbsrLogSummaryVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("StmManageService")
public class StmManageServiceImpl extends AbstractServiceImpl implements
        StmManageService {

    @Resource(name="StmManageDAO")
    private StmManageDAO StmManageDAO;
        
    /** ID Generation */
/*    
	@Resource(name="egovStmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
	/**
	 * 자료 이용 통계 조회
	 */
	public List selectUseLogList(UseLogSummaryVO UseLogSummaryVO) throws Exception {
		return (List)StmManageDAO.selectUseLogList(UseLogSummaryVO);
	}
	
	/**
	 * 자료 이용 통계 조회2
	 */
	public List selectDusList(UseLogSummaryVO UseLogSummaryVO) throws Exception {
		return (List)StmManageDAO.selectDusList(UseLogSummaryVO);
	}
	
	/**
	 * 자료 이용 통계 조회 - 그룹별
	 */
	public List selectGrpDusList(UseLogSummaryVO UseLogSummaryVO) throws Exception {
		return (List)StmManageDAO.selectGrpDusList(UseLogSummaryVO);
	}
	
	/**
	 * 이용자 방문 통계 - 그룹별 - 기간지정
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpUvsList(StmManageDefaultVO UseLogSummaryVO) throws Exception{
		return (List)StmManageDAO.selectGrpUvsList(UseLogSummaryVO);
	}
	
	/**
	 * 이용자 방문 통계 - 그룹별 - 월별
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpUvsMonthList(StmManageDefaultVO UseLogSummaryVO) throws Exception{
		return (List)StmManageDAO.selectGrpUvsMonthList(UseLogSummaryVO);
	}
	
	/**
	 * 이용자 방문 통계 - 월별 (그룹화)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectUvsMonGrpList(StmManageDefaultVO UseLogSummaryVO) throws Exception{
		return (List)StmManageDAO.selectUvsMonGrpList(UseLogSummaryVO);
	}
	
	/**
	 * 이용자 방문 통계 - OS별
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectOsUvsList(StmManageDefaultVO UseLogSummaryVO) throws Exception{
		return (List)StmManageDAO.selectOsUvsList(UseLogSummaryVO);
	}
	

	/**
	 * OS 별 이용 통계 조회
	 */
	public List selectOSLogList(StmManageDefaultVO vo) throws Exception {
		return (List)StmManageDAO.selectOSLogList(vo);
	}

	/**
	 * 브라우져 별 이용 통계 조회
	 */
	public List selectWbsrLogList(StmManageDefaultVO vo) throws Exception {
		return (List)StmManageDAO.selectWbsrLogList(vo);
	}

	/**
	 * 시간 별 이용 통계 조회
	 */
	public List selectHourLogList(StmManageDefaultVO vo) throws Exception {
		return (List)StmManageDAO.selectHourLogList(vo);
	}

	/**
	 * 일 별 이용 통계 조회
	 */
	public List selectDayLogList(StmManageDefaultVO vo) throws Exception {
		return (List)StmManageDAO.selectDayLogList(vo);
	}

	/**
	 * 월 별 이용 통계 조회
	 */
	public List selectMonthLogList(StmManageDefaultVO vo) throws Exception {
		return (List)StmManageDAO.selectMonthLogList(vo);
	}

	/**
	 * 년 별 이용 통계 조회
	 */
	public List selectYearLogList(StmManageDefaultVO vo) throws Exception {
		return (List)StmManageDAO.selectYearLogList(vo);
	}

	/**
	 * 전일 방문자 수
	 */
	public String countVisitorDayBefore(String dt) throws Exception {
		return (String)StmManageDAO.countVisitorDayBefore(dt);
	}

	/**
	 * 금일 방문자 수
	 */
	public String countVisitorToday(String dt) throws Exception {
		return (String)StmManageDAO.countVisitorToday(dt);
	}

	/**
	 * 플랫폼별 방문자 수
	 */
	public List selectVisitorPlatform(String dt) throws Exception {
		return (List)StmManageDAO.selectVisitorPlatform(dt);
	}

	/**
	 * 금일 시간대별 방문자 수
	 */
	public List selectVisitorHour(String dt) throws Exception {
		return (List)StmManageDAO.selectVisitorHour(dt);
	}

	/**
	 * 통계정보관리 목록을 조회한다
	 * @param StmInfoMngVO
	 * @return List<StmInfoMngVO>	
	 * @throws Exception
	 */
	public List<StmInfoMngVO> selectStmInfoMngList(StmInfoMngVO searchVO) throws Exception{
		return (List<StmInfoMngVO>) StmManageDAO.selectStmInfoMngList(searchVO);
	}
	
	/**
	 * 통계정보관리 - 상임위원회 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoScommit(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoScommit(map);
	}
	
	/**
	 * 통계정보관리 - 의원 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoLaman(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoLaman(map);
	}
	
	/**
	 * 통계정보관리 - 의안 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoBill(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoBill(map);
	}
	
	/**
	 * 통계정보관리 - 상임위원회 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoScommitDetail(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoScommitDetail(map);
	}
	
	/**
	 * 통계정보관리 - 의원 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoLamanDetail(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoLamanDetail(map);
	}
	
	/**
	 * 통계정보관리 - 의안 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoBillDetail(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoBillDetail(map);
	}
	
	/**
	 * 통계정보관리 - 회의록 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoMints(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.deleteStmInfoMints(map);
	}
	
	/**
	 * 자료등록 통계정보화면 - 기수기간 목록을 조회한다.
	 * @param 
	 * @return 기수기간 목록
	 * @exception Exception
	 */    
    public List<HrsmnpdVO> selectHrsmnpdList() throws Exception{
		return StmManageDAO.selectHrsmnpdList();
	}
	
    /**
	 * 통계정보화면 - 의회정당별의원 정보를 조회한다.
	 * @param 
	 * @return 의회정당별의원 정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectAsmbyAsembyList(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.selectAsmbyAsembyList(map);
	}
	
    /**
	 * 통계정보화면 - 상임위원회 정보를 조회한다.
	 * @param 
	 * @return 상임위원회 정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectPrmpstCmitList(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.selectPrmpstCmitList(map);
	}
    
    /**
	 * 통계정보화면 - 의안통계 정보를 조회한다.
	 * @param 
	 * @return 의안통계 정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectStatsBillList(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.selectStatsBillList(map);
	}
    
    /**
	 * 통계정보화면 - 회의록 정보를 조회한다.
	 * @param 
	 * @return 회의록 정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectMinutesCnt(HashMap<String,Object> map) throws Exception{
		return StmManageDAO.selectMinutesCnt(map);
	}
	
    /**
	 * 통계정보관리화면 - 지방의회 대수를 조회한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectRasmblyNumpr(HashMap<String,Object> map) throws Exception{
    	return StmManageDAO.selectRasmblyNumpr(map);
    }

    /**
	 * 통계정보화면 - 의회정당별 의원정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsAsmblyAsemby(StatsAsmblyAsemby vo) throws Exception{
    	StmManageDAO.insertStatsAsmblyAsemby(vo);
    }
	
    /**
	 * 통계정보화면 - 의회정당별 의원정보를 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsAsmblyAsemby(StatsAsmblyAsemby vo) throws Exception{
    	return StmManageDAO.updateStatsAsmblyAsemby(vo);
    }
    
    /**
	 * 통계정보화면 - 의회정당별 의원 상세정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsAsmblyAsembyDetail(StatsAsmblyAsembyDetail vo) throws Exception{
    	StmManageDAO.insertStatsAsmblyAsembyDetail(vo);
    }
	
    /**
	 * 통계정보화면 - 의회정당별 의원 상세정보를 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsAsmblyAsembyDetail(StatsAsmblyAsembyDetail vo) throws Exception{
    	return StmManageDAO.updateStatsAsmblyAsembyDetail(vo);
    }
    
    /**
	 * 통계정보화면 - 상임위원회설치내역을 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsPrmpstCmit(StatsPrmpstCmit vo) throws Exception{
    	StmManageDAO.insertStatsPrmpstCmit(vo);
    }
    
    /**
	 * 통계정보화면 - 상임위원회설치내역을 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsPrmpstCmit(StatsPrmpstCmit vo) throws Exception{
    	return StmManageDAO.updateStatsPrmpstCmit(vo);
    }
    
    /**
	 * 통계정보화면 - 상임위원회설치내역 상세를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsPrmpstCmitDetail(StatsPrmpstCmitDetail vo) throws Exception{
    	StmManageDAO.insertStatsPrmpstCmitDetail(vo);
    }
	
    /**
	 * 통계정보화면 - 상임위원회설치내역 상세를 삭제한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int deleteStatsPrmpstCmitDetail(StatsPrmpstCmitDetail vo) throws Exception{
    	return StmManageDAO.deleteStatsPrmpstCmitDetail(vo);
    }
    
    /**
	 * 통계정보화면 - 검색 정보 유지를 위한 의회 정보를 조회한다.
	 * @param 의회아이디
	 * @return 의회 정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectRasmblyInfo(HashMap<String,Object> map) throws Exception{
    	return StmManageDAO.selectRasmblyInfo(map);
    }
    
    /**
	 * 통계정보화면 - 의안통계정보를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsBill(HashMap<String,Object> map) throws Exception{
    	StmManageDAO.insertStatsBill(map);
    }
	
    /**
	 * 통계정보화면 - 의안통계정보를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsBill(HashMap<String,Object> map) throws Exception{
    	return StmManageDAO.updateStatsBill(map);
    }
	
    /**
	 * 통계정보화면 - 의안통계정보 상세를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsBillDetail(HashMap<String,Object> map) throws Exception{
    	StmManageDAO.insertStatsBillDetail(map);
    }
	
    /**
	 * 통계정보화면 - 의안통계정보 상세를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsBillDetail(HashMap<String,Object> map) throws Exception{
    	return StmManageDAO.updateStatsBillDetail(map);
    }
    
    /**
	 * 통계정보화면 - 회의록 통계정보를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsMints(HashMap<String,Object> map) throws Exception{
    	StmManageDAO.insertStatsMints(map);
    }
    
    /**
	 * 통계정보화면 - 회의록 통계정보를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsMints(HashMap<String,Object> map) throws Exception{
    	return StmManageDAO.updateStatsMints(map);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	public void	insertOSLogSummary(OSLogSummaryVO vo) throws Exception {
		StmManageDAO.insertOSLogSummary(vo);
	}
	
	public void insertWbsrLogSummary(WbsrLogSummaryVO vo) throws Exception {
		StmManageDAO.insertWbsrLogSummary(vo);
	}
	
	public void insertHourLogSummary(HourLogSummaryVO vo) throws Exception {
		StmManageDAO.insertHourLogSummary(vo);
	}

	public void insertDtaUseLogSummary(DtaUseLogSummaryVO vo) throws Exception {
		StmManageDAO.insertDtaUseLogSummary(vo);
	}

	
	/**
	 * 자료 이용 통계 조회 엑셀출력
	 */
	public ModelAndView selectDusExcel(UseLogSummaryVO UseLogSummaryVO)  throws Exception {
		return (ModelAndView)StmManageDAO.selectDusList(UseLogSummaryVO);
	}	
	
	/**
	 * OS 별 이용 통계 조회
	 */
	public ModelAndView selectOSLogExcel(StmManageDefaultVO vo) throws Exception {
		return (ModelAndView)StmManageDAO.selectOSLogList(vo);
	}

	/**
	 * 브라우져 별 이용 통계 조회
	 */
	public ModelAndView selectWbsrLogExcel(StmManageDefaultVO vo) throws Exception {
		return (ModelAndView)StmManageDAO.selectWbsrLogList(vo);
	}

	/**
	 * 시간 별 이용 통계 조회
	 */
	public ModelAndView selectHourLogExcel(StmManageDefaultVO vo) throws Exception {
		return (ModelAndView)StmManageDAO.selectHourLogList(vo);
	}

	/**
	 * 일 별 이용 통계 조회
	 */
	public ModelAndView selectDayLogExcel(StmManageDefaultVO vo) throws Exception {
		return (ModelAndView)StmManageDAO.selectDayLogList(vo);
	}

	/**
	 * 월 별 이용 통계 조회
	 */
	public ModelAndView selectMonthLogExcel(StmManageDefaultVO vo) throws Exception {
		return (ModelAndView)StmManageDAO.selectMonthLogList(vo);
	}

	/**
	 * 년 별 이용 통계 조회
	 */
	public ModelAndView selectYearLogExcel(StmManageDefaultVO vo) throws Exception {
		return (ModelAndView)StmManageDAO.selectYearLogList(vo);
	}
	

}
