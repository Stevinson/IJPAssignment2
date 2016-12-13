import java.util.HashMap;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Abstract class for the location. Each location has a HashMap of its exits and of its views, 
 * as well as an ArrayList of all the objects in the location.
 * 
 * @author Edward Stevinson
 * @version 1 
 */

public abstract class Location2 {
	
    protected String locationName;
    protected HashMap<String, Location2> exits;       
    protected HashMap<String, Image> views;

    protected ArrayList<Object> objectsInRoom = new ArrayList<>();

    
    /**
     * Create a room described "locationName".
     * @param locationName The room's description.
     */
    public Location2 (String locationName) 
    {
        this.locationName = locationName;
        exits = new HashMap<String, Location2>();
        views = new HashMap<String, Image>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Location2 neighbor) // enter arrays (of same length)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Allocate an image to the specified direction.
     * @param direction The direction to allocate the image.
     * @param view The image of the view in that direction.
     */
    public void setView(String direction, Image view)
    {
    	views.put(direction,  view);
    }
    
    /*
     * Possible extension (will not currently work)
     * Set up the views, but instead of one-by-one, takes a HashMap of direction/image 
     * pairs, and then adds them to the HashMaps. 
     * 
    public void setView(HashMap<String, Image> hashedViews)
    {
    	Set<String> keys = new HashSet<>();
    	keys = hashedViews.keySet();
    	for(String key: keys){
    		setView(key, hashedViews.get(key));
    	}
    }
    */
    
    /**
     * Return the name of the location.
     * @return The short description of the location.
     */
    public String getLocationName()
    {
        return locationName;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     * @throws IllegalArgumentException if the direction is invalid.
     */
    public Location2 getExit(String direction) 
    {
    	// Throw error if null entered as direction, as this is not a valid key.
    	if(direction == null) 
    	{
    		throw new IllegalArgumentException(
    				"null key in getExit");
    	}
    	if(direction.trim().length() ==0)
    	{
    		throw new IllegalArgumentException(
    				"Empty direction passed to getExit");
    	}
    	// Get exit in requested direction. Will return null if it does not have exit in that direction.
        return exits.get(direction);
    }
    
    /**
     * Return the image of the direction being faced. If no image is available 
     * display a 'Image Not Available' picture.
     * @param direction The direction which we want the view from.
     * @return Image The image associated with that view.
     */
    public Image getView(String direction) 
    {
    	// If the direction is a key in the views hashmap return the associated image
    	if (keyInUseInViews(direction))
    	{
        	return views.get(direction);
    	}
    	// Otherwise return 'Image Not Available' image
    	else
    	{
    		return new Image("notAvailable.png");
    	}
    }
    
    /**
     * Method to return whether a specific String is currently a key in the views HashMap.
     * @param key The String to check if it is a key.
     * @return True if key is in the views HashMap
     */
    public boolean keyInUseInViews(String key) 
    {
    	return views.containsKey(key);
    }
    
    /**
     * Get names of items from room (from index)
     * @param index The index of the object to return.
     * @return The returned object.
     */
 	public Object getObject(int index)
 	{
    	if(index < 0) 
    	{
    		throw new IllegalArgumentException(
    				"negative value as ArrayList index");
    	}
 		return objectsInRoom.get(index);
 	}
 	
 	/**
 	 * Get names of items from room (from Object name)
 	 * @param objectName The name of the object to return.
     * @return The returned object.
 	 */
	public Object getObject(String objectName)
	{
		
		for(int i = 0; i < objectsInRoom.size(); i++) {
			if (objectsInRoom.get(i).getDescription().equals(objectName)) {
				return objectsInRoom.get(i);
			}
		}
		return null;
	}
    
	/**
	 * Return a String of all the objects in the location.
	 * @return The names of the objects.
	 */
 	public String getRoomObjects()
 	{
 		String output = "";
 		for (int i = 0; i < objectsInRoom.size(); i++)
 		{
 			output += objectsInRoom.get(i).getDescription() + " ";
 		}
 		return output;
 	}
 	
 	/**
 	 * Return an ArrayList of all the objects in the location.
 	 * @return The ArrayList of objects.
 	 */
 	public ArrayList<Object> getRoomObjectsList()
 	{
 		return objectsInRoom;
 	}
 
 	/**
 	 * Remove an object from a location: removes it from the objectsInRoom ArrayList.
 	 * @param objectName The name of the object we want to remove.
 	 */
 	public void removeObject(String objectName) 
 	{
		
		for(int i = 0; i < objectsInRoom.size(); i++) {
			if (objectsInRoom.get(i).getDescription().equals(objectName)) {
				objectsInRoom.remove(i);
			}
		}
 	}

	/**
	 * Place an object in the location: adds it to the objectsInRoom ArrayList.
	 * @param newObject The object to add.
	 */
 	public void placeObject(Object newObject)
 	{
 		objectsInRoom.add(newObject);
 	}
 	
 	/**
 	 * Return the number of objects currently in the location.
 	 * @return The number of objects in the location.
 	 */
 	public int numberOfObjectsInLocation()
 	{
 		return objectsInRoom.size();
 	}
 	
 	/**
 	 * Return a long description of the location. Abstract method.
 	 * @return The long description.
 	 */
 	abstract public String returnLongDescription();

}

