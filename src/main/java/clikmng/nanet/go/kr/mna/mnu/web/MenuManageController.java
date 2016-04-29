package clikmng.nanet.go.kr.mna.mnu.web;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.ComDefaultVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mna.mnu.service.MenuManageService;
import clikmng.nanet.go.kr.mna.mnu.service.MenuManageVO;
import clikmng.nanet.go.kr.sit.prm.service.ProgrmManageService;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메뉴목록 관리및 메뉴생성을 처리하는 비즈니스 구현 클래스
 * @author 
 * @since 
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 */

@Controller
public class MenuManageController {

	protected Log log = LogFactory.getLog(this.getClass());

	/* Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	/** PropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** MenuManageService */
	@Resource(name = "meunManageService")
    private MenuManageService menuManageService;

    /** MenuManageService */
	@Resource(name = "progrmManageService")
	private ProgrmManageService progrmManageService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;


  
    /**
     * 레벨별 메뉴리스트를 조회한다.
     * @param MenuManageVO
     * @return 출력페이지정보 "mna/mnu/MenuList"
     * @exception Exception
     */
    @IncludedInfo(name="메뉴리스트관리", order = 1090 ,gid = 60)
    @RequestMapping(value="/mna/mnu/MenuList.do")
    public String selectMenuList(
    		@ModelAttribute("searchVO") MenuManageVO searchVO,
    		ModelMap model)
            throws Exception {
    	
       	String resultMsg    = "";
/*       	
       	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	//return "clikMng/uat/uia/LoginUsr";
        	return "forward:/uat/uia/LoginUsr.do";
    	}
*/
    	String level = StringUtil.nullConvert(searchVO.getMenuLevel());       	    
       	
       	searchVO.setEnterLevel(StringUtil.nullConvert(searchVO.getEnterLevel()));
       	//메뉴 신규 입력
       	if (!searchVO.getEnterLevel().equals("")){
       		//대분류 입력
       		if (searchVO.getEnterLevel().equals("depth1")){
           		searchVO.setUpperMenuId(0);
       			searchVO.setMenuListdepth1("");
       			searchVO.setMenuListdepth2("");
       			searchVO.setMenuListdepth3("");
       		}
       		//중분류 입력
       		if (searchVO.getEnterLevel().equals("depth2")){
       			searchVO.setMenuListdepth2("");
       			searchVO.setMenuListdepth3("");
       		}       
       		//소분류 입력
       		if (searchVO.getEnterLevel().equals("depth3")){
       			searchVO.setMenuListdepth3("");
       		}       		       		
       		searchVO.setMenuNo(0);
       		searchVO.setMenuNm("");
       	}
        //상세내용 조회
       	else {       		
       		if (!level.equals("")){
       			MenuManageVO resultVO = menuManageService.selectMenuManage(searchVO);
       			model.addAttribute("menuManageVO", resultVO);
       		}        	       		
       	}
       	
    	//대메뉴 목록을 조회
        searchVO.setMenuLevel("1");
    	searchVO.setMenuClCode(StringUtil.nullConvert(searchVO.getMenuClCode()));
    	
    	if(searchVO.getMenuClCode().equals(""))
    		searchVO.setMenuClCode("CLIK");
    	
    	List menuList1 = menuManageService.selectMenuListByLevel(searchVO);
    	
    	//중메뉴 목록을 조회
    	searchVO.setMenuLevel("2");    	
    	List menuList2 = menuManageService.selectMenuListByLevel(searchVO);    	
    	
    	//소메뉴 목록을 조회
    	searchVO.setMenuLevel("3"); 
    	List menuList3 = menuManageService.selectMenuListByLevel(searchVO);    	
    	
    	
    	searchVO.setMenuLevel(level);
    	//resultMsg = egovMessageSource.getMessage("success.common.select");
    	
        model.addAttribute("menuList1", menuList1);
        model.addAttribute("menuList2", menuList2);
        model.addAttribute("menuList3", menuList3);        
        model.addAttribute("resultMsg", resultMsg);
        
      	return  "clikMng/mna/mnu/MenuList";
    }

