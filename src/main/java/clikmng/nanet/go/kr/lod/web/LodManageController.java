package clikmng.nanet.go.kr.lod.web;

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
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.lod.service.LodManageDefaultVO;
import clikmng.nanet.go.kr.lod.service.LodManageService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * LOD 관리를 처리하는 Controller 클래스
 */

@Controller
public class LodManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "LodManageService")
    private LodManageService LodManageService;

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
     * LOD 관리
     * @param searchVO
     * @param model
     * @return	"/lod/LodInqire"
     * @throws Exception
     */
    @IncludedInfo(name="LOD 관리", order = 680 ,gid = 50)
    @RequestMapping(value="/lod/LodInqire.do")
    public String lodInqire(@ModelAttribute("searchVO") LodManageDefaultVO searchVO, ModelMap model) throws Exception {

		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));

		
        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        {
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + 
        			"-" + searchVO.getSchDt1().substring(4, 6) + 
        			"-" + searchVO.getSchDt1().substring(6, 8));
        }
        
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        {
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + 
        			"-" + searchVO.getSchDt2().substring(4, 6) + 
        			"-" + searchVO.getSchDt2().substring(6, 8));
        }
		
    	model.addAttribute("searchVO", searchVO);

        return "clikMng/lod/LodInqire";
    }

}
