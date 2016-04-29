package clikmng.nanet.go.kr.mdm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmRegionNewsVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileDetailVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileVO;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngUtil;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
import clikmng.nanet.go.kr.mdm.service.MdmRegionNewsService;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.mdm.utl.MdmCalUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmExcelUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmPaging;
import clikmng.nanet.go.kr.mdm.utl.MdmStrUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */
@Controller
public class MdmRegionNewsController {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "MdmRegionNewsService")
	private MdmRegionNewsService mdmRegionNewsService;

	@Resource(name = "MdmTnsrAsmblyCodeService")
	private MdmTnsrAsmblyCodeService mdmTnsrAsmblyCodeService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "CmmUseService")
	private CmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "MdmFileMngService")
	private MdmFileMngService fileMngService;

	@Resource(name = "MdmFileMngUtil")
	private MdmFileMngUtil fileUtil;

	// Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 지역현안소식 목록
	 * 
	 * @param mdmPolicyInfoVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfoList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 목록", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsList.do")
	public String MdmRegionNewsList(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			ModelMap model) throws Exception {

		if (!mdmSearchVO.getSchBrtcCode().equals("")
				&& mdmSearchVO.getSchLoAsmCode().equals("")) {
			mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode() + "001");
		}

		//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("REGDATE DESC");
    	}
    	
		String schDt1 = mdmSearchVO.getSchDt1();
		String schDt2 = mdmSearchVO.getSchDt2();
		String schDt3 = mdmSearchVO.getSchDt3();
		String schDt4 = mdmSearchVO.getSchDt4();

		if (mdmSearchVO.getSchKw() != null
				&& !mdmSearchVO.getSchKw().trim().equals("")) {
			if (mdmSearchVO.getSchKey().equals("")
					|| mdmSearchVO.getSchKey().equals("schTitle")) {
				mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw()); // 제목
			} else if (mdmSearchVO.getSchKey().equals("schContent")) {
				mdmSearchVO.setSchContent(mdmSearchVO.getSchKw()); // 내용
			} else if (mdmSearchVO.getSchKey().equals("cnId")) {
				mdmSearchVO.setSchKw("'" + mdmSearchVO.getSchKw() + "'"); // 문서번호
				mdmSearchVO.setSchDt1("");
				mdmSearchVO.setSchDt2("");
				mdmSearchVO.setSchDt3("");
				mdmSearchVO.setSchDt4("");
			}
		}

		List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService
				.selectMdmOrgCodeStep1List(); // 기관유형 1단계

		List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
		if (mdmSearchVO.getSchOrgCodeStep1() != null
				&& !mdmSearchVO.getSchOrgCodeStep1().trim().equals("")) {
			codeOrgCodeStep2List = mdmTnsrAsmblyCodeService
					.selectMdmOrgCodeStep2List(mdmSearchVO.getSchOrgCodeStep1()); // 기관유형
																					// 2단계
		}

		List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
		if (mdmSearchVO.getSchOrgCodeStep2() != null
				&& !mdmSearchVO.getSchOrgCodeStep2().trim().equals("")) {
			codeOrgCodeStep3List = mdmTnsrAsmblyCodeService
					.selectMdmOrgCodeStep3List(mdmSearchVO.getSchOrgCodeStep2()); // 기관유형
																					// 3단계
		}

		List<MdmOrgCodeVO> codeOrgSiteList = null;
		if (mdmSearchVO.getSchOrgCodeStep3() != null
				&& !mdmSearchVO.getSchOrgCodeStep3().trim().equals("")) {
			codeOrgSiteList = mdmTnsrAsmblyCodeService
					.selectMdmOrgSiteList(mdmSearchVO); // 기관명
		}

		List<MdmOutSiteVO> siteList = null;
		if (mdmSearchVO.getSchRegion() != null
				&& !mdmSearchVO.getSchRegion().trim().equals("")) {
			siteList = mdmTnsrAsmblyCodeService.selectMdmSiteList(mdmSearchVO
					.getSchRegion());
		}

		List<MdmOutSeedVO> seedList = null;
		if (!"".equals(mdmSearchVO.getSchSiteId())) {
			MdmOutSeedVO seedVO = new MdmOutSeedVO();
			seedVO.setREGION(mdmSearchVO.getSchRegion());
			seedVO.setSITEID(mdmSearchVO.getSchSiteId());
			seedList = mdmTnsrAsmblyCodeService.selectMdmSeedList(seedVO);
		}

		List<MdmDetailCodeVO> codeRKS021AllList = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS021All(); // 자료유형
		List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS025(); // 수집유형
		List<MdmDetailCodeVO> codeRKS026List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS026(); // 수집유형

		//수집기관, 등록기간 OR 연산을 위하여 추가
    	if( (!"".equals(mdmSearchVO.getSchDt1()) || !"".equals(mdmSearchVO.getSchDt2())) && (!"".equals(mdmSearchVO.getSchDt3()) || !"".equals(mdmSearchVO.getSchDt4())) ){
    		//수집, 등록 기간에 정보가 모두 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("Y");
    	}else if( "".equals(mdmSearchVO.getSchDt1()) && "".equals(mdmSearchVO.getSchDt2()) && "".equals(mdmSearchVO.getSchDt3()) && "".equals(mdmSearchVO.getSchDt4()) ){ 
    		//수집, 등록 기간에 정보가 모두 없을 경우
    		mdmSearchVO.setSchDtConditionOperators("");
    	}else{
    		//수집, 등록 기간에 정보가 한쪽만 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("N");
    	}
    	
		int regionNewsListTotCnt = 0;

		if (mdmSearchVO.getSchDflt() == null
				|| !mdmSearchVO.getSchDflt().equals("N")) { // 총 검색 건수(기본 1주일간)
			MdmCalUtil calUtil = new MdmCalUtil();
			calUtil.setDelimiter("-");
			calUtil.setDecimalFormat("00");
			String TODAY = calUtil.getToday();

			if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(TODAY);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(TODAY);
    		}
    		if(mdmSearchVO.getSchDt3() == null || "".equals(mdmSearchVO.getSchDt3())){
    			mdmSearchVO.setSchDt3(TODAY);
    		}
    		if(mdmSearchVO.getSchDt4() == null || "".equals(mdmSearchVO.getSchDt4())){
    			mdmSearchVO.setSchDt4(TODAY);
    		}
		}

		regionNewsListTotCnt = mdmRegionNewsService
				.selectMdmRegionNewsListTotCnt(mdmSearchVO); // 총 검색 건수

		MdmPaging paging = new MdmPaging();
		paging.setPagingCalc(regionNewsListTotCnt, mdmSearchVO.getPageNum(),
				mdmSearchVO.getListCnt());
		paging.setParam(mdmSearchVO);

		mdmSearchVO.setFirstRecord(paging.getFirstRecord());
		mdmSearchVO.setLastRecord(paging.getLastRecord());

		List<MdmRegionNewsVO> regionNewsList = null;
		regionNewsList = mdmRegionNewsService
				.selectMdmRegionNewsList(mdmSearchVO); // 검색 리스트

		if ("cnId".equals(mdmSearchVO.getSchKey())) {
			mdmSearchVO.setSchKw(mdmSearchVO.getSchKw().replace("'", ""));
			mdmSearchVO.setSchDt1(schDt1);
			mdmSearchVO.setSchDt2(schDt2);
			mdmSearchVO.setSchDt3(schDt3);
			mdmSearchVO.setSchDt4(schDt4);
		}

		int listStartNo = paging.getFirstRecord();

		model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
		model.addAttribute("codeRKS021AllList", codeRKS021AllList);
		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("codeRKS026List", codeRKS026List);
		model.addAttribute("regionNewsListTotCnt", regionNewsListTotCnt);
		model.addAttribute("regionNewsList", regionNewsList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", listStartNo);
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);

		String currentSearchCnList = "";
		for (MdmRegionNewsVO vo : regionNewsList) {
			currentSearchCnList += vo.getNEWS_ID() + ",";
		}

		if (!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0,
					currentSearchCnList.length() - 1);

		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());

		return "clikMng/mdm/MdmRegionNewsList";
	}

	/**
	 * 지역현안소식 목록 (엑셀 조회)
	 * 
	 * @param mdmPolicyInfoVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfoList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 목록", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/ExcelMdmRegionNewsList.do")
	public String ExcelMdmRegionNewsList(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			ModelMap model) throws Exception {

		//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("REGDATE DESC");
    	}
    	
		/*
		 * 검색 form 기본 정보 조회 S
		 */

		if (!mdmSearchVO.getSchBrtcCode().equals("")
				&& mdmSearchVO.getSchLoAsmCode().equals("")) {
			mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode() + "001");
		}

		if (mdmSearchVO.getSchKw() != null
				&& !mdmSearchVO.getSchKw().trim().equals("")) {
			if (mdmSearchVO.getSchKey().equals("")
					|| mdmSearchVO.getSchKey().equals("schTitle")) {
				mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw()); // 제목
			} else if (mdmSearchVO.getSchKey().equals("schContent")) {
				mdmSearchVO.setSchContent(mdmSearchVO.getSchKw()); // 내용
			} else if (mdmSearchVO.getSchKey().equals("cnId")) {
				mdmSearchVO.setSchKw("'" + mdmSearchVO.getSchKw() + "'"); // 문서번호
				mdmSearchVO.setSchDt1("");
				mdmSearchVO.setSchDt2("");
				mdmSearchVO.setSchDt3("");
				mdmSearchVO.setSchDt4("");
			}
		}

		List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService
				.selectMdmOrgCodeStep1List(); // 기관유형 1단계

		List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
		if (mdmSearchVO.getSchOrgCodeStep1() != null
				&& !mdmSearchVO.getSchOrgCodeStep1().trim().equals("")) {
			codeOrgCodeStep2List = mdmTnsrAsmblyCodeService
					.selectMdmOrgCodeStep2List(mdmSearchVO.getSchOrgCodeStep1()); // 기관유형
																					// 2단계
		}

		List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
		if (mdmSearchVO.getSchOrgCodeStep2() != null
				&& !mdmSearchVO.getSchOrgCodeStep2().trim().equals("")) {
			codeOrgCodeStep3List = mdmTnsrAsmblyCodeService
					.selectMdmOrgCodeStep3List(mdmSearchVO.getSchOrgCodeStep2()); // 기관유형
																					// 3단계
		}

		List<MdmOrgCodeVO> codeOrgSiteList = null;
		if (mdmSearchVO.getSchOrgCodeStep3() != null
				&& !mdmSearchVO.getSchOrgCodeStep3().trim().equals("")) {
			codeOrgSiteList = mdmTnsrAsmblyCodeService
					.selectMdmOrgSiteList(mdmSearchVO); // 기관명
		}

		List<MdmOutSiteVO> siteList = null;
		if (mdmSearchVO.getSchRegion() != null
				&& !mdmSearchVO.getSchRegion().trim().equals("")) {
			siteList = mdmTnsrAsmblyCodeService.selectMdmSiteList(mdmSearchVO
					.getSchRegion());
		}

		List<MdmOutSeedVO> seedList = null;
		if (!"".equals(mdmSearchVO.getSchSiteId())) {
			MdmOutSeedVO seedVO = new MdmOutSeedVO();
			seedVO.setREGION(mdmSearchVO.getSchRegion());
			seedVO.setSITEID(mdmSearchVO.getSchSiteId());
			seedList = mdmTnsrAsmblyCodeService.selectMdmSeedList(seedVO);
		}

		List<MdmDetailCodeVO> codeRKS021AllList = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS021All(); // 자료유형
		List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS025(); // 수집유형
		List<MdmDetailCodeVO> codeRKS026List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS026(); // 수집유형

		/*
		 * 검색 form 기본 정보 조회 E
		 */

		// 엑셀 조회 설정
		MdmSearchVO mdmSearchVOTemp = new MdmSearchVO();
		mdmSearchVOTemp.setSchKey("cnId");

		// 조회할 OUTBBS_CN 정보 생성
		String outbbsList = MdmExcelUtil.getOutbbsCn(multiRequest);

		mdmSearchVOTemp.setSchKw(outbbsList);

		// 총 검색 건수
		int regionNewsListTotCnt = mdmRegionNewsService
				.selectMdmRegionNewsListTotCnt(mdmSearchVOTemp);

		// 엑셀조회 최대 카운트
		final int maxExcelSearchCount = Integer.parseInt(MdmProperties
				.getProperty("Globals.mdm.max_excel_search_count"));

		// 페이징 되지 않도록 최대 100까지 설정
		regionNewsListTotCnt = regionNewsListTotCnt > maxExcelSearchCount ? maxExcelSearchCount
				: regionNewsListTotCnt;

		// 최대 검색 결과를 제한 처리
		mdmSearchVO.setLastRecord(maxExcelSearchCount);
		mdmSearchVO.setListCnt(maxExcelSearchCount);
		mdmSearchVOTemp.setLastRecord(maxExcelSearchCount);
		mdmSearchVOTemp.setListCnt(maxExcelSearchCount);

		MdmPaging paging = new MdmPaging();
		paging.setPagingCalc(regionNewsListTotCnt, mdmSearchVO.getPageNum(),
				mdmSearchVO.getListCnt());
		paging.setParam(mdmSearchVO);

		List<MdmRegionNewsVO> regionNewsList = mdmRegionNewsService
				.selectMdmRegionNewsList(mdmSearchVOTemp); // 검색 리스트

		model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
		model.addAttribute("codeRKS021AllList", codeRKS021AllList);
		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("codeRKS026List", codeRKS026List);
		model.addAttribute("regionNewsListTotCnt", regionNewsListTotCnt);
		model.addAttribute("regionNewsList", regionNewsList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("isExcelSearch", "Y");
		model.addAttribute("excelSearchCnList", outbbsList);

		model.addAttribute("cnList", outbbsList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());

		return "clikMng/mdm/MdmRegionNewsList";
	}

	/**
	 * 지역현안소식 내용보기
	 * 
	 * @param mdmPolicyInfoVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfoView"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 내용보기", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsView.do")
	public String MdmRegionNewsView(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			ModelMap model) throws Exception {

		MdmRegionNewsVO regionNewsVO = mdmRegionNewsService
				.selectMdmRegionNewsView(mdmSearchVO.getExtId());
		List<MdmTnpFileDetailVO> regionNewsFileList = mdmRegionNewsService
				.selectMdmRegionNewsFileList(regionNewsVO.getATCH_FILE_ID());

		List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS025(); // 수집유형
		List<MdmDetailCodeVO> codeRKS026List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS026(); // 수집유형

		String cDate = regionNewsVO.getINDT();
		if (cDate != null && cDate.length() > 10) {
			cDate = cDate.substring(0, 4) + "-" + cDate.substring(5, 7) + "-"
					+ cDate.substring(8, 10);
			regionNewsVO.setINDT(cDate);
		}

		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("codeRKS026List", codeRKS026List);
		model.addAttribute("regionNewsFileList", regionNewsFileList);
		model.addAttribute("regionNewsVO", regionNewsVO);
		model.addAttribute("mdmSearchVO", mdmSearchVO);

		return "clikMng/mdm/MdmRegionNewsView";

	}

	/**
	 * 지역현안소식 내용보기 1
	 * 
	 * @param mdmPolicyInfoVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfoView1"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 내용보기", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsView1.do")
	public String MdmRegionNewsView1(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			ModelMap model, HttpServletRequest request) throws Exception {

		// 상세화면 이전다음 관련 조회 S
		String cnList = "";
		if ("Y".equals(mdmSearchVO.getIsPrevNextPaging())) {
			// 이전 다음 CN 정보 조회
			int regionNewsListTotCnt = mdmRegionNewsService
					.selectMdmRegionNewsListTotCnt(mdmSearchVO); // 총 검색 건수

			MdmPaging paging = new MdmPaging();
			paging.setPagingCalc(regionNewsListTotCnt,
					mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
			paging.setParam(mdmSearchVO);

			mdmSearchVO.setFirstRecord(paging.getFirstRecord());
			mdmSearchVO.setLastRecord(paging.getLastRecord());

			String tempCn = mdmSearchVO.getExtId();
			mdmSearchVO.setExtId("");

			List<MdmRegionNewsVO> regionNewsList = null;
			regionNewsList = mdmRegionNewsService
					.selectMdmRegionNewsList(mdmSearchVO); // 검색 리스트

			mdmSearchVO.setExtId(tempCn);

			for (MdmRegionNewsVO vo : regionNewsList) {
				cnList += vo.getNEWS_ID() + ",";
			}
			
			cnList = cnList.substring(0, cnList.length()-1);
			
			// 이전다음 페이징된 경우 해당하는 데이터 CN 설정
			if ("".equals(mdmSearchVO.getExtId()) && "next".equals(mdmSearchVO.getPrevNextGubun())) {
				mdmSearchVO.setExtId(regionNewsList.get(0).getNEWS_ID());
			} else if ("".equals(mdmSearchVO.getExtId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())) {
				mdmSearchVO.setExtId(regionNewsList.get(regionNewsList.size() - 1).getNEWS_ID());
			}

			model.addAttribute("cnList", cnList);
			model.addAttribute("EndPage", paging.getTotalPages());
		} else {
			model.addAttribute("cnList", request.getParameter("cnList"));
			model.addAttribute("EndPage", request.getParameter("EndPage"));
		}
		// 상세화면 이전다음 관련 조회 E

		MdmRegionNewsVO regionNewsVO = mdmRegionNewsService
				.selectMdmRegionNewsView(mdmSearchVO.getExtId());
		List<MdmTnpFileDetailVO> regionNewsFileList = mdmRegionNewsService.selectMdmRegionNewsFileList(regionNewsVO.getATCH_FILE_ID());

		List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025(); // 수집유형
		List<MdmDetailCodeVO> codeRKS026List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS026(); // 수집유형

		String cDate = regionNewsVO.getINDT();
		if (cDate != null && cDate.length() > 10) {
			cDate = cDate.substring(0, 4) + "-" + cDate.substring(5, 7) + "-"
					+ cDate.substring(8, 10);
			regionNewsVO.setINDT(cDate);
		}

		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("codeRKS026List", codeRKS026List);
		model.addAttribute("regionNewsFileList", regionNewsFileList);
		model.addAttribute("regionNewsVO", regionNewsVO);
		model.addAttribute("mdmSearchVO", mdmSearchVO);

		return "clikMng/mdm/MdmRegionNewsView2";

	}

	/**
	 * 지역현안소식 내용보기 2
	 * 
	 * @param mdmPolicyInfoVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfoView2"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 내용보기", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsView2.do")
	public String MdmRegionNewsView2(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			ModelMap model) throws Exception {

		MdmRegionNewsVO regionNewsVO = mdmRegionNewsService
				.selectMdmRegionNewsView(mdmSearchVO.getExtId());
		List<MdmTnpFileDetailVO> regionNewsFileList = mdmRegionNewsService
				.selectMdmRegionNewsFileList(regionNewsVO.getATCH_FILE_ID());

		List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS025(); // 수집유형
		List<MdmDetailCodeVO> codeRKS026List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS026(); // 수집유형

		String cDate = regionNewsVO.getINDT();
		if (cDate != null && cDate.length() > 10) {
			cDate = cDate.substring(0, 4) + "-" + cDate.substring(5, 7) + "-"
					+ cDate.substring(8, 10);
			regionNewsVO.setINDT(cDate);
		}

		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("codeRKS026List", codeRKS026List);
		model.addAttribute("regionNewsFileList", regionNewsFileList);
		model.addAttribute("regionNewsVO", regionNewsVO);
		model.addAttribute("mdmSearchVO", mdmSearchVO);

		return "clikMng/mdm/MdmRegionNewsView2";

	}

	/**
	 * 지역현안 뉴스 게시 수정
	 * 
	 * @param mdmRegionNewsVO
	 * @param model
	 * @return "/mdm/MdmRegionNewsIsView"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 목록", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsIsView.do")
	public String MdmRegionNewsIsView(
			@ModelAttribute("mdmRegionNewsVO") MdmRegionNewsVO mdmRegionNewsVO,
			ModelMap model) throws Exception {
		String arr[];
		String isView[];

		MdmIsViewVO isViewVO = null;

		isView = mdmRegionNewsVO.getISVIEW().split("&");
		for (int i = 0; i < isView.length; i++) {
			isViewVO = new MdmIsViewVO();
			arr = isView[i].split("=");
			isViewVO.setUid(arr[0]);
			isViewVO.setIsview(arr[1]);
			mdmRegionNewsService.updateMdmRegionNewsIsView(isViewVO);
		}

		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
		return "clikMng/mdm/MdmIsView";

	}

	/**
	 * 지역현안 뉴스 게시 삭제
	 * 
	 * @param mdmPolicyInfoVO
	 * @param model
	 * @return "/mdm/MdmPolicyInfoDeleteChk"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 게시 삭제", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsDeleteChk.do")
	public String MdmRegionNewsDeleteChk(
			@ModelAttribute("mdmRegionNewsVO") MdmRegionNewsVO mdmRegionNewsVO,
			ModelMap model) throws Exception {
		String arr[];
		String isView[];

		MdmIsViewVO isViewVO = null;
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		isView = mdmRegionNewsVO.getISVIEW().split("&");
		for (int i = 0; i < isView.length; i++) {
			isViewVO = new MdmIsViewVO();
			arr = isView[i].split("=");
			isViewVO.setUid(arr[0]);
			isViewVO.setIsview(arr[1]);
			isViewVO.setLAST_UPDUSR_ID(user.getMngrId());
			mdmRegionNewsService.deleteMdmRegionNewsChk(isViewVO);
		}

		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
		return "clikMng/mdm/MdmIsView";

	}

	/**
	 * 지역현안 뉴스 등록폼
	 * 
	 * @param mdmPolicyVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfo"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 등록폼", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsForm.do")
	public String MdmRegionNewsForm(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			ModelMap model) throws Exception {
		List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS025(); // 수집유형
		List<MdmDetailCodeVO> codeRKS026List = mdmTnsrAsmblyCodeService
				.selectMdmDetailCodeRKS026(); // 수집유형

		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("codeRKS026List", codeRKS026List);

		return "clikMng/mdm/MdmRegionNewsForm";
	}

	/**
	 * 지역현안 뉴스 등록
	 * 
	 * @param mdmMemberVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfo"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 등록", order = 680, gid = 50)
	@RequestMapping("/mdm/MdmRegionNewsRegist.do")
	public String insertMdmRegionNews(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("fileVO") MdmFileVO fileVO,
			@ModelAttribute("mdmRegionNewsVO") MdmRegionNewsVO mdmRegionNewsVO,
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			BindingResult bindingResult, SessionStatus status, ModelMap model)
			throws Exception {

		List<MdmFileVO> result = null;
		MdmFileVO fvo = null;
		MdmCalUtil calUtil = new MdmCalUtil();
		MdmStrUtil strUtil = new MdmStrUtil();
		MdmTnpFileVO mdmTnpFileVO = new MdmTnpFileVO();
		MdmTnpFileDetailVO mdmTnpFileDetailVO = new MdmTnpFileDetailVO();

		int uid = 0;
		boolean flg = false;
		String NEWS_ID = "";
		String ATCH_FILE_ID = "";

		try {
			calUtil.setDecimalFormat("00");
			uid = mdmRegionNewsService.selectMdmOutBbsSeq();
			NEWS_ID = "CLIKM" + calUtil.getYear()
					+ strUtil.getDecimalFormat("0000000000", uid);
			mdmRegionNewsVO.setNEWS_ID(NEWS_ID);

			calUtil.setDelimiter(".");
			mdmRegionNewsVO
					.setINDT(mdmRegionNewsVO.getINDT().replace("-", "/"));
			mdmRegionNewsVO.setREGDATE(calUtil.getTodayWithTime());

			String REGION_NM = mdmTnsrAsmblyCodeService
					.selectMdmDetailCodeRKS025ByCodeNm(mdmRegionNewsVO
							.getREGION()); // 수집유형
			String SEED_NM = mdmTnsrAsmblyCodeService
					.selectMdmDetailCodeRKS026ByCodeNm(Integer
							.toString(mdmRegionNewsVO.getSEED_ID())); // 수집유형
			mdmRegionNewsVO.setSEED_NM(SEED_NM);
			mdmRegionNewsVO.setREGION_NM(REGION_NM);

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "clik009",
						calUtil.getYear() + "/" + mdmRegionNewsVO.getSEED_ID()
								+ "/");

				if (result.size() > 0) {
					calUtil.setDelimiter("-");
					flg = true;
					fvo = new MdmFileVO();
					fvo = result.get(0);

					uid = mdmRegionNewsService.selectMdmOutBbsFileSeq();
					ATCH_FILE_ID = "MNL_"
							+ strUtil.getDecimalFormat("0000000000000000", uid);

					mdmTnpFileVO.setATCH_FILE_ID(ATCH_FILE_ID);
					mdmTnpFileVO.setCREAT_DT(calUtil.getToday());
					mdmTnpFileVO.setUSE_AT("Y");

					mdmTnpFileDetailVO.setATCH_FILE_ID(ATCH_FILE_ID);
					mdmTnpFileDetailVO.setFILE_SN(0);
					mdmTnpFileDetailVO.setFILE_STRE_COURS(fvo
							.getFileStreCours());
					mdmTnpFileDetailVO.setSTRE_FILE_NM(fvo.getStreFileNm());
					mdmTnpFileDetailVO.setORIGNL_FILE_NM(fvo.getOrignlFileNm());
					mdmTnpFileDetailVO.setFILE_EXTSN(fvo.getFileExtsn());
					mdmTnpFileDetailVO.setFILE_SIZE(Integer.parseInt(fvo
							.getFileMg()));
				}
			}
			if (flg == true) {
				mdmRegionNewsVO.setARTICLE_ID(ATCH_FILE_ID);
				mdmRegionNewsVO.setATCH_FILE_ID(NEWS_ID);
			}
			mdmRegionNewsService.insertMdmRegionNews(mdmRegionNewsVO);
			if (flg == true) {
				mdmRegionNewsService.insertMdmRegionNewsFile(mdmTnpFileVO);
				mdmRegionNewsService
						.insertMdmRegionNewsFileDetail(mdmTnpFileDetailVO);
			}
		} catch (Exception e) {
		}

		model.addAttribute("mdmSearchVO", mdmSearchVO);

		return "redirect:/mdm/MdmRegionNewsList.do";
	}

	/**
	 * 지역현안 뉴스 수정
	 * 
	 * @param mdmMemberVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfo"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 수정", order = 680, gid = 50)
	@RequestMapping("/mdm/MdmRegionNewsUpdate.do")
	public String updateMdmRegionNews(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("fileVO") MdmFileVO fileVO,
			@ModelAttribute("mdmRegionNewsVO") MdmRegionNewsVO mdmRegionNewsVO,
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			BindingResult bindingResult, SessionStatus status, ModelMap model)
			throws Exception {

		List<MdmFileVO> result = null;
		MdmFileVO fvo = null;
		MdmCalUtil calUtil = new MdmCalUtil();
		MdmStrUtil strUtil = new MdmStrUtil();
		MdmTnpFileVO mdmTnpFileVO = new MdmTnpFileVO();
		MdmTnpFileDetailVO mdmTnpFileDetailVO = new MdmTnpFileDetailVO();

		int uid = 0;
		boolean flg = false;
		String ATCH_FILE_ID = "";

		try {
			calUtil.setDecimalFormat("00");
			calUtil.setDelimiter(".");
			mdmRegionNewsVO
					.setINDT(mdmRegionNewsVO.getINDT().replace("-", "/"));
			mdmRegionNewsVO.setUPDT(calUtil.getTodayWithTime());

			String REGION_NM = mdmTnsrAsmblyCodeService
					.selectMdmDetailCodeRKS025ByCodeNm(mdmRegionNewsVO
							.getREGION()); // 수집유형
			String SEED_NM = mdmTnsrAsmblyCodeService
					.selectMdmDetailCodeRKS026ByCodeNm(Integer
							.toString(mdmRegionNewsVO.getSEED_ID())); // 수집유형
			mdmRegionNewsVO.setSEED_NM(SEED_NM);
			mdmRegionNewsVO.setREGION_NM(REGION_NM);

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "news", calUtil.getYear()
						+ "/" + mdmRegionNewsVO.getSEED_ID() + "/");

				if (result.size() > 0) {
					calUtil.setDelimiter("-");
					flg = true;
					fvo = new MdmFileVO();
					fvo = result.get(0);

					uid = mdmRegionNewsService.selectMdmOutBbsFileSeq();

					if (mdmRegionNewsVO.getATCH_FILE_ID() == null
							|| mdmRegionNewsVO.getATCH_FILE_ID().equals("")) {
						ATCH_FILE_ID = "MNL_"
								+ strUtil.getDecimalFormat("0000000000000000",
										uid);
					} else {
						ATCH_FILE_ID = mdmRegionNewsVO.getATCH_FILE_ID();
					}

					mdmTnpFileVO.setATCH_FILE_ID(ATCH_FILE_ID);
					mdmTnpFileVO.setCREAT_DT(calUtil.getToday());
					mdmTnpFileVO.setUSE_AT("Y");

					mdmTnpFileDetailVO.setATCH_FILE_ID(ATCH_FILE_ID);
					mdmTnpFileDetailVO.setFILE_SN(0);
					mdmTnpFileDetailVO.setFILE_STRE_COURS(fvo
							.getFileStreCours());
					mdmTnpFileDetailVO.setSTRE_FILE_NM(fvo.getStreFileNm());
					mdmTnpFileDetailVO.setORIGNL_FILE_NM(fvo.getOrignlFileNm());
					mdmTnpFileDetailVO.setFILE_EXTSN(fvo.getFileExtsn());
					mdmTnpFileDetailVO.setFILE_SIZE(Integer.parseInt(fvo
							.getFileMg()));
					mdmTnpFileDetailVO.setFILE_SN(mdmRegionNewsService
							.selectMdmOutNewsFileSn(ATCH_FILE_ID));
				}
			}
			if (flg == true) {
				mdmRegionNewsVO.setARTICLE_ID(ATCH_FILE_ID);
				mdmRegionNewsVO.setATCH_FILE_ID(ATCH_FILE_ID);
			}

			LoginVO user = (LoginVO) EgovUserDetailsHelper
					.getAuthenticatedUser();
			mdmRegionNewsVO.setLAST_UPDUSR_ID(user.getMngrId());

			mdmRegionNewsService.updateMdmRegionNews(mdmRegionNewsVO);

			if (flg == true) {
				if (mdmRegionNewsService
						.selectMdmRegionNewsFileExist(mdmRegionNewsVO
								.getATCH_FILE_ID()) == 0) {
					mdmRegionNewsService.insertMdmRegionNewsFile(mdmTnpFileVO);
				}
				mdmRegionNewsService
						.insertMdmRegionNewsFileDetail(mdmTnpFileDetailVO);
			}
		} catch (Exception e) {
			model.addAttribute(
					"return_url",
					"/mdm/MdmRegionNewsView1.do?extId="
							+ mdmRegionNewsVO.getNEWS_ID());
			model.addAttribute("message", e.getMessage());
		}
		model.addAttribute("return_url", "/mdm/MdmRegionNewsView1.do?extId="
				+ mdmRegionNewsVO.getNEWS_ID());
		model.addAttribute("message", "정상처리되었습니다.");
		model.addAttribute("menuName", "지역현안소식");
		model.addAttribute("cnId", mdmRegionNewsVO.getNEWS_ID());
		model.addAttribute("EndPage", multiRequest.getParameter("EndPage"));
		model.addAttribute("cnList", multiRequest.getParameter("cnList"));
		model.addAttribute("sort", multiRequest.getParameter("sort"));
		model.addAttribute("work", "수정");
		model.addAttribute("work", "수정");

		// return "redirect:/mdm/MdmRegionNewsList.do";
		return "clikMng/mdm/MdmResult";
	}

	/**
	 * 지역현안 뉴스 삭제
	 * 
	 * @param mdmPolicyVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfo"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 삭제", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsDelete.do")
	public String MdmRegionNewsDelete(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		if (request.getParameter("NEWS_ID") != null) {
			MdmRegionNewsVO mdmRegionNewsVO = new MdmRegionNewsVO();
			LoginVO user = (LoginVO) EgovUserDetailsHelper
					.getAuthenticatedUser();
			mdmRegionNewsVO.setLAST_UPDUSR_ID(user.getMngrId());
			mdmRegionNewsVO.setNEWS_ID(request.getParameter("NEWS_ID"));
			mdmRegionNewsService.deleteMdmRegionNews(mdmRegionNewsVO);
		}

		model.addAttribute("menuName", "지역현안소식");
		model.addAttribute("cnId", mdmSearchVO.getCnId());
		model.addAttribute("work", "삭제");

		// return "redirect:/mdm/MdmRegionNewsList.do";
		return "clikMng/mdm/MdmResult";
	}

	/**
	 * 지역현안 뉴스 첨부파일 삭제
	 * 
	 * @param mdmPolicyVO
	 * @param model
	 * @return "/Mdm/MdmPolicyInfo"
	 * @throws Exception
	 */
	@IncludedInfo(name = "메타데이터 첨부파일 삭제", order = 680, gid = 50)
	@RequestMapping(value = "/mdm/MdmRegionNewsFileDelete.do")
	public String MdmRegionNewsFileDelete(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		String msg = "{\"rst\":\"no\"}";

		if (request.getParameter("ATCH_FILE_ID") != null) {
			MdmTnpFileDetailVO vo = new MdmTnpFileDetailVO();
			vo.setATCH_FILE_ID(request.getParameter("ATCH_FILE_ID"));
			vo.setFILE_SN(Integer.parseInt(request.getParameter("FILE_SN")));
			mdmRegionNewsService.deleteMdmRegionNewsFile(vo);
			msg = "{\"rst\":\"yes\"}";
		}

		model.addAttribute("msg", msg);
		return "clikMng/mdm/MdmIsView";

	}

	/**
	 * 지역현안 뉴스 - 최근 수집일
	 * 
	 * @param mdmTnsrasmblyBiVO
	 * @param model
	 * @return "/mdm/MdmBillIsView"
	 * @throws Exception
	 */
	public String MdmRegionNewsMaxRegDate() throws Exception {
		MdmStrUtil strUtil = new MdmStrUtil();
		MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDecimalFormat("00");
		calUtil.setDelimiter("-");

		String dt2 = mdmRegionNewsService.selectMdmRegionNewsMaxRegDate(calUtil
				.getToday());
		dt2 = strUtil.getStringFormat8("-", dt2);

		return dt2;
	}

	/**
	 * 지역현안 뉴스 - 최근 수집일
	 * 
	 * @param mdmTnsrasmblyBiVO
	 * @param model
	 * @return "/mdm/MdmBillIsView"
	 * @throws Exception
	 */
	public String MdmRegionNewsRegDate(String dt2) throws Exception {
		MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		String[] arr = dt2.split("-");

		return calUtil
				.getDateBeforeDt(
						calUtil.getUnixTime(Integer.parseInt(arr[0]),
								Integer.parseInt(arr[1]),
								Integer.parseInt(arr[2])), -7);
	}

	/**
	 * 지역현안소식 엑셀파일로 다운로드 한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "/sit/log/LogMngList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/mdm/selectMdmRegionNewsExcel.do")
	public ModelAndView selectMdmRegionNewsExcel(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			@SuppressWarnings("rawtypes") Map commandMap, ModelMap model)
			throws Exception {

		if (!mdmSearchVO.getSchBrtcCode().equals("")
				&& mdmSearchVO.getSchLoAsmCode().equals("")) {
			mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode() + "001");
		}

		if (mdmSearchVO.getSchKw() != null
				&& !mdmSearchVO.getSchKw().trim().equals("")) {
			if (mdmSearchVO.getSchKey().equals("")
					|| mdmSearchVO.getSchKey().equals("schTitle")) {
				mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw()); // 제목
			} else if (mdmSearchVO.getSchKey().equals("schContent")) {
				mdmSearchVO.setSchContent(mdmSearchVO.getSchKw()); // 내용
			} else if (mdmSearchVO.getSchKey().equals("cnId")) {
				mdmSearchVO.setCnId(mdmSearchVO.getSchKw()); // 문서번호
			}
		}

		if (mdmSearchVO.getSchDflt() == null
				|| !mdmSearchVO.getSchDflt().equals("N")) { // 총 검색 건수(기본 1주일간)
			MdmCalUtil calUtil = new MdmCalUtil();
			calUtil.setDelimiter("-");
			calUtil.setDecimalFormat("00");
			String TODAY = calUtil.getToday();

			if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(TODAY);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(TODAY);
    		}
    		if(mdmSearchVO.getSchDt3() == null || "".equals(mdmSearchVO.getSchDt3())){
    			mdmSearchVO.setSchDt3(TODAY);
    		}
    		if(mdmSearchVO.getSchDt4() == null || "".equals(mdmSearchVO.getSchDt4())){
    			mdmSearchVO.setSchDt4(TODAY);
    		}
		}

		// 총 검색 건수
		int regionNewsListTotCnt = mdmRegionNewsService
				.selectMdmRegionNewsListTotCnt(mdmSearchVO);

		// 엑셀조회 후 다운로드할 경우 정보설정
		if (commandMap.get("isExcelSearch") != null
				&& commandMap.get("isExcelSearch").equals("Y")) {
			mdmSearchVO.setSchDtConditionOperators("");
			mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			mdmSearchVO.setSchDt3("");
			mdmSearchVO.setSchDt4("");
			mdmSearchVO.setSchKw(commandMap.get("excelSearchCnList").toString()
					.replaceAll("&apos;", "'"));
			regionNewsListTotCnt = Integer.parseInt(MdmProperties
					.getProperty("Globals.mdm.max_excel_search_count"));
		}

		mdmSearchVO.setLastRecord(regionNewsListTotCnt);

		// 검색 리스트
		List<MdmRegionNewsVO> regionNewsList = mdmRegionNewsService
				.selectMdmRegionNewsList(mdmSearchVO);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
		map.put("searchKeyword", commandMap.get("searchKeyword") == null ? ""
				: (String) commandMap.get("searchKeyword"));
		map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? ""
				: (String) commandMap.get("selSearchGubun"));

		map.put("resultList", regionNewsList);
		map.put("resultCnt", Integer.toString(regionNewsListTotCnt));

		return new ModelAndView("MdmRegionNewsExcel", map);
	}

	/**
	 * 지역현안소식 Text파일로 다운로드 한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "/sit/log/LogMngList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/mdm/selectMdmRegionNewsText.do")
	public ModelAndView selectMdmRegionNewsText(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
			@SuppressWarnings("rawtypes") Map commandMap, ModelMap model)
			throws Exception {

		if (!mdmSearchVO.getSchBrtcCode().equals("")
				&& mdmSearchVO.getSchLoAsmCode().equals("")) {
			mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode() + "001");
		}

		if (mdmSearchVO.getSchKw() != null
				&& !mdmSearchVO.getSchKw().trim().equals("")) {
			if (mdmSearchVO.getSchKey().equals("")
					|| mdmSearchVO.getSchKey().equals("schTitle")) {
				mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw()); // 제목
			} else if (mdmSearchVO.getSchKey().equals("schContent")) {
				mdmSearchVO.setSchContent(mdmSearchVO.getSchKw()); // 내용
			} else if (mdmSearchVO.getSchKey().equals("cnId")) {
				mdmSearchVO.setCnId(mdmSearchVO.getSchKw()); // 문서번호
			}
		}

		if (mdmSearchVO.getSchDflt() == null
				|| !mdmSearchVO.getSchDflt().equals("N")) { // 총 검색 건수(기본 1주일간)
			MdmCalUtil calUtil = new MdmCalUtil();
			calUtil.setDelimiter("-");
			calUtil.setDecimalFormat("00");
			String TODAY = calUtil.getToday();

			if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(TODAY);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(TODAY);
    		}
    		if(mdmSearchVO.getSchDt3() == null || "".equals(mdmSearchVO.getSchDt3())){
    			mdmSearchVO.setSchDt3(TODAY);
    		}
    		if(mdmSearchVO.getSchDt4() == null || "".equals(mdmSearchVO.getSchDt4())){
    			mdmSearchVO.setSchDt4(TODAY);
    		}
		}

		// 총 검색 건수
		int regionNewsListTotCnt = mdmRegionNewsService
				.selectMdmRegionNewsListTotCnt(mdmSearchVO);

		// 엑셀조회 후 다운로드할 경우 정보설정
		if (commandMap.get("isExcelSearch") != null
				&& commandMap.get("isExcelSearch").equals("Y")) {
			mdmSearchVO.setSchDtConditionOperators("");
			mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			mdmSearchVO.setSchDt3("");
			mdmSearchVO.setSchDt4("");
			mdmSearchVO.setSchKw(commandMap.get("excelSearchCnList").toString()
					.replaceAll("&apos;", "'"));
			regionNewsListTotCnt = Integer.parseInt(MdmProperties
					.getProperty("Globals.mdm.max_excel_search_count"));
		}

		mdmSearchVO.setLastRecord(regionNewsListTotCnt);

		// 검색 리스트
		List<MdmRegionNewsVO> regionNewsList = mdmRegionNewsService
				.selectMdmRegionNewsList(mdmSearchVO);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
		map.put("searchKeyword", commandMap.get("searchKeyword") == null ? ""
				: (String) commandMap.get("searchKeyword"));
		map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? ""
				: (String) commandMap.get("selSearchGubun"));

		map.put("resultList", regionNewsList);
		map.put("resultCnt", Integer.toString(regionNewsListTotCnt));

		return new ModelAndView("MdmRegionNewsText", map);
	}
}
