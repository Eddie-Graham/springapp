package springapp.service;


import java.sql.Timestamp;
import java.text.ParseException;

public class Utils {
	
	public static Timestamp getTimestamp(String timestampStr) throws ParseException{
		
        Timestamp timestamp = Timestamp.valueOf(timestampStr);
	    
	    return timestamp;
	}

}
