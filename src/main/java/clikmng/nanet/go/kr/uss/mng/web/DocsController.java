package clikmng.nanet.go.kr.uss.mng.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import clikmng.nanet.go.kr.cmm.enc.Encrypt;
import clikmng.nanet.go.kr.uss.mng.service.DocsService;
import clikmng.nanet.go.kr.uss.mng.service.DocsVO;
import clikmng.nanet.go.kr.uss.mng.service.MngService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DocsController {

	/** log */
    protected static final Log LOG = LogFactory.getLog(DocsController.class);

    @Resource(name="DocsService")
    private DocsService docsService;
    
    /** PropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService; 
    
    /**
     * 관리자 등록시 직원찾기
     */
    @RequestMapping(value = "/docs/SearchEmp.do")
    public String selectSearchEmp(@ModelAttribute("docsVO") DocsVO docsVO
    		, HttpServletRequest request
    		, HttpServletResponse response
    		, Map commandMap
    		, ModelMap model) throws Exception {

        /** PropertyService.sample */
    	docsVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	docsVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(docsVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(docsVO.getPageUnit());
        paginationInfo.setPageSize(docsVO.getPageSize());

        docsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        docsVO.setLastIndex(paginationInfo.getLastRecordIndex());
        docsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List reusltList = docsService.selectSearchEmp(docsVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        
        int totCnt = (Integer) docsService.selectSearchEmpCnt(docsVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);    

        
        return "clikMng/uss/mng/PopSearchEmp";
			
	}    
    
    
	
	
    /**
     *  파라미터 값 체크 , injection 취약점 예방
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getPrmStrCnvr(String srcString) {    
        if (srcString == null){
          return "";
        }

        try 
		{
        	srcString=replace(srcString,"'","");
        	srcString=replace(srcString,"`","");
	        srcString=replace(srcString,"\"","");
	        srcString=replace(srcString,"%","");
	        srcString=replace(srcString,"<","");
	        srcString=replace(srcString,">","");
	        srcString=replace(srcString,"(","");
	        srcString=replace(srcString,")","");
	        srcString=replace(srcString,"#","");
	        srcString=replace(srcString,"&","");
	        srcString=replace(srcString,";","");
	        srcString=replace(srcString,"\\'", "''");
	        srcString=replace(srcString,"\t'", "' '");
	        srcString=replace(srcString," ", "");
		}
        catch (Exception ex)
		{
			ex.printStackTrace();
		}

        return srcString;
    }	

    
    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;
        
        if(srcStr!=null){
	        while (srcStr.indexOf(subject) >= 0) {
	            preStr = srcStr.substring(0, srcStr.indexOf(subject));
	            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
	            srcStr = nextStr;
	            rtnStr.append(preStr).append(object);
	        }
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }
    
    
    @RequestMapping(value = "/test.do")
    public String selectUserLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		/**
		*	로그인
		*
		*	@param userid 사용자 아이디
		*	@param user_password 사용자 비밀번호
		*	@return
		*	@exception Exception
		*/	
		
	        String userid = null;
	        String user_password = null;

	        String LOGIN_TYPE = null;


			userid = getPrmStrCnvr(request.getParameter("USER_ID"));
			user_password = getPrmStrCnvr(request.getParameter("USER_PASSWORD"));
	   		
			user_password = Encrypt.com_Encode(":" + user_password + ":sisenc");
			
	    		
    		LOGIN_TYPE = "TUSER";

			HashMap map = new HashMap();
			map.put("userid", userid);
			map.put("user_password", user_password);   

			Object result = null;
			
			try{
				//result = docsService.selectUserInfo(map); 
			}catch(Exception e){
				System.out.println("Tuser 테이블 접속 오류");
				System.out.println("Tuser 테이블 접속 오류");
				System.out.println("Tuser 테이블 접속 오류");
				System.out.println("Tuser 테이블 접속 오류");
				System.out.println("Tuser 테이블 접속 오류");
			}
			
			return "clikMng/mna/mnu/MenuList";
			
	}    

}
