package qaFramework.support;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import com.google.gson.JsonParseException;

public class DateAndTimeFunctions {

	/**
	 * This function return the date of birth of a 25 yr old
	 */
	public String getPastDate(int intyear) {
		String DATE_FORMAT = "MM d yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		int yearval = 365 * intyear;
		c1.add(Calendar.DATE, -yearval);
		String strDOB = sdf.format(c1.getTime());
		return strDOB;
	}

	/* get past year */
	public String getPastDate(int intyear, String strDateFormate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(strDateFormate);
		Calendar c1 = Calendar.getInstance();
		int yearval = 365 * intyear;
		c1.add(Calendar.DATE, -yearval);
		String strDOB = sdf.format(c1.getTime());
		return strDOB;
	}

	/*
	 * Fetch the current time
	 */
	public String getCurrentTime(String timeFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		return sdf.format(cal.getTime());

	}

	/**
	 * This function return the next date
	 */

	public String getFutureDate(int intDate, String strDateFormat) {
		String DATE_FORMAT = strDateFormat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		int yearval = intDate;
		c1.add(Calendar.DATE, +yearval);
		String strDOB = sdf.format(c1.getTime());
		return strDOB;
	}

	/*
	 * public String getCurrentDate(String Dateformat) {
	 * 
	 * java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	 * DATE_FORMAT); Calendar c1 = Calendar.getInstance(); String strCurrentDate =
	 * sdf.format(c1.getTime());
	 * 
	 * LocalDate localDate = LocalDate.now();
	 * DateTimeFormatter.ofPattern(Dateformat).format(localDate); return Dateformat;
	 * }
	 */

	public String getCurrentDate(String Dateformat) {
		String DATE_FORMAT = Dateformat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		String strCurrentDate = sdf.format(c1.getTime());
		String str = strCurrentDate.replace("am", "AM").replace("pm", "PM");
		return str;
	}

	public String getPastDay(int intDate, String strDateFormat) {
		String DATE_FORMAT = strDateFormat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		int yearval = intDate;
		c1.add(Calendar.DATE, -yearval);
		String strDOB = sdf.format(c1.getTime());
		return strDOB;
	}

	public String getFutureDay(int intDate, String strFormat) {
		String DATE_FORMAT = strFormat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		int yearval = intDate;
		c1.add(Calendar.DATE, +yearval);
		String strDOB = sdf.format(c1.getTime());
		return strDOB;
	}

	/**
	 * This function returns the name of the month
	 */
	public String getMonth(String strMonth) {
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		String month = monthName[Integer.parseInt(strMonth) - 1];

		return month;
	}

