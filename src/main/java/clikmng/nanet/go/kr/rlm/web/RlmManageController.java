package clikmng.nanet.go.kr.rlm.web;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
import clikmng.nanet.go.kr.rlm.service.CodeVO;
import clikmng.nanet.go.kr.rlm.service.HrsmnpdPprtyVO;
import clikmng.nanet.go.kr.rlm.service.HrsmnpdVO;
import clikmng.nanet.go.kr.rlm.service.PprtyVO;
import clikmng.nanet.go.kr.rlm.service.RasmblyNumprPdVO;
import clikmng.nanet.go.kr.rlm.service.RasmblyVO;
import clikmng.nanet.go.kr.rlm.service.RlmManageDefaultVO;
import clikmng.nanet.go.kr.rlm.service.RlmManageService;
import clikmng.nanet.go.kr.rlm.service.RlmManageVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 지방의회 연계관리를 처리하는 Controller 클래스
 */

@Controller
public class RlmManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "RlmManageService")
    private RlmManageService RlmManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/** ID Generation */
    @Resource(name="stdCntcApiColctIdGnrService")
    private EgovIdGnrService stdCntcApiColctIdGnrService;
    
    @Resource(name="councilSystemContrlIdGnrService")
    private EgovIdGnrService councilSystemContrlIdGnrService;
    
    /**
     * 지방의회 연계관리 - 수집 API Key 관리 목록 화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/CollectionApiList"
     * @throws Exception
     */
    @IncludedInfo(name="수집 api key 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionApiList.do")
    public String selectCollectionApiList(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, ModelMap model) throws Exception {

    	/** EgovPropertyService.LogList */
		if(searchVO.getPageUnit() == 0){
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		}
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
		
		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		
		// 3. 지방의회 상태 코드
		cmmCodeVO.setCodeId("RIS023");
		List<?> statusCodeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("statusCodeList", statusCodeList);

        List<RasmblyVO> rasmblyList = RlmManageService.selectRasmblyApiKeyList(searchVO);

        int totCnt = RlmManageService.selectRasmblyApiKeyListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + "-" + searchVO.getSchDt1().substring(4, 6) + "-" + searchVO.getSchDt1().substring(6, 8));
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + "-" + searchVO.getSchDt2().substring(4, 6) + "-" + searchVO.getSchDt2().substring(6, 8));
		
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("paginationInfo", paginationInfo);
    	model.addAttribute("resultList", rasmblyList);
    	
        return "clikMng/rlm/CollectionApiList";
    }
    
    
    
    
    /**
     * 지방의회 연계관리 - 수집 API Key 등록화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/CollectionApiRegist"
     * @throws Exception
     */
    @IncludedInfo(name="수집 api key 등록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionApiRegist.do")
    public String collectionApiRegist(@ModelAttribute("RasmblyVO") RasmblyVO rasmblyVO, ModelMap model) throws Exception {
        List<CodeVO> codeList = RlmManageService.getCommCodeList("RIS023");
        model.put("codeList",codeList);
    	return "clikMng/rlm/CollectionApiRegist";
    }
    
    /**
     * 지방의회 연계관리 - 수집 API Key 정보를 처리한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/CollectionApiRegist"
     * @throws Exception
     */
    @IncludedInfo(name="수집 api key 등록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionApiRegistProc.do")
    public String collectionApiRegistProc(
    		@ModelAttribute("RasmblyVO") RasmblyVO rasmblyVO
    		, HttpServletRequest request
    		, ModelMap model) throws Exception {

    	List<CodeVO> codeList = RlmManageService.getCommCodeList("RIS023");
        model.put("codeList",codeList);
    	
    	if(rasmblyVO.getApi_crtfc_key() == null || "".equals(rasmblyVO.getApi_crtfc_key()))
    	{
    		RasmblyVO result = RlmManageService.selectRasmblyApiKey(rasmblyVO);

    		if(result == null)
    		{
    			rasmblyVO.setApi_crtfc_key(encodeSHA256(rasmblyVO.getRasmbly_id()));
    			RlmManageService.insertRasmblyApiKey(rasmblyVO);
    			
    			if("RAS002".equals(rasmblyVO.getRasmbly_sttus_code())){
    				
    				rasmblyVO.setRasmbly_prmtago_id(rasmblyVO.getRasmbly_id());
    				rasmblyVO.setRasmbly_id(request.getParameter("prmt_rasmbly_id"));
    				if(RlmManageService.updatePrmtRasmbly(rasmblyVO) == 0){
    					RlmManageService.insertPrmtRasmbly(rasmblyVO);
    				}
    			}
    		}
    		else
    		{
    			model.put("errMsg","중복된 아이디 입니다.");
    			model.put("rasmblyVO",rasmblyVO);
    			return "clikMng/rlm/CollectionApiRegist";
    		}
    		
        	model.put("resultMsg","정상 처리되었습니다.");
        	return "forward:/rlm/CollectionApiList.do";
    		
    	}
    	else
    	{
    		RlmManageService.updateRasmblyApiKey(rasmblyVO);
    		
    		if("RAS002".equals(rasmblyVO.getRasmbly_sttus_code())){
				
				rasmblyVO.setRasmbly_prmtago_id(rasmblyVO.getRasmbly_id());
				rasmblyVO.setRasmbly_id(request.getParameter("prmt_rasmbly_id"));
				if(RlmManageService.updatePrmtRasmbly(rasmblyVO) == 0){
					RlmManageService.insertPrmtRasmbly(rasmblyVO);
				}
			}else{
				RlmManageService.deletePrmtRasmbly(rasmblyVO);
			}
    		
        	model.put("resultMsg","수정되었습니다.");
        	return "forward:/rlm/CollectionApiDetail.do";
    		
    	}
    	
    	
    }
    
    /**
     * 지방의회 연계관리 - 등록된 지방의회인지 조회
     * @param searchVO
     * @param model
     * @return	"/rlm/CollectionApiRegist"
     * @throws Exception
     */
    @IncludedInfo(name="지방의회 등록 여부 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionApiExist.do")
    public void collectionApiExist(
    			@ModelAttribute("RasmblyVO") RasmblyVO rasmblyVO
    		,	ModelMap model
    		, 	HttpServletResponse resp
    		, 	HttpServletRequest request) throws Exception {

    	
    		boolean isExist = false;

    		RasmblyVO result = RlmManageService.selectRasmblyApiKey(rasmblyVO);
    		
    		if(result != null)
    		{
    			isExist = true;
    		}
    		
    		resp.setContentType("text/html;charset=utf-8");
    		PrintWriter out = resp.getWriter();  
    		out.print(isExist);

    
    }
    
    /**
     * 지방의회 연계관리 - 수집 API Key 삭제처리한다.
     * @param 삭제 할 API Key 리스트
     * @param model
     * @return	"clikMng/rlm/CollectionApiList"
     * @throws Exception
     */
    @IncludedInfo(name="수집 api key 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionApiDelete.do")
    public String collectionApiDelete(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, 
    		HttpServletRequest request, ModelMap model) throws Exception {
    	String[] rasmblyIdList = request.getParameter("deleteKeyList").split(",");
    	
    	for(String key : rasmblyIdList){
    		RlmManageService.deleteRasmblyApiKey(key);
    	}
    	
    	model.addAttribute("searchVO", searchVO);
    	
        return "forward:/rlm/CollectionApiList.do";
    }
    
    /**
     * 지방의회 연계관리 - 수집 API Key 상세보기
     * @param searchVO
     * @param model
     * @return	"/rlm/RlmInqire"
     * @throws Exception
     */
    @IncludedInfo(name="수집 api key 상세보기", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionApiDetail.do")
    public String collectionApiDetail(@ModelAttribute("RasmblyVO") RasmblyVO rasmblyVO, ModelMap model) throws Exception {

    	RasmblyVO result = RlmManageService.selectRasmblyApiKey(rasmblyVO);
    	List<CodeVO> codeList = RlmManageService.getCommCodeList("RIS023");
        
    	model.put("codeList",codeList);
    	model.addAttribute("result", result);
    	
    	return "clikMng/rlm/CollectionApiDetail";
    }        

    /**
     * 지방의회 연계관리 - 대수/기수 목록
     * @param searchVO
     * @param model
     * @return	"/rlm/CollectionApiList"
     * @throws Exception
     */
    @IncludedInfo(name="대수/기수 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/GenerationFlagList.do")
    public String selectGenerationFlagList(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, ModelMap model) throws Exception {

    	/** EgovPropertyService.LogList */
		if(searchVO.getPageUnit() == 0){
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		}
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		
		List<HrsmnpdVO> list = RlmManageService.selectGenerationFlagList(searchVO);

        int totCnt = 0;
        
        if(list.size() > 0){
        	totCnt = list.get(0).getTotcnt();
        }
        
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("paginationInfo", paginationInfo);
    	model.addAttribute("resultList", list);
    	
        return "clikMng/rlm/GenerationFlagList";
    }
    
    /**
     * 지방의회 연계관리 - 대수/기수 등록 화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/GenerationFlagRegist"
     * @throws Exception
     */
    @IncludedInfo(name="대수/기수 등록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/GenerationFlagRegist.do")
    public String GenerationFlagRegist(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
        return "clikMng/rlm/GenerationFlagRegist";
    }
    
    /**
     * 지방의회 연계관리 - 대수/기수 등록,수정을 처리한다.
     * @param searchVO
     * @param model
     * @return 처리결과
     * @throws Exception
     */
    @IncludedInfo(name="대수/기수 등록,수정 처리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/GenerationFlagRegistProc.do")
    public String GenerationFlagRegistProc(@ModelAttribute("HrsmnpdVO") HrsmnpdVO hrsmnpdVO,
    		HttpServletRequest request,
    		ModelMap model) throws Exception {
    	
    	hrsmnpdVO.setBegin_de(hrsmnpdVO.getBegin_de().replaceAll("-", ""));
    	hrsmnpdVO.setEnd_de(hrsmnpdVO.getEnd_de().replaceAll("-", ""));
    	hrsmnpdVO.setFrhfyr_begin_de(hrsmnpdVO.getFrhfyr_begin_de().replaceAll("-", ""));
    	hrsmnpdVO.setFrhfyr_end_de(hrsmnpdVO.getFrhfyr_end_de().replaceAll("-", ""));
    	hrsmnpdVO.setShyy_begin_de(hrsmnpdVO.getShyy_begin_de().replaceAll("-", ""));
    	hrsmnpdVO.setShyy_end_de(hrsmnpdVO.getShyy_end_de().replaceAll("-", ""));
    	
    	if("C".equals(request.getParameter("mode")))
    	{
    		String pk = RlmManageService.getNextPKValue("TNMHRSMNPD");
    		RlmManageService.setNextPKValue("TNMHRSMNPD");
    		hrsmnpdVO.setHrsmnpd_sn(pk);
    		
    		RlmManageService.insertHrsmnpd(hrsmnpdVO);
    		
    		if(!"".equals(request.getParameter("mapping_rasmbly")))
    		{
    			String[] numprpdList = request.getParameter("mapping_rasmbly").split(",");
    			RasmblyNumprPdVO vo = null;
    			for(String str : numprpdList){
    				vo = new RasmblyNumprPdVO();
    				vo.setHrsmnpd_sn(pk);
    				vo.setRasmbly_id(str.split("#")[0]);
    				vo.setRasmbly_numpr(str.split("#")[1]);
    				RlmManageService.insertNumprpd(vo);
    			}
    		}
    	}
    	else if("U".equals(request.getParameter("mode")))
    	{
    		RlmManageService.updateHrsmnpd(hrsmnpdVO);
    		
    		if(!"".equals(request.getParameter("mapping_rasmbly")))
    		{
    			RlmManageService.deleteNumprpd(hrsmnpdVO);
    			
    			String[] numprpdList = request.getParameter("mapping_rasmbly").split(",");
    			RasmblyNumprPdVO vo = null;
    			for(String str : numprpdList){
    				vo = new RasmblyNumprPdVO();
    				vo.setHrsmnpd_sn(hrsmnpdVO.getHrsmnpd_sn());
    				vo.setRasmbly_id(str.split("#")[0]);
    				vo.setRasmbly_numpr(str.split("#")[1]);
    				
    				if(RlmManageService.updateNumprpd(vo) == 0)
    				{
    					RlmManageService.insertNumprpd(vo);
    				}
    			}
    		}
    	}
    	else if("D".equals(request.getParameter("mode")))
    	{
    		RlmManageService.deleteHrsmnpd(hrsmnpdVO);
    		RlmManageService.deleteNumprpd(hrsmnpdVO);
    	}
    	
        return "redirect:/rlm/GenerationFlagList.do";
    }
    
    /**
     * 지방의회 연계관리 - 대수/기수 상세보기
     * @param searchVO
     * @param model
     * @return	"/rlm/RlmInqire"
     * @throws Exception
     */
    @IncludedInfo(name="대수/기수 상세보기", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/GenerationFlagDetail.do")
    public String GenerationFlagDetail(@ModelAttribute("HrsmnpdVO") HrsmnpdVO searchVO, ModelMap model) throws Exception {
    	
    	if(searchVO.getHrsmnpd_sn() == null || searchVO.getHrsmnpd_sn().equals(""))
    	{
    		model.addAttribute("errMsg", "해당하는 정보가 존재하지 않습니다.");
    	}
    	else
    	{
    		HrsmnpdVO vo = RlmManageService.selectGenerationFlag(searchVO);
    		List<RasmblyNumprPdVO> resultList = RlmManageService.selectRasmblyNumprPdList(searchVO);
    		
    		model.addAttribute("resultObj", vo);
    		model.addAttribute("resultList", resultList);
    	}
    	
        return "clikMng/rlm/GenerationFlagDetail";
    }
    
    /**
     * 지방의회 연계관리 - 대수/기수 새로운 맵핑 정보를 조회한다.
     * @param HrsmnpdVO
     * @param model
     * @return 조회결과
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="대수/기수 새로운 맵핑 정보", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/ajaxSearchNumprMapping.do")
    public void ajaxSearchNumprMapping(@ModelAttribute("HrsmnpdVO") HrsmnpdVO searchVO, 
    		HttpServletRequest request,
    		HttpServletResponse response,
    		ModelMap model) throws Exception {
    	
    	List<HashMap<String,Object>> list = RlmManageService.selectNumprMapping(searchVO);
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();

    	for(int i = 0; i < list.size(); i++) {
    		jso = new JSONObject();
    		HashMap<String, Object> map = list.get(i);
    		jso.put("0", map.get("RASMBLY_ID")+"#"+map.get("RASMBLY_NM")+"#"+map.get("RASMBLY_NUMPR"));
    		jarray.add(i, jso);
    	}

    	response.setHeader("Content-Type", "text/html;charset=utf-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
    	out.print(jarray.toString());
    	out.flush();
    }
    
    /**
     * 지방의회 연계관리 - 기수별 정당 관리 화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/HrsmnPprtyMngList"
     * @throws Exception
     */
    @IncludedInfo(name="기수별 정당 관리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/HrsmnPprtyMngList.do")
    public String HrsmnPprtyMngList(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	List<HrsmnpdPprtyVO> list = RlmManageService.selectHrsmnPprtyMngList();
    	model.put("resultList", list);
        return "clikMng/rlm/HrsmnPprtyMngList";
    }
    
    /**
     * 지방의회 연계관리 - 기수별 정당 등록 화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/HrsmnPprtyMngRegist"
     * @throws Exception
     */
    @IncludedInfo(name="기수별 정당 등록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/HrsmnPprtyMngRegist.do")
    public String HrsmnPprtyMngRegist(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, ModelMap model) throws Exception {

    	List<HrsmnpdVO> hrsmnList = RlmManageService.selectGenerationFlagList(null);
    	List<PprtyVO> pprtyList = RlmManageService.selectPprtyList(null);
    	List<HrsmnpdPprtyVO> list = RlmManageService.selectHrsmnPprtyMng(null);

    	model.put("HrsmnpdPprtyList", list);
    	model.put("HrsmnList", hrsmnList);
    	model.put("pprtyList", pprtyList);

        return "clikMng/rlm/HrsmnPprtyMngRegist";
    }
    
    /**
     * 지방의회 연계관리 - 기수별 정당 상세 화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/HrsmnPprtyMngDetail"
     * @throws Exception
     */
    @IncludedInfo(name="기수별 정당 상세", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/HrsmnPprtyMngDetail.do")
    public String HrsmnPprtyMngDetail(@ModelAttribute("HrsmnpdPprtyVO") HrsmnpdPprtyVO searchVO, ModelMap model) throws Exception {
    	
    	List<HrsmnpdVO> hrsmnList = RlmManageService.selectGenerationFlagList(null);
    	List<PprtyVO> pprtyList = RlmManageService.selectPprtyList(null);
    	List<HrsmnpdPprtyVO> list = RlmManageService.selectHrsmnPprtyMng(searchVO);

    	HashMap<String, String> resultMap = new HashMap<String, String>();
    	
    	for(HrsmnpdPprtyVO vo : list)
		{
    		resultMap.put(vo.getPprty_code(), "true");
		}

    	model.put("HrsmnpdPprtyVO", searchVO);
    	model.put("resultList", list);
    	model.put("resultMap", resultMap);
    	model.put("HrsmnList", hrsmnList);
    	model.put("pprtyList", pprtyList);
        
    	return "clikMng/rlm/HrsmnPprtyMngDetail";
    }
    
    /**
     * 지방의회 연계관리 - 기수별 정당 정보를 처리한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/HrsmnPprtyMngRegistProc"
     * @throws Exception
     */
    @IncludedInfo(name="기수별 정당 정보 처리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/HrsmnPprtyMngRegistProc.do")
    public String HrsmnPprtyMngRegistProc(@ModelAttribute("HrsmnpdPprtyVO") HrsmnpdPprtyVO hrsmnpdPprtyVO,
    		HttpServletRequest request,
    		ModelMap model) throws Exception {
    	
    	String[] pprtyList = null;
    	
    	if(request.getParameter("pprty_list") != null && !request.getParameter("pprty_list").equals(""))
    		pprtyList = request.getParameter("pprty_list").split(",");
    	
    	String ht_se_code = hrsmnpdPprtyVO.getHrsmnpd_sn().split("#")[1];
    	
    	HrsmnpdVO hrsmnpdVO = new HrsmnpdVO();
    	hrsmnpdVO.setHrsmnpd_sn(hrsmnpdPprtyVO.getHrsmnpd_sn().split("#")[0]);
    	
    	hrsmnpdVO = RlmManageService.selectGenerationFlag(hrsmnpdVO);
    	
    	hrsmnpdPprtyVO.setHrsmnpd_sn(hrsmnpdPprtyVO.getHrsmnpd_sn().split("#")[0]);
    	hrsmnpdPprtyVO.setHt_se_stdcd(ht_se_code);
    	RlmManageService.deleteHrsmnPprtyMng(hrsmnpdPprtyVO);
    	
    	if(request.getParameter("pprty_list") != null && !request.getParameter("pprty_list").equals("")){
	    	for(String str : pprtyList){
	    		hrsmnpdPprtyVO = new HrsmnpdPprtyVO();
	    		hrsmnpdPprtyVO.setHt_se_stdcd(ht_se_code);
	    		hrsmnpdPprtyVO.setHrsmnpd_sn(hrsmnpdVO.getHrsmnpd_sn());
	    		hrsmnpdPprtyVO.setPprty_code(str);
	    		hrsmnpdPprtyVO.setBegin_de(hrsmnpdVO.getBegin_de().replaceAll("-", ""));
	    		hrsmnpdPprtyVO.setEnd_de(hrsmnpdVO.getEnd_de().replaceAll("-", ""));
	    		
	    		if(RlmManageService.updateHrsmnPprtyMng(hrsmnpdPprtyVO) == 0)
	    			RlmManageService.insertHrsmnPprtyMng(hrsmnpdPprtyVO);
	    	}
    	}
    	
        return "redirect:/rlm/HrsmnPprtyMngList.do";
    }
    
    /**
	 * 문자열을 SHA-256으로 encode하여 반환해준다. 
	 * 
	 * @author lth
	 * @param str : SHA-256 해쉬값을 얻을 문자열
	 * @return encode 된 문자열
	 * @throws Exception 
	 * */
	public static String encodeSHA256(String str) throws Exception{

		String encryptedSHA256 = ""; 
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes(), 0, str.length());
			encryptedSHA256 = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			
		}
		
		return encryptedSHA256;
	}
	
    /**
     * 지방의회 연계관리 - 수집 API Key 관리 목록 화면을 호출한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/CollectionApiList"
     * @throws Exception
     */
    @IncludedInfo(name="수집 api 수집 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/CollectionInfoList.do")
    public String selectCollectionInfoList(@ModelAttribute("searchVO") RlmManageVO searchVO, ModelMap model) throws Exception {
    	
       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	/** EgovPropertyService.LogMngList */
    	if(searchVO.getPageUnit() == 0){
    		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	}
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
			searchVO.setSchDt1(searchVO.getSchDt1().replace("-", ""));
		if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
			searchVO.setSchDt2(searchVO.getSchDt2().replace("-", ""));
		
		// 연계의회 목록
		List<RlmManageVO> rasmblyList =  RlmManageService.selectRasmblyList();
		// 연계API 구분
		List<RlmManageVO> apiList =  RlmManageService.selectApiList();
		
		model.addAttribute("rasmblyList", rasmblyList);
		model.addAttribute("apiList", apiList);
		
		HashMap<String, Object> _map = (HashMap<String, Object>)RlmManageService.selectCollectionInfoList(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);		
		
		model.addAttribute("selectRasmbly", searchVO.getSelectRasmbly() == null ? "" : (String) searchVO.getSelectRasmbly() );
        model.addAttribute("selectApi", searchVO.getSelectApi() == null ? "" : (String) searchVO.getSelectApi());
        
        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + "-" + searchVO.getSchDt1().substring(4, 6) + "-" + searchVO.getSchDt1().substring(6, 8));
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + "-" + searchVO.getSchDt2().substring(4, 6) + "-" + searchVO.getSchDt2().substring(6, 8));
		
		model.addAttribute("searchVO", searchVO);
        
        return "clikMng/rlm/CollectionInfoList";
    }	
    
    /**
     * 수집관리 - 표준연계API 수집관리 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/rlm/StdCntcApiColctMngList.do"
     * @throws Exception
     */
    @IncludedInfo(name="표준연계API 수집관리 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/StdCntcApiColctMngList.do")
    public String selectStdCntcApiColctMngList(@ModelAttribute("searchVO") RlmManageVO searchVO, ModelMap model) throws Exception {
    	
       	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	/** EgovPropertyService.LogMngList */
    	if(searchVO.getPageUnit() == 0){
    		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	}
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		CmmnDetailCode vo = new CmmnDetailCode();
		
    	//지방의회로 고정
    	searchVO.setINSTT_CL_CODE("INTSTTCL_000012");
    	
    	//검색 정보 설정을 위하여 의회 정보가 존재할 경우 조회
    	if(searchVO.getLOASM_CODE() != null && !"".equals(searchVO.getLOASM_CODE())){
    		HashMap<String, Object> param = new HashMap<String, Object>();
    		param.put("RASMBLY_ID", searchVO.getLOASM_CODE());
    		HashMap<String,Object> obj = RlmManageService.selectRasmblyInfo(param);
    		
    		searchVO.setINSTT_CL_CODE(obj.get("UPPER_INSTT_CL_CODE").toString());
    		searchVO.setINSTT_TY_CODE(obj.get("INSTT_CL_CODE").toString());
    		searchVO.setLOASM_TY_CODE(obj.get("LOASM_TY_CODE").toString());
    		searchVO.setBRTC_CODE(obj.get("BRTC_CODE").toString());
    	}
    	
    	//지역목록
    	if(searchVO.getINSTT_TY_CODE() != null && !"".equals(searchVO.getINSTT_TY_CODE())){
    		vo.setBrtcCode("LMC");
    		List<CmmnDetailCode> brtc_code_list = cmmUseService.selectBrtcCodeDetails(vo);
    		model.addAttribute("brtc_code_list", brtc_code_list);
    	}

    	//의회목록
     	if(searchVO.getBRTC_CODE() != null && !"".equals(searchVO.getBRTC_CODE())){
    		vo.setInsttClCode(searchVO.getINSTT_TY_CODE());
    		vo.setBrtcCode(searchVO.getBRTC_CODE());
         	List<CmmnDetailCode> loasm_code_list = cmmUseService.selectLoasmInfo(vo);
         	model.addAttribute("loasm_code_list", loasm_code_list);
    	}
		
     	//목록 조회
		List<HashMap<String,Object>> _map = (List<HashMap<String,Object>>)RlmManageService.selectStdCntcApiColctMngList(searchVO);
		int totCnt = 0;
		
		if(_map != null && _map.size() > 0)
			totCnt = Integer.parseInt(_map.get(0).get("TOTCNT").toString());
		
		model.addAttribute("resultList", _map);
		model.addAttribute("resultCnt", totCnt);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);		
		
		model.addAttribute("searchVO", searchVO);
        
        return "clikMng/rlm/StdCntcApiColctMngList";
    }
    
    /**
     * 수집관리 - 표준연계API 수집요청 정보를 처리한다.
     * @param searchVO
     * @param model
     * @return	처리결과
     * @throws Exception
     */
    @IncludedInfo(name="표준연계API 수집요청 정보 처리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/StdCntcApiColctProc.do")
    public String StdCntcApiColctProc(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	String return_url = "/rlm/StdCntcApiColctMngList.do";
    	
    	try{
    		//지정 재 수집할 경우 화면이 달라 url로 분기 되도록 
    		if(request.getParameter("return_url") != null && !"".equals(request.getParameter("return_url"))){
    			return_url = request.getParameter("return_url");
    		}
    		
	    	HashMap<String,Object> param = new HashMap<String,Object>();
	    	param.put("RASMBLY_ID", request.getParameter("LOASM_CODE"));
	    	param.put("DTA_SE_CODE", request.getParameter("DTA_SE_CODE"));
	    	param.put("COLCT_SE_CODE", request.getParameter("COLCT_SE_CODE"));
	    	param.put("PROCESS_STTUS_CODE", "N");
	
	    	//재수집 일시 지정할 경우
	    	if(request.getParameter("PROCESS_GUBUN") != null && "APPOINT".equals(request.getParameter("PROCESS_GUBUN"))){
	    		param.put("COLCT_RESVE_DT", request.getParameter("COLCT_RESVE_DT") + " " + request.getParameter("COLCT_RESVE_H") + ":" + request.getParameter("COLCT_RESVE_M"));
	    	}else{
	    		param.put("COLCT_RESVE_DT", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    	}
	    	
	    	//표준연계API 수집요청 등록
	    	//기간별
	    	if(request.getParameter("COLCT_SE_CODE") != null && "CLT101".equals(request.getParameter("COLCT_SE_CODE"))){
	    		param.put("STD_CNTC_SETUP_ID", stdCntcApiColctIdGnrService.getNextIntegerId());
	    		param.put("BEGIN_DE", request.getParameter("BEGIN_DE") + "00:00:00");
	        	param.put("END_DE", request.getParameter("END_DE") + "23:59:59");
	        	RlmManageService.insertStdCntcApiColct(param);
	    	}
	    	//대수별
	    	else if(request.getParameter("COLCT_SE_CODE") != null && "CLT102".equals(request.getParameter("COLCT_SE_CODE"))){
	    		String[] numprList = request.getParameter("NUMPR").toString().split(",");  
	    		for(String numpr : numprList){
	    			param.put("STD_CNTC_SETUP_ID", stdCntcApiColctIdGnrService.getNextIntegerId());
	    			param.put("NUMPR", numpr);
	    			RlmManageService.insertStdCntcApiColct(param);
	    			
	    			//전체 선택일 경우 나머지 무시
	    			if("0".equals(numpr)) break;
	    		}
	    	}
	    	//메타데이터관리, 연계파일동기화 재수집
	        //지정
	    	else if(request.getParameter("COLCT_SE_CODE") != null && "CLT103".equals(request.getParameter("COLCT_SE_CODE"))){
	    		String[] pkList = request.getParameter("PK").toString().split(",");  
	    		for(String pk : pkList){
	    			param.put("STD_CNTC_SETUP_ID", stdCntcApiColctIdGnrService.getNextIntegerId());
	    			param.put("RASMBLY_ID", pk.split("@")[1]);
	    			
	    			String[] pkTemp = pk.split("@")[0].replace("^^","#").split("#");
	    			
	    			//회의록의 경우 CN 값을 따로 처리
	    			if(request.getParameter("DTA_SE_CODE") != null && "TAR101".equals(request.getParameter("DTA_SE_CODE"))){
	    				param.put("PK", pkTemp[0]+"#"+pkTemp[1]+"#"+pkTemp[2]+"#"+pkTemp[3]+"#"+pkTemp[4]+"#"+pkTemp[5]+"#"+pkTemp[6]);
	    				param.put("CN", pkTemp[7]); 
	    			}else{
	    				param.put("PK", pkTemp[0]);
	    				param.put("CN", pkTemp[1]);
	    			}
	    			
	    			RlmManageService.insertStdCntcApiColct(param);
	    		}
	    	}
	    	
	    	// 지방의회 연계서버에 재수집 요청할 수 있도록 등록해준다.
	    	String RASMLBY_ID = CommonStringUtil.nullConvert(param.get("RASMBLY_ID"));
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		param.clear();
    		param.put("REQST_NO", councilSystemContrlIdGnrService.getNextIntegerId());
    		param.put("RASMBLY_ID", RASMLBY_ID);
    		param.put("CMMND_CODE", "REQ007");
    		param.put("CMMND_TRNSMIS_AT", "N");
    		param.put("CMMND_EXC_AT", "N");
    		param.put("CMMND_EXC_CNFIRM_AT", "N");
    		param.put("FRST_REGISTER_ID", user.getMngrId());
    		RlmManageService.insertCouncilSystemControl(param);
    		
    		if("Y".equals(CommonStringUtil.nullConvert(request.getParameter("isMeta"))))
        	{
        		model.addAttribute("msg", "정상처리되었습니다");
        	}
        	else
        	{
        		model.addAttribute("message", "정상처리되었습니다");
    			model.addAttribute("return_url", return_url);
        	}
			
    	}catch(Exception e){
    		
    		if("Y".equals(CommonStringUtil.nullConvert(request.getParameter("isMeta"))))
        	{
    			model.addAttribute("msg", e.getMessage());
        	}
        	else
        	{
        		model.addAttribute("message", e.getMessage());
        		model.addAttribute("return_url", "/rlm/StdCntcApiColctMngList.do");
        	}
    	}
    	
    	if("Y".equals(CommonStringUtil.nullConvert(request.getParameter("isMeta"))))
    	{
    		model.addAttribute("msg", "정상처리되었습니다");
    		return "clikMng/mdm/MdmIsView";
    	}
    	else
    	{
    		return "clikMng/cmm/Result";
    	}
    }
    
    /**
     * 수집관리 - 표준연계API 수집요청 상세정보를 조회한다.
     * @param searchVO
     * @param model
     * @return	수집요청 상세정보
     * @throws Exception
     */
    @IncludedInfo(name="표준연계API 수집관리 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/StdCntcApiColctDetail.do")
    public String selectStdCntcApiColctDetail(@ModelAttribute("searchVO") RlmManageVO searchVO, ModelMap model) throws Exception {
    	//개발 중 수정 하지 않기로 해서 주석 처리
//    	HashMap<String,Object> param = new HashMap<String,Object>();
//    	
//    	//상세정보 조회
//    	HashMap<String,Object> _map = (HashMap<String,Object>)RlmManageService.selectStdCntcApiColctMngDetail(param);
//    	
//    	//정보 설정
//    	if(_map != null && _map.size() > 0){
//    		searchVO.setLOASM_CODE(_map.get("RASMBLY_ID").toString());
//    	}
//    	
//    	CmmnDetailCode vo = new CmmnDetailCode();
//    	
//    	//검색 정보 설정을 위하여 의회 정보가 존재할 경우 조회
//    	if(searchVO.getLOASM_CODE() != null && !"".equals(searchVO.getLOASM_CODE())){
//    		param.put("RASMBLY_ID", searchVO.getLOASM_CODE());
//    		HashMap<String,Object> obj = RlmManageService.selectRasmblyInfo(param);
//    		
//    		searchVO.setINSTT_CL_CODE(obj.get("UPPER_INSTT_CL_CODE").toString());
//    		searchVO.setINSTT_TY_CODE(obj.get("INSTT_CL_CODE").toString());
//    		searchVO.setLOASM_TY_CODE(obj.get("LOASM_TY_CODE").toString());
//    		searchVO.setBRTC_CODE(obj.get("BRTC_CODE").toString());
//    	}
//    	
//    	//지역목록
//    	if(searchVO.getINSTT_TY_CODE() != null && !"".equals(searchVO.getINSTT_TY_CODE())){
//    		vo.setBrtcCode("LMC");
//    		List<CmmnDetailCode> brtc_code_list = cmmUseService.selectBrtcCodeDetails(vo);
//    		model.addAttribute("brtc_code_list", brtc_code_list);
//    	}
//
//    	//의회목록
//     	if(searchVO.getBRTC_CODE() != null && !"".equals(searchVO.getBRTC_CODE())){
//    		vo.setInsttClCode(searchVO.getINSTT_TY_CODE());
//    		vo.setBrtcCode(searchVO.getBRTC_CODE());
//         	List<CmmnDetailCode> loasm_code_list = cmmUseService.selectLoasmInfo(vo);
//         	model.addAttribute("loasm_code_list", loasm_code_list);
//    	}
//     	
//     	model.addAttribute("result", _map);
//		model.addAttribute("searchVO", searchVO);
		
    	return "clikMng/rlm/StdCntcApiColctMngDetail";
    }
    
    /**
     * 의회 기간별 대수 정보 표
     * @param 
     * @return html 
     * @exception 
     * */
    @RequestMapping(value="/rlm/councilInfo.do")
    public String councilInfo(HttpServletRequest request, ModelMap model) throws Exception {
    	return "clikMng/rlm/council_info";
    }
    
    /**
     * 수집관리 - 표준연계API 수집요청 정보를 삭제한다.
     * @param searchVO
     * @param model
     * @return	처리결과
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="표준연계API 수집요청 정보 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/DeleteStdCntcApiColct.do")
    public void DeleteStdCntcApiColct(@ModelAttribute("searchVO") RlmManageVO searchVO, 
    									HttpServletRequest request,
    									HttpServletResponse response,
    									ModelMap model) throws Exception {
    	
    	HashMap<String,Object> param = new HashMap<String,Object>();
    	String[] pkList = request.getParameter("deleteKeyList").toString().split(","); 
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
		try{
	    	for(String key : pkList){
		    	param.put("STD_CNTC_SETUP_ID", key);
		    	RlmManageService.DeleteStdCntcApiColct(param);
	    	}
	    	jso.put("resultMsg", "정상처리되었습니다.");
    	}catch(Exception e){
    		jso.put("resultMsg", e.getMessage());
    	}
    	
    	jarray.add(jso);
    	
    	response.setHeader("Content-Type", "text/html;charset=utf-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
    	out.print(jarray.toString());
    	out.flush();
    }
    
    /**
     * 수집관리 - 표준연계API 모니터링 화면을 조회한다.
     * @param searchVO
     * @param model
     * @return	지방의회 연계 모니터링 화면
     * @throws Exception
     */
    @IncludedInfo(name="표준연계API 수집관리 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/StdCntcApiColctMntrng.do")
    public String StdCntcApiColctMntrng(@ModelAttribute("searchVO") RlmManageVO searchVO, ModelMap model) throws Exception {
    	
    	List<HashMap<String,Object>> result = RlmManageService.selectStdCntcApiColctMntrng();
    	model.addAttribute("resultList", result);
    	
    	String directoryPath = "";
    	double diskSize;
		double diskSizeGB;
		double diskFreeSize;
		double diskFreeSizeGB;
		//double diskUsableSize;
		double diskUsableSizeGB;
		
		//clikapi-file/clik001
		directoryPath = "/clikapi-file/clik001";
		
		diskSize = new File(directoryPath).getTotalSpace();
		diskSizeGB = diskSize / 1024 / 1024 / 1024;
		model.addAttribute("clikapiFileDiskSizeGB", String.format("%,d",(int)diskSizeGB));
		
		diskFreeSize = new File(directoryPath).getFreeSpace();
		diskFreeSizeGB = diskFreeSize / 1024 / 1024 / 1024;
		model.addAttribute("clikapiFileDiskFreeSizeGB", String.format("%,d",(int)diskFreeSizeGB));

		//diskUsableSize = new File(directoryPath).getUsableSpace();
		diskUsableSizeGB = diskSizeGB - diskFreeSizeGB;
		model.addAttribute("clikapiFileDiskUsableSizeGB", String.format("%,d",(int)diskUsableSizeGB));
		
		model.addAttribute("clikapiFileDiskUsableRate", (int)(diskUsableSizeGB * 100d / diskSizeGB));
		
		
		//clik-data
		directoryPath = "/clik-data";
		
		diskSize = new File(directoryPath).getTotalSpace();
		diskSizeGB = diskSize / 1024 / 1024 / 1024;
		model.addAttribute("clikDataDiskSizeGB", String.format("%,d",(int)diskSizeGB));
		
		diskFreeSize = new File(directoryPath).getFreeSpace();
		diskFreeSizeGB = diskFreeSize / 1024 / 1024 / 1024;
		model.addAttribute("clikDataDiskFreeSizeGB", String.format("%,d",(int)diskFreeSizeGB));
		
		//diskUsableSize = new File(directoryPath).getUsableSpace();
		diskUsableSizeGB = diskSizeGB - diskFreeSizeGB;
		model.addAttribute("clikDataDiskUsableSizeGB", String.format("%,d",(int)diskUsableSizeGB));
		
		model.addAttribute("clikDataDiskUsableRate", (int)(diskUsableSizeGB * 100d / diskSizeGB));
		
		
		//clik-cols
		directoryPath = "/clik-cols";
		
		diskSize = new File(directoryPath).getTotalSpace();
		diskSizeGB = diskSize / 1024 / 1024 / 1024;
		model.addAttribute("clikColsDiskSizeGB", String.format("%,d",(int)diskSizeGB));
		
		diskFreeSize = new File(directoryPath).getFreeSpace();
		diskFreeSizeGB = diskFreeSize / 1024 / 1024 / 1024;
		model.addAttribute("clikColsDiskFreeSizeGB", String.format("%,d",(int)diskFreeSizeGB));
		
		//diskUsableSize = new File(directoryPath).getUsableSpace();
		diskUsableSizeGB = diskSizeGB - diskFreeSizeGB;
		model.addAttribute("clikColsDiskUsableSizeGB", String.format("%,d",(int)diskUsableSizeGB));
		
		model.addAttribute("clikColsDiskUsableRate", (int)(diskUsableSizeGB * 100d / diskSizeGB));
		
		
    	return "clikMng/rlm/StdCntcApiColctMntrng";
    }
    
    /**
     * 지방의회 정당코드 관리 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	정당코드 목록
     * @throws Exception
     */
    @IncludedInfo(name="정당코드관리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/selectPprtyCodeList.do")
    public String selectPprtyCodeList(@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	/** EgovPropertyService.LogList */
		if(searchVO.getPageUnit() == 0){
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		}
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<HashMap<String,Object>> list = RlmManageService.selectPprtyCodeList(searchVO);
        
		if(list.size() > 0)
			paginationInfo.setTotalRecordCount(Integer.parseInt(list.get(0).get("TOTCNT").toString()));
		else
			paginationInfo.setTotalRecordCount(0);
			
    	model.put("resultList", list);
    	model.put("searchVO", searchVO);
    	model.put("paginationInfo", paginationInfo);
    	
        return "clikMng/rlm/PprtyCodeMngList";
    }
    
    /**
     * 지방의회 정당코드 관리 상세정보를 조회한다.
     * @param searchVO
     * @param model
     * @return	정당코드 상세정보
     * @throws Exception
     */
    @IncludedInfo(name="정당코드관리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/selectPprtyCodeDetail.do")
    public String selectPprtyCodeDetail(
    		@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, 
    		HttpServletRequest request, 
    		ModelMap model) throws Exception {
		
    	HashMap<String,Object> map = new HashMap<String, Object>();
    	map.put("PPRTY_CODE", request.getParameter("pprty_code"));
    	
		HashMap<String,Object> obj = null;
		
		if(request.getParameter("pprty_code") != null)
			obj = RlmManageService.selectPprtyCodeDetail(map);
        
    	model.put("obj", obj);
    	model.put("searchVO", searchVO);
    	
        return "clikMng/rlm/PprtyCodeMngDetail";
    }
    
    /**
     * 지방의회 정당코드 정보를 삭제한다.
     * @param searchVO
     * @param model
     * @return 처리결과
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="정당코드관리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/deletePprtyCode.do")
    public void deletePprtyCode(
    		@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, 
    		HttpServletRequest request,
    		HttpServletResponse response,
    		ModelMap model) throws Exception {
		
    	HashMap<String,Object> param = new HashMap<String,Object>();
    	String[] pkList = request.getParameter("deleteKeyList").toString().split(","); 
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
		try{
	    	for(String key : pkList){
		    	param.put("PPRTY_CODE", key);
		    	RlmManageService.DeletePprtyCode(param);
	    	}
	    	jso.put("resultMsg", "정상처리되었습니다.");
    	}catch(Exception e){
    		jso.put("resultMsg", e.getMessage());
    	}
    	
    	jarray.add(jso);
    	
    	response.setHeader("Content-Type", "text/html;charset=utf-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
    	out.print(jarray.toString());
    	out.flush();
    }
    
    /**
     * 지방의회 정당코드 관리 코등정보를 처리한다.
     * @param searchVO
     * @param model
     * @return 처리결과
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="정당코드관리", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/pprtyCodeProc.do")
    public void pprtyCodeProc(
    		@ModelAttribute("searchVO") RlmManageDefaultVO searchVO, 
    		HttpServletRequest request,
    		HttpServletResponse response,
    		ModelMap model) throws Exception {
		
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	HashMap<String,Object> map = new HashMap<String, Object>();
    	
    	map.put("PPRTY_CODE", URLDecoder.decode(CommonStringUtil.nullConvert(request.getParameter("PPRTY_CODE")), "UTF-8"));
    	map.put("PPRTY_NM", URLDecoder.decode(CommonStringUtil.nullConvert(request.getParameter("PPRTY_NM")), "UTF-8"));
    	map.put("PPRTY_ABRV", URLDecoder.decode(CommonStringUtil.nullConvert(request.getParameter("PPRTY_ABRV")), "UTF-8"));
    	map.put("RM", URLDecoder.decode(CommonStringUtil.nullConvert(request.getParameter("RM")), "UTF-8"));
    	map.put("DELETE_AT", CommonStringUtil.nullConvert(request.getParameter("DELETE_AT")));
    	map.put("PPRTY_SN", URLDecoder.decode(CommonStringUtil.nullConvert(request.getParameter("PPRTY_SN")), "UTF-8"));
    	map.put("BEGIN_DE", CommonStringUtil.nullConvert(request.getParameter("BEGIN_DE")).replace("-", "").replace(".", ""));
    	map.put("END_DE", CommonStringUtil.nullConvert(request.getParameter("END_DE")).replace("-", "").replace(".", ""));
    	
    	map.put("PPRTY_CODE",CommonStringUtil.getHtmlStrCnvr(map.get("PPRTY_CODE").toString()));
    	map.put("PPRTY_NM",CommonStringUtil.getHtmlStrCnvr(map.get("PPRTY_NM").toString()));
    	map.put("PPRTY_ABRV",CommonStringUtil.getHtmlStrCnvr(map.get("PPRTY_ABRV").toString()));
    	map.put("RM",CommonStringUtil.getHtmlStrCnvr(map.get("RM").toString()));
    	
    	try{

     		HashMap<String,Object> obj = RlmManageService.selectPprtyCodeDetail(map);
	    	
    		if("C".equals(request.getParameter("CUD_CODE")))
	    	{
	    		map.put("PPRTY_SN", "99");
	    		map.put("DELETE_AT", "N");
	    		
	    		if(obj == null || obj.size() == 0)
	    		{
	    			RlmManageService.InsertPprtyCode(map);
	    			jso.put("resultCode", "success");
	    			jso.put("resultMsg", "정상처리되었습니다.");
	    		}
	    		else
	    		{
	    			jso.put("resultCode", "fail");
	    			jso.put("resultMsg", "중복되는 정당코드가 존재합니다.");
	    		}
	    	}
	    	else if("U".equals(request.getParameter("CUD_CODE")))
	    	{
	    		if(obj != null && obj.size() > 0)
	    		{
	    			if("Y".equals(map.get("DELETE_AT")) && "N".equals(obj.get("DELETE_AT"))){
		    			map.put("DELETE_DT", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
	    			}else if("Y".equals(map.get("DELETE_AT")) && "Y".equals(obj.get("DELETE_AT"))){
	    				map.put("DELETE_DT", obj.get("DELETE_DT"));
	    			}
	    		}
	    		
 	    		RlmManageService.UpdatePprtyCode(map);
	    		jso.put("resultCode", "success");
	    		jso.put("resultMsg", "정상처리되었습니다.");
	    	}
	    	
    	}catch(Exception e){
    		jso.put("resultCode", "fail");
    		jso.put("resultMsg", e.getMessage());
    	}
    	
    	jarray.add(jso);
    	
    	response.setHeader("Content-Type", "text/html;charset=utf-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
    	out.print(jarray.toString());
    	out.flush();
    }
    
    /**
     * 수집관리 - 표준연계API 모니터링 : 지방의회 시스템 설정을 처리한다.
     * @param searchVO
     * @param model
     * @return	처리결과
     * @throws Exception
     */
	@IncludedInfo(name="표준연계API 모니터링 : agent server startup", order = 680 ,gid = 50)
    @RequestMapping(value="/rlm/insertCouncilSystemControl.do")
    public String insertCouncilSystemControl(MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {
    	
    	try{
    		
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		
    		String cmmndCode = CommonStringUtil.nullConvert(multiRequest.getParameter("CMMND_CODE"));
    		String rasmblyId = CommonStringUtil.nullConvert(multiRequest.getParameter("RASMBLY_ID"));

            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("REQST_NO", councilSystemContrlIdGnrService.getNextIntegerId());
            param.put("RASMBLY_ID", rasmblyId);
    		param.put("CMMND_CODE", cmmndCode);
    		param.put("CMMND_TRNSMIS_AT", "N");
    		param.put("CMMND_EXC_AT", "N");
    		param.put("CMMND_EXC_CNFIRM_AT", "N");
    		param.put("FRST_REGISTER_ID", user.getMngrId());
    		
    		//startup
    		if("REQ001".equals(cmmndCode)){
    			if("002001".equals(rasmblyId)){			//서울특별시의회
    				param.put("EXE_CMMND", "");
    			}else if("051001".equals(rasmblyId)){	//부산광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("053001".equals(rasmblyId)){	//대구광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("032001".equals(rasmblyId)){	//인천광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("062001".equals(rasmblyId)){	//광주광역시의회
    				param.put("EXE_CMMND", "service tomcat_AGT start");
    			}else if("042001".equals(rasmblyId)){	//대전광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("052001".equals(rasmblyId)){	//울산광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("044001".equals(rasmblyId)){	//세종특별자치시의회
    				param.put("EXE_CMMND", "");
    			}else if("031001".equals(rasmblyId)){	//경기도의회
    				param.put("EXE_CMMND", "");
    			}else if("033001".equals(rasmblyId)){	//강원도의회
    				param.put("EXE_CMMND", "net start agent");
    			}else if("043001".equals(rasmblyId)){	//충청북도의회
    				param.put("EXE_CMMND", "");
    			}else if("041001".equals(rasmblyId)){	//충청남도의회
    				param.put("EXE_CMMND", "");
    			}else if("063001".equals(rasmblyId)){	//전라북도의회
    				param.put("EXE_CMMND", "");
    			}else if("061001".equals(rasmblyId)){	//전라남도의회
    				param.put("EXE_CMMND", "service tomcat_AGT start");
    			}else if("054001".equals(rasmblyId)){	//경상북도의회
    				param.put("EXE_CMMND", "");
    			}else if("055001".equals(rasmblyId)){	//경상남도의회
    				param.put("EXE_CMMND", "");
    			}else if("064001".equals(rasmblyId)){	//제주특별자치도의회
    				param.put("EXE_CMMND", "");
    			}else if("031012".equals(rasmblyId)){	//경기도 부천시의회
    				param.put("EXE_CMMND", "");
    			}else if("031031".equals(rasmblyId)){	//경기도 하남시의회
    				param.put("EXE_CMMND", "net start agent");
    			}else if("033002".equals(rasmblyId)){	//강원도 강릉시의회
    				param.put("EXE_CMMND", "");
    			}else if("041009".equals(rasmblyId)){	//충청남도 서산시의회
    				param.put("EXE_CMMND", "");
    			}else if("041900".equals(rasmblyId)){	//충청남도 서산군의회
    				param.put("EXE_CMMND", "");
    			}else if("043012".equals(rasmblyId)){	//충청북도 청주시의회
    				param.put("EXE_CMMND", "");
    			}else if("054010".equals(rasmblyId)){	//경상북도 상주시의회
    				param.put("EXE_CMMND", "service tomcat_AGT start");
    			}else if("055002".equals(rasmblyId)){	//경상남도 거제시의회
    				param.put("EXE_CMMND", "");
    			}else if("055005".equals(rasmblyId)){	//경상남도 김해시의회
    				param.put("EXE_CMMND", "service tomcat_AGT start");
    			}else if("061012".equals(rasmblyId)){	//전라남도 순천시의회
    				param.put("EXE_CMMND", "");
    			}else if("063014".equals(rasmblyId)){	//전라북도 정읍시의회
    				param.put("EXE_CMMND", "");
    			}
    			
    			//TODO 제윤측에서 정보 받은 후 제거 해야함
    			param.put("EXE_CMMND", multiRequest.getParameter("EXE_CMMND"));
    		}
    		
    		//shutdown
    		if("REQ002".equals(cmmndCode)){
    			if("002001".equals(rasmblyId)){			//서울특별시의회
    				param.put("EXE_CMMND", "");
    			}else if("051001".equals(rasmblyId)){	//부산광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("053001".equals(rasmblyId)){	//대구광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("032001".equals(rasmblyId)){	//인천광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("062001".equals(rasmblyId)){	//광주광역시의회
    				param.put("EXE_CMMND", "service tomcat_AGT stop");
    			}else if("042001".equals(rasmblyId)){	//대전광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("052001".equals(rasmblyId)){	//울산광역시의회
    				param.put("EXE_CMMND", "");
    			}else if("044001".equals(rasmblyId)){	//세종특별자치시의회
    				param.put("EXE_CMMND", "");
    			}else if("031001".equals(rasmblyId)){	//경기도의회
    				param.put("EXE_CMMND", "");
    			}else if("033001".equals(rasmblyId)){	//강원도의회
    				param.put("EXE_CMMND", "net stop agent");
    			}else if("043001".equals(rasmblyId)){	//충청북도의회
    				param.put("EXE_CMMND", "");
    			}else if("041001".equals(rasmblyId)){	//충청남도의회
    				param.put("EXE_CMMND", "");
    			}else if("063001".equals(rasmblyId)){	//전라북도의회
    				param.put("EXE_CMMND", "");
    			}else if("061001".equals(rasmblyId)){	//전라남도의회
    				param.put("EXE_CMMND", "service tomcat_AGT stop");
    			}else if("054001".equals(rasmblyId)){	//경상북도의회
    				param.put("EXE_CMMND", "");
    			}else if("055001".equals(rasmblyId)){	//경상남도의회
    				param.put("EXE_CMMND", "");
    			}else if("064001".equals(rasmblyId)){	//제주특별자치도의회
    				param.put("EXE_CMMND", "");
    			}else if("031012".equals(rasmblyId)){	//경기도 부천시의회
    				param.put("EXE_CMMND", "");
    			}else if("031031".equals(rasmblyId)){	//경기도 하남시의회
    				param.put("EXE_CMMND", "net stop agent");
    			}else if("033002".equals(rasmblyId)){	//강원도 강릉시의회
    				param.put("EXE_CMMND", "");
    			}else if("041009".equals(rasmblyId)){	//충청남도 서산시의회
    				param.put("EXE_CMMND", "");
    			}else if("041900".equals(rasmblyId)){	//충청남도 서산군의회
    				param.put("EXE_CMMND", "");
    			}else if("043012".equals(rasmblyId)){	//충청북도 청주시의회
    				param.put("EXE_CMMND", "");
    			}else if("054010".equals(rasmblyId)){	//경상북도 상주시의회
    				param.put("EXE_CMMND", "service tomcat_AGT stop");
    			}else if("055002".equals(rasmblyId)){	//경상남도 거제시의회
    				param.put("EXE_CMMND", "");
    			}else if("055005".equals(rasmblyId)){	//경상남도 김해시의회
    				param.put("EXE_CMMND", "service tomcat_AGT stop");
    			}else if("061012".equals(rasmblyId)){	//전라남도 순천시의회
    				param.put("EXE_CMMND", "");
    			}else if("063014".equals(rasmblyId)){	//전라북도 정읍시의회
    				param.put("EXE_CMMND", "");
    			}
    			
    			//TODO 제윤측에서 정보 받은 후 제거 해야함
    			param.put("EXE_CMMND", multiRequest.getParameter("EXE_CMMND"));
    		}
    		
    		//update
    		else if("REQ003".equals(cmmndCode)){
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    			final Map<String, MultipartFile> files = multiRequest.getFileMap();
    			if (!files.isEmpty()) {
    				Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
    				while(itr.hasNext()){
    					Entry<String, MultipartFile> entry = itr.next();
    					MultipartFile mFile = entry.getValue();
    					String filePath = MdmProperties.getProperty("Globals.council.updatefile.path") + multiRequest.getParameter("RASMBLY_ID") + File.separator + sdf.format(new Date()) + File.separator;
    					File tempFile = new File(filePath);
    					if(!tempFile.exists()){
    						tempFile.mkdirs();
    					}
    					filePath += mFile.getOriginalFilename();
    					tempFile = new File(filePath);
    					mFile.transferTo(tempFile);
    					
    					if(!mFile.isEmpty())
    					{
    						param.put("TRGET_FILE_PATH", multiRequest.getParameter("TRGET_FILE_PATH"));
    						param.put("TRGET_FILE_STRE_PATH", filePath);
    						param.put("TRGET_FILE_SIZE", multiRequest.getParameter("TRGET_FILE_SIZE"));	
    					}
    				}//end while
    			}//end if
    		}
    		
    		//log list
    		else if("REQ004".equals(cmmndCode)){
    			
    		}
    		
    		//log view
    		else if("REQ005".equals(cmmndCode)){
    			param.put("LOG_FILE_NAME", multiRequest.getParameter("LOG_FILE_NAME"));
    			
    			if(!"".equals(multiRequest.getParameter("LOG_FILE_LINE_CO"))){
    				param.put("LOG_FILE_LINE_CO", multiRequest.getParameter("LOG_FILE_LINE_CO"));
    			}else{
    				param.put("LOG_FILE_LINE_CO", "0");
    			}
    		}
    		
    		//request interval
    		else if("REQ006".equals(cmmndCode)){
    			param.put("SET_INTERVAL", multiRequest.getParameter("SET_INTERVAL"));
    		}
    		
    		//표준연계 데이터 재수집 요청
    		else if("REQ007".equals(cmmndCode)){
    			
    		}
    		
    		//etc call
    		else if("REQ901".equals(cmmndCode)){
    			param.put("CALL_URL", multiRequest.getParameter("CALL_URL"));
    		}
    		
	    	RlmManageService.insertCouncilSystemControl(param);
	    	
	    	model.put("return_url", "/rlm/StdCntcApiColctMntrng.do");
	    	model.put("message", "정상처리되었습니다.");
	    	
    	}catch(Exception e){
    		model.put("return_url", "");
    		model.put("message", e.getMessage());
    	}
    	
    	return "clikMng/cmm/Result";
    }
	
	/**
     * 수집관리 - 표준연계API 모니터링 : 지방의회 시스템 log 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	처리결과
     * @throws Exception
     */
    @RequestMapping(value="/rlm/selectCouncilSystemControllList.do")
    public String selectCouncilSystemControllList(HttpServletRequest request, ModelMap model) throws Exception {
    	String RASMBLY_ID = CommonStringUtil.nullConvert(request.getParameter("RASMBLY_ID"));
    	List<HashMap<String,Object>> systemControllList = RlmManageService.selectCouncilSystemControllList(RASMBLY_ID);
    	
    	model.put("systemControllList", systemControllList);
		return "clikMng/rlm/councilSystemControllList";
	}
    
    /**
     * 수집관리 - 표준연계API 모니터링 : 지방의회 시스템 log 상세를 조회한다.
     * @param searchVO
     * @param model
     * @return	처리결과
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/rlm/selectCouncilSystemLogDetailView.do")
    public String selectCouncilSystemLogDetailView(HttpServletRequest request, ModelMap model) throws Exception {
    	String REQST_NO = CommonStringUtil.nullConvert(request.getParameter("reustNo"));
    	String log = RlmManageService.selectCouncilSystemLogDetailView(REQST_NO);
    	
    	int cnt = RlmManageService.updateCouncilSystemLogDetailView(REQST_NO);
    	
    	System.out.println("updateCouncilSystemLogDetailView count : " + cnt);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	jso.put("logText", log);
    	jarray.add(jso);
    	model.put("msg", jarray.toString());
		return "clikMng/mdm/MdmIsView";
	}
}
