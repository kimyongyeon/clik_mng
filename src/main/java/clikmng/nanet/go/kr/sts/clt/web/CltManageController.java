package clikmng.nanet.go.kr.sts.clt.web;

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
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.sts.clt.service.CltManageService;
import clikmng.nanet.go.kr.sts.clt.service.CltManageVO;
import clikmng.nanet.go.kr.sts.clt.service.CltStsVO;
import egovframework.rte.fdl.property.EgovPropertyService;



/**
 * 수집통계를 처리하는 Controller 클래스
 */

@Controller
public class CltManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "CltManageService")
    private CltManageService CltManageService;

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
     * 수집통계
     * @param searchVO
     * @param model
     * @return 
     * @throws Exception
     */
    @IncludedInfo(name="수집통계 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/sts/clt/CltList.do")
    public Object selectCltList(@ModelAttribute("searchVO") CltManageVO searchVO, ModelMap model) throws Exception {

    	Calendar calendar = Calendar.getInstance();
    	String curYear = String.valueOf(calendar.get(Calendar.YEAR));
    	String curMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if(curMonth.length() == 1) curMonth = "0" + curMonth;
    	
    	if("year".equals(searchVO.getSearchTerm()) || searchVO.getSearchTerm() == null || "".equals(searchVO.getSearchTerm())){
    		searchVO.setBgMonth(null);
    		searchVO.setEdMonth(null);
    	}

		if(searchVO.getBgDt() != null) searchVO.setBgDt(searchVO.getBgDt().replaceAll("-",""));
		if(searchVO.getEdDt() != null) searchVO.setEdDt(searchVO.getEdDt().replaceAll("-",""));
		if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
		
		
		if("term_sum".equals(searchVO.getSearchTerm()) || "day".equals(searchVO.getSearchTerm())){
			searchVO.setSchDt1(searchVO.getBgDt() + "000000");
			searchVO.setSchDt2(searchVO.getEdDt() + "235959");
			
			searchVO.setSearchGubun(8);
			
		}else if("month".equals(searchVO.getSearchTerm())){
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
			String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
			if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
			
			searchVO.setSchDt1(searchVO.getBgYear() + searchVO.getBgMonth() + "01" + "000000");
			searchVO.setSchDt2(searchVO.getEdYear() + searchVO.getEdMonth() + lastOfMonth + "235959");
			
			searchVO.setSearchGubun(6);
		}else{
			searchVO.setSchDt1(searchVO.getBgYear() + "0101" + "000000");
			searchVO.setSchDt2(searchVO.getEdYear() + "1231" + "235959");
			
			searchVO.setSearchGubun(4);
		}

		List<CltStsVO> cltList = null;
		if("term_sum".equals(searchVO.getSearchTerm())){
			cltList = CltManageService.selectCltStsTermSumList(searchVO);
		}else{
			cltList = CltManageService.selectCltStsList(searchVO);
		}
		model.addAttribute("cltList", cltList);
		

        if(searchVO.getBgDt() != null && !"".equals(searchVO.getBgDt()))
        {
        	searchVO.setBgDt(searchVO.getBgDt().substring(0, 4) + 
        			"-" + searchVO.getBgDt().substring(4, 6) + 
        			"-" + searchVO.getBgDt().substring(6, 8));
        }
        if(searchVO.getEdDt() != null && !"".equals(searchVO.getEdDt()))
        {
        	searchVO.setEdDt(searchVO.getEdDt().substring(0, 4) + 
        			"-" + searchVO.getEdDt().substring(4, 6) + 
        			"-" + searchVO.getEdDt().substring(6, 8));
        }

		model.addAttribute("searchVO", searchVO);
		
		String reqType = searchVO.getReqType();
		if("excel".equals(reqType)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("searchVO", searchVO);
	    	map.put("cltList", cltList);

	    	return new ModelAndView("CltExcel", map);
		}
		
        return "clikMng/sts/clt/CltList";
    }
}
