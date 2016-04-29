package clikmng.nanet.go.kr.elm.grp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.elm.grp.service.GrpManageService;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("GrpManageService")
public class GrpManageServiceImpl extends AbstractServiceImpl implements GrpManageService {

    @Resource(name="GrpManageDAO")
    private GrpManageDAO grpManageDAO;
        
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<UserClassVO> selectElmGrpList()  throws Exception{
		return grpManageDAO.selectElmGrpList();
	}

    /**
     * 관리자 등록시 중복 아이디 체크
     */
    public int selectUserGroupIdChk(String userGroupId) throws Exception {
        return grpManageDAO.selectUserGroupIdChk(userGroupId);
    }

    /**
     * 관리자 등록 및 Mapping Table insert(TNPEMPLYRSCRTYESTBS)
     */
    public void insertElmGrpRegist(UserClassVO userClassVO) throws Exception {
        grpManageDAO.insertElmGrpRegist(userClassVO);
    }
    
    /**
     * 관리자 상세내용
     */
    public UserClassVO selectElmGrpDetail(UserClassVO userClassVO) throws Exception {
        return (UserClassVO)grpManageDAO.selectElmGrpDetail(userClassVO);
    }
    
    /**
     * 관리자 수정처리
     */
    public void updateElmGrpDetail(UserClassVO userClassVO) throws Exception {
        // 관리자 수정
        grpManageDAO.updateElmGrpDetail(userClassVO);
        // 보안설정 테이블 수정
        // mngDao.editAuthorDetail(mngVO);
    }   
    
    /**
     * 관리자 삭제처리
     */
    public void deleteElmGrpDetail(UserClassVO userClassVO) throws Exception {
        // 보안설정 테이블 수정
        // mngDao.delMngMappingManage(mngVO);
        // 관리자 수정
        grpManageDAO.deleteElmGrpDetail(userClassVO);

    }

}
