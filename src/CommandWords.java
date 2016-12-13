import java.util.HashMap;

/**
 * Maps between command words and the associated CommandWord. 
 * 
 * @author Edward Stevinson
 * @version 1
 *
 *  Adapted from Barnes and Kolling's class of the same name (Chapter 6, Objects First With Java, 2012)
 */

public class CommandWords {
	 

    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor that creates a HashMap of all the command words listed in the CommandWord class.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Return the CommandWord associated with a string input parameter.
     * @param commandWord The string input parameter.
     * @return The CommandWord corresponding to the String parameter.
     */
    public CommandWord getCommandWord(String commandWord) 
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check whether a string is a valid command word. 
     * @param string The string to check.
     * @return Returns true if the string is a valid command word.
     */
    public boolean isCommand(String string)
    {
        return validCommands.containsKey(string);
    }
    
    /**
     * Return all the commands, separated by a space, as a String.
     * @return A String of all the commands, each separated by a space.
     */
    public String showAll() 
    {
    	String commandList = "";
        for(String command : validCommands.keySet()) {
            commandList += command + " ";
        }
        return commandList;
    }
}
