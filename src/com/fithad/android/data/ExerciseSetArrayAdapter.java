package com.fithad.android.data;

import java.util.List;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fithad.android.R;

public class ExerciseSetArrayAdapter extends ArrayAdapter<ExerciseSet>
{
	public ExerciseSetArrayAdapter(Context context, List<ExerciseSet> objects)
	{
		super(context, R.layout.rowlayout, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ExerciseSet exerciseSet = this.getItem(position);
		
		LinearLayout linearLayout = new LinearLayout(parent.getContext());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
			TextView eSetText = new TextView(parent.getContext());
			eSetText.setText("Set Count");
			linearLayout.addView(eSetText);
			
			final EditText eSetEditText = new EditText(parent.getContext());
			eSetEditText.setText(((Integer)exerciseSet.numberOfSets).toString());
			eSetEditText.setKeyListener(null);
			eSetEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
			linearLayout.addView(eSetEditText);
			
			LinearLayout setButtonLayout = new LinearLayout(parent.getContext());
			setButtonLayout.setOrientation(LinearLayout.VERTICAL);
			linearLayout.addView(setButtonLayout);
			
				////
				LinearLayout setButtonLayoutTop = new LinearLayout(parent.getContext());
				setButtonLayout.addView(setButtonLayoutTop);
				
					Button setP1Button = new Button(parent.getContext());
					setP1Button.setText("+1");
					setP1Button.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) 
						{
							adjustSet(eSetEditText, exerciseSet, 1);
						}
					
					});
					setButtonLayoutTop.addView(setP1Button);
					
					////
					
					Button setP5Button = new Button(parent.getContext());
					setP5Button.setText("+5");
					setP5Button.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) 
						{
							adjustSet(eSetEditText, exerciseSet, 5);
						}
					
					});
					setButtonLayoutTop.addView(setP5Button);
					
					////
					
					Button setP10Button = new Button(parent.getContext());
					setP10Button.setText("+10");
					setP10Button.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) 
						{
							adjustSet(eSetEditText, exerciseSet, 10);
						}
					
					});
					setButtonLayoutTop.addView(setP10Button);
				////
				
				////
				LinearLayout setButtonLayoutBottom = new LinearLayout(parent.getContext());
				setButtonLayout.addView(setButtonLayoutBottom);
				
					Button setM1Button = new Button(parent.getContext());
					setM1Button.setText("-1");
					setM1Button.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) 
						{
							adjustSet(eSetEditText, exerciseSet, -1);
						}
				
					});
					setButtonLayoutBottom.addView(setM1Button);
				
					////
				
					Button setM5Button = new Button(parent.getContext());
					setM5Button.setText("-5");
					setM5Button.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) 
						{
							adjustSet(eSetEditText, exerciseSet, -5);
						}
				
					});
					setButtonLayoutBottom.addView(setM5Button);
				
					////
				
					Button setM10Button = new Button(parent.getContext());
					setM10Button.setText("-10");
					setM10Button.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) 
					{
						adjustSet(eSetEditText, exerciseSet, -10);
					}
				
					});
					setButtonLayoutBottom.addView(setM10Button);
			////
				
		////
		
					
					TextView eRepText = new TextView(parent.getContext());
					eRepText.setText("Rep Count");
					linearLayout.addView(eRepText);
					
					final EditText eRepEditText = new EditText(parent.getContext());
					eRepEditText.setText(((Integer)exerciseSet.numberOfReps).toString());
					eRepEditText.setKeyListener(null);
					eRepEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
					linearLayout.addView(eRepEditText);
					
					LinearLayout repButtonLayout = new LinearLayout(parent.getContext());
					repButtonLayout.setOrientation(LinearLayout.VERTICAL);
					linearLayout.addView(repButtonLayout);
					
						////
						LinearLayout repButtonLayoutTop = new LinearLayout(parent.getContext());
						repButtonLayout.addView(repButtonLayoutTop);
						
							Button repP1Button = new Button(parent.getContext());
							repP1Button.setText("+1");
							repP1Button.setOnClickListener(new OnClickListener(){

								@Override
								public void onClick(View v) 
								{
									adjustRep(eRepEditText, exerciseSet, 1);
								}
							
							});
							repButtonLayoutTop.addView(repP1Button);
							
							////
							
							Button repP5Button = new Button(parent.getContext());
							repP5Button.setText("+5");
							repP5Button.setOnClickListener(new OnClickListener(){

								@Override
								public void onClick(View v) 
								{
									adjustRep(eRepEditText, exerciseSet, 5);
								}
							
							});
							repButtonLayoutTop.addView(repP5Button);
							
							////
							
							Button repP10Button = new Button(parent.getContext());
							repP10Button.setText("+10");
							repP10Button.setOnClickListener(new OnClickListener(){

								@Override
								public void onClick(View v) 
								{
									adjustRep(eRepEditText, exerciseSet, 10);
								}
							
							});
							repButtonLayoutTop.addView(repP10Button);
						////
						
						////
						LinearLayout repButtonLayoutBottom = new LinearLayout(parent.getContext());
						repButtonLayout.addView(repButtonLayoutBottom);
						
							Button repM1Button = new Button(parent.getContext());
							repM1Button.setText("-1");
							repM1Button.setOnClickListener(new OnClickListener(){

								@Override
								public void onClick(View v) 
								{
									adjustRep(eRepEditText, exerciseSet, -1);
								}
						
							});
							repButtonLayoutBottom.addView(repM1Button);
						
							////
						
							Button repM5Button = new Button(parent.getContext());
							repM5Button.setText("-5");
							repM5Button.setOnClickListener(new OnClickListener(){

								@Override
								public void onClick(View v) 
								{
									adjustRep(eRepEditText, exerciseSet, -5);
								}
						
							});
							repButtonLayoutBottom.addView(repM5Button);
						
							////
						
							Button repM10Button = new Button(parent.getContext());
							repM10Button.setText("-10");
							repM10Button.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View v) 
							{
								adjustRep(eRepEditText, exerciseSet, -10);
							}
						
							});
							repButtonLayoutBottom.addView(repM10Button);
					////
						
				////
			
					parent.invalidate();
		return linearLayout;
	}
	
	private void adjustSet(EditText editText, ExerciseSet exerciseSet, int change)
	{
		exerciseSet.numberOfSets += change;
		
		if(exerciseSet.numberOfSets < 0)
		{
			exerciseSet.numberOfSets = 0;
		}
		
		editText.setText(((Integer) exerciseSet.numberOfSets).toString());
		
		exerciseSet.save();
	}
	
	private void adjustRep(EditText editText, ExerciseSet exerciseSet, int change)
	{
		exerciseSet.numberOfReps += change;
		
		if(exerciseSet.numberOfReps < 0)
		{
			exerciseSet.numberOfReps = 0;
		}
		
		editText.setText(((Integer) exerciseSet.numberOfReps).toString());
		
		exerciseSet.save();
	}
}
