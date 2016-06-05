package cz.concrea.conferences.business.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.stereotype.Service;


@Service
public class DateService {

	public boolean isToday(Timestamp date) {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		if (date.after(today.getTime()))
			return true;
		return false;
	}
	
	public Calendar dateStringToDate(String dt){
		int year = Integer.parseInt(dt.substring(0, dt.indexOf("-")).trim());
		dt = dt.substring(dt.indexOf("-")+1, dt.length());
		int month =  Integer.parseInt(dt.substring(0, dt.indexOf("-")).trim());
		dt = dt.substring(dt.indexOf("-")+1, dt.length());
		int day = Integer.parseInt(dt.trim());
		Calendar cal = Calendar.getInstance();
		cal.set( year, month - 1, day); 
		return cal;
	}

}
