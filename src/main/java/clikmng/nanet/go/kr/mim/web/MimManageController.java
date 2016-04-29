package clikmng.nanet.go.kr.mim.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.ImHttpRequestor;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.enc.Encrypt;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.CalUtil;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.model.MdmRegionNewsVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.service.MdmRegionNewsService;
import clikmng.nanet.go.kr.mim.service.MimManageService;
import clikmng.nanet.go.kr.mim.service.MimManageVO;
import clikmng.nanet.go.kr.uss.mng.service.DocsService;
import clikmng.nanet.go.kr.uss.mng.service.DocsVO;
import clikmng.nanet.go.kr.uss.umt.service.UmtService;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메일링 관리를 처리하는 Controller 클래스
 */

@Controller
public class MimManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MimManageService")
    private MimManageService mimManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="UmtService") // 통합ID
    private UmtService umtService;
    
    @Resource(name="DocsService") // Docs서버
    private DocsService docsService;

	// 첨부파일 관련 
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	 
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    @Resource(name = "MdmRegionNewsService")
    private MdmRegionNewsService mdmRegionNewsService;
    
    @Resource(name = "SendMailDetailInfoIdGnrService")
    private EgovIdGnrService sendMailDetailInfoIdGnrService;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 메일링 - 그룹 목록
     * @param searchVO
     * @param model
     * @return	"/mim/GroupList"
     * @throws Exception
     */
    @IncludedInfo(name="메일링 - 그룹 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mim/GroupList.do")
    public String selectGroupList(@ModelAttribute("searchVO") MimManageVO searchVO, Map commandMap, ModelMap model) throws Exception {

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	searchVO.setPageUnit(searchVO.getPageUnit());
    	searchVO.setPageSize(searchVO.getPageSize());
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
        if(searchVO.getSelectCountPg() != 0) {
        	searchVO.setRecordCountPerPage(searchVO.getSelectCountPg());
        } else {
        	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        }

		HashMap _map = (HashMap)mimManageService.selectGroupListInfo(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);		
		model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
		
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        
    	
        return "clikMng/mim/GroupList";
    }

    /**
    * 메일링 - 그룹 등록
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/GroupRegist"
    * @throws Exception
    */
    @RequestMapping("/mim/GroupRegist.do")
    public String	groupRegist(@ModelAttribute("searchVO") MimManageVO searchVO
    												, BindingResult bindingResult
    												, ModelMap model) throws Exception {
    	
    	String sLocationUrl = "forward:/mim/GroupList.do";

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인 정보 가져오기.
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	// 수신거절 리스트
    	PaginationInfo paginationInfo = new PaginationInfo();
    	searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	HashMap rejectMap = (HashMap)mimManageService.selectRejectEmailList(searchVO);
		model.addAttribute("resultRejectList", rejectMap.get("resultList"));
    	
    	// 등록 process 인 경우
    	if(searchVO.getCmd() != null) {
        	if(searchVO.getCmd().equals("regist")) {
        		beanValidator.validate(searchVO, bindingResult);
        		if (bindingResult.hasErrors()){
        			sLocationUrl = "clikMng/mim/GroupList";
        			return sLocationUrl;
        		}
        		
        		// 0. 등록자 ID 남기기
        		searchVO.setFrstRegisterId(loginVO.getMngrId());
        		searchVO.setLastUpdusrId(loginVO.getMngrId());

        		// 1 그룹 및 구성원 등록
        		mimManageService.insertGroupInfo(searchVO);
        		
    			return sLocationUrl;
        	}
    	}
        return	"clikMng/mim/GroupRegist";
    }   
    
    /**
	    * 메일링 - 그룹 등록 - 구성원 검색 팝업 목록
	    * @param MimManageVO
	    * @param searchVO
	    * @param model
	    * @return	"/mim/PopSearchMember"
	    * @throws Exception
	    */
	    @RequestMapping("/mim/PopSearchMember.do")
	    public String	popupSelectMember(@ModelAttribute("searchVO") MimManageVO searchVO, Map commandMap, ModelMap model) throws Exception {
	    	
	       	// 0. 로그인 여부 확인
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        	return "forward:/uat/uia/LoginUsr.do";
	    	}
	    	
	    	/** EgovPropertyService.LogMngList */
	    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
	    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

	    	/** paging */
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());

			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        	/**
        	 * 0. 일반 이용자. - 통합ID
        	 * 1. 국회직원, 국회의원 - DOCS 서버
        	 * 2-1. 지방의원 - CLIK DB - 연계 
        	 * 2-2. 지방의회담당자, 지자체담당자 -CLIK DB  
        	 * 2-3. return시 직업구분 코드(jobSeCode )
        	 * 		A : 일반이용자, B : 국회직원, C : 국회의원, D : 지방의원, E : 지방의회담당자, F : 지자체담당자
        	 */
        	if (searchVO.getSearchCondition() != null && searchVO.getSearchCondition() != ""){
        		

            	// 0. 일반 이용자. - 통합ID
            	if (searchVO.getSearchCondition().equals("gnrlUser")) {
                    String sqlNanet = "dlib.op.user.UserNanetSearch";
                    String countNanetSql = "dlib.op.user.UserNanetSearchCount";
            		
            		HashMap map = new HashMap();
            		HashMap map2 = new HashMap();

            		//암호화 대상 조건
            		HashMap encMap = new HashMap();
            		encMap.put("USER_NAME", "enc");
            		encMap.put("USER_MAIL", "enc");
            		
            		//리스트 복호화 대상
            		encMap.put("user_name", "enc");
            		encMap.put("user_mail", "enc");
            		
            		//
            		map.put("USER_NAME", searchVO.getSearchKeyword());
            		map2.put("USER_NAME", searchVO.getSearchKeyword());
            		
            		int totalRecordCount = 0;
            		
                    totalRecordCount = umtService.dbListCntEnc(encMap, map, countNanetSql);
                    paginationInfo.setTotalRecordCount(totalRecordCount);

                    // 돌아갈 페이지에 데이터가 없다면 마지막 페이지로 이동(사용자가 데이터 삭제시)
                    int lastPage = (int)Math.ceil(totalRecordCount / (double)paginationInfo.getRecordCountPerPage());
                    if ( lastPage < paginationInfo.getCurrentPageNo() ) {
                        paginationInfo.setCurrentPageNo(lastPage);
                    }
                    map2.put("firstRecordIndex", (paginationInfo.getFirstRecordIndex() <= 0)? 0:paginationInfo.getFirstRecordIndex());
                    map2.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
            		
                    List<HashMap> list = null;
                	list = umtService.dbListEnc(encMap, map2, sqlNanet);
            		
            		try{

            			if(list != null){
            		        model.addAttribute("firstRecordIndex", paginationInfo.getFirstRecordIndex());
            		        model.addAttribute("resultList", list);
            		        model.addAttribute("paginationInfo", paginationInfo);
            		        //(jobSeCode ) A : 일반이용자, B : 국회직원, C : 국회의원, D : 지방의원, E : 지방의회담당자, F : 지자체담당자
            		        model.addAttribute("jobSeCode", "A");
                			return "clikMng/mim/PopSearchMember";
            			}
            			else{
                			model.addAttribute("msg", "fail.common.select");
                			return "clikMng/mim/PopSearchMember";
            			}
            		}
            		catch(Exception ex){
            			log.debug("Default Exeption Handler run...............::: PopSearchTotalId :::");
            			ex.printStackTrace();			
            			
            			model.addAttribute("msg", "fail.common.select");
            			return "clikMng/mim/PopSearchMember";
            		}
            		
            		// 1. 국회직원 또는 국회의원인 경우
            	} else if(searchVO.getSearchCondition().equals("assemblyEmp") || searchVO.getSearchCondition().equals("asemby")) {
            		
            		
            		DocsVO docsVO = new DocsVO();
            		
        			docsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        			docsVO.setLastIndex(paginationInfo.getLastRecordIndex());
        			docsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
            		
        			// 이름 검색 시
        			docsVO.setName(searchVO.getSearchKeyword());
        			
            		if (searchVO.getSearchCondition().equals("asemby")){
            			// 국회의원 직원구분 코드를 쿼리에 하드코딩
            			// 국회의원이면 Y, 국회직원이면 N 국회의원 코드는 :: 003001
            			docsVO.setSeCode("Y");
            		} else {
            			docsVO.setSeCode("N");
            		}
            		
            		List reusltList = docsService.selectPopSearchEmp(docsVO);
                    model.addAttribute("resultList", reusltList);
                    
                    int totCnt = (Integer) docsService.selectPopSearchEmpCnt(docsVO);
                    paginationInfo.setTotalRecordCount(totCnt);
                    model.addAttribute("paginationInfo", paginationInfo);
    		        //(jobSeCode ) A : 일반이용자, B : 국회직원, C : 국회의원, D : 지방의원, E : 지방의회담당자, F : 지자체담당자
                    if(docsVO.getSeCode().equals("Y")) {
                    	model.addAttribute("jobSeCode", "C");
                    } else {
                    	model.addAttribute("jobSeCode", "B");
                    }
                    
                    return "clikMng/mim/PopSearchMember";
            		
                 // 2-1. 지방의원 - CLIK DB - 연계                    
            	} else if(searchVO.getSearchCondition().equals("rasmbly")) {
                	HashMap _map = (HashMap)mimManageService.selectPopSearchRasmblyListInfo(searchVO);
        			int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
        			
        			model.addAttribute("resultList", _map.get("resultList"));
        			model.addAttribute("resultCnt", _map.get("resultCnt"));

        			paginationInfo.setTotalRecordCount(totCnt);
        			model.addAttribute("paginationInfo", paginationInfo);		
        			model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
    		        //(jobSeCode ) A : 일반이용자, B : 국회직원, C : 국회의원, D : 지방의원, E : 지방의회담당자, F : 지자체담당자
        			model.addAttribute("jobSeCode", "D");
        			
        			return "clikMng/mim/PopSearchMember";

        		// 2-2. 지방의회담당자, 지자체담당자 -CLIK DB
            	} else {
            		
            		if (searchVO.getSearchCondition().equals("rasmblyCharger")){
            			// 지방의회 담당자 이면 기관유형코드에 따라  , 지원 : 001 , 관리자 : 002
            			searchVO.setAsembySeCode("002");
            		} else {
            			searchVO.setAsembySeCode("001");
            		}
            		
                	HashMap _map = (HashMap)mimManageService.selectPopSearchRasmblyChargerListInfo(searchVO);
        			int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
        			
        			model.addAttribute("resultList", _map.get("resultList"));
        			model.addAttribute("resultCnt", _map.get("resultCnt"));

        			paginationInfo.setTotalRecordCount(totCnt);
        			model.addAttribute("paginationInfo", paginationInfo);		
        			model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
        			
    		        //(jobSeCode ) A : 일반이용자, B : 국회직원, C : 국회의원, D : 지방의원, E : 지방의회담당자, F : 지자체담당자
                    if(searchVO.getAsembySeCode().equals("002")) {
                    	model.addAttribute("jobSeCode", "E");
                    } else {
                    	model.addAttribute("jobSeCode", "F");
                    }
        			
        			return "clikMng/mim/PopSearchMember";            		
            	}
        		
        	}

	        return	"clikMng/mim/PopSearchMember";
	    }   
    	
    
    /**
    * 메일링 - 그룹 상세정보
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/GroupDetail"
    * @throws Exception
    */
    @RequestMapping("/mim/GroupDetailView.do")
    public String	groupDetail(@ModelAttribute("searchVO")MimManageVO searchVO,
            								ModelMap model) throws Exception {
    	
       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인 정보 가져오기.
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	// 그룹 정보 및 구성원 정보
    	HashMap map = (HashMap)mimManageService.selectGroupDetailInfo(searchVO);
    	
    	// 수신거절 리스트
    	PaginationInfo paginationInfo = new PaginationInfo();
    	searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	HashMap _map = (HashMap)mimManageService.selectRejectEmailList(searchVO);
		model.addAttribute("resultRejectList", _map.get("resultList"));
    	
    	
		model.addAttribute("resultGroupInfo", map.get("resultGroupInfo"));
		model.addAttribute("resultGroupDtlsList", map.get("resultGroupDtlsList"));
		model.addAttribute("resultGroupDtlsInfo", map.get("resultGroupDtlsInfo"));
		
        return	"clikMng/mim/GroupDetail";
    }
    
    
    /**
    * 메일링 - 그룹 수정 및 삭제
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/GroupList"
    * @throws Exception
    */
    @RequestMapping("/mim/GroupUpdate.do")
    public String	groupUpdate(@ModelAttribute("searchVO") MimManageVO searchVO
    												, BindingResult bindingResult
    												, ModelMap model) throws Exception {
    	
    	String sLocationUrl = "forward:/mim/GroupList.do";

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인 정보 가져오기.
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	// 수정 process.
    	if(searchVO.getCmd().equals("update")) {
    		beanValidator.validate(searchVO, bindingResult);
    		if (bindingResult.hasErrors()){
    			sLocationUrl = "clikMng/mim/GroupList";
    			return sLocationUrl;
    		}
    		
    		// 0. 수정자 ID 남기기
    		searchVO.setFrstRegisterId(loginVO.getMngrId());
    		searchVO.setLastUpdusrId(loginVO.getMngrId());

    		// 1 그룹 및 구성원 수정
    		mimManageService.updateGroupInfo(searchVO);
    		
			return sLocationUrl;
			
    	} else {
    		// 2 그룹 및 구성원 삭제
    		mimManageService.deleteGroupInfo(searchVO);
    	}
        return	sLocationUrl;
    }       
    
    //######################################  메일 발송 시작 ########################################//
    
    /**
    * 메일링 - 메일 발송화면(일반)
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/SendMail"
    * @throws Exception
    */
    @RequestMapping("/mim/SendMail.do")
    public String	sendMailView(@ModelAttribute("searchVO") MimManageVO mimManageVO,
            ModelMap model) throws Exception {

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	model.addAttribute("mailSndr", StringUtil.isNullToString(loginVO.getMngrEmail()));
    	
    	
        return	"clikMng/mim/SendMail";
    }    
    
    /**
    * 메일링 - 메일 발송(팝업 그룹선택)
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/PopSearchGroup"
    * @throws Exception
    */
    @RequestMapping("/mim/PopSelectGroup.do")
    public String	popupSelectGroup(@ModelAttribute("searchVO") MimManageVO searchVO
            										,Map commandMap, ModelMap model) throws Exception {
    	
       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	/** EgovPropertyService.LogMngList */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 그룹 목록 조회.
		HashMap _map = (HashMap)mimManageService.selectPopGroupListInfo(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);		
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        
        return	"clikMng/mim/PopSearchGroup";
    }       	

    /**
    * 메일링 - 메일 발송(일반) - 실행
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/SendMail"
    * @throws Exception
    */
    @RequestMapping("/mim/ProcSendMail.do")
    public String	sendMail(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("searchVO") MimManageVO mimManageVO,
    		BindingResult bindingResult,
            ModelMap model) throws Exception {

    	beanValidator.validate(mimManageVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "clikMng/mim/SendMail";
		}
    	
       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	
    	try {
    		
    		// 0. 특수문자 변환 된 것을 HTML로 다시 변환
    		//mimManageVO.setEmailCn(ReplaceTag(mimManageVO.getEmailCn(), "decode"));
    		
    		mimManageVO.setEmailCn(URLDecoder.decode(mimManageVO.getEmailCn(),"utf-8"));
				
	       	// 1. 메일 발송내역 저장----------------------------------------------------------------------------
	    	// 첨부파일 관련 첨부파일ID 생성
			List<FileVO> _result = null;
			String _atchFileId = "";
			
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
	
			if(!files.isEmpty()){
			 _result = fileUtil.parseFileInf(files, "EMAIL_", 0, "", "Globals.mailFileStorePath"); 
			 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			}
	    	
	    	// 리턴받은 첨부파일ID를 셋팅한다..
			mimManageVO.setAtchFileId(_atchFileId);					// 첨부파일 ID
	    	
	    	// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();    	
	    	String frstRegisterId = loginVO.getMngrId();
	    	mimManageVO.setFrstRegisterId(frstRegisterId);			// 최초등록자ID
	    	mimManageVO.setLastUpdusrId(frstRegisterId);    		// 최종수정자ID
//	    	mimManageVO.setSn(sendMailDetailInfoIdGnrService.getNextIntegerId());	//상세내용 Sequence ID
	
	    	mimManageService.insertSendMailInfo(mimManageVO);
	
	       	// 1. 메일 발송내역 저장 끝----------------------------------------------------------------------------
	    	
	    	// 2. 메일 수신 거부 제외 -----------------------------------------------------------------------------
	    	int rejectCnt = 0;			// 실 수신자 수
	    	List rejectRcverEmailList = mimManageService.selectRejectRcvr(mimManageVO);
	    	
	    	// 2. 메일 수신 거부 끝 -------------------------------------------------------------------------------    	
	    	
	       	// 3. 메일 발송----------------------------------------------------------------------------------------
	    	String returnMsg = "";
			String returnUrl = "";
			String from = StringUtil.isNullToString(mimManageVO.getSendNm()); //보내는사람
			String to = ""; 			//받는사람 - 메일주소
			String recname = ""; //받는사람 - 이름
	
			// 받는 사람 스트링으로 만들기.
	    	String[] rcversDtls;		//textarea에 있던 구성원을 분리 값을 담는 배열
	    	String[] rcversSt;			//textarea에 있던 이름과 이메일 주소 구분값 담는 배열
	    	String rcverNm = "";		//구성원 이름
	    	String rcverEmail = "";	//구성원 이메일
	    	String job = "";				//수신자 직업구분
	
			String subject = StringUtil.isNullToString(mimManageVO.getSj()); //제목
			String body = StringUtil.isNullToString(mimManageVO.getEmailCn()); //내용
			String charset = "utf-8"; //인코딩
	    	
			subject = new String(subject.getBytes("utf-8"));
			body = new String(body.getBytes("utf-8"));
			
			String tempFile = "";
			
	    	//첨부파일
	    	String fileStorePath = EgovProperties.getProperty("Globals.mailFileStorePath");
	    	Iterator fileIter = multiRequest.getFileNames();
	    	int cnt = 0;
	    	while (fileIter.hasNext()) {
	    	    MultipartFile mFile = multiRequest.getFile((String)fileIter.next());
	    	    if (mFile.getSize() > 0) {
	    			HashMap _map = new EgovFileMngUtil().mailUploadFile(mFile, fileStorePath);
					tempFile += (String)_map.get("FILE_PATH")+"/"+(String)_map.get("UPLOAD_FILE_NM")+",";
	    	    }
	    	}
	    	
	    	String[] realFile = tempFile.split(",");
	    	
	    	for(int i=0; i<rejectRcverEmailList.size(); i++) {
	    		MimManageVO vo = (MimManageVO)rejectRcverEmailList.get(i);
	
	    		// 수신자 이메일
	    		to = StringUtil.isNullToString(vo.getRcverEmail());
	    		if (vo.getRcverNm() == "" || vo.getRcverNm() != null) {
	    			String[] arrayRcverNm = vo.getRcverNm().split("\\_");
	    			rcverNm = arrayRcverNm[1];
	    			job = arrayRcverNm[0];
	    		}
	    		
	    		boolean find = false;
	    		String cd = "";
	    		find = isEmailPattern(to);
	    		
	    		if(find == false){
	    			cd = "false";
	    		}
	    		
	        	// 이메일 유효성 검사
	        	if(cd.equals("false")){
	        		multiRequest.setAttribute("find", "fail");
	    			returnUrl = "redirect:/mim/SendMailList.do";
	    		}else{
	
	    	    	String result = "";
	    	    	
	    	    	// 수신거부 파라미터 암호화
	    	    	String encString1 = "";
	    	    	String encString2 = "";
	    	    	String encString3 = "";
	    	    	encString1 = EncodeBySType(to);
	    	    	encString2 = EncodeBySType(rcverNm);
	    	    	encString3 = EncodeBySType(job);
	    	    	
	    	    	String hostAddress = EgovProperties.getProperty("Globals.HostAddress");
	    	    	String hostPort = EgovProperties.getProperty("Globals.Port");
	    	    	// 수신거부 HTML
	    	    	// encString1 : 이메일주소, encString2 : 이름, encString3 : 직업
	    	    	
	    	    	log.info("hostAddress = " + hostAddress + ", hostPort = " + hostPort);
	    	    	
	    	    	String rejectHtml = "<a href=\"http://"+hostAddress+":"+hostPort+"/mim/rejectEmail.do?value1=" + encString1 + "&value2=" +encString2+ "&value3=" +encString3+ "\">수신거부</a> ";    	    	
	    	    	
	    			URL url = new URL("http://ems.nanet.go.kr/servlet/sendemailu");
	    	        InputStream is = null;
	    	        BufferedReader br = null;
	    	        ImHttpRequestor requestor = new ImHttpRequestor(url);
	    	    	requestor.addParameter("from", from);	// 보내는 사람
	    	    	requestor.addParameter("to", to);	// 받는사람 이메일
	    	    	requestor.addParameter("subject", subject);	// 제목
	    	    	requestor.addParameter("body", body+rejectHtml);	// 본문
	    	    	requestor.addParameter("charset", charset);	
	    	    	
	    	    	for(int j = 0; j < realFile.length; j++){
	    	    		if(!realFile[j].equals("")){
	    	    			requestor.addFile("file", realFile[j]);
	    	    		}
	    	    	}
	    	    	
	    	        try {
	    	            is = requestor.sendMultipartPost();
	    	            br = new BufferedReader(new InputStreamReader(is));
	    	            String line = "";
	    	            while( (line=br.readLine())!= null ) {
	    	                returnMsg += line.trim();
	    	                result = "ok";
	    	            }
	    	            br.close();
	    	        } catch(Exception e){
	    	        	e.printStackTrace();
	    	        } finally {
	    	            if ( is != null ){
	    	            	try {
	    	            		is.close();
	    	            	}
	    	            	catch(Exception e){
	    	            		e.printStackTrace();
	    	            	}
	    	            }
	    	            if ( br != null ){
	    	            	try {
	    	            		br.close();
	    	            	}catch(Exception e){
	    	            		e.printStackTrace();
	    	            	}
	    	            }
	    	        }
	    		}    		
	    		
	    	}
	    	
	    	// 실 수신자 수
	    	mimManageVO.setSendRejectCnt(rejectRcverEmailList.size());
			// 메일 발송이 성공이면 메일 상태를 발송성공으로 수정 및 메일 수신거부자 수정
			mimManageService.updateSendMailStatus(mimManageVO);
    	
    	} catch (Exception e) {
    		log.debug("::: EMAIL SEND ERROR ::: " + e.getMessage());
    		e.printStackTrace();
			e.getMessage();
		}    	
    	// --------------------------------------------   메일 발송 끝 --------------------------------------------
    	
        return	"redirect:/mim/SendMailList.do";
    }        	
    	
    /**
    * 메일링 - 그룹 구성원 조회
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	
    * @throws Exception
    */    
    @RequestMapping(value = "/mim/ajaxSearchGroup.do")
    public void ajaxSearchGroup(
            Map commandMap,
            HttpServletResponse response,
            HttpServletRequest request,
            MimManageVO mimManageVO
            ) throws Exception {

       mimManageVO.setEmailGroupId(StringUtil.isNullToString(request.getParameter("emailGroupId")));
      
       // 그룹 구성원 조회
       List memberList = new ArrayList();
       memberList = mimManageService.selectSearchGroupAjax(mimManageVO);
      
       JSONObject jso = new JSONObject();    // JASON 객체생성
       JSONArray jarray = new JSONArray();
       
       for(int i=0; i<memberList.size(); i++) {
    	   MimManageVO mimVOs = (MimManageVO)memberList.get(i);
    	   jso = new JSONObject();

   			jso.put("name", StringUtil.isNullToString(mimVOs.getRcverNm()));
   			jso.put("email", StringUtil.isNullToString(mimVOs.getRcverEmail()));
   			
   			jarray.add(i, jso);
   		}

       response.setHeader("Content-Type", "text/html;charset=utf-8");
       PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
       out.print(jarray.toString());
       out.flush();
    }    	
    	
    	
    /**
    * 메일링 - 메일 발송화면(지역현안자료)
    * @param MimManageVO
    * @param searchVO
    * @param model
    * @return	"/mim/AreaPndprbDta"
    * @throws Exception
    */ 
    @RequestMapping("/mim/AreaPndprbDta.do")
    public String	areaPndprbDta(@ModelAttribute("searchVO") MimManageVO mimManageVO,
            ModelMap model) throws Exception {

       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	model.addAttribute("mailSndr", StringUtil.isNullToString(loginVO.getMngrEmail()));
    	
    	// 1. 양식 셀렉트 박스 조회
    	List formList = mimManageService.selectFormList();
    	model.addAttribute("formList", formList);
    	
    	
        return	"clikMng/mim/AreaPndprbDta";
    }       	
    	
    	
    	
    /**
	    * 메일링 - 양식 변경
	    * @param MimManageVO
	    * @param searchVO
	    * @param model
	    * @return	
	    * @throws Exception
	    */    
	    @RequestMapping(value = "/mimt/AjaxChangeForm.do")
	    public void ajaxChangeForm(
	            Map commandMap,
	            HttpServletResponse response,
	            HttpServletRequest request,
	            MimManageVO mimManageVO
	            ) throws Exception {

	    	
   System.out.println("###########################################################");
   System.out.println("##### mimManageVO ===> " + mimManageVO);
   System.out.println("##### mimManageVO.getEmailFormId() ===> " + mimManageVO.getEmailFormId());
   System.out.println("##### mimManageVO.getSchDt1() ===> " + mimManageVO.getSchDt1());
   System.out.println("##### mimManageVO.getSchDt2() ===> " + mimManageVO.getSchDt2());
	    	
	       mimManageVO.setEmailFormId(StringUtil.isNullToString(request.getParameter("emailFormId")));

	       MdmSearchVO mdmSearchVO = new MdmSearchVO();
	       List<MdmRegionNewsVO> regionNewsList = null;
	       
	    	mdmSearchVO.setFirstRecord(0);
	    	mdmSearchVO.setLastRecord(10);
	    	
	    	mdmSearchVO.setSchDt1(mimManageVO.getSchDt1());
	    	mdmSearchVO.setSchDt2(mimManageVO.getSchDt2());
	       
	       regionNewsList = mdmRegionNewsService.selectMdmRegionNewsList(mdmSearchVO);
	
	       
	       System.out.println("##### regionNewsList ===> ");
	       System.out.println("##### regionNewsList Size ===> " + regionNewsList.size());
	       
	       // 양식 조회
	       String formHtml = mimManageService.selectChangeFormAjax(mimManageVO);
	       
	       String	titleHtml = "지역현안소식 (" + mimManageVO.getSchDt1() + " ~ " + mimManageVO.getSchDt2() + ")";
	       String 	listHtml = "";
	       for(MdmRegionNewsVO newsVO : regionNewsList)
	       {
      			listHtml += "<li style=\"width:100%; overflow: hidden;white-space:nowrap; text-overflow:ellipsis;-o-text-overflow:ellipsis;margin-bottom:5px;list-style:none; background:url(http://clik.nanet.go.kr/images/newsletter/dot.gif) left 5px no-repeat; padding-left:10px;\">";
				listHtml += "<a href=\"http://clik.nanet.go.kr/search/searchList.do?collection=news&DOCID=" + newsVO.getNEWS_ID() + "\" style=\"text-decoration:none; color:#000000; font-size:14px; font-family:'NanumGothic','나눔고딕', Dotum, arial, sans-serif;\">";
				listHtml += "	" + newsVO.getTITLE();
				listHtml += "</a>";
				listHtml += "</li>";
	       }
	       
	       formHtml = formHtml.replaceAll("<!--TITLE-->", titleHtml);
	       formHtml = formHtml.replaceAll("<!--NEWSLIST-->", listHtml);
	       
	       System.out.println("##### formHtml ===> " + formHtml);
	       response.setHeader("Content-Type", "text/html;charset=utf-8");
	       PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
	       out.print(formHtml.toString());
	       out.flush();
	    }        	
    	
    
    //######################################  메일 발송 끝 ########################################//    
    
    //######################################  메일 발송 내역 시작 ########################################//
	    
	    /**
	     * 메일링 - 메일 발송 내역
	     * @param MimManageVO
	     * @param searchVO
	     * @param model
	     * @return	"/mim/SendMailList"
	     * @throws Exception
	     */
	     @RequestMapping("/mim/SendMailList.do")
	     public String	sendMailList(@ModelAttribute("searchVO") MimManageVO searchVO,
	             Map commandMap,
	             ModelMap model) throws Exception {

	       	// 0. 로그인 여부 확인
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        	return "forward:/uat/uia/LoginUsr.do";
	    	}

			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
	    	
	    	// 페이징 처리
	        PaginationInfo paginationInfo = new PaginationInfo();
	        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	        paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
	        
	        if(searchVO.getSelectCountPg() != 0) {
	        	searchVO.setRecordCountPerPage(searchVO.getSelectCountPg());
	        } else {
	        	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	        }
	        paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
	        paginationInfo.setPageSize(propertiesService.getInt("pageSize"));

	        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 첫 페이지 로딩시 검색 기간은 기본 일주일
			if(searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1()) || 
					searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2()) ) {
	    		
				CalUtil calUtil = new CalUtil();
	    		calUtil.setDelimiter("-");
	    		calUtil.setDecimalFormat("00");
	    		String beforeWeek 	= calUtil.getBeforeWeek();
	    		String today 		= calUtil.getToday();
	    		
	    		if(searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1())){
	    			searchVO.setSchDt1(beforeWeek);
	    		}
	    		if(searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2())){
	    			searchVO.setSchDt2(today);
	    		}
			}

			HashMap _map = (HashMap)mimManageService.selectSendMailListInfo(searchVO);
			int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
			
			model.addAttribute("resultList", _map.get("resultList"));
			model.addAttribute("resultCnt", _map.get("resultCnt"));

			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);		
			model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
			
	        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
	        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

	        return	"clikMng/mim/SendMailList";
	    }
	    
	    
	    
    //######################################  메일 발송 내역 끝 ########################################//
	     
    //######################################  메일 수신거부 내역 시작 ########################################//
	     
     /**
      * 메일링 - 메일 수신 거부 내역
      * @param MimManageVO
      * @param searchVO
      * @param model
      * @return	"/mim/RejectMailList"
      * @throws Exception
      */
      @RequestMapping("/mim/RejectMailList.do")
      public String	rejectMailList( @ModelAttribute("searchVO") MimManageVO searchVO
    		  										, Map commandMap
    		  										, ModelMap model) throws Exception {
    	  
    	  // 0. 로그인 여부 확인
    	  Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	  if(!isAuthenticated) {
    		  model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		  return "forward:/uat/uia/LoginUsr.do";
    	  }
    	  
    	/** EgovPropertyService.LogMngList */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
        if(searchVO.getSelectCountPg() != 0) {
        	searchVO.setRecordCountPerPage(searchVO.getSelectCountPg());
        } else {
        	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        }

		HashMap _map = (HashMap)mimManageService.selectRejectEmailList(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);		
		model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
		
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

      return	"clikMng/mim/RejectMailList";
      } 	     
     
      
	     
  /**
   * 메일링 - 메일 수신 거부 등록
   * @param MimManageVO
   * @param searchVO
   * @param model
   * @return	"/mim/RejectMailList"
   * @throws Exception
   */
   @RequestMapping("/mim/rejectEmail.do")
   public String	insertRejectMailInfo( @ModelAttribute("searchVO") MimManageVO searchVO
		   										, HttpServletRequest request
 		  										, Map commandMap
 		  										, ModelMap model) throws Exception {
	   
	   String rejectEmail = "";		// 수신거부 value1(이메일)
	   String rejectNm = "";			// 수신거부 value2(이름)
	   String rejectJob = "";			// 수신거부 value3(직업)
	   

	   rejectEmail = DecodeBySType(CommonStringUtil.getPrmStrCnvr(request.getParameter("value1")));
	   rejectNm	= DecodeBySType(CommonStringUtil.getPrmStrCnvr(request.getParameter("value2")));
	   rejectJob 	= DecodeBySType(CommonStringUtil.getPrmStrCnvr(request.getParameter("value3")));

	   HashMap<String, String> rejectInfoMap = new HashMap<String, String>();
	   rejectInfoMap.put("rejectEmail", rejectEmail);
	   rejectInfoMap.put("rejectNm", rejectNm);
	   rejectInfoMap.put("rejectJob", rejectJob);
	   
	   
	   // 수신거부 중복체크 0: 중복 없음, 1:중복 있음
	   int emailConfirm = mimManageService.selectConfirmEmail(rejectEmail);
	   
	   if(emailConfirm == 0) {
		   // 수신거부 등록
		   mimManageService.insertRejectEmailInfo(rejectInfoMap);
		   model.addAttribute("resultCode", 0);
		   model.addAttribute("resultMessage", "해당 이메일주소("+ rejectEmail +"("+rejectNm+")" + ")가 정상적으로 수신거부 메일로 등록 되었습니다.");
	   } else {
		   model.addAttribute("resultCode", 1);
		   model.addAttribute("resultMessage", "이미 수신거부 등록되어 있는 메일입니다.");
	   }
	   
	   return	"clikMng/mim/RejectMailComplete";
   } 	     
	     
         
      
	     
    //######################################  메일 수신거부 내역 끝 ########################################//	     
    
	/**
	 * 이메일 유효성 체크
	 * @param email
	 * @return
	 */
	public static boolean isEmailPattern(String email){
		//Pattern pattern=Pattern.compile("\\w+[@]\\w+\\.\\w+");
	    Pattern pattern=Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
	    Matcher match=pattern.matcher(email);
	    return match.find();
	}

	/**
	 * 이메일 주소 암호화
	 * @param email
	 * @return
	 */	
	// EncodeBySType(strD1);
	public String EncodeBySType(String strData){
		String strRet = null;
		strRet = Encrypt.com_Encode(":" + strData + ":sisenc");
		return strRet;	
	}

	/**
	 * 이메일 주소 복호화
	 * @param email
	 * @return
	 */
	// SSO 복호화, 사용방법 : strD1	=	DecodeBySType(strEncArr);
	public String DecodeBySType(String strData){
		String strRet = null;
		int e, d, s, i=0;

		strRet = Encrypt.com_Decode(strData);
		e = strRet.indexOf(":");
		d = strRet.indexOf(":sisenc");
		strRet = strRet.substring(e+1, d);
		return strRet;
	}	 
	
	/**
	 * 이메일 내용 decoding
	 * @param email
	 * @return
	 */
    public String ReplaceTag(String Expression, String type){
        String result = "";
        if (Expression==null || Expression.equals("")) return "";

        if (type == "encode") {
            result = StringUtils.replace(Expression, "&", "&amp;");
            result = StringUtils.replace(result, "\"", "&quot;");
    
            result = StringUtils.replace(result, "'", "&apos;");
            result = StringUtils.replace(result, "<", "&lt;");
            result = StringUtils.replace(result, ">", "&gt;");
            result = StringUtils.replace(result, "\r", "<br>");
            result = StringUtils.replace(result, "\n", "<p>");
        }
        else if (type == "decode") {
            result = StringUtils.replace(Expression, "&amp;", "&");
            result = StringUtils.replace(result, "&quot;", "\"");
    
            result = StringUtils.replace(result, "&#35;", "#");
            result = StringUtils.replace(result, "&#39;", "\\");
            result = StringUtils.replace(result, "&#37;", "%");
            result = StringUtils.replace(result, "&#40;", "(");
            result = StringUtils.replace(result, "&#41;", ")");
            result = StringUtils.replace(result, "&#43;", "+");
            result = StringUtils.replace(result, "&#46;", ".");
            result = StringUtils.replace(result, "&#47;", "/");
            
            result = StringUtils.replace(result, "&apos;", "'");
            result = StringUtils.replace(result, "&lt;", "<");
            result = StringUtils.replace(result, "&gt;", ">");
            result = StringUtils.replace(result, "<br>", "\r");
            result = StringUtils.replace(result, "<p>", "\n");        
        }
        
        return result;  
    }
	     
	
	
}
