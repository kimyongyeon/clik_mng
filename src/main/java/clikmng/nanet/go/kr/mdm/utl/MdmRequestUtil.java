package clikmng.nanet.go.kr.mdm.utl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class MdmRequestUtil {
	private HttpServletRequest req;
	private MultipartHttpServletRequest multi;

	public MdmRequestUtil(HttpServletRequest req) {
		this.req = req;
	}

	public String getRequest(String paramName) {
		String rtn = "";
		if( req.getParameter(paramName) != null ) {
			try {
				rtn = req.getParameter(paramName).trim();
				if( rtn == null ) {
					rtn = "";
				}
			}
			catch(Exception e) {
				rtn = "";
			}
		}
		return rtn;
	}

	public int getRequestInt(String paramName, int defaultVal) {
		int rtn = defaultVal;
		if( req.getParameter(paramName) != null ) {
			try {
				rtn = Integer.parseInt(req.getParameter(paramName).trim());
			}
			catch(Exception e) {
				rtn = defaultVal;
			}
		}
		return rtn;
	}
	
	public String getRequestStr(String paramName) {
		String rtn = "";
		if( req.getParameter(paramName) != null ) {
			try {
				rtn = req.getParameter(paramName).trim();
				if( rtn == null ) {
					rtn = "";
				}
			}
			catch(Exception e) {
				rtn = "";
			}
		}
		return rtn;
	}

	public String getRequest(String paramName, String defaultVal) {
		String rtn = defaultVal;
		if( req.getParameter(paramName) != null ) {
			try {
				rtn = req.getParameter(paramName).trim();
				if( rtn == null ) {
					rtn = defaultVal;
				}
			}
			catch(Exception e) {
				rtn = defaultVal;
			}
		}
		return rtn;
	}

	public String getMultipart(String paramName, String defaultVal) {
		String rtn = defaultVal;
		if( multi.getParameter(paramName) != null ) {
			try {
				rtn = multi.getParameter(paramName).trim();
				if( rtn == null ) {
					rtn = defaultVal;
				}
			}
			catch(Exception e) {
				rtn = defaultVal;
			}
		}
		return rtn;
	}

	public int getMultipartInt(String paramName, int defaultVal) {
		int rtn = defaultVal;
		if( multi.getParameter(paramName) != null ) {
			try {
				rtn = Integer.parseInt(multi.getParameter(paramName).trim());
			}
			catch(Exception e) {
				rtn = defaultVal;
			}
		}
		return rtn;
	}

}
