package clikmng.nanet.go.kr.elm.grp.web;

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

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.grp.service.GrpManageService;
import egovframework.rte.fdl.property.EgovPropertyService;



/**
 * 전자도서관 권한 관리 Controller 클래스
 */

@Controller
public class GrpManageController {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "GrpManageService")
	private GrpManageService grpManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "CmmUseService")
	private CmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	// Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 환경설정
	 * 
	 * @param searchVO
	 * @param model
	 * @return "/elm/ElmGroupList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "전자도서관 권한 관리", order = 16, gid = 47)
	@RequestMapping(value = "/elm/grp/ElmGrpList.do")
	public String selectElmGrpList(
			@ModelAttribute("searchVO") UserClassVO searchVO, ModelMap model)
			throws Exception {

		// 0. 로그인 여부 확인
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) 
		{ 
			model.addAttribute("message",	egovMessageSource.getMessage("fail.common.login")); 
			return "forward:/uat/uia/LoginUsr.do"; 
		}

		// 1. 클래스 그룹 목록 목록
		List<UserClassVO> resultList = grpManageService.selectElmGrpList();
		model.addAttribute("resultList", resultList);
		model.addAttribute("resultListSize", resultList.size());

		return "clikMng/elm/grp/ElmGrpList";
	}

	
	/**
	 * 그룹 등록을 한다
	 * 
	 * @param vo
	 *  - userClassVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/elm/grp/ElmGrpRegist.do")
	public String insertElmGrpRegist(
			@ModelAttribute("userClassVO") UserClassVO userClassVO,
			BindingResult bindingResult, Map commandMap, ModelMap model)
			throws Exception {


		// 0. Spring Security 사용자권한 처리 
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); 
		if (!isAuthenticated) 
		{
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login")); 
			return "clikMng/uat/uia/EgovLoginUsr"; 
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String frstRegisterId = loginVO.getMngrId();

		String sLocationUrl = "clikMng/elm/grp/ElmGrpRegist";

		List authorList = null;

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap
				.get("cmd");
		log.info("cmd =>" + sCmd);

		
		// 등록화면과 등록실행의 분기
		// 등록실행
		if (sCmd.equals("save")) 
		{
			// 서버 validate 체크
			beanValidator.validate(userClassVO, bindingResult);
			if (bindingResult.hasErrors()) 
			{
				return sLocationUrl;
			}
			
			// 아이디 설정
			userClassVO.setFrstRegisterId(frstRegisterId);
			userClassVO.setLastUpdusrId(frstRegisterId);
			
			try 
			{
				// 기존 PK 중복 확인
				int dupliId = grpManageService.selectUserGroupIdChk(userClassVO.getUserGroupId());
				if(dupliId > 0) {
					model.addAttribute("msg", "이미 등록된 그룹 아이디 입니다.");
					log.debug("::::::::::::::ERROR::::::::::::::  -- EXIST USER_GROUP_ID");
					return sLocationUrl;
				}
				
				// 그룹 관리 테이블 저장
				grpManageService.insertElmGrpRegist(userClassVO);


			} 
			catch (Exception ex) 
			{
				log.debug("Default Exeption Handler run...............::: insertElmGrpRegist :::");
				ex.printStackTrace();

				model.addAttribute("msg", "fail.common.insert");
			}

			return "forward:/elm/grp/ElmGrpList.do";

		} 
		else 
		{
			// 전자도서관 그룹 등록
			sLocationUrl = "clikMng/elm/grp/ElmGrpRegist";
			return sLocationUrl;

		}

	}

	/**
	 * 관리자 수정을 한다
	 * 
	 * @param vo
	 *            - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/elm/grp/ElmGrpDetail.do")
	public String updateElmGrpDetail(
			@ModelAttribute("userClassVO") UserClassVO userClassVO,
			BindingResult bindingResult, Map commandMap, ModelMap model)
			throws Exception {

		// 0. Spring Security 사용자권한 처리
		/*
		 * Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); if
		 * (!isAuthenticated) { model.addAttribute("message",
		 * egovMessageSource.getMessage("fail.common.login")); return
		 * "clikMng/uat/uia/EgovLoginUsr"; }
		 */

		/*
		 * //-- 사용자 관련 개발 끝나면 다시 개발 // 로그인 객체 선언 LoginVO loginVO = (LoginVO)
		 * EgovUserDetailsHelper.getAuthenticatedUser();
		 */

		// return Url
		String sLocationUrl = "clikMng/elm/grp/ElmGrpDetail";

		// 기능구분 del = 삭제, edit = 수정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap
				.get("cmd");

		/** 관리자 및 지방의회 상세보기 */
		if (sCmd.equals("")) {
			// 관리자 상세내용 조회
			model.addAttribute("userClassVO",
					grpManageService.selectElmGrpDetail(userClassVO));

			return sLocationUrl;

		} else if (sCmd.equals("edit")) {
			// 관리자 수정
			// 서버 validate 체크
			beanValidator.validate(userClassVO, bindingResult);
			if (bindingResult.hasErrors()) {
				return sLocationUrl;
			}

			// -- 사용자 관련 개발 끝나면 다시 개발
			// 아이디 설정
			userClassVO.setLastUpdusrId("TESTER");
			grpManageService.updateElmGrpDetail(userClassVO);

			return "redirect:/elm/grp/ElmGrpList.do";

		} else if (sCmd.equals("del")) {
			grpManageService.deleteElmGrpDetail(userClassVO);
			sLocationUrl = "clikMng/elm/grp/ElmGrpList";
			return "redirect:/elm/grp/ElmGrpList.do";
		}

		return sLocationUrl;
	}

}
