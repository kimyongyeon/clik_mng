package clikmng.nanet.go.kr.mna.ram.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.SessionVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mna.ram.service.AuthorManage;
import clikmng.nanet.go.kr.mna.ram.service.AuthorManageVO;
import clikmng.nanet.go.kr.mna.ram.service.AuthorManageService;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한관리에 관한 controller 클래스를 정의한다.
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
 

@Controller
@SessionAttributes(types=SessionVO.class)
public class AuthorManageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "AuthorManageService")
    private AuthorManageService AuthorManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    /**
	 * 권한 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/mna/ram/EgovAuthorListView.do")
    public String selectAuthorListView(ModelMap model)
            throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
        return "clikMng/mna/ram/EgovAuthorManage";
    }    
    
    /**
	 * 권한 목록을 조회한다
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="권한관리", listUrl="/mna/ram/EgovAuthorList.do", order = 60,gid = 20)
    @RequestMapping(value="/mna/ram/EgovAuthorList.do")
    public String selectAuthorList(@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, 
    		                        ModelMap model)
            throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	/** EgovPropertyService.sample */
    	authorManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	authorManageVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
		paginationInfo.setPageSize(authorManageVO.getPageSize());
		
		authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// Tap 구분( 0. CLIK : 사용자 , 1. MNG : 관리자 )
		authorManageVO.setAuthorClCode(StringUtil.nullConvert(authorManageVO.getAuthorClCode()));
    	if(authorManageVO.getAuthorClCode().equals("")){
    		authorManageVO.setAuthorClCode("CLIK");
    	}
		
		authorManageVO.setAuthorManageList(AuthorManageService.selectAuthorList(authorManageVO));
        model.addAttribute("authorList", authorManageVO.getAuthorManageList());
        
        int totCnt = AuthorManageService.selectAuthorListTotCnt(authorManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        if(authorManageVO.getAuthorClCode().equals("CLIK")){
        	return "clikMng/mna/ram/EgovAuthorManage";
        } else {
        	return "clikMng/mna/ram/AuthorManageMng";
        }
    } 
    
    
    /**
	 * 권한 세부정보를 조회한다.
	 * @param authorCode String
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */   
    @RequestMapping(value="/mna/ram/EgovAuthor.do")
    public String selectAuthor(@RequestParam("authorCode") String authorCode,
    	                       @ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, 
    		                    ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	authorManageVO.setAuthorCode(authorCode);

    	model.addAttribute("authorManage", AuthorManageService.selectAuthor(authorManageVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "clikMng/mna/ram/EgovAuthorUpdate";
    }     

    /**
	 * 권한 등록화면 이동
	 * @return String
	 * @exception Exception
	 */     
    @RequestMapping("/mna/ram/EgovAuthorInsertView.do")
    public String insertAuthorView(HttpServletRequest request,ModelMap model)
            throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	model.addAttribute("authorClCode", StringUtil.isNullToString(request.getParameter("authorClCode")));
    	
        return "clikMng/mna/ram/EgovAuthorInsert";
    }
    
    /**
	 * 권한 세부정보를 등록한다.
	 * @param authorManage AuthorManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */ 
    @RequestMapping(value="/mna/ram/EgovAuthorInsert.do")
    public String insertAuthor(@ModelAttribute("authorManage") AuthorManage authorManage, 
    		                    BindingResult bindingResult,
    		                    SessionStatus status, 
    		                    ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	beanValidator.validate(authorManage, bindingResult); //validation 수행
    	
		if (bindingResult.hasErrors()) { 
			return "clikMng/mna/ram/EgovAuthorInsert";
		} else {
	    	AuthorManageService.insertAuthor(authorManage);
	        status.setComplete();
	        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	        return "forward:/mna/ram/EgovAuthor.do";
		}
    }
    
    /**
	 * 권한 세부정보를 수정한다.
	 * @param authorManage AuthorManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */   
    @RequestMapping(value="/mna/ram/EgovAuthorUpdate.do")
    public String updateAuthor(@ModelAttribute("authorManage") AuthorManage authorManage, 
    		                    BindingResult bindingResult,
    		                    SessionStatus status, 
    		                    Model model) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	beanValidator.validate(authorManage, bindingResult); //validation 수행
    	
		if (bindingResult.hasErrors()) {
			return "clikMng/mna/ram/EgovAuthorUpdate";
		} else {
	    	AuthorManageService.updateAuthor(authorManage);
	        status.setComplete();
	        model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
	        return "forward:/mna/ram/EgovAuthor.do";
		}
    }    

    /**
	 * 권한 세부정보를 삭제한다.
	 * @param authorManage AuthorManage
	 * @return String
	 * @exception Exception
	 */  
    @RequestMapping(value="/mna/ram/EgovAuthorDelete.do")
    public String deleteAuthor(@ModelAttribute("authorManage") AuthorManage authorManage, 
    		                    SessionStatus status,
    		                    Model model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	AuthorManageService.deleteAuthor(authorManage);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/mna/ram/EgovAuthorList.do";
    }   
    
    /**
	 * 권한목록을 삭제한다.
	 * @param authorCodes String
	 * @param authorManage AuthorManage
	 * @return String
	 * @exception Exception
	 */  
    @RequestMapping(value="/mna/ram/EgovAuthorListDelete.do")
    public String deleteAuthorList(@RequestParam("authorCodes") String authorCodes,
    		                       @ModelAttribute("authorManage") AuthorManage authorManage, 
    		                        SessionStatus status,
    		                        Model model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	String [] strAuthorCodes = authorCodes.split(";");
    	for(int i=0; i<strAuthorCodes.length;i++) {
    		authorManage.setAuthorCode(strAuthorCodes[i]);
    		AuthorManageService.deleteAuthor(authorManage);
    	}
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/mna/ram/EgovAuthorList.do";
    }    
    
    /**
	 * 권한제한 화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/mna/ram/accessDenied.do")
    public String accessDenied()
            throws Exception {
        return "clikMng/sec/accessDenied";
    } 
}
