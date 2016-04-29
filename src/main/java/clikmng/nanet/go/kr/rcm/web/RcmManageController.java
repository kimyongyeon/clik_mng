package clikmng.nanet.go.kr.rcm.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.ImHttpRequestor;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mim.service.MimManageService;
import clikmng.nanet.go.kr.mim.service.MimManageVO;
import clikmng.nanet.go.kr.mim.web.MimManageController;
import clikmng.nanet.go.kr.rcm.service.RcmManageDefaultVO;
import clikmng.nanet.go.kr.rcm.service.RcmManageService;
import clikmng.nanet.go.kr.rcm.service.RcmManageVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 리소스 관리를 처리하는 Controller 클래스
 */

@Controller
public class RcmManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "RcmManageService")
    private RcmManageService RcmManageService;

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

	@Resource(name = "MimManageService")
    private MimManageService mimManageService;
	
    /**
     * 리소스 - OPENAPI 신청내역 조회
     * @param searchVO
     * @param model
     * @return	"/rcm/AilList"
     * @throws Exception
     */
    @RequestMapping(value="/rcm/AilList.do")
    public String selectAilList(@ModelAttribute("searchVO") RcmManageDefaultVO searchVO, ModelMap model) throws Exception {

    	//** EgovPropertyService.LogList *//*
    	searchVO.setPageUnit(searchVO.getPageUnit());
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	//** pageing *//*
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> ailList = RcmManageService.selectAilList(searchVO);
        model.addAttribute("resultList", ailList);
        model.addAttribute("searchVO", searchVO);

        int totCnt = RcmManageService.selectAilListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "clikMng/rcm/AilList";
    }

    /**
    * 리소스 - OPENAPI 신청내역 상세정보
    * @param RcmManageVO
    * @param searchVO
    * @param model
    * @return	"/rcm/AilDetail"
    * @throws Exception
    */
    @RequestMapping("/rcm/AilDetail.do")
    public String	AilDetail(RcmManageVO RcmManageVO,
            @ModelAttribute("searchVO") RcmManageDefaultVO searchVO,
            ModelMap model) throws Exception {            
    	
    	try {
    		
	    	RcmManageVO = RcmManageService.selectAiDetail(RcmManageVO);
	    	
    	}
    	catch(Exception e){
    		
    		e.getStackTrace();
    		
    		return "forward:/rcm/AilList.do";
    	}
    		
        model.addAttribute("result", RcmManageVO);    		
    		    	    	
        return	"clikMng/rcm/AilDetail";
    }
    
    
    /**
     * 리소스 - OPENAPI 처리
     * @param RcmManageVO
     * @param searchVO
     * @param model
     * @return	"/rcm/AilDetail"
     * @throws Exception
     */
     @RequestMapping("/rcm/AilProc.do")
     public String	AilProc(RcmManageVO RcmManageVO,
             @ModelAttribute("searchVO") RcmManageDefaultVO searchVO,
             HttpServletResponse response,
	         HttpServletRequest request,
             ModelMap model) throws Exception {            
    	
     	try {
     		
     		RcmManageVO.setReqstInsttNm(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(RcmManageVO.getReqstInsttNm(), "UTF-8")));
     		RcmManageVO.setChargerNm(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(RcmManageVO.getChargerNm(), "UTF-8")));
     		RcmManageVO.setChargerEmail(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(RcmManageVO.getChargerEmail(), "UTF-8")));
     		RcmManageVO.setPrcusePrpos(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(RcmManageVO.getPrcusePrpos(), "UTF-8")));
     		RcmManageVO.setRm(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(RcmManageVO.getRm(), "UTF-8")));
     		
     		int result =0;
     		if (RcmManageVO.getProcMode().equals("UPDATE")){
     			
     			RcmManageVO vo = RcmManageService.selectAiDetail(RcmManageVO);
     			StringBuffer sb = new StringBuffer();
     			
     			/* 메일 발송 처리 - 이전 상태가 승인이 아닐 경우에만 메일 발송*/
     			if(!vo.getSttusCode().equals("STC02")){
     				MimManageVO mimManageVO = new MimManageVO();
     				
     				//승인 안내 내용
					sb.append("<!doctype html>                                                                                                                                       	");
					sb.append("<html lang='en'>                                                                                                                                      	");
					sb.append("	<head>                                                                                                                                               	");
					sb.append("		 <meta charset='UTF-8'>                                                                                                                          	");
					sb.append("		 <meta name='Generator' content='EditPlus®'>                                                                                                     	");
					sb.append("		 <meta name='Author' content=''>                                                                                                                 	");
					sb.append("		 <meta name='Keywords' content=''>                                                                                                               	");
					sb.append("		 <meta name='Description' content=''>                                                                                                            	");
					sb.append("		 <title>Document</title>                                                                                                                         	");
					sb.append("	</head>                                                                                                                                              	");
					sb.append("	<body style='margin:0; padding:0; font-size:14px; color:#666; font-family:'맑은고딕', '돋움', Dotum, arial, sans-serif;'>                            		");
					sb.append("		<div style='width:710px; height:450px; float:left; background:url(\"http://clik.nanet.go.kr/images/openapi/openapi_confm_mail_bg.jpg\") top left no-repeat;'>                                                	");
					sb.append("			<h3 style='margin: 70px 60px 10px 60px; color:#383838;'>안녕하십니까 국회.지방의회 의정자료 공유통합시스템입니다. <br>신청하신 OpenAPI 인증키 요청이 승인되어 인증키가 발급되었습니다.<br> OpenAPI 활용 방법은 [국회.지방의회 의정자료 공유통합시스템]의<br> 리소스센터에서 확인하실 수 있습니다.</h3>                                                       				");
					sb.append("			<h3 style='margin: 0px 60px 10px 60px;'>인증키 정보는 아래와 같습니다.</h3>                                                                				");
					sb.append("			<table style='width: 590px; margin:0 60px; padding:20px; font-size:1.0em; line-height:2.0em; border:2px solid #ccc; border-radius:20px;'>    	");
					sb.append("				<tbody>                                                                                                                                    	");
					sb.append("				<thead>                                                                                                                                    	");
					sb.append("					<colgroup>                                                                                                                             	");
					sb.append("						<col width='25%' />                                                                                                                	");
					sb.append("						<col width='75%' />                                                                                                                	");
					sb.append("					</colgroup>                                                                                                                            	");
					sb.append("				</thead>                                                                                                                                   	");
					sb.append("					<tr>                                                                                                                                   	");
					sb.append("						<th style='text-align:right;'>인증키 : </th>                                                                                       	");
					sb.append("						<td>"+vo.getCrtfcKey()+"</td>                                                                                                      	");
					sb.append("					</tr>                                                                                                                                  	");
					sb.append("					<tr>                                                                                                                                   	");
					sb.append("						<th style='text-align:right;'>신청기관명 : </th>                                                                                   		");
					sb.append("						<td>"+vo.getReqstInsttNm()+"</td>                                                                                                  	");
					sb.append("					</tr>                                                                                                                                  	");
					sb.append("					<tr>                                                                                                                                   	");
					sb.append("						<th style='text-align:right;'>담당자명 : </th>                                                                                     		");
					sb.append("						<td>"+vo.getChargerNm()+"</td>                                                                                                     	");
					sb.append("					</tr>                                                                                                                                  	");
					sb.append("					<tr>                                                                                                                                   	");
					sb.append("						<th style='text-align:right;'>담당자 이메일 : </th>                                                                                		");
					sb.append("						<td>"+vo.getChargerEmail()+"</td>                                                                                                  	");
					sb.append("					</tr>                                                                                                                                  	");
					sb.append("					<tr>                                                                                                                                   	");
					sb.append("						<th style='text-align:right;'>담당자 연락처 : </th>                                                                                		");
					sb.append("						<td>"+vo.getChargerTelno()+"</td>                                                                                                  	");
					sb.append("					</tr>                                                                                                                                  	");
					sb.append("				</tbody>                                                                                                                                   	");
					sb.append("			</table>                                                                                                                                       	");
					sb.append("		</div>                                                                                                                                             	");
					sb.append("	</body>                                                                                                                                                	");
					sb.append("</html>                                                                                                                                                 	");

     				
     				try {
     		    		//담당자 이메일
     					mimManageVO.setEmail(vo.getChargerEmail());
     		    		//제목
     					mimManageVO.setSj("[국회.지방의회 의정자료 공유통합시스템][OpenAPI 승인] 신청하신 OpenAPI 인증키 요청이 승인되었습니다.");
     					//내용
     					mimManageVO.setEmailCn(sb.toString());
     					//발신자
     					mimManageVO.setSendNm("clik@nanet.go.kr");
     					
     					mimManageVO.setMailRcver(vo.getChargerNm() + "(" + vo.getChargerEmail() + ")");
     					
     			    	// 로그인VO에서  사용자 정보 가져오기
     					LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();    	
     			    	String frstRegisterId = loginVO.getMngrId();
     			    	mimManageVO.setFrstRegisterId(frstRegisterId);			// 최초등록자ID
     			    	mimManageVO.setLastUpdusrId(frstRegisterId);    		// 최종수정자ID
     			
     			    	mimManageService.insertSendMailInfo(mimManageVO);
     			    	
     			       	// 3. 메일 발송----------------------------------------------------------------------------------------
     			    	String returnMsg = "";
     					String from = StringUtil.isNullToString(mimManageVO.getSendNm()); //보내는사람
     					String to = ""; 			//받는사람 - 메일주소
     			
     					String subject = StringUtil.isNullToString(mimManageVO.getSj()); //제목
     					String body = StringUtil.isNullToString(mimManageVO.getEmailCn()); //내용
     					String charset = "utf-8"; //인코딩
     			    	
     					subject = new String(subject.getBytes("utf-8"));
     					body = new String(body.getBytes("utf-8"));
     					
     			
 			    		// 수신자 이메일
 			    		to = StringUtil.isNullToString(vo.getChargerEmail());
 			    		
 			    		boolean find = false;
 			    		String cd = "";
 			    		find = MimManageController.isEmailPattern(to);
 			    		
 			    		if(find == false){
 			    			cd = "false";
 			    		}
 			    		
 			        	// 이메일 유효성 검사
 			        	if(cd.equals("false"))
 			        	{
 			    			System.out.println("OPENAPI 승인 메일 발송 실패 - 이메일 유효성 오류");
 			    		}
 			        	else
 			        	{
 			
 			    	    	
 			    	    	String hostAddress = EgovProperties.getProperty("Globals.HostAddress");
 			    	    	String hostPort = EgovProperties.getProperty("Globals.Port");
 			    	    	
 			    	    	log.info("hostAddress = " + hostAddress + ", hostPort = " + hostPort);
 			    	    	
 			    			URL url = new URL("http://ems.nanet.go.kr/servlet/sendemailu");
 			    	        InputStream is = null;
 			    	        BufferedReader br = null;
 			    	        ImHttpRequestor requestor = new ImHttpRequestor(url);
 			    	    	requestor.addParameter("from", from);		// 보내는 사람
 			    	    	requestor.addParameter("to", to);			// 받는사람 이메일
 			    	    	requestor.addParameter("subject", subject);	// 제목
 			    	    	requestor.addParameter("body", body);		// 본문
 			    	    	requestor.addParameter("charset", charset);	
 			    	    	
 			    	        try {
 			    	            is = requestor.sendMultipartPost();
 			    	            br = new BufferedReader(new InputStreamReader(is));
 			    	            String line = "";
 			    	            while( (line=br.readLine())!= null ) {
 			    	                returnMsg += line.trim();
 			    	            }
 			    	            System.out.println("returnMsg : " + returnMsg);
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
     			    	
     			    	// 실 수신자 수
     			    	mimManageVO.setSendRejectCnt(1);
     			    	
     					// 메일 발송이 성공이면 메일 상태를 발송성공으로 수정 및 메일 수신거부자 수정
     					mimManageService.updateSendMailStatus(mimManageVO);
     		    	
     		    	} catch (Exception e) {
     		    		log.debug("::: EMAIL SEND ERROR ::: " + e.getMessage());
     		    		e.printStackTrace();
     					e.getMessage();
     				} 
     			}
     			
     			result = RcmManageService.updateAiDetail(RcmManageVO);
     		}
     		else if (RcmManageVO.getProcMode().equals("DELETE")) {
     				
     			result=	RcmManageService.deleteAiDetail(RcmManageVO);
     		}
     		
     		if (result != 0)
     			RcmManageVO = RcmManageService.selectAiDetail(RcmManageVO);
     		
     	}
     	catch(Exception e){
     		e.getStackTrace();     	
     		model.addAttribute("result", RcmManageVO);
     	}
     		
             		
		model.addAttribute("result", RcmManageVO);     		    	    	
		return "forward:/rcm/AilList.do";
     }
    
    /**
     * 리소스 - OPENAPI 이용내역 조회
     * @param searchVO
     * @param model
     * @return	"/rcm/AulList"
     * @throws Exception
     */  
    @RequestMapping(value="/rcm/AulList.do")
    public String selectAulList(@ModelAttribute("searchVO") RcmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
		// 날짜 값이 없으면 오늘날짜로 셋팅
		if(searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1()) 
				|| searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2())) {

			String TODAY = getToday();
			searchVO.setSchDt1(TODAY);
			searchVO.setSchDt2(TODAY);
		}
		
		searchVO.setSchDt1(searchVO.getSchDt1().replace("-", ""));
        searchVO.setSchDt2(searchVO.getSchDt2().replace("-", ""));
		
        List<?> LogList = RcmManageService.selectAuList(searchVO);
        model.addAttribute("resultList", LogList);
        
        String schDt1 = searchVO.getSchDt1();
        String schDt2 = searchVO.getSchDt2();
        
        schDt1 = schDt1.substring(0, 4) + "-" + schDt1.substring(4, 6) + "-" + schDt1.substring(6, 8);
        schDt2 = schDt2.substring(0, 4) + "-" + schDt2.substring(4, 6) + "-" + schDt2.substring(6, 8);
        
        searchVO.setSchDt1(schDt1);
        searchVO.setSchDt2(schDt2);
    	
        model.addAttribute("searchVO", searchVO);
        return "clikMng/rcm/AulList";
    }
    
    /**
     * 인증키 이용내역 엑셀 출력
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/rcm/AulListExcel.do")
    public ModelAndView selectAulListExcel(@ModelAttribute("searchVO") RcmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	// 날짜 값이 없으면 오늘날짜로 셋팅
		if(searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1()) 
				|| searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2())) {

			String TODAY = getToday();
			searchVO.setSchDt1(TODAY);
			searchVO.setSchDt2(TODAY);
		}
		
		searchVO.setSchDt1(searchVO.getSchDt1().replace("-", ""));
        searchVO.setSchDt2(searchVO.getSchDt2().replace("-", ""));
		
        List<?> LogList = RcmManageService.selectAuList(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
    	map.put("resultList", LogList);
    	map.put("resultCnt", Integer.toString(LogList.size()));

        return new ModelAndView("AulExcel", map);
    }
    
	/**
	 * 금일 날짜 입력
	 * @return
	 * @throws Exception
	 */
	public String getToday() throws Exception {
		GregorianCalendar cal = new GregorianCalendar();
		DecimalFormat df = null; 
		df = new DecimalFormat("00");

		String delimiter = "-";
		
		StringBuffer strdate = new StringBuffer();
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.DATE)));
		return strdate.toString();
	}
	
	/**
    * 리소스 - OPENAPI 요청 테스트 화면
    * @param RcmManageVO
    * @param searchVO
    * @param model
    * @return	"/rcm/AilDetail"
    * @throws Exception
    */
    @RequestMapping("/rcm/openapiCallTest.do")
    public String	openapiCallTest(HttpServletRequest request, ModelMap model) throws Exception {            
    		    	    	
        return	"clikMng/rcm/openapiCallTest";
    }
	
    /**
     * 리소스 - OPENAPI 요청 테스트 화면
     * @param model
     * @return	"검색결과"
     * @throws Exception
     */
     @RequestMapping("/rcm/openapiCallAjaxRequest.do")
     public String openapiCallAjaxRequest(HttpServletRequest request, ModelMap model) throws Exception {            
    	
    	String apiType 			= CommonUtil.nvl(request.getParameter("apiType"),"");
     	String key 				= CommonUtil.nvl(request.getParameter("key"),"");
     	String type 			= CommonUtil.nvl(request.getParameter("type"),"");
     	String displayType 		= CommonUtil.nvl(request.getParameter("displayType"),"");
     	String startCount 		= CommonUtil.nvl(request.getParameter("startCount"),"");
     	String listCount 		= CommonUtil.nvl(request.getParameter("listCount"),"");
     	String searchType 		= CommonUtil.nvl(request.getParameter("searchType"),"");
     	String searchKeyword 	= CommonUtil.nvl(request.getParameter("searchKeyword"),"");
     	String docid 			= CommonUtil.nvl(request.getParameter("docid"),"");
     	String paramUrl 		= "";
  		
     	paramUrl += "key=" + key; 
     	paramUrl += "&type=" + type;
     	paramUrl += "&displayType=" + displayType;
     	
     	if("list".equals(displayType))
     	{
	     	paramUrl += "&startCount=" + startCount;
	     	paramUrl += "&listCount=" + listCount;
	     	paramUrl += "&searchType=" + searchType;
	     	paramUrl += "&searchKeyword=" + searchKeyword;
     	}
     	else if("detail".equals(displayType))
     	{
     		paramUrl += "&docid=" + docid;
     	}
     	
    	//System.out.println("paramUrl >>>>>>>>>>>>>>>>> " + paramUrl + ", apiType>>>>>>>>> " +apiType);
    	
		String serverName = request.getServerName();
		
		String baseUrl = "";
		if ("clik.nanet.go.kr".equals(serverName)) 
		{
			baseUrl = "http://openapi-clik.nanet.go.kr/" + apiType;
		} 
		else if ("10.201.27.151".equals(serverName)) 
		{
			baseUrl = "http://10.201.27.151:19082/" + apiType;
		}
		else if ("localhost".equals(serverName)) 
		{
			//baseUrl = "http://localhost:9090/" + apiType;
			baseUrl = "http://10.201.27.151:19082/" + apiType;
		}
		
		String requestUrl = baseUrl + "?"+ paramUrl;
		
		//System.out.println(">>>>>>>>>>>>>>>>> " + requestUrl);
		
		URL url = new URL(requestUrl);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("CONTENT-TYPE","text/html"); 
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
		String inputLine;
		String buffer = "";
		while ((inputLine = in.readLine()) != null){
		 	buffer += inputLine.trim();
		}

		//System.out.println("buffer : " + buffer);
		
		in.close();
		
		model.put("msg", buffer);
		
		return "clikMng/mdm/MdmIsView";
     }
}
