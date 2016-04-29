package clikmng.nanet.go.kr.sit.pwm.web;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sit.pwm.service.PopupManageService;
import clikmng.nanet.go.kr.sit.pwm.service.PopupManageVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 팝업창에 대한 Controller를 정의한다.
 * 
 * 상세내용
 * - 팝업창에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 팝업창의 조회기능은 목록조회, 상세조회로, 사용자 화면 보기로 구분된다.
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:19:57
  *
  * </pre>
 */

@Controller
public class PopupManageController {
	
	 
	 

    protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** PopupManageService */
    @Resource(name = "popupManageService")
    private PopupManageService popupManageService;
    
    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;    

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;    
    
    /**
     * 팝업창관리 목록을 조회한다.
     * @param popupManageVO
     * @param model
     * @return "clikMng/sit/pwm/listPopupManage"
     * @throws Exception
     */
    @IncludedInfo(name="팝업창관리", order = 720 ,gid = 50)
    @RequestMapping(value = "/sit/pwm/listPopup.do")
    public String egovPopupManageList(
            Map commandMap,
            PopupManageVO popupManageVO, 
            ModelMap model)
            throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


        /** EgovPropertyService.sample */
    	if(popupManageVO.getPageUnit() == 0){
    		popupManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	}
    	popupManageVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(popupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(popupManageVO.getPageUnit());
		paginationInfo.setPageSize(popupManageVO.getPageSize());

		popupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		popupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		popupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List reusltList = popupManageService.selectPopupList(popupManageVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        
        int totCnt = (Integer) popupManageService.selectPopupListCount(popupManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "clikMng/sit/pwm/EgovPopupList";
    }
    
    /**
     * 팝업관리 목록을 상세조회 조회한다.
     * @param popupManageVO
     * @param commandMap
     * @param model
     * @return 
     *         "/sit/pwm/detailPopupManage"
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/detailPopup.do")
    public String egovPopupManageDetail(
    		HttpServletRequest request,
            PopupManageVO popupManageVO,
            FileVO fileVO,
            Map commandMap,
            ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


        String sLocationUrl = "clikMng/sit/pwm/EgovPopupDetail";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        
        //팝업창시작일자(시)
        model.addAttribute("ntceBgndeHH", (List)getTimeHH());
        //팝업창시작일자(분)
        model.addAttribute("ntceBgndeMM", (List)getTimeMM());
        //팝업창종료일자(시)
        model.addAttribute("ntceEnddeHH", (List)getTimeHH());
        //팝업창정료일자(분)
        model.addAttribute("ntceEnddeMM", (List)getTimeMM());


    	//상세정보 불러오기
    	if(StringUtil.isNullToString(request.getParameter("rpopupId")) != "") {
    		popupManageVO.setPopupId(request.getParameter("rpopupId"));
    	} 
    	
        PopupManageVO popupManageVOs = popupManageService.selectPopup(popupManageVO);
        
        String sNtceBgnde = popupManageVOs.getNtceBgnde();
        String sNtceEndde = popupManageVOs.getNtceEndde();
        
        popupManageVOs.setNtceBgndeHH(sNtceBgnde.substring(8, 10));
        popupManageVOs.setNtceBgndeMM(sNtceBgnde.substring(10, 12));
        
        popupManageVOs.setNtceEnddeHH(sNtceEndde.substring(8, 10));
        popupManageVOs.setNtceEnddeMM(sNtceEndde.substring(10, 12));
        
        model.addAttribute("popupManageVO", popupManageVOs);
    	
        //파일정보 불러오기
    	String atchFileId = popupManageVOs.getImageFileNm();

    	fileVO.setAtchFileId(atchFileId);

    	List<FileVO> result = fileMngService.selectFileInfs(fileVO);
    	
    	model.addAttribute("fileList", result);
    	model.addAttribute("fileListCnt", result.size());
    	model.addAttribute("atchFileId", atchFileId);            
        

        return sLocationUrl;
    }
    
    
    /**
     * 통합링크관리를 수정한다.
     * @param searchVO
     * @param popupManageVO
     * @param bindingResult
     * @param model
     * @return 
     *         "/sit/pwm/updtPopupManage"
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/updtPopup.do")
    public String egovPopupManageUpdt(final MultipartHttpServletRequest multiRequest,
            Map commandMap,
            PopupManageVO popupManageVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
    	
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
	    //아이디 설정
	    popupManageVO.setFrstRegisterId((String)loginVO.getMngrId());
	    popupManageVO.setLastUpdusrId((String)loginVO.getMngrId());
    	
        String sLocationUrl = "";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

        if (sCmd.equals("save")) {
            sLocationUrl = "forward:/sit/pwm/listPopup.do";
            //서버  validate 체크
            beanValidator.validate(popupManageVO, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            } else {
    	    	List<FileVO> result = null;

    	    	String uploadFolder = "Globals.popupFileStorePath";
    	    	String atchFileId = "";

    	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

    	    	if(!files.isEmpty()){
    	    	    result = fileUtil.parseFileInf(files, "POPUP_", 0, "", uploadFolder);
    	    	    atchFileId = fileMngService.insertFileInfs(result);

//    	        	FileVO vo = null;
//    	        	Iterator iter = result.iterator();
/*
    	        	while (iter.hasNext()) {
    	        	    vo = (FileVO)iter.next();
    	        	    popupImage = vo.getOrignlFileNm();
    	        	    popupImageFile = vo.getStreFileNm();
    	        	}
*/
	        		popupManageVO.setImageFileNm(atchFileId);
	        		popupManageVO.setAtchFile(true);
    	    	} else {
    	    		popupManageVO.setAtchFile(false);
    	    	}
            }
            //저장
            popupManageService.updatePopup(popupManageVO);
        }

        return sLocationUrl;
    }

    /**
     * 팝업 등록화면
     * @param searchVO
     * @param popupManageVO
     * @param bindingResult
     * @param model
     * @return 
     *         "/sit/pwm/registPopupManage"
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/registPopup.do")
    public String egovPopupManageRegist(
    		Map commandMap,
    		@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
    	
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	//아이디 설정
        popupManageVO.setFrstRegisterId(loginVO.getMngrId());
        popupManageVO.setLastUpdusrId(loginVO.getMngrId());
    	
        String sLocationUrl = "clikMng/sit/pwm/EgovPopupRegist";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        log.info("cmd =>" + sCmd);

        
        //팝업창시작일자(시)
        model.addAttribute("ntceBgndeHH", (List)getTimeHH());
        //팝업창시작일자(분)
        model.addAttribute("ntceBgndeMM", (List)getTimeMM());
        //팝업창종료일자(시)
        model.addAttribute("ntceEnddeHH", (List)getTimeHH());
        //팝업창정료일자(분)
        model.addAttribute("ntceEnddeMM", (List)getTimeMM());
        
        return sLocationUrl;
    }    
    
    
    
    /**
     * 팝업을 등록한다.
     * @param searchVO
     * @param popupManageVO
     * @param bindingResult
     * @param model
     * @return 
     *         "/sit/pwm/registPopupManage"
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/registPopupProc.do")
    public String egovPopupManageRegisProct(final MultipartHttpServletRequest multiRequest,
    		Map commandMap,
    		@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
    	
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	//아이디 설정
        popupManageVO.setFrstRegisterId(loginVO.getMngrId());
        popupManageVO.setLastUpdusrId(loginVO.getMngrId());
    	
        String sLocationUrl = "forward:/sit/pwm/listPopup.do";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        log.info("cmd =>" + sCmd);

            //서버  validate 체크
            beanValidator.validate(popupManageVO, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            } else {
    	    	List<FileVO> result = null;

    	    	String uploadFolder = "Globals.popupFileStorePath";
    	    	String atchFileId = "";

    	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

    	    	if(!files.isEmpty()){
    	    	    result = fileUtil.parseFileInf(files, "POPUP_", 0, "", uploadFolder);
    	    	    atchFileId = fileMngService.insertFileInfs(result);

    	        	popupManageVO.setImageFileNm(atchFileId);
    	    	}
            }
            
            //저장
            popupManageService.insertPopup(popupManageVO);
            
            sLocationUrl = "forward:/sit/pwm/listPopup.do";
        
        return sLocationUrl;
    }
 
    /**
     * 팝업창정보를 삭제한다.
     * @param commandMap
     * @param popupManageVO
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/ajaxPopupManageDelte.do")
    public void egovPopupManageDelteAjax(
            Map commandMap,
            HttpServletResponse response,
            PopupManageVO popupManageVO
            ) throws Exception {

		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
      
       	popupManageService.deletePopup(popupManageVO);

		out.print("true");
		out.flush();
    }
    
    /**
     * 팝업창정보를 조회한다.
     * @param commandMap
     * @param popupManageVO
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/ajaxPopupManageInfo.do")
    public void egovPopupManageInfoAjax(
            Map commandMap,
            HttpServletResponse response,
            PopupManageVO popupManageVO
            ) throws Exception {

       response.setHeader("Content-Type", "text/html;charset=utf-8");
       PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
      
       log.debug("commandMap : " + commandMap);
       log.debug("popupManageVO : " + popupManageVO);
       
       PopupManageVO popupManageVOs = popupManageService.selectPopup(popupManageVO);
      
       String sPrint = "";
       sPrint = popupManageVOs.getFileUrl();
       sPrint = sPrint + "||"+popupManageVOs.getPopupWSize();
       sPrint = sPrint + "||"+popupManageVOs.getPopupHSize();
       sPrint = sPrint + "||"+popupManageVOs.getPopupHlc();
       sPrint = sPrint + "||"+popupManageVOs.getPopupWlc();
       sPrint = sPrint + "||"+popupManageVOs.getStopVewAt();
       sPrint = sPrint + "||"+popupManageVOs.getImageFileNm();
       out.print(sPrint);
       out.flush();
    }
    
    /**
     * 팝업창을 오픈 한다.
     * @param commandMap
     * @param popupManageVO
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "/sit/pwm/openPopupManage.do")
    public String egovPopupManagePopupOpen(
                @RequestParam("fileUrl") String fileUrl,
                @RequestParam("stopVewAt") String stopVewAt,
                @RequestParam("popupId") String popupId,
                @RequestParam("imageFileNm") String imageFileNm,
                ModelMap model
            ) throws Exception {
        
        model.addAttribute("stopVewAt", stopVewAt);
        model.addAttribute("popupId", popupId);
        model.addAttribute("fileUrl", fileUrl);
        model.addAttribute("imageFileNm", imageFileNm);
        
        return "clikMng/sit/pwm/sample/PopupSample";
    }
    
    
    /**
     * 팝업창관리 메인 테스트 목록을 조회한다.
     * @param popupManageVO
     * @param model
     * @return "clikMng/sit/pwm/listMainPopup"
     * @throws Exception
     */
