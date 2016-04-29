package clikmng.nanet.go.kr.mna.ram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mna.ram.service.AuthorManage;
import clikmng.nanet.go.kr.mna.ram.service.AuthorManageVO;
import clikmng.nanet.go.kr.mna.ram.service.AuthorManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
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

@Service("AuthorManageService")
public class AuthorManageServiceImpl extends AbstractServiceImpl implements AuthorManageService {
    
	@Resource(name="authorManageDAO")
    private AuthorManageDAO authorManageDAO;

    /**
	 * 권한 목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
    public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) throws Exception {
        return authorManageDAO.selectAuthorList(authorManageVO);
    }
    
	/**
	 * 권한을 등록한다.
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
    public void insertAuthor(AuthorManage authorManage) throws Exception {
    	authorManageDAO.insertAuthor(authorManage);
    }

    /**
	 * 권한을 수정한다.
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
    public void updateAuthor(AuthorManage authorManage) throws Exception {
    	authorManageDAO.updateAuthor(authorManage);
    }

    /**
	 * 권한을 삭제한다.
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
    public void deleteAuthor(AuthorManage authorManage) throws Exception {
    	authorManageDAO.deleteAuthor(authorManage);
    }

    /**
	 * 권한을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return AuthorManageVO
	 * @exception Exception
	 */
    public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) throws Exception {
    	AuthorManageVO resultVO = authorManageDAO.selectAuthor(authorManageVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * 권한 목록 카운트를 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 * @exception Exception
	 */
    public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) throws Exception {
        return authorManageDAO.selectAuthorListTotCnt(authorManageVO);
    }    
    
    /**
	 * 모든 권한목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
	public List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO) throws Exception {
    	return authorManageDAO.selectAuthorAllList(authorManageVO);
    }      
}
