package marsrover.controller.tasks;

import marsrover.controller.*;

/**
* A Task for taking a photo.
*/
public class TakePhoto extends Camera implements Task, Subject
{
	private NasaObserver controller;
	
	/**
	* Constructor
	*/
	public TakePhoto(NasaObserver inController)
	{
		controller = inController;
	}
	
	/**
	* Calls the takePhoto() method in the Camera class.
	*/
	public void performTask()
	{
		super.takePhoto();
	}
	
	/**
	* Is called by the superclass takePhoto() method when the photo is ready to be
	* transmitted. Notifies the observer and passes it the photo data.
	*/
	protected void photoReady(char[] photoData)
	{
		// convert char array to String
		String data = new String(photoData);
		
		notifyObserver("Success;Camera;" + data);
	}
	
	/**
	* Passes a string to the observing controller.
	*/
	public void notifyObserver(String details)
	{
		controller.update(details);
	}
}