/*    
    @RequestMapping(value = "/sit/pwm/listMainPopup.do")
    public String egovPopupManageMainList(
            PopupManageVO popupManageVO, 
            ModelMap model)
            throws Exception {

        List reusltList = popupManageService.selectPopupMainList(popupManageVO);
        model.addAttribute("resultList", reusltList);

        return "clikMng/sit/pwm/EgovPopupMainList";
    }
*/
    
    /**
     * 시간을 LIST를 반환한다.
     * @return  List
     * @throws 
     */
    private List getTimeHH (){
    ArrayList listHH = new ArrayList();
    HashMap hmHHMM;
    for(int i=0;i <= 24; i++){
            String sHH = "";
            String strI = String.valueOf(i);
            if(i<10){
                    sHH = "0" + strI;
            }else{
                    sHH = strI;
            }
            
            ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
            codeVO.setCode(sHH);
            codeVO.setCodeNm(sHH);
            
            listHH.add(codeVO);
    }
    
    return listHH;
    }
    /**
     * 분을 LIST를 반환한다.
     * @return  List
     * @throws 
     */
    private List getTimeMM (){
    ArrayList listMM = new ArrayList();
    HashMap hmHHMM;
    for(int i=0;i <= 60; i++){
            
            String sMM = "";
            String strI = String.valueOf(i);
            if(i<10){
                    sMM = "0" + strI;
            }else{
                    sMM = strI;
            }

            ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
            codeVO.setCode(sMM);
            codeVO.setCodeNm(sMM);
            
            listMM.add(codeVO);
    }
    return listMM;
    }
    /**
     * 0을 붙여 반환
     * @return  String
     * @throws 
     */
    public String dateTypeIntForString(int iInput){
                String sOutput = "";
                if(Integer.toString(iInput).length() == 1){
                        sOutput = "0" + Integer.toString(iInput);
                }else{
                        sOutput = Integer.toString(iInput);
                }
                
       return sOutput;
}
}