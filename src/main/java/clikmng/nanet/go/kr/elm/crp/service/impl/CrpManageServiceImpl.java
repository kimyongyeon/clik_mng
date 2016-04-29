package clikmng.nanet.go.kr.elm.crp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestSetupVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpListVO;
import clikmng.nanet.go.kr.elm.crp.service.ElmCrpDetailVO;
import clikmng.nanet.go.kr.elm.crp.service.CrpManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("CrpManageService")
public class CrpManageServiceImpl extends AbstractServiceImpl implements CrpManageService {

    @Resource(name="CrpManageDAO")
    private CrpManageDAO crpManageDAO;

	@Resource(name="ElmCaseIdGnrService")
	private EgovIdGnrService idgenService;

	/**
     * 열람신청 권한 관리 리스트 조회
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<ElmCrpListVO> selectElmCrpList(ElmCrpListVO searchVO)  throws Exception{
		return crpManageDAO.selectElmCrpList(searchVO);
	}

	public int selectElmCrpListTotCnt(ElmCrpListVO searchVO)  throws Exception{
		return crpManageDAO.selectElmCrpListTotCnt(searchVO);
	}
	

    /**
     * 열람신청 권한 관리 등록
     */
    public void insertElmCrpRegist(ElmCrpDetailVO vo) throws Exception {

    	// 기본키 자동생성
    	vo.setReadngReqstSetupId(idgenService.getNextStringId());
    	// 열람신청 권한 등록 
    	crpManageDAO.insertElmCrpRegist(vo);
    	
    	HashMap<String, String> openCode = vo.getOpenCode();
    	Iterator it = openCode.keySet().iterator(); 
    	while(it.hasNext())
   		{

    		String key = (String)it.next();
    		String val = (String)openCode.get(key);
    		
    		System.out.println("#####################################################");
    		System.out.println(vo.getReadngReqstSetupId());
    		System.out.println(key);
    		System.out.println(val);
    		System.out.println("#####################################################");
    		
    		ReadGrantVO rgVO = new ReadGrantVO();
    		rgVO.setReadngReqstSetupId(vo.getReadngReqstSetupId());
    		rgVO.setReadngSeCode(key);
    		rgVO.setReadngAt(val);
    		crpManageDAO.insertElmCrpOpenCode(rgVO);

 
   		}
    	
    	
    }
    
    /**
     * 관리자 상세내용
     */
    public ElmCrpDetailVO selectElmCrpDetail(ElmCrpDetailVO vo) throws Exception {
        return (ElmCrpDetailVO)crpManageDAO.selectElmCrpDetail(vo);
    }
    public List<ReadGrantVO> selectElmCrpOpenCode(ElmCrpDetailVO vo) throws Exception {
        return crpManageDAO.selectElmCrpOpenCode(vo);
    }
    
    
    /**
     * 관리자 수정처리
     */
    public void updateElmCrpDetail(ElmCrpDetailVO vo) throws Exception {
        // 관리자 수정
    	crpManageDAO.updateElmCrpDetail(vo);

    	HashMap<String, String> openCode = vo.getOpenCode();
    	Iterator it = openCode.keySet().iterator(); 
    	while(it.hasNext())
   		{
    		String key = (String)it.next();
    		String val = (String)openCode.get(key);

    		System.out.println("#########################################################");
    		System.out.println(vo.getReadngReqstSetupId());
    		System.out.println(key);
    		System.out.println(val);
    		System.out.println("#########################################################");

    		try
    		{
	    		ReadGrantVO rgVO = new ReadGrantVO();
	    		rgVO.setReadngReqstSetupId(vo.getReadngReqstSetupId());
	    		rgVO.setReadngSeCode(key);
	    		rgVO.setReadngAt(val);
	    		crpManageDAO.updateElmCrpOpenCode(rgVO);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}

 
   		}

    	
        // 보안설정 테이블 수정
        // mngDao.editAuthorDetail(mngVO);
    }   
    
    /**
     * 관리자 삭제처리
     */
    public void deleteElmCrpDetail(ElmCrpDetailVO vo) throws Exception {
        // 보안설정 테이블 수정
        // mngDao.delMngMappingManage(mngVO);
        // 관리자 수정
    	crpManageDAO.deleteElmCrpOpenCode(vo);
    	crpManageDAO.deleteElmCrpDetail(vo);
    	


    }	
}
