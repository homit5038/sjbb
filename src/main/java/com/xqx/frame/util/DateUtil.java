package com.xqx.frame.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class DateUtil {
	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 取得指定日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param
	 * @return
	 */
	public static Date getFirstDayOfWeek() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param
	 * @return
	 */
	public static Date getLastDayOfWeek() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	 
	 /**
	  * 根据年月得到 改年该月最后一天的日期 byLchaosheng
	  * 根据年月直接拼接1号 修改byXciyue 20160823
	  * @param year
	  * @param month
	  * @return
	  */
	 public static Date getDaysOfMonth(String year,String month){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String date1 = year+ "-" + month + "-" + String.valueOf(days);
		String date1 = year+ "-" + month + "-01";
		Date date = null;
		try {
			date = sdf.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateYear(Date date) {
		int result = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		result = c.get(Calendar.YEAR);
		return result;

	}

	/**
	 * 获取日期月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateMonth(Date date) {
		int result = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		result = c.get(Calendar.MONTH)+1;
		if (result < 10) {
			return "0" + result;
		}
		return String.valueOf(result);

	}
	
	/**
	 * 计算2个日期的相差多少年
	 * @param b
	 * @param e
	 * @return
	 */
	public static long getYearDifference(Date b,Date e){
		if(b != null && e != null){
			Calendar date = Calendar.getInstance();
			Calendar edate = Calendar.getInstance();
			date.setTime(b); 
			edate.setTime(getNextDay(e)); 
			Long y = date.getTimeInMillis()-edate.getTimeInMillis();
			long diff=(y/(3600*24*1000))/365;
			return diff;
		}else{
			return 0l;
		}
	}

	public static Map<String,Date> getLastDate(Date d,Date d1) throws ParseException{
		Map<String,Date> map = new HashMap<String,Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		Calendar date1 = Calendar.getInstance();
		date.setTime(d);
		date1.setTime(d1);
		long diffDays = (date1.getTimeInMillis() - date.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		if(diffDays == 0l){
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			map.put("lastDay", sdf.parse(sdf.format(date.getTime())));
			map.put("lastDay1", sdf.parse(sdf.format(date.getTime())));
		}else{
			date.add(Calendar.MONTH, -1);
			date1.add(Calendar.MONTH, -1);
			map.put("lastDay", sdf.parse(sdf.format(date.getTime())));
			map.put("lastDay1", sdf.parse(sdf.format(date1.getTime())));
		}
		return map;
	} 
	

	/**
	 * 获取指定时间的前一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
	public static String getStringOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		String y = Integer.toString(year).substring(2);
		return y;
	}

	/**
	 * 当前时间和指定时间比较
	 * 当前时间 => 指定时间 false
	 * 当前时间 < 指定时间 true
	 * @param dt 指定时间
	 * @return
	 */
	public static boolean contrastDate(Date dt){
		Date currentTime = new Date();// 当前时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowtime = formatter.format(currentTime);

		Calendar now = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		try {
			now.setTime(formatter.parse(nowtime));
			c1.setTime(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now.compareTo(c1) < 0;// 比开始时间小，未开始
	}
	
	//==========================================================
	/**
	 * 获取当前日期加a天
	 */
	public static Date afterDays(Long days) {
		ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now().plusDays(days);
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        return Date.from(zdt.toInstant());
	}

    /**
     * 当前日期加指定月数
     * @param months  月
     * @return
     */
	public static Date afterMonths(Long months) {
		ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now().plusMonths(months);
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

        return Date.from(zdt.toInstant());
	}
	
	/**
     * 指定日期加指定年数
     * @param years 年
     * @return
     */
	public static Date afterYears(Date d,int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
	}
	

	/**  
	 * 字符串日期转换成中文格式日期  
	 * @param date  字符串日期 yyyy-MM-dd  
	 * @return  yyyy年MM月dd日  
	 * @throws Exception  
	 */  
	public static String dateToCnDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
	    String result = "";   
	    String[]  cnDate = new String[]{"0","一","二","三","四","五","六","七","八","九"};   
	    String ten = "十";   
	    String[] dateStr = date.split("-");   
	    for (int i=0; i<dateStr.length; i++) {   
	        for (int j=0; j<dateStr[i].length(); j++) {   
	            String charStr = dateStr[i];   
	            String str = String.valueOf(charStr.charAt(j));   
	            if (charStr.length() == 2) {   
	                if (charStr.equals("10")) {   
	                    result += ten;   
	                    break;   
	                } else {   
	                    if (j == 0) {   
	                        if (charStr.charAt(j) == '1')    
	                            result += ten;   
	                        else if (charStr.charAt(j) == '0')   
	                            result += "";   
	                        else  
	                            result += cnDate[Integer.parseInt(str)] + ten;   
	                    }   
	                    if (j == 1) {   
	                        if (charStr.charAt(j) == '0')   
	                            result += "";   
	                         else  
	                            result += cnDate[Integer.parseInt(str)];   
	                    }   
	                }   
	            } else {   
	                result += cnDate[Integer.parseInt(str)];   
	            }   
	        }   
	        if (i == 0) {   
	            result += "年";   
	            continue;   
	        }   
	        if (i == 1) {   
	            result += "月";   
	            continue;   
	        }   
	        if (i == 2) {   
	            result += "日";   
	            continue;   
	        }   
	    }   
	    return result;   
	} 
	
	/**  
	 * 字符串日期转换成中文格式日期  
	 * @param date  字符串日期 yyyy-MM-dd  
	 * @return  yyyy年MM月dd日  
	 * @throws Exception  
	 */  
	public static List<String> dateToCnStringList(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		List<String> temp = new ArrayList<String>();
	    String result = "";   
	    String[]  cnDate = new String[]{"0","一","二","三","四","五","六","七","八","九"};   
	    String ten = "十";   
	    String[] dateStr = date.split("-");   
	    for (int i=0; i<dateStr.length; i++) {   
	        for (int j=0; j<dateStr[i].length(); j++) {   
	            String charStr = dateStr[i];   
	            String str = String.valueOf(charStr.charAt(j));   
	            if (charStr.length() == 2) {   
	                if (charStr.equals("10")) {   
	                    result += ten;   
	                    break;   
	                } else {   
	                    if (j == 0) {   
	                        if (charStr.charAt(j) == '1')    
	                            result += ten;   
	                        else if (charStr.charAt(j) == '0')   
	                            result += "";   
	                        else  
	                            result += cnDate[Integer.parseInt(str)] + ten;   
	                    }   
	                    if (j == 1) {   
	                        if (charStr.charAt(j) == '0')   
	                            result += "";   
	                         else  
	                            result += cnDate[Integer.parseInt(str)];   
	                    }   
	                }   
	            } else {   
	                result += cnDate[Integer.parseInt(str)];   
	            }   
	        }   
	        temp.add(result);
	        result = "";
            continue; 
	    }   
	    return temp;   
	} 

	
	
	
    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    //获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }
    //获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }
    //获取本周的开始时间
    @SuppressWarnings("unused")
   public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }
    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    //获取上周的开始时间
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }
    //获取上周的结束时间
    public static Date getEndDayOfLastWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    //获取本月的开始时间
     public static Date getBeginDayOfMonth() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(getNowYear(), getNowMonth() - 1, 1);
            return getDayStartTime(calendar.getTime());
        }
    //获取本月的结束时间
     public static Date getEndDayOfMonth() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(getNowYear(), getNowMonth() - 1, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), getNowMonth() - 1, day);
            return getDayEndTime(calendar.getTime());
        }
     //获取上月的开始时间
     public static Date getBeginDayOfLastMonth() {
         Calendar calendar = Calendar.getInstance();
         calendar.set(getNowYear(), getNowMonth() - 2, 1);
         return getDayStartTime(calendar.getTime());
     }
     //获取上月的结束时间
     public static Date getEndDayOfLastMonth() {
         Calendar calendar = Calendar.getInstance();
         calendar.set(getNowYear(), getNowMonth() - 2, 1);
         int day = calendar.getActualMaximum(5);
         calendar.set(getNowYear(), getNowMonth() - 2, day);
         return getDayEndTime(calendar.getTime());
     }
     //获取本年的开始时间
     public static java.util.Date getBeginDayOfYear() {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, getNowYear());
            // cal.set
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DATE, 1);

            return getDayStartTime(cal.getTime());
        }
     //获取本年的结束时间
     public static java.util.Date getEndDayOfYear() {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, getNowYear());
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DATE, 31);
            return getDayEndTime(cal.getTime());
        }
    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }
    //获取今年是哪一年
     public static Integer getNowYear() {
             Date date = new Date();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            return Integer.valueOf(gc.get(1));
        }
     //获取本月是哪一月
     public static int getNowMonth() {
             Date date = new Date();
            GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
            gc.setTime(date);
            return gc.get(2) + 1;
        }
     //两个日期相减得到的天数
     public static int getDiffDays(Date beginDate, Date endDate) {

            if (beginDate == null || endDate == null) {
                throw new IllegalArgumentException("getDiffDays param is null!");
            }

            long diff = (endDate.getTime() - beginDate.getTime())
                    / (1000 * 60 * 60 * 24);

            int days = new Long(diff).intValue();

            return days;
        }
    //两个日期相减得到的毫秒数
     public static long dateDiff(Date beginDate, Date endDate) {
            long date1ms = beginDate.getTime();
            long date2ms = endDate.getTime();
            return date2ms - date1ms;
        }
     //获取两个日期中的最大日期
     public static Date max(Date beginDate, Date endDate) {
            if (beginDate == null) {
                return endDate;
            }
            if (endDate == null) {
                return beginDate;
            }
            if (beginDate.after(endDate)) {
                return beginDate;
            }
            return endDate;
        }
     //获取两个日期中的最小日期
     public static Date min(Date beginDate, Date endDate) {
            if (beginDate == null) {
                return endDate;
            }
            if (endDate == null) {
                return beginDate;
            }
            if (beginDate.after(endDate)) {
                return endDate;
            }
            return beginDate;
        }
     //返回某月该季度的第一个月
     public static Date getFirstSeasonDate(Date date) {
             final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int sean = SEASON[cal.get(Calendar.MONTH)];
            cal.set(Calendar.MONTH, sean * 3 - 3);
            return cal.getTime();
        }
     //返回某个日期下几天的日期
     public static Date getNextDay(Date date, int i) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
            return cal.getTime();
        }
     //返回某个日期前几天的日期
     public static Date getFrontDay(Date date, int i) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
            return cal.getTime();
        }
   
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws ParseException {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String a = sdf.format(new Date());
		//System.out.println(a);
		System.out.println(dateToCnStringList(new Date()));
	}
}
