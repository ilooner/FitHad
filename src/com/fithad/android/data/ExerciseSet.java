package com.fithad.android.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="exercise_set")
public class ExerciseSet extends Model
{
	@Column(name="exercise_event")
	public ExerciseEvent exerciseEvent;
	@Column(name="number_of_sets")
	public int numberOfSets;
	@Column(name="number_of_reps")
	public int numberOfReps;
	@Column(name="ordering")
	public int ordering;
	
	public ExerciseSet()
	{
	}
	
	public ExerciseSet(ExerciseEvent exerciseEvent,
					   int numberOfSets,
					   int numberOfReps,
					   int ordering)
	{
		setExerciseEvent(exerciseEvent);
		setNumberOfSets(numberOfSets);
		setNumberOfReps(numberOfReps);
		setOrdering(ordering);
	}
	
	public void setExerciseEvent(ExerciseEvent exerciseEvent)
	{
		if(exerciseEvent == null)
		{
			throw new NullPointerException("The exerciseEvent cannot be null.");
		}
		
		this.exerciseEvent = exerciseEvent;
	}
	
	public void setNumberOfSets(int numberOfSets)
	{
		if(numberOfSets < 0)
		{
			throw new IllegalArgumentException("The number of sets must be non-negative.");
		}
		
		this.numberOfSets = numberOfSets;
	}
	
	public void setNumberOfReps(int numberOfReps)
	{
		if(numberOfReps < 0)
		{
			throw new IllegalArgumentException("The number of reps must be non-negative.");
		}
	}
	
	public void setOrdering(int ordering)
	{
		if(ordering <= 0)
		{
			throw new IllegalArgumentException("The ordering value must be non-negative.");
		}
		
		this.ordering = ordering;
	}
}
