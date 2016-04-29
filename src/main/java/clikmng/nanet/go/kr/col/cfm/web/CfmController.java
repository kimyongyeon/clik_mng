package clikmng.nanet.go.kr.col.cfm.web;

import java.io.File;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.col.cfm.service.CfmCompareListVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmCompareResultVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmFileVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmSearchVO;
import clikmng.nanet.go.kr.col.cfm.service.CfmService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 표준연계API 파일동기화를 처리하는 Controller 클래스
 * */

@Controller
public class CfmController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "CfmService")
    private CfmService cfmService;

    /** Message ID Generation */
    @Resource(name="cfmIdGnrService")
    private EgovIdGnrService cfmIdGnrService;
    
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
     * 표준연계API 파일동기화 목록
     * @param searchVO
     * @param model
     * @return	"/col/cfm/cfmList"
     * @throws Exception
     */
    @IncludedInfo(name="표준연계API 파일동기화 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/col/cfm/CfmList.do")
    public String compareCollectionFile(@ModelAttribute("searchVO") CfmSearchVO searchVO
    																	, HttpServletRequest request
    																	, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	List<CfmCompareResultVO> compareResult = null;
    	
    	// 조회 조건
    	if(searchVO.isCompareYn()){
        	// 수집년도, 지방의회ID, 자료유형
    		if ( searchVO.getColct_year() != null && searchVO.getRasmbly_id() != null && searchVO.getApi_code() != null ){
    			
    			//TEMPTABLE
    			//-----------------
    			//수집파일비교실행ID
    			//파일경로
    			//관리자아이디
    			//-----------------
    			
    			//--------------------------수집파일비교실행시작----------------------------------
    			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
    			String mngrID = loginVO.getMngrId();
    			
    			// 기존입력TEMP DATA 삭제
    			cfmService.deleteTempCollectionFileList(mngrID);    			
    			cfmService.deleteCollectionFileList(mngrID);
    			
    			// 수집파일경로
    			String apiFileRootPath = EgovProperties.getProperty(searchVO.getApi_code());
    			String apiFileSrcDir = apiFileRootPath + searchVO.getColct_year() + "/" + searchVO.getRasmbly_id();
				
    			// 수집파일비교실행ID
    			String compare_id = cfmIdGnrService.getNextStringId();
    			
    			// 수집자료유형
    			String api_code = searchVO.getApi_code();
    			
    			// 수집년도, 지방의회ID, 자료유형에 해당하는 디렉토리 리스트를 TEMP테이블에 저장
				subDirList(apiFileSrcDir, api_code, compare_id, mngrID);

				//로그처리를 위해 ID셋팅
				searchVO.setCompare_id(compare_id);
				searchVO.setMngrID(mngrID);
				
				// 이력테이블 정보 저장
				cfmService.insertCompareLog(searchVO);
            	
            	// TEMP테이블과 해당하는 자료유형 테이블과 FULL조인하여 비교 결과 저장
    			cfmService.compareCollectionFile(searchVO);

    			//세션에 compare_id 저장
    			request.getSession().setAttribute("cfm.compare_id", compare_id);
    			
    		}
    	}

    	model.addAttribute("compareResultList", null);

		//비교 결과 조회
		if (request.getSession().getAttribute("cfm.compare_id") != null
				&& !((String) request.getSession().getAttribute("cfm.compare_id")).equals("")) {
    		
    		compareResult = cfmService.compareResult((String)request.getSession().getAttribute("cfm.compare_id"));
    		CfmCompareResultVO cfmCompareResultVO = null;
    		if(compareResult.size() > 0) {
    			cfmCompareResultVO = compareResult.get(0);
    			model.addAttribute("cfmCompareResultVO", cfmCompareResultVO);
    		}else{
    	     	
    	     	cfmCompareResultVO = new CfmCompareResultVO();
    			
    	     	cfmCompareResultVO.setInsttClCode(request.getParameter("selRasmbly"));
    			cfmCompareResultVO.setBrtcCode(request.getParameter("selRegion"));
    			cfmCompareResultVO.setLoasmCode(request.getParameter("rasmbly_id"));
    			cfmCompareResultVO.setApi_code(request.getParameter("api_code"));
    			
    			cfmCompareResultVO.setColct_year(request.getParameter("colct_year"));
    			cfmCompareResultVO.setApi_nm(request.getParameter("api_nm"));
    			cfmCompareResultVO.setRasmbly_nm(request.getParameter("rasmbly_nm"));
    			cfmCompareResultVO.setTotCnt(0);
    			cfmCompareResultVO.setNrmltCnt(0);
    			cfmCompareResultVO.setRetryColCnt(0);
    			cfmCompareResultVO.setDelCnt(0);
    			
    			model.addAttribute("cfmCompareResultVO", cfmCompareResultVO);
    		}
    	}
    	
    	return "clikMng/col/cfm/CfmList";    	
    }
    
    public void subDirList(String source, String api_code, String compare_id, String mngrID) throws Exception {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {

					CfmFileVO cfmFileVO = new CfmFileVO();
					cfmFileVO.setCompare_id(compare_id);
					cfmFileVO.setMngrID(mngrID);
					String filePath = file.getPath();
					
					//TODO 서버 적용 시 제거 해야함
					//filePath = filePath.replaceAll("D:", "").replaceAll("\\\\", "/");
					
					cfmFileVO.setPath(filePath);
					
	            	//FilePath 입력
	    			cfmService.insertTempCollectionFileList(cfmFileVO);
					System.out.println("file path : " + file.getPath());
					
				} else if (file.isDirectory()) {
					
					//만약 RESxxx 폴더가 포함되어 있을 경우 지정된 수집 자료 유형 일 경우에만 하위폴더 탐색
					if(file.getPath().indexOf("RES") > 0){
						
						int s = file.getPath().indexOf("RES");
						String tmpApi_code = file.getPath().substring(s, s + 6);
						
						if(api_code.equals(tmpApi_code)){
							subDirList(file.getCanonicalPath().toString(), api_code, compare_id, mngrID);
						}
					}else{
						subDirList(file.getCanonicalPath().toString(), api_code, compare_id, mngrID);
					}
					
					
				}
			}
		} catch (Exception e) {

		}
	}
    
    /**
     * 표준연계API 파일동기화 상세 리스트
     * @param searchVO
     * @param model
     * @return	"/col/cfm/CfmPopupCompareList"
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name = "표준연계API 파일동기화 상세 리스트", order = 680, gid = 50)
	@RequestMapping(value = "/col/cfm/CfmPopupCompareList.do")
	public void selectPopupCompareList(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model)
			throws Exception {

    	JSONArray jarray = new JSONArray();
    	
		String compare_id = (String)request.getSession().getAttribute("cfm.compare_id");
		if(compare_id != null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("procNo", request.getParameter("procNo"));
			map.put("compare_id", compare_id);
	
			List<CfmCompareListVO> compareList = cfmService.selectCompareList(map);
			
	    	JSONObject jso = new JSONObject();    // JASON 객체생성
	    	
	    	for(int i=0; i<compareList.size(); i++) {
	    		CfmCompareListVO ccl = new CfmCompareListVO(); 
	    		ccl = (CfmCompareListVO) compareList.get(i);
	    		jso = new JSONObject();
	    		
				jso.put("api_key", ccl.getApi_key());			// apikey
	    		jso.put("file_url", ccl.getFile_url());			// fileurl
	    		jso.put("file_path", ccl.getFile_path());		// filepath
	    		
	    		jarray.add(i, jso);
	    	}
		} else {
	    	JSONObject jso = new JSONObject();    // JASON 객체생성
			jso.put("api_key", "");			// apikey
    		jso.put("file_url", "");			// fileurl
    		jso.put("file_path", "");		// filepath
    		
    		jarray.add(0, jso);
		}
		
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();  
		out.print(jarray.toString());
		
	}
	
    /**
     * 표준연계API 파일동기화 자료가 없는데 파일이 존재하는 경우 파일 삭제
     * @param searchVO
     * @param model
     * @return	"/col/cfm/CfmPopupCompareListDelete"
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name = "표준연계API 파일동기화 상세 리스트", order = 680, gid = 50)
	@RequestMapping(value = "/col/cfm/CfmDeleteCompareListFile.do")
	public void deleteCompareListFile(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model)
			throws Exception {
		
    	JSONArray jarray = new JSONArray();
    	
		String compare_id = (String)request.getSession().getAttribute("cfm.compare_id");
		String[] checkedFilePath = request.getParameterValues("checkedRows");
		
		if(compare_id != null && checkedFilePath != null) {

			int delCnt = 0;
			for(String s:checkedFilePath){
				File f = new File(s);
				if (!f.isDirectory()) {
					f.delete(); // 파일이면 바로 삭제
					delCnt++;
				} 
			}
	    	
	    	JSONObject jso = new JSONObject();    // JASON 객체생성
			jso.put("deletedFileCount", delCnt+"");
    		jarray.add(0, jso);

		} else {
			
	    	JSONObject jso = new JSONObject();    // JASON 객체생성
			jso.put("deletedFileCount", "0");
    		jarray.add(0, jso);
    		
		}		
		
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();  
		out.print(jarray.toString());
		
	}	
	
	/**
	 * 표준연계API 파일동기화 엑셀 다운로드
	 * @param 
	 * @return 표준연계API 파일동기화 엑셀
	 * @exception Exception
	 */
    @IncludedInfo(name="표준연계API 파일동기화 엑셀 다운로드", order = 3 ,gid = 20)
    @RequestMapping(value="/col/cfm/CfmListExcelDownload.do")
    public ModelAndView CfmListListExcelDownload(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {
    	
		String compare_id = (String)request.getSession().getAttribute("cfm.compare_id");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("procNo", request.getParameter("procNo"));
		map.put("compare_id", compare_id);

		List<CfmCompareListVO> compareList = cfmService.selectCompareList(map);

		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("resultList", compareList);
        paramMap.put("resultCnt", compareList.size());
    	return new ModelAndView("CfmListExcel", paramMap);
    	
    }
}
