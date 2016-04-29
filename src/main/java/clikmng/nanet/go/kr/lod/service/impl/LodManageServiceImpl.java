package clikmng.nanet.go.kr.lod.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.lod.service.LodManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("LodManageService")
public class LodManageServiceImpl extends AbstractServiceImpl implements LodManageService {

    @Resource(name="LodManageDAO")
    private LodManageDAO LodManageDAO;
        
    /** ID Generation */
/*    
	@Resource(name="egovCsmManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
    
}
