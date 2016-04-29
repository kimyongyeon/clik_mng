package clikmng.nanet.go.kr.cmm.dashBoard.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.dashBoard.service.DashBoardManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("DashBoardManageService")
public class DashBoardManageServiceImpl extends AbstractServiceImpl implements
        DashBoardManageService {

    @Resource(name="DashBoardManageDAO")
    private DashBoardManageDAO dashBoardManageDAO;
    
}
