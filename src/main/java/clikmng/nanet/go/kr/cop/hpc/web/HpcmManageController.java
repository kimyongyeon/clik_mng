package clikmng.nanet.go.kr.cop.hpc.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.hpc.service.HpcmManageService;
import clikmng.nanet.go.kr.cop.hpc.service.HpcmManageDefaultVO;
import clikmng.nanet.go.kr.cop.hpc.service.HpcmManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 도움말을 처리하는 비즈니스 구현 클래스
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
public class HpcmManageController {
	 
	 

	protected Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name = "HpcmManageService")
    private HpcmManageService hpcmManageService;
    
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

    
//    /**
//     * 개별 배포시 메인메뉴를 조회한다.
//     * @param model
//     * @return	"/cop/hpc/"
//     * @throws Exception
//     */
//    @RequestMapping(value="/cop/hpc/EgovMain.do")
//    public String egovMain(ModelMap model) throws Exception {
//    	return "clikMng/cop/hpc/EgovMain";
//    }
//    
//    /**
//     * 메뉴를 조회한다.
//     * @param model
//     * @return	"/cop/hpc/EgovLeft"
//     * @throws Exception
//     */
//    @RequestMapping(value="/cop/hpc/EgovLeft.do")
//    public String egovLeft(ModelMap model) throws Exception {
//    	return "clikMng/cop/hpc/EgovLeft";
//    }
    
    /**
     * 도움말내용 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/cop/hpc/EgovHpcmListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="도움말", order = 520 ,gid = 50)
    @RequestMapping(value="/cop/hpc/HpcmListInqire.do")
    public String selectHpcmList(@ModelAttribute("searchVO") HpcmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	searchVO.setPageUnit(searchVO.getPageUnit());
    	searchVO.setPageSize(searchVO.getPageSize());
    	
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List HpcmList = hpcmManageService.selectHpcmList(searchVO);
        model.addAttribute("resultList", HpcmList);
        
        int totCnt = hpcmManageService.selectHpcmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "clikMng/cop/hpc/EgovHpcmListInqire";
    } 
    
    /**
     * 도움말내용 목록에 대한 상세정보를 조회한다.
     * @param hpcmManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/hpc/EgovHpcmDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/cop/hpc/HpcmDetailInqire.do")
    public String	selectHpcmDetail(HpcmManageVO hpcmManageVO,
            @ModelAttribute("searchVO") HpcmManageDefaultVO searchVO,
            ModelMap model) throws Exception {  
    	
		HpcmManageVO vo = hpcmManageService.selectHpcmDetail(hpcmManageVO);
		
		model.addAttribute("result", vo);
			
        return	"clikMng/cop/hpc/EgovHpcmDetailInqire";
    }

    /**
     * 도움말내용를 등록하기 위한 전 처리(공통코드 처리)   
     * @param searchVO
     * @param model
     * @return	"/cop/hpc/EgovHpcmCnRegist"
     * @throws Exception
     */
    @RequestMapping("/cop/hpc/HpcmCnRegistView.do")
    public String insertHpcmCnView(
            @ModelAttribute("searchVO") HpcmManageDefaultVO searchVO, Model model)
            throws Exception {
    	
    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("RKS016");
		
		List _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", _result);		
		
    	
        model.addAttribute("hpcmManageVO", new HpcmManageVO());              
        
        return "clikMng/cop/hpc/EgovHpcmCnRegist";
                
    }
    
    /**
     * 도움말내용를 등록한다.
     * @param searchVO
     * @param hpcmManageVO
     * @param bindingResult
     * @return	"forward:/cop/hpc/HpcmListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/hpc/HpcmCnRegist.do")
    public String insertHpcmCn(
            @ModelAttribute("searchVO") HpcmManageDefaultVO searchVO,
            @ModelAttribute("hpcmManageVO") HpcmManageVO hpcmManageVO,
            BindingResult bindingResult)            
            throws Exception {

    	beanValidator.validate(hpcmManageVO, bindingResult);
    	
		if(bindingResult.hasErrors()){
			
			return "clikMng/cop/hpc/EgovHpcmCnRegist";//2011.09.28
						
		}

		//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
/*    	
		LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String	frstRegisterId = loginVO.getUniqId();
*/   			        
		String	frstRegisterId = "TESTER";
    	hpcmManageVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	hpcmManageVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID
    	            	    
        hpcmManageService.insertHpcmCn(hpcmManageVO);                 
		        
        return "forward:/cop/hpc/HpcmListInqire.do";        
    }

    /**
     * 도움말내용를 수정하기 위한 전 처리(공통코드 처리)        
     * @param hpcmId
     * @param searchVO
     * @param model
     * @return	"/cop/hpc/EgovHpcmCnUpdt"
     * @throws Exception
     */
    @RequestMapping("/cop/hpc/HpcmCnUpdtView.do")
    public String updateHpcmCnView(@RequestParam("hpcmId") String hpcmId ,
            @ModelAttribute("searchVO") HpcmManageDefaultVO searchVO, ModelMap model)
            throws Exception {
    	
    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("RKS016");
		
		List _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", _result);		
    	
        HpcmManageVO hpcmManageVO = new HpcmManageVO();
        
        // Primary Key 값 세팅
        hpcmManageVO.setHpcmId(hpcmId);
		
        // 변수명은 CoC 에 따라 sampleVO
        model.addAttribute(selectHpcmDetail(hpcmManageVO, searchVO, model));
        
        // 변수명은 CoC 에 따라 JSTL사용을 위해
        model.addAttribute("hpcmManageVO", hpcmManageService.selectHpcmDetail(hpcmManageVO));
        
        
        return "clikMng/cop/hpc/EgovHpcmCnUpdt";
    }

    /**
     * 도움말내용를 수정한다.           
     * @param searchVO
     * @param hpcmManageVO
     * @param bindingResult
     * @return	"forward:/cop/hpc/HpcmListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/hpc/HpcmCnUpdt.do")
    public String updateHpcmCn(
            @ModelAttribute("searchVO") HpcmManageDefaultVO searchVO, 
            @ModelAttribute("hpcmManageVO") HpcmManageVO hpcmManageVO,
            BindingResult bindingResult)
            throws Exception {
    	    	
    	// Validation
    	beanValidator.validate(hpcmManageVO, bindingResult);
    	
		if(bindingResult.hasErrors()){
			
			return "clikMng/cop/hpc/EgovHpcmCnUpdt";//2011.09.28
						
		}    	
    	
		//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
/*    	
		LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String	lastUpdusrId = loginVO.getUniqId();
*/  		
    	String	lastUpdusrId = "TESTER";
    			        
    	hpcmManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID
    	
    	hpcmManageService.updateHpcmCn(hpcmManageVO);
        
    	        
        return "forward:/cop/hpc/HpcmListInqire.do";
        
    }

    /**
     * 도움말내용를 삭제한다.
     * @param hpcmManageVO
     * @param searchVO
     * @return	"forward:/cop/hpc/HpcmListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/hpc/HpcmCnDelete.do")
    public String deleteHpcmCn(
            HpcmManageVO hpcmManageVO,
            @ModelAttribute("searchVO") HpcmManageDefaultVO searchVO)
            throws Exception {
    	
    	hpcmManageService.deleteHpcmCn(hpcmManageVO);        
        
        return "forward:/cop/hpc/HpcmListInqire.do";
    }

}
