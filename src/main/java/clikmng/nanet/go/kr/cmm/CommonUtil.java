/**
 * 
 * Date 에 대한 Util 클래스 
 * @author
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01  이중호          최초 생성
 *
 * </pre>
 */

package clikmng.nanet.go.kr.cmm;

import java.io.StringReader;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import egovframework.rte.fdl.property.EgovPropertyService;


@SuppressWarnings("unchecked")
public class CommonUtil {

	// 현재 시간을 return
	public static String getCurrent()
	{
		String ret = null;
		Date now = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		ret = sdf.format(now);

		return ret;
	}

	/**
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년, 월, 일을 
     * 증감한다. 년, 월, 일은 가감할 수를 의미하며, 음수를 입력할 경우 감한다.</p>
     * 
     * <pre>
     * DateUtil.addYearMonthDay("19810828", 0, 0, 19)  = "19810916"
     * DateUtil.addYearMonthDay("20060228", 0, 0, -10) = "20060218"
     * DateUtil.addYearMonthDay("20060228", 0, 0, 10)  = "20060310"
     * DateUtil.addYearMonthDay("20060228", 0, 0, 32)  = "20060401"
     * DateUtil.addYearMonthDay("20050331", 0, -1, 0)  = "20050228"
     * DateUtil.addYearMonthDay("20050301", 0, 2, 30)  = "20050531"
     * DateUtil.addYearMonthDay("20050301", 1, 2, 30)  = "20060531"
     * DateUtil.addYearMonthDay("20040301", 2, 0, 0)   = "20060301"
     * DateUtil.addYearMonthDay("20040229", 2, 0, 0)   = "20060228"
     * DateUtil.addYearMonthDay("20040229", 2, 0, 1)   = "20060301"
     * </pre>
     * 
     * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @param  year 가감할 년. 0이 입력될 경우 가감이 없다
     * @param  month 가감할 월. 0이 입력될 경우 가감이 없다
     * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
     * @return  yyyyMMdd 형식의 날짜 문자열
     * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
     *         입력 값이 <code>null</code>인 경우.
     */
    public static String addYearMonthDay(String sDate, int year, int month, int day) {

    	String dateStr = validChkDate(sDate);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            cal.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        
        if (year != 0) 
            cal.add(Calendar.YEAR, year);
        if (month != 0) 
            cal.add(Calendar.MONTH, month);
        if (day != 0) 
            cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }
    
    /**
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년을 
     * 증감한다. <code>year</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.</p>
     * 
     * <pre>
     * DateUtil.addYear("20000201", 62)  = "20620201"
     * DateUtil.addYear("20620201", -62) = "20000201"
     * DateUtil.addYear("20040229", 2)   = "20060228"
     * DateUtil.addYear("20060228", -2)  = "20040228"
     * DateUtil.addYear("19000101", 200) = "21000101"
     * </pre>
     * 
     * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @param  year 가감할 년. 0이 입력될 경우 가감이 없다
     * @return  yyyyMMdd 형식의 날짜 문자열
     * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
     *         입력 값이 <code>null</code>인 경우.
     */
    public static String addYear(String dateStr, int year) {
        return addYearMonthDay(dateStr, year, 0, 0);
    }
    
    /**
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 월을 
     * 증감한다. <code>month</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.</p>
     * 
     * <pre>
     * DateUtil.addMonth("20010201", 12)  = "20020201"
     * DateUtil.addMonth("19800229", 12)  = "19810228"
     * DateUtil.addMonth("20040229", 12)  = "20050228"
     * DateUtil.addMonth("20050228", -12) = "20040228"
     * DateUtil.addMonth("20060131", 1)   = "20060228"
     * DateUtil.addMonth("20060228", -1)  = "20060128"
     * </pre>
     * 
     * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @param  month 가감할 월. 0이 입력될 경우 가감이 없다
     * @return  yyyyMMdd 형식의 날짜 문자열
     * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
     *         입력 값이 <code>null</code>인 경우.
     */
    public static String addMonth(String dateStr, int month) {
        return addYearMonthDay(dateStr, 0, month, 0);
    }
    
    /**
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 일(day)를  
     * 증감한다. <code>day</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
     * <br/><br/>
     * 위에 정의된 addDays 메서드는 사용자가 ParseException을 반드시 처리해야 하는 불편함이
     * 있기 때문에 추가된 메서드이다.</p>
     * 
     * <pre>
     * DateUtil.addDay("19991201", 62) = "20000201"
     * DateUtil.addDay("20000201", -62) = "19991201"
     * DateUtil.addDay("20050831", 3) = "20050903"
     * DateUtil.addDay("20050831", 3) = "20050903"
     * // 2006년 6월 31일은 실제로 존재하지 않는 날짜이다 -> 20060701로 간주된다
     * DateUtil.addDay("20060631", 1) = "20060702"
     * </pre>
     * 
     * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
     * @return  yyyyMMdd 형식의 날짜 문자열
     * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
     *         입력 값이 <code>null</code>인 경우.
     */
    public static String addDay(String dateStr, int day) {
        return addYearMonthDay(dateStr, 0, 0, day);
    }
    
    /**
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 <code>dateStr1</code>과 <code>
     * dateStr2</code> 사이의 일 수를 구한다.<br>
     * <code>dateStr2</code>가 <code>dateStr1</code> 보다 과거 날짜일 경우에는
     * 음수를 반환한다. 동일한 경우에는 0을 반환한다.</p>
     * 
     * <pre>
     * DateUtil.getDaysDiff("20060228","20060310") = 10
     * DateUtil.getDaysDiff("20060101","20070101") = 365
     * DateUtil.getDaysDiff("19990228","19990131") = -28
     * DateUtil.getDaysDiff("20060801","20060802") = 1
     * DateUtil.getDaysDiff("20060801","20060801") = 0
     * </pre>
     * 
     * @param  dateStr1 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @param  dateStr2 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @return  일 수 차이.
     * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
     *         입력 값이 <code>null</code>인 경우.
     */
    public static int getDaysDiff(String sDate1, String sDate2) {
    	String dateStr1 = validChkDate(sDate1);
    	String dateStr2 = validChkDate(sDate2);
    	
        if (!checkDate(sDate1) || !checkDate(sDate2)) {
            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(dateStr1);
            date2 = sdf.parse(dateStr2);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2);
        }
        int days1 = (int)((date1.getTime()/3600000)/24);
        int days2 = (int)((date2.getTime()/3600000)/24);
        
