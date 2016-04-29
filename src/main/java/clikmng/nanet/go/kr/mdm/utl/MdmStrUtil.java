package clikmng.nanet.go.kr.mdm.utl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyApndxVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMintsVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtrVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblySpkngVO;

public class MdmStrUtil {
	public String getDecimalFormat(String format, int num) throws Exception {
		try {
			DecimalFormat df = new DecimalFormat(format); // format = "###,###.###"
			return df.format(num);
		}
		catch(Exception e) {
			return "";
		}
	}
	
	public int getIntegerParseInt(String paramName, int defaultVal) throws Exception {
		int rtn = defaultVal;
		if( !paramName.equals("") ) {
			try {
				rtn = Integer.parseInt(paramName);
			}
			catch(Exception e) {
				rtn = defaultVal;
			}
		}
		return rtn;
	}
	
	public String getIntegerToString(int val) throws Exception {
		String rtn = "";
		try {
			rtn = Integer.toString(val);
		}
		catch(Exception e) {
			rtn = "";
		}
		return rtn;
	}

	public String getNull(String str) throws Exception {
		if( str == null || str.equals("null") ) {
			str = "";
		}
	    return str;
	}
	
	public String getRemoveSpace(String str) throws Exception {
		return str.replace(" ", "");
	}
	
	public String getRemoveWhiteSpace(String str) throws Exception {
		return str.replaceAll("\\s", "");
	}

	public String getRemoveStr(String reg, String str) throws Exception {
		if( str == null || str.equals("") ) {
			return "";
		}
		Pattern pattern = Pattern.compile(reg);
		Matcher m = pattern.matcher(str);

		if( m.find() ) {
			str = str.replace(m.group(), "");
		}
		return str.trim();
	}
	
	public String getReplaceStr(String reg1, String reg2, String str) throws Exception {
		Pattern pattern = Pattern.compile(reg1);
		Matcher matcher = pattern.matcher(str);

		if( matcher.find() ) {
			str = str.replace(matcher.group(), reg2);
		}
		return str;
	}
	
	public String getStrCut(String str, int num) throws Exception {
		if( str == null || str.equals("") ) {
			return "";
		}
		else if(str.length() < num ) {
			return str;
		}
		else {
			return str.substring(0, num);
		}
	}

	public String getStrCutWithEllipsis(String str, int num) throws Exception {
		if( str == null || str.equals("") ) {
			return "";
		}
		else if(str.length() < num ) {
			return str;
		}
		else {
			return str.substring(0, num)+"ㆍㆍㆍ";
		}
	}
	
	public String getStringFormat8(String delimiter, String str) throws Exception {
		try {
			if( str.length() >= 8 ) {
				str = str.substring(0,4)+delimiter+str.substring(4,6)+delimiter+str.substring(6,8);
			}
		}
		catch(Exception e) {
		}
		return str;
	}

