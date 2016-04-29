package clikmng.nanet.go.kr.mdm.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttCodeEstbsVO;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.mdm.utl.MdmCalUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */
@Controller
public class MdmTnsrAsmblyCodeController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmTnsrAsmblyCodeService")
    private MdmTnsrAsmblyCodeService mdmTnsrAsmblyCodeService;

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
     * 코드가져오기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEstbsList.do")
    public String MdmAsmblyAsembyList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {

    	List<MdmTnpInsttCodeEstbsVO> codeEstbsList = mdmTnsrAsmblyCodeService.selectMdmTnpInsttCodeEstbs(mdmSearchVO);

    	model.addAttribute("codeEstbsList",  codeEstbsList);

        return "clikMng/mdm/MdmEstbsList";
        
    }

    /**
     * 지방정책정보 2단계 코드 가져오기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmOrgCodeStep2List.do")
    public String MdmOrgCodeStep2List(@RequestParam("schOrgCodeStep2") String schOrgCodeStep2, ModelMap model) throws Exception {

    	List<MdmOrgCodeVO> codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(schOrgCodeStep2);

    	model.addAttribute("codeOrgCodeStep2List",  codeOrgCodeStep2List);
        return "clikMng/mdm/MdmOrgCodeStep2List";
    }

    /**
     * 지방정책정보 3단계 코드 가져오기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmOrgCodeStep3List.do")
    public String MdmOrgCodeStep3List(@RequestParam("schOrgCodeStep3") String schOrgCodeStep3, ModelMap model) throws Exception {

    	List<MdmOrgCodeVO> codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(schOrgCodeStep3);

    	model.addAttribute("codeOrgCodeStep3List",  codeOrgCodeStep3List);
        return "clikMng/mdm/MdmOrgCodeStep3List";
    }
    
    /**
     * 지방정책정보 3단계 후 사이트 코드 가져오기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/MdmOrgSiteList.do")
    public String MdmOrgSiteList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {

    	List<MdmOrgCodeVO> codeSiteList = mdmTnsrAsmblyCodeService.selectMdmOrgSiteList(mdmSearchVO);

    	model.addAttribute("codeSiteList",  codeSiteList);
    	model.addAttribute("mdmSearchVO",  mdmSearchVO);
        return "clikMng/mdm/MdmOrgSiteList";
    }    

    /**
     * 날짜 가져오기
     * @param 
     * @param model
     * @return	"/Mdm/MdmGetDate"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmGetDate.do")
    public String MdmGetDate(@RequestParam("schDt") String schDt, ModelMap model) throws Exception {
    	MdmCalUtil calUtil = new MdmCalUtil();
    	calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		int dt = 0;
		
		if( schDt.equals("M") ) {
			dt = -30;
		}
		else if( schDt.equals("W") ) {
			dt = -7;
		}
		
		String dts = "";
		String dt2 = calUtil.getToday();
		String dt1 = calUtil.getDateFromToday(dt);
		
		dts = "{\"schDt1\":\"" + dt1 + "\", \"schDt2\":\"" + dt2 + "\"}";
    	model.addAttribute("dts",  dts);

        return "clikMng/mdm/MdmGetDate";
        
    }

}
