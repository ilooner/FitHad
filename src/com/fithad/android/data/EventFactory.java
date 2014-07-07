package com.fithad.android.data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activeandroid.query.Select;

public class EventFactory
{
	private EventFactory()
	{
	}
	
	public static final Event getEvent(Date date)
	{
		Event event = new Select().from(Event.class).where("date = ?", date.getTime()).executeSingle();
		return event;
	}
	
	public static final Event addEvent(Event event)
	{
		Event checkEvent = new Select().from(Event.class).where("date = ?", event.date).executeSingle();
		
		if(checkEvent == null)
		{
			event.save();
			checkEvent = event;
		}
		
		return checkEvent;
	}
	
	public static final List<Event> getAllEvents()
	{
		return new Select().from(Event.class).execute();
	}
	
	private static final List<Date> getAllDates(List<Event> events)
	{
		List<Date> dates = new ArrayList<Date>();
		
		for(Event event: events)
		{
			dates.add(event.getDate());
		}
		
		return dates;
	}
	
	public static final List<Event> getAllEventsDesc()
	{
		List<Event> events = getAllEvents();
		Collections.sort(events, EventComparatorDesc.getInstance());
		
		return events;
	}
	
	public static final List<Date> getAllDatesDesc()
	{
		return getAllDates(getAllEventsDesc());
	}
	
	public static final boolean areEvents()
	{
		return getAllEvents().size() > 0;
	}
	
}
