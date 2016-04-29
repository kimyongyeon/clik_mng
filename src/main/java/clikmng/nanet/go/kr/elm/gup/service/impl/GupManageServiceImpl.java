package clikmng.nanet.go.kr.elm.gup.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.UserClassDtaseVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestVO;

import clikmng.nanet.go.kr.elm.gup.service.ElmGupListVO;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupDetailVO;

import clikmng.nanet.go.kr.elm.gup.service.GupManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 *
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("GupManageService")
public class GupManageServiceImpl extends AbstractServiceImpl implements GupManageService {

	@Resource(name="GupManageDAO")
	private GupManageDAO gupManageDAO;

	/**
	* 환경설정 - 자동/수동 게시 목록
	* @param searchVO
	* @return 그룹 목록
	* @throws Exception
	*/
	public List<ElmGupListVO> selectElmGupList()  throws Exception{
		return gupManageDAO.selectElmGupList();
	}


	/**
	* 사용자 그룹 조회
	* @param searchVO
	* @return 그룹 목록
	* @throws Exception
	*/
	public List<UserClassVO> selectUserClass()  throws Exception{
		return gupManageDAO.selectUserClass();
	}

	public ElmGupDetailVO selectElmGupDetail(ElmGupDetailVO vo) throws Exception{
		
		
		vo.setPrposCode("100");
		
		HashMap<String, String> grantUseCode = new HashMap();
		
		for(UserClassDtaseVO ucdVO : gupManageDAO.selectUserClassDtase(vo))
		{
			System.out.println("################################################################");
			System.out.println("UserClassDtaseVO - use");
			System.out.println("ucdVO.getDtaSeCode() ===> " + ucdVO.getDtaSeCode());
			System.out.println("ucdVO.getPrposCode() ===> " + ucdVO.getPrposCode());
			System.out.println("################################################################");
			
			grantUseCode.put(ucdVO.getDtaSeCode(), ucdVO.getPrposCode());
		}
		
		vo.setGrantUseCode(grantUseCode);

		
		vo.setPrposCode("200");
		
		HashMap<String, String> grantBenCode = new HashMap();
		HashMap<String, String> grantBenMsg = new HashMap();
		
		for(UserClassDtaseVO ucdVO : gupManageDAO.selectUserClassDtase(vo))
		{
			System.out.println("################################################################");
			System.out.println("UserClassDtaseVO - ben");
			System.out.println("ucdVO.getDtaSeCode() ===> " + ucdVO.getDtaSeCode());
			System.out.println("ucdVO.getDtaSeCode() ===> " + ucdVO.getPrposCode());
			System.out.println("ucdVO.getDtaSeCode() ===> " + ucdVO.getProcessMssage());
			System.out.println("################################################################");

			grantBenCode.put(ucdVO.getDtaSeCode(), ucdVO.getPrposCode());
			grantBenMsg.put(ucdVO.getDtaSeCode(), ucdVO.getProcessMssage());
		}

		
		vo.setGrantBenCode(grantBenCode);

		HashMap<String, String> openCode = new HashMap();
		
		for(ReadGrantRequestVO rgrVO : gupManageDAO.selectReadGrantRequest(vo))
		{
			
			System.out.println("################################################################");
			System.out.println("selectReadGrantRequest");
			System.out.println("rgrVO.getReadngSeCode() ===> " + rgrVO.getReadngSeCode());
			System.out.println("################################################################");
			
			openCode.put(rgrVO.getReadngSeCode(), "Y");
		}
		
		vo.setOpenCode(openCode);
		
		return vo;
		
	}

	
	/**
	* 관리자 등록 및 Mapping Table insert(TNPEMPLYRSCRTYESTBS)
	*/
	public void insertElmGupRegist(ElmGupDetailVO vo) throws Exception {
	
		//자료 이용 권한 
		HashMap<String, String> grantUseCode		=	vo.getGrantUseCode();
		//자료 이용 제한
		HashMap<String, String> grantBenCode		=	vo.getGrantBenCode();
		//자료 이용 제한 메세지
		HashMap<String, String> grantBenMsg			=	vo.getGrantBenMsg();
		//열람신청 권한
		HashMap<String, String> openCode			=	vo.getOpenCode();
		
		Iterator it;
		
		//자료 이용 권한 
		it = grantUseCode.keySet().iterator(); 
		while(it.hasNext())
		{
	
			String key = (String)it.next();
			String val = (String)grantUseCode.get(key);

			UserClassDtaseVO ucdVO = new UserClassDtaseVO();
			ucdVO.setUserGroupId(vo.getUserGroupId());
			ucdVO.setPrposCode(val);
			ucdVO.setProcessMssage("");
			ucdVO.setDtaSeCode(key);
			ucdVO.setFrstRegisterId(vo.getFrstRegisterId());
			gupManageDAO.insertUserClassDtase(ucdVO);
		}
		
		//자료 이용 제한
		it = grantBenCode.keySet().iterator(); 
		while(it.hasNext())
		{
	
			String key = (String)it.next();
			String val = (String)grantBenCode.get(key);

			System.out.println("#####################################################");
			System.out.println(key + " ======= " + val);
			System.out.println("msg ======= " + grantBenMsg.get(key));			
			System.out.println("#####################################################");

			UserClassDtaseVO ucdVO = new UserClassDtaseVO();
			ucdVO.setUserGroupId(vo.getUserGroupId());
			ucdVO.setPrposCode(val);
			ucdVO.setProcessMssage(grantBenMsg.get(key));
			ucdVO.setDtaSeCode(key);
			ucdVO.setFrstRegisterId(vo.getFrstRegisterId());
			gupManageDAO.insertUserClassDtase(ucdVO);
		}

		//열람신청 권한
		gupManageDAO.deleteReadGrantRequest(vo);
		
		it = openCode.keySet().iterator(); 
		while(it.hasNext())
		{
	
			String key = (String)it.next();
			String val = (String)openCode.get(key);

			ReadGrantRequestVO rgrVO = new ReadGrantRequestVO();
			rgrVO.setUserGroupId(vo.getUserGroupId());
			rgrVO.setReadngSeCode(key);
			rgrVO.setFrstRegisterId(vo.getFrstRegisterId());
			gupManageDAO.insertReadGrantRequest(rgrVO);
		}



	}

