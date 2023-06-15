package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getStringDate(Date date) {
		String strDateFormat = "yyyyMMdd";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		return dateFormat.format(date);
	}
}