package clikmng.nanet.go.kr.mdm.service;

import java.util.List;

import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmRegionNewsVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileDetailVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpFileVO;

/**
 * 
 * 모바일 관리 처리하는 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */

public interface MdmRegionNewsService {

	int selectMdmOutBbsSeq() throws Exception;

	int selectMdmOutBbsFileSeq() throws Exception;

	int selectMdmOutNewsFileSn(String ATCH_FILE_ID) throws Exception;

	String selectMdmRegionNewsMaxRegDate(String TODAY) throws Exception;
	
	int selectMdmRegionNewsDfltListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmRegionNewsVO> selectMdmRegionNewsDfltList(MdmSearchVO vo) throws Exception;

	int selectMdmRegionNewsListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmRegionNewsVO> selectMdmRegionNewsList(MdmSearchVO vo) throws Exception;
    
	int selectMdmRegionNewsDplctListTotCnt(MdmSearchVO vo) throws Exception;
	
    List<MdmRegionNewsVO> selectMdmRegionNewsDplctList(MdmSearchVO vo) throws Exception;

    MdmRegionNewsVO selectMdmRegionNewsView(String NEWS_ID) throws Exception;
    
    List<MdmTnpFileDetailVO> selectMdmRegionNewsFileList(String ATCH_FILE_ID) throws Exception;
    
    int selectMdmRegionNewsFileExist(String ATCH_FILE_ID) throws Exception;
    
	void insertMdmRegionNews(MdmRegionNewsVO vo) throws Exception;
	
	void insertMdmRegionNewsFile(MdmTnpFileVO vo) throws Exception;
	
	void insertMdmRegionNewsFileDetail(MdmTnpFileDetailVO vo) throws Exception;
 
	void updateMdmRegionNews(MdmRegionNewsVO vo) throws Exception;
	
	void deleteMdmRegionNews(MdmRegionNewsVO vo) throws Exception;
	
	void deleteMdmRegionNewsFile(MdmTnpFileDetailVO vo) throws Exception;

	void updateMdmRegionNewsIsView(MdmIsViewVO vo) throws Exception;
	
	void deleteMdmRegionNewsChk(MdmIsViewVO vo) throws Exception;
	
}
