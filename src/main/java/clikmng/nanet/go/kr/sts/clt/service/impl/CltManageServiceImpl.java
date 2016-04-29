package clikmng.nanet.go.kr.sts.clt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.sts.clt.service.CltManageService;
import clikmng.nanet.go.kr.sts.clt.service.CltManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 수집통계를 처리하는 구현 클래스
 */
@Service("CltManageService")
public class CltManageServiceImpl extends AbstractServiceImpl implements CltManageService {

    @Resource(name="CltManageDAO")
    private CltManageDAO CltManageDAO;
        
	/**
	 * 수집통계 - 기간별
	 */
    @Override
	public List selectCltStsList(CltManageVO CltManageVO) throws Exception {
		return (List)CltManageDAO.selectCltStsList(CltManageVO);
	}

    /**
	 * 수집통계 - 기간합산
	 */
	@Override
	public List selectCltStsTermSumList(CltManageVO CltManageVO) throws Exception {
		return (List)CltManageDAO.selectCltStsTermSumList(CltManageVO);
	}

}
