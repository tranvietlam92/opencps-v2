package com.fds.vr.business.action.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

public class ConvertFormatDate {

	public static final String _NORMAL_DATE = "dd/MM/yyyy";
	public static final String PATTERN_DATE = "dd-MM-yyyy HH:mm:ss";

	public static String formatDateSQL(String dateInput) {

		String[] dateElementArr = StringUtil.split(dateInput, "/");
		String dateSQL = StringPool.BLANK;
		if (dateElementArr != null && dateElementArr.length == 3) {
			dateSQL = dateElementArr[2] + StringPool.DASH + dateElementArr[1] + StringPool.DASH + dateElementArr[0];
		}

		return dateSQL;

	}

	public static String convertDateToString(Date date, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	public static Date parseStringToDate(String strDate) {
		
		try {
			SimpleDateFormat df = new SimpleDateFormat(PATTERN_DATE);
			return df.parse(strDate);
		}
		catch (ParseException e) {
//				_log.error(e);
			return null;
		}
		
	}

	public static Date parseStringToDate(String strDate, String pattern) {
		
		try {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.parse(strDate);
		}
		catch (ParseException e) {
//				_log.error(e);
			return null;
		}
		
	}

}
