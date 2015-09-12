package springapp.service;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static Timestamp getTimestamp(String timestampStr) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate = dateFormat.parse(timestampStr);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    
	    return timestamp;
	}

}
