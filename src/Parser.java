/**
 * A parser to interpret text as commands - and creates objects of class Command that represent these commands.
 * 
 * @author Edward Stevinson
 * @version 1
 * 
 * Adapted from Barnes and Kolling's class of the same name (Chapter 6, Objects First With Java, 2012)
 */

public class Parser {
	
	private CommandWords commands;  // holds all valid command words

    /**
     *  Create a parser to read from the commandBox TextField
     */
    public Parser() 
    {
        commands = new CommandWords();
    }

    /**
     * Get the text command.
     * @param commandSentence The text.
     * @return The text command from the user.
     */
    public Command getCommand(String commandSentence) 
    {
        String word1 = null;
        String word2 = null;

        // Make the text input insensitive to case 
        commandSentence = commandSentence.toLowerCase();
        // Separate the input into separate words
        String[] commandWords = commandSentence.split(" ");
        
        // Find up to two words
        if(commandWords[0]!=null) {
            word1 = commandWords[0];
            // If there is another word and it is not null...
            if(commandWords.length > 1 && commandWords[1]!=null) {
                word2 = commandWords[1];   
            }
        }
        
        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Print out a list of valid command words.
     * @return A String of all the valid command words.
     */
    public String listCommands()
    {
        return commands.showAll();
    }

    
}
