package clikmng.nanet.go.kr.sit.spc.web;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sit.spc.service.SpcService;
import clikmng.nanet.go.kr.sit.spc.service.SpcVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 스페셜 검색 Controller 클래스
 * @see
 */

@Controller
public class SpcController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "SpcService")
    private SpcService spcService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** 파일관련 */
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    /** Message ID Generation */
    @Resource(name="spcIdGnrService")
    private EgovIdGnrService spcIdGnrService;
    
    @Resource(name="spcKwrdIdGnrService")
    private EgovIdGnrService spcKwrdIdGnrService;
    

//    final private Log logger = LogFactory.getLog(this.getClass());
	

    /**
     * 스페셜검색 조회한다.
     * @param spcVO
     * @param model
     * @return	"/sit/spc/SpcList.do"
     * @throws Exception
     */
    @IncludedInfo(name="스페셜검색 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/sit/spc/SpcList.do")
    public String selectSpcList(@ModelAttribute("spcVO") SpcVO spcVO, Map commandMap, ModelMap model) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	/** EgovPropertyService.SpcList */
    	/*
    	spcVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	spcVO.setPageSize(propertiesService.getInt("pageSize"));
    	*/

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(spcVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(spcVO.getPageUnit());
		paginationInfo.setPageSize(spcVO.getPageSize());

		spcVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		spcVO.setLastIndex(paginationInfo.getLastRecordIndex());
		spcVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
//		
//        if(spcVO.getSelectCountPg() != 0) {
//        	spcVO.setRecordCountPerPage(spcVO.getSelectCountPg());
//        } else {
//        	spcVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//        }
        

        List SpcList = spcService.selectSpcList(spcVO);
        model.addAttribute("resultList", SpcList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        
        int totCnt = (Integer) spcService.selectSpcListTotCnt(spcVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("selectCountperpg", spcVO.getSelectCountPg());
        model.addAttribute("paginationInfo", paginationInfo);    	
        
        return "clikMng/sit/spc/SpcList";
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     * 목록 화면에 이미지 출력
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sit/spc/selectImageFileInfs.do")
    public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {
    	
	// 0. 로그인 여부 확인
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if(!isAuthenticated) {
		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    	return "forward:/uat/uia/LoginUsr.do";
	}	

	String atchFileId = (String)commandMap.get("atchFileId");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectImageFileList(fileVO);
	
	model.addAttribute("fileList", result);

	return "clikMng/cmm/fms/EgovImgFileList";
    }    
    
    
    
    
    
    /**
     * 스페셜검색 등록 페이지
     * @param spcVO
     * @param model
     * @return	"/sit/spc/SpcRegist"
     * @throws Exception
     */
    @RequestMapping("/sit/spc/SpcRegist.do")
    public String spcRegistInfo(
            @ModelAttribute("spcVO") SpcVO spcVO, CmmnDetailCode vo, Model model)
            throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	return "clikMng/sit/spc/SpcRegist";

    }
    
    
    /**
     * ajax 광역시도 정보 불러오기
     * @param searchVO
     * @param model
     * @return	"/sit/spc/SpcRegist"
     * @throws Exception
     */
    @RequestMapping("/sit/spc/selectAjaxBrtc.do")
    public void selectAjaxBrtc(
    		@ModelAttribute("spcVO") SpcVO spcVO
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
     * ajax 지방의회 불러오기
     * @param searchVO
     * @param model
     * @return	"/sit/spc/SpcRegist"
     * @throws Exception
     */
    @RequestMapping("/sit/spc/AjaxAsmbly.do")
    public void selectAjaxAsmbly(
    		@ModelAttribute("spcVO") SpcVO spcVO
    		, CmmnDetailCode vo
    		, HttpServletResponse resp
    		, HttpServletRequest request
    		, ModelMap model)
            throws Exception {

       	// 자치단체 및 의회 코드, 코드명 조회
     	List<CmmnDetailCode> result = cmmUseService.selectLoasmInfo(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
			jso.put("brtcCode", cdc.getBrtcCode());					// 광역시도코드
    		jso.put("insttTyCode", cdc.getInsttTyCode());			// 기관유형코드
    		jso.put("loasmNm", cdc.getLoasmNm());					// 지방의회명
    		jso.put("loasmCode", cdc.getLoasmCode());				// 지방의회코드
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }     

	/**
	 * 스페셜검색 관리 등록
	 * @param spc
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/spc/SpcRegistProc.do")
	public String insertSpc(final MultipartHttpServletRequest multiRequest,
			                   @ModelAttribute("spc") SpcVO spcVO,
			                    BindingResult bindingResult,
			                    SessionStatus status,
			                    ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	spcVO.setMainUrl(URLDecoder.decode(spcVO.getMainUrl(), "UTF-8"));
    	spcVO.setMainSj(URLDecoder.decode(spcVO.getMainSj(), "UTF-8"));
    	spcVO.setMainCtt(URLDecoder.decode(spcVO.getMainCtt(), "UTF-8"));
    	spcVO.setKwrd(URLDecoder.decode(spcVO.getKwrd(), "UTF-8"));
    	
    	spcVO.setSubText1(URLDecoder.decode(spcVO.getSubText1(), "UTF-8"));
    	spcVO.setSubText2(URLDecoder.decode(spcVO.getSubText2(), "UTF-8"));
    	spcVO.setSubText3(URLDecoder.decode(spcVO.getSubText3(), "UTF-8"));
    	spcVO.setSubText4(URLDecoder.decode(spcVO.getSubText4(), "UTF-8"));
    	spcVO.setSubText5(URLDecoder.decode(spcVO.getSubText5(), "UTF-8"));
    	spcVO.setSubText6(URLDecoder.decode(spcVO.getSubText6(), "UTF-8"));
    	
    	spcVO.setSubImage1Url(URLDecoder.decode(spcVO.getSubImage1Url(), "UTF-8"));
    	spcVO.setSubImage2Url(URLDecoder.decode(spcVO.getSubImage2Url(), "UTF-8"));
    	spcVO.setSubImage3Url(URLDecoder.decode(spcVO.getSubImage3Url(), "UTF-8"));
    	spcVO.setSubImage4Url(URLDecoder.decode(spcVO.getSubImage4Url(), "UTF-8"));
    	spcVO.setSubImage5Url(URLDecoder.decode(spcVO.getSubImage5Url(), "UTF-8"));
    	spcVO.setSubImage6Url(URLDecoder.decode(spcVO.getSubImage6Url(), "UTF-8"));
    	
    	beanValidator.validate(spcVO, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("spcVO", spcVO);
			return "clikMng/sit/spc/SpcRegist";
		} else {
	    	List<FileVO> result = null;

	    	String uploadFolder = "Globals.spcFileStorePath";
	    	String[] spcImage = new String[7];
//	    	String[] spcImageFile = new String[7];
	    	String[] spcSortImageFile = new String[7];
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    	    result = fileUtil.parseFileInf(files, "SPC_", 0, "", uploadFolder);
	    	    atchFileId = fileMngService.insertFileInfs(result);

	        	FileVO vo = (FileVO)result.get(0);
	        	Iterator<FileVO> iter = result.iterator();

	        	int i=0;
	        	while (iter.hasNext()) {
	        	    vo = (FileVO)iter.next();
	        	    spcImage[i] = vo.getOrignlFileNm();
	        	    //spcImageFile[i] = vo.getStreFileNm();
	        	    //spcSortImageFile[i] = vo.getStreFileNm();
	        	    spcSortImageFile[i] = atchFileId;
	        	    i++;
	        	}
	    	}
	    	
	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	
			spcVO.setFrstRegisterId(user.getMngrId());
			spcVO.setLastUpdusrId(user.getMngrId());
	        
	    	spcVO.setSpeclSearchId(spcIdGnrService.getNextStringId());
	    	spcVO.setMainImagePath(spcImage[0]);							// 원 파일명
	    	spcVO.setMainImageFileNm(spcSortImageFile[0]);				// 변환된 파일아이디
	    	//SUB 
	    	spcVO.setSubImage1Path(spcImage[1]);
	    	spcVO.setSubImage2Path(spcImage[2]);
	    	spcVO.setSubImage3Path(spcImage[3]);
	    	spcVO.setSubImage4Path(spcImage[4]);
	    	spcVO.setSubImage5Path(spcImage[5]);
	    	spcVO.setSubImage6Path(spcImage[6]);
	    	
	    	spcVO.setSubImage1FileNm(spcSortImageFile[1]);
	    	spcVO.setSubImage2FileNm(spcSortImageFile[2]);
	    	spcVO.setSubImage3FileNm(spcSortImageFile[3]);
	    	spcVO.setSubImage4FileNm(spcSortImageFile[4]);
	    	spcVO.setSubImage5FileNm(spcSortImageFile[5]);
	    	spcVO.setSubImage6FileNm(spcSortImageFile[6]);
	    	
	    	//status.setComplete();
	    	//model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	// 스페셜 검색 등록
	    	spcService.insertSpcInfo(spcVO);
	    	// 스페셜 검색 키워드 등록
	    	// 1. 입력된 키워드를 ,로 분리함.
	    	String[] kwrd = CommonStringUtil.isNullToString(spcVO.getKwrd().replaceAll("\\p{Space}", "")).split(",");
	    	
	    	System.out.println("kwrd size =============== " + kwrd.length);
	    	
	    	// 2. 키워드를 TNPSPECLSEARCHKWRD에 등록.
	    	for(int i=0; i<kwrd.length; i++){
		    	// 2.1 ssearch_keyword PK값 설정
	    		spcVO.setSpeclSearchKwrdId(spcKwrdIdGnrService.getNextStringId());	   
	    		spcVO.setKwrd(kwrd[i]);
	    		spcService.insertKwrdInfo(spcVO);
	    	}	    	
	    	
			return "forward:/sit/spc/SpcList.do";

		}
	}
    
	/**
	 * 스페셜검색 관리 상세보기 및 삭제
	 * @param spc 
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/spc/SpcDetail.do")
	public String DetailSpc(@ModelAttribute("spc") SpcVO spcVO,
									BindingResult bindingResult,
            						FileVO fileVO,
            						HttpServletRequest request,
            						Map commandMap,
            						ModelMap model
			                    ) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

//    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String sLocationUrl	= "clikMng/sit/spc/SpcDetail"; 
    	
    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        

        if(sCmd.equals("del")) {
        	// 스페셜검색 삭제
        	spcService.deleteSpcInf(spcVO);
            sLocationUrl = "redirect:/sit/spc/SpcList.do";        	
        } else {
        	
        	if(StringUtil.isNullToString(request.getParameter("rspeclSearchId")) != "") {
        		spcVO.setSpeclSearchId(request.getParameter("rspeclSearchId"));
        	} 
        	if(StringUtil.isNullToString(request.getParameter("rinsttClCode")) != "") {
        		spcVO.setInsttClCode(request.getParameter("rinsttClCode"));
        	}
        	if(StringUtil.isNullToString(request.getParameter("rbrtcCode")) != "") {
        		spcVO.setBrtcCode(request.getParameter("rbrtcCode"));
        	}
        	if(StringUtil.isNullToString(request.getParameter("rloasmCode")) != "") {
        		spcVO.setLoasmCode(request.getParameter("rloasmCode"));
        	}
        	
        	//상세정보 불러오기
        	SpcVO spcVOs = spcService.selectSpcDetail(spcVO);

        	// 파일 불러오기
        	String atchFileId = spcVOs.getMainImageFileNm();

        	fileVO.setAtchFileId(atchFileId);

        	List<FileVO> result = fileService.selectFileInfs(fileVO);
        	
        	model.addAttribute("fileList", result);
        	model.addAttribute("fileListCnt", result.size());
        	model.addAttribute("atchFileId", atchFileId);
        	
        	//키워드 불러오기
        	List<?> selectkwrdList = spcService.selectSpcDetailKwrd(spcVO);
        	
        	// 키워드 합치기.
        	String[] mergeKwrd = new String[selectkwrdList.size()];
        	
        	for(int i=0; i<selectkwrdList.size(); i++) {
        		SpcVO sVO = (SpcVO)selectkwrdList.get(i);
        		
        		mergeKwrd[i] = sVO.getKwrd();
        	}
        	
        	String kwrdJoin = StringUtils.join(mergeKwrd, ", ");
        	
        	model.addAttribute("insttClCode", spcVO.getInsttClCode());
        	model.addAttribute("brtcCode", spcVO.getBrtcCode());
        	model.addAttribute("loasmCode", spcVO.getLoasmCode());
        	model.addAttribute("spcDetailInfo",  spcVOs);
        	model.addAttribute("kwrdJoin",  kwrdJoin);
        }
    	
    	return sLocationUrl ;

	}
    
	/**
	 * 스페셜검색 관리 수정
	 * @param spc
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/spc/SpcUpdateProc.do")
	public String updateSpc(final MultipartHttpServletRequest multiRequest,
			                   @ModelAttribute("spc") SpcVO spcVO,
			                    BindingResult bindingResult,
			                    SessionStatus status,
			                    ModelMap model) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	spcVO.setMainUrl(URLDecoder.decode(spcVO.getMainUrl(), "UTF-8"));
    	spcVO.setMainSj(URLDecoder.decode(spcVO.getMainSj(), "UTF-8"));
    	spcVO.setMainCtt(URLDecoder.decode(spcVO.getMainCtt(), "UTF-8"));
    	spcVO.setKwrd(URLDecoder.decode(spcVO.getKwrd(), "UTF-8"));
    	
    	spcVO.setSubText1(URLDecoder.decode(spcVO.getSubText1(), "UTF-8"));
    	spcVO.setSubText2(URLDecoder.decode(spcVO.getSubText2(), "UTF-8"));
    	spcVO.setSubText3(URLDecoder.decode(spcVO.getSubText3(), "UTF-8"));
    	spcVO.setSubText4(URLDecoder.decode(spcVO.getSubText4(), "UTF-8"));
    	spcVO.setSubText5(URLDecoder.decode(spcVO.getSubText5(), "UTF-8"));
    	spcVO.setSubText6(URLDecoder.decode(spcVO.getSubText6(), "UTF-8"));
    	
    	spcVO.setSubImage1Url(URLDecoder.decode(spcVO.getSubImage1Url(), "UTF-8"));
    	spcVO.setSubImage2Url(URLDecoder.decode(spcVO.getSubImage2Url(), "UTF-8"));
    	spcVO.setSubImage3Url(URLDecoder.decode(spcVO.getSubImage3Url(), "UTF-8"));
    	spcVO.setSubImage4Url(URLDecoder.decode(spcVO.getSubImage4Url(), "UTF-8"));
    	spcVO.setSubImage5Url(URLDecoder.decode(spcVO.getSubImage5Url(), "UTF-8"));
    	spcVO.setSubImage6Url(URLDecoder.decode(spcVO.getSubImage6Url(), "UTF-8"));
    	
    	beanValidator.validate(spcVO, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("spcVO", spcVO);
			return "clikMng/sit/spc/SpcDetail";
		} else {
	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			spcVO.setLastUpdusrId(user.getMngrId());
	    	spcVO.setSpeclSearchId(spcVO.getSpeclSearchId());
			
			
			List<FileVO> result = null;

	    	String uploadFolder = "Globals.spcFileStorePath";
	    	String[] spcImage = new String[7];
//	    	String[] spcImageFile = new String[7];
	    	String[] spcSortImageFile = new String[7];
	    	String atchFileId = "";
	    	
	    	// 파일 명
	    	String fileName = spcVO.getMainImageFileNm();
	    	
	    	Iterator<String> itr = multiRequest.getFileNames();

	    	String inputFileName = "";
	    	int inputFileSn = 0 ;
	    	while(itr.hasNext()) {
	    		inputFileName = itr.next();
	    		MultipartFile file=multiRequest.getFile(inputFileName);

	    		if(!file.isEmpty()){
	    			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
	    			files.put(inputFileName, file);
	    			inputFileSn = Integer.parseInt(inputFileName.substring(5, 6));
	    			
	    			System.out.println(">>>subString>> " + inputFileName);
	    			result = fileUtil.parseFileInfSpc(files, "SPC_", inputFileSn, fileName, uploadFolder);
	    			
		    	    atchFileId = fileMngService.insertSpcFileInfs(result);
		    	    System.out.println(">>>>>>>>>>>> " + atchFileId);
		    	    
		    	    FileVO vo = (FileVO)result.get(0);
		    	    spcImage[inputFileSn] = vo.getOrignlFileNm();
		    	    spcSortImageFile[inputFileSn] = atchFileId;					// 변환된 파일아이디
		    	    if (inputFileSn == 0) {
		    	    	spcVO.setMainImagePath(spcImage[0]);							// 원 파일명
		    	    	spcVO.setMainImageFileNm(spcSortImageFile[0]);				// 변환된 파일아이디
		    	    }
		    	    if (inputFileSn == 1) {
		    	    	spcVO.setSubImage1Path(spcImage[1]);							// 원 파일명
		    	    	spcVO.setSubImage1FileNm(spcSortImageFile[1]);				// 변환된 파일아이디
		    	    }
		    	    if (inputFileSn == 2) {
		    	    	spcVO.setSubImage2Path(spcImage[2]);							// 원 파일명
		    	    	spcVO.setSubImage2FileNm(spcSortImageFile[2]);				// 변환된 파일아이디
		    	    }
		    	    if (inputFileSn == 3) {
		    	    	spcVO.setSubImage3Path(spcImage[3]);							// 원 파일명
		    	    	spcVO.setSubImage3FileNm(spcSortImageFile[3]);				// 변환된 파일아이디
		    	    }
		    	    if (inputFileSn == 4) {
		    	    	spcVO.setSubImage4Path(spcImage[4]);							// 원 파일명
		    	    	spcVO.setSubImage4FileNm(spcSortImageFile[4]);				// 변환된 파일아이디
		    	    }
		    	    if (inputFileSn == 5) {
		    	    	spcVO.setSubImage5Path(spcImage[5]);							// 원 파일명
		    	    	spcVO.setSubImage5FileNm(spcSortImageFile[5]);				// 변환된 파일아이디
		    	    }
		    	    if (inputFileSn == 6) {
		    	    	spcVO.setSubImage6Path(spcImage[6]);							// 원 파일명
		    	    	spcVO.setSubImage6FileNm(spcSortImageFile[6]);				// 변환된 파일아이디
		    	    }	
		    	    
			    	// 스페셜 검색 수정
		    	    spcVO.setInputFileSn(inputFileSn);
	    		}
	    		
	    	}
	    	
	    	spcService.updateSpcInfo(spcVO);
	    	
	    	// 스페셜 검색 키워드 등록
	    	// 1. 입력된 키워드를 ,로 분리함.
	    	String[] kwrd = CommonStringUtil.isNullToString(spcVO.getKwrd().replaceAll("\\p{Space}", "")).split(",");
	    	
	    	System.out.println("kwrd size =============== " + kwrd.length);
	    	
	    	// 2. 키워드를 TNPSPECLSEARCHKWRD에 등록.
	    	// 삭제 후 입력
	    	spcService.deleteKwrdInfo(spcVO);
	    	for(int j=0; j<kwrd.length; j++){
		    	// 2.1 ssearch_keyword PK값 설정
	    		spcVO.setSpeclSearchKwrdId(spcKwrdIdGnrService.getNextStringId());	   
	    		spcVO.setKwrd(kwrd[j]);
	    		spcService.insertKwrdInfo(spcVO);
	    	}	    	
	    	
			return "redirect:/sit/spc/SpcList.do";

		}
	}

}
