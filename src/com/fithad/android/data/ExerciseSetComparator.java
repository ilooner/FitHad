package com.fithad.android.data;

import java.util.Comparator;

public class ExerciseSetComparator implements Comparator<ExerciseSet>
{
	public static final ExerciseSetComparator instance = new ExerciseSetComparator();
	
	private ExerciseSetComparator()
	{
	}

	@Override
	public int compare(ExerciseSet es1, ExerciseSet es2) 
	{
		return es1.ordering - es2.ordering;
	}
	
	public static final ExerciseSetComparator getInstance()
	{
		return instance;
	}
}
