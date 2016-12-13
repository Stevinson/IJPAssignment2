import javafx.event.ActionEvent;
import javafx.fxml.FXML;			
import javafx.scene.control.TextField; 
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * The controller for the application. This  manages the other classes and 
 * determines the overall behaviour of the program.
 * 
 * @author Edward
 * @version 1
 */

public class MyController {
	
    private Location2 currentLocation;
    private String currentDirection;
    
    private Parser parser;
    
    // A LinkedList of all the possible directions
    LinkedList<String> directions = new LinkedList<>();

    ArrayList<Object> objectsBeingCarried = new ArrayList<>(); 
    
    // The maximum number of items that can be displayed (dependent on the # of ImageViewers dedicated to this task)
    private static final int maxNumberOfItemsDisplayed = 4;
    
    // ArrayList of all the available ImageViewers for objects
  	ArrayList<ImageView> objectImages = new ArrayList<>();
    
    // All the javafx elements in the user interface
	@FXML
	private Button lookRight, lookLeft, moveForward;
	@FXML
	private ImageView mainImage;
	@FXML
	private TextField messageInput;
	@FXML
	private TextArea messageOutput;
	@FXML 
	private ImageView objectInLocation1, objectInLocation2, objectInLocation3, objectInLocation4;
	@FXML
	private ImageView carriedObjectImage1, carriedObjectImage2;
	
	
	
	/**
	 * Called by MainProgram to initialise the application. Calls methods that set up all the starting values.
	 */
	public void Initialise() {
		createLocations(); 
		createDirections();
		parser = new Parser();
		// Make output text drop to a new line if longer than the text area width
		messageOutput.setWrapText(true);
		mainImage.setImage(currentLocation.getView(currentDirection));
		// Add the ImageViwers for the objects to the objectImage HashMap
		objectImages.add(objectInLocation1);
      	objectImages.add(objectInLocation2);
      	objectImages.add(objectInLocation3);
      	objectImages.add(objectInLocation4);
		displayObjects();
		printWelcome();
	}

	/**
	 * Detect when text is entered into the TextField. Passes this text to the parser to 
	 * interpret as a command.
	 * @param event When text is entered and submitted into the TextField.
	 */
	public void command(ActionEvent event)
	{
		String commandText = messageInput.getText();
		Command command = parser.getCommand(commandText);
		processCommand(command);
		messageInput.setText(null);
	}
	
	/**
	 * Turn left method. This calls the changeDirection method and displays the new view.
	 * @param event When the 'Turn Left' button is clicked.
	 */
	public void turnLeft(ActionEvent event) 
	{		
		boolean clockwiseBoolean = false;
		changeDirection(clockwiseBoolean, currentDirection);
		messageOutput.appendText("\nYou are now facing: " + currentDirection);
		mainImage.setImage(currentLocation.getView(currentDirection));
	}
	
	/**
	 * Turn right method. This calls the changeDirection method and displays the new view.
	 * @param event When the 'Turn Right' button is clicked.
	 */
	public void turnRight(ActionEvent event)
	{
		boolean clockwiseBoolean = true;
		changeDirection(clockwiseBoolean, currentDirection);
		messageOutput.appendText("\nYou are now facing: " + currentDirection);
		mainImage.setImage(currentLocation.getView(currentDirection));
	}
		
	/**
	 * Method to move between rooms when the move button is pressed. If the move is allowed 
	 * it changes the currentRoom and displays the new view. If not a message is relayed to the
	 * TextArea stating this.
	 * @param event The 'Move Forward' button being clicked.
	 */
	public void move(ActionEvent event)
	{
		Location2 nextLocation = currentLocation.getExit(currentDirection);
		if (nextLocation == null) {
			messageOutput.appendText("\nYou cannot move in this direction!"); // Change what it does here
	    }
        else {
        	currentLocation = nextLocation;
    		mainImage.setImage(currentLocation.getView(currentDirection));
        	displayObjects();
    		messageOutput.appendText("\nYou are now in: " + currentLocation.getLocationName());
    		messageOutput.appendText("\nThe following objects are in this location:\n" + currentLocation.getRoomObjects());
        }
	}
    
