package clikmng.nanet.go.kr.sts.stm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 모바일 관리 처리하는 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */
public interface StmManageService {
	 
	/**
	 * 자료이용 통계
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectUseLogList(UseLogSummaryVO UseLogSummaryVO) throws Exception;


	/**
	 * 자료이용 통계 2
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectDusList(UseLogSummaryVO UseLogSummaryVO) throws Exception;
	
	/**
	 * 자료이용 통계 - 그룹별
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpDusList(UseLogSummaryVO UseLogSummaryVO) throws Exception;
	
	
	/**
	 * 이용자 방문 통계 - 그룹별(기간별)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpUvsList(StmManageDefaultVO UseLogSummaryVO) throws Exception;
	
	/**
	 * 이용자 방문 통계 - 그룹별 (월별)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectGrpUvsMonthList(StmManageDefaultVO UseLogSummaryVO) throws Exception;
	
	/**
	 * 이용자 방문 통계 - 월별 (그룹화)
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectUvsMonGrpList(StmManageDefaultVO UseLogSummaryVO) throws Exception;
	
	/**
	 * 이용자 방문 통계 - OS별
	 * @param UseLogSummaryVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectOsUvsList(StmManageDefaultVO UseLogSummaryVO) throws Exception;
			
	
	/**
	 * os별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectOSLogList(StmManageDefaultVO vo) throws Exception;
	
	/**
	 * 브라우져별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectWbsrLogList(StmManageDefaultVO vo) throws Exception;

	/**
	 * 시간별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectHourLogList(StmManageDefaultVO vo) throws Exception;

	/**
	 * 일별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectDayLogList(StmManageDefaultVO vo) throws Exception;

	/**
	 * 일별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectMonthLogList(StmManageDefaultVO vo) throws Exception;

	/**
	 * 년별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectYearLogList(StmManageDefaultVO vo) throws Exception;

	/**
	 * 전일 방문자 수
	 * @param String
	 * @return String	
	 * @throws Exception
	 */
	public String countVisitorDayBefore(String dt) throws Exception;

	/**
	 * 금일 방문자 수
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public String countVisitorToday(String dt) throws Exception;

	/**
	 * 플랫폼별 방문자 수
	 * @param String
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectVisitorPlatform(String dt) throws Exception;

	/**
	 * 금일 시간대별 방문자 수
	 * @param String
	 * @return List	
	 * @throws Exception
	 */
	public List<?> selectVisitorHour(String dt) throws Exception;

	/**
	 * 통계정보관리 목록을 조회한다
	 * @param StmInfoMngVO
	 * @return List<StmInfoMngVO>	
	 * @throws Exception
	 */
	public List<StmInfoMngVO> selectStmInfoMngList(StmInfoMngVO searchVO) throws Exception;

