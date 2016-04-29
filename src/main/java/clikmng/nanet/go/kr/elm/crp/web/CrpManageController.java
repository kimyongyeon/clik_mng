package	clikmng.nanet.go.kr.elm.crp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.crp.service.CrpManageService;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpDetailVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpListVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 열람신청 권한 관리를 처리하는 Controller 클래스
 */

@Controller
public class CrpManageController {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "CrpManageService")
	private CrpManageService crpManageService;

	/**	EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private	CmmUseService cmmUseService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	// Validation 관련
	@Autowired
	private	DefaultBeanValidator beanValidator;

	/**
	 * 열람신청권한 리스트 조회
	 * @param searchVO
	 * @param model
	 * @return	"/elm/ElmGroupList"
	 * @throws Exception
	 */
	@IncludedInfo(name="전자도서관 권한	관리", order = 16 ,gid = 47)
	@RequestMapping(value="/elm/crp/ElmCrpList.do")
	public String selectElmCrpList(@ModelAttribute("searchVO") ElmCrpListVO searchVO, ModelMap model, Map commandMap) throws Exception 
	{

		// 0. 로그인 여부 확인
		Boolean	isAuthenticated	= EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "forward:/uat/uia/LoginUsr.do";
		}


		
		//** pageing *//*
		PaginationInfo paginationInfo =	new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	searchVO.setPageUnit(searchVO.getPageUnit());
    	searchVO.setPageSize(searchVO.getPageSize());
		
    	
		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		
		// 3. 열람 권한 코드
		cmmCodeVO.setCodeId("ELA003");
		List codeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("codeList", codeList);    	


		int totCnt = crpManageService.selectElmCrpListTotCnt(searchVO);
		List<ElmCrpListVO> list	= crpManageService.selectElmCrpList(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		model.addAttribute("searchVO", searchVO);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("resultList", list);
		model.addAttribute("paginationInfo", paginationInfo);
		


		return "clikMng/elm/crp/ElmCrpList";
	}



	/**
	 * 열람신청 권한 등록
	 * @param vo - userClassVo
	 * @return
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/crp/ElmCrpRegist.do")
	public String insertElmCrpRegist(@ModelAttribute("vo") ElmCrpDetailVO vo
		, BindingResult bindingResult
		, Map commandMap
		, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated)
		{
		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		return "clikMng/uat/uia/EgovLoginUsr";
		}

		//-- 사용자	관련 개발 끝나면 다시 개발
		// 로그인VO에서  사용자 정보 가져오기
	/*
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String  frstRegisterId = loginVO.getUniqId();
	*/

		String sLocationUrl	= "clikMng/elm/crp/ElmCrpRegist";
		String sCmd	= commandMap.get("cmd")	== null	? "" : (String)	commandMap.get("cmd");


		// 등록화면과 등록실행의 분기
		// 등록실행
		if (sCmd.equals("save"))
		{
			//서버	validate 체크
			beanValidator.validate(vo, bindingResult);
			if(bindingResult.hasErrors())
			{
				return sLocationUrl;
			}
			
			
			//-- 사용자 관련 개발 끝나면 다시 개발
			//아이디 설정
			//mngVO.setFrstRegisterId((String)loginVO.getUniqId());
			//mngVO.setLastUpdusrId((String)loginVO.getUniqId());
			vo.setFrstRegisterId("TESTER");
			//vo.setLastUpdusrId("TESTER");

			try
			{
				// 관리자 테이블 저장
				crpManageService.insertElmCrpRegist(vo);
			} 
			catch	(Exception ex) 
			{
				log.debug("Default Exeption	Handler	run...............::: insertElmCrpRegist	:::");
				ex.printStackTrace();

				model.addAttribute("msg", "fail.common.select");
				return "clikMng/elm/crp/ElmCrpList";
			}

			return "redirect:/elm/crp/ElmCrpList.do";

		} 
		else
		{
			// clik	관리자 등록 관련
			CmmCodeVO cmmCodeVO;

			// 1. 자료 구분	코드
			cmmCodeVO = new	CmmCodeVO();
			cmmCodeVO.setCodeId("ELA003");
			List codeList =	cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

			// 2. 열람 구분	코드
			cmmCodeVO = new	CmmCodeVO();
			cmmCodeVO.setCodeId("ELA002");
			List openList =	cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

			model.addAttribute("codeList", codeList);
			model.addAttribute("openList", openList);

			model.addAttribute("vo", vo);
			
			return "clikMng/elm/crp/ElmCrpRegist";
		}

	}



	/**
	 * 열람신청 권한 수정
	 * @param vo - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/crp/ElmCrpDetail.do")
	public String updateElmCrpDetail(@ModelAttribute("vo") ElmCrpDetailVO	vo
		, BindingResult bindingResult
		, Map commandMap
		, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean	isAuthenticated	= EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) 
		{
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "clikMng/uat/uia/EgovLoginUsr";
		}


	/*
	*	//-- 사용자 관련 개발 끝나면 다시 개발
	// 로그인 객체 선언
	LoginVO	loginVO	= (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	*/

		// return Url
		String sLocationUrl = "clikMng/elm/crp/ElmCrpDetail";

		// 기능구분 del	= 삭제,	edit = 수정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");


		/** 관리자 및 지방의회 상세보기	*/
		System.out.println("##############################################");
		System.out.println("##### sCmd ===> " + sCmd);
		System.out.println("##############################################");
		
		if(sCmd.equals("edit")) 
		{
			System.out.println("##### edit #####");
			
			// 관리자 수정
			//서버  validate 체크
			beanValidator.validate(vo,	bindingResult);
			if(bindingResult.hasErrors())
			{
				return sLocationUrl;
			}

			//-- 사용자	관련 개발 끝나면 다시 개발
			//아이디 설정
			vo.setLastUpdusrId("TESTER");
			crpManageService.updateElmCrpDetail(vo);

		} 
		else if(sCmd.equals("del")) 
		{
			System.out.println("##### del #####");
			crpManageService.deleteElmCrpDetail(vo);
			return "redirect:/elm/crp/ElmCrpList.do";
		}

		System.out.println("##### view #####");
		
		// 	관리자 상세내용 조회
		ElmCrpDetailVO detail = crpManageService.selectElmCrpDetail(vo);
		
		if(detail == null)
		{
			model.addAttribute("message", "데이터가 존재하지 않습니다.");
			return "redirect:/elm/crp/ElmCrpList.do";
		}
		
		model.addAttribute("vo", detail); 
		
		// clik	관리자 등록 관련
		CmmCodeVO cmmCodeVO;

		// 1. 자료 구분	코드
		cmmCodeVO = new	CmmCodeVO();
		cmmCodeVO.setCodeId("ELA003");
		List codeList =	cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

		// 2. 열람 구분	코드
		cmmCodeVO = new	CmmCodeVO();
		cmmCodeVO.setCodeId("ELA002");
		List openList =	cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		

		model.addAttribute("codeList", codeList);
		model.addAttribute("openList", openList);
		

		List<ReadGrantVO> openCodeList = crpManageService.selectElmCrpOpenCode(vo);
		HashMap<String, String> openCode = new HashMap();
		
		System.out.println("##############################################");
		System.out.println(openCodeList.size());
		System.out.println("##############################################");

		for(ReadGrantVO o : openCodeList)
		{
			System.out.println("##############################################");
			System.out.println(o.getReadngSeCode());
			System.out.println(o.getReadngAt());
			System.out.println("##############################################");
			openCode.put(o.getReadngSeCode(), o.getReadngAt());
		}

		model.addAttribute("openCode", openCode);


		return sLocationUrl;


    }
}
