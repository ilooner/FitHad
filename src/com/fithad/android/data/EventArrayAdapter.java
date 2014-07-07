package com.fithad.android.data;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fithad.android.R;

public class EventArrayAdapter extends ArrayAdapter<Event>
{
	private int selectedIndex = -1;
	
	public EventArrayAdapter(Context context, List<Event> objects)
	{
		super(context, R.layout.rowlayout, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Event event = this.getItem(position);
		
		RelativeLayout layout = new RelativeLayout(parent.getContext());
		
			TextView textView = new TextView(parent.getContext());
			
			if(position == selectedIndex)
			{
				textView.setBackgroundResource(android.R.color.darker_gray);
			}
			
			textView.setText(event.getDateString());
			layout.addView(textView);
		
		return layout;
	}
	
	public void setSelectedIndex(int selectedIndex)
	{
		if(selectedIndex < 0 || selectedIndex >= this.getCount())
		{
			throw new IllegalArgumentException("The selected index is not valid");
		}
		
		this.selectedIndex = selectedIndex;
	}
	
	public int getSelectedIndex()
	{
		return selectedIndex;
	}
}
