package clikmng.nanet.go.kr.mdm.utl;

import java.io.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class MdmFtp_20150210 {
	static String server   = "10.201.27.149";
	static int    port     = 21;
	static String id       = "clik";
	static String password = "qhdks00";
	
	FTPClient ftpClient;
	
	final static Logger logger = Logger.getLogger(MdmFtp_20150210.class);
			
	public int ftp(String source, String target) {
		int flg = 0;
	    ftpClient = new FTPClient();
		connect();
		login(id, password);
		
		flg = get(source, target);

		logout();
		disconnect();
		
		return flg;
	}
	
	// 파일을 전송 받는다
	public int get(String source, String target) {
		int flg = 0;
		OutputStream output = null;
		
		try {
			File local = new File(target); // 로컬 위치
			output = new FileOutputStream(local);
			if( ftpClient.retrieveFile(source, output) ) {
				return flg = 1;
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			if( output != null ) {
				try{ output.close(); } catch(Exception e){};	
			}
		}
		return flg;
	}
	
	// 서버 디렉토리 이동
	public void cd(String path) {
		try {
			ftpClient.changeWorkingDirectory(path);
	        //System.out.println(path + " : Current directory is " + ftpClient.printWorkingDirectory());
		}
	    catch (IOException ioe) {
	    	ioe.printStackTrace();
	        //System.out.println(path + "로 이동 실패");
	    }
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
				//System.err.println("서버로부터 연결을 거부당했습니다");
				//System.exit(1);
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
			//System.err.println("서버에 연결할 수 없습니다");
			//System.exit(1);
		}
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
