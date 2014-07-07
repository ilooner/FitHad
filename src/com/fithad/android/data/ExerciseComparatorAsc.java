package com.fithad.android.data;

import java.util.Comparator;

public class ExerciseComparatorAsc implements Comparator<Exercise>
{
	public static final ExerciseComparatorAsc instance = new ExerciseComparatorAsc();

	@Override
	public int compare(Exercise e1, Exercise e2) 
	{
		return e1.exerciseName.compareTo(e2.exerciseName);
	}
	
	public static final ExerciseComparatorAsc getInstance()
	{
		return instance;
	}
}
