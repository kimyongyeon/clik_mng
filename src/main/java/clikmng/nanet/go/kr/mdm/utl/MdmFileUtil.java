package clikmng.nanet.go.kr.mdm.utl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class MdmFileUtil {

	public void getTextFileWrite(String f) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
	        bw.write("minutesVO.getContent()");
	        bw.flush();
	    }
	    catch (IOException e) {
	    }
		finally {
			try {
				if( bw != null ) { bw.close(); }
			}
			catch(Exception e) {}
		}
	}

	public String getMakeDir(String path) {
		String flg = "Y";
		try {
			File f = new File(path);
			if( f.exists() ) {
				throw new Exception("Directory already exist!");
			}
			if( !f.mkdir() ) {
				throw new Exception("Directory make failure!");
			}
		} 
		catch(Exception e) {
			flg = e.getMessage();
		}
		return flg;
	}
	
	public String getFileExists(String path) {
		String flg = "N";
		try {
			File f = new File(path);
			if( f.exists() ) {
				flg = "Y";
			}
		} 
		catch(Exception e) {
			flg = e.getMessage();
		}
		return flg;
	}

	public File getCreateFile(String path) {
		File f = new File(path);
		try {
			if( f.exists() ) {
				throw new Exception("File already exist!");
			}
			if( !f.createNewFile() ) {
				throw new Exception("File create failure!");
			}
		} 
		catch(Exception e) {
			System.out.println(e.toString());
			f = null;
		}
		return f;
	}
	
	public File getCreateFileByOverWrite(String path) {
		File f = new File(path);
		try {
			if( f.exists() ) {
				f.delete();
			}
			if( !f.createNewFile() ) {
				throw new Exception("File create failure!");
			}
		} 
		catch(Exception e) {
			f = null;
		}
		return f;
	}

	public String getFileCopy(String path1, String path2) {
		String flg = "Y";
    	int i = 0;
		FileInputStream fis =null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
    	try {
    		fis = new FileInputStream(path1);
    		fos = new FileOutputStream(path2);
    		bis = new BufferedInputStream(fis);
    		bos = new BufferedOutputStream(fos);
    		while( (i = bis.read()) != -1 ) {
    			bos.write(i);
    		}
    		bis.close();
    		bos.close();
    	} 
    	catch(Exception e) {
    		flg = "File copy failure!";
    	}
		finally {
			try {
				if (bis != null) { bis.close();	}
				if (bos != null) { bos.close();	}
				if (fis != null) { fis.close();	}
				if (fos != null) { fos.close();	}
			} 
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
   	
    	return flg;
    }
    
	public String getFileRenameTo(String path1, String path2) {
		String flg = "Y";
		try {
			File f1 = new File(path1);
    		File f2 = new File(path2);
    		
    		if( !f1.exists() ) {
				throw new Exception("File not found!");
    		}
    		if( !f1.renameTo(f2) ) {
				throw new Exception("File rename failure!");
    		}
    	} 
    	catch(Exception e) {
    		flg = e.getMessage();
    	}
    	return flg;
    }

	public String getFileRenameToByOverWrite(String path1, String path2) {
		String flg = "Y";
		try {
			File f1 = new File(path1);
    		File f2 = new File(path2);

    		if( f2.exists() ) {
				f2.delete();
    		}
    		if( !f1.renameTo(f2) ) {
				throw new Exception("File rename failure!");
    		}
    	} 
    	catch(Exception e) {
    		flg = e.getMessage();
    	}
    	return flg;
    }
	
	public String getFileDelete(String path) {
		String flg = "Y";
		try {
			File f = new File(path);
			if( !f.exists() ) {
				throw new Exception("File not found!");
			}
			if( !f.delete() ) {
				throw new Exception("File delete failure!");
			}
		}
		catch(Exception e) {
			flg = e.getMessage();
		}
		return flg;
	}
	
	public String getFileExt(String fileName) {
		String ext = "";
		if( fileName != null && !fileName.equals("") ) {
			int pos = 0;
			
			pos = fileName.lastIndexOf(".");
			if (pos != -1) {
				ext = fileName.substring(pos + 1).toLowerCase();
			}
		}
		return ext;
	}
	
	public String getExt(String ext) {
		Vector<String> vec = new Vector<String>();
		vec.add("xls");
		vec.add("xlsx");
		vec.add("ppt");
		vec.add("pptx");
		vec.add("pdf");
		vec.add("hwp");
		vec.add("hwt");
		vec.add("docx");
		vec.add("doc");
		
		if( vec.contains(ext) ) {
			return ext;
		}
		else {
			return "txt";
		}
	}

}