	/**
	 * 통계정보관리 - 상임위원회 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoScommit(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보관리 - 의원 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoLaman(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보관리 - 의안 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoBill(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보관리 - 상임위원회 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoScommitDetail(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보관리 - 의원 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoLamanDetail(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보관리 - 의안 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoBillDetail(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보관리 - 회의록 정보를 삭제한다.
	 * @param HashMap
	 * @return 처리결과	
	 * @throws Exception
	 */
	public int deleteStmInfoMints(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 통계정보화면 - 기수기간 목록을 조회한다.
	 * @param 
	 * @return 기수기간 목록
	 * @exception Exception
	 */    
    public List<HrsmnpdVO> selectHrsmnpdList() throws Exception;
	
    /**
	 * 통계정보화면 - 의회정당별의원 정보를 조회한다.
	 * @param 
	 * @return 의회정당별의원 정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectAsmbyAsembyList(HashMap<String,Object> map) throws Exception;
    
    /**
	 * 통계정보화면 - 상임위원회 정보를 조회한다.
	 * @param 
	 * @return 상임위원회 정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectPrmpstCmitList(HashMap<String,Object> map) throws Exception;
    
    /**
	 * 통계정보화면 - 의안통계 정보를 조회한다.
	 * @param 
	 * @return 의안통계 정보
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectStatsBillList(HashMap<String,Object> map) throws Exception;
    
    /**
	 * 통계정보화면 - 회의록 정보를 조회한다.
	 * @param 
	 * @return 회의록 정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectMinutesCnt(HashMap<String,Object> map) throws Exception;
	
    /**
	 * 통계정보관리화면 - 지방의회 대수를 조회한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public List<HashMap<String,Object>> selectRasmblyNumpr(HashMap<String,Object> map) throws Exception;
    
    /**
	 * 통계정보화면 - 의회정당별 의원정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsAsmblyAsemby(StatsAsmblyAsemby vo) throws Exception;
	
    /**
	 * 통계정보화면 - 의회정당별 의원정보를 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsAsmblyAsemby(StatsAsmblyAsemby vo) throws Exception;
    
    /**
	 * 통계정보화면 - 의회정당별 의원 상세정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsAsmblyAsembyDetail(StatsAsmblyAsembyDetail vo) throws Exception;
	
    /**
	 * 통계정보화면 - 의회정당별 의원 상세정보를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsAsmblyAsembyDetail(StatsAsmblyAsembyDetail vo) throws Exception;
    
    /**
	 * 통계정보화면 - 상임위원회설치내역을 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsPrmpstCmit(StatsPrmpstCmit vo) throws Exception;
    
    /**
	 * 통계정보화면 - 상임위원회설치내역을 수정한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsPrmpstCmit(StatsPrmpstCmit vo) throws Exception;
    
    /**
	 * 통계정보화면 - 상임위원회설치내역 상세를 등록한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsPrmpstCmitDetail(StatsPrmpstCmitDetail vo) throws Exception;
	
    /**
	 * 통계정보화면 - 상임위원회설치내역 상세를 삭제한다.
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int deleteStatsPrmpstCmitDetail(StatsPrmpstCmitDetail vo) throws Exception;
	
    /**
	 * 통계정보화면 - 검색 정보 유지를 위한 의회 정보를 조회한다.
	 * @param 의회아이디
	 * @return 의회 정보
	 * @exception Exception
	 */    
    public HashMap<String,Object> selectRasmblyInfo(HashMap<String,Object> map) throws Exception;
    
    /**
	 * 통계정보화면 - 의안통계정보를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsBill(HashMap<String,Object> vo) throws Exception;
	
    /**
	 * 통계정보화면 - 의안통계정보를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsBill(HashMap<String,Object> vo) throws Exception;
	
    /**
	 * 통계정보화면 - 의안통계정보 상세를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsBillDetail(HashMap<String,Object> map) throws Exception;
	
    /**
	 * 통계정보화면 - 의안통계정보 상세를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsBillDetail(HashMap<String,Object> map) throws Exception;
	
    /**
	 * 통계정보화면 - 회의록 통계정보를 등록한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public void insertStatsMints(HashMap<String,Object> map) throws Exception;
    
    /**
	 * 통계정보화면 - 회의록 통계정보를 수정한다
	 * @param 
	 * @return 
	 * @exception Exception
	 */    
    public int updateStatsMints(HashMap<String,Object> map) throws Exception;
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	/**
	 * 운영체제별 이용통계 테스트 데이터 입력
	 */
	public void insertOSLogSummary(OSLogSummaryVO vo) throws Exception;
	
	
	/**
	 * 브라우져별 이용통계 테스트 데이터 입력
	 */
	public void insertWbsrLogSummary(WbsrLogSummaryVO vo) throws Exception;


	/**
	 * 브라우져별 이용통계 테스트 데이터 입력
	 */
	public void insertHourLogSummary(HourLogSummaryVO vo) throws Exception;


	/**
	 * 자료이용 이용통계 테스트 데이터 입력
	 */
	public void insertDtaUseLogSummary(DtaUseLogSummaryVO vo) throws Exception;
	
	
	/**
	 * 엑셀출력
	 */
    public ModelAndView selectDusExcel(UseLogSummaryVO UseLogSummaryVO) throws Exception ;
        	
	/**
	 * 엑셀출력
	 */
	/**
	 * os별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public ModelAndView selectOSLogExcel(StmManageDefaultVO vo) throws Exception;
	
	/**
	 * 브라우져별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public ModelAndView selectWbsrLogExcel(StmManageDefaultVO vo) throws Exception;

	/**
	 * 시간별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public ModelAndView selectHourLogExcel(StmManageDefaultVO vo) throws Exception;

	/**
	 * 일별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public ModelAndView selectDayLogExcel(StmManageDefaultVO vo) throws Exception;

	/**
	 * 월별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public ModelAndView selectMonthLogExcel(StmManageDefaultVO vo) throws Exception;

	/**
	 * 년별 방문자 통계 조회
	 * @param StmManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public ModelAndView selectYearLogExcel(StmManageDefaultVO vo) throws Exception;

	


}
