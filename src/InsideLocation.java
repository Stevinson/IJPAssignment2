/**
 * A subclass of the Location abstract class. Inherits all the methods and fields of a location but adds some
 * functionality only associated with an InsideLocation, such as a detailed description of the location.
 * 
 * @author Edward Stevinson
 * @version 1
 */

public class InsideLocation extends Location2 {

	String longDescription;
	
	/**
	 * Initialises the location with the following parameters.
	 * @param locationName The location name.
	 * @param longDescription A description of the history of the location.
	 */
	public InsideLocation(String locationName, String longDescription) 
	{
		super(locationName);
		this.longDescription = longDescription; 
	}	


 	/**
 	 * Method which returns the longDescription property.
 	 * @return The detailed description of the location.
 	 */
 	public String returnLongDescription() 
 	{
 		return longDescription;
 	}
}
