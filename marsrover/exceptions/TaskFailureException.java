package marsrover.exceptions;

/**
* Thrown when a task fails to complete.
*/

public class TaskFailureException extends RuntimeException
{
	public TaskFailureException(String cause)
	{
		super(cause);
	}
}
