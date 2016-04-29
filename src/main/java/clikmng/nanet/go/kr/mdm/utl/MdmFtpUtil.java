package clikmng.nanet.go.kr.mdm.utl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

import clikmng.nanet.go.kr.cmm.EgovWebUtil;

public class MdmFtpUtil 
{

	public MdmFtpUtil()
	{

	}
	
	
	// FTP 연결
	public FTPClient getClient(String ip, int port, String username, String password) throws Exception
	{

//		System.out.println("####################################################");
//		System.out.println("MdmFtpUtil > getClient");
//		System.out.println("####################################################");
		
		FTPClient 				client 				= new FTPClient();

		try
		{
			client.connect(ip, port);

			if(FTPReply.isPositiveCompletion(client.getReplyCode()) == false)
			{
				client.disconnect();
			}
			else
			{
				if(client.login(username, password) == true)
				{

					//client.setControlEncoding("UTF-8");
					client.setFileType(FTP.BINARY_FILE_TYPE);

					//client.enterLocalPassiveMode();
					//client.setAutodetectUTF8(true);

				}
			}

		}
		catch(Exception e)
		{
			client.logout();
			client.disconnect();

			e.printStackTrace();

		}

		return client;
	}
	
	public void close(FTPClient client) throws Exception
	{
		
//		System.out.println("####################################################");
//		System.out.println("MdmFtpUtil > close");
//		System.out.println("####################################################");
		
		try
		{
			client.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// FTP 상 작업 디렉토리 변경
	public boolean changeWorkDirectory(FTPClient client, String path) throws Exception
	{

//		System.out.println("####################################################");
//		System.out.println("MdmFtpUtil > changeWorkDirectory");
//		System.out.println("####################################################");

		boolean isResult = true;

		try
		{
			client.changeWorkingDirectory(path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return isResult;

	}

	// FTP 작업 디렉토리 파일 리스트 조회
	public ArrayList<FTPFile> ftpFileList(FTPClient client) throws Exception
	{
		
//		System.out.println("####################################################");
//		System.out.println("MdmFtpUtil > searchFtpFiles");
//		System.out.println("####################################################");
		
		ArrayList<FTPFile> ftpFiles = new ArrayList();

		for(FTPFile file : client.listFiles())
		{
			ftpFiles.add(file);
		}

		return ftpFiles;
	}

	public File ftpDownload(FTPClient client, String ftpFileName, String localSavePath, boolean isDelete) throws Exception
	{
		
//		System.out.println("####################################################");
//		System.out.println("MdmFtpUtil > download > ");
//		System.out.println("ftpFileName ===> " + ftpFileName);
//		System.out.println("localSavePath ===> " + localSavePath);

		
		File 					localFile 			= 	null;
		FileOutputStream 		fos 				= 	null;
		OutputStream			os					= 	null;
		String					ftpFilePath			=	null;
		boolean 				isDownloadFile		=	false;

		
		
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
			ftpFilePath			=	ftpFileName;
			


			localFile 			=	new File(localSavePath + File.separator + ftpFileName);
			
//			System.out.println("localFile ===> " + localFile);

			fos					= 	new FileOutputStream(localFile, false);
			isDownloadFile		= 	client.retrieveFile(ftpFileName, fos);

//			System.out.println("isDownloadFile ===> " + isDownloadFile);
//			System.out.println("localFile.length() ===> " + localFile.length());
			
			if(isDownloadFile && localFile.length() > 0)
			{
				// 파일 삭제
				if(isDelete == true)
				{
					client.deleteFile(ftpFilePath);
				}
			}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			fos.close();
		}
//		System.out.println("####################################################");
		return localFile;
	}

	public boolean ftpDelete(FTPClient client, String ftpFileName) throws Exception
	{
		
		
		String					ftpFilePath			=	null;
		boolean 				isDeleteFile		=	false;

		
		
		
		try
		{
			ftpFilePath			=	ftpFileName;
			isDeleteFile		= 	client.deleteFile(ftpFileName);
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		return isDeleteFile;
	}
}
