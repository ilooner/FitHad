package com.fithad.android.data;

import java.util.Comparator;

public class EventComparatorDesc implements Comparator<Event>
{
	private static final EventComparatorDesc instance = new EventComparatorDesc();
	
	@Override
	public int compare(Event e1, 
					   Event e2)
	{
		if(e1.getDate().after(e2.getDate()))
		{
			return -1;
		}
		else if(e1.getDate().before(e2.getDate()))
		{
			return 1;
		}
		
		return 0;
	}
	
	public static final EventComparatorDesc getInstance()
	{
		return instance;
	}

}
