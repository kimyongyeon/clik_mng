package clikmng.nanet.go.kr.ccm.ccd.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmClCode;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmClCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCode;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmDetailCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 
 * 공통분류코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
public class CmmCodeManageController {
	@Resource(name = "CmmCodeManageService")
    private CmmCodeManageService cmmCodeManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/**
	 * 공통분류코드를 등록한다.
	 * @param loginVO
	 * @param CmmClCode
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmClCodeRegist.do")
	public String insertCmmClCode (@ModelAttribute("CmmClCode") CmmClCode CmmClCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인VO에서  사용자 정보 가져오기
   	    LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
       	String frstRegisterId = loginVO.getMngrId();
    	
    	if (CmmCodeVO.getCommCd() == null ||CmmClCode.getClCode().equals("")) {
    		CmmClCode.setClCode(CmmCodeVO.getCommCd());
    		CmmClCode.setClCodeNm(CmmCodeVO.getCommCdNm());
    		CmmClCode.setUseAt("Y");
    	}
    	
		if(CmmClCode.getClCode() != null){
			CmmCodeVO vo = cmmCodeManageService.selectCmmClCode(CmmCodeVO);
			if(vo != null){
				model.addAttribute("message", "이미 등록된 분류코드가 존재합니다.");
				//return "clikMng/ccm/ccd/CcmCmmCodeList";
				return "forward:/ccm/ccd/CcmCmmCodeList.do";
			}
		}
		
		CmmClCode.setFrstRegisterId(frstRegisterId);
		
		cmmCodeManageService.insertCmmClCode(CmmClCode);
		return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }

    
	/**
	 * 공통코드를 등록한다.
	 * @param loginVO
	 * @param cmmnCode
	 * @param bindingResult
	 * @param model
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CcmCmmCodeRegist.do")
	public String insertCmmCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmCode cmmCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO			
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {    

    	if   (cmmCode.getClCode() != null
    		||!cmmCode.getClCode().equals("")) {

    		cmmCode.setCodeId(CmmCodeVO.getCommCd());
    		cmmCode.setCodeIdNm(CmmCodeVO.getCommCdNm());
    		cmmCode.setClCode(CmmCodeVO.getParentCd());
    		cmmCode.setUseAt("Y");
    	}

    	cmmCode.setFrstRegisterId(loginVO.getMngrId());
    	cmmCodeManageService.insertCmmCode(cmmCode);
        return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }
    
	/**
	 * 공통상세코드를 등록한다.
	 * @param loginVO
	 * @param cmmnCode
	 * @param bindingResult
	 * @param model
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CcmCmmDetailCodeRegist.do")
	public String insertCmmDetailCode (@ModelAttribute("cmmCode") CmmnDetailCode cmmCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO			
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {    
    	
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String	frstRegisterId = loginVO.getMngrId();
    	cmmCode.setFrstRegisterId(frstRegisterId);

    	if   (cmmCode.getCodeId() != null
    		||!cmmCode.getCodeId().equals("")) {

    		cmmCode.setCodeId(CmmCodeVO.getParentCd());
    		cmmCode.setCode(CmmCodeVO.getCommCd());
    		cmmCode.setCodeNm(CmmCodeVO.getCommCdNm());
    		cmmCode.setUseAt("Y");
    	}

    	
    	cmmCodeManageService.insertCmmDetailCode(cmmCode);
        return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }    
    

    /**
	 * 공통분류코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "clikMng/ccm/ccd/EgovCcmCmmClCodeList"
     * @throws Exception
     */
	@IncludedInfo(name="공통분류코드", listUrl="/ccm/ccd/CcmCmmCodeList.do", order = 960 ,gid = 60)
    @RequestMapping(value="/ccm/ccd/CcmCmmCodeList.do")
	public String selectCmmClCodeList (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") CmmClCodeVO searchVO
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO
			, @ModelAttribute("CmmDetailCodeVO") CmmDetailCodeVO CmmDetailCodeVO
			, @ModelAttribute("CmmCode") CmmCode CmmCode
			, ModelMap model
			) throws Exception {
	
		
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        if (!CmmCodeVO.getEnterDepth().equals("")){       
        	//대분류 입력
        	if (CmmCodeVO.getEnterDepth().equals("depth1")){
        		        		
            	CmmCodeVO.setParentCd("");
            	CmmCodeVO.setParentCdNm("");        		
        		CmmCodeVO.setCodeClCd("");
        		CmmCodeVO.setCodeId("");
        		CmmDetailCodeVO.setCode("");
        	}
        	//중분류 입력
        	else if (CmmCodeVO.getEnterDepth().equals("depth2")){
        		        		
        		CmmCodeVO.setCodeId("");
        		CmmDetailCodeVO.setCode("");        		
        	}
        	//소분류 입력
        	else if (CmmCodeVO.getEnterDepth().equals("depth3")){
        		
        		CmmDetailCodeVO.setCode("");
        	}	
        }
        else{
        	//대분류 조회
        	if (CmmCodeVO.getCodeDepth().equals("1")){
        		CmmCodeVO vo = cmmCodeManageService.selectCmmClCode(CmmCodeVO);
        		model.addAttribute("result", vo);
        	}
        	//중분류 조회
        	else if (CmmCodeVO.getCodeDepth().equals("2")){
        		CmmCodeVO vo = cmmCodeManageService.selectCmmCode(CmmCodeVO);
        		model.addAttribute("result", vo);
        	}
        	//소분류 조회
        	else if (CmmCodeVO.getCodeDepth().equals("3")){
        		CmmCodeVO vo = cmmCodeManageService.selectCmmCodeDetail(CmmCodeVO);
        		model.addAttribute("result", vo);
        	}        	
        }
		
		/** 공통분류 코드 조회 **/
        List CmmClCodeList = cmmCodeManageService.selectCmmClCodeList(CmmCodeVO);
        model.addAttribute("resultList", CmmClCodeList);                
        
        /** 공통 코드 조회 **/
        List CmmCodeListDept2 =cmmCodeManageService.selectCmmCodeList(CmmCodeVO);
        model.addAttribute("resultListDept2", CmmCodeListDept2);        
        
        /** 공통 상세코드 조회 **/		
		List CmmCodeListDept3 = cmmCodeManageService.selectCmmDetailCodeList(CmmCodeVO);
		model.addAttribute("resultListDept3", CmmCodeListDept3);
	
//        int totCnt = CmmClCodeManageService.selectCmmClCodeListTotCnt(searchVO);
//		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "clikMng/ccm/ccd/CcmCmmCodeList";
                                
	}

	
	/**
	 * 공통분류코드를 수정한다.
	 * @param loginVO
	 * @param CmmClCode
	 * @param CmmCodeVO	  
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmClCodeModify.do")
	public String modifyCmmClCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("CmmClCode") CmmClCode CmmClCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO vo = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	if (CmmCodeVO.getCommCd() == null ||CmmClCode.getClCode().equals("")) {
    		CmmClCode.setClCode(CmmCodeVO.getCommCd());
    		CmmClCode.setClCodeNm(CmmCodeVO.getCommCdNm());
    	}
    	
//    	if   (CmmClCode.getClCode() == null
//    		||CmmClCode.getClCode().equals("")) {
//    		return "clikMng/ccm/ccd/CcmCmmCodeList";
//    	}

//        beanValidator.validate(CmmClCode, bindingResult);
//		if (bindingResult.hasErrors()){
//    		return "clikMng/ccm/ccd/CcmCmmCodeList";
//		}
		
    	
		CmmClCode.setLastUpdusrId(vo.getMngrId());
		
		cmmCodeManageService.updateCmmClCode(CmmClCode);
		return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }	
    
	/**
	 * 공통코드를 수정한다.
	 * @param loginVO
	 * @param CmmCode
	 * @param CmmCodeVO	 
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmCodeModify.do")
	public String modifyCmmCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmCode cmmCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO	
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	
    	
    	if   (cmmCode.getClCode() != null
        		||!cmmCode.getClCode().equals("")) {

        		cmmCode.setCodeId(CmmCodeVO.getCommCd());
        		cmmCode.setCodeIdNm(CmmCodeVO.getCommCdNm());
        		cmmCode.setClCode(CmmCodeVO.getParentCd());
        		//cmmCode.setUseAt(CmmCodeVO.getUseAt());
        	}

        	cmmCode.setFrstRegisterId(loginVO.getMngrId());
        	cmmCodeManageService.updateCmmCode(cmmCode);
            
        	return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }	    
	
    
	/**
	 * 공통상세코드를 수정한다.
	 * @param loginVO
	 * @param CmmCode
	 * @param CmmCodeVO	 
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmDetailCodeModify.do")
	public String modifyCmmDetailCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmnDetailCode cmmCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO			
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	
    	
    		if (cmmCode.getCodeId() != null
        		||!cmmCode.getCodeId().equals("")) {

        		cmmCode.setCodeId(CmmCodeVO.getParentCd());
        		cmmCode.setCode(CmmCodeVO.getCommCd());
        		cmmCode.setCodeNm(CmmCodeVO.getCommCdNm());
        		//cmmCode.setUseAt("Y");
        	}

        	cmmCode.setFrstRegisterId(loginVO.getMngrId());
        	cmmCodeManageService.updateCmmDetailCode(cmmCode);
            
        	return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }	  
    
	/**
	 * 공통분류코드를 삭제한다.
	 * @param loginVO
	 * @param CmmClCode
	 * @param CmmCodeVO	  
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmClCodeDelete.do")
	public String deleteCmmClCode (@ModelAttribute("CmmClCode") CmmClCode CmmClCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        String lastUpdusrId = loginVO.getMngrId();
    	
    	String resultMsg ="";
    	if (CmmCodeVO.getCommCd() == null ||CmmClCode.getClCode().equals("")) {
    		CmmClCode.setClCode(CmmCodeVO.getCommCd());
    		CmmClCode.setClCodeNm(CmmCodeVO.getCommCdNm());
    	}

		CmmClCode.setLastUpdusrId(lastUpdusrId);
		
		
		try {
			cmmCodeManageService.deleteCmmClCode(CmmClCode);	
		} catch (Exception e){			
			//e.getMessage();
			resultMsg = ("코드삭제에 실패하였습니다.");
			model.addAttribute("resultMsg", resultMsg);
		}
		
		return "forward:/ccm/ccd/CcmCmmCodeList.do";			
		
    }	    
    
	/**
	 * 공통코드를 삭제한다.
	 * @param loginVO
	 * @param CmmCode
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmCodeDelete.do")
	public String deleteCmmCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmCode cmmCode	
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	
    		String resultMsg ="";
    		
        	cmmCode.setFrstRegisterId(loginVO.getMngrId());
        	
    		try {        	
    			cmmCodeManageService.deleteCmmCode(cmmCode);
    		} catch (Exception e){			
    			//e.getMessage();
    			resultMsg = ("코드삭제에 실패하였습니다.");
    			model.addAttribute("resultMsg", resultMsg);
    		}
    		    			
            
        	return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }	 
    
	/**
	 * 공통상세코드를 삭제한다.
	 * @param loginVO
	 * @param CmmCode
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmCodeDetailDelete.do")
	public String deleteCmmDetailCode (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmnDetailCode cmmCode	
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	
    		String resultMsg ="";
        	cmmCode.setFrstRegisterId(loginVO.getMngrId());
    		try {        	
    			cmmCodeManageService.deleteCmmDetailCode(cmmCode);
    		} catch (Exception e){        	    						
    			//e.getMessage();
    			resultMsg = ("코드삭제에 실패하였습니다.");
    			model.addAttribute("resultMsg", resultMsg);
    		}
    		    	    			
            
        	return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }    
    
	/**
	 * 공통상세코드 순번을 수정한다.
	 * @param loginVO
	 * @param CmmCode
	 * @param CmmCodeVO	 
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmDetailCodeOrdrModify.do")
	public String modifyCmmDetailCodeOrdr (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmnDetailCode cmmCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO			
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	    	
			cmmCode.setCodeId(CmmCodeVO.getParentCd());
    		
			String strCodeOrders = StringUtil.nullConvert(cmmCode.getCodeOrdrChg());
    		
    		if(strCodeOrders.length() > 1 && strCodeOrders.lastIndexOf(",") > 0){
    			String[] arrMenuOrders = strCodeOrders.split(",");
    			for(int i=0;i<arrMenuOrders.length;i++){
    				String[] arrInfo = arrMenuOrders[i].split("=");
    				cmmCode.setCode(arrInfo[0]);
    				cmmCode.setCodeOrdr(Integer.parseInt(arrInfo[1]));
    	        	cmmCodeManageService.updateCmmDetailCodeOrdr(cmmCode);
    			}
    		}        		
            
        	return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }	  
        
    /**
	 * 공통상세코드 순번을 수정한다.
	 * @param loginVO
	 * @param CmmCode
	 * @param CmmCodeVO	 
	 * @param bindingResult
	 * @return "clikMng/ccm/ccd/CcmCmmCodeList"
	 * @throws Exception
	 */
    @RequestMapping(value="/ccm/ccd/CmmCodeOrdrModify.do")
	public String modifyCmmCodeOrdr (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("cmmCode") CmmCode cmmCode
			, @ModelAttribute("CmmCodeVO") CmmCodeVO CmmCodeVO			
			, BindingResult bindingResult , ModelMap model
			) throws Exception {    
    	    	
			//cmmCode.setCodeId(CmmCodeVO.getParentCd());
    		
			String strCodeOrders = StringUtil.nullConvert(cmmCode.getCodeOrdrChg());
    		
    		if(strCodeOrders.length() > 1 && strCodeOrders.lastIndexOf(",") > 0){
    			String[] arrMenuOrders = strCodeOrders.split(",");
    			for(int i=0;i<arrMenuOrders.length;i++){
    				String[] arrInfo = arrMenuOrders[i].split("=");
    				cmmCode.setCodeId(arrInfo[0]);
    				cmmCode.setCodeOrdr(Integer.parseInt(arrInfo[1]));
    	        	cmmCodeManageService.updateCmmCodeOrdr(cmmCode);
    			}
    		}        		
            
        	return "forward:/ccm/ccd/CcmCmmCodeList.do";
    }	  
	
}