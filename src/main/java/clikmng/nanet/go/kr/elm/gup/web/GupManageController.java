package clikmng.nanet.go.kr.elm.gup.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmDetailCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.elm.cup.service.ElmCupListVO;
import clikmng.nanet.go.kr.elm.cup.service.CupManageService;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupListVO;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupDetailVO;
import clikmng.nanet.go.kr.elm.gup.service.GupManageService;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 환경설정 관리를 처리하는 Controller 클래스
 */

@Controller
public class GupManageController {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "GupManageService")
	private GupManageService gupManageService;

	@Resource(name = "CupManageService")
	private CupManageService cupManageService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;


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
	 * 환경설정
	 * @param searchVO
	 * @param model
	 * @return	"/elm/ElmGroupList"
	 * @throws Exception
	 */
	@IncludedInfo(name="전자도서관 권한 관리", order = 16 ,gid = 47)
	@RequestMapping(value="/elm/gup/ElmGupList.do")
	public String selectElmGupList(@ModelAttribute("searchVO") ElmGupListVO searchVO, ModelMap model) throws Exception {

		// 0. 로그인 여부 확인
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "forward:/uat/uia/LoginUsr.do";
		}


		// 1. 클래스 그룹 목록 목록
		List<ElmGupListVO> list = gupManageService.selectElmGupList();

		model.addAttribute("searchVO", searchVO);
		model.addAttribute("resultList", list);

		return "clikMng/elm/gup/ElmGupList";
	}




	/**
	 * 그룹 등록을 한다
	 * @param vo - userClassVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/gup/ElmGupRegist.do")
	public String insertElmGupRegist(@ModelAttribute("searchVO") ElmGupDetailVO searchVO
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model) throws Exception {



		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "clikMng/uat/uia/EgovLoginUsr";
		}

			//-- 사용자 관련 개발 끝나면 다시 개발
			// 로그인VO에서  사용자 정보 가져오기
	/*
			LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

			String  frstRegisterId = loginVO.getUniqId();
	*/

		String sLocationUrl = "clikMng/elm/gup/ElmGupRegist";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		// 등록화면과 등록실행의 분기
		// 등록실행
		if (sCmd.equals("save")) 
		{
			//서버  validate 체크
			beanValidator.validate(searchVO, bindingResult);
			if(bindingResult.hasErrors())
			{
				return sLocationUrl;
			}

			//-- 사용자 관련 개발 끝나면 다시 개발
			//아이디 설정
			searchVO.setFrstRegisterId("TESTER");

			System.out.println("#####################################################");
			System.out.println("msg ======= " + searchVO.getGrantBenMsg());			
			System.out.println("#####################################################");

			try 
			{
				gupManageService.insertElmGupRegist(searchVO);
			} 
			catch (Exception ex) 
			{
				log.debug("Default Exeption Handler run...............::: MngRegist :::");
				ex.printStackTrace();

				model.addAttribute("msg", "fail.common.select");
				return "clikMng/elm/gup/ElmGupList";
			}

			return "forward:/elm/gup/ElmGupList.do";

		} 
		else 
		{
			CmmCodeVO cmmCodeVO;

			// 1. 클래스 그룹 목록 목록
			List<UserClassVO> userClassList = gupManageService.selectUserClass();
			model.addAttribute("userClassList", userClassList);

			// 2. 자료 권한 코드
			cmmCodeVO = new CmmCodeVO();
			cmmCodeVO.setCodeId("ELA003");
			List codeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
			model.addAttribute("codeList", codeList);

			// 3. 열람 권한 코드
			cmmCodeVO = new CmmCodeVO();
			cmmCodeVO.setCodeId("ELA002");
			List openList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
			model.addAttribute("openList", openList);

			// 4. 저작권 허락 코드
			//List<ElmCupListVO> cuplist = cupManageService.selectElmCupList();
			//model.addAttribute("cuplist", cuplist);

			sLocationUrl = "clikMng/elm/gup/ElmGupRegist";


			return sLocationUrl;
		}

	}



	/**
	 * 관리자 수정을 한다
	 * @param vo - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/gup/ElmGupDetail.do")
	public String updateElmGupDetail(@ModelAttribute("searchVO") ElmGupDetailVO searchVO
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model) throws Exception {

		System.out.println("##############################################################");
		System.out.println("updateElmGupDetail");
		System.out.println(searchVO);
		System.out.println("##############################################################");
		
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "clikMng/uat/uia/EgovLoginUsr";
		}


	/*
	*      //-- 사용자 관련 개발 끝나면 다시 개발
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	*/

		// return Url
		String sLocationUrl = "clikMng/elm/gup/ElmGupDetail";

		// 기능구분 del = 삭제, edit = 수정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");


		if(sCmd.equals("edit")) 
		{
			//서버  validate 체크
			beanValidator.validate(searchVO, bindingResult);
			if(bindingResult.hasErrors())
			{
				return sLocationUrl;
			}

			//-- 사용자 관련 개발 끝나면 다시 개발
			//아이디 설정
			searchVO.setFrstRegisterId("TESTER");

			try 
			{
				gupManageService.updateElmGupDetail(searchVO);
			} 
			catch (Exception ex) 
			{
				log.debug("Default Exeption Handler run...............::: MngRegist :::");
				ex.printStackTrace();

				model.addAttribute("msg", "fail.common.select");
				return "clikMng/elm/gup/ElmGupList";
			}

		} 
		else if(sCmd.equals("del")) 
		{
			gupManageService.deleteElmGupDetail(searchVO);
			return "redirect:/elm/gup/ElmGupList.do";
		}

		// 1. 클래스 그룹 목록 목록
		List<UserClassVO> userClassList = gupManageService.selectUserClass();
		model.addAttribute("userClassList", userClassList);

		// 2. 자료 권한 코드
		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		cmmCodeVO.setCodeId("ELA003");
		List codeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("codeList", codeList);

		// 3. 열람 권한 코드
		cmmCodeVO = new CmmCodeVO();
		cmmCodeVO.setCodeId("ELA002");
		List openList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("openList", openList);

		// 4. 저작권 허락 코드
		//List<ElmCupListVO> cuplist = cupManageService.selectElmCupList();
		//model.addAttribute("cuplist", cuplist);

		ElmGupDetailVO result = gupManageService.selectElmGupDetail(searchVO);
		
		System.out.println("############################################################");
		System.out.println(result.getUserGroupId());
		System.out.println(result.getGrantUseCode());
		System.out.println(result.getGrantBenCode());
		System.out.println(result.getGrantBenMsg());
		System.out.println(result.getOpenCode());
		System.out.println("############################################################");
		
		model.addAttribute("userGroupId", result.getUserGroupId());
		model.addAttribute("grantUseCode", result.getGrantUseCode());
		model.addAttribute("grantBenCode", result.getGrantBenCode());
		model.addAttribute("grantBenMsg", result.getGrantBenMsg());
		model.addAttribute("openCode", result.getOpenCode());
		// 관리자 상세내용 조회
		model.addAttribute("result", result);

		return sLocationUrl;

	}


}
