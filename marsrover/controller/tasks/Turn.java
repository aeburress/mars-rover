package marsrover.controller.tasks;

import java.util.*;
import marsrover.controller.*;
import marsrover.exceptions.*;

/**
* A Task object for performing a turn with the rover.
*/

public class Turn extends Driver implements Subject, Task
{
	// class fields
	private double degrees;
	private NasaObserver controller;
	
	// class constants
	public static final double MIN_DEGREES = -100.0;
	public static final double MAX_DEGREES = 100.0;
	
	/**
	* Constructor
	*/
	public Turn(double inDegrees, NasaObserver inController) throws IllegalArgumentException
	{
		if (degreesValid(inDegrees))
		{
			degrees = inDegrees;
			controller = inController;
		}
		else
		{
			throw new IllegalArgumentException("Degrees must be between -180.0 and 180.0");
		}
	}
	
	/**
	* Strategy pattern implementation for performing the turn. Calls the turn()
	* method from the abstract superclass.
	*/
	public void performTask()
	{
		super.turn(degrees);
	}
	
	/**
	* Called in the superclass's turn method once the rover has finished turning.
	* Notifies the observer that the turn was completed.
	*/
	protected void moveFinished()
	{
		String details = "Success;Turn;" + degrees;
		notifyObserver(details);
	}
	
	/**
	* Called in the superclass's turn method if the rover failed to turn. Notifies the
	* observer of the failure and throws an exception.
	*/
	protected void mechanicalError()
	{
		String details = "Failure;Turn;" + degrees;
		notifyObserver(details);
		throw new MechFailureException(details);
	}
	
	/**
	* Passes a string to the observer.
	*/
	public void notifyObserver(String details)
	{
		controller.update(details);
	}
	
	/**
	* Disallows drive functionality with a Turn Task object.
	*/
	@Override
	public void drive(double angle) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Turn class does not support drive functionality");
	}

	/**
	* Validator for the turning angle.
	*/
	private boolean degreesValid(double inDegrees)
	{
		boolean valid = false;
		if(inDegrees >= MIN_DEGREES && inDegrees <= MAX_DEGREES)
		{
			valid = true;
		}
		
		return valid;
	}
}
