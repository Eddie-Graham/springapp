package springapp.service;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static Timestamp getTimestamp(String timestampStr) throws ParseException{
		
        Timestamp timestamp = Timestamp.valueOf(timestampStr);
	    
	    return timestamp;
	}

}
