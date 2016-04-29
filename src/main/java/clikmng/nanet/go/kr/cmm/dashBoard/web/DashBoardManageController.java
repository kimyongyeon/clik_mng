package clikmng.nanet.go.kr.cmm.dashBoard.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.dashBoard.service.DashBoardManageVO;
import clikmng.nanet.go.kr.cmm.top.service.TopManageService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.service.MdmPolicyInfoService;
import clikmng.nanet.go.kr.mob.service.MobManageService;
import clikmng.nanet.go.kr.mob.service.MobManageVO;
import clikmng.nanet.go.kr.rcm.service.RcmManageDefaultVO;
import clikmng.nanet.go.kr.rcm.service.RcmManageService;
import clikmng.nanet.go.kr.sts.stm.service.StmManageService;
import clikmng.nanet.go.kr.uss.mng.service.MngService;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;
import egovframework.rte.fdl.property.EgovPropertyService;
 

/**
 * 
 * DashBoard 처리
 */

@Controller
public class DashBoardManageController {

	protected Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name = "TopManageService")
    private TopManageService topManageService;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    protected EgovMessageSource egovMessageSource;

    @Resource(name = "MngService")
    private MngService MngService;

    @Resource(name = "MdmPolicyInfoService")
    private MdmPolicyInfoService mdmPolicyInfoService;

    @Resource(name = "RcmManageService")
    private RcmManageService RcmManageService;

    
    @Resource(name = "MobManageService")
    private MobManageService MobManageService;
    

    @Resource(name = "StmManageService")
    private StmManageService stmManageService;
    

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
    /**
     * 대쉬보드 화면 
     * @param model
     * @return	"/include/DashBoard"
     * @throws Exception
     */
    @RequestMapping(value="/cmm/dashBoard/DashBoard.do")
    public String selectDashBoard(@ModelAttribute("dashBoardManageVO") DashBoardManageVO dashBoardManageVO
    								, ModelMap model) throws Exception {
    	

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

    	
    	// 1. 지방의회/지자체 담당자 승인요청 목록
    	MngVO mngVO = new MngVO();
        mngVO.setFirstIndex(0);
        mngVO.setLastIndex(5);
        mngVO.setRecordCountPerPage(5);
        mngVO.setSearchCondition("selConfmSttusAt");
        mngVO.setSearchKeyword("N");
        
        List localMngList = MngService.selectLocalMngList(mngVO);
        model.addAttribute("localMngList", localMngList);

    	
    	// 승인 대기 게시물 목록
        // 지방정책정보
//        MdmSearchVO mdmSearchVO = new MdmSearchVO();
//        mdmSearchVO.setFirstRecord(0);
//        mdmSearchVO.setLastRecord(5);
//        //mdmSearchVO.setREGION("051");
//        mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
//    	List<MdmPolicyInfoVO> policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVO);
//    	model.addAttribute("policyInfoList", policyInfoList);
    	
    	// Open API 발급 목록
    	RcmManageDefaultVO rcmManageDefaultVO = new RcmManageDefaultVO();
    	rcmManageDefaultVO.setFirstIndex(0);
    	rcmManageDefaultVO.setLastIndex(5);
    	rcmManageDefaultVO.setRecordCountPerPage(5);
		
        List rcmList = RcmManageService.selectAilList(rcmManageDefaultVO);
        model.addAttribute("rcmList", rcmList);
    	
    	// 모바일 의견 목록
    	MobManageVO mobManageVO = new MobManageVO();
    	
		mobManageVO.setFirstIndex(0);
		//mobManageVO.setLastIndex(5);
		mobManageVO.setRecordCountPerPage(5);
        List mobList = MobManageService.selectMobOnsList(mobManageVO);

        model.addAttribute("mobList", mobList);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String today = sdf.format(cal.getTime());
        cal.add(cal.DATE, -1);
        String beforeDay = sdf.format(cal.getTime());

        // 전일 방문자 수
        model.addAttribute("cntBefore", stmManageService.countVisitorDayBefore(beforeDay));

        // 금일 방문자 수
        model.addAttribute("cntToday", stmManageService.countVisitorToday(today));

    	// Web, Mobile 접속 현황 파이 그래프
        model.addAttribute("platformList", stmManageService.selectVisitorPlatform(today));

    	// 금일 현재 기준까지 시간별 접속 현황 그래프 표시 바 그래프
        model.addAttribute("hourList", stmManageService.selectVisitorHour(today));



    	return "clikMng/include/DashBoard";
    }
 
}
