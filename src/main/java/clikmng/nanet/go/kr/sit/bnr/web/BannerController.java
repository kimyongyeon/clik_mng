/**
 * 개요
 * - 배너에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:07:11
 *  * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.8.3	lee.m.j          최초 생성 
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *  
 *  </pre>
 */

package clikmng.nanet.go.kr.sit.bnr.web;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sit.bnr.service.Banner;
import clikmng.nanet.go.kr.sit.bnr.service.BannerVO;
import clikmng.nanet.go.kr.sit.bnr.service.BannerService;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BannerController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "BannerService")
    private BannerService bannerService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Message ID Generation */
    @Resource(name="egovBannerIdGnrService")
    private EgovIdGnrService egovBannerIdGnrService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;
	
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    final private Log logger = LogFactory.getLog(this.getClass());

    /**
	 * 배너 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sit/bnr/selectBannerListView.do")
    public String selectBannerListView(ModelMap model) throws Exception {
    	
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}

        return "clikMng/sit/bnr/EgovBannerList";
    }

	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 URL
	 * @throws Exception
	 */
    @IncludedInfo(name="배너관리", order = 740 ,gid = 50)
    @RequestMapping(value="/sit/bnr/selectBannerList.do")
	public String selectBannerList(@ModelAttribute("bannerVO") BannerVO bannerVO, ModelMap model) throws Exception{
    	
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bannerVO.getPageUnit());
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		bannerVO.setBannerList(bannerService.selectBannerList(bannerVO));

		model.addAttribute("bannerList", bannerVO.getBannerList());
        model.addAttribute("searchKeyword",bannerVO.getSearchKeyword());
        model.addAttribute("searchCondition",bannerVO.getSearchCondition());
        

        int totCnt = bannerService.selectBannerListTotCnt(bannerVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "clikMng/sit/bnr/EgovBannerList";
	}

	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 * @param bannerVO - 배너 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/bnr/getBanner.do")
	public String selectBanner(@RequestParam("bannerId") String bannerId,
			                   @ModelAttribute("bannerVO") BannerVO bannerVO,
			                   ModelMap model) throws Exception {

        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
    	
        //팝업창시작일자(시)
        model.addAttribute("ntceBgndeHH", (List)getTimeHH());
        //팝업창시작일자(분)
        model.addAttribute("ntceBgndeMM", (List)getTimeMM());
        //팝업창종료일자(시)
        model.addAttribute("ntceEnddeHH", (List)getTimeHH());
        //팝업창정료일자(분)
        model.addAttribute("ntceEnddeMM", (List)getTimeMM());

    	BannerVO bannerVOs = bannerService.selectBanner(bannerVO);

    	bannerVOs.setBannerId(bannerId);
    	
    	String sNtceBgnde = bannerVOs.getNtceBgnde();
        String sNtceEndde = bannerVOs.getNtceEndde();
        
        bannerVOs.setNtceBgndeHH(sNtceBgnde.substring(8, 10));
        bannerVOs.setNtceBgndeMM(sNtceBgnde.substring(10, 12));
        
        bannerVOs.setNtceEnddeHH(sNtceEndde.substring(8, 10));
        bannerVOs.setNtceEnddeMM(sNtceEndde.substring(10, 12));
    	
        model.addAttribute("banner", bannerVOs);
        
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "clikMng/sit/bnr/EgovBannerUpdt";
	}

	/**
	 * 배너등록 화면으로 이동한다.
	 * @param banner - 배너 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/bnr/addViewBanner.do")
	public String insertViewBanner(@ModelAttribute("bannerVO") BannerVO bannerVO,
			                        ModelMap model) throws Exception {
    	
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
    	

    	model.addAttribute("banner", bannerVO);
    	
        //팝업창시작일자(시)
        model.addAttribute("ntceBgndeHH", (List)getTimeHH());
        //팝업창시작일자(분)
        model.addAttribute("ntceBgndeMM", (List)getTimeMM());
        //팝업창종료일자(시)
        model.addAttribute("ntceEnddeHH", (List)getTimeHH());
        //팝업창정료일자(분)
        model.addAttribute("ntceEnddeMM", (List)getTimeMM());
    	
    	return "clikMng/sit/bnr/EgovBannerRegist";
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 * @param banner - 배너 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/bnr/addBanner.do")
	public String insertBanner(final MultipartHttpServletRequest multiRequest,
			                   @ModelAttribute("banner") Banner banner,
			                   @ModelAttribute("bannerVO") BannerVO bannerVO,
			                    BindingResult bindingResult,
			                    SessionStatus status,
			                    ModelMap model) throws Exception {

        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
    	
    	//beanValidator.validate(banner, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("bannerVO", bannerVO);
			return "clikMng/sit/bnr/EgovBannerRegist";
		} else {
	    	List<FileVO> result = null;

	    	String uploadFolder = "Globals.bnrFileStorePath";
	    	String bannerImage = "";
	    	String bannerImageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    	    result = fileUtil.parseFileInf(files, "BNR_", 0, "", uploadFolder);
	    	    atchFileId = fileMngService.insertFileInfs(result);

	        	FileVO vo = (FileVO)result.get(0);
	        	Iterator iter = result.iterator();

	        	while (iter.hasNext()) {
	        	    vo = (FileVO)iter.next();
	        	    bannerImage = vo.getOrignlFileNm();
	        	    bannerImageFile = vo.getStreFileNm();
	        	}
	    	}

	    	
	        //팝업창시작일자(시)
	        model.addAttribute("ntceBgndeHH", (List)getTimeHH());
	        //팝업창시작일자(분)
	        model.addAttribute("ntceBgndeMM", (List)getTimeMM());
	        //팝업창종료일자(시)
	        model.addAttribute("ntceEnddeHH", (List)getTimeHH());
	        //팝업창정료일자(분)
	        model.addAttribute("ntceEnddeMM", (List)getTimeMM());
	    	
	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	
	    	banner.setBannerNm(CommonStringUtil.getHtmlStrCnvr(CommonStringUtil.getURLDecode(banner.getBannerNm(),"UTF-8")));
	    	banner.setLinkUrl(CommonStringUtil.getHtmlStrCnvr(CommonStringUtil.getURLDecode(banner.getLinkUrl(),"UTF-8")));
	    	banner.setBannerDc(CommonStringUtil.getHtmlStrCnvr(CommonStringUtil.getURLDecode(banner.getBannerDc(),"UTF-8")));
	    	
	    	banner.setUserId(user.getMngrId());
	    	banner.setBannerId(egovBannerIdGnrService.getNextStringId());
	    	banner.setBannerImage(bannerImage);
	    	banner.setBannerImageFile(atchFileId);
	    	bannerVO.setBannerId(banner.getBannerId());
	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	model.addAttribute("banner", bannerService.insertBanner(banner, bannerVO));

//	    	return "clikMng/sit/bnr/EgovBannerUpdt";
			return "forward:/sit/bnr/selectBannerList.do";

		}
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * @param banner - 배너 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sit/bnr/updtBanner.do")
	public String updateBanner(final MultipartHttpServletRequest multiRequest,
			                   @ModelAttribute("banner") Banner banner,
			                    BindingResult bindingResult,
                                SessionStatus status,
                                ModelMap model) throws Exception {
    	
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
    	
    	try {
    	//beanValidator.validate(banner, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("bannerVO", banner);
			return "clikMng/sit/bnr/EgovBannerUpdt";
		} else {

	    	List<FileVO> result = null;

	    	String uploadFolder = "Globals.bnrFileStorePath";
	    	String bannerImage = "";
	    	String bannerImageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    	    result = fileUtil.parseFileInf(files, "BNR_", 0, "", uploadFolder);
	    	    atchFileId = fileMngService.insertFileInfs(result);

	        	FileVO vo = null;
	        	Iterator iter = result.iterator();

	        	while (iter.hasNext()) {
	        	    vo = (FileVO)iter.next();
	        	    bannerImage = vo.getOrignlFileNm();
	        	    bannerImageFile = vo.getStreFileNm();
	        	}

	        	if (vo == null) {
	        		banner.setAtchFile(false);
	        	} else {
	        		banner.setBannerImage(bannerImage);
	        		banner.setBannerImageFile(atchFileId);
	        		banner.setAtchFile(true);
	        	}
	    	} else {
	    		banner.setAtchFile(false);
	    	}
	    	
		// 로그인VO에서  사용자 정보 가져오기
		    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	banner.setUserId(user.getMngrId());
	    	
	    	banner.setBannerNm(CommonStringUtil.getHtmlStrCnvr(CommonStringUtil.getURLDecode(banner.getBannerNm(),"UTF-8")));
	    	banner.setLinkUrl(CommonStringUtil.getHtmlStrCnvr(CommonStringUtil.getURLDecode(banner.getLinkUrl(),"UTF-8")));
	    	banner.setBannerDc(CommonStringUtil.getHtmlStrCnvr(CommonStringUtil.getURLDecode(banner.getBannerDc(),"UTF-8")));
	    	
	    	bannerService.updateBanner(banner);
//	    	return "forward:/sit/bnr/getBanner.do";
	    	return "forward:/sit/bnr/selectBannerList.do";

		}
    	} catch (Exception ex) {
    		logger.error("Exception:  "  +  ex.getClass().getName());  
    		logger.error("Exception  Message:  "  +  ex.getMessage());
    		throw ex;
    	}
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * @param banner Banner
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sit/bnr/removeBanner.do")
	public String deleteBanner(@RequestParam("bannerId") String bannerId,
			                   @ModelAttribute("banner") Banner banner,
			                    SessionStatus status,
			                    ModelMap model) throws Exception {
    	
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}

    	banner.setBannerId(bannerId);
    	bannerService.deleteBanner(banner);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sit/bnr/selectBannerList.do";
	}

	/**
	 * 기 등록된 배너정보목록을 일괄 삭제한다.
	 * @param banners String
	 * @param banner Banner
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sit/bnr/removeBannerList.do")
	public String deleteBannerList(@RequestParam("bannerIds") String bannerIds,
			                       @ModelAttribute("banner") Banner banner,
			                        SessionStatus status,
			                        ModelMap model) throws Exception {
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
    	
    	String [] strBannerIds = bannerIds.split(";");

    	for(int i=0; i<strBannerIds.length;i++) {
    		banner.setBannerId(strBannerIds[i]);
    		bannerService.deleteBanner(banner);
    	}

    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sit/bnr/selectBannerList.do";
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/sit/bnr/getBannerImage.do")
	public String selectBannerResult(@ModelAttribute("bannerVO") BannerVO bannerVO,
                                      ModelMap model) throws Exception {

        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
		
		List<BannerVO> fileList = bannerService.selectBannerResult(bannerVO);
		model.addAttribute("fileList", fileList);
		model.addAttribute("resultType", bannerVO.getResultType());

		return "clikMng/sit/bnr/EgovBannerView";
	}

	/**
	 * MyPage에 배너정보를 제공하기 위해 목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 URL
	 * @throws Exception
	 */
	@IncludedInfo(name="MYPAGE배너관리", order = 741 ,gid = 50)
    @RequestMapping(value="/sit/bnr/selectBannerMainList.do")
	public String selectBannerMainList(@ModelAttribute("bannerVO") BannerVO bannerVO,
                             		ModelMap model) throws Exception{

        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
		
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		bannerVO.setBannerList(bannerService.selectBannerList(bannerVO));

		model.addAttribute("bannerList", bannerVO.getBannerList());

		return "clikMng/sit/bnr/EgovBannerMainList";
	}
	
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
    
