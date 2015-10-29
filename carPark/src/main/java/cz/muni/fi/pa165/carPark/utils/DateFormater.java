package cz.muni.fi.pa165.carPark.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * 
 * @author xruzic16
 */

public class DateFormater {
	
	/*
	 * Static method which take Date and return string with format dd-mm-yyyy
	 * 
	 * @return string date dd-mm-yyyy
	 */
	public static String format(Date date){
		SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
		return dt.format(date);
	}
	
	/*
	 * Static method take year month and day
	 * 
	 * @return Date cleared of actual time
	 */
	public static Date newDate(int year, int month, int day){
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month, day);
		return c.getTime();
	}

}
