package marsrover.exceptions;

/**
* Thrown when an error occurs trying to convert a string representing a task
* into a task object.
*/

public class TaskConversionException extends Exception
{
	public TaskConversionException(Throwable cause)
	{
		super(cause);
	}
}
