package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsembyOfCpsStdCdVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyActVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyEstVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyOfCpsVO;

/**
 * 
 * 사이트정보를 처리하는 DAO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Repository("MdmTnsrAsmblyAsembyDAO")
public class MdmTnsrAsmblyAsembyDAO extends EgovComAbstractDAO {

	public int selectMdmTnsrAsmblyAsembySeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembySeq", null);
	}

 	public String selectMdmTnsrAsmblyAsembyActMaxRegDate(String TODAY) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActMaxRegDate", TODAY);
	}

 	public int selectMdmTnsrAsmblyAsembyActDfltListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActDfltListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyAsembyActVO> selectMdmTnsrAsmblyAsembyActDfltList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActDfltList", vo);
	}
 	
	public int selectMdmTnsrAsmblyAsembyActListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActListTotCnt", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyAsembyActVO> selectMdmTnsrAsmblyAsembyActList(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActList", vo);
	}
	
	public MdmTnsrAsmblyAsembyVO selectMdmTnsrAsmblyAsembyView(MdmSearchVO vo) throws Exception {
        return (MdmTnsrAsmblyAsembyVO)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyView", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmDetailCodeRIS018", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmDetailCode", CODE_ID);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmJrsdCmitId", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyEstVO> selectMdmTnsrEstIdList(MdmTnsrAsmblyEstVO vo) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrEstIdList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMtgNmVO> selectMdmTnsrJrsdCmitIdList(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrJrsdCmitIdList", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsembyOfCpsStdCdVO> selectMdmAsembyOfCpsStdCd(MdmSearchVO vo) throws Exception {
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmAsembyOfCpsStdCd", vo);
	}

	public MdmTnsrAsmblyOfCpsVO selectMdmTnsrAsmblyOfCps(MdmTnsrAsmblyOfCpsVO vo) throws Exception {
		return (MdmTnsrAsmblyOfCpsVO)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyOfCps", vo);
	}

    public void insertMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        insert("MdmTnsrAsmblyAsembyDAO.insertMdmTnsrAsmblyAsemby", vo);
    }

    public void insertMdmTnsrAsmblyAsembyAct(MdmTnsrAsmblyAsembyActVO vo) throws Exception {
        insert("MdmTnsrAsmblyAsembyDAO.insertMdmTnsrAsmblyAsembyAct", vo);
    }
   
    public void insertMdmTnsrAsmblyOfCps(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        insert("MdmTnsrAsmblyAsembyDAO.insertMdmTnsrAsmblyOfCps", vo);
    }
    
    /**
     * 지방의회 의원 직위 정보를 수정한다.
     * @param vo
     * @return 처리결과
     * @throws Exception
     */
    public int updateMdmTnsrAsmblyOfCps(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        return update("MdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyOfCps", vo);
    }
    
    public void updateMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        insert("MdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyAsemby", vo);
    }

    public int updateMdmTnsrAsmblyAsembyAct(MdmTnsrAsmblyAsembyActVO vo) throws Exception {
        return update("MdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyAsembyAct", vo);
    }
   
    public void updateMdmTnsrAsmblyOfCps01(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        insert("MdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyOfCps01", vo);
    }
    
    public void updateMdmTnsrAsmblyOfCps02(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        insert("MdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyOfCps02", vo);
    }
    
    public void updateMdmTnsrAsmblyAsembyIsView(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        update("MdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyAsembyIsView", vo);
    }

    public void deleteMdmTnsrAsmblyAsembyChk(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        update("MdmTnsrAsmblyAsembyDAO.deleteMdmTnsrAsmblyAsembyChk", vo);
    }

    public void deleteMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception {
        update("MdmTnsrAsmblyAsembyDAO.deleteMdmTnsrAsmblyAsemby", vo);
    }
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectMdmLamanActivityList(MdmTnsrAsmblyAsembyVO vo) throws Exception{
		return list("MdmTnsrAsmblyAsembyDAO.selectMdmLamanActivityList",vo);
	}
    
    public int deleteAsembyPhotoFile(MdmSearchVO vo) throws Exception{
		return update("MdmTnsrAsmblyAsembyDAO.deleteAsembyPhotoFile", vo);
	}
    
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectMdmTnsrMinutes(MdmSearchVO vo) throws Exception{
    	return list("MdmTnsrAsmblyAsembyDAO.selectMdmTnsrMinutes", vo);
	}

    
    /**
     * 지방의회 별 대수 목록을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap<String,Object>> MdmTnsrRasmblyList(MdmSearchVO vo) throws Exception{
    	return list("MdmTnsrAsmblyAsembyDAO.MdmTnsrRasmblyList", vo);
	}
    
    /**
     * 지방의회 별 회의명 목록을 조회한다.
     * @param vo
     * @return 회의명 목록
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<HashMap<String,Object>> MdmTnsrMtgnmList(MdmSearchVO vo) throws Exception{
    	return list("MdmTnsrAsmblyAsembyDAO.MdmTnsrMtgnmList", vo);
	}
}