    /**
     * Updates the currentDirection field upon turning.
     * @param clockwise Boolean value that is true to turn clockwise, and false to turn anti-clockwise.
     * @param currentDirection The direction that is currently being faced.
     */
    public void changeDirection(boolean clockwise, String currentDirection)
    {
    	int index = directions.indexOf(currentDirection); //error handling
    	if (clockwise)
    	{
    		if(index+1<directions.size())
    		{
    			index++;
    		}
    		else
    		{
    			index = 0;
    		}
    	}
    	else
    	{
    		if(index != 0)
    		{
    			index--;
    		}
    		else
    		{
    			index=directions.size()-1;
    		}
    	}
    	this.currentDirection = directions.get(index);
    }
    
    
    /**
     * Take the relevant action upon receiving a command. Uses a switch statement to
     * select between the possible commands defined in the CommandWord enumerated class.
     * @param command
     */
    private void processCommand(Command command) 
    {
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                messageOutput.appendText("\nI don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case INVENTORY:
            	printInventory();
            	break;
            case PICK:
            	getObject(command);
            	break;
            case DROP:
            	dropObject(command);
            	break;
            case INFO:
            	printInfo();
            	break;
        }
    }
    
    /**
     * Prints out all the objects the user is currently carrying.
     */
	private void printInventory() 
	{
		String output = "";
		for (int i = 0; i < objectsBeingCarried.size(); i++) {
			output += objectsBeingCarried.get(i).getDescription() + " ";
		}
		messageOutput.appendText("\nYou are carrying:\n");
		messageOutput.appendText(output);
	} 
	
 	
 	/**
 	 * Pick up an item upon getting 'pick' as the command input. If the item is in the location
 	 * and you are not already carrying the maximum number of items then it is added to your carried items.
 	 * @param command The command passed into the TextField.
 	 */
 	public void getObject(Command command)
 	{
 		if(!command.hasSecondWord()) {
 			messageOutput.appendText("\nWhat do you want to pick up?");
 			return;
 		}
 		
 		String object = command.getSecondWord();
 		Object newObject = currentLocation.getObject(object);
 		
 		if(newObject==null) {
 			messageOutput.appendText("\nThat item is not in this location.");
 		}
 		else {
 			objectsBeingCarried.add(newObject);
 			currentLocation.removeObject(object);
 			messageOutput.appendText("\nPicked up the: " + object);
 	 		displayObjects();
 		}
 	}
 	
 	/**
 	 * Drop an item upon getting 'drop' as the command input.
 	 * @param command The command passed into the TextField.
 	 */
 	public void dropObject(Command command) {
	
		if(!command.hasSecondWord()) {
			messageOutput.appendText("\nDrop what?");
			return;
		}
		
		String object = command.getSecondWord();
		Object newObject = null;
		int index = 0;
		for (int i = 0; i < objectsBeingCarried.size(); i++) {
			if (objectsBeingCarried.get(i).getDescription().equals(object)) {
				newObject = objectsBeingCarried.get(i);
				index = i;
			}
		}
		if(newObject==null) {
			messageOutput.appendText("\nThat item is not in your inventory");
		}
		// Don't allow the drop if all the ImageViewers are already filled with objects
		else if (currentLocation.numberOfObjectsInLocation() > maxNumberOfItemsDisplayed-1) {
			messageOutput.appendText("\nThis location has all the items it can hold!"); /////TEMP
		}
		else {
			currentLocation.placeObject(objectsBeingCarried.get(index));
			objectsBeingCarried.remove(index);
			messageOutput.appendText("\nDropped: " + object);
	 		displayObjects();
		}
 	}
 	
 	/**
 	 * Display in the user interface the items currently in the location.
 	 * Only maxNumberOfItemsDisplayed are allowed to be displayed.
 	 */
     public void displayObjects() 
     {   	 
    	ArrayList<Object> items = currentLocation.getRoomObjectsList();
    	// If there are more items in the room than ImageViewers available, display the first four in the list
    	if (items.size() > maxNumberOfItemsDisplayed) {
    		for (int i = 0; i < maxNumberOfItemsDisplayed; i++) 
    		{
    			objectImages.get(i).setImage(items.get(i).getImage());
    		}
    	}
    	else {
    		for (int i = 0; i<items.size(); i++) {
    			objectImages.get(i).setImage(items.get(i).getImage());
    		}
    		for (int i = items.size(); i < maxNumberOfItemsDisplayed; i++) 
    		{
    			objectImages.get(i).setImage(null);
    		}
    	}
     }

     /**
      * Print text to the screen which shows the possible commands.
      */
     private void printHelp() 
     {
    	 messageOutput.appendText("\nYour possible commands are:");
    	 messageOutput.appendText("\n" + parser.listCommands());
     }
     
     /**
      * Prints a welcome message and advice to the screen. 
      */
     private void printWelcome()
     {
    	 messageOutput.appendText("Roam around George Square discovering facts and moving objects!");
    	 messageOutput.appendText("\nEnter commands in the text window below.");
    	 messageOutput.appendText("\nEnter 'help' for a list of possible commands");
     }
     