        return days2 - days1;
    }
        
    /**
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 유효한 날짜인지 검사.</p>
     * 
     * <pre>
     * DateUtil.checkDate("1999-02-35") = false
     * DateUtil.checkDate("2000-13-31") = false
     * DateUtil.checkDate("2006-11-31") = false
     * DateUtil.checkDate("2006-2-28")  = false
     * DateUtil.checkDate("2006-2-8")   = false
     * DateUtil.checkDate("20060228")   = true
     * DateUtil.checkDate("2006-02-28") = true
     * </pre>
     * 
     * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @return  유효한 날짜인지 여부
     */
    public static boolean checkDate(String sDate) {
    	String dateStr = validChkDate(sDate);

        String year  = dateStr.substring(0,4);
        String month = dateStr.substring(4,6);
        String day   = dateStr.substring(6);
   
        return checkDate(year, month, day);
    }   

    /**
     * <p>입력한 년, 월, 일이 유효한지 검사.</p>
     * 
     * @param  year 연도
     * @param  month 월
     * @param  day 일
     * @return  유효한 날짜인지 여부
     */
    public static boolean checkDate(String year, String month, String day) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
            
            Date result = formatter.parse(year + "." + month + "." + day);
            String resultStr = formatter.format(result);
            if (resultStr.equalsIgnoreCase(year + "." + month + "." + day))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 날짜형태의 String의 날짜 포맷 및 TimeZone을 변경해 주는 메서드
     *
     * @param  strSource       바꿀 날짜 String
     * @param  fromDateFormat  기존의 날짜 형태
     * @param  toDateFormat    원하는 날짜 형태
     * @param  strTimeZone     변경할 TimeZone(""이면 변경 안함)
     * @return  소스 String의 날짜 포맷을 변경한 String
     */
    public static String convertDate(String strSource, String fromDateFormat, 
            String toDateFormat, String strTimeZone) {
        SimpleDateFormat simpledateformat = null;
        Date date = null;
        String _fromDateFormat = "";
        String _toDateFormat = "";
        
        if(CommonStringUtil.isNullToString(strSource).trim().equals("")) {
            return "";
        }
        if(CommonStringUtil.isNullToString(fromDateFormat).trim().equals(""))
        {
        	_fromDateFormat = "yyyyMMddHHmmss";                    // default값
        }
        else
        {
        	_fromDateFormat = fromDateFormat;
        }
        if(CommonStringUtil.isNullToString(toDateFormat).trim().equals(""))
        {
        	_toDateFormat = "yyyy-MM-dd HH:mm:ss";                 // default값
	    }
	    else
	    {
	    	_toDateFormat = toDateFormat;
	    }

        try {
        	simpledateformat = new SimpleDateFormat(_fromDateFormat, Locale.getDefault());
            date = simpledateformat.parse(strSource);
            if (!CommonStringUtil.isNullToString(strTimeZone).trim().equals("")) {
                simpledateformat.setTimeZone(TimeZone.getTimeZone(strTimeZone));
            }
            simpledateformat = new SimpleDateFormat(_toDateFormat, Locale.getDefault());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }        
        return simpledateformat.format(date);
        
    }    
    
    
    /**
     * yyyyMMdd 형식의 날짜문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다<br/>
    * <pre>
    * ex) 20030405, ch(.) -> 2003.04.05
    * ex) 200304, ch(.) -> 2003.04
    * ex) 20040101,ch(/) --> 2004/01/01 로 리턴
    * </pre>
    * 
    * @param date yyyyMMdd 형식의 날짜문자열
    * @param ch 구분자
    * @return 변환된 문자열
     */
    public static String formatDate(String sDate, String ch) {
    	//String dateStr = validChkDate(sDate);

        String str = sDate.trim();
        String yyyy = "";
        String mm = "";
        String dd = "";

        if (str.length() == 8) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";

            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;

            dd = str.substring(6, 8);
            if (dd.equals("00"))
                return yyyy + ch + mm;

            return yyyy + ch + mm + ch + dd;
        } else if (str.length() == 6) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";

            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;

            return yyyy + ch + mm;
        } else if (str.length() == 4) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";
            else
                return yyyy;
        } else if (str.length() > 8) {
        	str = str.replaceAll("-", "");
        	 yyyy = str.substring(0, 4);
        	 
             if (yyyy.equals("0000"))
                 return "";

             mm = str.substring(4, 6);
             if (mm.equals("00"))
                 return yyyy;

             dd = str.substring(6, 8);
             if (dd.equals("00"))
                 return yyyy + ch + mm;

             return yyyy + ch + mm + ch + dd;
        } else{
        	 return "";
        }
           
    }    
    
    /**
     * HH24MISS 형식의 시간문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다 <br>
     * <pre>
     *     ex) 151241, ch(/) -> 15/12/31
     * </pre>
     *
     * @param str HH24MISS 형식의 시간문자열
     * @param ch 구분자
     * @return 변환된 문자열
     */
     public static String formatTime(String sTime, String ch) {
     	String timeStr = validChkTime(sTime);
        return timeStr.substring(0, 2) + ch + timeStr.substring(2, 4) + ch + timeStr.substring(4, 6);
     }    
    
     /**
      * 연도를 입력 받아 해당 연도 2월의 말일(일수)를 문자열로 반환한다.
      * 
      * @param year
      * @return 해당 연도 2월의 말일(일수)
      */
     public String leapYear(int year) {
         if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
             return "29";
         }

         return "28";
     }    
    
     /**
      * <p>입력받은 연도가 윤년인지 아닌지 검사한다.</p>
      * 
      * <pre>
      * DateUtil.isLeapYear(2004) = false
      * DateUtil.isLeapYear(2005) = true
      * DateUtil.isLeapYear(2006) = true
      * </pre>
      * 
      * @param  year 연도
      * @return  윤년 여부
      */
     public static boolean isLeapYear(int year) {
         if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
             return false;
         }
         return true;
     }     
     
     
     /**
      * 현재(한국기준) 날짜정보를 얻는다.                     <BR>
      * 표기법은 yyyy-mm-dd                                  <BR>
      * @return  String      yyyymmdd형태의 현재 한국시간.   <BR>
      */
     public static String getToday(){
         return getCurrentDate("");
     }

 	/**
 	 * 문자열속에 포함된 특정문자의 수를 되돌린다.
 	 * Default - '#'     *
 	 * 호출양식 :
 	 * int count = count(source_str);
 	 * int count = count(source_str, '*');
 	 * @param str String. 문자열
 	 * @return int
 	 * @exception  N/A
 	 */
 	public static int countChar(String str) {
 		int i;
 		int count = 0;

 		for (i = 0; i < str.length(); i++)
 			if (str.charAt(i) == '#')
 				count++;

 		return count;
 	}

 	/**
 	 *     매개변수로 전달된 문자열을 형식화 문자열에 맞추어 변환한다.
 	 *     ------------------------------------------------------------------------
 	 *    문자열 형식화 규칙
 	 *    1. 좌측문자부터 변환이 되며, 부족한 문자열을 무시된다.
 	 *    2. 숫자가 아닌 문자열이 매개변수로 전달되면
 	 *    FormatTypeDismatchException이 발생한다.
 	 *    3. 형식문자열을 제외한 문자열은 강제문자열로 취급한다.
 	 *    (강제문자열 - 사용자정의 출력 문자열)
 	 *    4. 형식문자열
 	 *    '#'  - Digit
 	 *    etc. - String
 	 *    5. 기능
 	 *        - 매개변수 문자열과 형식문자열의 갯수 일치여부 판별
 	 *    6. 추가되어야할 기능
 	 *        - 형식문자열 확장
 	 *
 	 *     예) String return_value = format("20010101", "####-##-##");
 	 *        String return_value = format("200101011123", "####-##-## ##:##");
 	 *    --------------------------------------------------------------------------
 	 *   @param str String.  날짜 문자열
 	 *   @param format_str String. 형식문자열
 	 *   @return String
 	 *   @exception  Exception
 	 */
 	public static String format(String str, String format_str) {

 		int i;
 		int ptr = 0;
 		StringBuffer strbuf = new StringBuffer();

 		try {
 			if ((str == null) || (str.length() == 0))
 				return "";

 			if (str.length() != countChar(format_str)) {
 				System.out.println("[CommUtill/format]Input String : " + str);
 				System.out.println("[CommUtill/format]Format String : "
 						+ format_str);

 				throw new Exception("변경문자열의 수가 일치하지 않습니다.");
 			}

 			for (i = 0; i < format_str.length(); i++) {
 				if (format_str.charAt(i) == '#')
 					strbuf.append(str.charAt(ptr++));
 				else
 					strbuf.append(format_str.charAt(i));
 			}

 			return strbuf.toString();
 		} catch (Exception e) {
 			System.out.println("[CommUtill/format]Exception : "
 					+ e.getMessage());
 			return "";
 		}
 	}
 	/**
 	 * 문자열의 길이가 size크기가 되도록 '0'을 문자열앞에 덧붙인다.
 	 * @param str String 문자열
 	 * @param size int. 길이
 	 * @return String
 	 */
 	public static String addZero(String str, int size) {
 		String result = "";

 		if (size < str.length())
 			return str;

 		for (int i = 0; i < size - str.length(); i++)
 			result = result + "0";

 		return result + str;
 	}

 	/**
 	 * 현재(시스템) 날짜를 되돌려준다.
 	 * 호출양식 :
 	 * String sysdate = systemDate("####-##-##");
 	 * @param format_str String. 날짜 형식 문자열
 	 * @return String
 	 * @exception  N/A
 	 */
 	@SuppressWarnings("static-access")
	public static String systemDate(String format_str) {
 		Calendar cd = new java.util.GregorianCalendar(java.util.Locale.KOREAN);

 		String year = String.valueOf(cd.get(cd.YEAR));
 		String month = addZero(String.valueOf(cd.get(cd.MONTH) + 1), 2);
 		String day = addZero(String.valueOf(cd.get(cd.DAY_OF_MONTH)), 2);
 		String hour = addZero(String.valueOf(cd.get(cd.HOUR_OF_DAY)), 2);
 		String minute = addZero(String.valueOf(cd.get(cd.MINUTE)), 2);
 		String second = addZero(String.valueOf(cd.get(cd.SECOND)), 2);

 		return format(year + month + day, format_str);
 	}
     /**
      * 현재(한국기준) 날짜정보를 얻는다.                     <BR>
      * 표기법은 yyyy-mm-dd                                  <BR>
      * @return  String      yyyymmdd형태의 현재 한국시간.   <BR>
      */
     public static String getCurrentDate(String dateType) {
         Calendar aCalendar = Calendar.getInstance();

         int year = aCalendar.get(Calendar.YEAR);
         int month = aCalendar.get(Calendar.MONTH) + 1;
         int date = aCalendar.get(Calendar.DATE);
         String strDate = Integer.toString(year) +
                 ((month<10) ? "0" + Integer.toString(month) : Integer.toString(month)) +
                 ((date<10) ? "0" + Integer.toString(date) : Integer.toString(date));
         
         if(!"".equals(dateType)) strDate = convertDate(strDate, "yyyyMMdd", dateType);

         return  strDate;
     }
     
	/**
	 * 날짜형태의 String의 날짜 포맷만을 변경해 주는 메서드
	 * @param sDate 날짜
	 * @param sTime 시간
	 * @param sFormatStr 포멧 스트링 문자열
	 * @return 지정한 날짜/시간을 지정한 포맷으로 출력
	 * @See Letter  Date or Time Component  Presentation  Examples  
	           G  Era designator  Text  AD  
	           y  Year  Year  1996; 96  
	           M  Month in year  Month  July; Jul; 07  
	           w  Week in year  Number  27  
	           W  Week in month  Number  2  
	           D  Day in year  Number  189  
	           d  Day in month  Number  10  
	           F  Day of week in month  Number  2  
	           E  Day in week  Text  Tuesday; Tue  
	           a  Am/pm marker  Text  PM  
	           H  Hour in day (0-23)  Number  0  
	           k  Hour in day (1-24)  Number  24  
	           K  Hour in am/pm (0-11)  Number  0  
	           h  Hour in am/pm (1-12)  Number  12  
	           m  Minute in hour  Number  30  
	           s  Second in minute  Number  55  
	           S  Millisecond  Number  978  
	           z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00  
	           Z  Time zone  RFC 822 time zone  -0800  
	           
	            
	           
	           Date and Time Pattern  Result  
	           "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT  
	           "EEE, MMM d, ''yy"  Wed, Jul 4, '01  
	           "h:mm a"  12:08 PM  
	           "hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time  
	           "K:mm a, z"  0:08 PM, PDT  
	           "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM  
	           "EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700  
	           "yyMMddHHmmssZ"  010704120856-0700  
	
	 */
    public static String convertDate(String sDate, String sTime, String sFormatStr) {
    	String dateStr = validChkDate(sDate);
    	String timeStr = validChkTime(sTime);
    	
    	Calendar cal = null;
    	cal = Calendar.getInstance() ;
    	
    	cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
    	cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
    	cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(timeStr.substring(0,2)));
    	cal.set(Calendar.MINUTE      , Integer.parseInt(timeStr.substring(2,4)));
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(sFormatStr,Locale.ENGLISH);
    	
    	return sdf.format(cal.getTime());
    }   

    /**
     * 입력받은 일자 사이의 임의의 일자를 반환
     * @param sDate1 시작일자
     * @param sDate2 종료일자
     * @return 임의일자
     */
    public static String getRandomDate(String sDate1, String sDate2) {    
    	String dateStr1 = validChkDate(sDate1);
    	String dateStr2 = validChkDate(sDate2);

    	String randomDate   = null;
    	
    	int sYear, sMonth, sDay;
    	int eYear, eMonth, eDay;
    	
    	sYear  = Integer.parseInt(dateStr1.substring(0, 4));
    	sMonth = Integer.parseInt(dateStr1.substring(4, 6));
    	sDay   = Integer.parseInt(dateStr1.substring(6, 8));
    	
    	eYear  = Integer.parseInt(dateStr2.substring(0, 4));
    	eMonth = Integer.parseInt(dateStr2.substring(4, 6));
    	eDay   = Integer.parseInt(dateStr2.substring(6, 8));
    	
    	GregorianCalendar beginDate = new GregorianCalendar(sYear, sMonth-1, sDay,    0, 0);
    	GregorianCalendar endDate   = new GregorianCalendar(eYear, eMonth-1, eDay,   23,59);
    	
    	if (endDate.getTimeInMillis() < beginDate.getTimeInMillis()) {
    	    throw new IllegalArgumentException("Invalid input date : " + sDate1 + "~" + sDate2);
    	}
    	
    	SecureRandom r = new SecureRandom();

    	long rand = ((r.nextLong()>>>1)%( endDate.getTimeInMillis()-beginDate.getTimeInMillis() + 1)) + beginDate.getTimeInMillis();
    	
    	GregorianCalendar cal = new GregorianCalendar();
    	//SimpleDateFormat calformat = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat calformat = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
    	cal.setTimeInMillis(rand);
    	randomDate = calformat.format(cal.getTime()); 
    	
    	// 랜덤문자열를 리턴
    	return  randomDate;
    }
          
    /**
     * 입력받은 양력일자를 변환하여 음력일자로 반환 
     * @param sDate 양력일자
     * @return 음력일자
     */
    public static HashMap toLunar(String sDate) {
    	
    	String dateStr = validChkDate(sDate);

		HashMap hm = new HashMap();
		hm.put("day", "");
		hm.put("leap", 0);

		if(dateStr.length() != 8) {
			return hm;
		}
		/*
		Calendar cal ;
		ChineseCalendar lcal ;
		
		cal = Calendar.getInstance() ;
		lcal = new ChineseCalendar();
		
		cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));
		
		lcal.setTimeInMillis(cal.getTimeInMillis());
		
		String year  = String.valueOf(lcal.get(ChineseCalendar.EXTENDED_YEAR) - 2637);
		String month = String.valueOf(lcal.get(ChineseCalendar.MONTH        ) + 1   );
		String day   = String.valueOf(lcal.get(ChineseCalendar.DAY_OF_MONTH )       );
		String leap  = String.valueOf(lcal.get(ChineseCalendar.IS_LEAP_MONTH)       );
		
		String pad4Str = "0000";
		String pad2Str = "00";
		
		String retYear  = (pad4Str + year ) .substring(year .length());    
		String retMonth = (pad2Str + month) .substring(month.length());    
		String retDay   = (pad2Str + day  ) .substring(day  .length());    
		
		String SDay = retYear+retMonth+retDay;
		
		hm.put("day", SDay);
		hm.put("leap", leap);
		*/
    	return hm;
	}

    /**
     * 입력받은 음력일자를 변환하여 양력일자로 반환 
     * @param sDate 음력일자
     * @param iLeapMonth 음력윤달여부(IS_LEAP_MONTH)
     * @return 양력일자
     */
	public static String toSolar(String sDate, int iLeapMonth) {
		/*
		String dateStr = validChkDate(sDate);

    	Calendar cal ;
    	
		ChineseCalendar lcal ;
		
		cal = Calendar.getInstance() ;
		lcal = new ChineseCalendar();
		
      
		lcal.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(dateStr.substring(0,4)) + 2637);
		lcal.set(ChineseCalendar.MONTH        , Integer.parseInt(dateStr.substring(4,6)) - 1);
		lcal.set(ChineseCalendar.DAY_OF_MONTH , Integer.parseInt(dateStr.substring(6,8)));
		lcal.set(ChineseCalendar.IS_LEAP_MONTH, iLeapMonth);
		
		cal.setTimeInMillis(lcal.getTimeInMillis());
		
		String year  = String.valueOf(cal.get(Calendar.YEAR        )    );
		String month = String.valueOf(cal.get(Calendar.MONTH       ) + 1);
		String day   = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)    );
		
		String pad4Str = "0000";
		String pad2Str = "00";
		
		String retYear  = (pad4Str + year ).substring(year .length());    
		String retMonth = (pad2Str + month).substring(month.length());    
		String retDay   = (pad2Str + day  ).substring(day  .length());    
		
		return retYear+retMonth+retDay;
		*/
    	return "";
	}


    /**
     * 입력받은 요일의 영문명을 국문명의 요일로 반환 
     * @param sWeek 영문 요일명
     * @return 국문 요일명
     */
	public static String convertWeek(String sWeek) {
		String retStr = null;
		
		if        (sWeek.equals("SUN")   ) { retStr = "일요일";
		} else if (sWeek.equals("MON")   ) { retStr = "월요일";
		} else if (sWeek.equals("TUE")   ) { retStr = "화요일";
		} else if (sWeek.equals("WED")   ) { retStr = "수요일";
		} else if (sWeek.equals("THR")   ) { retStr = "목요일";
		} else if (sWeek.equals("FRI")   ) { retStr = "금요일";
		} else if (sWeek.equals("SAT")   ) { retStr = "토요일";
		}
		   
		return retStr;
	}

    /**
     * 입력일자의 유효 여부를 확인
     * @param sDate 일자
     * @return 유효 여부
     */
    public static boolean validDate(String sDate) {
    	String dateStr = validChkDate(sDate);

		Calendar cal ;
		boolean ret  = false;
		
		cal = Calendar.getInstance() ;
		
		cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));
		
		String year  = String.valueOf(cal.get(Calendar.YEAR        )    );
		String month = String.valueOf(cal.get(Calendar.MONTH       ) + 1);
		String day   = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)    );
		
		String pad4Str = "0000";
		String pad2Str = "00";
		
		String retYear  = (pad4Str + year ).substring(year .length());
		String retMonth = (pad2Str + month).substring(month.length());
		String retDay   = (pad2Str + day  ).substring(day  .length());
		
		String retYMD = retYear+retMonth+retDay;
		
		if(sDate.equals(retYMD)) {
			ret  = true;
		}
		
		return ret;
	}
    
    /**
     * 입력일자, 요일의 유효 여부를 확인
     * @param     sDate 일자
     * @param     sWeek 요일 (DAY_OF_WEEK)
     * @return    유효 여부
     */
    public static boolean validDate(String sDate, int sWeek) {
    	String dateStr = validChkDate(sDate);

		Calendar cal ;
		boolean ret  = false;
		
		cal = Calendar.getInstance() ;
		
		cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));
		
		int    Week  =                cal.get(Calendar.DAY_OF_WEEK      );
		
		if (validDate(sDate)) {
			if (sWeek == Week) {
				ret = true;
			}
		}
		
		return ret;
	}

    /**
     * 입력시간의 유효 여부를 확인
     * @param     sTime 입력시간
     * @return    유효 여부
     */
    public static boolean validTime(String sTime) {
    	String timeStr = validChkTime(sTime);

		Calendar cal ;
		boolean ret = false;
		
		cal = Calendar.getInstance() ;
		
		cal.set(Calendar.HOUR_OF_DAY  , Integer.parseInt(timeStr.substring(0,2)));
		cal.set(Calendar.MINUTE       , Integer.parseInt(timeStr.substring(2,4)));
		
		String HH     = String.valueOf(cal.get(Calendar.HOUR_OF_DAY  ));
		String MM     = String.valueOf(cal.get(Calendar.MINUTE       ));
		
		String pad2Str = "00";
		
		String retHH = (pad2Str + HH).substring(HH.length());
		String retMM = (pad2Str + MM).substring(MM.length());
		
		String retTime = retHH + retMM;
		
		if(sTime.equals(retTime)) {
			ret  = true;
		}
		
		return ret;
	}
    
    /**
     * 입력된 일자에 연, 월, 일을 가감한 날짜의 요일을 반환
     * @param sDate 날짜
     * @param year 연
     * @param month 월
     * @param day 일
     * @return 계산된 일자의 요일(DAY_OF_WEEK)
     */
    public static String addYMDtoWeek(String sDate, int year, int month, int day) {
    	String dateStr = validChkDate(sDate);
    	
		dateStr = addYearMonthDay(dateStr, year, month, day);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
		try {
			cal.setTime(sdf.parse(dateStr));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}
		
		SimpleDateFormat rsdf = new SimpleDateFormat("E",Locale.ENGLISH);
		
		return rsdf.format(cal.getTime());
	}

    /**
     * 입력된 일자에 연, 월, 일, 시간, 분을 가감한 날짜, 시간을 포멧스트링 형식으로 반환
     * @param sDate 날짜
     * @param sTime 시간
     * @param year 연
     * @param month 월
     * @param day 일
     * @param hour 시간
     * @param minute 분
     * @param formatStr 포멧스트링
     * @return
     */
    public static String addYMDtoDayTime(String sDate, String sTime, int year, int month, int day, int hour, int minute, String formatStr) {
    	String dateStr = validChkDate(sDate);
    	String timeStr = validChkTime(sTime);
    	
		dateStr = addYearMonthDay(dateStr, year, month, day);
		
		dateStr = convertDate(dateStr, timeStr, "yyyyMMddHHmm");      
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",Locale.ENGLISH);

        try {
    		cal.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
		
		if (hour != 0) {
			cal.add(Calendar.HOUR, hour);
		}

		if (minute != 0) {
			cal.add(Calendar.MINUTE, minute);
		}
		
		SimpleDateFormat rsdf = new SimpleDateFormat(formatStr,Locale.ENGLISH);
		
		return rsdf.format(cal.getTime());
	}
 
    /**
     * 입력된 일자를 int 형으로 반환
     * @param sDate 일자
     * @return int(일자)
     */
    public static int datetoInt(String sDate) {
    	return Integer.parseInt(convertDate(sDate, "0000", "yyyyMMdd"));
    }
    
    /**
     * 입력된 시간을 int 형으로 반환
     * @param sTime 시간
     * @return int(시간)
     */
    public static int timetoInt(String sTime) {
        return Integer.parseInt(convertDate("00000101", sTime, "HHmm"));
    }

    /**
     * 입력된 일자 문자열을 확인하고 8자리로 리턴   
     * @param sDate
     * @return
     */
    public static String validChkDate(String dateStr) {
    	
    	String _dateStr = dateStr;
    	
        if (dateStr == null) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        
        if( (dateStr.trim().length() == 8 || dateStr.trim().length() == 10) == false)
        {
        	throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        
        if (dateStr.length() == 10) {
        	_dateStr = CommonStringUtil.removeMinusChar(dateStr);
        }
        return _dateStr;
    }
 
    /**
     * 입력된 일자 문자열을 확인하고 8자리로 리턴   
     * @param sDate
     * @return
     */
    public static String validChkTime(String timeStr) {
    	String _timeStr = timeStr;
    	
    	if (_timeStr.length() == 5) {
    		_timeStr = CommonStringUtil.remove(_timeStr,':');
    	}
    	if (_timeStr == null || !(_timeStr.trim().length() == 4)) {
    	    throw new IllegalArgumentException("Invalid time format: " + _timeStr);
    	}

    	return _timeStr;
    }
    
    


	 /**
    * <p>XXXXXX - XXXXXXX 형식의 주민번호 앞, 뒤 문자열 2개 입력 받아 유효한 주민번호인지 검사.</p>
    *      
    * 
    * @param   6자리 주민앞번호 문자열 , 7자리 주민뒷번호 문자열
    * @return  유효한 주민번호인지 여부 (True/False)
    */
   public static boolean checkJuminNumber(String jumin1, String jumin2) {
   	
   	String juminNumber = jumin1 + jumin2;
   	String  IDAdd = "234567892345"; 	// 주민등록번호에 가산할 값
   	
		int count_num = 0;
   	int add_num = 0;    	
       int total_id = 0;      //검증을 위한 변수선언
               
       if (juminNumber.length() != 13) return false;	 // 주민등록번호 자리수가 맞는가를 확인
     
      	for (int i = 0; i <12 ; i++){
      		if(juminNumber.charAt(i)< '0' || juminNumber.charAt(i) > '9') return false;		//숫자가 아닌 값이 들어왔는지를 확인
      		count_num = Character.getNumericValue(juminNumber.charAt(i));
      		add_num = Character.getNumericValue(IDAdd.charAt(i));
       	total_id += count_num * add_num;      //유효자리 검증식을 적용
       }
      	      
      	if(Character.getNumericValue(juminNumber.charAt(0)) == 0 || Character.getNumericValue(juminNumber.charAt(0)) == 1){
      		if(Character.getNumericValue(juminNumber.charAt(6)) > 4) return false;
      		String temp = "20" + juminNumber.substring(0,6);
      		if(!CommonUtil.checkDate(temp)) return false;
      	}else{
      		if(Character.getNumericValue(juminNumber.charAt(6)) > 2) return false;
      		String temp = "19" + juminNumber.substring(0,6);
      		if(!CommonUtil.checkDate(temp)) return false;       			
      	}	//주민번호 앞자리 날짜유효성체크 & 성별구분 숫자 체크       		
      		
      	if(Character.getNumericValue(juminNumber.charAt(12)) == (11 - (total_id % 11)) % 10) //마지막 유효숫자와 검증식을 통한 값의 비교
       	return true;
       else
       	return false;  
      	
   }   
   
   /**
    * <p>XXXXXXXXXXXXX 형식의 13자리 주민번호 1개를 입력 받아 유효한 주민번호인지 검사.</p>
    * 
    * 
    * @param   13자리 주민번호 문자열
    * @return  유효한 주민번호인지 여부 (True/False)
    */
   public static boolean checkJuminNumber(String jumin) {        
 	
   	if(jumin.length() != 13) return false;
   	
       return checkJuminNumber(jumin.substring(0,6), jumin.substring(6,13));	//주민번호
       
   }   
   
   /**
    * <p>XXXXXX - XXXXXXX 형식의 법인번호 앞, 뒤 문자열 2개 입력 받아 유효한 법인번호인지 검사.</p>
    * 
    * 
    * @param   6자리 법인앞번호 문자열 , 7자리 법인뒷번호 문자열
    * @return  유효한 법인번호인지 여부 (True/False)
    */
   public static boolean checkBubinNumber(String bubin1, String bubin2) {
   	
   	String bubinNumber = bubin1 + bubin2;
   	
   	int hap = 0;
   	int temp = 1;	//유효검증식에 사용하기 위한 변수
   	
   	if(bubinNumber.length() != 13) return false;	//법인번호의 자리수가 맞는 지를 확인    		
   	
   	for(int i=0; i < 13; i++){
   		if (bubinNumber.charAt(i) < '0' || bubinNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인
   			return false;    		
   	}
   	
   	

   	for ( int i=0; i<13; i++){
   		if(temp ==3) temp = 1;    		
   		hap = hap + (Character.getNumericValue(bubinNumber.charAt(i)) * temp);
   		temp++;
   	}	//검증을 위한 식의 계산
   				
   	if ((10 - (hap%10))%10 == Character.getNumericValue(bubinNumber.charAt(12))) //마지막 유효숫자와 검증식을 통한 값의 비교
   		return true;
   	else
   		return false;    				
   	}
   
   /**
    * <p>XXXXXXXXXXXXX 형식의 13자리 법인번호 1개를 입력 받아 유효한 법인번호인지 검사.</p>
    *     
    * 
    * @param   13자리 법인번호 문자열
    * @return  유효한 법인번호인지 여부 (True/False)
    */
   public static boolean checkBubinNumber(String bubin) {
   	
   	if(bubin.length() != 13) return false;
   	
   	return checkBubinNumber(bubin.substring(0,6), bubin.substring(6,13));
   	}
      

   /**
    * <p>XXX - XX - XXXXX 형식의 사업자번호 앞,중간, 뒤 문자열 3개 입력 받아 유효한 사업자번호인지 검사.</p>
    *     
    * 
    * @param   3자리 사업자앞번호 문자열 , 2자리 사업자중간번호 문자열, 5자리 사업자뒷번호 문자열
    * @return  유효한 사업자번호인지 여부 (True/False)
    */
	public static boolean checkCompNumber(String comp1, String comp2, String comp3) {
	
		String compNumber = comp1 + comp2 + comp3;
		
		int hap = 0;
		int temp = 0;
		int check[] = {1,3,7,1,3,7,1,3,5};  //사업자번호 유효성 체크 필요한 수
	
		if(compNumber.length() != 10)    //사업자번호의 길이가 맞는지를 확인한다.
			return false;
	
		for(int i=0; i < 9; i++){
			if(compNumber.charAt(i) < '0' || compNumber.charAt(i) > '9')  //숫자가 아닌 값이 들어왔는지를 확인한다.
				return false; 
			
			hap = hap + (Character.getNumericValue(compNumber.charAt(i)) * check[temp]); //검증식 적용
			temp++;
		}
			
		hap += (Character.getNumericValue(compNumber.charAt(8))*5)/10;

		if ((10 - (hap%10))%10 == Character.getNumericValue(compNumber.charAt(9))) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true;
		else
			return false;
	}	
	
	 /**
    * <p>XXXXXXXXXX 형식의 10자리 사업자번호 3개를 입력 받아 유효한 사업자번호인지 검사.</p>
    *      
    * 
    * @param   10자리 사업자번호 문자열
    * @return  유효한 사업자번호인지 여부 (True/False)
    */
	public static boolean checkCompNumber(String comp) {
		
		if(comp.length() != 10) return false;
		return checkCompNumber(comp.substring(0,3), comp.substring(3,5), comp.substring(5,10));
		
	}	
	
	
	
	 /**
    * <p>XXXXXX - XXXXXXX 형식의 외국인등록번호 앞, 뒤 문자열 2개 입력 받아 유효한 외국인등록번호인지 검사.</p>
    *      
    * 
    * @param   6자리 외국인등록앞번호 문자열 , 7자리 외국인등록뒷번호 문자열
    * @return  유효한 외국인등록번호인지 여부 (True/False)
    */
	public static boolean checkforeignNumber( String foreign1, String foreign2  ) { 
   	
		String foreignNumber = foreign1 + foreign2; 
		int check = 0; 
		
		if( foreignNumber.length() != 13 )   //외국인등록번호의 길이가 맞는지 확인한다.
			return false; 

		for(int i=0; i < 13; i++){
   		if (foreignNumber.charAt(i) < '0' || foreignNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인한다.
   			return false;    		
   	}
		
    	if(Character.getNumericValue(foreignNumber.charAt(0)) == 0 || Character.getNumericValue(foreignNumber.charAt(0)) == 1){
      		if(Character.getNumericValue(foreignNumber.charAt(6)) == 5 && Character.getNumericValue(foreignNumber.charAt(6)) == 6) return false;
      		String temp = "20" + foreignNumber.substring(0,6);
      		if(!CommonUtil.checkDate(temp)) return false;
      	}else{
      		if(Character.getNumericValue(foreignNumber.charAt(6)) == 5 && Character.getNumericValue(foreignNumber.charAt(6)) == 6) return false;
      		String temp = "19" + foreignNumber.substring(0,6);
      		if(!CommonUtil.checkDate(temp)) return false;       			
      	}	//외국인등록번호 앞자리 날짜유효성체크 & 성별구분 숫자 체크     
		
		for( int i = 0 ; i < 12 ; i++ ) { 
			check += ( ( 9 - i % 8 ) * Character.getNumericValue( foreignNumber.charAt( i ) ) ); 
		} 
				
		if ( check % 11 == 0 ){ 
			check = 1; 
		}else if ( check % 11==10 ){ 
			check = 0; 
		}else 
			check = check % 11; 

		if ( check + 2 > 9 ){ 
			check = check + 2- 10; 
		}else check = check+2;	//검증식을 통합 값의 도출
		
		if( check == Character.getNumericValue( foreignNumber.charAt( 12 ) ) ) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true; 
		else 
			return false; 
		} 
	
	
	 /**
    * <p>XXXXXXXXXXXXX 형식의 13자리 외국인등록번호 1개를 입력 받아 유효한 외국인등록번호인지 검사.</p>
    *     
    * 
    * @param   13자리 외국인등록번호 문자열
    * @return  유효한 외국인등록번호인지 여부 (True/False)
    */
	public static boolean checkforeignNumber( String foreign  ) { 
	
		if(foreign.length() != 13) return false;
		return checkforeignNumber(foreign.substring(0,6), foreign.substring(6,13));
	}
	
	
	   /**
     * 특정숫자 집합에서 랜덤 숫자를 구하는 기능 시작숫자와 종료숫자 사이에서 구한 랜덤 숫자를 반환한다
     * 
     * @param startNum - 시작숫자
     * @param endNum - 종료숫자
     * @return 랜덤숫자
     * @exception MyException
     * @see
     */
    public static int getRandomNum(int startNum, int endNum) {
	int randomNum = 0;

	try {
	    // 랜덤 객체 생성
	    SecureRandom rnd = new SecureRandom();

	    do {
		// 종료숫자내에서 랜덤 숫자를 발생시킨다.
		randomNum = rnd.nextInt(endNum + 1);
	    } while (randomNum < startNum); // 랜덤 숫자가 시작숫자보다 작을경우 다시 랜덤숫자를 발생시킨다.
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return randomNum;
    }

    /**
     * 특정 숫자 집합에서 특정 숫자가 있는지 체크하는 기능 12345678에서 7이 있는지 없는지 체크하는 기능을 제공함
     * 
     * @param sourceInt - 특정숫자집합
     * @param searchInt - 검색숫자
     * @return 존재여부
     * @exception MyException
     * @see
     */
    public static Boolean getNumSearchCheck(int sourceInt, int searchInt) {
	String sourceStr = String.valueOf(sourceInt);
	String searchStr = String.valueOf(searchInt);

	// 특정숫자가 존재하는지 하여 위치값을 리턴한다. 없을 시 -1
	if (sourceStr.indexOf(searchStr) == -1) {
	    return false;
	} else {
	    return true;
	}
    }

    /**
     * 숫자타입을 문자열로 변환하는 기능 숫자 20081212를 문자열 '20081212'로 변환하는 기능
     * 
     * @param srcNumber
     *            - 숫자
     * @return 문자열
     * @exception MyException
     * @see
     */
    public static String getNumToStrCnvr(int srcNumber) {
	String rtnStr = null;

	try {
	    rtnStr = String.valueOf(srcNumber);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return rtnStr;
    }
	
	
    /**
     * 숫자타입을 데이트 타입으로 변환하는 기능
     * 숫자 20081212를 데이트타입  '2008-12-12'로 변환하는 기능
     * @param srcNumber - 숫자
     * @return String
     * @exception MyException 
     * @see  
     */
    public static String getNumToDateCnvr(int srcNumber) {

	String pattern = null;
	String cnvrStr = null;

	String srcStr = String.valueOf(srcNumber);

	// Date 형태인 8자리 및 14자리만 정상처리 
	if (srcStr.length() != 8 && srcStr.length() != 14) {
	    throw new IllegalArgumentException("Invalid Number: " + srcStr + " Length=" + srcStr.trim().length());
	}

	if (srcStr.length() == 8) {
	    pattern = "yyyyMMdd";
	} else if (srcStr.length() == 14) {
	    pattern = "yyyyMMddhhmmss";
	}

	SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern, Locale.KOREA);

	Date cnvrDate = null;

	try {
	    cnvrDate = dateFormatter.parse(srcStr);
	} catch (ParseException e) {
	    e.printStackTrace();
	}

	cnvrStr = String.format("%1$tY-%1$tm-%1$td", cnvrDate);

	return cnvrStr;

    }

    /**
     * 체크할 숫자 중에서 숫자인지 아닌지 체크하는 기능
     * 숫자이면 True, 아니면 False를 반환한다
     * @param checkStr - 체크문자열
     * @return 숫자여부
     * @exception MyException 
     * @see  
     */	
	public static Boolean getNumberValidCheck(String checkStr) {

	    if ( checkStr != null && checkStr.length() > 0 ) {
        	int i;
        	//String sourceStr = String.valueOf(sourceInt);
        
        	int checkStrLt = checkStr.length();
        
        	try {
        	    for (i = 0; i < checkStrLt; i++) {
        
            		// 아스키코드값( '0'-> 48, '9' -> 57)
            		if (checkStr.charAt(i) > 47 && checkStr.charAt(i) < 58) {
            		    continue;
            		} else {
            		    return false;
            		}
        	    }
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
        
        	return true;
	    } else {
	        return false;
	    }
    }

    /**
     * 특정숫자를 다른 숫자로 치환하는 기능 숫자 12345678에서 123를 999로 변환하는 기능을 제공(99945678)
     * 
     * @param srcNumber - 숫자집합
     * @param cnvrSrcNumber - 원래숫자
     * @param cnvrTrgtNumber - 치환숫자
     * @return 치환숫자
     * @exception MyException
     * @see
     */

    public static int getNumberCnvr(int srcNumber, int cnvrSrcNumber, int cnvrTrgtNumber) {

	// 입력받은 숫자를 문자열로 변환
	String source = String.valueOf(srcNumber);
	String subject = String.valueOf(cnvrSrcNumber);
	String object = String.valueOf(cnvrTrgtNumber);

	StringBuffer rtnStr = new StringBuffer();
	String preStr = "";
	String nextStr = source;

	try {

	    // 원본숫자에서 변환대상숫자의 위치를  찾는다.
	    while (source.indexOf(subject) >= 0) {
		preStr = source.substring(0, source.indexOf(subject)); // 변환대상숫자 위치까지 숫자를 잘라낸다
		nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
		source = nextStr;
		rtnStr.append(preStr).append(object); // 변환대상위치 숫자에 변환할 숫자를 붙여준다.	
	    }
	    rtnStr.append(nextStr); // 변환대상 숫자 이후 숫자를 붙여준다.
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Integer.parseInt(rtnStr.toString());
    }

    /**
     * 특정숫자가 실수인지, 정수인지, 음수인지 체크하는 기능 123이 실수인지, 정수인지, 음수인지 체크하는 기능을 제공함
     * 
     * @param srcNumber - 숫자집합
     * @return -1(음수), 0(정수), 1(실수)
     * @exception MyException
     * @see
     */
    public static int checkRlnoInteger(double srcNumber) {

	// byte 1바이트 		▶소수점이 없는 숫자로, 범위 -2^7 ~ 2^7 -1 
	// short 2바이트		▶소수점이 없는 숫자로, 범위 -2^15 ~ 2^15 -1 
	// int 4바이트 		▶소수점이 없는 숫자로, 범위 -2^31 ~ 2^31 - 1 
	// long 8바이트 		▶소수점이 없는 숫자로, 범위 -2^63 ~ 2^63-1 

	// float 4바이트		▶소수점이 있는 숫자로, 끝에 F 또는 f 가 붙는 숫자 (예:3.14f) 
	// double 8바이트	▶소수점이 있는 숫자로, 끝에 아무것도 붙지 않는 숫자 (예:3.14)
	//			▶소수점이 있는 숫자로, 끝에 D 또는 d 가 붙는 숫자(예:3.14d)

	String cnvrString = null;

	if (srcNumber < 0) {
	    return -1;
	} else {
	    cnvrString = String.valueOf(srcNumber);

	    if (cnvrString.indexOf(".") == -1) {
		return 0;
	    } else {
		return 1;
	    }
	}
    }
    
	/**
	 *   null이거나 ""인 String을 주어진 String으로 대체해 리턴한다.
	 *   @param src String. null이거나 ""일 수 있는 String
	 *   @param des String. null이거나 ""일 경우 대체할 String
	 *   @return String
	 */
	public static String nvl(String src, String des) {
		return src == null ? des : ("".equals(src) ? des : src);
	}

	/**
	 *   null 이거나 "null"인 Object 에 대하여 "" 를 반환한다.
	 *   null 이거나 "null" 이 아닐 경우 String 으로 치환되어 반환된다.
	 *   @param Object. null이거나 ""일 수 있는 String
	 *   @return String
	 */
	public static String strnvl(Object o) 
	{
		try
		{
			String result = (String)o;
			return result.toUpperCase().equals("NULL")?"":result;
		}
		catch(Exception e)
		{
			return "";
		}
	}

    /**
     * request키와 값을 hashmap의 키와 값으로 입력
     * 
     * @param request
     * @return 입력된 hashmap
     * @throws Exception
     * @see dlib.poll.common.Common.reqMapping
     */
    public static HashMap reqMapping(HttpServletRequest request){
        HashMap map = new HashMap();
        if(request != null){
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                // value값이 2개 이상이면 String[]
                if ( request.getParameterValues(key).length > 1 ) {
                    map.put(key, request.getParameterValues(key));
                } else {
                    map.put(key, request.getParameter(key));
                }
            }
        }
        return map;
    }
    
    
    /**
     * request키와 값을 model의 키와 값으로 입력
     * 
     * @param model
     * @param request
     * @return 입력된 model
     * @throws Exception
     * @see dlib.poll.common.Common.ModelMapping
     */
        
    public static ModelMap modelMapping(ModelMap model, HttpServletRequest request){
        if(model != null){
            if ( request != null ) {
                Enumeration enu = request.getParameterNames();
                while (enu.hasMoreElements()) {
                    String key = (String) enu.nextElement();
                    // value값이 2개 이상이면 String[]
                    if ( request.getParameterValues(key).length > 1 ) {
                        model.addAttribute(key, request.getParameterValues(key));
                    } else {
                        model.addAttribute(key, request.getParameter(key));
                    }
                }
            }
        }
        return model;
    }
    

    /**
     * 
     * Properties 파일에 있는 데이터 List로 가지고 오기.
     * 
     * @param propertyService 프로퍼티 서비스
     * @param propertiesName 프로퍼티 key명
     * @param separator 구분자
     * @return List
     * @see dlib.poll.common.Common.propertiesToStringArray
     */
    public static List propertiesToStringArray(EgovPropertyService propertyService, String propertiesName, String separator) {
        // 프로퍼티 목록
        Vector property_list = propertyService.getVector(propertiesName);
        // 1번째 배열 (목록), 2번째 배열(첫번째 = value값, 두번째 = text값, ...)
        List list = new ArrayList();
        for ( int i = 0; i < property_list.size(); i++ ) {
            list.add(((String)property_list.get(i)).split(separator));
        }
        
        return list;
    }
    
    /**
     * 
     * Properties 파일에서 가지고 온 List<String> 데이터를 구분자로 List 가지고 오기.
     * 
     * @param propertiesData 프로퍼티 데이터 목록
     * @param separator 구분자
     * @return List
     * @see dlib.poll.common.Common.propertiesToArray
     */
    public static List propertiesToArray(List<String> propertiesData, String separator) {
        // 1번째 배열 (목록), 2번째 배열(첫번째 = value값, 두번째 = text값, ...)
        List list = new ArrayList();
        for ( int i = 0; i < propertiesData.size(); i++ ) {
            list.add(propertiesData.get(i).split(separator));
        }
        
        return list;
    }
    
	/**
	*두개의 날짜를 받아 들여서 그 사이의 날짜를 반환한다.<br>
	*@param		sStartYear		시작 년 (yyyy)
	*@param		sStartMonth		시작 월 (mm)
	*@param		sStartDay		시작 일 (dd)
	*@param		sFinishYear		종료 년 (yyyy)
	*@param		sFinishMonth	종료 월 (mm)
	*@param		sFinishDay		종료 일 (dd)
	*@return	날짜 사이의 값을 반환하는 Stirng []
	*/
	public static Vector getPeriodDate(String sStartYear, String sStartMonth, String sStartDay, String sFinishYear, String sFinishMonth, String sFinishDay)
		throws Exception {

		Vector vec 				= new Vector();
		Date dStartDate			= null;
		Date dFinishDate		= null;

		int stringArrSize;
		int iStartYear		= Integer.parseInt(sStartYear);
		int iStartMonth 	= Integer.parseInt(sStartMonth);
		int iStartDay		= Integer.parseInt(sStartDay);
		int iFinishYear		= Integer.parseInt(sFinishYear);
		int iFinishMonth	= Integer.parseInt(sFinishMonth);
		int iFinishDay		= Integer.parseInt(sFinishDay);

		Calendar cal = Calendar.getInstance();

		DateFormat df = DateFormat.getDateInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		cal.set(iFinishYear,iFinishMonth - 1, iFinishDay);
		dFinishDate = cal.getTime();

		cal.set(iStartYear, iStartMonth-1, iStartDay-1);
		dStartDate = cal.getTime();

		if(dFinishDate.compareTo(dStartDate) < 0 ) {
			//String [] dates	=	new String[0];
			return vec;
		}

		while (!dFinishDate.equals(dStartDate)){
			cal.add(Calendar.DATE, 1);
			dStartDate = cal.getTime();
			vec.addElement(formatter.format(dStartDate));
		}
		//stringArrSize = vec.size();
		
		//String [] dates	=	new String[stringArrSize];
		//for(int i=0;i <vec.size(); i++) {
		//	dates[i] = (String)vec.elementAt(i);
		//}
		return vec;
	}

	/**
	*두개의 날짜를 받아 들여서 그 사이의 날짜를 반환한다.<br>
	*@param		sStartYear		시작 년 (yyyy)
	*@param		sStartMonth		시작 월 (mm)
	*@param		sFinishYear		종료 년 (yyyy)
	*@param		sFinishMonth	종료 월 (mm)
	*@return	날짜 사이의 값을 반환하는 Stirng []
	*/

	public static Vector getPeriodMonth(String sStartYear, String sStartMonth, String sFinishYear, String sFinishMonth) throws Exception {

		Vector vec 				= new Vector();
		Date dStartDate			= null;
		Date dFinishDate		= null;
		
		int stringArrSize;
		int iStartYear		= Integer.parseInt(sStartYear);
		int iStartMonth 	= Integer.parseInt(sStartMonth);
		
		int iFinishYear		= Integer.parseInt(sFinishYear);
		int iFinishMonth	= Integer.parseInt(sFinishMonth);
		
		Calendar cal = Calendar.getInstance();
		
		DateFormat df = DateFormat.getDateInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		
		cal.set(iFinishYear,iFinishMonth - 1,1);
		dFinishDate = cal.getTime();
		
		cal.set(iStartYear, iStartMonth-2,1);
		dStartDate = cal.getTime();
		
		if(dFinishDate.compareTo(dStartDate) < 0 ) {
			//String [] months	=	new String[0];
			return vec;
		}
		
		while (!dFinishDate.equals(dStartDate)){
			cal.add(Calendar.MONTH, 1);
			dStartDate = cal.getTime();
			vec.addElement(formatter.format(dStartDate));
		}
		//stringArrSize = vec.size();
		//String [] months	=	new String[stringArrSize];
		//for(int i=0;i <vec.size(); i++) {
		//	months[i] = (String)vec.elementAt(i);			
		//}
		return vec;
	}

	
	/**
	*하나의 String을 받아 들여서 대문자를 소문자로 반환한다.<br>
	*@param		val
	*@return	날짜 사이의 값을 반환하는 Stirng
	*/

   public static String ConUpperLower(String val) {

        char[] str = val.toCharArray();
        String reVal = "";

        // 문자열의 갯수 만큼 루프를 돔.
        for(int i=0; i<str.length; i++) {

                // 각 인덱스의 문자가 대문자라면 소문자로 변환
                if((str[i] >= 65) && (str[i] <= 90)) {
                        str[i] += 32;
                }
                reVal += str[i];
        }
        // 결과 출력
        return reVal;
    }

 
	/**
	*하나의 String을 받아 들여서 소문자를 대문자로 반환한다.<br>
	*@param		val
	*@return	날짜 사이의 값을 반환하는 Stirng
	*/

   public static String ConLowerUpper(String val) {

        char[] str = val.toCharArray();
        String reVal = "";

        // 문자열의 갯수 만큼 루프를 돔.
        for(int i=0; i<str.length; i++) {

                // 각 인덱스의 문자가 소문자라면 대문자로 변환
                if((str[i] >= 97) && (str[i] <= 122)) {
                        str[i] -= 32;
                }
                reVal += str[i];
        }
        // 결과 출력
        return reVal;
    }

   /**
    * 
    * request의 파라미터 값이 배열이면 구분자로 구분하여 데이터 반환
    * 
    * @param request Request
    * @param param 파라미터
    * @param separator 구분자
    * @return String 파라미터 값이 배열이면 구분자로 구분하여 데이터 반환
    * @see dlib.poll.common.Common.getRequestParam
    */
   public static String getRequestParam(HttpServletRequest request, String param, String separator) {
       if ( request.getParameterValues(param) == null ) {
           if ( request.getParameter(param) == null ) {
               return "";
           } else {
               return request.getParameter(param);
           }
       } else {
           String[] tmp = request.getParameterValues(param);
           String result = "";
           
           for ( int i = 0; i < tmp.length; i++ ) {
               if ( i != 0 ) {
                   result += separator;
               }
               result += tmp[i];
           }
           
           return result;
       }
   }
   

   /**
    * 
    * DB code로 DB명을 가져온다.(db_list.xml)
    * 
    * @param dbcode DB code명
    * @return String 파라미터 값이 배열이면 구분자로 구분하여 데이터 반환
    * @see 
    */   
/*   
   public static String getDbName(String dbcode) throws Exception{
	   	   
       ConfParser confParser = new ConfParser();
       
       // xml 파일 경로
       String path = "/svc/common.xml";
       // xml내에서가져올 태그경로 
       String xpath = "/NADL/DB_LIST_XML/ITEM/*";
       
       HashMap<String, String> queries = confParser.ConfParsing(path,xpath);
	   String db_list = null;
	   
	   if(queries.get(dbcode)!=null){
		   db_list = (String) queries.get(dbcode);
	   }
		
		return db_list;
   }
*/
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

   public static String getTableName(String sql) {
	   
	   String tbName = null;
	   sql = sql.toLowerCase();
	   sql = CommonStringUtil.removeWhitespace(sql);
	   
	   try {
		   String sql_type = sql.substring(0,6);
		   
		  if(sql_type.equals("insert")){
			  tbName = CommonStringUtil.splitResult(sql,"into","\\(");
		  } else if (sql_type.equals("delete")){

			  tbName = CommonStringUtil.splitResult(sql,"from","where");
			  
			  if(CommonStringUtil.isEmpty(tbName)){
				  tbName = sql.replace("delete","");
			  }
			  
		  } else if(sql_type.equals("update")){			  
			  tbName = CommonStringUtil.splitResult(sql,"update","set");
		  }
		   
	   } catch (Exception e){}
		
	   
	   return tbName;
   }
   
   
	public static StringReader getXml()
	{

		StringReader ret = null;
		return ret;
	}
   
}

