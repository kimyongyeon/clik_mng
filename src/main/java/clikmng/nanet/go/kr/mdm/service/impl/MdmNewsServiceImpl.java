package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mdm.model.MdmNewsVO;
import clikmng.nanet.go.kr.mdm.service.MdmNewsService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("MdmNewsService")
public class MdmNewsServiceImpl extends AbstractServiceImpl implements MdmNewsService {

    @Resource(name="MdmNewsDAO")
    private MdmNewsDAO mdmNewsDAO;
        
    /** ID Generation */
    
	@Resource(name="MdmNewsIdGnrService")
	private EgovIdGnrService idgenService;


	public int selectMdmNewsListTotCnt(MdmNewsVO vo) {
		return mdmNewsDAO.selectMdmNewsListTotCnt(vo);
	}
	
	public List<MdmNewsVO> selectMdmNewsList(MdmNewsVO vo) throws Exception {
		return mdmNewsDAO.selectMdmNewsList(vo);
	}
	
	public MdmNewsVO selectMdmNewsView(MdmNewsVO vo) throws Exception {
		return mdmNewsDAO.selectMdmNewsView(vo);
	}

	public int insertMdmNews(ArrayList<MdmNewsVO> list) throws Exception {
		
		int result = 0;
		for(MdmNewsVO vo : list)
		{
			vo.setNewsId(idgenService.getNextStringId());
			mdmNewsDAO.insertMdmNews(vo);
			result++;
		}
		return result;
	}

}
