package com.fithad.android.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="exercise")
public class Exercise extends Model
{
	public static final String EXERCISE_TYPE_SET_REP = "Set Rep";
	public static final String[] EXERCISE_TYPES = {EXERCISE_TYPE_SET_REP};
	
	@Column(name="exercise_name")
	public String exerciseName;
	
	@Column(name="exercise_type")
	public String exerciseType;
	
	public Exercise()
	{
	}
	
	public Exercise(String exerciseName,
					String exerciseType)
	{
		setExerciseName(exerciseName);
		setExerciseType(exerciseType);
	}
	
	public void setExerciseName(String exerciseName)
	{
		if(exerciseName == null)
		{
			throw new NullPointerException("The given exercise name cannot be null.");
		}
		
		this.exerciseName = exerciseName;
	}
	
	public void setExerciseType(String exerciseType)
	{
		for(String exerciseTypeItem: EXERCISE_TYPES)
		{
			if(exerciseTypeItem.equals(exerciseType))
			{
				this.exerciseType = exerciseType;
				return;
			}
		}
		
		throw new IllegalArgumentException("The given exercise type is not valid.");
	}
}
