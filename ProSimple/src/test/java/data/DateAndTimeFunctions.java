package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateAndTimeFunctions {
	
	public String getPresentDate()throws Exception{

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String todayAsString = dateFormat.format(today);
		return todayAsString;
	}
	
	public String getFutureDate()throws Exception{
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String tomorrowAsString = dateFormat.format(tomorrow);
		return tomorrowAsString;
	}
	
	public String getPastDate()throws Exception{
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String tomorrowAsString = dateFormat.format(tomorrow);
		return tomorrowAsString;
	}
	
	public static int generateRandomIntIntRange(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}

}
