package springapp.service;

import java.sql.Timestamp;
import java.text.ParseException;

public interface UtilsInterface {

	public Timestamp getTimestamp(String timestampStr) throws ParseException;
	public String getDateString(Timestamp timestamp);
	public String getTimeString(Timestamp timestamp);
}
