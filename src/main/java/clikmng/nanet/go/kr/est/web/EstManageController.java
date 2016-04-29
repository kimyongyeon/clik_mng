package clikmng.nanet.go.kr.est.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.est.service.EstManageDefaultVO;
import clikmng.nanet.go.kr.est.service.EstManageService;
import clikmng.nanet.go.kr.est.service.EstManageVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 환경설정 관리를 처리하는 Controller 클래스
 */

@Controller
public class EstManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "EstManageService")
    private EstManageService estManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 환경설정
     * @param searchVO
     * @param model
     * @return	"/est/EnvEstbs"
     * @throws Exception
     */
    @IncludedInfo(name="환경설정", order = 680 ,gid = 50)
    @RequestMapping(value="/est/EnvEstbs.do")
    public String estInqire(@ModelAttribute("searchVO") EstManageVO searchVO, ModelMap model) throws Exception {

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 1. 자동/수동 게시 승인 목록
    	List<EstManageVO> ntctAtList = estManageService.selectNtctAtList();

    	// 2. 자동/수동 PDF 변환 승인 목록
    	List<EstManageVO> cnvrAtList = estManageService.selectCnvrAtList();
    	
		
		model.addAttribute("ntctAtList", ntctAtList);
		model.addAttribute("ntctAtListSize", ntctAtList.size());
		model.addAttribute("cnvrAtList", cnvrAtList);
		model.addAttribute("cnvrAtListSize", cnvrAtList.size());
		
        return "clikMng/est/EnvEstbs";
    }
    
    /**
     * 환경설정
     * @param searchVO
     * @param model
     * @return	"/est/ApplySetup.do"
     * @throws Exception
     */
    @IncludedInfo(name="환경설정 적용", order = 680 ,gid = 50)
    @RequestMapping(value="/est/ApplySetup.do")
    public String estApply(@ModelAttribute("searchVO") EstManageVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 0-1. 수정자 ID
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	searchVO.setLastUpdusrId(loginVO.getMngrId());
    	
    	// 1. 수정
    	String[] ArrayKeyValue;
    	// 1. 자동게시 여부 확인
    	for(int i=1; i<searchVO.getNtctAtListSize()+1; i++){
    		ArrayKeyValue = new String[i];
    		ArrayKeyValue = StringUtil.isNullToString(request.getParameter("checkAuto_"+i)).split("_");
    		
    		searchVO.setAtmcNtceAt(ArrayKeyValue[0]);
    		searchVO.setColctInfoTyCode(ArrayKeyValue[1]); 
    		
    		// 1-1 수정처리
    		estManageService.updateNtctAt(searchVO);
   
    	}

    	// 2. 자동PDF변환 여부 확인
    	for(int i=1; i<searchVO.getCnvrAtListSize()+1; i++){
    		ArrayKeyValue = new String[i];
    		
    		ArrayKeyValue = StringUtil.isNullToString(request.getParameter("checkPdfAuto_"+i)).split("_");
    		
    		searchVO.setAtmcCnvrAt(ArrayKeyValue[0]);
    		searchVO.setColctInfoTyCode(ArrayKeyValue[1]); 

    		
    		estManageService.updateCnvrAt(searchVO);
    	}    	
    	
		
        return "forward:/est/EnvEstbs.do";
    }
    

}
