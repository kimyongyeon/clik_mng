package clikmng.nanet.go.kr.mdm.service;

import java.util.HashMap;
import java.util.List;

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
 * 모바일 관리 처리하는 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */

public interface MdmTnsrAsmblyAsembyService {

    int selectMdmTnsrAsmblyAsembySeq() throws Exception;

    String selectMdmTnsrAsmblyAsembyActMaxRegDate(String TODAY) throws Exception;
    
 	int selectMdmTnsrAsmblyAsembyActDfltListTotCnt(MdmSearchVO vo) throws Exception;

 	List<MdmTnsrAsmblyAsembyActVO> selectMdmTnsrAsmblyAsembyActDfltList(MdmSearchVO vo) throws Exception;
    
    int selectMdmTnsrAsmblyAsembyActListTotCnt(MdmSearchVO vo) throws Exception;

    List<MdmTnsrAsmblyAsembyActVO> selectMdmTnsrAsmblyAsembyActList(MdmSearchVO vo) throws Exception;
    
    MdmTnsrAsmblyAsembyVO selectMdmTnsrAsmblyAsembyView(MdmSearchVO vo) throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCodeRIS018() throws Exception;
    
    List<MdmDetailCodeVO> selectMdmDetailCode(String CODE_ID) throws Exception;
    
    List<MdmTnsrAsmblyMtgNmVO> selectMdmJrsdCmitId(MdmTnsrAsmblyBiVO vo) throws Exception;

    List<MdmTnsrAsmblyMtgNmVO> selectMdmTnsrJrsdCmitIdList(MdmTnsrAsmblyMtgNmVO vo) throws Exception;

    List<MdmTnsrAsmblyEstVO> selectMdmTnsrEstIdList(MdmTnsrAsmblyEstVO vo) throws Exception;

    List<MdmTnsrAsembyOfCpsStdCdVO> selectMdmAsembyOfCpsStdCd(MdmSearchVO vo) throws Exception;

    MdmTnsrAsmblyOfCpsVO selectMdmTnsrAsmblyOfCps(MdmTnsrAsmblyOfCpsVO vo) throws Exception;

    void insertMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception;

    void insertMdmTnsrAsmblyAsembyAct(MdmTnsrAsmblyAsembyActVO vo) throws Exception;
    
    void insertMdmTnsrAsmblyOfCps(MdmTnsrAsmblyAsembyVO vo) throws Exception;
    
    /**
     * 지방의회 의원 직위 정보를 수정한다.
     * @param vo
     * @return 처리결과
     * @throws Exception
     */
    int updateMdmTnsrAsmblyOfCps(MdmTnsrAsmblyAsembyVO vo) throws Exception;
    
    void updateMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception;

    int updateMdmTnsrAsmblyAsembyAct(MdmTnsrAsmblyAsembyActVO vo) throws Exception;
    
    void updateMdmTnsrAsmblyOfCps01(MdmTnsrAsmblyAsembyVO vo) throws Exception;

    void updateMdmTnsrAsmblyOfCps02(MdmTnsrAsmblyAsembyVO vo) throws Exception;
    
    void updateMdmTnsrAsmblyAsembyIsView(MdmTnsrAsmblyAsembyVO vo) throws Exception;

    void deleteMdmTnsrAsmblyAsembyChk(MdmTnsrAsmblyAsembyVO vo) throws Exception;

    void deleteMdmTnsrAsmblyAsemby(MdmTnsrAsmblyAsembyVO vo) throws Exception;
    
    List<HashMap<String,Object>> selectMdmLamanActivityList(MdmTnsrAsmblyAsembyVO vo) throws Exception;
    
    int deleteAsembyPhotoFile(MdmSearchVO mdmSearchVO) throws Exception;
    
    List<HashMap<String,Object>> selectMdmTnsrMinutes(MdmSearchVO vo) throws Exception;
    
    /**
     * 지방의회 별 대수 목록을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    List<HashMap<String,Object>> MdmTnsrRasmblyList(MdmSearchVO vo) throws Exception;
    
    /**
     * 지방의회 별 회의명 목록을 조회한다.
     * @param vo
     * @return 회의명 목록
     * @throws Exception
     */
    List<HashMap<String,Object>> MdmTnsrMtgnmList(MdmSearchVO vo) throws Exception;
}
