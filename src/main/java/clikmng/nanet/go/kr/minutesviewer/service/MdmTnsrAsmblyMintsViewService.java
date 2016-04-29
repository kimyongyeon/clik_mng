package clikmng.nanet.go.kr.minutesviewer.service;

import java.util.List;

import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewApndxVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewBillVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewMainVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewSpkrVO;

/**
 * 
 * 회의록 뷰어의 서비스 클래스
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

public interface MdmTnsrAsmblyMintsViewService {
	MdmTnsrAsmblyMintsViewMainVO selectMdmMinutesViewMainInfo(MdmTnsrAsmblyMintsViewMainVO vo) throws Exception;
	List<MdmTnsrAsmblyMintsViewSpkrVO> selectMdmMinutesViewSpeakerInfo(MdmTnsrAsmblyMintsViewMainVO vo) throws Exception;
	List<MdmTnsrAsmblyMintsViewApndxVO> selectMdmMinutesViewAppendixInfo(MdmTnsrAsmblyMintsViewMainVO vo) throws Exception;
	List<MdmTnsrAsmblyMintsViewBillVO> selectMdmMinutesViewBillInfo(MdmTnsrAsmblyMintsViewMainVO vo) throws Exception;
}
