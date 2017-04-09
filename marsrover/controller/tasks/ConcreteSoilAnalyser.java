package marsrover.controller.tasks;

import marsrover.controller.*;

/**
* A concrete task for taking a photo.
*/

public class ConcreteSoilAnalyser extends SoilAnalyser implements Task, Subject
{
	private NasaObserver controller;
	
	/**
	* Constructor
	*/
	public ConcreteSoilAnalyser(NasaObserver inController)
	{
		controller = inController;
	}
	
	/**
	* Performs a soil analysis.
	*/
	public void performTask()
	{
		super.analyse();
	}
	
	/**
	* analysisReady() is called from the superclass's analyse() method once
	* analysis has completed. This method notifies the observing controller that the
	* task has succesfully run.
	*/
	protected void analysisReady(String soilAnalysis)
	{
		notifyObserver("Success;SoilAnalysis;" + soilAnalysis);
	}
	
	/**
	* Notifies the observing controller with the passed in string.
	*/
	public void notifyObserver(String details)
	{
		controller.update(details);
	}
}
