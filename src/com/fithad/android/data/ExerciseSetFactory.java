package com.fithad.android.data;

import java.util.Collections;
import java.util.List;

import com.activeandroid.query.Select;
import com.fithad.android.R;

public class ExerciseSetFactory 
{
	private ExerciseSetFactory()
	{
	}
	
	/*public static ExerciseSet addExerciseSet(ExerciseSet exerciseSet)
	{
		ExerciseSet checkExerciseSet = new Select().from(ExerciseSet.class).where("exercise_event = ?", exerciseSet.exerciseEvent.getId()).where("ordering = ?", exerciseSet.ordering).executeSingle();
		
		if(checkExerciseSet == null)
		{
			exerciseSet.save();
			checkExerciseSet = exerciseSet;
		}
		
		return checkExerciseSet;
	}*/
	
	public static List<ExerciseSet> getAllExerciseSets(ExerciseEvent exerciseEvent)
	{
		return new Select().from(ExerciseSet.class).where("exercise_event = ?", exerciseEvent.getId()).execute();
	}
	
	public static List<ExerciseSet> getAllExerciseSetsAsc(ExerciseEvent exerciseEvent)
	{
		List<ExerciseSet> exerciseSets = getAllExerciseSets(exerciseEvent);
		Collections.sort(exerciseSets, ExerciseSetComparator.getInstance());
		
		return exerciseSets;
	}
	
	public static Integer maxOrdering(ExerciseEvent exerciseEvent)
	{
		List<ExerciseSet> exerciseSets = getAllExerciseSets(exerciseEvent);
		
		int orderingMax = Integer.MIN_VALUE;
		
		for(ExerciseSet exerciseSet: exerciseSets)
		{
			if(orderingMax < exerciseSet.ordering)
			{
				orderingMax = exerciseSet.ordering;
			}
		}
		
		if(orderingMax < 0)
		{
			return null;
		}
		
		return orderingMax;
	}
}
