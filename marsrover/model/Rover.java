package marsrover.model;

import java.util.*;
import java.lang.*;
import marsrover.controller.tasks.*;
import marsrover.controller.factories.*;

/**
* A Container class for holding the rovers list of previous tasks and its current
* backlog of task lists to be performed for the first time.
*/

public class Rover
{
	private Map<Integer, TaskList> previousJobs;
	private LinkedList<TaskList> backlog;
	
	/**
	* Default constructor
	*/
	public Rover()
	{
		previousJobs = new HashMap<Integer, TaskList>();
		backlog = new LinkedList<TaskList>();
	}
	
	/**
	* Adds a TaskList to the end of the backlog
	*/
	public void addToBacklog(TaskList list)
	{
		backlog.addLast(list);
	}
	
	/**
	* Dequeues the first item from the backlog
	*/
	public TaskList dequeueFromBacklog()
	{
		return backlog.removeFirst();
	}
	
	/**
	* Retrieves the ii'th TaskList from the map of previous jobs
	*/
	public TaskList getTaskList(int ii) throws IndexOutOfBoundsException
	{
		TaskList list = previousJobs.get(new Integer(ii));
		
		if (list == null)
		{
			throw new IndexOutOfBoundsException("No task list corresponding to " + ii);
		}

		return list;	
	}
	
	/**
	* Adds a TaskList to the cache of previously executed TaskLists
	*/
	public void addToFinishedJobs(TaskList list)
	{
		previousJobs.put(new Integer(list.getKey()), list);
	}
	
	/**
	* Returns the size of the backlog
	*/
	public int getBacklogSize()
	{
		return backlog.size();
	}
}
