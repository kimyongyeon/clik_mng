package clikmng.nanet.go.kr.uss.umt.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.uss.umt.service.UmtService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class UmtMngController {

	/** log */
    protected static final Log log = LogFactory.getLog(UmtMngController.class);
	
    @Resource(name="UmtService")
    private UmtService umtService;
    
    /** PropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;      
    
	/**
	 * 이용자 목록을 조회한다
	 * @param vo - UmtVo
	 * @return 이용자 목록 페이지
	 * @exception Exception
	 */    
    @RequestMapping(value="/uss/umt/UmtList.do")
    public String selectMngList(HttpServletRequest request, Map commandMap
    		, ModelMap model) throws Exception {
    	
    	String select_db = CommonStringUtil.isNullToString((request.getParameter("select_db")));
    	model.addAttribute("select_db", select_db);
    	

		// 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(1);
        /** PropertyService.sample */
        paginationInfo.setRecordCountPerPage(propertiesService.getInt("pageUnit"));
        paginationInfo.setPageSize(propertiesService.getInt("pageSize"));        
        
        paginationInfo.setTotalRecordCount(0);
        model.addAttribute("paginationInfo", paginationInfo);    	
        
    	return "clikMng/uss/umt/UmtList";
    }
    
	/**
     * 회원조회목록
     * @param request
     * @return list
     * @throws Exception
     */
    @RequestMapping("/uss/umt/UmtSearchList.do")
    public String userSearchLst(ModelMap model, HttpServletRequest request) throws Exception {
    	

        String sqlNanet = "dlib.op.user.UserNanetSearch";
        String countNanetSql = "dlib.op.user.UserNanetSearchCount";

        String select_db = CommonStringUtil.isNullToString(request.getParameter("select_db"));
    	String select_classtype = CommonStringUtil.isNullToString(request.getParameter("select_classtype"));
    	String select_userid = CommonStringUtil.isNullToString(request.getParameter("select_userid"));
    	String select_username = CommonStringUtil.isNullToString(request.getParameter("select_username"));
    	String select_mail = CommonStringUtil.isNullToString(request.getParameter("select_mail"));
    	String select_birth = CommonStringUtil.isNullToString(request.getParameter("select_birth"));
    	select_birth = select_birth.replaceAll("-", "");
    	String select_regdate = CommonStringUtil.isNullToString(request.getParameter("select_regdate"));
    	String select_orderfield = CommonStringUtil.isNullToString(request.getParameter("select_orderfield"));
    	String select_ordersort = CommonStringUtil.isNullToString(request.getParameter("select_ordersort"));
    	String select_countperpg = CommonStringUtil.isNullToString(request.getParameter("select_countperpg"));

    	model = CommonUtil.modelMapping(model, request);
		
        // 현재 페이지
        String currentPageNo = CommonUtil.nvl(request.getParameter("currentPageNo"), "1");
        
        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(Integer.parseInt(currentPageNo));
        int countperpg = 20;
        try{
        	countperpg = Integer.parseInt(select_countperpg);
        }catch(Exception e){
        	countperpg = propertiesService.getInt("pageUnit");
        }
        paginationInfo.setRecordCountPerPage(countperpg);
        paginationInfo.setPageSize(propertiesService.getInt("pageSize"));
                
		HashMap map = new HashMap();
		map.put("CLASS", select_classtype);
		map.put("USERID", select_userid);  
		map.put("USER_NAME", select_username);
		map.put("USER_MAIL", select_mail);
		map.put("USER_BIRTH", select_birth);
		map.put("REG_DATE", select_regdate);
		map.put("ORDER_FIELD", select_orderfield);
		map.put("ORDER_SORT", select_ordersort);
		
		HashMap map2 = new HashMap();
		map2.put("CLASS", select_classtype);
		map2.put("USERID", select_userid);  
		map2.put("USER_NAME", select_username);
		map2.put("USER_MAIL", select_mail);
		map2.put("USER_BIRTH", select_birth);
		map2.put("REG_DATE", select_regdate);
		map2.put("ORDER_FIELD", select_orderfield);
		map2.put("ORDER_SORT", select_ordersort);		
        
        
		//암호화 대상 조건
		HashMap encMap = new HashMap();
		encMap.put("USER_NAME", "enc");
		encMap.put("USER_MAIL", "enc");
		encMap.put("USER_BIRTH", "enc");
		
		//리스트 복호화 대상
		encMap.put("user_name", "enc");
		encMap.put("user_mail", "enc");
		encMap.put("birth", "enc");
		
		
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
		        model.addAttribute("list", list);
		        model.addAttribute("paginationInfo", paginationInfo);
    			return "clikMng/uss/umt/UmtList";
			}
			else{
    			model.addAttribute("msg", "fail.common.select");
    			return "clikMng/uss/umt/UmtList";
			}
		}
		catch(Exception ex){
			log.debug("Default Exeption Handler run...............::: UmtSearchList :::");
			ex.printStackTrace();			
			
			model.addAttribute("msg", "fail.common.select");
			return "clikMng/uss/umt/UmtList";
		}
    }
    
	/**
     * 회원정보 상세보기
     * @param request
     * @return info
     * @throws Exception
     */
    @RequestMapping("/uss/umt/UmtDetailView.do")
    public String userEdit2(HttpServletRequest request, ModelMap model) throws Exception {

    	String sql = "dlib.op.user.UserNanetInfo";

    	String select_db = CommonStringUtil.isNullToString(request.getParameter("select_db"));
    	String userid = CommonStringUtil.isNullToString(request.getParameter("userid"));
		
    	model = CommonUtil.modelMapping(model, request);

    	if("".equals(userid)) {
			model.addAttribute("msg", "fail.common.select");
			return "forward:/uss/umt/UmtList.do";
		}
		
    	HashMap map	= new HashMap();
	    map.put("USERID", userid);
		
	    HashMap encMap = new HashMap();
	    encMap.put("USER_NAME", "enc");
	    encMap.put("HINT_Q", "enc"); // 2013-11-29
	    encMap.put("HINT_A", "enc");
	    encMap.put("JOB", "enc");
	    encMap.put("USER_MAIL", "enc");
	    encMap.put("BIRTH", "enc");
	    
	    encMap.put("user_name", "enc");
	    encMap.put("hint_q", "enc"); // 2013-11-29
	    encMap.put("hint_a", "enc");
	    encMap.put("job", "enc");
	    encMap.put("user_mail", "enc");
	    encMap.put("birth", "enc");
	    encMap.put("zipcode", "enc");
	    encMap.put("address", "enc");
	    encMap.put("address_detail", "enc");	    
	    encMap.put("user_tel", "enc");
	    
		try{
			HashMap info = umtService.dbReadEnc(encMap, map, sql);	

			if(info != null){
				String hint_q = info.get("hint_q").toString();
			    model.put("info", info);
				return "clikMng/uss/umt/UmtDetail";
			}else {
				model.addAttribute("msg", "fail.common.select");
				return "forward:/uss/umt/UmtSearchList.do";
			}
		}
		catch(Exception ex){
			log.debug("Default Exeption Handler run...............::: UmtDetailView :::");
			ex.printStackTrace();
			
			model.addAttribute("msg", "fail.common.sql");
			return "forward:/uss/umt/UmtSearchList.do";
		}
    }       
    
}
