package clikmng.nanet.go.kr.minutesviewer;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import clikmng.nanet.go.kr.minutesviewer.service.MdmTnsrAsmblyMintsViewService;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewMainVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewSpkrVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewApndxVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewBillVO;

import clikmng.nanet.go.kr.minutesviewer.utl.MdmHtmlEnCodeUtil;
/**
 * 
 * 회의록 뷰어의 컨트롤러 클래스
 * @author 강희준(ngluka@gmail.com)
 * @since 2014.10.29
 * @version 1.3
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		수정자			수정내용
 *  -------		---			--------------------
 *   014.11.13	강희준			최초 생성
 *   014.12.12	강희준			일부 뷰페이지 오류 수정
 *   014.12.29	강희준			전면 수정 (주석부분 구 버전)
 *   015.02.16	강희준			폴더이동
 *
 * </pre>
 */

@Controller
public class minutesviewController {
    @Resource(name = "MdmTnsrAsmblyMintsViewService")
    private MdmTnsrAsmblyMintsViewService mdmTnsrAsmblyMintsViewService;
    
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(minutesviewController.class);
	
	/*
	 * TODO : 회의록 뷰어 컨트롤러 뷰페이지
	 */
	@RequestMapping(value = "/minutesviewer/MinutesHtmlView.do", method = RequestMethod.GET)
	public String minutesViewHtmlPage(@ModelAttribute("mdmTnsrAsmblyMintsViewMainVO") MdmTnsrAsmblyMintsViewMainVO mdmTnsrAsmblyMintsViewMainVO, ModelMap model) throws Exception
	{
		MdmHtmlEnCodeUtil htmlencode = new MdmHtmlEnCodeUtil();
		
		MdmTnsrAsmblyMintsViewMainVO mintsviewVO = mdmTnsrAsmblyMintsViewService.selectMdmMinutesViewMainInfo(mdmTnsrAsmblyMintsViewMainVO);
		List<MdmTnsrAsmblyMintsViewSpkrVO> speakerVO = mdmTnsrAsmblyMintsViewService.selectMdmMinutesViewSpeakerInfo(mdmTnsrAsmblyMintsViewMainVO);
		List<MdmTnsrAsmblyMintsViewApndxVO> appendixVO = mdmTnsrAsmblyMintsViewService.selectMdmMinutesViewAppendixInfo(mdmTnsrAsmblyMintsViewMainVO);
		List<MdmTnsrAsmblyMintsViewBillVO> billVO = mdmTnsrAsmblyMintsViewService.selectMdmMinutesViewBillInfo(mdmTnsrAsmblyMintsViewMainVO);
		
		model.addAttribute("ViewDocID",mintsviewVO.getMINTS_CN());
		model.addAttribute("ViewConcilCode",mintsviewVO.getRASMBLY_ID());
		model.addAttribute("ViewConcilName",mintsviewVO.getRASMBLY_NM());
		model.addAttribute("ViewCmt",mintsviewVO.getMTGNM_ID());
		model.addAttribute("ViewCmtName",mintsviewVO.getMTGNM_NM());
		model.addAttribute("ViewDaesu",mintsviewVO.getRASMBLY_NUMPR());
		model.addAttribute("ViewTh",mintsviewVO.getRASMBLY_SESN());
		model.addAttribute("ViewCha",mintsviewVO.getMINTS_ODR());
		model.addAttribute("ViewTitle","");
		model.addAttribute("ViewText",htmlencode.HtmlSpecialCharControl(mintsviewVO.getMINTS_VIEW()));
		model.addAttribute("ViewOrginFileLoc",mintsviewVO.getORGINL_FILE_URL());
		model.addAttribute("ViewOrginFilePath",mintsviewVO.getORGINL_FILE_PATH());
		model.addAttribute("ViewOrginFileName",mintsviewVO.getORGINL_FILE_NM());
		
		model.addAttribute("Appendix",  appendixVO);
		model.addAttribute("Bill",	    billVO);
		model.addAttribute("Speaker",   speakerVO);
		
		
		return "clikMng/minutesviewer/htmlview";
	}
	
	/*
	 * TODO : 회의록 뷰어 컨트롤러 프린트 페이지
	 */
	@RequestMapping(value = "/minutesviewer/MinutesPrintView.do", method = RequestMethod.GET)
	public String minutesViewPrintPage(@ModelAttribute("mdmTnsrAsmblyMintsViewMainVO") MdmTnsrAsmblyMintsViewMainVO mdmTnsrAsmblyMintsViewMainVO, ModelMap model) throws Exception
	{
		MdmHtmlEnCodeUtil htmlencode = new MdmHtmlEnCodeUtil();
		
		MdmTnsrAsmblyMintsViewMainVO mintsviewVO = mdmTnsrAsmblyMintsViewService.selectMdmMinutesViewMainInfo(mdmTnsrAsmblyMintsViewMainVO);
		
		model.addAttribute("ViewDocID",mintsviewVO.getMINTS_CN());
		model.addAttribute("ViewConcilCode",mintsviewVO.getRASMBLY_ID());
		model.addAttribute("ViewConcilName",mintsviewVO.getRASMBLY_NM());
		model.addAttribute("ViewCmt",mintsviewVO.getMTGNM_ID());
		model.addAttribute("ViewCmtName",mintsviewVO.getMTGNM_NM());
		model.addAttribute("ViewDaesu",mintsviewVO.getRASMBLY_NUMPR());
		model.addAttribute("ViewTh",mintsviewVO.getRASMBLY_SESN());
		model.addAttribute("ViewCha",mintsviewVO.getMINTS_ODR());
		model.addAttribute("ViewTitle","");
		model.addAttribute("ViewText",htmlencode.HtmlSpecialCharControl(mintsviewVO.getMINTS_VIEW()));
		model.addAttribute("ViewOrginFileLoc",mintsviewVO.getORGINL_FILE_URL());
		model.addAttribute("ViewOrginFilePath",mintsviewVO.getORGINL_FILE_PATH());
		model.addAttribute("ViewOrginFileName",mintsviewVO.getORGINL_FILE_NM());
		
			
		return "clikMng/minutesviewer/printview";
	}
}
