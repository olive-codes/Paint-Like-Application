package model.Commands;
import model.interfaces.ICommand;


public class RedoCommand implements ICommand {

    public void execute () {
        CommandHistory.redo();
    }
    
}
