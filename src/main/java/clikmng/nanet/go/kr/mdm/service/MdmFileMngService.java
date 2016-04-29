package clikmng.nanet.go.kr.mdm.service;

import java.util.List;
import java.util.Map;

import clikmng.nanet.go.kr.mdm.model.MdmFileVO;

/**
 * @Class Name : EgovFileMngService.java
 * @Description : 파일정보의 관리를 위한 서비스 인터페이스
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
public interface MdmFileMngService {

    /**
     * 파일에 대한 목록을 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public List<MdmFileVO> selectFileInfs(MdmFileVO fvo) throws Exception;

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public String insertFileInf(MdmFileVO fvo) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     * 
     * @param fvoList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String insertFileInfs(List fvoList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     * 
     * @param fvoList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void updateFileInfs(List fvoList) throws Exception;

    /**
     * 여러 개의 파일을 삭제한다.
     * 
     * @param fvoList
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void deleteFileInfs(List fvoList) throws Exception;

    /**
     * 하나의 파일을 삭제한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(MdmFileVO fvo) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public MdmFileVO selectFileInf(MdmFileVO fvo) throws Exception;

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(MdmFileVO fvo) throws Exception;

    /**
     * 전체 파일을 삭제한다.
     * 
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(MdmFileVO fvo) throws Exception;

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectFileListByFileNm(MdmFileVO fvo) throws Exception;

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    public List<MdmFileVO> selectImageFileList(MdmFileVO vo) throws Exception;
}
