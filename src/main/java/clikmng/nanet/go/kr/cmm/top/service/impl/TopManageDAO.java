package clikmng.nanet.go.kr.cmm.top.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.cmm.top.service.TopManageVO;
import clikmng.nanet.go.kr.cop.faq.service.FaqManageDefaultVO;
import clikmng.nanet.go.kr.cop.faq.service.FaqManageVO;


/**
 * TOP MENU를 처리하는 DAO 클래스
 */
@Repository("TopManageDAO")
public class TopManageDAO extends EgovComAbstractDAO {


    /**
	 * TOP MENU 조회
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public List<TopManageVO> selectMenuList(TopManageVO topManageVO) throws Exception {
    	
        return list("TopManageDAO.selectMenuList", topManageVO);
        
    }
    
}