	public String FutureDate(int intAdd, String DateFormat) {
		String DATE_FORMAT = DateFormat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.DATE, intAdd);
		String strNextDate = sdf.format(c1.getTime());
		return strNextDate;
	}

	public String FutureMonth(int intAdd, String DateFormat) {
		String DATE_FORMAT = DateFormat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, intAdd);
		String strNextDate = sdf.format(c1.getTime());
		return strNextDate;
	}

	public String compareTwoDates(Calendar c1, Calendar c2) {
		String strRes = "";

		if (c1.before(c2)) {
			strRes = "before";
		}
		if (c1.after(c2)) {
			strRes = "after";
		}
		if (c1.equals(c2)) {
			strRes = "same";
		}

		return strRes;
	}

	public String AddDaytoExistingDate(String strExistDate, int intdays, String strDateFormat) throws ParseException {

		String dt = strExistDate; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));

		c.add(Calendar.DATE, intdays); // number of days to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		System.out.println(dt);

		return dt;
	}

	public String addTimetoExisting(String strExistTime, int intMin, String strTimeFormat) throws ParseException {
		String dt = strExistTime; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat(strTimeFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));
		c.add(Calendar.MINUTE, intMin); // number of minutes to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		System.out.println(dt);

		return dt;
	}

	public String addTimetoExistingHour(String strExistTime, int intHour, String strTimeFormat) throws ParseException {
		String dt = strExistTime; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat(strTimeFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));

		c.add(Calendar.HOUR, intHour); // number of minutes to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		System.out.println(dt);

		return dt;
	}

	public String converDateFormat(String strExistDate, String strInputFormat, String strOutputFormat)
			throws ParseException {

		String dt = strExistDate; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat(strInputFormat);
		SimpleDateFormat sdf1 = new SimpleDateFormat(strOutputFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));

		dt = sdf1.format(c.getTime()); // dt is now the new date
		System.out.println(dt);

		return dt;
	}

	public int getTimeDiff(String Time1, String Time2) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

		// Convert the user input into a date object
		Date time1 = sdf.parse(Time1);
		Date time2 = sdf.parse(Time2);

		// Get time values of the date objects
		long l1 = time1.getTime();
		long l2 = time2.getTime();
		double difference = (l1 - l2) / 1000; // Calculate the difference in
												// time (divide by 1000 as in
												// milliseconds)
		difference = (difference <= 0 ? 1 : difference); // If difference is
															// negative, make
															// positive

		int Timedifference = (int) difference;
		return Timedifference;
	}

	public String getItemNameFromDomainName(String pStrItemDomainName) {
		pStrItemDomainName = pStrItemDomainName.replace("(", ":");
		String[] lArrItemName = pStrItemDomainName.split(":");
		String pStrItemName = lArrItemName[0].trim();
		pStrItemDomainName = pStrItemDomainName.replace(":", "(");

		return pStrItemName;
	}

	public long currentTimeinMilliSec() {
		Date date = new Date();
		long timestamp = date.getTime();
		return timestamp;
	}

	public String getYesterdayDate(String strDateFormate) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormate);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	public String format(Date date) {
		String CURRENT_DATE_FORMAT = "MM-dd-yyyy";
		DateFormat dateFormat = new SimpleDateFormat(CURRENT_DATE_FORMAT);
		return dateFormat.format(date);
	}

	public String formatToday() {
		String CURRENT_DATE_FORMAT = "MM-dd-yyyy";
		DateFormat dateFormat = new SimpleDateFormat(CURRENT_DATE_FORMAT);
		return dateFormat.format(new Date());
	}

	public String formatYesterday() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		// return dateFormat.format(new Date(new Date().getTime() - 24*3600*1000));
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	public String pastDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return dateFormat.format(cal.getTime());

	}
	
	public String timeNow(String dateFormat) { Calendar cal =
	 Calendar.getInstance(); SimpleDateFormat sdf = new
	  SimpleDateFormat(dateFormat);
	 return sdf.format(cal.getTime()); }
	 

	/* Author: Hashmitha, Date:02/04/2019 */
	public static int generateRandomRange(int min, int max) {
		Random r = new Random();
		return r.nextInt(99);
	}

	/* Author: vinay, Date:02/04/2019 */
	public static int getRandom(int max) {
		// return (int) (Math.random()*max); //for 2 degits
		return (int) (Math.random() * max);
	}

	/* Author: Vinay, Date:02/04/2019 */
	public int generateRandomFromTwoInterval(int min, int max) {
		double a = Math.floor(Math.random() * (max - min + 1)) + min;
		int x = (int) a;
		return x;
	}

	/*
	 * Fetch the current time
	 * 
	 */
	public ZonedDateTime timeNow1(String dateFormat) {
		final ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		return now1;
	}

	public String timeist(String format) throws JsonParseException, IOException {
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat(format);
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Current Date and Time in IST time zone: " + gmtDateFormat.format(new Date()));

		return gmtDateFormat.format(new Date());

	}

	public String timeUTC(String format) throws JsonParseException, IOException {
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat(format);
		DateFormat formatterUTC = new SimpleDateFormat(format);
		formatterUTC.setTimeZone(TimeZone.getTimeZone("UTC")); // UTC timezone
		return gmtDateFormat.format(new Date());

	}

	public String timeNowInCaps(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String str = sdf.format(cal.getTime()).replace("am", "AM").replace("pm", "PM");
		return str;
	}
	
	public static int getPreviousYear() {
	    Calendar prevYear = Calendar.getInstance();
	    prevYear.add(Calendar.YEAR, -1);
	    return prevYear.get(Calendar.YEAR);
	    
	    
	}
	public String  generateRandomNumber() throws Exception {
		int x = ((int)(Math.random() * 10000)) % 100;
		 String y = Integer.toString(x); 
		 System.out.println(y);
		return y;
	  	
	}
	
	public String getCurrentDateInIST(String Dateformat) {
		SimpleDateFormat sd = new SimpleDateFormat(Dateformat);
	        Date date = new Date();
	        sd.setTimeZone(TimeZone.getTimeZone("IST"));
	        System.out.println(sd.format(date));
			String str = sd.format(date).replace("am", "AM").replace("pm", "PM");
			return str;
	}
}
