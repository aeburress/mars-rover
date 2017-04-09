package marsrover.controller;

/**
* Interface for a custom observer, the class is titled NasaObserver to avoid
* conflicts with the native java Observer class.
*
* Each NasaObserver can be updated with a String.
*/

public interface NasaObserver
{
	public void update(String details);
}
