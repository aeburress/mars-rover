package marsrover.exceptions;

/**
* Thrown when a task string sent from Earth contains invalid data.
*/

public class InvalidInstructionsException extends RuntimeException
{
	InvalidInstructionsException(throwable cause)
	{
		super(cause);
	}
}