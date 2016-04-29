package clikmng.nanet.go.kr.cop.tpl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.cop.tpl.service.TemplateInf;
import clikmng.nanet.go.kr.cop.tpl.service.TemplateInfVO;

/**
 * 템플릿 정보관리를 위한 데이터 접근 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
@Repository("TemplateManageDAO")
public class TemplateManageDAO extends EgovComAbstractDAO {

    /**
     * 템플릿 정보를 삭제한다.
     * 
     * @param tmplatInf
     * @throws Exception
     */
    public void deleteTemplateInf(TemplateInf tmplatInf) throws Exception {
	update("TemplateManageDAO.deleteTemplateInf", tmplatInf);
    }

    /**
     * 템플릿 정보를 등록한다.
     * 
     * @param tmplatInf
     * @throws Exception
     */
    public void insertTemplateInf(TemplateInf tmplatInf) throws Exception {
	insert("TemplateManageDAO.insertTemplateInf", tmplatInf);
    }

    /**
     * 템플릿 정보를 수정한다.
     * 
     * @param tmplatInf
     * @throws Exception
     */
    public void updateTemplateInf(TemplateInf tmplatInf) throws Exception {
	update("TemplateManageDAO.updateTemplateInf", tmplatInf);
    }

    /**
     * 템플릿에 대한 목록를 조회한다.
     * 
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<TemplateInfVO> selectTemplateInfs(TemplateInfVO tmplatInfVO) throws Exception {
	return list("TemplateManageDAO.selectTemplateInfs", tmplatInfVO);
    }

    /**
     * 템플릿에 대한 목록 전체 건수를 조회한다.
     * 
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    public int selectTemplateInfsCnt(TemplateInfVO tmplatInfVO) throws Exception {
	return (Integer)getSqlMapClientTemplate().queryForObject("TemplateManageDAO.selectTemplateInfsCnt", tmplatInfVO);
    }

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     * 
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    public TemplateInfVO selectTemplateInf(TemplateInfVO tmplatInfVO) throws Exception {
	return (TemplateInfVO)selectByPk("TemplateManageDAO.selectTemplateInf", tmplatInfVO);

    }

    /**
     * 템플릿에 대한 미리보기 정보를 조회한다.
     * 
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    public TemplateInfVO selectTemplatePreview(TemplateInfVO tmplatInfVO) throws Exception {
	return null;
    }

    /**
     * 템플릿 구분에 따른 목록을 조회한다.
     * 
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<TemplateInfVO> selectTemplateInfsByCode(TemplateInfVO tmplatInfVO) throws Exception {
	return list("TemplateManageDAO.selectTemplateInfsByCode", tmplatInfVO);
    }
	
}
