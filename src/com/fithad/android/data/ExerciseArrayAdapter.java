package com.fithad.android.data;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fithad.android.R;

public class ExerciseArrayAdapter extends ArrayAdapter<Exercise>
{
	private int selectedIndex = -1;
	
	public ExerciseArrayAdapter(Context context, 
								List<Exercise> objects)
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
		Exercise exercise = this.getItem(position);
		
		RelativeLayout layout = new RelativeLayout(parent.getContext());
		
			TextView textView = new TextView(parent.getContext());
			
			if(position == selectedIndex)
			{
				textView.setBackgroundResource(android.R.color.darker_gray);
			}
			
			textView.setText(exercise.exerciseName);
			layout.addView(textView);
		
		return layout;
	}
}