	/**
	* 관리자 등록 및 Mapping Table insert(TNPEMPLYRSCRTYESTBS)
	*/
	public void updateElmGupDetail(ElmGupDetailVO vo) throws Exception {
	
		//자료 이용 권한 
		HashMap<String, String> grantUseCode		=	vo.getGrantUseCode();
		//자료 이용 제한
		HashMap<String, String> grantBenCode		=	vo.getGrantBenCode();
		//자료 이용 제한 메세지
		HashMap<String, String> grantBenMsg			=	vo.getGrantBenMsg();
		//열람신청 권한
		HashMap<String, String> openCode			=	vo.getOpenCode();
		
		
		gupManageDAO.deleteUserClassDtase(vo);
		gupManageDAO.deleteReadGrantRequest(vo);
		
		Iterator it;
		
		//자료 이용 권한 
		it = grantUseCode.keySet().iterator(); 
		while(it.hasNext())
		{
	
			String key = (String)it.next();
			String val = (String)grantUseCode.get(key);

			System.out.println("#####################################################");
			System.out.println(key + " ======= " + val);
			System.out.println("#####################################################");
			UserClassDtaseVO ucdVO = new UserClassDtaseVO();
			ucdVO.setUserGroupId(vo.getUserGroupId());
			ucdVO.setPrposCode(val);
			ucdVO.setProcessMssage("");
			ucdVO.setDtaSeCode(key);
			ucdVO.setFrstRegisterId(vo.getFrstRegisterId());
			gupManageDAO.insertUserClassDtase(ucdVO);
		}
		
		//자료 이용 제한
		it = grantBenCode.keySet().iterator(); 
		while(it.hasNext())
		{

			String key = (String)it.next();
			String val = (String)grantBenCode.get(key);
			
			System.out.println("#####################################################");
			System.out.println(key + " ======= " + val);
			System.out.println("msg ======= " + grantBenMsg.get(key));			
			System.out.println("#####################################################");
	

			UserClassDtaseVO ucdVO = new UserClassDtaseVO();
			ucdVO.setUserGroupId(vo.getUserGroupId());
			ucdVO.setPrposCode(val);
			ucdVO.setProcessMssage(grantBenMsg.get(key));
			ucdVO.setDtaSeCode(key);
			ucdVO.setFrstRegisterId(vo.getFrstRegisterId());
			gupManageDAO.insertUserClassDtase(ucdVO);
		}
		
		//열람신청 권한
		gupManageDAO.deleteReadGrantRequest(vo);
		
		it = openCode.keySet().iterator(); 
		while(it.hasNext())
		{
	
			String key = (String)it.next();
			String val = (String)openCode.get(key);

			ReadGrantRequestVO rgrVO = new ReadGrantRequestVO();
			rgrVO.setUserGroupId(vo.getUserGroupId());
			rgrVO.setReadngSeCode(key);
			rgrVO.setFrstRegisterId(vo.getFrstRegisterId());
			gupManageDAO.insertReadGrantRequest(rgrVO);
		}



	}
	
	public void deleteElmGupDetail(ElmGupDetailVO vo) throws Exception {
		gupManageDAO.deleteUserClassDtase(vo);
		gupManageDAO.deleteReadGrantRequest(vo);

	}

}
