package com.fithad.android.data;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fithad.android.R;

public class ExerciseEventArrayAdapter extends ArrayAdapter<ExerciseEvent>
{
	private ExerciseSetArrayAdapter exerciseSetArrayAdapter;
	private int selectedIndex = -1;
	private LinearLayout linearLayout;
	
	public ExerciseEventArrayAdapter(Context context, List<ExerciseEvent> objects)
	{
		super(context, R.layout.rowlayout, objects);
	}
	
	public void setSelectedIndex(int selectedIndex)
	{
		this.selectedIndex = selectedIndex;
	}
	
	public int getSelectedIndex()
	{
		return selectedIndex;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ExerciseEvent exerciseEvent = this.getItem(position);
		
		linearLayout = new LinearLayout(parent.getContext());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
			TextView textView = new TextView(parent.getContext());
			
			if(position == selectedIndex)
			{
				textView.setBackgroundResource(android.R.color.darker_gray);
			}
			
			textView.setText(exerciseEvent.exercise.exerciseName);
			linearLayout.addView(textView);
			
			////
			
			Button addExerciseSetButton = new Button(parent.getContext());
			addExerciseSetButton.setText("Add Exercise Set");
			addExerciseSetButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) 
				{
					addExerciseSet(exerciseEvent);
				}
				
			});
			linearLayout.addView(addExerciseSetButton);
			
			////
			
			ListView listView = new ListView(parent.getContext());
			
			exerciseSetArrayAdapter = new ExerciseSetArrayAdapter(parent.getContext(),
													  	    	  ExerciseSetFactory.getAllExerciseSetsAsc(exerciseEvent));
			
			for(int counter = 0; counter < exerciseSetArrayAdapter.getCount(); counter++)
			{
				linearLayout.addView(exerciseSetArrayAdapter.getView(counter, null, linearLayout));
			}
		
		return linearLayout;
	}
	
	private void addExerciseSet(ExerciseEvent exerciseEvent)
	{
		Integer maxOrdering = ExerciseSetFactory.maxOrdering(exerciseEvent);
		
		if(maxOrdering == null)
		{
			maxOrdering = 1;
		}
		else
		{
			maxOrdering++;
		}
		
		ExerciseSet exerciseSet = new ExerciseSet(exerciseEvent,
												  0,
												  0,
												  maxOrdering);
		
		exerciseSet.save();// = ExerciseSetFactory.addExerciseSet(exerciseSet);
		
		exerciseSetArrayAdapter.add(exerciseSet);
		
		linearLayout.addView(exerciseSetArrayAdapter.getView(exerciseSetArrayAdapter.getCount() - 1, null, linearLayout));
	}
}
