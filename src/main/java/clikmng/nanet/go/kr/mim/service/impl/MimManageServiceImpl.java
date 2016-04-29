package clikmng.nanet.go.kr.mim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mim.service.MimManageService;
import clikmng.nanet.go.kr.mim.service.MimManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("MimManageService")
public class MimManageServiceImpl extends AbstractServiceImpl implements
        MimManageService {

    @Resource(name="MimManageDAO")
    private MimManageDAO mimManageDAO;
        
    /** ID Generation */
    // 메일그룹 ID 생성
	@Resource(name="mimIdgenService")
	private EgovIdGnrService mimIdgenService;

    // 메일그룹구성원 ID 생성
	@Resource(name="groupDtlsIdGnrService")
	private EgovIdGnrService groupDtlsIdGnrService;
	
	// 메일발송 정보 ID 생성
	@Resource(name="sendMailInfoIdGnrService")
	private EgovIdGnrService sendMailInfoIdGnrService;

	// 메일발송 정보 디테일 ID 생성
    @Resource(name = "SendMailDetailInfoIdGnrService")
    private EgovIdGnrService sendMailDetailInfoIdGnrService;
	
	// 메일수신거부 ID 생성
	@Resource(name="rejectMailInfoIdGnrService")
	private EgovIdGnrService rejectMailInfoIdGnrService;
		
    /**
	 * 그룹설정 - 목록
	 * @param vo - 조회할 정보가 담긴 MimManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public Map selectGroupListInfo(MimManageVO vo) throws Exception {
    	
		List _result = mimManageDAO.selectGroupList(vo);
		int _cnt = mimManageDAO.selectGroupListCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}    
    
    
    /**
	 * 팝업 - 구성원 조회
	 * @param vo - 조회할 정보가 담긴 MimManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public Map selectPopSearchRasmblyListInfo(MimManageVO vo) throws Exception {
    	
		List _result = mimManageDAO.selectPopSearchRasmblyListInfo(vo);
		int _cnt = mimManageDAO.selectPopSearchRasmblyListCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}
    
    /**
	 * 팝업 - 지방의회 및 지자체 담당자 조회
	 * @param vo - 조회할 정보가 담긴 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public Map selectPopSearchRasmblyChargerListInfo(MimManageVO vo) throws Exception {
    	
		List _result = mimManageDAO.selectPopSearchRasmblyChargerListInfo(vo);
		int _cnt = mimManageDAO.selectPopSearchRasmblyChargerListInfoCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}
    
    /**
	 * 그룹등록
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public void insertGroupInfo(MimManageVO vo) throws Exception {
    	// - 그룹 및 구성원 테이블 ID
    	vo.setEmailGroupId(mimIdgenService.getNextStringId());
    	
    	//0. mail_group 테이블에 값을 INSERT.
    	mimManageDAO.insertGroupInfo(vo);
    	
    	//1. mail_group_user 테이블에 값을 INSERT.
    	//1.1 구성원을 이름과 이메일로 구분.
    	String[] groupDtls;		//textarea에 있던 구성원
    	String rcverNm = "";		//구성원 이름
    	String rcverEmail = "";	//구성원 이메일
    	
    	// 1.2 " 제거 후 배열로  구성원을 담는다.
    	String group = vo.getGroupArea().replace("&quot;", "").replace(")", "");
    	groupDtls = group.split(",");

    	for(int i=0; i<groupDtls.length; i++) {
    		
    		String[] groupDtlsS = new String[2];

    		groupDtlsS = groupDtls[i].split("\\(");

    		rcverNm = groupDtlsS[0];
    		rcverEmail = groupDtlsS[1];
    		
    		vo.setRcverNm(rcverNm.trim());
    		vo.setRcverEmail(rcverEmail.trim());
    		
    		vo.setEmailGroupDtlsNo(groupDtlsIdGnrService.getNextStringId());
    		mimManageDAO.insertGroupDtlsInfo(vo);
    		
    	}
   	}
    
    /**
	 * 팝업 - 지방의회 및 지자체 담당자 조회
	 * @param vo - 조회할 정보가 담긴 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public Map selectGroupDetailInfo(MimManageVO vo) throws Exception {
    	//0. 그룹 정보 조회
    	MimManageVO groupInfo = mimManageDAO.selectGroupDetailInfo(vo);
    	//1. 구성원 정보
		List groupDtlsList = mimManageDAO.selectGroupDetailDtlsInfo(vo);
		//1-1. 구성원 정보를 하나의 값에 담는다.

		//String[] groupDtls = new String[groupDtlsList.size()];
		String groupDtls = "";
		String areaGroupDtls = "";
		
		List<String> dtlsList = new ArrayList<String>();
		
		for(int i=0; i<groupDtlsList.size(); i++) {
			MimManageVO groupDtlsVO = (MimManageVO)groupDtlsList.get(i);
			
			groupDtls = groupDtlsVO.getGroupDtlsInfo();

			dtlsList.add(groupDtls);
			
			areaGroupDtls += groupDtls;

			if (i<groupDtlsList.size()-1){
				areaGroupDtls = areaGroupDtls+",";
			}
			
		}
		
		Map<String, Object> _map = new HashMap();
		_map.put("resultGroupInfo", groupInfo);
		_map.put("resultGroupDtlsList", dtlsList);
		_map.put("resultGroupDtlsInfo", areaGroupDtls);
		
    		 
   		return _map;
   	}
    
    /**
	 * 그룹수정
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public void updateGroupInfo(MimManageVO vo) throws Exception {
    	
    	//0. mail_group 테이블에 값을 INSERT.
    	mimManageDAO.updateGroupInfo(vo);
    	
    	//1. mail_group_user 테이블에 값을 삭제 후 INSERT
    	//1.1 구성원을 이름과 이메일로 구분.
    	String[] groupDtls;		//textarea에 있던 구성원
    	String rcverNm = "";		//구성원 이름
    	String rcverEmail = "";	//구성원 이메일
    	
    	// 1.2 " 제거 후 배열로  구성원을 담는다.
    	String group = vo.getGroupArea().replace("&quot;", "").replace(")", "");
    	groupDtls = group.split(",");

    	// 해당 그룹 ID에 속한 구성원을 삭제한다.
    	mimManageDAO.deleteGroupDtlsInfo(vo);
    	for(int i=0; i<groupDtls.length; i++) {
    		
    		String[] groupDtlsS = new String[2];

    		groupDtlsS = groupDtls[i].split("\\(");

    		rcverNm = groupDtlsS[0];
    		rcverEmail = groupDtlsS[1];
    		
    		vo.setRcverNm(rcverNm.trim());
    		vo.setRcverEmail(rcverEmail.trim());
    		
    		vo.setEmailGroupDtlsNo(groupDtlsIdGnrService.getNextStringId());
    		mimManageDAO.insertGroupDtlsInfo(vo);
    		
    	}
   	}
    
    /**
	 * 그룹삭제
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public void deleteGroupInfo(MimManageVO vo) throws Exception {
    	// 0. 구성원 삭제
    	mimManageDAO.deleteGroupDtlsInfo(vo);
    	// 1. 그룹 삭제
    	mimManageDAO.deleteGroupInfo(vo);
    	
   	}
    
    
    //######################################  메일 발송 시작 ########################################//
	
	/**
	 * 메일링 -  팝업 그룹 조회
	 * @param searchVO
	 * @throws Exception
	 */
	public Map selectPopGroupListInfo(MimManageVO mimManageVO) throws Exception {
		
		List _result = mimManageDAO.selectPopSearchGroupListInfo(mimManageVO);
		int _cnt = mimManageDAO.selectPopSearchGroupListInfoCnt(mimManageVO);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
		
	}
	
    /**
	 * 메일 - 메일발송 정보 등록
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public void insertSendMailInfo(MimManageVO vo) throws Exception {
    	
    	// 0. 메일 내용 등록
    	// 0.1 ID SETTING
    	vo.setEmailSndngId(sendMailInfoIdGnrService.getNextStringId());
    	mimManageDAO.insertSendMailInfo(vo);

    	// 1. 메일 상세 내용 등록
    	
    	//1. mail_group_user 테이블에 값을 INSERT.
    	//1.1 구성원을 이름과 이메일로 구분.
		// 받는 사람 스트링으로 만들기.
    	String[] rcversDtls;		//textarea에 있던 구성원을 분리 값을 담는 배열
    	String[] rcversSt;			//textarea에 있던 이름과 이메일 주소 구분값 담는 배열
    	String rcverNm = "";		//구성원 이름
    	String rcverEmail = "";	//구성원 이메일
    	
    	// 1.2 " 제거 후 배열로  구성원을 담는다.
    	// ex) test(test@test.com), test(test@test.com) 
    	rcversDtls = vo.getMailRcver().trim().split(",");
    	
    	// 1.3 배열 개수만큼 for문을 실행
    	for(int i=0; i<rcversDtls.length; i++) {
    		//1.3.1 "(" 가 있는지 없는지에 따라 분기처리
    		if (rcversDtls[i].indexOf("(") == -1) {
    			rcverNm = "";
   				rcverEmail = rcversDtls[i];
    		
    		} else {
    			rcversSt = new String[2];
    			String rcvers = rcversDtls[i].replaceAll("\\)", "");
    			rcversSt = rcvers.split("\\(");

        		rcverNm = rcversSt[0];
        		rcverEmail = rcversSt[1];
    		}
    		
    		vo.setSn(sendMailDetailInfoIdGnrService.getNextIntegerId());	//상세내용 Sequence ID
    		vo.setRcverNm(rcverNm.trim());
    		vo.setRcverEmail(rcverEmail.trim());
    		
    		mimManageDAO.insertSendMailDetailInfo(vo);
    	}
    	
/*    	
    	String[] groupDtls;		//textarea에 있던 구성원
    	String rcverNm = "";		//구성원 이름
    	String rcverEmail = "";	//구성원 이메일
    	
    	// 1.2 " 제거 후 배열로  구성원을 담는다.
    	String group = vo.getMailRcver().replace("&quot;", "").replace(")", "");
    	groupDtls = group.split(",");

    	for(int i=0; i<groupDtls.length; i++) {
    		
    		String[] groupDtlsS = new String[2];

    		groupDtlsS = groupDtls[i].split("\\(");

    		rcverNm = groupDtlsS[0];
    		rcverEmail = groupDtlsS[1];
    		
    		vo.setRcverNm(rcverNm.trim());
    		vo.setRcverEmail(rcverEmail.trim());
    		
    		mimManageDAO.insertSendMailDetailInfo(vo);
    		
    	}    
 */   	
   	}	
    
    /**
	 * 메일링 -  수신거부 수신자 제외
	 * @param String rcvrEmail
	 * @throws Exception
	 */
	public List selectRejectRcvr(MimManageVO mimManageVO) throws Exception {
		return mimManageDAO.selectRejectRcvr(mimManageVO);
	}
    
	/**
	 * 메일링 -  발송완료 후 발송상태 수정 및 수신자거부 명수 수정
	 * @param MimManageVO
	 * @throws Exception
	 */
	public void updateSendMailStatus(MimManageVO mimManageVO) throws Exception {
		mimManageDAO.updateSendMailStatus(mimManageVO);
	}
    
    /**
	 * 메일 - 그룹 구성원 조회
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public List selectSearchGroupAjax(MimManageVO vo) throws Exception {
    	return mimManageDAO.selectSearchGroupAjax(vo);
   	}	
	
    /**
	 * 메일 - 양식 선택
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public List selectFormList() throws Exception {
    	return mimManageDAO.selectFormList();
   	}	
    
    /**
	 * 메일 - 양식 선택
	 * @param vo - 그룹 정보 MimManageVO
	 * @return 
	 * @exception Exception
	 */
    public String selectChangeFormAjax(MimManageVO vo) throws Exception {
    	return mimManageDAO.selectChangeFormAjax(vo);
   	}	    
	
    //######################################  메일 발송 끝 ########################################//	    
    
    
    //######################################  메일 발송 목록 시작 ########################################//
	
	/**
	 * 메일링 -  발송목록 내역
	 * @param 
	 * @throws Exception
	 */
    public Map selectSendMailListInfo(MimManageVO vo) throws Exception {
		List _result = mimManageDAO.selectSendMailListInfo(vo);
		int _cnt = mimManageDAO.selectSendMailListCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;    	
    	
    	
   	}	   
	
    //######################################  메일 발송 목록 끝 ########################################//
    
    //######################################  메일 수신거부 목록 시작 ########################################//
	
	/**
	 * 메일링 -  수신거부목록 내역
	 * @param 
	 * @throws Exception
	 */
	public Map selectRejectEmailList(MimManageVO vo) throws Exception {
		List _result = mimManageDAO.selectRejectEmailList(vo);
		int _cnt = mimManageDAO.selectRejectEmailListCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;    
	}
	
	/**
	 * 메일링 -  수신거부정보 중복확인
	 * @param 
	 * @throws Exception
	 */
	public int selectConfirmEmail(String rejectEmail) throws Exception{
		return mimManageDAO.selectConfirmEmail(rejectEmail);
	}
	
	
	/**
	 * 메일링 -  수신거부정보 등록
	 * @param 
	 * @throws Exception
	 */
	public void insertRejectEmailInfo(HashMap<String, String> rejectInfoMap) throws Exception{
		rejectInfoMap.put("rejectId", rejectMailInfoIdGnrService.getNextStringId());
		
		mimManageDAO.insertRejectEmailInfo(rejectInfoMap);
	}
	
    //######################################  메일 수신거부 목록 끝 ########################################//	    
    
    
    
}
