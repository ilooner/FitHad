package com.fithad.android.data;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="event")
public class Event extends Model 
{
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

	@Column(name="date")
	public Date date;

	private String dateString;
	
	public Event()
	{	
	}
	
	public Event(Date date)
	{
		setDate(date);
	}
	
	public void setDate(Date date)
	{
		if(date == null)
		{
			throw new NullPointerException("The given date cannot be null.");
		}
		
		this.date = date;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String getDateString()
	{
		if(dateString == null)
		{
			dateString = dateFormatter.format(date);
		}
		
		return dateString;
	}
}
