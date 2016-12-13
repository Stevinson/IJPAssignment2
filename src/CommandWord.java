/**
 * Enumerated type that defines all the commands in the application. Used so as to decouple the 
 * application logic from a particular natural language.
 * 
 * @author Edward Stevinson
 * @version 1
 */

public enum CommandWord {
	
    // The commands words with their user interface string
    HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), PICK("pick"), DROP("drop"), INFO("info"); 
    
    private String commandString;
    
    /**
     * Constructor which initialises the command word with its corresponding string.
     * @param commandString The first word (the command)
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * Returns the command word as a string
     * @return The command word.
     */
    public String toString()
    {
        return commandString;
    }
}