     /**
      * Prints a description of the current location. 
      */
     private void printInfo()
     {
    	 messageOutput.appendText("\nInfo about this location:\n");
    	 messageOutput.appendText(currentLocation.returnLongDescription());
     }
     
     /**
 	 * Initialises all the room locations (and currently objects as well)
 	 */
     private void createLocations()  
     {
         OutsideLocation locationNW, locationN, locationNE, locationW, locationC, locationS;
         InsideLocation locationLibrary;        
         
         // Create all the locations
         locationNW = new OutsideLocation("George Square (North-West)"); //create a hashmap or the views and exits and pass to argument of location constructor
         locationN = new OutsideLocation("George Square (North)");
         locationNE = new OutsideLocation("George Square (North-East)");
         locationW = new OutsideLocation("George Square (West)");
         locationC = new OutsideLocation("George Square Gardens");
         locationS = new OutsideLocation("George Square (South)");
         locationLibrary = new InsideLocation("Edinburgh University Library", "Opened in 1967 and designed by Sir Basil Spence");
         
         // Exits and views are added one by one here. There is also methods that allow HashMaps of values to be entered.
         locationNW.setExit("east", locationN);
         locationNW.setExit("south", locationW);
         locationNW.setView("north", new Image("location1N.jpeg"));
         locationNW.setView("east", new Image("location1E.jpeg"));
         locationNW.setView("south", new Image("location1S.jpeg"));
         locationNW.setView("west", new Image("location1W.jpeg"));
         
         locationN.setExit("east", locationNE);
         locationN.setExit("south", locationC);
         locationN.setExit("west", locationNW);
         locationN.setView("north", new Image("location2NN.jpeg"));
         locationN.setView("east", new Image("location2E.jpeg"));
         locationN.setView("south", new Image("location2S.jpeg"));
         locationN.setView("west", new Image("location2W.jpeg"));

         locationNE.setExit("west", locationN);
         locationNE.setView("north", new Image("location3N.jpeg"));
         locationNE.setView("east", new Image("location3E.jpeg"));
         locationNE.setView("south", new Image("location3S.jpeg"));
         locationNE.setView("west", new Image("location3W.jpeg"));

         locationW.setExit("north", locationNW);
         locationW.setExit("east", locationC);
         locationW.setView("north", new Image("location4N.jpeg"));
         locationW.setView("east", new Image("location4E.jpeg"));
         locationW.setView("south", new Image("location4S.jpeg"));
         locationW.setView("west", new Image("location4W.jpeg"));
         
         locationC.setExit("north", locationN);
         locationC.setExit("south", locationS);
         locationC.setExit("west", locationW);
         locationC.setView("north", new Image("locationCN.JPG"));
         locationC.setView("east", new Image("locationCE.JPG"));
         locationC.setView("south", new Image("locationCS.JPG"));
         locationC.setView("west", new Image("locationCW.JPG"));
         
         locationS.setExit("north", locationC);
         locationS.setExit("south", locationLibrary);
         locationS.setView("north", new Image("location6N.png"));
         locationS.setView("east", new Image("locationSE.JPG"));
         locationS.setView("south", new Image("location6S.jpg"));
         // locationS.setView("west", new Image("location6W.jpg"));
         
         locationLibrary.setExit("north", locationS);
         locationLibrary.setView("south", new Image("locationLS.png"));
         locationLibrary.setView("north", new Image("locationLN.jpeg"));
         locationLibrary.setView("west", new Image("locationLW.jpg"));
         

         // Set starting location
         currentLocation = locationNW; 
         
         // Objects are initialised and set in locations here
         Object coursework = new Object("coursework", new Image("coursework.png"));
         locationLibrary.placeObject(coursework);
         Object prize = new Object("prize", new Image("prize.png"));
         locationN.placeObject(prize);
         Object pen = new Object("pen", new Image("pen.png"));
         locationS.placeObject(pen);
         Object laptop = new Object("laptop", new Image("laptop.png"));
         locationNW.placeObject(laptop);
     }
     
    /**
     * Initialises the directions in the game and adds them into an ArrayList.
     * Currently they must be entered in order (clockwise)          
     */
    private void createDirections() 
    {
    	// Extra directions can be added here, but must be done in the correct order (added clockwise)
      	// and can't be duplicates
        directions.add("north");
        directions.add("east");
        directions.add("south");
        directions.add("west");
        // Staring direction set
      	currentDirection = "east"; 
    }    
}
