package clikmng.nanet.go.kr.minutesviewer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewApndxVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewBillVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewMainVO;
import clikmng.nanet.go.kr.minutesviewer.model.MdmTnsrAsmblyMintsViewSpkrVO;
import clikmng.nanet.go.kr.minutesviewer.service.MdmTnsrAsmblyMintsViewService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("MdmTnsrAsmblyMintsViewService")
public class MdmTnsrAsmblyMintsViewServiceImpl extends AbstractServiceImpl
		implements MdmTnsrAsmblyMintsViewService {

	@Resource(name="MdmTnsrAsmblyMintsViewDAO")
	private MdmTnsrAsmblyMintsViewDAO mdmTnsrAsmblyMintsViewDAO;
	
	public MdmTnsrAsmblyMintsViewMainVO selectMdmMinutesViewMainInfo(
			MdmTnsrAsmblyMintsViewMainVO vo) throws Exception {
		return mdmTnsrAsmblyMintsViewDAO.selectMdmMinutesViewMainInfo(vo);
	}
	
	public List<MdmTnsrAsmblyMintsViewSpkrVO> selectMdmMinutesViewSpeakerInfo(
			MdmTnsrAsmblyMintsViewMainVO vo) throws Exception {
		return mdmTnsrAsmblyMintsViewDAO.selectMdmMinutesViewSpeakerInfo(vo);
	}
	
	public List<MdmTnsrAsmblyMintsViewApndxVO> selectMdmMinutesViewAppendixInfo(
			MdmTnsrAsmblyMintsViewMainVO vo) throws Exception {
		return mdmTnsrAsmblyMintsViewDAO.selectMdmMinutesViewAppendixInfo(vo);
	}
	
	public List<MdmTnsrAsmblyMintsViewBillVO> selectMdmMinutesViewBillInfo(
			MdmTnsrAsmblyMintsViewMainVO vo) throws Exception
	{
		return mdmTnsrAsmblyMintsViewDAO.selectMdmMinutesViewBillInfo(vo);
	}
}
