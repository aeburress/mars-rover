package marsrover.controller;

import java.util.*;
import marsrover.model.*;
import marsrover.controller.tasks.*;
import marsrover.controller.factories.*;
import marsrover.exceptions.*;

/**
* RoverController is the primary controller for this system. It is an observer and also
* inherits from the abstract Comm class, meaning it has send and receive
* functionality.
*/

public class RoverController extends Comm implements NasaObserver
{
	Rover rover;
	
	/**
	* Default constructor
	*/
	public RoverController()
	{
		rover = new Rover();
	}
	
	/**
	* Used to receive instructions from Earth. Converts the message to TaskList and
	* adds it to the backlog.
	*/
	public void receive(String message)
	{			
		String[] splitMessage = message.split(";");
		
		try
		{
			TaskList newList = new TaskList(rover, splitMessage, Integer.parseInt(splitMessage[0]), this);
			rover.addToBacklog(newList);
		}
		catch (TaskConversionException e)
		{
			throw new InvalidInstructionsException(e);
		}
		catch (IllegalArgumentException e)
		{
			throw new InvalidInstructionsException(e);
		}
	}
	
	/**
	* Executes the first TaskList in the backlog.
	*/
	public void doFirstJob()
	{
		TaskList currentList;
		
		if (rover.getBacklogSize() > 0)
		{
			currentList = rover.dequeueFromBacklog();
			currentList.executeTaskList();
		}
	}
	
	/**
	* Sends the passed in string to Earth.
	*/
	public void update(String message)
	{
		super.send(message);
		
		if (message.charAt(0) == 'F')
		{
			throw new TaskFailureException("Task failed: " + message);
		}
	}
}
