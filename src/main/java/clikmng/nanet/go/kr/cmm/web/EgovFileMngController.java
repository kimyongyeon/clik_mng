package clikmng.nanet.go.kr.cmm.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
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
 *
 * </pre>
 */
@Controller
public class EgovFileMngController {
	
	 
	 

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    //private static final Logger LOG = Logger.getLogger(EgovFileMngController.class.getName());

    /**
     * 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfs.do")
    public String selectFileInfs(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap, ModelMap model) throws Exception {
	String atchFileId = (String)commandMap.get("param_atchFileId");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectFileInfs(fileVO);

	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "N");
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);
	
	return "clikMng/cmm/fms/EgovFileList";
    }

    /**
     * 첨부파일 변경을 위한 수정페이지로 이동한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfsForUpdate.do")
    public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("param_atchFileId");

	fileVO.setAtchFileId(atchFileId);

	List<FileVO> result = fileService.selectFileInfs(fileVO);
	
	model.addAttribute("fileList", result);
	model.addAttribute("updateFlag", "Y");
	model.addAttribute("fileListCnt", result.size());
	model.addAttribute("atchFileId", atchFileId);
	
	return "clikMng/cmm/fms/EgovFileList";
    }

    /**
     * 첨부파일에 대한 삭제를 처리한다.
     * 
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/deleteFileInfs.do")
    public String deleteFileInf(@ModelAttribute("searchVO") FileVO fileVO, @RequestParam("returnUrl") String returnUrl,
	    //SessionVO sessionVO,
	    HttpServletRequest request,
	    ModelMap model) throws Exception {

	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
		//2015.05.21 추가
		//실제 경로에 있는 파일도 제거
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		if(result.size() > 0){
			File file = null;
			for(FileVO vo : result){
				file = new File(vo.getFileStreCours() + vo.getStreFileNm());
				
				if(file.exists() && vo.getFileSn().equals(fileVO.getFileSn()))
					file.delete();
				
				if(vo.getFileSn().equals(fileVO.getFileSn()))
					fileService.deleteFileInf(fileVO);
			}
		}
	}
	
	//--------------------------------------------
	// contextRoot가 있는 경우 제외 시켜야 함
	//--------------------------------------------
	////return "forward:/cmm/fms/selectFileInfs.do";
	//return "forward:" + returnUrl;
	
	if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
	    return "forward:" + returnUrl;
	}
	
	if (returnUrl.startsWith(request.getContextPath())) {
	    return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
	} else {
	    return "forward:" + returnUrl;
	}
	////------------------------------------------
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectImageFileInfs.do")
    public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

	String atchFileId = (String)commandMap.get("atchFileId");

	fileVO.setAtchFileId(atchFileId);
	List<FileVO> result = fileService.selectImageFileList(fileVO);
	
	model.addAttribute("fileList", result);

	return "clikMng/cmm/fms/EgovImgFileList";
    }
    
    /**
     * 이미지 첨부파일에 대한 목록을 조회한다. main첫번째만 - 스페셜검색에서 사용
     * 
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectImageFileInf.do")
    public String selectImageFileInf(@ModelAttribute("searchVO") FileVO fileVO, Map<String, Object> commandMap,
	    //SessionVO sessionVO,
	    ModelMap model) throws Exception {

/*
	String atchFileId = (String)commandMap.get("atchFileId");
	String fileSn = (String)commandMap.get("fileSn");
	fileVO.setAtchFileId(atchFileId);
	fileVO.setFileSn(fileSn);
	fileVO.setFileExtsn((String)commandMap.get("fileExtsn"));
	fileVO.setStreFileNm((String)commandMap.get("streFileNm"));
	fileVO.setFileStreCours((String)commandMap.get("streCours"));
*/	
	List<FileVO> result = fileService.selectImageFile(fileVO);
	//List<FileVO> result = new ArrayList<FileVO>();
	//result.add(fileVO);
	
	model.addAttribute("fileList", result);

	return "clikMng/cmm/fms/EgovImgFileList";
    }    
}
