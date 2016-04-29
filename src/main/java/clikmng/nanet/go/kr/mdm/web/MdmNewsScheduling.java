package clikmng.nanet.go.kr.mdm.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.w3c.dom.Document;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.mdm.model.MdmNewsVO;
import clikmng.nanet.go.kr.mdm.service.MdmNewsService;
import clikmng.nanet.go.kr.mdm.utl.MdmFtpUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmNewsRegionUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmXmlUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;




/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */

@Service("MdmNewsScheduling")
public class MdmNewsScheduling {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmNewsService")
    private MdmNewsService mdmNewsService;

    //** EgovPropertyService *//
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;

	//** EgovMessageSource *//
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "NewsImgFileIdGnrService")
    private EgovIdGnrService idgenService;
    
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 파잉된 뉴스 읽기
     * @param mdmNewsVO
     * @param model
     * @return	"/Mdm/MdmNews"
     * @throws Exception
     */
    public void NewsCrawling() throws Exception {

    	// 뉴시스 크롤링

    	log.debug("############################################ 뉴시스 크롤링 시작 ########################################");
    	ArrayList<MdmNewsVO> newsysList = new ArrayList();
    	log.debug("############################################ 크롤링 & 파싱 ########################################");    	
    	newsysList = this.getNewsis();
    	log.debug("############################################ DB Insert ########################################");    	
    	mdmNewsService.insertMdmNews(newsysList);
    	log.debug("############################################ 뉴시스 크롤링 끝 ########################################");
    	
    	// 연합뉴스 크롤링
    	log.debug("############################################ 연합뉴스 크롤링 시작 ########################################");
    	ArrayList<MdmNewsVO> yonhapList = new ArrayList();
    	log.debug("############################################ 크롤링 & 파싱 ########################################");    	
    	yonhapList = this.getYonhap();
    	log.debug("############################################ DB Insert ########################################");    	
    	mdmNewsService.insertMdmNews(yonhapList);
    	log.debug("############################################ 연합뉴스 크롤링 끝 ########################################");

    	try {
    		String[] command = {"/clik-search/search/batch/dynamic/dyn_new.sh"};
        	shellCommand(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
   
    // 뉴시스 FTP 기사 추출
    public ArrayList<MdmNewsVO> getNewsis() throws Exception 
    {
    	
    	String 		ip 					= EgovProperties.getProperty("ftp.newsis.ip");
    	int 		port 				= Integer.parseInt(EgovProperties.getProperty("ftp.newsis.port"));
    	String 		username 			= EgovProperties.getProperty("ftp.newsis.id");
    	String 		password 			= EgovProperties.getProperty("ftp.newsis.password");
    	String 		ftpPath 			= EgovProperties.getProperty("ftp.newsis.ftpPath");
    	String 		localSavePath		= EgovProperties.getProperty("ftp.newsis.fileStorePath");
    	int			_limit				= Integer.parseInt(EgovProperties.getProperty("ftp.newsis.limit"));
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        
        localSavePath = localSavePath + sdf1.format(cal.getTime()) + "/newsis/" + sdf2.format(cal.getTime()) + "/"; 
    	
    	boolean		isProcess	= 	true;
    	boolean		isDelete	= 	true;
    	
    	
    	FTPClient	client = new FTPClient();
    	MdmFtpUtil	ftpUtil = new MdmFtpUtil();
    	MdmXmlUtil	xmlUtil = new MdmXmlUtil();
    	
    	MdmNewsRegionUtil	regionUtil	=	new MdmNewsRegionUtil();

    	
    	ArrayList<FTPFile> 		ftpFileList		= new ArrayList();
    	ArrayList<File> 		downloadList	= new ArrayList();
    	ArrayList<File> 		imageList		= new ArrayList();
    	ArrayList<MdmNewsVO> 	resultList		= new ArrayList();
    	
    	int						processCount	= 0;

    	int						allFileCount	=	0;

    	int						fileCount		=	0;
    	int						zeroFileCount	=	0;

    	int						xmlCount		=	0;
    	int						noXmlCount		=	0;
    	int						zeroXmlCount	=	0;

    	int						regionCount		=	0;
    	int						noRegionCount	=	0;
    	
    	int						imgCount		=	0;
    	int						imgRegionCount	=	0;
    	int						imgZeroCount	=	0;
    	int						imgNoRegionCount	=	0;
    	
    	
    	CmmCodeVO 				cmmCodeVO 		= new CmmCodeVO();


		// 뉴시스 지역코드
		cmmCodeVO.setCodeId("NRC200");
		List<EgovMap>			regionList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

		// 국회도서관 지역 코드
		cmmCodeVO.setCodeId("RKS025");
		List<EgovMap> 			codeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

		// 등록된 지역 코드가 없으면 중지
		if(regionList.isEmpty() == true || codeList.isEmpty() == true)
		{
			isProcess = false;
		}

		// FTP 연결이 안되면 중지
		if(isProcess == true)
		{
    		client		=	ftpUtil.getClient(ip, port, username, password);
    		ftpUtil.changeWorkDirectory(client, ftpPath);
		}

		if(client.isConnected() == false)
		{
			isProcess = false;
		}
		
		// FTP 파일 리스트 목록 조회
		if(isProcess == true)
		{
			ftpFileList = ftpUtil.ftpFileList(client);
		}

		// ftp 목록 검색
		allFileCount = ftpFileList.size();
		
		for(FTPFile ftpFile : ftpFileList)
		{

			processCount++;
			
			// 파일이 0이 아닌것들
   			if(ftpFile.getSize() > 0)
   			{
   				fileCount++;
   				isProcess = true;
   			}
   			else
   			{
   				zeroFileCount++;
   				if(isDelete == true) ftpUtil.ftpDelete(client, ftpFile.getName());
   				isProcess = false;
   			}

   			File 		localFile 			= 	null;
   			File 		localImageFile		=	null;

   			FileVO 		fileVO				=	new FileVO();
   			
   			Document 	doc					=	null;
   			EgovMap 	region				=	null;

   			String		category			=	null;
   			String 		img_url				=	null;

   			String 		atchFileId			=	null;
			String 		regionCode 			=	null;
			String 		regionName 			=	null;
			String 		title 				=	null;
			String 		content 			=	null;
			String 		writer 				=	null;
			String 		article_id 			=	null;
			String 		regdate				=	null;


   			// xml 파일 검사
   			if(isProcess == true && ftpFile.getName().endsWith("xml") == true)
   			{
   				// xml 파일 받기
   				localFile = ftpUtil.ftpDownload(client, ftpFile.getName(), localSavePath, isDelete);

   				// 다운로드 받은 파일 0 이상인것만
   				if(localFile.length() > 0)
   				{
   					xmlCount++;
   					doc 				=	xmlUtil.xmlParser(localFile.getAbsolutePath());
   					category			=	xmlUtil.getValue(doc, "CATEGORY");
   					img_url 			= 	xmlUtil.getValue(doc, "IMG_URL").trim();

	   				region				=	regionUtil.searchRegionCode(regionList, category);

   					downloadList.add(localFile);
   					localFile.delete();
   					isProcess			=	true;
   				}
   				else
   				{
   					zeroXmlCount++;
   					localFile.delete();
   					isProcess			=	false;
   				}
   			}
   			else
   			{
   				noXmlCount++;
   				isProcess = false;
   			}

   			// 지역 코드 조회
   			if( isProcess == true )
   			{

   				// 지역 코드 조회 된것만
	   			if(region != null && region.isEmpty() == false)
	   			{
	   				
	   				regionCount++;
	   				
	   				// 전자도서관 내 지역 코드로 변환
	   				regionCode 			= 	(String)region.get("codeDc");
	   				regionName 			= 	"";
	
	   				for(EgovMap code : codeList)
					{
						if(regionCode.equals((String)code.get("code")) == true)
						{
							regionName = (String)code.get("codeNm");
						}
					}

	   				isProcess = true;
	   			}
	   			else
	   			{
	   				noRegionCount++;
	   				isProcess = false;
	   			}


	   			// 이미지 url 이 존재할 때 
				if(img_url != null && "".equals(img_url)== false)
				{
					imgCount++;
					
					// 지역코드가 있으면
					if(isProcess == true)
					{

						localImageFile			=	ftpUtil.ftpDownload(client, img_url, localSavePath, isDelete);
						
						if(localImageFile.length() > 0)
						{
							imgRegionCount++;
							
							atchFileId			=	idgenService.getNextStringId();
							fileVO				=	regionUtil.moveImgFile(atchFileId, "0", localImageFile, localSavePath);
							atchFileId			=	fileMngService.insertFileInf(fileVO);
							
							imageList.add(localImageFile);
						}
						else
						{
							imgZeroCount++;
							localImageFile.delete();
						}

					}
					else
					{
						imgNoRegionCount++;
						
						// 지역코드가 없으면 삭제
						if(isDelete == true)
						{
							ftpUtil.ftpDelete(client, img_url);
						}
					}
				}
				
				
				if(isProcess == true)
				{
					// VO 만들기
					regdate 		= 	xmlUtil.getValue(doc, "DATE") + " " + xmlUtil.getValue(doc, "TIME");
					regdate 		= 	regionUtil.getDateFormat(regdate, "yyyy-MM-dd hh:mm:ss", "yyyy.MM.dd hh:mm:ss");
					
					title			=	xmlUtil.getValue(doc, "TITLE");
					content			=	xmlUtil.getValue(doc, "CONTENT");
					writer			=	xmlUtil.getValue(doc, "WRITER");
					article_id		=	xmlUtil.getValue(doc, "NSID");
					
					MdmNewsVO vo = regionUtil.setNewsysVO(
							regionCode,
							regionName,
							title, 
							content,
							atchFileId,
							regdate,
							writer,
							article_id
					);
					
					resultList.add(vo);
				}

   			}

   			// 검색 제한
			if(_limit != 0 && downloadList.size() > _limit)
			{
				break;
			}
		}
		
		
		log.debug("############### Newsis Process End ##################");
		log.debug("processCount ===> " + processCount);

		log.debug("allFileCount ===> " + allFileCount);

		log.debug("fileCount ===> " + fileCount);
		log.debug("zeroFileCount ===> " + zeroFileCount);

		log.debug("xmlCount ===> " + xmlCount);
		log.debug("zeroXmlCount ===> " + zeroXmlCount);
		log.debug("noXmlCount ===> " + noXmlCount);
		

		log.debug("regionCount ===> " + regionCount);
		log.debug("noRegionCount ===> " + noRegionCount);
    	
		log.debug("imgCount ===> " + imgCount);
		log.debug("imgRegionCount ===> " + imgRegionCount);
		log.debug("imgZeroCount ===> " + imgZeroCount);
		log.debug("imgNoRegionCount ===> " + imgNoRegionCount);

		log.debug("#############################################################");
		
		return resultList;
    }



    //연합뉴스 FTP 기사 추출
    public ArrayList<MdmNewsVO> getYonhap() throws Exception 
    {
    	
    	String 		ip 					= EgovProperties.getProperty("ftp.yonhap.ip");
    	int 		port 				= Integer.parseInt(EgovProperties.getProperty("ftp.yonhap.port"));
    	String 		username 			= EgovProperties.getProperty("ftp.yonhap.id");
    	String 		password 			= EgovProperties.getProperty("ftp.yonhap.password");
    	String 		ftpPath 			= EgovProperties.getProperty("ftp.yonhap.ftpPath");
    	String 		localSavePath		= EgovProperties.getProperty("ftp.yonhap.fileStorePath");
    	int			_limit				= Integer.parseInt(EgovProperties.getProperty("ftp.yonhap.limit"));
    
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        
        localSavePath = localSavePath + sdf1.format(cal.getTime()) + "/yonhap/" + sdf2.format(cal.getTime()) + "/"; 

    	boolean		isProcess	= 	true;
    	boolean		isDelete	= 	true;

    	
    	FTPClient	client = new FTPClient();
    	MdmFtpUtil	ftpUtil = new MdmFtpUtil();
    	MdmXmlUtil	xmlUtil = new MdmXmlUtil();
    	
    	MdmNewsRegionUtil	regionUtil	=	new MdmNewsRegionUtil();

    	
    	ArrayList<FTPFile> 		ftpFileList		= new ArrayList();
    	ArrayList<File> 		downloadList	= new ArrayList();
    	ArrayList<File> 		imageList		= new ArrayList();
    	ArrayList<MdmNewsVO> 	resultList		= new ArrayList();
    	
    	int						processCount	= 0;

    	int						allFileCount	=	0;

    	int						fileCount		=	0;
    	int						zeroFileCount	=	0;

    	int						xmlCount		=	0;
    	int						noXmlCount		=	0;
    	int						zeroXmlCount	=	0;

    	int						regionCount		=	0;
    	int						noRegionCount	=	0;
    	
    	int						imgCount		=	0;
    	int						imgRegionCount	=	0;
    	int						imgZeroCount	=	0;
    	int						imgNoRegionCount	=	0;
    	
    	
    	CmmCodeVO 				cmmCodeVO 		= new CmmCodeVO();


		// 연합뉴스 지역코드
		cmmCodeVO.setCodeId("NRC100");
		List<EgovMap>			regionList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

		// 국회도서관 지역 코드
		cmmCodeVO.setCodeId("RKS025");
		List<EgovMap> 			codeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);

		// 등록된 지역 코드가 없으면 중지
		if(regionList.isEmpty() == true || codeList.isEmpty() == true)
		{
			isProcess = false;
		}

		// FTP 연결이 안되면 중지
		if(isProcess == true)
		{
    		client		=	ftpUtil.getClient(ip, port, username, password);
    		ftpUtil.changeWorkDirectory(client, ftpPath);
		}

		if(client.isConnected() == false)
		{
			isProcess = false;
		}
		
		// FTP 파일 리스트 목록 조회
		if(isProcess == true)
		{
			ftpFileList = ftpUtil.ftpFileList(client);
		}

		// ftp 목록 검색
		allFileCount = ftpFileList.size();
		
		for(FTPFile ftpFile : ftpFileList)
		{

			processCount++;

			// 파일이 0이 아닌것들
   			if(ftpFile.getSize() > 0)
   			{
   				fileCount++;
   				isProcess = true;
   			}
   			else
   			{
   				zeroFileCount++;
   				if(isDelete == true) ftpUtil.ftpDelete(client, ftpFile.getName());
   				isProcess = false;
   			}

   			File 		localFile 			= 	null;
   			File 		localImageFile		=	null;

   			FileVO 		fileVO				=	new FileVO();
   			
   			Document 	doc					=	null;
   			EgovMap 	region				=	null;

   			String		category			=	null;
   			String 		img_url				=	null;

   			String 		atchFileId			=	null;
			String 		regionCode 			=	null;
			String 		regionName 			=	null;
			String 		title 				=	null;
			String 		content 			=	null;
			String 		writer 				=	null;
			String 		article_id 			=	null;
			String 		regdate				=	null;

   			// xml 파일 검사
   			if(isProcess == true && ftpFile.getName().endsWith("xml") == true)
   			{
   				// xml 파일 받기
   				localFile = ftpUtil.ftpDownload(client, ftpFile.getName(), localSavePath, isDelete);

   				// 다운로드 받은 파일 0 이상인것만
   				if(localFile.length() > 0)
   				{
   					xmlCount++;
   					doc 				=	xmlUtil.xmlParser(localFile.getAbsolutePath());
   					category			=	xmlUtil.getAttribute(doc, "RegionCode").get("code");
   					img_url 			= 	xmlUtil.getValue(doc, "IMG_URL").trim();

	   				region				=	regionUtil.searchRegionCode(regionList, category);

   					downloadList.add(localFile);
   					localFile.delete();
   					isProcess			=	true;
   				}
   				else
   				{
   					zeroXmlCount++;
   					localFile.delete();
   					isProcess			=	false;
   				}
   			}
   			else
   			{
   				noXmlCount++;
   				isProcess = false;
   			}

   			// 지역 코드 조회
   			if( isProcess == true )
   			{

   				// 지역 코드 조회 된것만
	   			if(region != null && region.isEmpty() == false)
	   			{
	   				
	   				regionCount++;
	   				// 전자도서관 내 지역 코드로 변환
	   				regionCode 			= 	(String)region.get("codeDc");
	   				regionName 			= 	"";
	
	   				for(EgovMap code : codeList)
					{
						if(regionCode.equals((String)code.get("code")) == true)
						{
							regionName = (String)code.get("codeNm");
						}
					}

	   				isProcess = true;
	   			}
	   			else
	   			{
	   				noRegionCount++;
	   				isProcess = false;
	   			}

	   			// 이미지 url 이 존재할 때 
				if(img_url != null && "".equals(img_url)== false)
				{
					imgCount++;
					
					// 지역코드가 있으면
					if(isProcess == true)
					{

						localImageFile			=	ftpUtil.ftpDownload(client, img_url, localSavePath, isDelete);
						
						if(localImageFile.length() > 0)
						{
							imgRegionCount++;
							
							atchFileId			=	idgenService.getNextStringId();
							fileVO				=	regionUtil.moveImgFile(atchFileId, "0", localImageFile, localSavePath);
							atchFileId			=	fileMngService.insertFileInf(fileVO);
							
							imageList.add(localImageFile);
						}
						else
						{
							imgZeroCount++;
							localImageFile.delete();
						}

					}
					else
					{
						imgNoRegionCount++;
						
						// 지역코드가 없으면 삭제
						if(isDelete == true)
						{
							ftpUtil.ftpDelete(client, img_url);
						}
					}
				}
				
				
				if(isProcess == true)
				{
					// VO 만들기
					regdate 		= 	xmlUtil.getValue(doc, "SendDate") + " " + xmlUtil.getValue(doc, "SendTime");
					regdate = regionUtil.getDateFormat(regdate, "yyyyMMdd hhmmss", "yyyy.MM.dd hh:mm:ss");
					
					title			=	xmlUtil.getValue(doc, "Title");
					content			=	xmlUtil.getValue(doc, "Body");
					writer			=	xmlUtil.getValue(doc, "Writer");
					article_id		=	xmlUtil.getValue(doc, "ContentID");

					MdmNewsVO vo = regionUtil.setYonhapVO(
							regionCode,
							regionName,
							title, 
							content,
							atchFileId,
							regdate,
							writer,
							article_id
					);

					resultList.add(vo);

				}

   			}

   			// 검색 제한
			if(_limit != 0 && downloadList.size() > _limit)
			{
				break;
			}
		}
		
		
		log.debug("############### Yonhap Process End ##################");
		log.debug("processCount ===> " + processCount);

		log.debug("allFileCount ===> " + allFileCount);

		log.debug("fileCount ===> " + fileCount);
		log.debug("zeroFileCount ===> " + zeroFileCount);

		log.debug("xmlCount ===> " + xmlCount);
		log.debug("zeroXmlCount ===> " + zeroXmlCount);
		log.debug("noXmlCount ===> " + noXmlCount);
		

		log.debug("regionCount ===> " + regionCount);
		log.debug("noRegionCount ===> " + noRegionCount);
    	
		log.debug("imgCount ===> " + imgCount);
		log.debug("imgRegionCount ===> " + imgRegionCount);
		log.debug("imgZeroCount ===> " + imgZeroCount);
		log.debug("imgNoRegionCount ===> " + imgNoRegionCount);
		
		log.debug("resultList.size() ===> " + resultList.size());
    	
		
		log.debug("#############################################################");
		
		return resultList;
    }
    
    private void shellCommand(String[] command) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        String line = null;
        while((line = br.readLine()) != null) {
        	log.debug(line);
	    }
	}
}
