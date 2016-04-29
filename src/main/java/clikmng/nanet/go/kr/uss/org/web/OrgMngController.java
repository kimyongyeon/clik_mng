/**
 * 협정기관관리 컨트롤러 클래스
 */
package clikmng.nanet.go.kr.uss.org.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.uss.org.service.OrgService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class OrgMngController {

	/** log */
    protected static final Log log = LogFactory.getLog(OrgMngController.class);
	
    @Resource(name="OrgService")
    private OrgService OrgService;
    
    /** PropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;      
    
	/**
	 * 협정기관 목록을 조회한다
	 * @param vo - OrgVo
	 * @return 이용자 목록 페이지
	 * @exception Exception
	 */    
    @RequestMapping(value="/uss/org/OrgList.do")
	public String OrgList(HttpServletRequest request, ModelMap model) {
        // 페이징 처리
    	
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(1);
        paginationInfo.setRecordCountPerPage(propertiesService.getInt("pageUnit"));
        paginationInfo.setPageSize(propertiesService.getInt("pageSize"));
        paginationInfo.setTotalRecordCount(0);
        model.addAttribute("paginationInfo", paginationInfo);
        
		return "clikMng/uss/org/OrgList";
	}
    
	/**
     * 협정기관 목록을 검색어로 조회한다
     * @param request
     * @return list
     * @throws Exception
     */
    @RequestMapping("/uss/org/OrgSearchList.do")
    public String OrgSearchList(HttpServletRequest request, ModelMap model) throws Exception {
    	
		String sql = "dlib.op.user.LibraryInfoUserSearch";
        String countSql = "dlib.op.user.LibraryInfoUserSearchCount";
    	
    	String criteria = CommonStringUtil.isNullToString(request.getParameter("criteria"));
    	String search = CommonStringUtil.isNullToString(request.getParameter("search"));
    	
    	model = CommonUtil.modelMapping(model, request);
    	
        // 현재 페이지
        String currentPageNo = CommonUtil.nvl(request.getParameter("currentPageNo"), "1");
        
        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(Integer.parseInt(currentPageNo));
        paginationInfo.setRecordCountPerPage(propertiesService.getInt("pageUnit"));
        paginationInfo.setPageSize(propertiesService.getInt("pageSize"));
    	
    	log.debug("Input Data : "+criteria);
    	
		HashMap map = new HashMap();
		map.put("VALUESTRING", criteria);
		map.put("SEARCHNAME", search);  

		int totalRecordCount = OrgService.dbListCnt(map, countSql);
        paginationInfo.setTotalRecordCount(totalRecordCount);
		
        // 돌아갈 페이지에 데이터가 없다면 마지막 페이지로 이동(사용자가 데이터 삭제시)
        int lastPage = (int)Math.ceil(totalRecordCount / (double)paginationInfo.getRecordCountPerPage());
        if ( lastPage < paginationInfo.getCurrentPageNo() ) {
            paginationInfo.setCurrentPageNo(lastPage);
        }
        map.put("firstRecordIndex", (paginationInfo.getFirstRecordIndex() <= 0)? 0:paginationInfo.getFirstRecordIndex());
        map.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		
		List<HashMap> list = OrgService.dbList(map, sql);

		try{
			if(list != null){
				model.addAttribute("search", search);
		        model.addAttribute("list", list);
		        model.addAttribute("paginationInfo", paginationInfo);
    			return "clikMng/uss/org/OrgList";
			}
			else{
    			model.addAttribute("msg", "fail.common.select");
    			return "forward:/uss/org/OrgList.do";				
			}
		}
		catch(Exception ex){
			log.debug("Default Exeption Handler run...............::: OrgSearchList :::");
			ex.printStackTrace();
			
			model.addAttribute("msg", "fail.common.select");
			return "/uss/org/OrgList.do";
		}
    }   
    
    
	/**
     * 협정기관 특정기관 상세보기
     * @param request
     * @return list
     * @throws Exception
     */
    @RequestMapping("/uss/org/OrgDetailView.do")
    public String OrgDetailView(HttpServletRequest request, ModelMap model) throws Exception {

		model = CommonUtil.modelMapping(model, request);
    	/*
    	 * 선택된 협정기관정보
    	 */
		String update_name = CommonStringUtil.isNullToString(request.getParameter("update_name"));
		
		if(update_name.length() <= 0) {
			update_name	= CommonStringUtil.isNullToString(request.getAttribute("update_name"));
		}
		
		String msg			= CommonStringUtil.isNullToString(request.getParameter("msg"));
		
		if(update_name.equals("")) {
			model.addAttribute("msg", "fail.common.select");
			return "/uss/org/OrgSearchList.do";
		}
	
    	HashMap map			= new HashMap();
    	HashMap Hmap		= new HashMap();
	    String sql 			= "dlib.op.user.LibraryInfo";
	    
	    //2012-08-31 권인애 :  본관 이름 가져오기 
	    String sql_uname="dlib.op.user.LibraryUniList";
	    HashMap umap		= new HashMap();
	    
	    map.put("NAME", update_name);
		try{
			Hmap = OrgService.dbRead(map, sql);	
			
			if( Hmap.get("up_agree_no") != null && !"".equals((String)Hmap.get("up_agree_no")) ){
				//2012-08-31 권인애 :  본관 이름 가져오기 
				map.put("search_agree_no", Hmap.get("up_agree_no"));
				umap = OrgService.dbRead(map, sql_uname);	
			}
			if(Hmap != null){
			    model.put("info", Hmap);
			    model.put("msg", msg);
			    
			  //2012-08-31 권인애 :  본관 이름 가져오기 
			    model.put("umap", umap);
			}
			else {
				model.addAttribute("msg", "fail.common.select");
				return "forward:/op/LibraryInfoUserUserSearch.do";
			}
			return "clikMng/uss/org/OrgDetail";
		}
		catch(Exception ex){
			log.debug("Default Exeption Handler run...............::: OrgDetailView :::");
			ex.printStackTrace();
			
			model.addAttribute("msg", "fail.common.sql");
			return "/uss/org/OrgList.do";
		}
    	
    }    
    
    
	/**
     * 변경 이력 조회
     * @param request
     * @return list
     * @throws Exception
     */
    @RequestMapping("/uss/org/OrgUserInfoHistory.do")
    public String LibraryInfoUserHistorySearch(HttpServletRequest request, ModelMap model) throws Exception {

    	String sql = "dlib.op.user.LibraryInfoUserHistorySearch";
    	
    	String flag = CommonStringUtil.isNullToString(request.getParameter("flag"));
    	String old_name = CommonStringUtil.isNullToString(request.getParameter("old_name"));
    	String old_agreename = CommonStringUtil.isNullToString(request.getParameter("old_agreename"));
    	String info_name = "";
    	
		HashMap map = new HashMap();
    	if("A".equals(flag)){
    		map.put("NAME_LAST", old_agreename);
    		map.put("CODE_TYPE", flag);  
    		info_name = old_agreename;
    	}else{
    		map.put("NAME_LAST", old_name);
    		map.put("CODE_TYPE", flag);  
    		info_name = old_name;
    	}

    	List<HashMap> list = OrgService.dbList(map, sql);
    	
		try{
			if(list != null){
		        model.addAttribute("flag", flag);
		        model.addAttribute("info_name", info_name);
		        model.addAttribute("list", list);
    			return "clikMng/uss/org/OrgUserInfoHistory";
			}
			else{
    			model.addAttribute("msg", "fail.common.select");
    			return "clikMng/uss/org/OrgUserInfoHistory";				
			}
		}
		catch(Exception ex){
			log.debug("Default Exeption Handler run...............::: OrgUserInfoHistory :::");
			ex.printStackTrace();
			
			model.addAttribute("msg", "fail.common.select");
			return "/uss/org/OrgList.do";
		}
    }   
    

	/**
     * DOCS MACAddress 테이블 조회
     * @param request
     * @return list
     * @throws Exception
     */
    @RequestMapping("/uss/org/UserPCIDDupCheck.do")
    public String macAddressSearch(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	String sql = "dlib.op.user.MacAddressSearch";
    	
    	String names = CommonStringUtil.isNullToString(request.getParameter("names"));
    	
		HashMap map = new HashMap();
		map.put("NAME", names);

		List<HashMap> list = OrgService.dbList(map, sql);
		
		try{
	        model.addAttribute("names", names);
	        model.addAttribute("list", list);
			return "clikMng/uss/org/UserPCIDDupCheck";
		}
		catch(Exception ex){
			log.debug("Default Exeption Handler run...............::: UserPCIDDupCheck :::");
			ex.printStackTrace();
			
			model.addAttribute("msg", "fail.common.select");
			return "/uss/org/OrgList.do";
		}
    }
    
}
