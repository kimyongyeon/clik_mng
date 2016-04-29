package clikmng.nanet.go.kr.mdm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;

/**
 * @Class Name : EgovFileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Repository("MdmFileManageDAO")
public class MdmFileManageDAO extends EgovComAbstractDAO {

    //private static final Logger LOG = Logger.getLogger(this.getClass());

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @param fileList
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String insertFileInfs(List fileList) throws Exception {
		MdmFileVO vo = (MdmFileVO)fileList.get(0);
		String atchFileId = vo.getAtchFileId();
		
		insert("MdmFileManageDAO.insertFileMaster", vo);
	
		Iterator iter = fileList.iterator();
		while (iter.hasNext()) {
		    vo = (MdmFileVO)iter.next();
		    
		    insert("MdmFileManageDAO.insertFileDetail", vo);
		}
		
		return atchFileId;
    }

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @param vo
     * @throws Exception
     */
    public void insertFileInf(MdmFileVO vo) throws Exception {
		insert("MdmFileManageDAO.insertFileMaster", vo);
		insert("MdmFileManageDAO.insertFileDetail", vo);
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     * 
     * @param fileList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void updateFileInfs(List fileList) throws Exception {
		MdmFileVO vo;
		Iterator iter = fileList.iterator();
		while (iter.hasNext()) {
		    vo = (MdmFileVO)iter.next();
		    
		    insert("MdmFileManageDAO.insertFileDetail", vo);
		}
    }

    /**
     * 여러 개의 파일을 삭제한다.
     * 
     * @param fileList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void deleteFileInfs(List fileList) throws Exception {
		Iterator iter = fileList.iterator();
		MdmFileVO vo;
		while (iter.hasNext()) {
		    vo = (MdmFileVO)iter.next();
		    
		    delete("MdmFileManageDAO.deleteFileDetail", vo);
		}
    }

    /**
     * 하나의 파일을 삭제한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(MdmFileVO fvo) throws Exception {
    	delete("MdmFileManageDAO.deleteFileDetail", fvo);
    }

    /**
     * 파일에 대한 목록을 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MdmFileVO> selectFileInfs(MdmFileVO vo) throws Exception {
    	return list("MdmFileManageDAO.selectFileList", vo);
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(MdmFileVO fvo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MdmFileManageDAO.getMaxFileSN", fvo);
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public MdmFileVO selectFileInf(MdmFileVO fvo) throws Exception {
    	return (MdmFileVO)selectByPk("MdmFileManageDAO.selectFileInf", fvo);
    }

    /**
     * 전체 파일을 삭제한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(MdmFileVO fvo) throws Exception {
    	update("MdmFileManageDAO.deleteCOMTNFILE", fvo);
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MdmFileVO> selectFileListByFileNm(MdmFileVO fvo) throws Exception {
    	return list("MdmFileManageDAO.selectFileListByFileNm", fvo);
    }

    /**
     * 파일명 검색에 대한 목록 전체 건수를 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public int selectFileListCntByFileNm(MdmFileVO fvo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MdmFileManageDAO.selectFileListCntByFileNm", fvo);
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MdmFileVO> selectImageFileList(MdmFileVO vo) throws Exception {
    	return list("MdmFileManageDAO.selectImageFileList", vo);
    }
}
