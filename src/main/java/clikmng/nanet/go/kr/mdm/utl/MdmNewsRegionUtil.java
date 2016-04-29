package clikmng.nanet.go.kr.mdm.utl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;





import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.EgovWebUtil;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;

//import clikmng.nanet.go.kr.cmm.EgovWebUtil;
//import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.mdm.model.MdmNewsVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmDetailCodeVO;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;


import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;

public class MdmNewsRegionUtil 
{
	
	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;
	

	HashMap regionMap = new HashMap();
	
	public MdmNewsRegionUtil()
	{
	}
	
	public void initNewsys() throws Exception 
	{
//		System.out.println("################################################");
//		System.out.println("initNewsys");
//		System.out.println("### 1");
		regionMap = new HashMap();
//		System.out.println("### 2");
		CmmCodeVO cmmCodeVO = new CmmCodeVO();
//		System.out.println("### 3");
		//cmmCodeVO.setCodeId("NRC200");
		cmmCodeVO.setCodeId("ELA004");
		System.out.println("### 4");
		try
		{
			List svcScopeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		}
		catch(IOException ioe)
		{
			
		}
//		System.out.println("### 5");
//			System.out.println("###########################################################");
//			System.out.println(cmmCodeVO.getCodeId());
//			System.out.println(cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO));
//			System.out.println("###########################################################");
			
			

			//System.out.println("Size ===> " + regionList.size() );
/*
			for(Object obj : regionList)
			{


				System.out.println("obj ===> " + obj);


//				//regionMap.put( vo.getCode(), vo.getCodeDc() );
			}
			*/
	}
	

	public EgovMap searchRegionCode(List<EgovMap> regionList, String category)
	{
//		System.out.println("########################################################");
//		System.out.println("searchRegionCode");
//		System.out.println("category ===> " + category);
		EgovMap result = new EgovMap();

		if(category != null && "".equals(category) == false)
		{
			for(EgovMap obj : regionList)
			{
//				System.out.println("obj ===> " + obj);
//				System.out.println( category.indexOf( (String)obj.get("code") ) ); 
				
				if(obj.isEmpty() == false)
				{
					if( category.indexOf( (String)obj.get("code") ) > -1 )
					{
						result = obj;
					}
				}
			}
		}
//		System.out.println("########################################################");
		return result;
	}

	public FileVO moveImgFile(String atchFileId, String sn, File file, String localSavePath)
	{
//		System.out.println("##############################################################");
//		System.out.println("moveImgFile");
		
		String		orginFileName			=	file.getName();

	    int			index					=	orginFileName.lastIndexOf(".");
	    String		fileExt					=	orginFileName.substring(index + 1);
	    String		newName					=	"Newsys_" + CommonUtil.getTimeStamp() + sn;
	    long 		_size 					= 	file.length();
	    String 		filePath				=	localSavePath;

	    FileVO 		fvo 					= 	new FileVO();

		try
		{
		
			File saveDir = new File(EgovWebUtil.filePathBlackList(localSavePath));

//			System.out.println("before saveDir.exists() ===> " + saveDir.exists());

			if(saveDir.exists() == false || saveDir.isFile())
			{
				saveDir.mkdirs();
			}
//			System.out.println("after saveDir.exists() ===> " + saveDir.exists());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
		    if (file.length() > 0 && file.isFile() == true) 
		    {
		    	filePath = localSavePath + File.separator + newName;
		    }

//		    System.out.println("Before move File ===> " + filePath);
		    file.renameTo(new File(EgovWebUtil.filePathBlackList(filePath)));
//		    System.out.println("After move File ===> " + file.getAbsolutePath());

		    fvo.setFileExtsn(fileExt);
		    fvo.setFileStreCours(localSavePath);
		    fvo.setFileMg(Long.toString(_size));
		    fvo.setOrignlFileNm(orginFileName);
		    fvo.setStreFileNm(newName);
		    fvo.setAtchFileId(atchFileId);
		    fvo.setFileSn(sn);
		    
//		    System.out.println(fvo);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

//		System.out.println("#########################################################");
		return fvo;
	}

	
	
	
	public String getDateFormat(String date, String from, String to)
	{
		SimpleDateFormat fromFormat		=	new SimpleDateFormat(from);
		SimpleDateFormat toFormat		=	new SimpleDateFormat(to);

		Date  fromDate = null;
		
		try
		{
		
			fromDate = fromFormat.parse(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return toFormat.format(fromDate);

	}
	
	
	public MdmNewsVO setNewsysVO(String regionCode, String regionName, 
			String title, String content,
			String atchFileId, 
			String regdate, String writer, 
			String articleId)
	{
		return setMdmNewsVO(regionCode, regionName, 
				title, content,
				atchFileId, 
				regdate, writer, 
				articleId,
				"200", "뉴시스", "1", "1", "1"); 
	}


	public MdmNewsVO setYonhapVO(String regionCode, String regionName, 
			String title, String content,
			String atchFileId, 
			String regdate, String writer, 
			String articleId)
	{
		MdmNewsVO vo = new MdmNewsVO();
		
		return setMdmNewsVO(regionCode, regionName, 
				title, content,
				atchFileId, 
				regdate, writer, 
				articleId,
				"100", "연합뉴스", "1", "1", "1"); 
	}
	
	public MdmNewsVO setMdmNewsVO(String regionCode, String regionName, 
			String title, String content,
			String atchFileId, 
			String date, String writer, 
			String articleId,
			String seedId, String seedNm,
			String cud, String auto, String isView)
	{
		MdmNewsVO vo = new MdmNewsVO();
		
		vo.setRegion(regionCode);
		vo.setRegionNm(regionName);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setAtchFileId(atchFileId);
		vo.setRegdate(date);
		vo.setWriter(writer);
		vo.setArticleId(articleId);
		vo.setSeedId(seedId);
		vo.setSeedNm(seedNm);
		vo.setCud(cud);
		vo.setAuto(auto);
		vo.setIsView(isView);

		return vo;
	}


}
