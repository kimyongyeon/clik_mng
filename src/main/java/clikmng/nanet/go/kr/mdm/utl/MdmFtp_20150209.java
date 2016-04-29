package clikmng.nanet.go.kr.mdm.utl;

import java.io.*;

import javax.servlet.ServletContext;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class MdmFtp_20150209 {
	static String server   = "10.201.27.151";
	static int    port     = 21;
	static String id       = "clik";
	static String password = "qhdks00";
	
	FTPClient ftpClient;
	
	final static Logger logger = Logger.getLogger(MdmFtp_20150209.class);
			
	public MdmFtp_20150209(String server, int port, String id, String password) {
		this.server = server;
	    this.port   = port;
	    this.ftpClient = new FTPClient();
	}
	
	public static void main(String args[]) {
		MdmFtp_20150209 ftp = new MdmFtp_20150209(server, port, id, password);
		ftp.connect();
		ftp.login(ftp.id, ftp.password);
		
		// 로그파일이 있는 디렉토리로 이동한다
		//ftp.cd("/home/ggckms/wwwroot/old/Conference/FileText");
		ftp.cd("/clikapi-file/clik001/2015/055001/20150129/RES403");
		FTPFile[] files = ftp.list();
		for(int i = 0; i < files.length ; i++) {
			String fileName = files[i].getName();
			
			// 파일 이름에서 확장자만 추출
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
			long size = files[i].getSize();
			// 파일 사이즈가 0보다 크고 로그 파일만 가져온다
			if( (size > 0) && (extension.equalsIgnoreCase("pdf")) ) {
				File file = ftp.get(fileName, fileName);
			}
		}
		
		//ftp.put("String source", "String target");

		ftp.logout();
		ftp.disconnect();
		System.exit(1);
	}
	
	// 계정과 패스워드로 로그인
	public boolean login(String user, String password) {
		try {
			this.connect();
			return ftpClient.login(user, password);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	}
	
	// 서버로부터 로그아웃
	private boolean logout() {
		try {
			return ftpClient.logout();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	}
	
	// 서버로 연결
	public void connect() {
		try {
			// 한글파일명 때문에 디폴트 인코딩을 euc-kr로 합니다
			ftpClient.setControlEncoding("utf-8");
			ftpClient.connect(server, port);
			int reply;
			
			// 연결 시도후, 성공했는지 응답 코드 확인
			reply = ftpClient.getReplyCode();
			if( !FTPReply.isPositiveCompletion(reply) ) {
				ftpClient.disconnect();
				System.err.println("서버로부터 연결을 거부당했습니다");
				System.exit(1);
			}
		}
		catch(IOException ioe) {
			if( ftpClient.isConnected() ) {
				try {
					ftpClient.disconnect();
				}
				catch(IOException f) {
					//
				}
			}
			System.err.println("서버에 연결할 수 없습니다");
			System.exit(1);
		}
	}
	
	// FTP의 ls 명령, 모든 파일 리스트를 가져온다
	public FTPFile[] list() {
		FTPFile[] files = null;
		
		try {
			files = this.ftpClient.listFiles();
			return files;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	// 파일을 전송 받는다
	public File get(String source, String target) {
		OutputStream output = null;
		
		try {
			File local = new File("D:/works/temp/" + source); // 로컬 위치
			output = new FileOutputStream(local);
		}
		catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		
		File file = new File(source);
		
		try {
			if( ftpClient.retrieveFile("/clikapi-file/clik001/2015/055001/20150129/RES403/"+source, output) ) {
				return file;
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
				
		return null;
	}
	
	// 서버 디렉토리 이동
	public void cd(String path) {
		try {
			ftpClient.changeWorkingDirectory(path);
	        System.out.println(path + " : Current directory is " + ftpClient.printWorkingDirectory());
		}
	    catch (IOException ioe) {
	    	ioe.printStackTrace();
	        System.out.println(path + "로 이동 실패");
	    }
	}

	// 서버로부터 연결을 닫는다
	private void disconnect() {
		try {
			ftpClient.disconnect();
	    }
	    catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	    
}
