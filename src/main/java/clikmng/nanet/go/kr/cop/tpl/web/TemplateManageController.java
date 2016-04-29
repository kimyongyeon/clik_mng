package clikmng.nanet.go.kr.cop.tpl.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.tpl.service.TemplateManageService;
import clikmng.nanet.go.kr.cop.tpl.service.TemplateInf;
import clikmng.nanet.go.kr.cop.tpl.service.TemplateInfVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 템플릿 관리를 위한 컨트롤러 클래스
 * @author
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------        --------    ---------------------------
 *
 * </pre>
 */

@Controller
public class TemplateManageController {
	
	 
	 

    @Resource(name = "TemplateManageService")
    private TemplateManageService tmplatService;

    @Resource(name="CmmUseService")
    private CmmUseService cmmUseService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    //Logger log = Logger.getLogger(this.getClass());

    /**
     * 템플릿 목록을 조회한다.
     * 
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="템플릿관리", order = 200 ,gid = 40)
    @RequestMapping("/cop/tpl/selectTemplateInfs.do")
    public String selectTemplateInfs(@ModelAttribute("searchVO") TemplateInfVO tmplatInfVO, ModelMap model) throws Exception {
	tmplatInfVO.setPageUnit(propertyService.getInt("pageUnit"));
	tmplatInfVO.setPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	
	paginationInfo.setCurrentPageNo(tmplatInfVO.getPageIndex());
	paginationInfo.setRecordCountPerPage(tmplatInfVO.getPageUnit());
	paginationInfo.setPageSize(tmplatInfVO.getPageSize());

	tmplatInfVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	tmplatInfVO.setLastIndex(paginationInfo.getLastRecordIndex());
	tmplatInfVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = tmplatService.selectTemplateInfs(tmplatInfVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));
	
	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("paginationInfo", paginationInfo);

	return "clikMng/cop/tpl/EgovTemplateList";
    }

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     * 
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/tpl/selectTemplateInf.do")
    public String selectTemplateInf(@ModelAttribute("searchVO") TemplateInfVO tmplatInfVO, ModelMap model) throws Exception {

	ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
	
	codeVO.setCodeId("RKS013");
	List result = cmmUseService.selectCmmCodeDetail(codeVO);

	TemplateInfVO vo = tmplatService.selectTemplateInf(tmplatInfVO);

	model.addAttribute("TemplateInfVO", vo);
	model.addAttribute("resultList", result);

	return "clikMng/cop/tpl/EgovTemplateUpdt";
    }

    /**
     * 템플릿 정보를 등록한다.
     * 
     * @param searchVO
     * @param tmplatInfo
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/tpl/insertTemplateInf.do")
    public String insertTemplateInf(@ModelAttribute("searchVO") TemplateInfVO searchVO, @ModelAttribute("templateInf") TemplateInf templateInf,
	    BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

/*	// 사용자 인증 완료 후에 재코딩    	
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
*/
	
	beanValidator.validate(templateInf, bindingResult);

	if (bindingResult.hasErrors()) {
	    ComDefaultCodeVO vo = new ComDefaultCodeVO();
	    
	    vo.setCodeId("RKS013");
	    
	    List result = cmmUseService.selectCmmCodeDetail(vo);
	    
	    model.addAttribute("resultList", result);

	    return "clikMng/cop/tpl/EgovTemplateRegist";
	}

	//templateInf.setFrstRegisterId(user.getUniqId());
	//로그인 개발 후 다시 코딩	
	templateInf.setFrstRegisterId("TESTER");
	
/*
	if (isAuthenticated) {
	    tmplatService.insertTemplateInf(templateInf);
	}
*/
    tmplatService.insertTemplateInf(templateInf);
    
