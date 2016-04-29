package clikmng.nanet.go.kr.mdm.service;

import java.util.List;
import java.util.ArrayList;

import clikmng.nanet.go.kr.mdm.model.MdmNewsVO;

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
public interface MdmNewsService {

    int selectMdmNewsListTotCnt(MdmNewsVO vo);

    List<MdmNewsVO> selectMdmNewsList(MdmNewsVO vo) throws Exception;
    
    MdmNewsVO selectMdmNewsView(MdmNewsVO vo) throws Exception;
    
    int insertMdmNews(ArrayList<MdmNewsVO> list) throws Exception;
    
}
