package clikmng.nanet.go.kr.uss.mng.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.sts.stm.service.StmManageService;
import clikmng.nanet.go.kr.uss.mng.service.MngService;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MngController {

	/** log */
    protected static final Log LOG = LogFactory.getLog(MngController.class);
    
    protected Log log = LogFactory.getLog(this.getClass());
    
    /** PropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService; 
	
    @Resource(name="MngService")
    private MngService mngService;
    
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    @Resource(name = "MdmTnsrAsmblyCodeService")
    private MdmTnsrAsmblyCodeService mdmTnsrAsmblyCodeService;
    
    @Resource(name = "StmManageService")
    private  StmManageService StmManageService;
    
	/**
	 * 관리자 목록을 조회한다
	 * @param vo - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
    @IncludedInfo(name="관리자 관리", order = 3 ,gid = 19)
    @RequestMapping(value="/uss/mng/MngList.do")
    public String selectMngList(@ModelAttribute("mngVO") MngVO mngVO, Map commandMap, ModelMap model) throws Exception {
    	// 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "clikMng/uat/uia/EgovLoginUsr";
        }

    	/** EgovPropertyService.LogMngList */
    	if(mngVO.getPageUnit() == 0){
    		mngVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	}
    	mngVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mngVO.getPageUnit());
		paginationInfo.setPageSize(mngVO.getPageSize());

		mngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        
        
        List reusltList = mngService.selectMngList(mngVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        model.addAttribute("selMngrOpt", commandMap.get("selMngrOpt") == null ? "" : (String) commandMap.get("selMngrOpt"));
        
        int totCnt = (Integer) mngService.selectMngListCnt(mngVO);
        paginationInfo.setTotalRecordCount(totCnt);
        //model.addAttribute("selectCountperpg", mngVO.getSelectCountperpg());
        model.addAttribute("paginationInfo", paginationInfo);    	    	
    	
    	return "clikMng/uss/mng/MngList";
    }
    
	/**
	 * 관리자 등록을 한다
	 * @param vo - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
    @IncludedInfo(name="관리자 관리", order = 3 ,gid = 19)
    @RequestMapping(value="/uss/mng/MngRegist.do")
    public String insertMngRegist(@ModelAttribute("mngVO") MngVO mngVO
    		, BindingResult bindingResult
    		, Map commandMap
    		, ModelMap model) throws Exception {

    	// 0. Spring Security 사용자권한 처리
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
                return "clikMng/uat/uia/EgovLoginUsr";
            }

            LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        	
    		String sLocationUrl = "clikMng/uss/mng/MngRegist";
    	
    		List authorList = null; 

            String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
            log.info("cmd =>" + sCmd);

            // 등록화면과 등록실행의 분기 
            // 등록실행
            if (sCmd.equals("save")) {
                //서버  validate 체크
                beanValidator.validate(mngVO, bindingResult);
                if(bindingResult.hasErrors()){
                    return sLocationUrl;
                } 
                //아이디 설정
                mngVO.setFrstRegisterId((String)loginVO.getMngrId());
                mngVO.setLastUpdusrId((String)loginVO.getMngrId());
                try {
                	// 이미 등록되어 있는 아이디인지 확인
                	int confirmId = 0;
                	confirmId = mngService.selectMngId(mngVO);
                	if(confirmId != 0) {
                		log.debug("::: Error occured ::: Already Exist MNGID ");
    					model.addAttribute("msg", "이미 ID가 존재합니다.");
    					return "forward:/uss/mng/MngList.do";	
                	}
                	
                    // 관리자 테이블 저장
                    mngService.insertMngManage(mngVO);
                    
                    model.addAttribute("msg", egovMessageSource.getMessage("success.common.insert"));
                    // 사용자보안설정 테이블 저장
                    //mngService.insertMngMappingManage(mngVO);
				} catch (Exception ex) {
					log.debug("Default Exeption Handler run...............::: MngRegist :::");
					ex.printStackTrace();			
					
					model.addAttribute("msg", egovMessageSource.getMessage("fail.common.insert"));
					return "forward:/uss/mng/MngList.do";
				}

                // 등록화면
            } else {
        			// clik 관리자 등록 관련
        			
            		// 권한리스트 가져오기
        			authorList = mngService.selectAuthorList(mngVO);
        			model.addAttribute("authorList", authorList);
        			
            		sLocationUrl = "clikMng/uss/mng/MngRegist";
            		
            		
            		return sLocationUrl;
            	
            }
            
            return "forward:/uss/mng/MngList.do";
    }
    
    /**
     * 관리자 등록 > 직원찾기 팝업화면
     */
     @RequestMapping(value="/uss/mng/PopSearchEmp.do")
     public String popupSelectSearchEmp(@ModelAttribute("mngVO") MngVO mngVO
     		, ModelMap model) throws Exception {
    	 
     	// 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
     	
     	return "clikMng/uss/mng/PopSearchEmp";
     }     
     
    
	/**
	 * 관리자 수정을 한다
	 * @param vo - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
    @IncludedInfo(name="관리자 관리", order = 3 ,gid = 19)     
    @RequestMapping(value="/uss/mng/MngDetail.do")
    public String updateMngModify(@ModelAttribute("mngVO") MngVO mngVO
    		, BindingResult bindingResult
    		, Map commandMap
    		, ModelMap model) throws Exception {
    	
	    	// 0. 로그인 여부 확인
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        	return "forward:/uat/uia/LoginUsr.do";
	    	}
    	
	    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	// return Url
    	String sLocationUrl = "clikMng/uss/mng/MngDetail";
    	
    	// 기능구분 del = 삭제, edit = 수정 
    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
    	
    	// 권한리스트
    	List authorList = null;
    	
		// 권한리스트 가져오기
		authorList = mngService.selectAuthorList(mngVO);
		model.addAttribute("authorList", authorList);
		
		model.addAttribute("searchCondition", mngVO.getSearchCondition());
		model.addAttribute("searchKeyword", mngVO.getSearchKeyword());
    	
    	/** 관리자 및 지방의회 상세보기 */
		if (sCmd.equals("")) {
			// 관리자 상세내용 조회
			model.addAttribute("mngVO", mngService.selectMngDetail(mngVO));
			
			return sLocationUrl;
			
		} else if(sCmd.equals("edit")) {
			
			
			
			
			// 관리자 수정
            //서버  validate 체크
            beanValidator.validate(mngVO, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            } 
            
            //아이디 설정
            mngVO.setLastUpdusrId(loginVO.getMngrId());    
            mngService.editMngDetail(mngVO);
			
			return "forward:/uss/mng/MngList.do";
			
		} else if(sCmd.equals("del")) {
			mngService.delMngManage(mngVO);
			
			return "forward:/uss/mng/MngList.do";    			
		}
    		

    	return "";
    }
    

    /** ----------------------------- 지방의회 / 지자체 관리자 시작 ----------------------------- */
    
	/**
	 * 지방의회/지자체 관리자 목록을 조회한다
	 * @param vo - MngVo
	 * @return 지방의회/지자체 목록 페이지
	 * @exception Exception
	 */
    @IncludedInfo(name="지방의회/지자체 담당자 관리", order = 3 ,gid = 20)
    @RequestMapping(value="/uss/mng/LocalMngList.do")
    public String selectLocalMngList(@ModelAttribute("mngVO") MngVO mngVO, Map commandMap, ModelMap model) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	/** EgovPropertyService.LogMngList */
    	if(mngVO.getPageUnit() == 0){
    		mngVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	}

    	mngVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mngVO.getPageUnit());
		paginationInfo.setPageSize(mngVO.getPageSize());


		mngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		CmmnDetailCode vo = new CmmnDetailCode();
    	
    	//지방의회로 고정
		//mngVO.setInsttClCode(CommonUtil.strnvl(commandMap.get("fInsttClCode")));
    	
    	//검색 정보 설정을 위하여 의회 정보가 존재할 경우 조회
    	if(mngVO.getLoasmCode() != null && !"".equals(mngVO.getLoasmCode())){
    		HashMap<String, Object> param = new HashMap<String, Object>();
    		param.put("RASMBLY_ID", mngVO.getLoasmCode());
    		HashMap<String,Object> obj = StmManageService.selectRasmblyInfo(param);
    		
    		mngVO.setInsttClCode(obj.get("UPPER_INSTT_CL_CODE").toString());
    		mngVO.setInsttClCode(obj.get("INSTT_CL_CODE").toString());
    		mngVO.setLoasmCode(obj.get("LOASM_TY_CODE").toString());
    		mngVO.setBrtcCode(obj.get("BRTC_CODE").toString());
    	}
    	
    	//기관유형
    	if(mngVO.getfInsttClCode() != null && !"".equals(mngVO.getfInsttClCode())){
    		vo.setfInsttClCode(mngVO.getfInsttClCode());
         	List<CmmnDetailCode> loasm_ty_code_list = cmmUseService.selectInsttTyDetails(vo);
         	model.addAttribute("instt_ty_code_code_list", loasm_ty_code_list);
    	}
    	
    	//지역목록
    	if(mngVO.getInsttClCode() != null && !"".equals(mngVO.getInsttClCode())){
    		vo.setBrtcCode("LMC");
    		List<CmmnDetailCode> brtc_code_list = cmmUseService.selectBrtcCodeDetails(vo);
    		model.addAttribute("brtc_code_list", brtc_code_list);
    	}

    	//의회목록
     	if(mngVO.getBrtcCode() != null && !"".equals(mngVO.getBrtcCode())){
    		vo.setInsttClCode(mngVO.getInsttClCode());
    		vo.setBrtcCode(mngVO.getBrtcCode());
         	List<CmmnDetailCode> loasm_code_list = cmmUseService.selectLoasmInfo(vo);
         	model.addAttribute("loasm_code_list", loasm_code_list);
    	}
		
        // 담당 코드 셀렉트 . 담당코드 하드코딩
		ComDefaultCodeVO comCodeVO = new ComDefaultCodeVO();
		
        comCodeVO.setCodeId("RKS028");  
        List<?> chargerSeCodeList = cmmUseService.selectCmmCodeDetail(comCodeVO);
        model.addAttribute("chargerSeCodeList", chargerSeCodeList);
		
        comCodeVO.setCodeId("RKS023");  
        List<?> chrgList = cmmUseService.selectCmmCodeDetail(comCodeVO);
        model.addAttribute("chrgList", chrgList);

        mngVO.setfInsttClCode(CommonUtil.strnvl(commandMap.get("fInsttClCode")));//기관
        mngVO.setInsttClCode(CommonUtil.strnvl(commandMap.get("insttClCode")));//기관유형
        mngVO.setBrtcCode(CommonUtil.strnvl(commandMap.get("brtcCode")));//지역
        mngVO.setLoasmCode(CommonUtil.strnvl(commandMap.get("loasmCode")));//소속
        
        
        List<?> reusltList = mngService.selectLocalMngList(mngVO);
        
        int totCnt = (Integer) mngService.selectLocalMngListCnt(mngVO);

        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", reusltList);
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : commandMap.get("searchCondition"));
        //model.addAttribute("selectCountperpg", mngVO.getSelectCountperpg());
        model.addAttribute("paginationInfo", paginationInfo);    	  

        model.addAttribute("mngVO", mngVO);
        
        return "clikMng/uss/mng/LocalMngList"; 
    	
    }
    
    /**
	 * 지방의회/지자체 관리자 목록 엑셀 다운로드
	 * @param vo - MngVo
	 * @return 지방의회/지자체 담당자 목록 엑셀
	 * @exception Exception
	 */
    @IncludedInfo(name="지방의회/지자체 담당자 엑셀다운로드", order = 3 ,gid = 20)
    @RequestMapping(value="/uss/mng/LocalMngListExcelDownload.do")
    public ModelAndView LocalMngListExcelDownload(@ModelAttribute("mngVO") MngVO mngVO, Map commandMap, ModelMap model) throws Exception {

        mngVO.setfInsttClCode(CommonUtil.strnvl(commandMap.get("fInsttClCode")));//기관
        mngVO.setInsttClCode(CommonUtil.strnvl(commandMap.get("insttClCode")));//기관유형
        mngVO.setBrtcCode(CommonUtil.strnvl(commandMap.get("brtcCode")));//지역
        mngVO.setLoasmCode(CommonUtil.strnvl(commandMap.get("loasmCode")));//소속
        
        mngVO.setFirstIndex(0);
        mngVO.setRecordCountPerPage(99999);
        
        List<?> reusltList = mngService.selectLocalMngList(mngVO);
        int totCnt = (Integer) mngService.selectLocalMngListCnt(mngVO);
        
        Map<String, Object> map = new HashMap<String, Object>();
    	map.put("resultList", reusltList);
    	map.put("resultCnt", totCnt);

        return new ModelAndView("LocalMngListExcel", map);
    	
    }
    
	/**
	 * 지방의회/지자체 관리자 상세보기 및 수정
	 * @param vo - MngVo
	 * @return 관리자 상세보기 페이지
	 * @exception Exception
	 */
    @IncludedInfo(name="지방의회/지자체 담당자 관리", order = 3 ,gid = 20)
    @RequestMapping(value="/uss/mng/LocalMngDetail.do")
    public String updateLocalMngModify(@ModelAttribute("mngVO") MngVO mngVO
    		, BindingResult bindingResult
    		, ComDefaultCodeVO comCodeVO
    		, Map commandMap
    		, ModelMap model ) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	// return Url
    	String sLocationUrl = "clikMng/uss/mng/LocalMngDetail";
    	
    	// 기능구분 del = 삭제, edit = 수정 
    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
    	
    	/** 지방의회/지자체 관리자 상세보기 / 수정 */
		if (sCmd.equals("")) {
			// 관리자 상세내용 조회
			MngVO resultVO = mngService.selectLocalMngDetail(mngVO);
			model.addAttribute("mngVO", resultVO);
			
	        // 담당 코드 셀렉트 . 담당코드 하드코딩
	        comCodeVO.setCodeId("RKS028");  
	        List chargerSeCodeList = cmmUseService.selectCmmCodeDetail(comCodeVO);
	        model.addAttribute("chargerSeCodeList", chargerSeCodeList);
			
	        comCodeVO.setCodeId("RKS023");  
	        List chrgList = cmmUseService.selectCmmCodeDetail(comCodeVO);
	        model.addAttribute("chrgList", chrgList);
	        
	        int rasmblyCnt = 0;
	        if(resultVO.getIntrstRasmblyId1() != null && resultVO.getIntrstRasmblyId1() != "") {
	        	rasmblyCnt++;
	        }
	        if(resultVO.getIntrstRasmblyId2() != null && resultVO.getIntrstRasmblyId2() != "") {
	        	rasmblyCnt++;
	        }
	        if(resultVO.getIntrstRasmblyId3() != null && resultVO.getIntrstRasmblyId3() != "") {
	        	rasmblyCnt++;
	        }	        
	        
	        model.addAttribute("rasmblyCnt", rasmblyCnt);
	        
			return sLocationUrl;
			
		} else if(sCmd.equals("edit")) {
			// 관리자 수정
            //서버  validate 체크
            beanValidator.validate(mngVO, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            } 

            // 관심의회 값 설정
            String[] arrayAssembly = mngVO.getSelAssembly();

            for(int i=0; i<arrayAssembly.length; i++) {
            	if(i==0){
            		mngVO.setIntrstRasmblyId1(arrayAssembly[i]);	
            	}
            	if(i==1){
            		mngVO.setIntrstRasmblyId2(arrayAssembly[i]);	
            	}
            	if(i==2){
            		mngVO.setIntrstRasmblyId3(arrayAssembly[i]);	
            	}
            }
            //아이디 설정
            mngVO.setLastUpdusrId(loginVO.getMngrId());    			
			mngService.updateLocalMngDetail(mngVO);
			
			return "forward:/uss/mng/LocalMngList.do";
			
		} else if(sCmd.equals("del")) {
			mngService.deleteLocalMngDetail(mngVO);
			
			return "forward:/uss/mng/LocalMngList.do";    			
		}
		return "";
    }
    
    /**
     * ajax 기관유형 불러오기
     * @param searchVO
     * @param model
     * @return	clikMng/uss/mng/LocalMngDetail
     * @throws Exception
     */
    @RequestMapping("/uss/mng/selectAjaxInsttTy.do")
    public void selectAjaxInsttTy(@ModelAttribute("mngVO") MngVO mngVO
								    		, CmmnDetailCode vo
								    		, HttpServletResponse resp
								    		, HttpServletRequest request) throws Exception {
    	
    	// 0. 파라미터 값에 기관분류(insttCode)가 있으면 기관유형코드를 조회
    	vo.setInsttClCode(StringUtil.isNullToString(request.getParameter("fInsttClCode")));
    	
    	// 1. 기관유형
     	List<CmmnDetailCode> result = cmmUseService.selectInsttTyDetails(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
			jso.put("fInsttTyClCodeNm", cdc.getfInsttClCodeNm());		// 기관유형 코드명
    		jso.put("fInsttTyClCode", cdc.getfInsttClCode());				// 기관유형 코드
    		
    		jarray.add(i, jso);
    	}
	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());
    } 
    
    /**
     * ajax 광역시도 정보 불러오기
     * @param searchVO
     * @param model
     * @return	clikMng/uss/mng/LocalMngDetail
     * @throws Exception
     */
    @RequestMapping("/uss/mng/selectAjaxBrtc.do")
    public void selectAjaxBrtc(@ModelAttribute("mngVO") MngVO mngVO
    		, CmmnDetailCode vo
    		, HttpServletResponse resp
    		, HttpServletRequest request)
            throws Exception {

    	// 0. 파라미터 값에 기관분류코드(insttClCode)가 있으면 광역시도코드를 조회
    	// 광역시도 값 가져오기 공통분류 LMC
    	vo.setBrtcCode("LMC");
     	List<CmmnDetailCode> result = cmmUseService.selectBrtcCodeDetails(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
			jso.put("codeIdNm", cdc.getCodeIdNm());		// 광역시도 코드명
    		jso.put("codeId", cdc.getCodeId());					// 광역시도 코드
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    } 
            
    
    /**
     * ajax 소속 정보 불러오기
     * @param searchVO
     * @param model
     * @return	clikMng/uss/mng/LocalMngDetail
     * @throws Exception
     */
    @RequestMapping("/uss/mng/selectAjaxPsitn.do")
    public void selectAjaxPsitn(@ModelAttribute("mngVO") MngVO mngVO
    		, CmmnDetailCode vo
    		, HttpServletResponse resp
    		, HttpServletRequest request)
            throws Exception {

    	// 0. 기관코드 및 광역시군구 코드
    	vo.setInsttClCode(StringUtil.isNullToString(request.getParameter("insttClCode")));
    	vo.setBrtcCode(StringUtil.isNullToString(request.getParameter("brtcCode")));

    	// 1. 소속코드 및 소속명 리스트
    	List<CmmnDetailCode> result = cmmUseService.selectLoasmInfo(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
    		jso.put("loasmNm", cdc.getLoasmNm());					// 지방정부 및 지방의회명
    		jso.put("loasmCode", cdc.getLoasmCode());				// 지방정부 및 지방으회코드
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
    /**
     * ajax 소속 정보 불러오기 (전체)
     * @param searchVO
     * @param model
     * @return	clikMng/uss/mng/LocalMngDetail
     * @throws Exception
     */
    @RequestMapping("/uss/mng/selectAjaxPsitnAll.do")
    public void selectAjaxPsitnAll(@ModelAttribute("mngVO") MngVO mngVO
    		, CmmnDetailCode vo
    		, HttpServletResponse resp
    		, HttpServletRequest request)
            throws Exception {

    	// 0. 기관코드 및 광역시군구 코드
    	vo.setInsttClCode(StringUtil.isNullToString(request.getParameter("insttClCode")));
    	vo.setBrtcCode(StringUtil.isNullToString(request.getParameter("brtcCode")));

    	// 1. 소속코드 및 소속명 리스트
    	List<CmmnDetailCode> result = cmmUseService.selectLoasmInfoAll(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
    		jso.put("loasmNm", cdc.getLoasmNm());					// 지방정부 및 지방의회명
    		jso.put("loasmCode", cdc.getLoasmCode());				// 지방정부 및 지방으회코드
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
	/**
	 * 지방의회/지자체 관리자 승인
	 * @param vo - MngVo
	 * @return 지방의회/지자체 승인
	 * @exception Exception
	 */    
    @RequestMapping(value="/uss/mng/ApproveLocalMng.do")
    public String updateLocalMng(@ModelAttribute("mngVO") MngVO mngVO
    										, Map commandMap
    										, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        // 최종수정자 아이디 및 승인 아이디
        String userId = loginVO.getMngrId();
        mngVO.setLastUpdusrId(userId);
        mngVO.setConfmerId(userId);
        
        // 승인 업데이트
        mngService.updateApprovalLocalMng(mngVO);
        
    	
        return "forward:/uss/mng/LocalMngList.do"; 
    	
    }    
}
