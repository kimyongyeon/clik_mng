package clikmng.nanet.go.kr.mna.lau.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import clikmng.nanet.go.kr.mna.lau.service.LtrwkAuthService;
import clikmng.nanet.go.kr.mna.lau.service.LtrwkAuthVO;

/**
 * 
 * 저작물 권한관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 
 * @since 
 * @version 
 * @see
 */

@Controller
public class LtrwkAuthController {

	/** log */
    protected static final Log LOG = LogFactory.getLog(LtrwkAuthController.class);
    
    @Resource(name="LtrwkAuthService")
    private LtrwkAuthService ltrwkAuthService;
	
	/**
	 * 저작물권한 목록을 조회한다
	 * @param vo - LtrwkAuthVO
	 * @return 저작물권한 목록 페이지
	 * @exception Exception
	 */      
    @RequestMapping(value="/mna/lau/LtrwkAuthList.do")
    public String selectLtrwkAuthList(@ModelAttribute("ltrwkAuthVO") LtrwkAuthVO ltrwkAuthVO
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }    
    
    
	/**
	 * 저작물권한 목록을 등록한다
	 * @param vo - LtrwkAuthVO
	 * @return 저작물권한 목록 페이지
	 * @exception Exception
	 */         
    @RequestMapping(value="/mna/lau/LtrwkAuthList.do")
    public String ltrwkAuthRegist(@ModelAttribute("ltrwkAuthVO") LtrwkAuthVO ltrwkAuthVO
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }    
    
	/**
	 * 저작물권한 목록을 수정한다
	 * @param vo - LtrwkAuthVO
	 * @return 저작물권한 목록 페이지
	 * @exception Exception
	 */    
    @RequestMapping(value="/mna/lau/LtrwkAuthModify.do")
    public String ltrwkAuthModify(@ModelAttribute("ltrwkAuthVO") LtrwkAuthVO ltrwkAuthVO
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }      
    
	/**
	 * 저작물권한 목록을 삭제한다
	 * @param vo - LtrwkAuthVO
	 * @return 저작물권한 목록 페이지
	 * @exception Exception
	 */      
    @RequestMapping(value="/mna/lau/LtrwkAuthDelete.do")
    public String ltrwkAuthDelete(@ModelAttribute("ltrwkAuthVO") LtrwkAuthVO ltrwkAuthVO
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }      
}
