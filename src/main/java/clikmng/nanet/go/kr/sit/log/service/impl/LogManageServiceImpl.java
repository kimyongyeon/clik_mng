package clikmng.nanet.go.kr.sit.log.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.sit.log.service.LogManageDefaultVO;
import clikmng.nanet.go.kr.sit.log.service.LogManageService;
import clikmng.nanet.go.kr.sit.log.service.LogManageVO;
import clikmng.nanet.go.kr.sym.log.clg.service.LoginLog;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("LogManageService")
public class LogManageServiceImpl extends AbstractServiceImpl implements
        LogManageService {

    @Resource(name="LogManageDAO")
    private LogManageDAO logManageDAO;
        

    /**
	 * 접속로그정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 LogManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public Map selectConnectLogListInfo(LogManageVO vo) throws Exception {
    	
		List _result = logManageDAO.selectConnectLogListInfo(vo);
		int _cnt = logManageDAO.selectConnectLogListInfoCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}
    
    /**
	 * 웹로그정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 LogManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public Map selectWebLogListInfo(LogManageVO vo) throws Exception {
    	
		List _result = logManageDAO.selectWebLogListInfo(vo);
		int _cnt = logManageDAO.selectWebLogListInfoCnt(vo);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}
    	
}
