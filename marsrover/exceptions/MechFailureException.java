package marsrover.exceptions;

/**
* Thrown when the rover encounters a mechanical failure.
*/

public class MechFailureException extends RuntimeException
{
	public MechFailureException(String details)
	{
		super(details);
	}
}