	public String getStringFormat8(String delimiter1, String delimiter2, String str1, String str2) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);
		try {
			if( str1.length() == 8 && str2.length() == 8 ) {
				sb.append(str1.substring(0,4)).append(delimiter1).append(str1.substring(4,6)).append(delimiter1).append(str1.substring(6,8)).append(delimiter1.trim());
				sb.append(delimiter2);
				sb.append(str2.substring(0,4)).append(delimiter1).append(str2.substring(4,6)).append(delimiter1).append(str2.substring(6,8)).append(delimiter1.trim());
			}
		}
		catch(Exception e) {
			sb.setLength(0);
		}
		return sb.toString();
	}
	
	public int getStringToInteger(String paramName, int defaultVal) throws Exception {
		int rtn = defaultVal;
		if( !paramName.equals("") && paramName != null ) {
			try {
				rtn = Integer.parseInt(paramName);
			}
			catch(Exception e) {
				rtn = defaultVal;
			}
		}
		return rtn;
	}

	public String getSpecialChar(String str) throws Exception {
		if( str.equals("") ) {
			return str;
		}
		str = str.replace("\'",  "\\'");
		str = str.replace("&",  "\\&");
		return str;
	}
	
	public String getSpecialCharRemove(String str) throws Exception {
		if( str.equals("") ) {
			return str;
		}
		str = str.replace("\'",  "");
		str = str.replace("&",  "");
		str = str.replace("$",  "");
		return str;
	}

	public String getSlashRemove(String str) throws Exception {
		if( str.equals("") ) {
			return str;
		}
		str = str.replace("\\",  "");
		return str;
	}

	public String getEuckrToISO8859(String str) throws Exception {
		if( str == null || str.equals("") ) {
			str = "";
		}
		try {
			str = new String(str.getBytes("euc-kr"), "8859_1");
		}
		catch(Exception e) { }
		return str;
	}

	public String getEuckrToUtf8(String str) throws Exception {
		if( str == null || str.equals("") ) {
			str = "";
		}
		try {
			str = new String(str.getBytes("euc-kr"), "utf-8");
		}
		catch(Exception e) { }
		return str;
	}

	public String getISO8859ToEuckr(String str) throws Exception {
		if( str == null || str.equals("") ) {
			str = "";
		}
		try {
			str = new String(str.getBytes("8859_1"), "euc-kr");
		}
		catch(Exception e) { }
		return str;
	}
	
	public String getISO8859ToUtf8(String str) throws Exception {
		if( str == null || str.equals("") ) {
			str = "";
		}
		try {
			str = new String(str.getBytes("8859_1"), "utf-8");
		}
		catch(Exception e) { }
		return str;
	}

	public String getUtf8ToISO8859(String str) throws Exception {
		if( str == null || str.equals("") ) {
			str = "";
		}
		try {
			str = new String(str.getBytes("utf-8"), "8859_1");
		}
		catch(Exception e) { }
		return str;
	}

	public boolean getFirstCharIsNumber(String str) throws Exception {
		boolean flg = false;
		if( str == null || str.equals("") ) {
			flg = false;
		}
		str = str.trim();
		Pattern p = Pattern.compile("^\\d+");
		Matcher m = p.matcher(str);
		if( m.find() ) {
			flg = true;
		}
		return flg;
	}
	
	public String getXMLMinutes(MdmTnsrAsmblyMintsVO vo, List<MdmTnsrAsmblySpkngVO> spkngList, List<MdmTnsrAsmblyMtrVO> mtrList,List<MdmTnsrAsmblyApndxVO> apndxList) {
		int i = 0;
		MdmTnsrAsmblyMtrVO mtrVO = null;
		MdmTnsrAsmblySpkngVO spkngVO = null;
		MdmTnsrAsmblyApndxVO apndxVO = null;

		StringBuffer sb = new StringBuffer();
		
		sb.setLength(0);
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<MTV>");
		sb.append("<ViewCouncilCode>").append(vo.getRASMBLY_ID()).append("</ViewCouncilCode>");
		sb.append("<ViewDaesu>").append(vo.getRASMBLY_NUMPR()).append("</ViewDaesu>");
		sb.append("<ViewTh>").append(vo.getRASMBLY_SESN()).append("</ViewTh>");
		sb.append("<ViewCha>").append(vo.getMINTS_ODR()).append("</ViewCha>");
		sb.append("<ViewCmt>").append(vo.getMTGNM_ID()).append("</ViewCmt>");
		sb.append("<Viewtitle>").append(vo.getRASMBLY_SESN()).append(vo.getMTGNM_ID()).append(vo.getODR_NM()).append("</Viewtitle>");
		sb.append("<Bill>");
		
		for(i = 0; i < mtrList.size(); i++) {
			mtrVO = new MdmTnsrAsmblyMtrVO();
			mtrVO = mtrList.get(i);
			sb.append("<No>").append(i).append("</No>");
			sb.append("<Idx>").append(i).append("</Idx>");
			sb.append("<Title><![CDATA[ ").append(mtrVO.getMTR_SJ()).append(" ]]></Title>");
		}

		sb.append("</Bill>");
		sb.append("<Appendix>");
		
		for(i = 0; i < apndxList.size(); i++) {
			apndxVO = new MdmTnsrAsmblyApndxVO();
			apndxVO = apndxList.get(i);
			sb.append("<No>").append(i).append("</No>");
			sb.append("<Idx>").append(i).append("</Idx>");
			sb.append("<Title><![CDATA[ ").append(apndxVO.getAPNDX_FILE_NM()).append(" ]]></Title>");
			sb.append("<Location><![CDATA[ <a href='#' onclick=appDownload('')>").append(apndxVO.getAPNDX_ID()).append("</a> ]]></Location>");
		}
		
		sb.append("</Appendix>");
		sb.append("<Speaker>");
		
		for(i = 0; i < spkngList.size(); i++) {
			spkngVO = new MdmTnsrAsmblySpkngVO();
			spkngVO = spkngList.get(i);
			sb.append("<Code>").append(i).append("</Code>");
			sb.append("<Name><![CDATA[ ").append(spkngVO.getSPKR_PSITN_NM()).append(" ]]></Name>");
			sb.append("<Photo>").append(i).append("</Photo>");
		}
		
		sb.append("</Speaker>");
		sb.append("<ViewText>");
		sb.append("<![CDATA[");
		sb.append(vo.getMINTS_HTML());
		sb.append("]]>");
		sb.append("</ViewText>");
		sb.append("</MTV>");
		
		return sb.toString();
	}

}