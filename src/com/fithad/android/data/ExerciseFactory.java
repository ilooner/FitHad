package com.fithad.android.data;

import java.util.Collections;
import java.util.List;

import com.activeandroid.query.Select;

public class ExerciseFactory 
{
	private ExerciseFactory()
	{
	}
	
	public static final Exercise addExercise(Exercise exercise)
	{
		Exercise checkExercise = new Select().from(Exercise.class).where("exercise_name = ?", exercise.exerciseName).executeSingle();
		
		if(checkExercise == null)
		{
			exercise.save();
			checkExercise = exercise;
		}
		
		return checkExercise;
	}
	
	public static final List<Exercise> getAllExercises()
	{
		return new Select().from(Exercise.class).
							orderBy("exercise_name asc").
							execute();
	}
	
	public static final List<Exercise> getAllExercisesAsc()
	{
		List<Exercise> exercises = getAllExercises();
		Collections.sort(exercises, ExerciseComparatorAsc.getInstance());
		
		return exercises;
	}
}
