package springapp.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;

import org.springframework.stereotype.Component;

import springapp.service.UtilsInterface;

@Component
public class Utils implements UtilsInterface{

	public Timestamp getTimestamp(String timestampStr) throws ParseException{

		Timestamp timestamp = Timestamp.valueOf(timestampStr);

		return timestamp;
	}

	public String getDateString(Timestamp timestamp){

		// 21 Sep 2015 21:20:55 GMT
		String gmt = timestamp.toGMTString();

		String[] spaceSplit = gmt.split(" ");

		String dateString = "";
		int i = 0;

		for(String element : spaceSplit){

			if(i == 3)
				break;

			dateString += element + " ";
			i++;
		}

		dateString = dateString.trim();

		return dateString;
	}

	public String getTimeString(Timestamp timestamp){

		String hours = Integer.toString(timestamp.getHours());
		hours = timeFormatting(hours);

		String minutes = Integer.toString(timestamp.getMinutes());
		minutes = timeFormatting(minutes);

		String seconds = Integer.toString(timestamp.getSeconds());
		seconds = timeFormatting(seconds);

		return hours + ":" + minutes + ":" + seconds;
	}

	private String timeFormatting(String element){

		if(element.length() == 1)
			return "0" + element;

		return element;
	}
}