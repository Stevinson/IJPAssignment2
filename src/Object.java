import javafx.scene.image.*;

/**
 * The class of each object in the game. Each object has a description and an 
 * image associated with it.
 * 
 * @author Edward
 * @version 1
 */

public class Object {


	private String description;
	private Image image;

	/**
	 * Constructs an object, provided it is given what the object is and an image of the object.
	 * @param description A String description of the object.
	 * @param image An image of the object.
	 */
	public Object(String description, Image image) 
	{
		this.description = description;
		this.image = image;
	}
	
	/**
	 * Get the name of the object.
	 * @return Name of the object.
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Get the image of the object.
	 * @return The image
	 */
	public Image getImage() 
	{
		return image;
	}
	
}
