package clikmng.nanet.go.kr.cmm.top.web;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.top.service.TopManageService;
import clikmng.nanet.go.kr.cmm.top.service.TopManageVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mna.ram.service.AuthorRoleManageVO;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
 

/**
 * 
 * TOP 메뉴 처리
 */

@Controller
public class TopManageController {

	protected Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name = "TopManageService")
    private TopManageService topManageService;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    protected EgovMessageSource egovMessageSource;
    	
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
    /**
     * 메뉴 좌측 메뉴 
     * @param model
     * @return	"/include/top"
     * @throws Exception
     */
    @RequestMapping(value="/cmm/top/top.do")
    public String selectLeftMenu(@ModelAttribute("topManageVO") TopManageVO topManageVO
    								, ModelMap model) throws Exception {
    	
    	String sLocationUrl = "clikMng/include/top";
        
    	//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
        LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	topManageVO.setAuthorCode(loginVO.getAuthorCode());

    	// 상단 메뉴바에 회원이름 표기
    	//String mngrNm 		= loginVO.getMngrNm();
    	
    	// 메뉴 조회
    	List<TopManageVO> result = topManageService.selectMenuList(topManageVO);

    	model.addAttribute("menuList", result);
    	//model.addAttribute("mngrNm", mngrNm);
    	
    	
    	return sLocationUrl;
    }
 
}