//-------------------------------------  홍보존 이미지 관련 시작 ---------------------------------------------- 
    /**
	 * 홍보존 이미지 관리화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sit/bnr/selectBannerImgMngView.do")
    public String selectBannerImgMngList(@ModelAttribute("bannerVO") BannerVO bannerVO
    														, ModelMap model
    														, Map commandMap) throws Exception {
    	
       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	/** EgovPropertyService.LogMngList */
    	if(bannerVO.getPageUnit() == 0){
    		bannerVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	}
    	bannerVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bannerVO.getPageUnit());
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        
		HashMap _map = (HashMap)bannerService.selectBannerImgMngInfo(bannerVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);		
		//model.addAttribute("selectCountperpg", bannerVO.getSelectCountPg());
		
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        return "clikMng/sit/bnr/BannerImgMngList";
    }
    
    /**
 	 * 홍보존 이미지 관리 등록
 	 * @return String
 	 * @exception Exception
 	 */
     @RequestMapping("/sit/bnr/RegistBannerImgMng.do")
     public String registBannerImgMng(@ModelAttribute("bannerVO") BannerVO bannerVO
     														, ModelMap model
     														, Map commandMap) throws Exception {
     	
        // 0. 로그인 여부 확인
     	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
     	if(!isAuthenticated) {
     		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
         	return "forward:/uat/uia/LoginUsr.do";
     	}
     	
     	// 지역코드 셀렉트 박스 (공통코드 코드아이디 : LMC)
     	List areaList = bannerService.selectAreaCodeList(bannerVO);
     	model.addAttribute("areaList", areaList);
     	
         return "clikMng/sit/bnr/BannerImgMngRegist";
     }    

     
     /**
  	 * 홍보존 이미지 관리 등록처리
  	 * @return String
  	 * @exception Exception
  	 */
      @RequestMapping("/sit/bnr/RegistBannerImgMngProc.do")
      public String registBannerImgMngProc(final MultipartHttpServletRequest multiRequest
     		 												, @ModelAttribute("bannerVO") BannerVO bannerVO
      														, ModelMap model
      														, Map commandMap) throws Exception {
      	
         // 0. 로그인 여부 확인
      	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
      	if(!isAuthenticated) {
      		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
          	return "forward:/uat/uia/LoginUsr.do";
      	}

      	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
      	
      	// 등록자 ID
      	bannerVO.setFrstRegisterId(loginVO.getMngrId());
      	bannerVO.setLastUpdusrId(loginVO.getMngrId());
      	
      	try {
         	List<FileVO> result = null;

         	String uploadFolder = "Globals.bnrFileStorePath";
         	String bannerImage = "";
         	String bannerImageFile = "";
         	String atchFileId = "";

         	final Map<String, MultipartFile> files = multiRequest.getFileMap();

         	if(!files.isEmpty()){
         		// 업로드 이미지 폴더 지정
         	    result = fileUtil.parseFileInf(files, "BIM_", 0, "", uploadFolder);
         	    atchFileId = fileMngService.insertFileInfs(result);

             	FileVO vo = null;
             	Iterator iter = result.iterator();

             	while (iter.hasNext()) {
             	    vo = (FileVO)iter.next();
             	    bannerImage = vo.getOrignlFileNm();
             	    bannerImageFile = vo.getStreFileNm();
             	}

             	if (vo == null) {
             		bannerVO.setAtchFile(false);
             	} else {
             		bannerVO.setImageFileNm(bannerImage);
             		bannerVO.setImageCnvrFileNm(atchFileId);
             		bannerVO.setAtchFile(true);
             	}
         	} else {
         		bannerVO.setAtchFile(false);
         	}
          	
         	// 홍보존 소식 이미지 관리 등록
         	bannerService.insertBannerImgMng(bannerVO);
			
		} catch (Exception ex) {
    		logger.error("Exception:  "  +  ex.getClass().getName());  
    		logger.error("Exception  Message:  "  +  ex.getMessage());
    		throw ex;

		}
     	
     	return "redirect:/sit/bnr/selectBannerImgMngView.do";
      }    
     
     
     /**
      * ajax 광역시도 정보 불러오기
      * @param searchVO
      * @param model
      * @return	clikMng/sit/bnr/BannerImgMngRegist   
      * @throws Exception
      */
     @RequestMapping("/sit/bnr/selectAjaxBrtc.do")
     public void selectAjaxBrtc(@ModelAttribute("bannerVO") BannerVO bannerVO
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
      * @return	clikMng/sit/bnr/BannerImgMngRegist
      * @throws Exception
      */
     @RequestMapping("/sit/bnr/selectAjaxPsitn.do")
     public void selectAjaxPsitn(@ModelAttribute("bannerVO") BannerVO bannerVO
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
   	 * 홍보존 이미지 관리 수정페이지
   	 * @return String
   	 * @exception Exception
   	 */
       @RequestMapping("/sit/bnr/DetailBannerImgMng.do")
       public String detailBannerImgMng(@ModelAttribute("bannerVO") BannerVO bannerVO
    		   												, HttpServletRequest request
    		   												, FileVO fileVO
       														, Map commandMap
       														, ModelMap model) throws Exception {
       	
          // 0. 로그인 여부 확인
       	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
       	if(!isAuthenticated) {
       		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
           	return "forward:/uat/uia/LoginUsr.do";
       	}

    	//상세정보 불러오기
       	String rBannerImgMngId = "";
    	if(StringUtil.isNullToString(request.getParameter("rBannerImgMngId")) != "") {
    		rBannerImgMngId = StringUtil.isNullToString(request.getParameter("rBannerImgMngId").toString());
    		bannerVO.setBannerImgMngId(rBannerImgMngId);
    	} 
       	
       	// 수정상세정보 조회
       	BannerVO vo = bannerService.selectBannerImgMngDetail(bannerVO);
       	model.addAttribute("resultVO", vo);
       	
     	// 지역코드 셀렉트 박스 (공통코드 코드아이디 : LMC)
     	List areaList = bannerService.selectAreaCodeList(bannerVO);
     	model.addAttribute("areaList", areaList);
     	
       	// 파일
    	String atchFileId = vo.getImageCnvrFileNm();

    	fileVO.setAtchFileId(atchFileId);

    	List<FileVO> result = fileMngService.selectFileInfs(fileVO);
    	
    	model.addAttribute("fileList", result);
    	model.addAttribute("fileListCnt", result.size());
    	model.addAttribute("atchFileId", atchFileId);
       	
      	return "clikMng/sit/bnr/BannerImgMngDetail";
       }              
     
     /**
  	 * 홍보존 이미지 관리 수정처리
  	 * @return String
  	 * @exception Exception
  	 */
      @RequestMapping("/sit/bnr/DetailBannerImgMngProc.do")
      public String detailBannerImgMngProc(final MultipartHttpServletRequest multiRequest
    		  												, @ModelAttribute("bannerVO") BannerVO bannerVO
      														, Map commandMap
      														, ModelMap model) throws Exception {
      	
         // 0. 로그인 여부 확인
      	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
      	if(!isAuthenticated) {
      		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
          	return "forward:/uat/uia/LoginUsr.do";
      	}

      	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
      	
      	// 최종등록자 ID
      	bannerVO.setLastUpdusrId(loginVO.getMngrId());
      	
      	try {
         	List<FileVO> result = null;

         	String uploadFolder = "Globals.bnrFileStorePath";
         	String bannerImage = "";
         	String bannerImageFile = "";
         	String atchFileId = "";

         	final Map<String, MultipartFile> files = multiRequest.getFileMap();

         	if(!files.isEmpty()){
         		// 업로드 이미지 폴더 지정
         	    result = fileUtil.parseFileInf(files, "BIM_", 0, "", uploadFolder);
         	    atchFileId = fileMngService.insertFileInfs(result);

             	FileVO vo = null;
             	Iterator iter = result.iterator();

             	while (iter.hasNext()) {
             	    vo = (FileVO)iter.next();
             	    bannerImage = vo.getOrignlFileNm();
             	    bannerImageFile = vo.getStreFileNm();
             	}

             	if (vo == null) {
             		bannerVO.setAtchFile(false);
             	} else {
             		bannerVO.setImageFileNm(bannerImage);
             		bannerVO.setImageCnvrFileNm(atchFileId);
             		bannerVO.setAtchFile(true);
             	}
         	} else {
         		bannerVO.setAtchFile(false);
         	}
          	
         	// 홍보존 소식 이미지 관리 수정처리
         	bannerService.updateBannerImgMng(bannerVO);
			
		} catch (Exception ex) {
    		logger.error("Exception:  "  +  ex.getClass().getName());  
    		logger.error("Exception  Message:  "  +  ex.getMessage());
    		throw ex;

		}	
     	return "redirect:/sit/bnr/selectBannerImgMngView.do";
      }         
      

  	/**
  	 * 홍보존소식 이미지 관리 삭제
  	 * @param banner Banner
  	 * @return String
  	 * @exception Exception
  	 */
    @RequestMapping(value="/sit/bnr/DeleteBannerImgMngProc.do")
  	public String deleteBannerImgMng(@ModelAttribute("bannerVO") BannerVO bannerVO
												, FileVO fileVO
												, Map commandMap
												, ModelMap model) throws Exception {
      	
          // 0. 로그인 여부 확인
       	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
       	if(!isAuthenticated) {
       		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
           	return "forward:/uat/uia/LoginUsr.do";
       	}

       	bannerVO.setBannerId(bannerVO.getBannerImgMngId());
      	bannerService.deleteBannerImgMng(bannerVO);
      	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
  		return "forward:/sit/bnr/selectBannerImgMngView.do";
  	}
    
//-------------------------------------  홍보존 이미지 관련 끝 ----------------------------------------------     
}
