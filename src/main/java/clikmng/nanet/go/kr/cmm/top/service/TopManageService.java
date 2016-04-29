package clikmng.nanet.go.kr.cmm.top.service;

import java.util.List;

/**
 * TOP LEFT MENU를 처리하는 서비스 클래스
 */
public interface TopManageService {
	    
    /**
	 * TOP LEFT MENU를 조회
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	public List<TopManageVO> selectMenuList(TopManageVO topManageVO) throws Exception;

}
