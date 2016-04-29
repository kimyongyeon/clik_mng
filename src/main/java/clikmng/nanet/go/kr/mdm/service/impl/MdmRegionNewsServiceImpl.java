package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmRegionNewsVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileDetailVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileVO;
import clikmng.nanet.go.kr.mdm.service.MdmRegionNewsService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */

@Service("MdmRegionNewsService")
public class MdmRegionNewsServiceImpl extends AbstractServiceImpl implements MdmRegionNewsService {

    @Resource(name="MdmRegionNewsDAO")
    private MdmRegionNewsDAO mdmRegionNewsDAO;
        
    /** ID Generation */
    
/*	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
	public int selectMdmOutBbsSeq() throws Exception {
		return mdmRegionNewsDAO.selectMdmOutBbsSeq();
	}
	public int selectMdmOutBbsFileSeq() throws Exception {
		return mdmRegionNewsDAO.selectMdmOutBbsFileSeq();
	}
	public int selectMdmOutNewsFileSn(String ATCH_FILE_ID) throws Exception {
		return mdmRegionNewsDAO.selectMdmOutNewsFileSn(ATCH_FILE_ID);
	}
	public String selectMdmRegionNewsMaxRegDate(String TODAY) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsMaxRegDate(TODAY);
	}
	public int selectMdmRegionNewsDfltListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsDfltListTotCnt(vo);
	}
	public List<MdmRegionNewsVO> selectMdmRegionNewsDfltList(MdmSearchVO vo) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsDfltList(vo);
	}
	public int selectMdmRegionNewsListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsListTotCnt(vo);
	}
	public List<MdmRegionNewsVO> selectMdmRegionNewsList(MdmSearchVO vo) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsList(vo);
	}
	public int selectMdmRegionNewsDplctListTotCnt(MdmSearchVO vo) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsDplctListTotCnt(vo);
	}
	public List<MdmRegionNewsVO> selectMdmRegionNewsDplctList(MdmSearchVO vo) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsDplctList(vo);
	}
	public MdmRegionNewsVO selectMdmRegionNewsView(String NEWS_ID) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsView(NEWS_ID);
	}
	public List<MdmTnpFileDetailVO> selectMdmRegionNewsFileList(String ATCH_FILE_ID) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsFileList(ATCH_FILE_ID);
	}
	public int selectMdmRegionNewsFileExist(String ATCH_FILE_ID) throws Exception {
		return mdmRegionNewsDAO.selectMdmRegionNewsFileExist(ATCH_FILE_ID);
	}

	public void insertMdmRegionNews(MdmRegionNewsVO vo) throws Exception {
		mdmRegionNewsDAO.insertMdmRegionNews(vo);
	}
	public void insertMdmRegionNewsFile(MdmTnpFileVO vo) throws Exception {
		mdmRegionNewsDAO.insertMdmRegionNewsFile(vo);
	}
	public void insertMdmRegionNewsFileDetail(MdmTnpFileDetailVO vo) throws Exception {
		mdmRegionNewsDAO.insertMdmRegionNewsFileDetail(vo);
	}
	public void updateMdmRegionNews(MdmRegionNewsVO vo) throws Exception {
		mdmRegionNewsDAO.updateMdmRegionNews(vo);
	}

	public void deleteMdmRegionNews(MdmRegionNewsVO vo) throws Exception {
		mdmRegionNewsDAO.deleteMdmRegionNews(vo);
	}
	
	public void deleteMdmRegionNewsFile(MdmTnpFileDetailVO vo) throws Exception {
		mdmRegionNewsDAO.deleteMdmRegionNewsFile(vo);
	}
	public void updateMdmRegionNewsIsView(MdmIsViewVO vo) throws Exception {
		mdmRegionNewsDAO.updateMdmRegionNewsIsView(vo);
	}
	public void deleteMdmRegionNewsChk(MdmIsViewVO vo) throws Exception {
		mdmRegionNewsDAO.deleteMdmRegionNewsChk(vo);
	}

}
