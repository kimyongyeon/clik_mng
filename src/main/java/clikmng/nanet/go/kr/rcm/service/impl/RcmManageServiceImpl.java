package clikmng.nanet.go.kr.rcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.rcm.service.RcmManageDefaultVO;
import clikmng.nanet.go.kr.rcm.service.RcmManageService;
import clikmng.nanet.go.kr.rcm.service.RcmManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("RcmManageService")
public class RcmManageServiceImpl extends AbstractServiceImpl implements
        RcmManageService {

    @Resource(name="RcmManageDAO")
    private RcmManageDAO RcmManageDAO;
        
    /** ID Generation */
/*    
	@Resource(name="egovRcmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
    /**
	 * 사이트상세정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 RcmManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public RcmManageVO selectRcmDetail(RcmManageVO vo) throws Exception {
        RcmManageVO resultVO = RcmManageDAO.selectRcmDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 *  인증키 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectAilList(RcmManageDefaultVO searchVO) throws Exception {
        return RcmManageDAO.selectAilList(searchVO);
    }

    /**
	 * 인증키  총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    public int selectAilListTotCnt(RcmManageDefaultVO searchVO) {
		return RcmManageDAO.selectAilListTotCnt(searchVO);
	}
    
    /**
	 * 인증키  상세내용 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 인증키  상세내용
	 * @exception
	 */
    public RcmManageVO selectAiDetail(RcmManageVO vo) throws Exception {
        RcmManageVO resultVO = RcmManageDAO.selectAiDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }
    
    /**
	 * 인증키  상세내용 변경한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */
    public int updateAiDetail(RcmManageVO vo) throws Exception {
        return  RcmManageDAO.updateAiDetail(vo);
    }
        
    
    /**
	 * 인증키  상세내용 삭제
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 
	 * @exception
	 */
    public int deleteAiDetail(RcmManageVO vo) throws Exception {
    	return  RcmManageDAO.deleteAiDetail(vo);
    }
            
    
    /**
	 *  인증키 이용목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectAuList(RcmManageDefaultVO searchVO) throws Exception {
        return RcmManageDAO.selectAuList(searchVO);
    }    
    
    /**
	 * 인증키이용  총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    public int selectAuListTotCnt(RcmManageDefaultVO searchVO) {
		return RcmManageDAO.selectAuListTotCnt(searchVO);
	}    
    
}
