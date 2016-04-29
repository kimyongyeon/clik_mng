package clikmng.nanet.go.kr.sns.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sns.service.SnsFacebook;
import clikmng.nanet.go.kr.sns.service.SnsManageService;
import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.sns.service.SnsTwitter;
import clikmng.nanet.go.kr.sns.service.SnsVO;
import egovframework.rte.fdl.property.EgovPropertyService;
/*
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;
*/
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * SNS 등록 처리하는 컨트롤러 클래스
 * @version 1.0
 */

@Controller
public class SnsManageController {


    
	/** log */
    protected static final Log LOG = LogFactory.getLog(SnsManageController.class);
    
    protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;    
    
    /** PropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;    
    
    /** SnsManageService */
    @Resource(name = "snsManageService")
    private SnsManageService snsManageService;
    
	/**
	 * SNS 목록화면으로 들어간다
	 */
    @RequestMapping(value="/sns/SnsCmmCenList.do")
    public String selectMngList(
    		Map commandMap,
    		SnsManageVO snsManageVO,
    		ModelMap model) 
    		throws Exception {
    	
    	// 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "forward:/uat/uia/LoginUsr.do";
        }
    	
        /** PropertyService.sample */
    	snsManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	snsManageVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(snsManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage( snsManageVO.getSelectCountperpg() == 0 ? snsManageVO.getPageUnit() : snsManageVO.getSelectCountperpg());
        paginationInfo.setPageSize(snsManageVO.getPageSize());

        snsManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        snsManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        
        
        if(snsManageVO.getSelectCountperpg() != 0) {
        	snsManageVO.setRecordCountPerPage(snsManageVO.getSelectCountperpg());
        } else {
        	snsManageVO.setRecordCountPerPage(snsManageVO.getRecordCountPerPage());
        }
        //snsManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        //기본 정렬 설정
        if("".equals(snsManageVO.getSearchSort())) snsManageVO.setSearchSort("SNS_SE_CODE ASC");
        
        // SNS 공통코드 관련 코드 : RKS009 쿼리에 하드코딩
        List reusltList = snsManageService.selectSnsManageList(snsManageVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        model.addAttribute("snsSeCode", commandMap.get("snsSeCode") == null ? "" : (String) commandMap.get("snsSeCode"));
        model.addAttribute("useAt", commandMap.get("useAt") == null ? "" : (String) commandMap.get("useAt"));
        model.addAttribute("searchSort", commandMap.get("searchSort") == null ? "" : (String) commandMap.get("searchSort"));
        
        int totCnt = (Integer) snsManageService.selectSnsManageListCount(snsManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("selectCountperpg", snsManageVO.getRecordCountPerPage());
        model.addAttribute("paginationInfo", paginationInfo);    	
    	
    	
    	
    	return "clikMng/sns/SnsCmmCenList";
    }
    

	/**
	 * SNS 등록화면
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/sns/registSnsCmmCen.do")
    public String snsMngRegist(
            Map commandMap,
            @ModelAttribute("snsManageVO") SnsManageVO snsManageVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
    	
    	JSONObject jObj = new JSONObject();
    	JSONArray jArr = new JSONArray();
    	
    	// 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "forward:/uat/uia/LoginUsr.do";
        }

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        log.info("cmd =>" + sCmd);

        if (sCmd.equals("save")) {
            //서버  validate 체크
            beanValidator.validate(snsManageVO, bindingResult);
            if(bindingResult.hasErrors()){
            	jObj.put("message", bindingResult.getFieldError().toString());
                return "clikMng/sns/SnsRegist";
            } 
            
            //중복체크
            SnsManageVO dupCheckVO = snsManageService.selectSnsDetail(snsManageVO);
            if(dupCheckVO == null)
            {
            	//아이디 설정
                snsManageVO.setFrstRegisterId((String)loginVO.getMngrId());
                snsManageVO.setLastUpdusrId((String)loginVO.getMngrId());
                //저장
                snsManageService.insertSnsManage(snsManageVO);
                
                //등록한 계정의 최신글을 가져온다.
                String snsSeCode = snsManageVO.getSnsSeCode();
                String snsAcntId = snsManageVO.getSnsAcntId();
                
                SnsVO snsVO = null;
                if("FB".equals(snsSeCode)){
                	SnsFacebook snsFacebook = new SnsFacebook();
                	snsVO = snsFacebook.getFacebookFeeds(snsAcntId);
                }else if("TW".equals(snsSeCode)){
                	SnsTwitter snsTwitter = new SnsTwitter();
                	snsVO = snsTwitter.getRecentTwit(snsAcntId);
                }
                //등록한 계정의 최신글 저장한다.
                if(snsVO != null){
            		snsManageService.insertSnsNbc(snsVO);
            	}
                jObj.put("code", "success");
                jObj.put("message", "정상등록되었습니다.");
            }
            else
            {
            	jObj.put("code", "fail");
            	jObj.put("message", "이미 존재하는 아이디입니다.");
            }
            
            jArr.add(jObj);
            
            model.put("msg",jArr.toString());

            return "/clikMng/mdm/MdmIsView";
        }
        else
        {
        	return "/clikMng/sns/SnsRegist";
        }
        
        // SNS 구분 리스트 : SNS 종류가 늘때마다 DB에 Insert만 해주면 됨.
        // List snsCodeList = snsManageService.selectSnsCodeList();
        // model.addAttribute("snsCodeList", snsCodeList);
        
    }
    
    /**
     * SNS 소통센터 상세보기, 삭제하기
     */
    @RequestMapping(value = "/sns/detailSnsCmmCen.do")
    public String snsManageDetail(
            SnsManageVO snsManageVO,
            BindingResult bindingResult,
            Map commandMap,
            ModelMap model) throws Exception {
    	
    	JSONObject jObj = new JSONObject();
    	JSONArray jArr = new JSONArray();
    	
    	// 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "forward:/uat/uia/LoginUsr.do";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        String sLocationUrl = "clikMng/sns/SnsDetail";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        
        if (sCmd.equals("del")) {
        	snsManageService.deleteSns(snsManageVO);
        	
        	jObj.put("code", "success");
            jObj.put("message", "정상처리되었습니다.");
        	jArr.add(jObj);
            model.put("msg",jArr.toString());
            return "/clikMng/mdm/MdmIsView";
             
        } else if(sCmd.equals("edit")) {
            //서버  validate 체크
            beanValidator.validate(snsManageVO, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            }
          
            String snsSeCode = snsManageVO.getSnsSeCode();
            String newSnsAcntId = snsManageVO.getNewSnsAcntId();
            String oldSnsAcntId = snsManageVO.getSnsAcntId();
            
            //중복체크
            snsManageVO.setSnsAcntId(newSnsAcntId);
            SnsManageVO dupCheckVO = snsManageService.selectSnsDetail(snsManageVO);
            if(dupCheckVO == null || newSnsAcntId.equals(oldSnsAcntId)){
            	snsManageVO.setSnsAcntId(oldSnsAcntId);
            	snsManageVO.setNewSnsAcntId(newSnsAcntId);
            	
            	//아이디 설정
                snsManageVO.setLastUpdusrId(loginVO.getMngrId());
            	snsManageService.updtSns(snsManageVO);
            	
                //기존 최신글 삭제
                SnsVO snsVO = new SnsVO();
                snsVO.setSns_se_code(snsSeCode);
                snsVO.setSns_acnt_id(oldSnsAcntId);
                snsManageService.deleteSnsNbc(snsVO);
                
                //등록한 계정의 최신글을 가져온다.
                snsVO = null;
                if("FB".equals(snsSeCode)){
                	SnsFacebook snsFacebook = new SnsFacebook();
                	snsVO = snsFacebook.getFacebookFeeds(newSnsAcntId);
                }else if("TW".equals(snsSeCode)){
                	SnsTwitter snsTwitter = new SnsTwitter();
                	snsVO = snsTwitter.getRecentTwit(newSnsAcntId);
                }
                //등록한 계정의 최신글 저장한다.
                if(snsVO != null){
            		snsManageService.insertSnsNbc(snsVO);
            	}
                jObj.put("code", "success");
                jObj.put("message", "정상등록되었습니다.");
            }
            else
            {
            	jObj.put("code", "fail");
            	jObj.put("message", "이미 존재하는 아이디입니다.");
            }
            
            jArr.add(jObj);
            
            model.put("msg",jArr.toString());

            return "/clikMng/mdm/MdmIsView";

        } else {
        	//상세정보 불러오기
        	//SNS 공통코드 관련 코드 : RKS009 쿼리에 하드코딩
        	SnsManageVO snsManageVOs = snsManageService.selectSnsDetail(snsManageVO);
        	model.addAttribute("snsManageVO", snsManageVOs);
        }
        return sLocationUrl;
    }    
    
    
    /**
     * SNS 소통센터 리스트 사용여부 수정
     */
    @RequestMapping(value = "/sns/updateSnsUseAt.do")
    public String updateSnsUseAt(
            SnsManageVO snsManageVO,
            BindingResult bindingResult,
            Map commandMap,
            ModelMap model) throws Exception {
    	
    	// 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "forward:/uat/uia/LoginUsr.do";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        // 최종수정자 ID 
        snsManageVO.setLastUpdusrId(loginVO.getMngrId());

        try {
        	snsManageService.updateSnsUseAt(snsManageVO);	
		} catch (Exception e) {
			System.out.println("SNS 사용여부 업데이트 에러 :::" + e.getMessage());
			log.error(e.getMessage());
		}

        return "redirct:/sns/SnsCmmCenList.do";
    }    
        
   
}