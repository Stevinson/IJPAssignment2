/**
 * Class which represents a command that was entered by the user into the 
 * TextField in the user interface.
 * 
 * @author Edward Stevinson
 * @version 1
 * 
 * Adapted from Barnes and Kolling's class of the same name (Chapter 6, Objects First With Java, 2012)
 */

public class Command {
	
	private CommandWord commandWord;
    private String secondWord;

    /**
     * Constructs a command object.
     * @param commandWord The CommandWord (listed in CommandWord class)
     * @param secondWord The second word of the command.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word.
     * @return The command word.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * Return the (optional) second word of the text entered.
     * @return The second word of this command.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Method that returns true if the command does not match any of the commands 
     * listed in the CommandWord class.
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Checks for whether the text has a second word.
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
