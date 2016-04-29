/**
 * @Class Name  : CommonStringUtil.java
 * @Description : 문자열 데이터 처리 관련 유틸리티
 * @Modification Information
 * 
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.01.13     박정규          최초 생성
 *   2009.02.13     이삼섭          내용 추가
 *
 * @author 공통 서비스 개발팀 박정규
 * @since 2009. 01. 13
 * @version 1.0
 * @see 
 * 
 */

package clikmng.nanet.go.kr.cmm;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CommonStringUtil {
    /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";
    
    /**
     * <p>Padding을 할 수 있는 최대 수치</p>
     */
    // private static final int PAD_LIMIT = 8192;
    
    /**
     * <p>An array of <code>String</code>s used for padding.</p>
     * <p>Used for efficient space padding. The length of each String expands as needed.</p>
     */
    /*
	private static final String[] PADDING = new String[Character.MAX_VALUE];

	static {
		// space padding is most common, start with 64 chars
		PADDING[32] = "                                                                ";
	}	
     */	
    

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.(바이트 단위)
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
	
    public static String byteString(String source, String output, int slength) {
        
        String returnVal;
		try{
			returnVal= new String(source.getBytes(),0, slength);
		  if(returnVal.length()==0 ){
			  returnVal= new String(source.getBytes(),0, slength+1);
		  }
		  returnVal += output;    
		}catch(IndexOutOfBoundsException e){
			returnVal = source;              
		}
		return returnVal;
    }
	
	
	
    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else
                returnVal = source;
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }    
    
    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     * 
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     * 
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우 
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    
    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * 비었거나 null이면 false를 반환
     * </p>
     * 
     * <pre>
     *  StringUtil.isEmpty(null)      = false
     *  StringUtil.isEmpty("")        = false
     *  StringUtil.isEmpty(" ")       = true
     *  StringUtil.isEmpty("bob")     = true
     *  StringUtil.isEmpty("  bob  ") = true
     * </pre>
     * 
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우 
     */
    public static boolean isEmptyNo(String str) {
        return !(str == null || str.length() == 0);
    }

    
    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }
    
    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }
    
    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }
        
    
    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;
        
        if(srcStr!=null){
	        while (srcStr.indexOf(subject) >= 0) {
	            preStr = srcStr.substring(0, srcStr.indexOf(subject));
	            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
	            srcStr = nextStr;
	            rtnStr.append(preStr).append(object);
	        }
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본배열의 문자열이 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열을 배열로 넘김
     * @param object 변환할 문자열을 배열로 넘김
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
	 public static String replaceArray (String source, String[] subject, String[] object){
		  String str = source;
		  for(int i=0;i<subject.length;i++){
			  str=replace(str, subject[i], object[i]);
		  }
		  return str;
		 }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     * 
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;
        
        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }
        
        return srcStr;
    }  
    
    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }    
    
    
    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
     * </p>
     * 
     * <pre>
     * StringUtil.decode(null, null, "foo", "bar")= "foo"
     * StringUtil.decode("", null, "foo", "bar") = "bar"
     * StringUtil.decode(null, "", "foo", "bar") = "bar"
     * StringUtil.decode("하이", "하이", null, "bar") = null
     * StringUtil.decode("하이", "하이  ", "foo", null) = null
     * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     * 
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        if (sourceStr == null && compareStr == null) {
            return returnStr;
        }
        
        if (sourceStr == null && compareStr != null) {
            return defaultStr;
        }

        if (sourceStr.trim().equals(compareStr)) {
            return returnStr;
        }

        return defaultStr;
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
     * </p>
     * 
     * <pre>
     * StringUtil.decode(null, null, "foo") = "foo"
     * StringUtil.decode("", null, "foo") = ""
     * StringUtil.decode(null, "", "foo") = null
     * StringUtil.decode("하이", "하이", "foo") = "foo"
     * StringUtil.decode("하이", "하이 ", "foo") = "하이"
     * StringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     * 
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }    
    
    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";
        
        if (object != null) {
            string = object.toString().trim();
        }
        
        return string;
    }
    
    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(Object src) {
	//if (src != null && src.getClass().getName().equals("java.math.BigDecimal")) {
	if (src != null && src instanceof java.math.BigDecimal) {
	    return ((BigDecimal)src).toString();
	}

	if (src == null || src.equals("null")) {
	    return "";
	} else {
	    return ((String)src).trim();
	}
    }
    
    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(String src) {

	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
	    return "";
	} else {
	    return src.trim();
	}
    }	
	
    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(Object src) {

	if (src == null || src.equals("null")) {
	    return 0;
	} else {
	    return Integer.parseInt(((String)src).trim());
	}
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(String src) {

	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
	    return 0;
	} else {
	    return Integer.parseInt(src.trim());
	}
    }
	
    
    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvertHashMap(Object src) {

	if (src == null || src.equals("null")) {
	    return 0;
	} else {
	    return Integer.parseInt(src.toString());
	}
    }
    
    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된 
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        
        return new String(chs, 0, count);
    }
    	
    /**
     * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
     * 
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
    public static String checkHtmlView(String strString) {
	String strNew = "";

	try {
	    StringBuffer strTxt = new StringBuffer("");

	    char chrBuff;
	    int len = strString.length();

	    for (int i = 0; i < len; i++) {
		chrBuff = (char)strString.charAt(i);

		switch (chrBuff) {
		case '<':
		    strTxt.append("&lt;");
		    break;
		case '>':
		    strTxt.append("&gt;");
		    break;
		case '"':
		    strTxt.append("&quot;");
		    break;
		case 10:
		    strTxt.append("<br>");
		    break;
		case ' ':
		    strTxt.append("&nbsp;");
		    break;
		//case '&' :
		    //strTxt.append("&amp;");
		    //break;
		default:
		    strTxt.append(chrBuff);
		}
	    }

	    strNew = strTxt.toString();

	} catch (Exception ex) {
	    return null;
	}

	return strNew;
    }
	
    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        
        return returnVal;
    }
    
    /**
     * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        
        return str.toLowerCase();
    }
    
    /**
     * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        
        return str.toUpperCase();
    }
    
    
    /**
     * <p>소문자와 숫자만 가능하도록 한다.</p>
     *
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자나 숫자의 조합인지 여부를  리턴
     */
    public static boolean lowerNumCheck(String str) {
    	
    	if (str == null) {
            return false;
        }
    	 
    	String regex1 = "[a-z0-9]*";

    	return str.matches(regex1);
    }
    
    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }
        
        return str.substring(start);
    }


    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }
        
        return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     * 
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
	if (isEmpty(str)) {
	    return str;
	}

	String srcStr = str;
	srcStr = stripStart(srcStr, stripChars);
	
	return stripEnd(srcStr, stripChars);
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }
        
        return returnVal;
    }

    /**
     * 문자열 A에서 Z사이의 랜덤 문자열을 구하는 기능을 제공 시작문자열과 종료문자열 사이의 랜덤 문자열을 구하는 기능
     * 
     * @param startChr
     *            - 첫 문자
     * @param endChr
     *            - 마지막문자
     * @return 랜덤문자
     * @exception MyException
     * @see
     */
    public static String getRandomStr(char startChr, char endChr) {

	int randomInt;
	String randomStr = null;

	// 시작문자 및 종료문자를 아스키숫자로 변환한다.
	int startInt = Integer.valueOf(startChr);
	int endInt = Integer.valueOf(endChr);

	// 시작문자열이 종료문자열보가 클경우
	if (startInt > endInt) {
	    throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
	}

	try {
	    // 랜덤 객체 생성
	    SecureRandom rnd = new SecureRandom();

	    do {
		// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
		randomInt = rnd.nextInt(endInt + 1);
	    } while (randomInt < startInt); // 입력받은 문자 'A'(65)보다 작으면 다시 랜덤 숫자 발생.

	    // 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
	    randomStr = (char)randomInt + "";
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// 랜덤문자열를 리턴
	return randomStr;
    }

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     * 
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

	String rtnStr = null;

	if (srcString == null)
	    return null;

	try {
	    rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
	} catch (UnsupportedEncodingException e) {
	    rtnStr = null;
	}

	return rtnStr;
    }
    
    
    /**
     * String temp = new String(문자열.getBytes("KSC5601"),"8859_1");
     * EUC-KR
     * 
     * @param srcString
     *            - 문자열
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getConvert8859(String srcString)
	{
		if (srcString == null) {
			return "";
		}

		try {
			return new String(srcString.getBytes("KSC5601"),"8859_1");
		}
		catch (Exception e) {
			return "";
		}
	}

    /**
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601");
     * EUC-KR
     * 
     * @param srcString
     *            - 문자열
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
	public static String getConvertUTF8(String srcString)
	{
		if (srcString == null) {
			return "";
		}

		try {
			return new String(srcString.getBytes("8859_1"),"KSC5601");
		}
		catch (Exception e)
		{
			return "";
		}
	}


/**
     * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
     * @param 	srcString 		- '<' 
     * @return 	변환문자열('<' -> "&lt"
     * @exception MyException 
     * @see  
     */
    public static String getSpclStrCnvr(String srcString) {

	String rtnStr = null;

	try {
	    StringBuffer strTxt = new StringBuffer("");

	    char chrBuff;
	    int len = srcString.length();

	    for (int i = 0; i < len; i++) {
		chrBuff = (char)srcString.charAt(i);

		switch (chrBuff) {
		case '<':
		    strTxt.append("&lt;");
		    break;
		case '>':
		    strTxt.append("&gt;");
		    break;
		default:
		    strTxt.append(chrBuff);
		}
	    }

	    rtnStr = strTxt.toString();

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     * 
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {

	String rtnStr = null;

	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
	String pattern = "yyyyMMddhhmmssSSS";

	try {
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return rtnStr;
    }
    
    /**
     * html의 특수문자("")를 표현하기 위해
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */    
    public static String getHtmlStrCnvrQuot(String srcString) {
    	
    	String tmpString = srcString;

		try 
		{
			tmpString = tmpString.replaceAll("\"","&quot;");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return  tmpString;
	
	}    
    
    /**
     * html의 특수문자를 표현하기 위해
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */    
    public static String getHtmlStrCnvr(String srcString) {
    	
    	String tmpString = srcString;

		try 
		{
			tmpString = tmpString.replaceAll("&lt;", "<");
			tmpString = tmpString.replaceAll("&gt;", ">");
			tmpString = tmpString.replaceAll("&amp;", "&");
			tmpString = tmpString.replaceAll("&nbsp;", " ");
			tmpString = tmpString.replaceAll("&apos;", "\'");
			tmpString = tmpString.replaceAll("&quot;", "\"");
			
			
			//20150528 추가
			tmpString = tmpString.replaceAll("&middot;", "·");
			tmpString = tmpString.replaceAll("&#34;", "\"");
			tmpString = tmpString.replaceAll("&#39;", "'");
			tmpString = tmpString.replaceAll("&#35;", "#");
			tmpString = tmpString.replaceAll("&#37;", "%");
			tmpString = tmpString.replaceAll("&#92;", "\\");
			tmpString = tmpString.replaceAll("&#40;", "(");
			tmpString = tmpString.replaceAll("&#41;", ")");
			tmpString = tmpString.replaceAll("&#43;", "+");
			tmpString = tmpString.replaceAll("&#46;", ".");
			tmpString = tmpString.replaceAll("&#47;", "/");
			tmpString = tmpString.replaceAll("&#63;", "?");
			tmpString = tmpString.replaceAll("&#124;", "|");
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return  tmpString;
	
	}    
    
    
    public static String specialTrim(String str) {
    	
        StringBuffer    sb = new StringBuffer();

        for(int ii = 0; ii < str.length(); ii++) {
                if(str.charAt(ii) <  ' ') { continue; }
                if(' ' < str.charAt(ii) && str.charAt(ii) < '0') { continue; }
                if('9' < str.charAt(ii) && str.charAt(ii) < 'A') { continue; }
                if('Z' < str.charAt(ii) && str.charAt(ii) < 'a') { continue; }
                if('z' < str.charAt(ii) && str.charAt(ii) < '~') { continue; }
                if(str.charAt(ii)=='\n' && str.charAt(ii)=='\r' && str.charAt(ii)=='\t') { continue; }
                sb.append(str.charAt(ii));
        }
        return (String)sb.toString();
    }

    /**
     *  파라미터 값 체크 , injection 취약점 예방
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getPrmStrCnvr(String srcString) {    
        if (srcString == null){
          return "";
        }

        try 
		{
        	srcString=CommonStringUtil.replace(srcString,"'","");
        	srcString=CommonStringUtil.replace(srcString,"`","");
	        srcString=CommonStringUtil.replace(srcString,"\"","");
	        srcString=CommonStringUtil.replace(srcString,"%","");
	        srcString=CommonStringUtil.replace(srcString,"<","");
	        srcString=CommonStringUtil.replace(srcString,">","");
	        srcString=CommonStringUtil.replace(srcString,"(","");
	        srcString=CommonStringUtil.replace(srcString,")","");
	        srcString=CommonStringUtil.replace(srcString,"#","");
	        srcString=CommonStringUtil.replace(srcString,"&","");
	        srcString=CommonStringUtil.replace(srcString,";","");
	        srcString=CommonStringUtil.replace(srcString,"\\'", "''");
	        srcString=CommonStringUtil.replace(srcString,"\t'", "' '");
	        srcString=CommonStringUtil.replace(srcString," ", "");
		}
        catch (Exception ex)
		{
			ex.printStackTrace();
		}

        return srcString;
    }
    
    public static String getPrmStrCnvr2(String srcString) {    
        if (srcString == null){
          return "";
        }

        try 
		{
	        srcString=CommonStringUtil.replace(srcString,"/","");
	        srcString=CommonStringUtil.replace(srcString,"\\","");
	        srcString=CommonStringUtil.replace(srcString,":","");
	        srcString=CommonStringUtil.replace(srcString,"*","");
	        srcString=CommonStringUtil.replace(srcString,"<","");
	        srcString=CommonStringUtil.replace(srcString,">","");
	        srcString=CommonStringUtil.replace(srcString,"?","");
	        //2012-11-09 송진섭 웹취약성 점검으로 추가
	        srcString=CommonStringUtil.replace(srcString,"|","");
	        srcString=CommonStringUtil.replace(srcString,"&","");
	        srcString=CommonStringUtil.replace(srcString,"%","");
	        srcString=CommonStringUtil.replace(srcString,"@","");
	        srcString=CommonStringUtil.replace(srcString,"'","");
		}
        catch (Exception ex)
		{
			ex.printStackTrace();
		}

        return srcString;
    }
    
    /**
     *  파라미터 값 체크 , injection 취약점 예방
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getPrmStrCnvr3(String srcString) {    
        if (srcString == null){
          return "";
        }

        try 
		{
        	srcString=CommonStringUtil.replace(srcString,"'","");
        	srcString=CommonStringUtil.replace(srcString,"`","");
	        srcString=CommonStringUtil.replace(srcString,"\"","");
	        srcString=CommonStringUtil.replace(srcString,"%","");
	        srcString=CommonStringUtil.replace(srcString,"<","");
	        srcString=CommonStringUtil.replace(srcString,">","");
	        srcString=CommonStringUtil.replace(srcString,"(","");
	        srcString=CommonStringUtil.replace(srcString,")","");
	        srcString=CommonStringUtil.replace(srcString,"#","");
	        srcString=CommonStringUtil.replace(srcString,"&","");
	        srcString=CommonStringUtil.replace(srcString,";","");
	        srcString=CommonStringUtil.replace(srcString,"\\'", "''");
	        srcString=CommonStringUtil.replace(srcString,"\t'", "' '");
		}
        catch (Exception ex)
		{
			ex.printStackTrace();
		}

        return srcString;
    }
    
    /**
     * 마지막 스트링 제거
     * 
     *  @see
     */
    public static String cvtEndString(String str){
        
        int flag = 1; 
            
        
        if(str.substring(str.length()- flag).equals("&")) {
            str = str.substring(0, str.length()- flag); 
        }
        if(str.substring(str.length()- flag).equals(":")) {
            str = str.substring(0, str.length()- flag); 
        }
        if(str.substring(str.length()- flag).equals(";")) {
            str = str.substring(0, str.length()- flag); 
        } 
        if(str.substring(str.length()- flag).equals("/")) {
            str = str.substring(0, str.length()- flag); 
        }
        if(str.substring(str.length()- flag).equals(",")) {
            str = str.substring(0, str.length()- flag); 
        }
        if(str.substring(str.length()- flag).equals(".")) {
            str = str.substring(0, str.length()- flag); 
        }
        if(str.length() > 1 && str.substring(str.length()- 2).equals("--")) {
            str = str.substring(0, str.length()- 2); 
        }
        
        str = str.replace(" : ", " ");
        
        return str;
        
    }
    
    /**
     *  검색어 파라미터 값 체크 , 인코딩으로 인해 %는 넘어 올수 있으므로 %만 제외
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */     
    public static String getSearchStrCnvr(String srcString) {    
        if (srcString == null || srcString==""){
            return null;
          }
          
        try 
  		{
        	srcString=CommonStringUtil.replace(srcString,"'","");
  	        srcString=CommonStringUtil.replace(srcString,"\"","");
  	        srcString=CommonStringUtil.replace(srcString,"<","");
  	        srcString=CommonStringUtil.replace(srcString,">","");
  	        srcString=CommonStringUtil.replace(srcString,"(","");
  	        srcString=CommonStringUtil.replace(srcString,")","");
  	        srcString=CommonStringUtil.replace(srcString,"#","");
  	        srcString=CommonStringUtil.replace(srcString,"&","");
  		}
        catch (Exception ex)
  		{
  			ex.printStackTrace();
  		}

        return srcString;
      }
    
    /**
     *  검색어 파라미터 값 체크 , 인코딩으로 인해 %는 넘어 올수 있으므로 %만 제외
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */     
    public static String getSearchStrCnvr2(String srcString) {    
        if (srcString == null || srcString==""){
            return null;
          }
          
        try 
  		{
        	srcString=CommonStringUtil.replace(srcString,"'","");
            srcString=CommonStringUtil.replace(srcString,"\"","");  
            srcString=CommonStringUtil.replace(srcString,"?","");
  		}
        catch (Exception ex)
  		{
  			ex.printStackTrace();
  		}

        return srcString;
      }
    
    
    /**
     *  글 내용 Check 태그 제거 및 특정어 변환 textarea받는 곳에서 사용
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getContentsStrCnvr(String srcString) {  // 
        if (srcString == null){
          return null;
        }
        
       try 
  		{
        	srcString=srcString.toUpperCase();
  	        srcString=CommonStringUtil.replace(srcString,"<","&lt;");
  	        srcString=CommonStringUtil.replace(srcString,">","&gt;");
  	        srcString=CommonStringUtil.replace(srcString,"COOKIE","cook1e");
  	        srcString=CommonStringUtil.replace(srcString,"SCRIPT","scr1pt");
  	        srcString=CommonStringUtil.replace(srcString,"OBJECT","ob1ect");
  	        srcString=CommonStringUtil.replace(srcString,"APPLET","app1et");
  	        srcString=CommonStringUtil.replace(srcString,"EMBED","embedd");
  	        srcString=CommonStringUtil.replace(srcString,"FRAME","frami");
  	        srcString=CommonStringUtil.replace(srcString,"'","''");
  	        srcString=CommonStringUtil.replace(srcString,"\"","\"\"");
  	        srcString=CommonStringUtil.replace(srcString,"\\","\\\\");
  	        //srcString=CommonStringUtil.replace(srcString,";","");
  	        srcString=CommonStringUtil.replace(srcString,"#","");
  	        srcString=CommonStringUtil.replace(srcString,"--","");
  	        srcString=CommonStringUtil.replace(srcString,"/","");
  	        srcString=CommonStringUtil.replace(srcString,",","");
  	        srcString=CommonStringUtil.replace(srcString,"?","");
  		}    
        catch (Exception ex)
  		{
  			ex.printStackTrace();
  		}

        return srcString;
      }
    
    /**
     * getContentsStrCnvr 로 등록된 특수문자를 표현하기 위해
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */    
    public static String putContentsStrCnvr(String srcString) {
    	
    	String tmpString = srcString;
    	
		try 
		{
			//tmpString = tmpString.replaceAll("&lt", "<");
			//tmpString = tmpString.replaceAll("&gt", ">");
			tmpString = tmpString.replaceAll("<","&lt;");
			tmpString = tmpString.replaceAll(">","&gt;");
			tmpString = tmpString.replaceAll("&lt;br&gt;", "<br>");
//			Log.debug("putContentsStrCnvr");

		
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return  tmpString;
	
	}    
    

    
    
    /**
     *  제목, email등 사용자 입력내용 태그 변환하여 저장
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String chktag(String srcString) {   
        if (srcString == null){
          return null;
        }
        
        try 
  		{
        	 srcString=srcString.toUpperCase();
 	        srcString=CommonStringUtil.replace(srcString,"<","&lt;");
 	        srcString=CommonStringUtil.replace(srcString,"</","&lt;/");
 	        srcString=CommonStringUtil.replace(srcString,">","&gt;");
 	        srcString=CommonStringUtil.replace(srcString,">/","&gt;/");
 	        srcString=CommonStringUtil.replace(srcString,"'","''");
 	        srcString=CommonStringUtil.replace(srcString,"\\","\\\\");
 	        srcString=CommonStringUtil.replace(srcString,";","");
 	        srcString=CommonStringUtil.replace(srcString,",","");
 	        srcString=CommonStringUtil.replace(srcString,"/","");
 	        srcString=CommonStringUtil.replace(srcString,"#","");
 	        srcString=CommonStringUtil.replace(srcString,"--","");
 	        srcString=CommonStringUtil.replace(srcString,"-","");
 	        srcString=CommonStringUtil.replace(srcString,"NULL","");
 	        srcString=CommonStringUtil.replace(srcString,"SCRIPT","scr1pt");
 	        srcString=CommonStringUtil.replace(srcString,"FRAME","frami");
        }    		
        catch (Exception ex)
  		{
  			ex.printStackTrace();
  		}

        return srcString;
      }
      
    /**
     *  제목, email등 사용자 입력내용 태그 변환하여 저장
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String chktel(String srcString) {   
        if (srcString == null){
          return null;
        }

        try 
  		{
        	 srcString=srcString.toUpperCase();
 	        srcString=CommonStringUtil.replace(srcString,"<","&lt;");
 	        srcString=CommonStringUtil.replace(srcString,"</","&lt;/");
 	        srcString=CommonStringUtil.replace(srcString,">","&gt;");
 	        srcString=CommonStringUtil.replace(srcString,">/","&gt;/");
 	        srcString=CommonStringUtil.replace(srcString,"'","''");
 	        srcString=CommonStringUtil.replace(srcString,"\"","\"\"");
 	        srcString=CommonStringUtil.replace(srcString,"\\","\\\\");
 	        srcString=CommonStringUtil.replace(srcString,";","");
 	        srcString=CommonStringUtil.replace(srcString,",","");
 	        srcString=CommonStringUtil.replace(srcString,"/","");
 	        srcString=CommonStringUtil.replace(srcString,"#","");
 	        srcString=CommonStringUtil.replace(srcString,"--","");
 	        srcString=CommonStringUtil.replace(srcString,"NULL","");
 	        srcString=CommonStringUtil.replace(srcString,"SCRIPT","scr1pt");
 	        srcString=CommonStringUtil.replace(srcString,"FRAME","frami");
        }    		
        catch (Exception ex)
  		{
  			ex.printStackTrace();
  		}

        return srcString;
      }
    
	/**
     * 전달받은 문자열을 숫자형식으로 돌려준다.
     * 숫자가 아닌 값이 들어오면 입력값을 그대로 돌려준다.<BR><BR>
     *
     * 사용예) getFormattedNumber("200102042345")<BR>
     * 결 과 ) 200,102,042,345<BR><BR>
     *
     * @return java.lang.String
     * @param pInstr String
     */
    public static String getFormattedNumber(String pInstr) {

         String rStr = pInstr;

          try {
             Object[] testArgs = {new Long(pInstr)};
             MessageFormat form = new MessageFormat( "{0,number,###,###,##0}" );
             rStr = form.format(testArgs);
          } catch ( Exception e ) {}

          return rStr;
     }
     

    /**
     * 
     * 문자에 #!Enter!#, #QuestionMark#, #SingQu#, #DblSingQu# 있을시 치환
     * 
     * @param str 원본 객체
     * @return 치환된 객체 
     * @see dlib.common.util.CommonStringUtil.changeCode
     */
    public static String changeCode(String str) {
        String result = "";
        
        result = str.replaceAll("#!Enter!#", "\n");
        result = result.replaceAll("#QuestionMark#", "?");
        result = result.replaceAll("#SingQu#", "'");
        result = result.replaceAll("#DblSingQu#", "\"");
        
        return result;
    }
    
    
    /**
     * 
     * 배열을 '', '',... 식의 문자열로 변경.
     * 
     * @param str
     * @return String
     * @see dlib.common.util.CommonStringUtil..ArrayToString
     */
    public static String arrayToString(String[] str) {
        String result = "";
        
        for ( int i = 0; i < str.length; i++ ) {
            if ( "".equals(result) ) {
                result = "'" + str[i] + "'";
            } else {
                result = result + ", " + "'" + str[i] + "'";
            }
        }
        
        return result;
    }
    
    
    /**
     * 
     * 배열을 , ,... 식의 문자열로 변경.
     * 
     * @param str
     * @return List
     * @see dlib.common.util.CommonStringUtil..ArrayToString
     */
    public static String arrayToInt(String[] str) {
        String result = "";
        
        for ( int i = 0; i < str.length; i++ ) {
            if ( "".equals(result) ) {
                result = str[i];
            } else {
                result = result + ", " + str[i];
            }
        }
        
        return result;
    }
    
    
    public static String decode64(String value)
	{
		if (value == null) {
			return "";
		}
		else {
			byte[] buf;

			// ...

			buf = value.replace("_","+").getBytes();

			for (int i = 0; i < buf.length; i++) {
				if (buf[i] == 43 || (buf[i] >= 47 && buf[i]<= 57) || buf[i] == 61 || (buf[i] >= 65 && buf[i] <= 90) || (buf[i] >= 97 && buf[i] <= 122)) {
					continue;
				}
	
				return value;
			}
	
			try {
				return new String(new BASE64Decoder().decodeBuffer(value.replace("_","+")));
			}
			catch (Exception e) {
				System.out.println("FormatString - decode64() failed.");
				System.out.println(e.toString());
				System.out.println("----------[^]");
	
				return value;
			}
		}
	}

    public static String encode64(String value)
	{
		if (value == null) {
			return "";
		}
		else {
			return new BASE64Encoder().encode(value.getBytes()).replace("+","_");
		}
	}
    
    
    /**
     * 
     * 비교할 2개의 객체가 같다면 'checked=\"ckecked\"'를 반환해준다.
     * 다르면 공백을 넘겨준다.
     * 
     * @param str1 비교할 객체1
     * @param str2 비교할 객체2
     * @return String 비교할 2개의 객체가 같다면 'checked=\"ckecked\"'를 반환해준다.
     * @see dlib.common.util.CommonStringUtil..isChecked
     */
    public static String isChecked(Object str1, Object str2) {
        String result = "";
        
        if ( str1 != null && str1 instanceof String[] ) {
            String[] val = (String[])str1; 
            for ( int i = 0; i < val.length; i++ ) {
                if ( val[i].equals(String.valueOf(str2)) ) {
                  //return "checked=\"ckecked\"";  웹표준에 의한 태그 속성 수정
                    return "checked";
                }
            }
        } else if ( str1 != null && str1 instanceof String ) {
            if ( String.valueOf(str1).equals(String.valueOf(str2)) ) {
                return "checked";
            }
        }
        return result;
    }
    
    
    /**
     * 
     * 비교할 2개의 객체가 같다면 'selected=\"selected\"'를 반환해준다.
     * 다르면 공백을 넘겨준다.
     * 
     * @param str1 비교할 객체1
     * @param str2 비교할 객체2
     * @return String 비교할 2개의 객체가 같다면 'selected=\"selected\"'를 반환해준다.
     * @see dlib.common.util.CommonStringUtil..isChecked
     */
    public static String isSelected(Object str1, Object str2) {
        String result = "";

        if ( str1 != null && str1 instanceof String[] ) {
            String[] val = (String[])str1; 
            for ( int i = 0; i < val.length; i++ ) {
                if ( val[i].equals(String.valueOf(str2)) ) {
                    return "selected=\"selected\"";
                }
            }
        } else if ( str1 != null ) {
            if ( String.valueOf(str1).equals(String.valueOf(str2)) ) {
                return "selected=\"selected\"";
            }
        }
        return result;
    }
    
    /**
     * 
     * List객체를 구분자로 연결시켜 반환해준다.
     * 
     * @param str1 구분할 객체
     * @param str2 구분자
     * @return String 배열객체를 구분자로 연결시켜 반환해준다.
     * @see dlib.common.util.CommonStringUtil.arrayToStringDelim
     */
    public static String arrayToStringDelim(List<String> str1, String str2) {
        String result = "";
        for ( int i = 0; str1 != null && i < str1.size(); i++ ) {
            if ( result.equals("") ) {
                result = str1.get(i);
            } else {
                result += str2 + str1.get(i);
            }
        }
        return result;
    }
    
    /**
     * 
     * 배열객체를 구분자로 연결시켜 반환해준다.
     * 
     * @param str1 구분할 객체
     * @param str2 구분자
     * @return String 배열객체를 구분자로 연결시켜 반환해준다.
     * @see dlib.common.util.CommonStringUtil.arrayToStringDelim
     */
    public static String arrayToStringDelim(String[] str1, String str2) {
        String result = "";
        for ( int i = 0; str1 != null && i < str1.length; i++ ) {
            if ( result.equals("") ) {
                result = str1[i];
            } else {
                result += str2 + str1[i];
            }
        }
        return result;
    }
    
    /**
     * 배열에 지정한 문자가 검사할 문자열에 포함되어 있는지 확인한다.
     * @param source 원본 문자열
     * @param subject 검사할 문자열을 배열로 넘김
     * 
     */
	 public static boolean checkArray (String source, String[] subject){
		 boolean result = false;
		 
		 if(subject==null){
			 return result;
		 } 
		 
		 for(int i=0;i<subject.length;i++){
			  if(source.equals(subject[i])){
				  result = true;
			  }
		 }
		
		 return result;
      }
	 
	 
	 /**
     * 특정 문자와 문자 사이의 문자열을 반환환다
     * @param source 원본 문자열
     * @param str1 시작점
     * @param str2 종료점
     * @param ex String a="A1234B" => splitResult(a,"A","B") 반환은 1234
     * 
     */
	 public static String splitResult (String source, String str1, String str2){
		 String str = null;
		
		 if(source!=null) {
			
			 try 
				{
				 
				 String[] splitString = source.split(str1);
				 String firstString = splitString[1];
	
				 String[] resultString = firstString.split(str2);
				 String nextString = resultString[0];
				 
				 str = nextString;
					
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
		 }
		 
		 return str;
	}
	 
	/**
     * 자릿수 채우기.
     * @param code 원본 값.
     * @param length 몇 자리로 만들건지(총 자릿수).
     * @param chr  앞에 어떤 문자를 입력시킬껀지.
     * @return
     */
    public static String convert_Length(Object obj, int length, String chr) {
        String result = String.valueOf(obj);
        for ( int i = result.length(); i < length; i++ ) {
            result = chr + result;
        }
        
        return result;
    }


    /**
     *  제목에서 태그 치환
     * 
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getTagChage(String srcString) {    
        if (srcString == null){
          return "";
        }
        
        try 
		{
	        srcString=CommonStringUtil.replace(srcString,"\\", "");
	        srcString=CommonStringUtil.replace(srcString,"&lt","<");
	        srcString=CommonStringUtil.replace(srcString,"&gt",">");
		}
        catch (Exception ex)
		{
			ex.printStackTrace();
		}

        return srcString;
      }
    
    /**
     *  URL Encode
     * 
     * @param srcString, enc
     * @return String
     * @exception Exception
     * @see
     */   
    
    public static String getURLEncode(String srcString){
     return getURLEncode(srcString, "utf-8");
    }
    public static String getURLEncodeKr(String srcString){
        return getURLEncode(srcString, "euc-kr");
    }
    public static String getURLEncode(String srcString, String enc) {  
        if (srcString == null){
          return "";
        }
        
        try 
        {
            if(enc != null && enc.length() > 0){
                srcString = URLEncoder.encode(srcString, enc);
            }else{
                srcString = URLEncoder.encode(srcString);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    
        return srcString;
    }
    
    /**
     *  URL Decode
     * 
     * @param srcString, enc
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getURLDecode(String srcString) {    
        if (srcString == null){
          return "";
        }
        
        try 
        {
        	srcString = URLDecoder.decode(srcString);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  srcString;
    }
    
    public static String getURLDecode(String srcString,String enc) {    
        if (srcString == null){
          return "";
        }
        
        try 
        {
        	srcString = URLDecoder.decode(srcString,enc);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  srcString;
    }

    /**
     *  URL Decode
     * 
     * @param srcString, enc
     * @return String
     * @exception Exception
     * @see
     */   
    public static String getURLDecodeObj(Object object) {    
        if (object == null){
          return "";
        }
        
        try 
        {
         object = URLDecoder.decode((String) object);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return (String) object;
    }
    
    public static String checkHtmlTag(String strString) {
    	String strNew = "";

    	try {
    	    StringBuffer strTxt = new StringBuffer("");

    	    char chrBuff;
    	    int len = strString.length();

    	    for (int i = 0; i < len; i++) {
	    		chrBuff = (char)strString.charAt(i);
	
	    		switch (chrBuff) {
		    		case '<':
		    		    strTxt.append("&lt;");
		    		    break;
		    		case '>':
		    		    strTxt.append("&gt;");
		    		    break;
		    		case '"':
		    		    strTxt.append("&quot;");
		    		    break;
					case '\'':
						strTxt.append("&#39;");
						break;
		    		default:
		    		    strTxt.append(chrBuff);
	    		}
    	    }
    	    strNew = strTxt.toString();

    	}catch(Exception e) {
    		strNew = "";
    	}
    	return strNew;
    }
   
    public static String checkHtmlGetParam(String strString) {
		String rstr = "";
    	try{
    		rstr = URLEncoder.encode(CommonStringUtil.checkHtmlView(strString),"UTF-8");
    	}catch(Exception e){
    	}
    	return rstr;
    }
    
	/**
     * 주민번호 뒷문자 숨기기.
     * @param str  주민등록번호.
     * @return
     */
    public static String jumin_hide(String str) {
    	if(str == null || str.length() <= 0){
    		return "";
    	}
    	if(str.length() >= 6){
    		return str.substring(0, 6) + "*******";
    	}else{
    		return str;
    	}
    }
    
	/**
     * Sql문에 추가되는 파리미터의 특수문자 처리
     * @param strString
     * @return
     */
    public static String checkSqlParam(String strString) {
    	String strNew = "";

    	try {
    	    StringBuffer strTxt = new StringBuffer("");

    	    char chrBuff;
    	    int len = strString.length();

    	    for (int i = 0; i < len; i++) {
	    		chrBuff = (char)strString.charAt(i);
	
	    		switch (chrBuff) {
					case '\'':
						strTxt.append("''");
						break;
		    		case 0x00:
		    			//공백문자 제거
		    		    break;
		    		case 0x0d:
		    			//CR 제거
		    		    break;
		    		case 0x0a:
		    			//LF 제거
		    		    break;
		    		default:
		    		    strTxt.append(chrBuff);
	    		}
    	    }
    	    strNew = strTxt.toString();

    	}catch(Exception e) {
    		strNew = "";
    	}
    	return strNew;
    }
    
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);

        return sw.getBuffer().toString();
   }    
    
   public static boolean toNumberCheck(String str) throws Exception {
        boolean flag = false;
        String reStr = "";
        try{
        	str = str.replace(".", "A"); //소수점
        	reStr = str.replaceFirst("A", ""); // 소수점이 2개인지 체크
         
        	if(reStr.matches("[\\d]+")) flag = true;
        	else flag = false;
       } catch (Exception e) {
         e.printStackTrace();
       }
       return flag;
   }
   
}
