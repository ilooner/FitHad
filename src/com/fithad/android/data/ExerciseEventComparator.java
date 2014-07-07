package com.fithad.android.data;

import java.util.Comparator;

public class ExerciseEventComparator implements Comparator<ExerciseEvent>
{
	private static final ExerciseEventComparator instance = new ExerciseEventComparator();
	
	@Override
	public int compare(ExerciseEvent e1, ExerciseEvent e2) 
	{
		return ExerciseComparatorAsc.getInstance().compare(e1.getExercise(), e2.getExercise());
	}

	public static final ExerciseEventComparator getInstance()
	{
		return instance;
	}
}
