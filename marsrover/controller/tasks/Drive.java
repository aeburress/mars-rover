package marsrover.controller.tasks;

import java.util.*;
import marsrover.controller.*;
import marsrover.exceptions.*;

/**
* A Task for driving the rover.
*/

public class Drive extends Driver implements Subject, Task
{
	// class fields
	private double distance;
	private NasaObserver controller;
	
	// class constants
	public static final double MIN_DISTANCE = -100.0;
	public static final double MAX_DISTANCE = 100.0;
	
	/**
	* Constructor
	*/
	public Drive(double inDistance, NasaObserver inController) throws IllegalArgumentException
	{
		if (distanceValid(inDistance))
		{
			distance = inDistance;
			controller = inController;
		}
		else
		{
			throw new IllegalArgumentException("Distance must be between -100.0 and 100.0");
		}
	}
	
	/**
	* Calls the drive method in the abstract Driver superclass.
	*/
	public void performTask()
	{
		super.drive(distance);
	}
	
	/**
	* Is called by the superclass's drive() method once the rover has successfully
	* reached its destination. Notifies the observing controller.
	*/
	protected void moveFinished()
	{
		String details = "Success;Drive;" + distance;
		notifyObserver(details);
	}
	
	/**
	* Is called by the superclass's drive() method when the rover fails to complete
	* its drive operation. Notifies the observer of the failure.
	*/
	protected void mechanicalError()
	{
		String details = "Failure;Drive;" + distance;
		notifyObserver(details);
		throw new MechFailureException(details);
	}
	
	/**
	* Passes a string to the observing controller.
	*/
	public void notifyObserver(String details)
	{
		controller.update(details);
	}
	
	/**
	* Disallowing turn functionality for Drive Tasks.
	*/
	@Override
	public void turn(double angle) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Drive class does not support turn functionality");
	}
	
	/**
	* validates a distance variable.
	*/
	private boolean distanceValid(double inDistance)
	{
		boolean valid = false;
		if(inDistance >= MIN_DISTANCE && inDistance <= MAX_DISTANCE)
		{
			valid = true;
		}
		
		return valid;
	}
}
