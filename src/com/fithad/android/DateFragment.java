package com.fithad.android;

import java.sql.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fithad.android.data.Event;
import com.fithad.android.data.EventArrayAdapter;
import com.fithad.android.data.EventFactory;
import com.fithad.utils.DateUtils;

public class DateFragment extends Fragment
{
	private static final String ARG_SECTION_NUMBER = "section_number";

	private MainActivity mainActivity;
	private ListView listView;
	private EventArrayAdapter eventArrayAdapter;
	
	public DateFragment(MainActivity mainActivity)
	{
		setMainActivity(mainActivity);
	}
	
	private void setMainActivity(MainActivity mainActivity)
	{
		if(mainActivity == null)
		{
			throw new NullPointerException("The main activity cannot be null.");
		}
		
		this.mainActivity = mainActivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState)
	{
		LinearLayout linearLayout = new LinearLayout(this.getActivity());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
			Button addWorkoutButton = new Button(this.getActivity());
			addWorkoutButton.setText("Add Workout");
			addWorkoutButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) 
				{
					addWorkOut(v);
				}
				
			});
			
			linearLayout.addView(addWorkoutButton);
			
			////
			
			listView = new ListView(this.getActivity());
			listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			listView.setSelector(android.R.color.darker_gray);
		
			eventArrayAdapter = new EventArrayAdapter(this.getActivity(),
													  EventFactory.getAllEventsDesc());
			
			listView.setAdapter(eventArrayAdapter);
			listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) 
				{
					listItemSelected(arg0,
							 arg1,
							 arg2,
							 arg3);
					
				}
				
			});
			linearLayout.addView(listView);
			
		return linearLayout;
	}

	public void listItemSelected(AdapterView<?> arg0, 
							   View arg1, int position,
							   long arg3) 
	{
		eventArrayAdapter.setSelectedIndex(position);
		eventArrayAdapter.notifyDataSetChanged();
		
		if(!mainActivity.hasExerciseDataTab())
		{
			mainActivity.addExerciseDataTab();
		}
		else
		{
			mainActivity.updatePager();
		}
		/*//eventArrayAdapter.
		
		if(!mainActivity.hasExerciseDataTab())
		{
			mainActivity.addExerciseDataTab();
		}*/
	}

	public void listNothingSelected(AdapterView<?> arg0)
	{
		/*mainActivity.removeExerciseDataTab();*/
	}

	public void addWorkOut(View arg0) 
	{
		Event event = EventFactory.getEvent(DateUtils.getCurrentDaySQL());
		
		if(event != null)
		{
			return;
		}
		
		event = new Event(DateUtils.getCurrentDaySQL());
		event.save();
		eventArrayAdapter.insert(event, 0);
		eventArrayAdapter.notifyDataSetChanged();
		
		if(eventArrayAdapter.getSelectedIndex() >= 0)
		{
			eventArrayAdapter.setSelectedIndex(eventArrayAdapter.getSelectedIndex() + 1);
		}
	}
	
	public Event getSelectedEvent()
	{
		int selectedIndex = eventArrayAdapter.getSelectedIndex();
		
		if(selectedIndex < 0)
		{
			return null;
		}
		
		Event event = eventArrayAdapter.getItem(selectedIndex);
		return event;
	}
}
