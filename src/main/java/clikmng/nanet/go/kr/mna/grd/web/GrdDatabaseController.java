package clikmng.nanet.go.kr.mna.grd.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import clikmng.nanet.go.kr.mna.grd.service.GrdDatabaseAccessService;
import clikmng.nanet.go.kr.mna.grd.service.GrdDatabaseAccessVO;

/**
 * 
 * 등급별 데이터베이스 접근에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 
 * @since 
 * @version 
 * @see
 */

@Controller
public class GrdDatabaseController {

	/** log */
    protected static final Log LOG = LogFactory.getLog(GrdDatabaseController.class);
    
    @Resource(name="GrdDatabaseAccessService")
    
    private GrdDatabaseAccessService grdDatabaseAccessService;
	
	/**
	 * 등급별 데이터베이스 접근 목록을 조회한다
	 * @param vo - GrdDatabaseAccessVo
	 * @return 등급별 데이터베이스 접근 목록 페이지
	 * @exception Exception
	 */  
    @RequestMapping(value="/mna/grd/GrdDatabaseAccessList.do")
    public String selectGrdDatabaseAccessList(@ModelAttribute("grdDatabaseAccessVO") GrdDatabaseAccessVO grdDatabaseAccess
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }

	/**
	 * 등급별 데이터베이스 접근 목록을 등록한다
	 * @param vo - GrdDatabaseAccessVo
	 * @return 등급별 데이터베이스 접근 등록 페이지
	 * @exception Exception
	 */       
    @RequestMapping(value="/mna/grd/GrdDatabaseAccessRegist.do")
    public String grdDatabaseAccessRegist(@ModelAttribute("grdDatabaseAccessVO") GrdDatabaseAccessVO grdDatabaseAccess
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }
    
	/**
	 * 등급별 데이터베이스 접근 목록을 변경한다
	 * @param vo - GrdDatabaseAccessVo
	 * @return 등급별 데이터베이스 접근 변경 페이지
	 * @exception Exception
	 */    
    @RequestMapping(value="/mna/grd/GrdDatabaseAccessModify.do")
    public String grdDatabaseAccessModify(@ModelAttribute("grdDatabaseAccessVO") GrdDatabaseAccessVO grdDatabaseAccess
    			  , ModelMap model) throws Exception {
    	
    	return "";
    }    
}
