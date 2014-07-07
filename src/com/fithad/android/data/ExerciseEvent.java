package com.fithad.android.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="exercise_event")
public class ExerciseEvent extends Model
{
	@Column(name="exercise")
	public Exercise exercise;
	
	@Column(name="event")
	public Event event;
	
	public ExerciseEvent()
	{
	}
	
	public ExerciseEvent(Exercise exercise,
						 Event event)
	{
		setExercise(exercise);
		setEvent(event);
	}
	
	public void setExercise(Exercise exercise)
	{
		if(exercise == null)
		{
			throw new NullPointerException("The given exercise cannot be null.");
		}
		
		this.exercise = exercise;
	}
	
	public Exercise getExercise()
	{
		return exercise;
	}
	
	public void setEvent(Event event)
	{
		if(event == null)
		{
			throw new NullPointerException("The given event cannot be null.");
		}
		
		this.event = event;
	}
	
	public Event getEvent()
	{
		return event;
	}
}
