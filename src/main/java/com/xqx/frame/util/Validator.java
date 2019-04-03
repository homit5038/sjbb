/**
 * 
 */
package com.xqx.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wersion
 *
 * @version
 *
 * @Time 2017年8月9日
 *
 * @Description
 *
 */
public class Validator {
	/**
	 * Judge whether the specified value is Empty
	 * 
	 * @param value
	 *            The value to be Judged.
	 * @return true if the value is null, '' or 'null'
	 */
	public static boolean isNull(String val) {
		return ((val == null) || (val.trim().length() <= 0));
	}

	/**
	 * Judge whether the specified value is Empty
	 * 
	 * @param value
	 *            The value to be Judged.
	 * @return true if the value is null
	 */
	public static boolean isNull(Object val) {
		return (val == null);
	}

	/**
	 * Judge whether the specified value is Empty
	 * 
	 * @param value
	 *            The value to be Judged.
	 * @return true if the value is null or the list havn't any element.
	 */
	public static boolean isNull(List val) {
		return (val == null || val.size() <= 0);
	}

	public static boolean isNumber(String val) {
		try {
			Double.parseDouble(val);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isLong(String val) {
		try {
			if (val == null || "".equals(val)) {
				return false;
			}
			Long.parseLong(val.trim());
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isNotLong(String val) {
		return !isLong(val);
	}

	public static boolean isInteger(String val) {
		try {
			Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 时间处理
	 * 
	 * @param time
	 * @return
	 */
	public static Date toDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间处理第二个版本，主要是导入Excel用
	 * 
	 * @param time
	 * @return
	 */
	public static Date toDate2(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间处理
	 * 
	 * @param time
	 * @return
	 */
	public static String toString(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = null;
		try {
			if (isNull(time)) {
				return "";
			} else {
				date = sdf.format(time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}
