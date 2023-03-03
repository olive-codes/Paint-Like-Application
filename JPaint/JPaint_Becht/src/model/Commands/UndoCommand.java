package model.Commands;

import model.interfaces.ICommand;

public class UndoCommand implements ICommand {

    public void execute () {
        CommandHistory.undo();
    }
    
}
