package clikmng.nanet.go.kr.elm.cup.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.CopyrightPermVO;
import clikmng.nanet.go.kr.elm.cup.service.ElmCupListVO;
import clikmng.nanet.go.kr.elm.cup.service.CupManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 *
 * 저작권허락 권한  클래스
 */
@Service("CupManageService")
public class CupManageServiceImpl extends AbstractServiceImpl implements CupManageService {

    @Resource(name="CupManageDAO")
    private CupManageDAO cupManageDAO;

	/**
     * 저작권허락 권한 리스트
     * @param CopyrightPermVO
     * @return
     * @throws Exception
     */
	public int selectElmCupListTotCnt(ElmCupListVO vo)  throws Exception{
		return cupManageDAO.selectElmCupListTotCnt(vo);
	}

    public List<ElmCupListVO> selectElmCupList(ElmCupListVO vo)  throws Exception{
		return cupManageDAO.selectElmCupList(vo);
	}

    public List<ElmCupListVO> selectElmCupListAjax(ElmCupListVO vo)  throws Exception{
		return cupManageDAO.selectElmCupListAjax(vo);
	}

	/**
	 * 저작권허락 권한 등록
	 * @param CopyrightPermVO
	 *
	 */
    public void insertElmCupRegist(CopyrightPermVO copyrightPermVO) throws Exception {
        cupManageDAO.insertElmCupRegist(copyrightPermVO);
    }

	/**
	 * 저작권허락 권한 조회
	 * @param CopyrightPermVO
	 * @return CopyrightPermVO
	 *
	 * @param mngVO
	 */
    public CopyrightPermVO selectElmCupDetail(CopyrightPermVO vo) throws Exception {
        return (CopyrightPermVO)cupManageDAO.selectElmCupDetail(vo);
    }

	/**
	 * 저작권허락 권한 수정
	 * @param CopyrightPermVO
	 */
    public void updateElmCupDetail(CopyrightPermVO copyrightPermVO) throws Exception {
        cupManageDAO.updateElmCupDetail(copyrightPermVO);
    }

	/**
	 * 저작권허락 권한 삭제
	 * @param CopyrightPermVO
	 */
    public void deleteElmCupDetail(CopyrightPermVO copyrightPermVO) throws Exception {
        cupManageDAO.deleteElmCupDetail(copyrightPermVO);
    }
    
	/**
	 * 저작권허락 권한 아이콘 이미지파일을 삭제한다.
	 * @param copyrightPermVO
	 */
	public void deleteIconFile(CopyrightPermVO copyrightPermVO) throws Exception{
		FileVO fileVO = (FileVO)cupManageDAO.selectIconFile(copyrightPermVO);
		if(fileVO != null)
		{
			File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
			file.delete();
		}
	}
	
    
    
}
