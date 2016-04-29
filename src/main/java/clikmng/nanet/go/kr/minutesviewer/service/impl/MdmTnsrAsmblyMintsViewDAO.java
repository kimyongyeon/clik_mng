package clikmng.nanet.go.kr.minutesviewer.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewApndxVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewBillVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewMainVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewSpkrVO;

/**
 * 
 * 회의록 뷰어  DAO 클래스
 * @author 강희준(ngluka@gmail.com)
 * @since 2014.12.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		수정자			수정내용
 *  -------		---			--------------------
 *   014.12.29	강희준			최초 생성
 *
 * </pre>
 */

@Repository("MdmTnsrAsmblyMintsViewDAO")
public class MdmTnsrAsmblyMintsViewDAO extends EgovComAbstractDAO {
	
	/*
	 * TODO : MINTS_cn 값에 해당하는 회의록 기본 정보 추출
	 */
	public MdmTnsrAsmblyMintsViewMainVO selectMdmMinutesViewMainInfo(MdmTnsrAsmblyMintsViewMainVO vo)
	{
		return (MdmTnsrAsmblyMintsViewMainVO)getSqlMapClientTemplate().queryForObject("MdmTnsrAsmblyMintsViewDAO.selectMintsViewMainInfo",vo);
	}
	
	/*
	 * TODO : MINTS_cn 값에 해당하는 회의록의 발언자 정보 추출 중복 제거 및 의원 데이터만 추출
	 */
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMintsViewSpkrVO> selectMdmMinutesViewSpeakerInfo(MdmTnsrAsmblyMintsViewMainVO vo)
	{
		return list("MdmTnsrAsmbyMintsViewDAO.selectMintsViewSpeakerInfo",vo);
	}
	
	/*
	 * TODO : MINTS_cn 값에 해당하는 회의록의 부록 정보 추출
	 */
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMintsViewApndxVO> selectMdmMinutesViewAppendixInfo(MdmTnsrAsmblyMintsViewMainVO vo) throws Exception
	{
		return list("MdmTnsrAsmblyMintsViewDAO.selectMintsViewAppendixInfo",vo);
	}
	
	/*
	 * TODO : MINTS_cn 값에 해당하는 회의록의 안건 정보 추출
	 */
	@SuppressWarnings("unchecked")
	public List<MdmTnsrAsmblyMintsViewBillVO> selectMdmMinutesViewBillInfo(MdmTnsrAsmblyMintsViewMainVO vo) throws Exception
	{
		return list("MdmTnsrAsmblyMintsViewDAO.selectMintsViewBillInfo",vo);
	}
}
