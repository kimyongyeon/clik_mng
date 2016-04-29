package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmRegionNewsVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileDetailVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileVO;

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

@Repository("MdmRegionNewsDAO")
public class MdmRegionNewsDAO extends EgovComAbstractDAO {

 	public int selectMdmOutBbsSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmOutBbsSeq", null);
	}

 	public int selectMdmOutBbsFileSeq() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmOutBbsFileSeq", null);
	}

 	public int selectMdmOutNewsFileSn(String ATCH_FILE_ID) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmOutNewsFileSn", ATCH_FILE_ID);
	}

 	public String selectMdmRegionNewsMaxRegDate(String TODAY) throws Exception {
        return (String)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmRegionNewsMaxRegDate", TODAY);
	}

 	public int selectMdmRegionNewsDfltListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmRegionNewsDfltListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmRegionNewsVO> selectMdmRegionNewsDfltList(MdmSearchVO vo) throws Exception {
		return list("MdmRegionNewsDAO.selectMdmRegionNewsDfltList", vo);
	}

 	public int selectMdmRegionNewsListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmRegionNewsListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmRegionNewsVO> selectMdmRegionNewsList(MdmSearchVO vo) throws Exception {
		return list("MdmRegionNewsDAO.selectMdmRegionNewsList", vo);
	}
	
	public int selectMdmRegionNewsDplctListTotCnt(MdmSearchVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmRegionNewsDplctListTotCnt", vo);
	}
 	
	@SuppressWarnings("unchecked")
	public List<MdmRegionNewsVO> selectMdmRegionNewsDplctList(MdmSearchVO vo) throws Exception {
		return list("MdmRegionNewsDAO.selectMdmRegionNewsDplctList", vo);
	}

	public MdmRegionNewsVO selectMdmRegionNewsView(String NEWS_ID) throws Exception {
        return (MdmRegionNewsVO)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmRegionNewsView", NEWS_ID);
	}	

	@SuppressWarnings("unchecked")
	public List<MdmTnpFileDetailVO> selectMdmRegionNewsFileList(String ATCH_FILE_ID) throws Exception {
		return list("MdmRegionNewsDAO.selectMdmRegionNewsFileList", ATCH_FILE_ID);
	}

	public int selectMdmRegionNewsFileExist(String ATCH_FILE_ID) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmRegionNewsDAO.selectMdmRegionNewsFileExist", ATCH_FILE_ID);
	}

	public void insertMdmRegionNews(MdmRegionNewsVO vo) throws Exception {
        insert("MdmRegionNewsDAO.insertMdmRegionNews", vo);
    }

	public void insertMdmRegionNewsFile(MdmTnpFileVO vo) throws Exception {
        insert("MdmRegionNewsDAO.insertMdmRegionNewsFile", vo);
    }

	public void insertMdmRegionNewsFileDetail(MdmTnpFileDetailVO vo) throws Exception {
        insert("MdmRegionNewsDAO.insertMdmRegionNewsFileDetail", vo);
    }

	public void updateMdmRegionNews(MdmRegionNewsVO vo) throws Exception {
        update("MdmRegionNewsDAO.updateMdmRegionNews", vo);
    }

	public void deleteMdmRegionNews(MdmRegionNewsVO vo) throws Exception {
        update("MdmRegionNewsDAO.deleteMdmRegionNews", vo);
    }

	public void deleteMdmRegionNewsFile(MdmTnpFileDetailVO vo) throws Exception {
        update("MdmRegionNewsDAO.deleteMdmRegionNewsFile", vo);
    }

	void updateMdmRegionNewsIsView(MdmIsViewVO vo) throws Exception {
        update("MdmRegionNewsDAO.updateMdmRegionNewsIsView", vo);
	}	
	public void deleteMdmRegionNewsChk(MdmIsViewVO vo) throws Exception {
        update("MdmRegionNewsDAO.deleteMdmRegionNewsChk", vo);
    }

}
