package clikmng.nanet.go.kr.sts.clt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sts.clt.service.CltManageVO;



/**
 * 
 * 수집통계를 처리하는 DAO 클래스
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
@Repository("CltManageDAO")
public class CltManageDAO extends EgovComAbstractDAO {


	/**
	 * 수집통계 - 기간별
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CltManageVO> selectCltStsList(CltManageVO vo)  throws Exception{
		return list("cltManageDAO.selectCltList", vo);
	}
	
	/**
	 * 수집통계 - 기간합산
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CltManageVO> selectCltStsTermSumList(CltManageVO vo)  throws Exception{
		return list("cltManageDAO.selectCltStsTermSumList", vo);
	}

}