	return "forward:/cop/tpl/selectTemplateInfs.do";
    }

    /**
     * 템플릿 등록을 위한 등록페이지로 이동한다.
     * 
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/tpl/addTemplateInf.do")
    public String addTemplateInf(@ModelAttribute("searchVO") TemplateInfVO searchVO, ModelMap model) throws Exception {
	ComDefaultCodeVO vo = new ComDefaultCodeVO();
	
	vo.setCodeId("RKS013");
	
	List result = cmmUseService.selectCmmCodeDetail(vo);
	
	model.addAttribute("resultList", result);

	return "clikMng/cop/tpl/EgovTemplateRegist";
    }

    /**
     * 템플릿 정보를 수정한다.
     * 
     * @param searchVO
     * @param tmplatInfo
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/tpl/updateTemplateInf.do")
    public String updateTemplateInf(@ModelAttribute("searchVO") TemplateInfVO tmplatInfVO, @ModelAttribute("templateInf") TemplateInf templateInf,
	    BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

    /*	// 사용자 인증 완료 후에 재코딩    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    */

	beanValidator.validate(templateInf, bindingResult);

	if (bindingResult.hasErrors()) {
	    ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
	    
	    codeVO.setCodeId("RKS013");
	    
	    List result = cmmUseService.selectCmmCodeDetail(codeVO);

	    TemplateInfVO vo = tmplatService.selectTemplateInf(tmplatInfVO);

	    model.addAttribute("TemplateInfVO", vo);
	    model.addAttribute("resultList", result);

	    return "clikMng/cop/tpl/EgovTemplateUpdt";
	}
	
	// 사용자 인증 완료 후에 재코딩 

	//templateInf.setLastUpdusrId(user.getUniqId());
	templateInf.setLastUpdusrId("TESTER");
	
	   
/*	
	if (isAuthenticated) {
	    tmplatService.updateTemplateInf(templateInf);
	}
*/
    tmplatService.updateTemplateInf(templateInf);
    
	return "forward:/cop/tpl/selectTemplateInfs.do";
    }

    /**
     * 템플릿 정보를 삭제한다.
     * 
     * @param searchVO
     * @param tmplatInfo
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteTemplateInf.do")
    public String deleteTemplateInf(@ModelAttribute("searchVO") TemplateInfVO searchVO, @ModelAttribute("tmplatInf") TemplateInf tmplatInf,
	    SessionStatus status, ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

//	tmplatInf.setLastUpdusrId(user.getUniqId());
	
	if (isAuthenticated) {
	    tmplatService.deleteTemplateInf(tmplatInf);
	}

	return "forward:/cop/tpl/selectTemplateInfs.do";
    }

    /**
     * 팝업을 위한 템플릿 목록을 조회한다.
     * 
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/tpl/selectTemplateInfsPop.do")
    public String selectTemplateInfsPop(@ModelAttribute("searchVO") TemplateInfVO tmplatInfVO, Map<String, Object> commandMap, ModelMap model)
	    throws Exception {

	String typeFlag = (String)commandMap.get("typeFlag");
	
	if ("CLB".equals(typeFlag)) {
	    tmplatInfVO.setTypeFlag(typeFlag);
	    tmplatInfVO.setTmplatSeCode("TMPT03");
	} else if ("CMY".equals(typeFlag)) {
	    tmplatInfVO.setTypeFlag(typeFlag);
	    tmplatInfVO.setTmplatSeCode("TMPT02");
	} else {
	    tmplatInfVO.setTypeFlag(typeFlag);
	    tmplatInfVO.setTmplatSeCode("TMPT01");
	}
	
	tmplatInfVO.setPageUnit(propertyService.getInt("pageUnit"));
	tmplatInfVO.setPageSize(propertyService.getInt("pageSize"));
	//CMY, CLB

	PaginationInfo paginationInfo = new PaginationInfo();
	
	paginationInfo.setCurrentPageNo(tmplatInfVO.getPageIndex());
	paginationInfo.setRecordCountPerPage(tmplatInfVO.getPageUnit());
	paginationInfo.setPageSize(tmplatInfVO.getPageSize());

	tmplatInfVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	tmplatInfVO.setLastIndex(paginationInfo.getLastRecordIndex());
	tmplatInfVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = tmplatService.selectTemplateInfs(tmplatInfVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));
	
	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("typeFlag", typeFlag);
	model.addAttribute("paginationInfo", paginationInfo);

	return "clikMng/cop/tpl/EgovTemplateInqirePopup";
    }
}
