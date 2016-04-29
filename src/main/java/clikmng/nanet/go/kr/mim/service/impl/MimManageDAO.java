package clikmng.nanet.go.kr.mim.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mim.service.MimManageDefaultVO;
import clikmng.nanet.go.kr.mim.service.MimManageVO;
import clikmng.nanet.go.kr.sit.log.service.LogManageVO;

/**
 * 
 * 메일정보를 처리하는 DAO 클래스
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
@Repository("MimManageDAO")
public class MimManageDAO extends EgovComAbstractDAO {
	
	
	
	
    /**
     * 그룹설정 - 목록
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectGroupList(MimManageVO vo) throws Exception {
    	
        return list("MimManageDAO.selectGroupList", vo);
        
    }
	
    /**
     * 그룹설정 - 목록 갯수
     * @param searchVO
     * @return
     */
    public int selectGroupListCnt(MimManageVO vo) throws Exception {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectGroupListCnt", vo);
        
    }	
	
    /**
     * 지방의원 목록
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectPopSearchRasmblyListInfo(MimManageVO vo) throws Exception {
    	
        return list("MimManageDAO.selectPopSearchRasmblyListInfo", vo);
        
    }
	
    /**
     * 지방의원 목록 갯수
     * @param searchVO
     * @return
     */
    public int selectPopSearchRasmblyListCnt(MimManageVO vo) throws Exception {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectPopSearchRasmblyListCnt", vo);
        
    }

    
    /**
     * 지방의회 및 지자체 담당자 목록
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectPopSearchRasmblyChargerListInfo(MimManageVO vo) throws Exception {
    	
        return list("MimManageDAO.selectPopSearchRasmblyChargerListInfo", vo);
        
    }
	
    /**
     * 지방의회 및 지자체 담당자 목록 갯수
     * @param searchVO
     * @return
     */
    public int selectPopSearchRasmblyChargerListInfoCnt(MimManageVO vo) throws Exception {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectPopSearchRasmblyChargerListInfoCnt", vo);
        
    }
    
    /**
     * 메일링 - 그룹 등록
     * @param searchVO
     * @return
     */
    public void insertGroupInfo(MimManageVO vo) throws Exception {
    	
        insert("MimManageDAO.insertGroupInfo", vo);
        
    }
    
    /**
     * 메일링 - 그룹 구성원 등록
     * @param searchVO
     * @return
     */
    public void insertGroupDtlsInfo(MimManageVO vo) throws Exception {
    	
        insert("MimManageDAO.insertGroupDtlsInfo", vo);
        
    }
    
    /**
     * 메일링 - 그룹 정보
     * @param searchVO
     * @return
     */
    public MimManageVO selectGroupDetailInfo(MimManageVO vo) throws Exception {
    	
        return (MimManageVO)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectGroupDetailInfo", vo);
        
    }
    
    /**
     * 메일링 - 그룹 구성원 목록
     * @param searchVO
     * @return
     */
    public List selectGroupDetailDtlsInfo(MimManageVO vo) throws Exception {
    	
        return list("MimManageDAO.selectGroupDetailDtlsInfo", vo);
        
    }    
    
    /**
     * 메일링 - 그룹 수정
     * @param searchVO
     * @return
     */
    public void updateGroupInfo(MimManageVO vo) throws Exception {
    	
        insert("MimManageDAO.updateGroupInfo", vo);
        
    }
    
    /**
     * 메일링 - 그룹 구성원 삭제
     * @param searchVO
     * @return
     */
    public void deleteGroupDtlsInfo(MimManageVO vo) throws Exception {
    	
        delete("MimManageDAO.deleteGroupDtlsInfo", vo);
        
    }
    
    /**
     * 메일링 - 그룹 삭제
     * @param searchVO
     * @return
     */
    public void deleteGroupInfo(MimManageVO vo) throws Exception {
    	
        delete("MimManageDAO.deleteGroupInfo", vo);
        
    }
    
    
    //######################################  메일 발송 시작 ########################################//
	
	/**
	 * 메일링 -  팝업 그룹 조회
	 * @param searchVO
	 * @throws Exception
	 */
    public List selectPopSearchGroupListInfo(MimManageVO mimManageVO) throws Exception {
    	
        return list("MimManageDAO.selectPopSearchGroupListInfo", mimManageVO);
        
    }
	
    /**
     * 메일링 -  팝업 그룹 조회 갯수
     * @param searchVO
     * @return
     */
    public int selectPopSearchGroupListInfoCnt(MimManageVO mimManageVO) throws Exception {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectPopSearchGroupListInfoCnt", mimManageVO);
        
    }
    
    /**
     * 메일링 - 메일 발송 정보 저장
     * @param searchVO
     * @return
     */
    public void  insertSendMailInfo(MimManageVO vo) throws Exception {
    	
        insert("MimManageDAO.insertSendMailInfo", vo);
        
    }
                
    /**
     * 메일링 - 메일 발송 상세정보 저장
     * @param searchVO
     * @return
     */
    public void  insertSendMailDetailInfo(MimManageVO vo) throws Exception {
    	
        insert("MimManageDAO.insertSendMailDetailInfo", vo);
        
    }    
    
    /**
	 * 메일링 -  수신거부 수신자 제외
	 * @param String rcvrEmail
	 * @throws Exception
	 */
	public List selectRejectRcvr(MimManageVO vo) throws Exception {
		return list("MimManageDAO.selectRejectRcvr", vo);
	}
    
	/**
	 * 메일링 -  발송완료 후 발송상태 수정 및 수신자거부 명수 수정
	 * @param MimManageVO
	 * @throws Exception
	 */
	public void updateSendMailStatus(MimManageVO mimManageVO) throws Exception {
		update("MimManageDAO.updateSendMailStatus", mimManageVO);
	}
    
    /**
     * 메일링 - 그룹 조회
     * @param searchVO
     * @return
     */
    public List  selectSearchGroupAjax(MimManageVO vo) throws Exception {
    	
        return list("MimManageDAO.selectSearchGroupAjax", vo); 
        
    }        
    
    /**
     * 메일링 - 양식선택
     * @param 
     * @return
     */
    public List  selectFormList() throws Exception {
    	
        return list("MimManageDAO.selectFormList", ""); 
        
    }  
    
    
    /**
     * 메일링 - 양식선택
     * @param 
     * @return
     */
    public String  selectChangeFormAjax(MimManageVO vo) throws Exception {
    	
        return (String)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectChangeFormAjax", vo); 
        
    }     
	
    //######################################  메일 발송 끝 ########################################//	    
    
    
    //######################################  메일 발송 목록 시작 ########################################//
	
	/**
	 * 메일링 -  발송목록 내역
	 * @param 
	 * @throws Exception
	 */
    public List selectSendMailListInfo(MimManageVO vo) throws Exception {
    	return list("MimManageDAO.selectSendMailListInfo", vo); 
   	}	   
    
    
	/**
	 * 메일링 -  발송목록 내역
	 * @param 
	 * @throws Exception
	 */
    public int selectSendMailListCnt(MimManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectSendMailListCnt", vo);
   	}	   
    
	
    //######################################  메일 발송 목록 끝 ########################################//	 
    
    
    //######################################  메일 수신거부 목록 시작 ########################################//
	
	/**
	 * 메일링 -  수신거부목록 내역
	 * @param 
	 * @throws Exception
	 */
    public List selectRejectEmailList(MimManageVO vo) throws Exception {
    	return list("MimManageDAO.selectRejectEmailList", vo); 
   	}	   
    
    
	/**
	 * 메일링 -  수신거부목록 갯수
	 * @param 
	 * @throws Exception
	 */
    public int selectRejectEmailListCnt(MimManageVO vo) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectRejectEmailListCnt", vo);
   	}	   
    
	/**
	 * 메일링 -  수신거부정보 중복확인
	 * @param 
	 * @throws Exception
	 */
	public int selectConfirmEmail(String rejectEmail) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("MimManageDAO.selectConfirmEmail", rejectEmail);
	}
    
	/**
	 * 메일링 -  수신거부정보 등록
	 * @param 
	 * @throws Exception
	 */
	public void insertRejectEmailInfo(HashMap<String, String> rejectInfoMap) throws Exception{
		insert("MimManageDAO.insertRejectEmailInfo", rejectInfoMap);
	}
	
    //######################################  메일 수신거부 목록 끝 ########################################//	     
    
    
    
}
