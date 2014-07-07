package com.fithad.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
	public static final long MILLI_SECONDS_PER_DAY = 24 * 60 * 60 * 1000;
	
	public static final Date getCurrentDay()
	{
		Calendar c = Calendar.getInstance();
		Date currentDate = c.getTime();
		
		Date newDate = new Date(((currentDate.getTime() / MILLI_SECONDS_PER_DAY) + 2) * MILLI_SECONDS_PER_DAY);
		return newDate;
	}
	
	public static final java.sql.Date getCurrentDaySQL()
	{
		return new java.sql.Date(getCurrentDay().getTime());
	}
}
