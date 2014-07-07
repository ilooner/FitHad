package com.fithad.android;

import java.sql.Date;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.fithad.android.data.EventFactory;
import com.fithad.android.data.ExerciseArrayAdapter;
import com.fithad.android.data.ExerciseEvent;
import com.fithad.android.data.ExerciseEventArrayAdapter;
import com.fithad.android.data.ExerciseEventFactory;
import com.fithad.android.data.ExerciseFactory;

public class ExerciseDataFragment extends Fragment
{
	private Date date;

	private ExerciseEventArrayAdapter exerciseEventArrayAdapter;
	private LinearLayout linearLayout;
	
	public ExerciseDataFragment(Date date)
	{
		setDate(date);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState)
	{
		linearLayout = new LinearLayout(this.getActivity());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		rebuild();
			
		return linearLayout;
	}
	
	public void rebuild()
	{
		linearLayout.removeAllViews();
		
		final Event event = EventFactory.getEvent(date);
	
		Button addExerciseButton = new Button(this.getActivity());
		addExerciseButton.setText("Add Exercise");
		addExerciseButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				addExercise(event);
			}
		});
		linearLayout.addView(addExerciseButton);
		
		////
		
		ListView listView = new ListView(this.getActivity());
		
		exerciseEventArrayAdapter = new ExerciseEventArrayAdapter(this.getActivity(),
												  	    ExerciseEventFactory.getExerciseEventsAsc(event));
		
		listView.setAdapter(exerciseEventArrayAdapter);
		linearLayout.addView(listView);
	
		////
		
		linearLayout.invalidate();
	}
	
	public void setDate(Date date)
	{
		if(date == null)
		{
			throw new NullPointerException("The given date cannot be null.");
		}
		
		this.date = date;
	}
	
	private void addExercise(final Event event)
	{
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("Add Exercise");
	    

	    LinearLayout linearLayout = new LinearLayout(getActivity());
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
	    
	    builder.setView(linearLayout);
	    
	    	////
	    
	    	final ListView listView = new ListView(this.getActivity());
	    	final ExerciseArrayAdapter exerciseArrayAdapter = new ExerciseArrayAdapter(getActivity(), ExerciseFactory.getAllExercisesAsc());
	    	listView.setAdapter(exerciseArrayAdapter);
	    	
			listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) 
				{
					exerciseArrayAdapter.setSelectedIndex(position);
					exerciseArrayAdapter.notifyDataSetChanged();
				}
				
			});
	    	
	    	linearLayout.addView(listView);
	    	
	    	////
	    	
	    builder.setPositiveButton("Add", new DialogInterface.OnClickListener(){

	    	@Override
	    	public void onClick(DialogInterface dialog, int which) 
	    	{
	    		int selectedIndex = exerciseArrayAdapter.getSelectedIndex();
	    		if(selectedIndex == -1)
	    		{
	    			return;
	    		}
	    		
	    		ExerciseEvent exerciseEvent = new ExerciseEvent(exerciseArrayAdapter.getItem(selectedIndex), event);
	    		exerciseEvent = ExerciseEventFactory.addExerciseEvent(exerciseEvent);
	    		
	    		exerciseEventArrayAdapter.clear();
	    		
	    		List<ExerciseEvent> exerciseEvents = ExerciseEventFactory.getExerciseEventsAsc(event);
	    		
	    		for(ExerciseEvent tempExerciseEvent: exerciseEvents)
	    		{
	    			exerciseEventArrayAdapter.add(tempExerciseEvent);
	    		}
	    		
	    		exerciseEventArrayAdapter.notifyDataSetChanged();
	    	}
		   
	    });
	   
	    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
	    {
	    	@Override
	    	public void onClick(DialogInterface dialog, int which) 
	    	{
	    	}
	    });
	    
	    builder.create().show();
	}
}
