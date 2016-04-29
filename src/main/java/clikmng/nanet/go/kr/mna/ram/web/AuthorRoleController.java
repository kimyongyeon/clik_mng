package clikmng.nanet.go.kr.mna.ram.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.SessionVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mna.ram.service.AuthorRoleManage;
import clikmng.nanet.go.kr.mna.ram.service.AuthorRoleManageVO;
import clikmng.nanet.go.kr.mna.ram.service.AuthorRoleManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
 * @author
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 */

@Controller
@SessionAttributes(types=SessionVO.class)
public class AuthorRoleController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "AuthorRoleManageService")
    private AuthorRoleManageService AuthorRoleManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
	 * 권한 롤 관계 화면 이동
	 * @return "clikMng/mna/ram/EgovDeptAuthorList"
	 * @exception Exception
	 */
    @RequestMapping("/mna/ram/EgovAuthorRoleListView.do")
    public String selectAuthorRoleListView() throws Exception {

        return "clikMng/mna/ram/EgovAuthorRoleManage";
    } 

	/**
	 * 권한별 할당된 롤 목록 조회
	 * 
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/mna/ram/EgovAuthorRoleList.do")
	public String selectAuthorRoleList(@ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO,
			                            ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
		model.addAttribute("authorClCode", authorRoleManageVO.getAuthorClCode());
		
		authorRoleManageVO.setAuthorRoleList(AuthorRoleManageService.selectAuthorRoleList(authorRoleManageVO));
        model.addAttribute("authorRoleList", authorRoleManageVO.getAuthorRoleList());
        int totCnt = AuthorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        
        return "clikMng/mna/ram/EgovAuthorRoleManage";
	}
    
	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * @param authorCode String
	 * @param roleCodes String
	 * @param regYns String
	 * @param authorRoleManage AuthorRoleManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/mna/ram/EgovAuthorRoleInsert.do")
	public String insertAuthorRole(@RequestParam("authorCode") String authorCode,
			                       @RequestParam("roleCodes") String roleCodes,
			                       @RequestParam("regYns") String regYns,
			                       @ModelAttribute("authorRoleManage") AuthorRoleManage authorRoleManage,
			                         SessionStatus status,
			                         ModelMap model) throws Exception {
		
		// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
		
    	String [] strRoleCodes = roleCodes.split(";");
    	String [] strRegYns = regYns.split(";");
    	
    	authorRoleManage.setRoleCode(authorCode);
    	
    	for(int i=0; i<strRoleCodes.length;i++) {
    		authorRoleManage.setRoleCode(strRoleCodes[i]);
    		authorRoleManage.setRegYn(strRegYns[i]);
    		if(strRegYns[i].equals("Y")){
    			AuthorRoleManageService.deleteAuthorRole(authorRoleManage);//2011.09.07
    			AuthorRoleManageService.insertAuthorRole(authorRoleManage);
    		}else {
    			AuthorRoleManageService.deleteAuthorRole(authorRoleManage);
    		}
    	}

        status.setComplete();
        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));		
		return "forward:/mna/ram/EgovAuthorRoleList.do";
	}    
}