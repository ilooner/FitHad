package com.fithad.android.data;

import java.util.Collections;
import java.util.List;

import com.activeandroid.query.Select;

public class ExerciseEventFactory 
{
	private ExerciseEventFactory()
	{
	}
	
	public static final List<ExerciseEvent> getExerciseEventsAsc(Event event)
	{
		List<ExerciseEvent> exerciseEvents = getExerciseEvents(event);
		Collections.sort(exerciseEvents, ExerciseEventComparator.getInstance());
		return exerciseEvents;
	}
	
	public static final List<ExerciseEvent> getExerciseEvents(Event event)
	{
		return new Select().from(ExerciseEvent.class).where("event = ?", event.getId()).execute();
	}
	
	public static final ExerciseEvent addExerciseEvent(ExerciseEvent exerciseEvent)
	{
		ExerciseEvent checkExerciseEvent = new Select().from(ExerciseEvent.class).where("exercise = ?", exerciseEvent.exercise.getId()).executeSingle();
		
		if(checkExerciseEvent == null)
		{
			exerciseEvent.save();
			checkExerciseEvent = exerciseEvent;
		}
		
		return checkExerciseEvent;
	}
}
