/**
 * A subclass of the Location abstract class. Inherits all the methods and fields 
 * of a location but adds some functionality only associated with an OutsideLocation.
 * 
 * @author Edward Stevinson
 * @version 1
 */

public class OutsideLocation extends Location2 {

	/**
	 * Initialises the location with the following parameters.
	 * @param locationName The location name.
	 */
	public OutsideLocation(String locationName) 
	{
		super(locationName);
	}
	
 	
 	/**
 	 * Returns a String message stating that outside locations
 	 * do not have a detailed description assigned to them.
 	 * @return The String message.
 	 */
 	public String returnLongDescription() 
 	{
 		String output = "\nNo info about outside locations!";
 		return output;
 	}
}
