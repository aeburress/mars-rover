package marsrover.controller.tasks;

import java.util.*;
import java.lang.*;
import marsrover.model.*;
import marsrover.controller.*;
import marsrover.controller.factories.*;
import marsrover.exceptions.*;

/**
* Holds a list of tasks using a composite pattern where Tasks are leaf nodes and
* TaskLists are branches. Also holds the identifying key for a list of Tasks.
*/

public class TaskList implements Subject, Task
{
	private Rover rover;
	private LinkedList<Task> taskList;
	private int key;
	private NasaObserver controller;
	
	/**
	* Constructor. Creates a linked list of Tasks, using an iterator pattern with a
	* factory to instantiate objects for the list.
	*/
	public TaskList(Rover inRover, String[] taskStrings, int inKey, NasaObserver inController) throws TaskConversionException, IllegalArgumentException
	{
		taskList = new LinkedList<Task>();
		
		// convert each string in taskStrings to Tasks
		for (String taskString : taskStrings)
		{
			// skip the first String, it's the identifier, not a task
			if (taskString == taskStrings[0]) continue;
			
			try
			{
				taskList.addLast(TaskFactory.createTask(inRover, taskString, inController));
			}
			catch (IllegalArgumentException e)
			{
				throw new TaskConversionException(e);
			}
		}
		
		if (taskList.size() > 0)
		{
			rover = inRover;
			key = inKey;
			controller = inController;
		}
		else
		{
			throw new IllegalArgumentException("Passed in list of tasks is empty");
		}
	}
	
	/**
	* Goes through each task in the taskList and performs it. If a failure occurs
	* whilst performing a task the list is abandoned. Regardless of whether
	* the task list was successfully completed or not, it is added to the rover's
	* cache of previous lists.
	**/
	public void executeTaskList()
	{
		try
		{
			for (Task indTask : taskList)
			{
				indTask.performTask();
			}
			
			notifyObserver("List complete;" + key);
		}
		catch (MechFailureException e)
		{
			notifyObserver("Failure on list;" + key);
		}
		finally
		{
			rover.addToFinishedJobs(this);
		}
	}
	
	public int getKey()
	{
		return key;
	}
	
	/**
	* Passes a string to the observer.
	*/
	public void notifyObserver(String details)
	{
		controller.update(details);
	}
	
	/**
	* Wrapper method for executing a list of Tasks.
	*/
	public void performTask()
	{
		executeTaskList();
	}
}
