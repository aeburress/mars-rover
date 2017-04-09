package marsrover.controller.factories;

import java.util.*;
import marsrover.model.*;
import marsrover.controller.tasks.*;
import marsrover.controller.*;
import marsrover.exceptions.*;

/**
* Contains functions for creating Tasks after parsing string data
*/

public class TaskFactory
{
	/**
	* creates a new Task based off the first character of the passed in string. If the 
	* first character is 'L' a reference to an already existing object is returned rather
	* a new Task. If the taskString is a Drive or Turn instruction, it is assumed that
	* the string contains a space character and is followed by the magnitude by
	* which to travel or turn, hence splitting taskString by whitespace.
	*/
	public static Task createTask(Rover rover, String taskString, NasaObserver controller) throws IllegalArgumentException
	{
		Task chosenTask;
		String[] parsedTask = taskString.split("\\s+"); // split on space/s
		char taskChoice = parsedTask[0].charAt(0);	 // taskChoice = first character of taskSring
		
		switch (taskChoice)
		{
			// create Drive Task
			case 'D':
				chosenTask = new Drive(Double.parseDouble(parsedTask[1]), controller);
				break;
			
			// Turn task
			case 'T':
				chosenTask = new Turn(Double.parseDouble(parsedTask[1]), controller);
				break;
			
			// SoilAnalysis Task
			case 'A':
				chosenTask = new ConcreteSoilAnalyser(controller);
				break;
			
			// TakePhoto Task
			case 'P':
				chosenTask = new TakePhoto(controller);
				break;
				
			// Retrieve a TaskList from list of previously run TaskLists
			case 'L':
				try
				{
					chosenTask = rover.getTaskList(Integer.parseInt(parsedTask[1]));
				}
				catch (IndexOutOfBoundsException e)
				{
					throw new IllegalArgumentException(e);
				}
				break;
			
			default:
				throw new IllegalArgumentException("First character of the String " + taskString + " does not correspond to a task");
		}		
		
		return chosenTask;
	}
}
