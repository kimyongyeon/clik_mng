package clikmng.nanet.go.kr.mob.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.CalUtil;
import clikmng.nanet.go.kr.mob.service.MobManageDefaultVO;
import clikmng.nanet.go.kr.mob.service.MobManageService;
import clikmng.nanet.go.kr.mob.service.MobManageVO;
import clikmng.nanet.go.kr.mob.service.MobileLogVO;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 모바일 관리를 처리하는 Controller 클래스
 */

@Controller
public class MobManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MobManageService")
    private MobManageService MobManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 모바일 - 공지사항 목록 조회
     * @param searchVO
     * @param model
     * @return	"/mob/NoticeList"
     * @throws Exception
     */
    @IncludedInfo(name="모바일 공지사항", order = 680 ,gid = 50)
    @RequestMapping(value="/mob/NoticeList.do")
    public String selectNoticeList(@ModelAttribute("searchVO") MobManageDefaultVO searchVO, ModelMap model) throws Exception {
/*
    	*//** EgovPropertyService.LogList *//*
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	*//** pageing *//*
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		
        List LogList = MobManageService.selectLogList(searchVO);
        model.addAttribute("resultList", LogList);

        int totCnt = MobManageService.selectLogListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
*/
    	List test = null;
    	model.addAttribute("resultList", test);
    	model.addAttribute("paginationInfo", 0);
    	
        return "clikMng/mob/NoticeList";
    }

    /**
     * 모바일 - 공지사항 등록 화면
     * @param MobManageVO
     * @param searchVO
     * @param model
     * @return	"/mob/NoticeRegist"
     * @throws Exception
     */
     @RequestMapping("/mob/NoticeRegist.do")
     public String	 NoticeRegist(MobManageVO MobManageVO,
             @ModelAttribute("searchVO") MobManageDefaultVO searchVO,
             ModelMap model) throws Exception {


         return	"clikMng/mob/NoticeRegist";
     }

    
    
    /**
    * 모바일 - 공지사항 상세보기 화면
    * @param MobManageVO
    * @param searchVO
    * @param model
    * @return	"/mob/NoticeDetail"
    * @throws Exception
    */
    @RequestMapping("/mob/NoticeDetail.do")
    public String	NoticeDetail(MobManageVO MobManageVO,
            @ModelAttribute("searchVO") MobManageDefaultVO searchVO,
            ModelMap model) throws Exception {


        return	"clikMng/mob/NoticeDetail";
    }

    
    /**
     * 모바일 - 의견 보내기 목록 조회
     * @param searchVO
     * @param model
     * @return	"/mob/OnsList"
     * @throws Exception
     */
    @IncludedInfo(name="모바일 의견 보내기", order = 680 ,gid = 50)
    @RequestMapping(value="/mob/OnsList.do")
    public String selectOnsList(@ModelAttribute("mobManageVO") MobManageVO mobManageVO, Map commandMap, ModelMap model) throws Exception {

    	//** EgovPropertyService.LogList */
    	mobManageVO.setPageUnit(mobManageVO.getPageUnit());
    	mobManageVO.setPageSize(mobManageVO.getPageSize());
    	
    	mobManageVO.setSchDt1(mobManageVO.getSchDt1());
    	mobManageVO.setSchDt2(mobManageVO.getSchDt2());
    	
    	//** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mobManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mobManageVO.getPageUnit());
		paginationInfo.setPageSize(mobManageVO.getPageSize());

		mobManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mobManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mobManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(mobManageVO.getSelectCountperpg() != 0) {
			mobManageVO.setRecordCountPerPage(mobManageVO.getSelectCountperpg());
        } else {
        	mobManageVO.setRecordCountPerPage(mobManageVO.getRecordCountPerPage());
        }
		
		// 첫 페이지 로딩시 검색 기간은 기본 일주일
		if(mobManageVO.getSchDt1() == null || "".equals(mobManageVO.getSchDt1()) || 
				mobManageVO.getSchDt2() == null || "".equals(mobManageVO.getSchDt2()) ) {
    		
			CalUtil calUtil = new CalUtil();
    		calUtil.setDelimiter("-");
    		calUtil.setDecimalFormat("00");
    		String beforeWeek 	= calUtil.getBeforeWeek();
    		String today 		= calUtil.getToday();
    		
    		if(mobManageVO.getSchDt1() == null || "".equals(mobManageVO.getSchDt1())){
    			mobManageVO.setSchDt1(beforeWeek);
    		}
    		if(mobManageVO.getSchDt2() == null || "".equals(mobManageVO.getSchDt2())){
    			mobManageVO.setSchDt2(today);
    		}
		}
		
        List mobList = MobManageService.selectMobOnsList(mobManageVO);
       
        int totCnt = MobManageService.selectMobOnsListTotCnt(mobManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", mobList);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("selectCountperpg", mobManageVO.getSelectCountperpg());
    	
        return "clikMng/mob/OnsList";
    }
    
    /**
     * 모바일 - 의견 보내기 삭제
     * @param searchVO
     * @param model
     * @return	"/mob/OnsDel"
     * @throws Exception
     */
    @IncludedInfo(name="모바일 의견 보내기", order = 680 ,gid = 50)
    @RequestMapping(value="/mob/OnsDel.do")
    public String deleteOnsDel(@ModelAttribute("searchVO") MobManageVO searchVO, ModelMap model) throws Exception {

    	String delSeq = searchVO.getDelSeq();
    	
    	String[] arrDelSeq = delSeq.split(",");
    	ArrayList<String> delSeqList = new ArrayList<String>();
    	for(int i=0; i<arrDelSeq.length; i++){
    		delSeqList.add(arrDelSeq[i]);
    	}
    	
    	searchVO.setDelSeqList(delSeqList);
		
        MobManageService.deleteMobOns(searchVO);
    	
        return "redirect:/mob/OnsList.do";
    }
    

    /**
     * 모바일 - 모바일 이용현황
     * @param searchVO
     * @param model
     * @return	"/mob/MusList"
     * @throws Exception
     */
    /*@IncludedInfo(name="모바일 이용현황", order = 680 ,gid = 50)*/
    @RequestMapping(value="/mob/MusList.do")
    public String selectMusList(@ModelAttribute("searchVO") MobManageDefaultVO searchVO, ModelMap model) throws Exception {

    	CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		String curYear = calUtil.getYear();
		String curMonth = calUtil.getMonth();

    	if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
    	
		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt()) || 
				searchVO.getEdDt() == null || "".equals(searchVO.getEdDt()) ) {
    		
    		//String beforeWeek 	= calUtil.getBeforeWeek();
    		String today 		= calUtil.getToday();
    		
    		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt())){
    			searchVO.setBgDt(today);
    		}
    		if(searchVO.getEdDt() == null || "".equals(searchVO.getEdDt())){
    			searchVO.setEdDt(today);
    		}
		}
		
		if(searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())){
    		searchVO.setSearchCondition("term");
    	}
    	
		if("term".equals(searchVO.getSearchCondition())){
			searchVO.setSchDt1(searchVO.getBgDt().replaceAll("-",""));
			searchVO.setSchDt2(searchVO.getEdDt().replaceAll("-",""));
		}else if("month".equals(searchVO.getSearchCondition())){
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
			String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
			if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
			
			searchVO.setSchDt1(searchVO.getBgYear() + searchVO.getBgMonth() + "01");
			searchVO.setSchDt2(searchVO.getEdYear() + searchVO.getEdMonth() + lastOfMonth);
		}
		

    	// 누적 설치, 실행 횟수 조회
		List<MobileLogVO>	resultSumList = MobManageService.selectMusSumList(searchVO);
    	// 일자별 조회
    	List<MobileLogVO>	resultList	=	MobManageService.selectMusList(searchVO);
        
        model.addAttribute("searchVO", searchVO);
        model.addAttribute("resultSumList", resultSumList);
    	model.addAttribute("resultList", resultList);
    	
        return "clikMng/mob/MusList";
    }
    
    /**
     * 모바일 - 모바일 이용현황 엑셀
     * @param searchVO
     * @param model
     * @return	"/mob/MusList"
     * @throws Exception
     */
    @IncludedInfo(name="모바일 이용현황", order = 680 ,gid = 50)
    @RequestMapping(value="/mob/MusExcel.do")
    public ModelAndView selectMusExcel(@ModelAttribute("searchVO") MobManageDefaultVO searchVO, ModelMap model) throws Exception {

    	CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		String curYear = calUtil.getYear();
		String curMonth = calUtil.getMonth();

    	if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
    	
		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt()) || 
				searchVO.getEdDt() == null || "".equals(searchVO.getEdDt()) ) {
    		
    		//String beforeWeek 	= calUtil.getBeforeWeek();
    		String today 		= calUtil.getToday();
    		
    		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt())){
    			searchVO.setBgDt(today);
    		}
    		if(searchVO.getEdDt() == null || "".equals(searchVO.getEdDt())){
    			searchVO.setEdDt(today);
    		}
		}
		
		if(searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())){
    		searchVO.setSearchCondition("term");
    	}
    	
		if("term".equals(searchVO.getSearchCondition())){
			searchVO.setSchDt1(searchVO.getBgDt().replaceAll("-",""));
			searchVO.setSchDt2(searchVO.getEdDt().replaceAll("-",""));
		}else if("month".equals(searchVO.getSearchCondition())){
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
			String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
			if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
			
			searchVO.setSchDt1(searchVO.getBgYear() + searchVO.getBgMonth() + "01");
			searchVO.setSchDt2(searchVO.getEdYear() + searchVO.getEdMonth() + lastOfMonth);
		}
		
		
		// 누적 설치, 실행 횟수 조회
		List<MobileLogVO>	resultSumList = MobManageService.selectMusSumList(searchVO);
    	// 일자별 조회
    	List<MobileLogVO>	resultList	=	MobManageService.selectMusList(searchVO);
    	
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("searchVO", searchVO);
		map.put("resultSumList", resultSumList);
		map.put("resultList", resultList);
    	
    	return new ModelAndView("MusExcel", map);

    }


    /**
     * 모바일 - 모바일자료 이용통계
     * @param searchVO
     * @param model
     * @return	"/mob/DusList"
     * @throws Exception
     */
    /*@IncludedInfo(name="모바일자료 이용통계", order = 680 ,gid = 50)*/
    @RequestMapping(value="/mob/DusList.do")
    public String selectDusList(@ModelAttribute("searchVO") MobManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		String curYear = calUtil.getYear();
		String curMonth = calUtil.getMonth();

    	if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
    	
		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt()) || 
				searchVO.getEdDt() == null || "".equals(searchVO.getEdDt()) ) {

    		//String beforeWeek 	= calUtil.getBeforeWeek();
    		String today 		= calUtil.getToday();
    		
    		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt())){
    			searchVO.setBgDt(today);
    		}
    		if(searchVO.getEdDt() == null || "".equals(searchVO.getEdDt())){
    			searchVO.setEdDt(today);
    		}
		}
		
		if(searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())){
    		searchVO.setSearchCondition("term");
    	}
    	
		if("term".equals(searchVO.getSearchCondition())){
			searchVO.setSchDt1(searchVO.getBgDt());
			searchVO.setSchDt2(searchVO.getEdDt());
			
			HashMap<String, List> itemMap = new HashMap();
			CmmCodeVO cmmCodeVO = new CmmCodeVO();

			// 카테고리
			List<EgovMap> categoryList = new ArrayList<EgovMap>();
			EgovMap eMap = new EgovMap();
			eMap.put("codeId", "ELD");
			eMap.put("codeIdNm", "전자도서관");
			categoryList.add(eMap);
			
			//자료유형은 전자도서관만
			cmmCodeVO.setCodeClCd("ELD");
			List<EgovMap> category2List = cmmCodeManageService.selectCmmCodeList(cmmCodeVO);
			
			//자료유형 전체 추가
			EgovMap eMap2 = new EgovMap();
			eMap2.put("codeId", "SC_ALL");
			eMap2.put("codeIdNm", "전체");
			category2List.add(0,eMap2);
			itemMap.put("ELD", category2List);
					    	
			//자료이용통계 조회
			List<UseLogSummaryVO> dusList = MobManageService.selectDusList(searchVO);
			
			//모바일접속 현황 조회
			String visitTotalCnt = MobManageService.selectMobileVisitTotalCnt(searchVO);
			
			model.addAttribute("visitTotalCnt", visitTotalCnt);
			model.addAttribute("searchVO", searchVO);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("itemMap", itemMap);
			model.addAttribute("dusList", dusList);
			
		}else if("month".equals(searchVO.getSearchCondition())){
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
			String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
			if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
			
			searchVO.setSchDt1(searchVO.getBgYear() + "-" + searchVO.getBgMonth() + "-01");
			searchVO.setSchDt2(searchVO.getEdYear() + "-" + searchVO.getEdMonth() + "-" + lastOfMonth);
			
			//자료이용통계 조회
			List<UseLogSummaryVO> dusList = MobManageService.selectMonDusList(searchVO);
			
			model.addAttribute("dusList", dusList);
		}
		
        return "clikMng/mob/DusList";
    } 
    
    @IncludedInfo(name="모바일자료 이용통계", order = 680 ,gid = 50)
    @RequestMapping("/mob/selectMobDusExcel.do")    
    public ModelAndView selectMobDusExcel(@ModelAttribute("searchVO") MobManageDefaultVO searchVO, ModelMap model) throws Exception {

    	CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		String curYear = calUtil.getYear();
		String curMonth = calUtil.getMonth();

    	if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
    	
		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt()) || 
				searchVO.getEdDt() == null || "".equals(searchVO.getEdDt()) ) {

    		//String beforeWeek 	= calUtil.getBeforeWeek();
    		String today 		= calUtil.getToday();
    		
    		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt())){
    			searchVO.setBgDt(today);
    		}
    		if(searchVO.getEdDt() == null || "".equals(searchVO.getEdDt())){
    			searchVO.setEdDt(today);
    		}
		}
		
		if(searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())){
    		searchVO.setSearchCondition("term");
    	}
		
		Map<String, Object> map = new HashMap<String, Object>();
    	
		if("term".equals(searchVO.getSearchCondition())){
			searchVO.setSchDt1(searchVO.getBgDt());
			searchVO.setSchDt2(searchVO.getEdDt());
			
			HashMap<String, List> itemMap = new HashMap();
			CmmCodeVO cmmCodeVO = new CmmCodeVO();

			// 카테고리
			List<EgovMap> categoryList = new ArrayList<EgovMap>();
			EgovMap eMap = new EgovMap();
			eMap.put("codeId", "ELD");
			eMap.put("codeIdNm", "전자도서관");
			categoryList.add(eMap);
			
			//자료유형은 전자도서관만
			cmmCodeVO.setCodeClCd("ELD");
			List<EgovMap> category2List = cmmCodeManageService.selectCmmCodeList(cmmCodeVO);
			
			//자료유형 전체 추가
			EgovMap eMap2 = new EgovMap();
			eMap2.put("codeId", "SC_ALL");
			eMap2.put("codeIdNm", "전체");
			category2List.add(0,eMap2);
			itemMap.put("ELD", category2List);
					    	
			//자료이용통계 조회
			List<UseLogSummaryVO> dusList = MobManageService.selectDusList(searchVO);
			
			//모바일접속 현황 조회
			String visitTotalCnt = MobManageService.selectMobileVisitTotalCnt(searchVO);

			map.put("visitTotalCnt", visitTotalCnt);
			map.put("searchVO", searchVO);
	    	map.put("categoryList", categoryList);
	    	map.put("itemMap", itemMap);
	    	map.put("dusList", dusList);
			
		}else if("month".equals(searchVO.getSearchCondition())){
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
			String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
			if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
			
			searchVO.setSchDt1(searchVO.getBgYear() + "-" + searchVO.getBgMonth() + "-01");
			searchVO.setSchDt2(searchVO.getEdYear() + "-" + searchVO.getEdMonth() + "-" + lastOfMonth);
			
			//자료이용통계 조회
			List<UseLogSummaryVO> dusList = MobManageService.selectMonDusList(searchVO);
			
			map.put("dusList", dusList);
		}
		
    	return new ModelAndView("MobDusExcel", map);

    }
}
