package com.fithad.android;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fithad.android.data.Exercise;
import com.fithad.android.data.ExerciseArrayAdapter;
import com.fithad.android.data.ExerciseFactory;

public class ExerciseFragment extends Fragment 
{
	private static final String ARG_SECTION_NUMBER = "section_number";

	private MainActivity mainActivity;
	private ExerciseArrayAdapter exerciseArrayAdapter;
	
	public ExerciseFragment(MainActivity mainActivity)
	{
		setMainActivity(mainActivity);
	}
	
	private void setMainActivity(MainActivity mainActivity)
	{
		if(mainActivity == null)
		{
			throw new NullPointerException("The given main activity cannot be null.");
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
		
			////
		
			Button addExerciseButton = new Button(this.getActivity());
			addExerciseButton.setText("Add Exercise");
			addExerciseButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) 
				{
					addExerciseDialog();
				}
				
			});
			
			linearLayout.addView(addExerciseButton);
		
			////
			
			ListView listView = new ListView(this.getActivity());
		
			exerciseArrayAdapter = new ExerciseArrayAdapter(this.getActivity(),
													  	    ExerciseFactory.getAllExercisesAsc());
			
			listView.setAdapter(exerciseArrayAdapter);
			linearLayout.addView(listView);
			
			////
			
		return linearLayout;
	}
	
	private void addExerciseDialog()
	{
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("Add Exercise");
	    
	    LinearLayout linearLayout = new LinearLayout(getActivity());
	    linearLayout.setOrientation(LinearLayout.VERTICAL);
	    
	    builder.setView(linearLayout);
	    
	    	////
	    	final EditText editText = new EditText(getActivity());
	    	linearLayout.addView(editText);
	    	
	    
	    builder.setPositiveButton("Add", new OnClickListener(){

	    	@Override
	    	public void onClick(DialogInterface dialog, int which) 
	    	{
	    		addExercise(editText.getText().toString());
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
	
	private void addExercise(String exerciseName)
	{
		Exercise exercise = new Exercise(exerciseName, Exercise.EXERCISE_TYPE_SET_REP);
		
		ExerciseFactory.addExercise(exercise);
		
		exerciseArrayAdapter.clear();
		
		List<Exercise> exercises = ExerciseFactory.getAllExercisesAsc();
		
		for(Exercise tempExercise: exercises)
		{
			exerciseArrayAdapter.add(tempExercise);
		}
		
		exerciseArrayAdapter.notifyDataSetChanged();
	}
}