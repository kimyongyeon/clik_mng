package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mdm.model.MdmNewsVO;

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
@Repository("MdmNewsDAO")
public class MdmNewsDAO extends EgovComAbstractDAO {

	public int selectMdmNewsListTotCnt(MdmNewsVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("MdmNewsDAO.selectMdmNewsListTotCnt", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<MdmNewsVO> selectMdmNewsList(MdmNewsVO vo) throws Exception {
		return list("mdmNewsDAO.selectMdmNewsList", vo);
	}
	
	public MdmNewsVO selectMdmNewsView(MdmNewsVO vo) throws Exception {
        return (MdmNewsVO)getSqlMapClientTemplate().queryForObject("MdmNewsDAO.selectMdmMewsView", vo);
	}

	public void insertMdmNews(MdmNewsVO vo) throws Exception {
        insert("MdmNewsDAO.insertMdmNews", vo);
	}

	

}
