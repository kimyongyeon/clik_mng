package clikmng.nanet.go.kr.cmm.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalUtil {
 	GregorianCalendar cal = new GregorianCalendar();
	SimpleDateFormat sdf = null; 
	DecimalFormat df = null; 

	String delimiter = "";

	public void setDelimiter(String delimit) throws Exception {
		delimiter = delimit;
	}

	public void setDecimalFormat(String format) throws Exception {
		df = new DecimalFormat(format);
	}
	
	public void setSimpleDateFormat(String format) throws Exception {
		sdf = new SimpleDateFormat(format);
	}

	public Date getSimpleDateFormat(String str) throws Exception {
		Date dt = null;
		try {
			dt = (Date)sdf.parse(str);
		}
		catch(Exception e) {
		}
		return dt;
	}

	public int getUnixTime() throws Exception {
		return (int)(cal.getTimeInMillis()/1000L);
	} 

	public int getUnixTime(int year, int mon, int date) throws Exception {
		cal.set(year, mon-1, date, 0, 0, 0);
		return (int)(cal.getTimeInMillis()/1000L);
	} 

	public String getDay(int y, int m, int d) throws Exception {
		String[] dayOfWeek={"일","월","화","수","목","금","토"};
	    cal.set(y,m,d);
	    return dayOfWeek[cal.get(Calendar.DAY_OF_WEEK)-1];
	}

	public String getYear() throws Exception {
		StringBuffer strdate = new StringBuffer();
		strdate.append(cal.get(Calendar.YEAR));
		return strdate.toString();
	}
	
	public String getMonth() throws Exception {
		StringBuffer strdate = new StringBuffer();
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		return strdate.toString();
	}

	public String getToday() throws Exception {
		StringBuffer strdate = new StringBuffer();
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.DATE)));
		return strdate.toString();
	} 
	
	public String getBeforeWeek() throws Exception {
		StringBuffer strdate = new StringBuffer();
		Calendar currentCalendar = Calendar.getInstance();
		
		currentCalendar.add(currentCalendar.DATE, -7);
		
		strdate.append(currentCalendar.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(currentCalendar.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(currentCalendar.get(Calendar.DATE)));
		return strdate.toString();
	} 	

	public String getTodayWithTime() throws Exception {
		StringBuffer strdate = new StringBuffer();
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.DATE)));
		strdate.append(" ");
		strdate.append(df.format(cal.get(Calendar.HOUR_OF_DAY)));
		strdate.append(":");
		strdate.append(df.format(cal.get(Calendar.MINUTE)));
		strdate.append(":");
		strdate.append(df.format(cal.get(Calendar.SECOND)));
		return strdate.toString();
	} 

	public String getTodayWithTime2() throws Exception {
		StringBuffer strdate = new StringBuffer();
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(df.format(cal.get(Calendar.DATE)));
		strdate.append(df.format(cal.get(Calendar.HOUR_OF_DAY)));
		strdate.append(df.format(cal.get(Calendar.MINUTE)));
		strdate.append(df.format(cal.get(Calendar.SECOND)));
		return strdate.toString();
	} 

	public String getDate(int unixtime) throws Exception {
		StringBuffer strdate = new StringBuffer();
		cal.setTimeInMillis(unixtime * 1000L);
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.DATE)));
		return strdate.toString();
	} 

	public String getDateBeforeDt(int unixtime, int dt) throws Exception {
		StringBuffer strdate = new StringBuffer();
		cal.setTimeInMillis(unixtime * 1000L);
		cal.add(Calendar.DATE, dt);
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.DATE)));
		return strdate.toString();
	} 

	public String getDateFromToday(int dt) throws Exception {
		StringBuffer strdate = new StringBuffer();
		cal.add(Calendar.DATE, dt);
		strdate.append(cal.get(Calendar.YEAR));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.MONTH) + 1));
		strdate.append(delimiter);
		strdate.append(df.format(cal.get(Calendar.DATE)));
		return strdate.toString();
	} 

	public boolean getLeapYear(int year) throws Exception {
		if( cal.isLeapYear(year) ) {
			return true;
		}
		else {
			return false;
		}
	}

}