package com.xingrongjinfu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String formatCurrentDate(String format) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 比较两个日期
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static boolean compare_date(String DATE1, String DATE2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() == dt2.getTime()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println(lastDayOfYear(new Date()).toLocaleString());
		// List<Date> dates = getAllDate(new Date());
		// for (Date d : dates) {
		// System.out.println(d.toLocaleString());
		// }
	}

	/**
	 * 一个月的第一天
	 * 
	 * @param time
	 * @return
	 */
	public static Date firstDayOfMonth(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 一个月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static Date lastDayOfMonth(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	/**
	 * 每天的00:00:00
	 * 
	 * @param time
	 * @return
	 */
	public static Date firstMomentOfDay(Date time) {
		Calendar begCal = Calendar.getInstance();
		begCal.setTime(time);
		begCal.set(Calendar.HOUR_OF_DAY, 0);
		begCal.set(Calendar.MINUTE, 0);
		begCal.set(Calendar.SECOND, 0);
		begCal.set(Calendar.MILLISECOND, 0);
		return begCal.getTime();
	}

	/**
	 * 每天的23:59:59
	 * 
	 * @param time
	 * @return
	 */
	public static Date lastMomentOfDay(Date time) {
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(time);
		endCal.set(Calendar.HOUR_OF_DAY, 23);
		endCal.set(Calendar.MINUTE, 59);
		endCal.set(Calendar.SECOND, 59);
		endCal.set(Calendar.MILLISECOND, 0);
		return endCal.getTime();
	}

	/**
	 * 把结束区间加上一秒钟
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return
	 */
	public static int subDate(Date dateBegin, Date dateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long beginMs = sdf.parse(sdf.format(dateBegin)).getTime();
			long endMs = sdf.parse(sdf.format(dateEnd)).getTime();
			return (int) ((endMs - beginMs) / (1000 * 3600 * 24));
		} catch (ParseException e) {
		}
		return 0;
	}

	public static List<Date> getDaysByTwoDate(Date dateBegin, Date dateEnd) {
		/**
		 * 设置开始时间
		 */
		Calendar start = Calendar.getInstance();
		start.setTime(dateBegin);
		/**
		 * 设置结束时间
		 */
		Calendar end = Calendar.getInstance();
		end.setTime(dateEnd);
		List<Date> dates = new ArrayList<Date>();
		while (start.before(end)) {
			dates.add(start.getTime());
			start.add(Calendar.DATE, 1);
		}
		return dates;
	}

	public static Date firstMomentOfMonth(Date d) {
		Calendar start = Calendar.getInstance();
		start.setTime(d);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		return start.getTime();
	}

	public static Date lastMomentOfMonth(Date d) {
		Calendar end = Calendar.getInstance();
		end.setTime(d);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		return end.getTime();
	}

	/**
	 * 一年的第一天
	 * 
	 * @param d
	 * @return
	 */
	public static Date firstDayOfYear(Date d) {
		Calendar start = Calendar.getInstance();
		start.setTime(d);
		start.set(Calendar.DAY_OF_YEAR, 1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		return start.getTime();
	}

	/**
	 * 一年的最后一天
	 * 
	 * @param d
	 * @return
	 */
	public static Date lastDayOfYear(Date d) {
		Calendar end = Calendar.getInstance();
		end.setTime(d);
		end.set(Calendar.DAY_OF_YEAR, 1);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.add(Calendar.YEAR, 1);
		end.add(Calendar.DAY_OF_YEAR, -1);
		return end.getTime();
	}

	/**
	 * 获取昨天的开始时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getStarTimeYesterday(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstMomentOfDay(d));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取昨天的结束时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getEndTimeYesterday(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastMomentOfDay(d));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取本周的开始时间
	 * 天-1  星期一 2 星期二 
	 * 
	 * 
	 * @param d
	 * @return
	 */
	public static Date getFirstDayOfCurrentWeek(Date d) {
		Calendar cal = Calendar.getInstance();
		int day = 0;
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			day = 0;
			break;
		case Calendar.TUESDAY:
			day = -1;
			break;
		case Calendar.WEDNESDAY:
			day = -2;
			break;
		case Calendar.THURSDAY:
			day = -3;
			break;
		case Calendar.FRIDAY:
			day = -4;
			break;
		case Calendar.SATURDAY:
			day = -5;
			break;
		case Calendar.SUNDAY:
			day = -6;
			break;
		}
		
		
		cal.setTime(firstMomentOfDay(d));
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 获取本周的结束时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastDayOfCurrentWeek(Date d) {
		/*Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		cal.setTime(lastMomentOfDay(d));
		cal.add(Calendar.DATE, 7 - dayOfWeek);
		return cal.getTime();*/
		Calendar cal = Calendar.getInstance();
		int day = 0;
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			day = 6;
			break;
		case Calendar.TUESDAY:
			day = 5;
			break;
		case Calendar.WEDNESDAY:
			day = 4;
			break;
		case Calendar.THURSDAY:
			day = 3;
			break;
		case Calendar.FRIDAY:
			day = 2;
			break;
		case Calendar.SATURDAY:
			day = 1;
			break;
		case Calendar.SUNDAY:
			day = 0;
			break;
		}
		
		
		cal.setTime(lastMomentOfDay(d));
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 获取上周的开始时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getFirstDayOfLastWeek(Date d) {
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		cal.setTime(firstMomentOfDay(d));
		cal.add(Calendar.DATE, (1 - dayOfWeek) - 7);
		return cal.getTime();
	}

	/**
	 * 获取上周的结束时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastDayOfLastWeek(Date d) {
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		cal.setTime(lastMomentOfDay(d));
		cal.add(Calendar.DATE, (7 - dayOfWeek) - 7);
		return cal.getTime();
	}

	/**
	 * 获得本月第一天
	 * 
	 * @param d
	 * @return
	 */
	public static Date getTimesMonthmorning(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstMomentOfDay(d));
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 获得本月最后一天
	 * 
	 * @param d
	 * @return
	 */
	public static Date getTimesMonthnight(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastMomentOfDay(d));
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 获得上个月第一天
	 * 
	 * @param d
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstMomentOfDay(d));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 获得上个月最后一天
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastMomentOfDay(d));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
}
