package clikmng.nanet.go.kr.cmm.top.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.top.service.TopManageService;
import clikmng.nanet.go.kr.cmm.top.service.TopManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("TopManageService")
public class TopManageServiceImpl extends AbstractServiceImpl implements
        TopManageService {

    @Resource(name="TopManageDAO")
    private TopManageDAO topManageDAO;
    
    /**
	 * Top Left Menu 조회.
	 * @exception Exception
	 */
    public List<TopManageVO> selectMenuList(TopManageVO topManageVO) throws Exception {
    	return topManageDAO.selectMenuList(topManageVO);
    }
}