    /**
     * 메뉴리스트의 메뉴정보를 등록한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "/mna/mnu/MenuListInsert.do"
     * @exception Exception
     */
    @RequestMapping(value="/mna/mnu/MenuListInsert.do")
    public String insertMenuList(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	
    	// 0. Spring Security 사용자권한 처리
/*    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/EgovLoginUsr";
    	}
*/    	

//        beanValidator.validate(menuManageVO, bindingResult);
//		if (bindingResult.hasErrors()){
//			sLocationUrl = "clikMng/mna/mnu/EgovMenuList";
//			return sLocationUrl;
//		}

		if(menuManageService.selectMenuNoByPk(menuManageVO) == 0){
			ComDefaultVO searchVO = new ComDefaultVO();
			searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
			// 프로그램 관리 URL등록을 메뉴관리에서 직접 등록 함.
			//if(progrmManageService.selectProgrmNMTotCnt(searchVO)==0){
	    	//	resultMsg = egovMessageSource.getMessage("fail.common.insert");
		    //    sLocationUrl = "forward:/mna/mnu/MenuList.do";
			//}else{
	        	menuManageService.insertMenuManage(menuManageVO);
	    		resultMsg = egovMessageSource.getMessage("success.common.insert");
		        sLocationUrl = "forward:/mna/mnu/MenuList.do";
			//}
		}else{
    		resultMsg = egovMessageSource.getMessage("common.isExist.msg");
    		sLocationUrl = "forward:/mna/mnu/MenuList.do";
		}
		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    }
    
    
    /**
     * 메뉴리스트의 메뉴정보를 수정한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "mna/mnu/MenuList"
     * @exception Exception
     */
    @RequestMapping(value="/mna/mnu/MenuListUpdt.do")
    public String updateMenuList(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
    	
        String sLocationUrl = null;
    	String resultMsg    = "";
    	
    	// 0. Spring Security 사용자권한 처리
/*    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/EgovLoginUsr";
    	}
*/        
		
		ComDefaultVO searchVO = new ComDefaultVO();
		searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
		/*
		 * 메뉴관리에 프로그램 URL을 바로 등록하기 때문에 기존 정보를 검색할 필요가 없음
		 
		if(progrmManageService.selectProgrmNMTotCnt(searchVO)==0){
    		resultMsg = egovMessageSource.getMessage("fail.common.update");
    		System.out.println("resultMsg =================  " + resultMsg);
    		System.out.println("resultMsg =================  " + resultMsg);
    		System.out.println("resultMsg =================  " + resultMsg);
	        sLocationUrl = "forward:/mna/mnu/MenuList.do";
		}else{
		}
		*/
		
		menuManageService.updateMenuManage(menuManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.update");
        sLocationUrl = "forward:/mna/mnu/MenuList.do";

		
		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    }    
    

    /**
     * 메뉴리스트의 메뉴정보를 삭제한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "/mna/mnu/MenuList"
     * @exception Exception
     */
    @RequestMapping(value="/mna/mnu/MenuListDelete.do")
    public String deleteMenuList(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	
    	// 0. Spring Security 사용자권한 처리
/*    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/EgovLoginUsr";
    	}
*/
    	
		menuManageService.deleteMenuManage(menuManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.delete");
        sLocationUrl = "forward:/mna/mnu/MenuList.do";
		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    } 
    
    /**
     * 메뉴리스트의 메뉴순번을 변경한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "/mna/mnu/MenuList"
     * @exception Exception
     */    
    @RequestMapping(value="/mna/mnu/MenuOrdUpdt.do")
    public String updateMenuOrd(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		BindingResult bindingResult,
    		ModelMap model)
            throws Exception {
        String sLocationUrl = null;
    	String resultMsg    = "";
    	
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
    	String strMenuOrders = StringUtil.nullConvert(menuManageVO.getMenuOrdChg());
    	
		if(strMenuOrders.length() > 1 && strMenuOrders.lastIndexOf(",") > 0){
			String[] arrMenuOrders = strMenuOrders.split(",");
			for(int i=0;i<arrMenuOrders.length;i++){
				String[] arrInfo = arrMenuOrders[i].split("=");				
				menuManageVO.setMenuNo(Integer.parseInt(arrInfo[0]));
				menuManageVO.setMenuOrdr(Integer.parseInt(arrInfo[1]));
				menuManageService.updateMenuOrd(menuManageVO);
			}
		}
    					
		resultMsg = egovMessageSource.getMessage("success.common.update");
        sLocationUrl = "forward:/mna/mnu/MenuList.do";

		model.addAttribute("resultMsg", resultMsg);
      	return sLocationUrl;
    }     
    
    /**
     * 메뉴리스트의 메뉴정보를 사용 테이블에 적용한다.
     * @param menuManageVO MenuManageVO
     * @return 출력페이지정보 "/mna/mnu/ApplyMenuList.do"
     * @exception Exception
     */
    @RequestMapping(value="/mna/mnu/ApplyMenuList.do")
    public String applyMenuList(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		ModelMap model)
            throws Exception {
    	
    	String resultMsg    = "";
    	// 0. Spring Security 사용자권한 처리
/*    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/EgovLoginUsr";
    	}
*/
    	
    	// 1. 메뉴를 LIST로 담는다.
    	List<MenuManageVO> selMenuList = menuManageService.selectApplyMenuList();
    	
    	// 2. 실 테이블 데이터를 삭제.
    	menuManageService.deleteUseMenu();
    	
    	// 3. 실 테이블에 데이터를 Insert.
		try {
        	for(int i=0; i<selMenuList.size(); i++) {
        		MenuManageVO applyMenuListMap = (MenuManageVO)selMenuList.get(i);
        		
        		menuManageService.insertApplyMenuList(applyMenuListMap);
        	}	
		} catch (Exception e){			
			//e.getMessage();
			resultMsg = ("적용 테이블 입력에 실패하였습니다.");
			model.addAttribute("resultMsg", resultMsg);
		}
    	
		return "forward:/mna/mnu/MenuList.do";
    	
    }    
    
}