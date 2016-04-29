package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsembyOfCpsStdCdVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyActVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyEstVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyOfCpsVO;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyAsembyService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmTnsrAsmblyAsembyService")
public class MdmTnsrAsmblyAsembyServiceImpl extends AbstractServiceImpl implements MdmTnsrAsmblyAsembyService {

    @Resource(name="MdmTnsrAsmblyAsembyDAO")
    private MdmTnsrAsmblyAsembyDAO mdmTnsrAsmblyAsembyDAO;
        
    /** ID Generation */
    
/*	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
	public int selectMdmTnsrAsmblyAsembySeq() throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembySeq();
	}

	public String selectMdmTnsrAsmblyAsembyActMaxRegDate(String TODAY) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActMaxRegDate(TODAY);
	}

	public int selectMdmTnsrAsmblyAsembyActDfltListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActDfltListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyAsembyActVO> selectMdmTnsrAsmblyAsembyActDfltList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActDfltList(vo);
	}

	public int selectMdmTnsrAsmblyAsembyActListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActListTotCnt(vo);
	}
	
	public List<MdmTnsrAsmblyAsembyActVO> selectMdmTnsrAsmblyAsembyActList(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyActList(vo);
	}
	
	public MdmTnsrAsmblyAsembyVO selectMdmTnsrAsmblyAsembyView(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyAsembyView(vo);
	}

	public List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmDetailCodeRIS018();
	}

	public List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmDetailCode(CODE_ID);
	}

	public List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmJrsdCmitId(vo);
	}

	public List<MdmTnsrAsmblyMtgNmVO> selectMdmTnsrJrsdCmitIdList(MdmTnsrAsmblyMtgNmVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrJrsdCmitIdList(vo);
	}

	public List<MdmTnsrAsmblyEstVO> selectMdmTnsrEstIdList(MdmTnsrAsmblyEstVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrEstIdList(vo);
	}

	public List<MdmTnsrAsembyOfCpsStdCdVO> selectMdmAsembyOfCpsStdCd(MdmSearchVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmAsembyOfCpsStdCd(vo);
	}

	public MdmTnsrAsmblyOfCpsVO selectMdmTnsrAsmblyOfCps(MdmTnsrAsmblyOfCpsVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrAsmblyOfCps(vo);
	}

	public void insertMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.insertMdmTnsrAsmblyAsemby(vo);
	}

	public void insertMdmTnsrAsmblyAsembyAct(MdmTnsrAsmblyAsembyActVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.insertMdmTnsrAsmblyAsembyAct(vo);
	}
	
	public void insertMdmTnsrAsmblyOfCps(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.insertMdmTnsrAsmblyOfCps(vo);
	}
	
	/**
     * 지방의회 의원 직위 정보를 수정한다.
     * @param vo
     * @return 처리결과
     * @throws Exception
     */
	public int updateMdmTnsrAsmblyOfCps(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyOfCps(vo);
	}
	
	public void updateMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyAsemby(vo);
	}

	public int updateMdmTnsrAsmblyAsembyAct(MdmTnsrAsmblyAsembyActVO vo) throws Exception {
		return mdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyAsembyAct(vo);
	}
	
	public void updateMdmTnsrAsmblyOfCps01(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyOfCps01(vo);
	}

	public void updateMdmTnsrAsmblyOfCps02(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyOfCps02(vo);
	}
	
	public void updateMdmTnsrAsmblyAsembyIsView(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.updateMdmTnsrAsmblyAsembyIsView(vo);
	}

	public void deleteMdmTnsrAsmblyAsembyChk(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.deleteMdmTnsrAsmblyAsembyChk(vo);
	}

	public void deleteMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception {
		mdmTnsrAsmblyAsembyDAO.deleteMdmTnsrAsmblyAsemby(vo);
	}
	
	public List<HashMap<String,Object>> selectMdmLamanActivityList(MdmTnsrAsmblyAsembyVO vo) throws Exception{
		return mdmTnsrAsmblyAsembyDAO.selectMdmLamanActivityList(vo);
	}
	
	public int deleteAsembyPhotoFile(MdmSearchVO vo) throws Exception{
		return mdmTnsrAsmblyAsembyDAO.deleteAsembyPhotoFile(vo);
	}
	
	public List<HashMap<String,Object>> selectMdmTnsrMinutes(MdmSearchVO vo) throws Exception{
		return mdmTnsrAsmblyAsembyDAO.selectMdmTnsrMinutes(vo);
	}

	/**
     * 지방의회 별 대수 목록을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
	public List<HashMap<String,Object>> MdmTnsrRasmblyList(MdmSearchVO vo) throws Exception{
		return mdmTnsrAsmblyAsembyDAO.MdmTnsrRasmblyList(vo);
	}
	
	/**
     * 지방의회 별 회의명 목록을 조회한다.
     * @param vo
     * @return 회의명 목록
     * @throws Exception
     */
	public List<HashMap<String,Object>> MdmTnsrMtgnmList(MdmSearchVO vo) throws Exception{
		return mdmTnsrAsmblyAsembyDAO.MdmTnsrMtgnmList(vo);
	}
}
