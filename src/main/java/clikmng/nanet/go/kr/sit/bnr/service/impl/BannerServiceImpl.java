/**
 * 개요
 * - 배너에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:12
 */

package clikmng.nanet.go.kr.sit.bnr.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.sit.bnr.service.Banner;
import clikmng.nanet.go.kr.sit.bnr.service.BannerVO;
import clikmng.nanet.go.kr.sit.bnr.service.BannerService;
import clikmng.nanet.go.kr.sit.log.service.LogManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("BannerService")
public class BannerServiceImpl extends AbstractServiceImpl implements BannerService {

	@Resource(name="bannerDAO")
    private BannerDAO bannerDAO;

    /** Message ID Generation */
    @Resource(name="bannerImgMngIdGnrService")
    private EgovIdGnrService bannerImgMngIdGnrService;
	
	
	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return List - 배너 목록
	 */
	public List<BannerVO> selectBannerList(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectBannerList(bannerVO);
	}

	/**
	 * 배너목록 총 갯수를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return int - 배너 카운트 수
	 */
	public int selectBannerListTotCnt(BannerVO bannerVO) throws Exception {
		return bannerDAO.selectBannerListTotCnt(bannerVO);
	}
	
	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return BannerVO - 배너 VO
	 */
	public BannerVO selectBanner(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectBanner(bannerVO);
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 * @param banner - 배너 model
	 */
	public BannerVO insertBanner(Banner banner, BannerVO bannerVO) throws Exception{
        bannerDAO.insertBanner(banner);
        bannerVO.setBannerId(banner.getBannerId());
        return selectBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * @param banner - 배너 model
	 */
	public void updateBanner(Banner banner) throws Exception{
        bannerDAO.updateBanner(banner);
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * @param banner - 배너 model
	 */
	public void deleteBanner(Banner banner) throws Exception {
		deleteBannerFile(banner);
        bannerDAO.deleteBanner(banner);
	}

	/**
	 * 기 등록된 배너정보의 이미지파일을 삭제한다.
	 * @param banner - 배너 model
	 */
	public void deleteBannerFile(Banner banner) throws Exception{
		FileVO fileVO = (FileVO)bannerDAO.selectBannerFile(banner);
		File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
		file.delete();
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return BannerVO - 배너 VO
	 */
	public List<BannerVO> selectBannerResult(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectBannerResult(bannerVO);
	}

	//----------------------------------------------- 홍보존소식 이미지관리 시작 --------------------------------------------
	/**
	 * 홍보존 소식 이미지 관리 -- 지역코드 조회
	 * @param bannerVO - 배너 Vo
	 * @return BannerVO - 배너 Vo
	 * 
	 * @param bannerVO
	 */
	public List<BannerVO> selectAreaCodeList(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectAreaCodeList(bannerVO);
	}
	
	
	/**
	 * 홍보존 소식 이미지 관리 -- 등록
	 * @param banner - 배너 model
	 */
	public void insertBannerImgMng(BannerVO bannerVO) throws Exception{
		bannerVO.setBannerImgMngId(bannerImgMngIdGnrService.getNextStringId());
		bannerDAO.insertBannerImgMng(bannerVO);
	}
	
	/**
	 * 홍보존 소식 이미지 관리 -- 목록
	 * @param banner - 배너 model
	 */
    public Map selectBannerImgMngInfo(BannerVO bannerVO) throws Exception {
    	
		List _result = bannerDAO.selectBannerImgMngList(bannerVO);
		int _cnt = bannerDAO.selectBannerImgMngCnt(bannerVO);
		 
		Map<String, Object> _map = new HashMap();
		_map.put("resultList", _result);
		_map.put("resultCnt", Integer.toString(_cnt));
    		 
   		return _map;
   	}
    
	/**
	 * 홍보존 소식 이미지 관리 -- 상세정보
	 * @param bannerVO - 배너 Vo
	 * @return List - 배너 목록
	 * 
	 * @param bannerVO
	 */
	public BannerVO selectBannerImgMngDetail(BannerVO bannerVO) throws Exception {
		return bannerDAO.selectBannerImgMngDetail(bannerVO);
	}
    
	/**
	 * 홍보존 소식 이미지 관리 -- 수정처리
	 * @param bannerVO - 배너 Vo
	 * @return BannerVO - 배너 Vo
	 * 
	 * @param bannerVO
	 */
	public void updateBannerImgMng(BannerVO bannerVO) throws Exception{
		bannerDAO.updateBannerImgMng(bannerVO);
	}
    
	/**
	 * 홍보존 소식 이미지 관리 -- 삭제처리
	 * @param bannerVO - 배너 Vo
	 * @return BannerVO - 배너 Vo
	 * 
	 * @param bannerVO
	 */
	public void deleteBannerImgMng(BannerVO bannerVO) throws Exception{
		//deleteFileBannerImgMng(bannerVO);
        bannerDAO.deleteBannerImgMng(bannerVO);
	}
	
	/**
	 * 기 등록된 홍보존소식의 이미지파일을 삭제한다.
	 * @param banner - 배너 model
	 */
	public void deleteFileBannerImgMng(BannerVO bannerVO) throws Exception{
		FileVO fileVO = (FileVO)bannerDAO.selectBannerImgMngFile(bannerVO);
		File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
		file.delete();
	}	
    
	//----------------------------------------------- 홍보존소식 이미지관리 끝 --------------------------------------------
}